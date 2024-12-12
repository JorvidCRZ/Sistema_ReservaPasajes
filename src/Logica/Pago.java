package Logica;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class Pago extends Conexion {
	static Connection conn;
	static PreparedStatement pst;
	static ResultSet rs;

	private String metodoPago;
	private String fechaPago;
	private String nombre;
	private String apellido;
	private Reserva reserva;
	private double montoPago;
	private double igv;
	private double montoTotal;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public double getMontoTotal() {
		return montoTotal;
	}

	public void setMontoTotal(double montoTotal) {
		this.montoTotal = montoTotal;
	}

	public Reserva getReserva() {
		return reserva;
	}

	public void setReserva(Reserva reserva) {
		this.reserva = reserva;
	}

	public double getMontoPago() {
		return montoPago;
	}

	public void setMontoPago(double montoPago) {
		this.montoPago = montoPago;
	}

	public String getMetodoPago() {
		return metodoPago;
	}

	public void setMetodoPago(String metodoPago) {
		this.metodoPago = metodoPago;
	}

	public double getIgv() {
		return igv;
	}

	public void setIgv(double igv) {
		this.igv = igv;
	}

	public String getFechaPago() {
		return fechaPago;
	}

	public void setFechaPago(String fechaPago) {
		this.fechaPago = fechaPago;
	}

	private void obtenerNomApePasajero() throws Exception {
		try (Connection conn = Conexion.Conectar()) {
			String query = "SELECT nombres_pasajero, apellido_pasajero FROM pasajero WHERE reserva_id = ? ORDER BY pasajero_id LIMIT 1";
			try (PreparedStatement pst = conn.prepareStatement(query)) {
				pst.setInt(1, this.reserva.getReservaId());
				try (ResultSet rs = pst.executeQuery()) {
					if (rs.next()) {
						this.nombre = rs.getString("nombres_pasajero");
						this.apellido = rs.getString("apellido_pasajero");
					}
				}
			}
		} catch (Exception e) {
			throw e;
		}
	}

	private double obtenerMontoTotal(int i) throws Exception {
		double montoTotal = 0.0;
		try (Connection conn = Conexion.Conectar()) {
			String query = "SELECT SUM(monto_total) FROM detalle_reserva WHERE reserva_id = ?";
			try (PreparedStatement pst = conn.prepareStatement(query)) {
				pst.setInt(1, i);
				try (ResultSet rs = pst.executeQuery()) {
					if (rs.next()) {
						montoTotal = rs.getDouble(1);
					}
				}
			}
		} catch (Exception e) {
			throw e;
		}
		return montoTotal;
	}

	public List<Pago> mostrar() throws Exception {
		List<Pago> lista = new ArrayList<>();
		try (Connection conn = Conexion.Conectar();
				PreparedStatement pst = conn.prepareStatement("SELECT * FROM pago ORDER BY reserva_id");
				ResultSet rs = pst.executeQuery()) {
			while (rs.next()) {
				Pago pago = new Pago();
				Reserva reserva = new Reserva();
				reserva.setReservaId(rs.getInt("reserva_id"));
				pago.setReserva(reserva);
				pago.setMontoPago(rs.getDouble("monto_pago"));
				pago.setMetodoPago(rs.getString("metodo_pago"));
				pago.setIgv(rs.getDouble("igv"));
				pago.setFechaPago(rs.getString("fecha_pago"));
				pago.obtenerNomApePasajero();
				double montoTotal = obtenerMontoTotal(pago.getReserva().getReservaId());
				pago.setMontoTotal(montoTotal);
				lista.add(pago);
			}
		} catch (Exception e) {
			throw e;
		}
		return lista;
	}

	public Pago buscarPorID(int reservaId) throws Exception {
		Pago pago = null;
		try (Connection conn = Conexion.Conectar()) {
			String query = "SELECT * FROM reserva WHERE reserva_id = ?";
			try (PreparedStatement pst = conn.prepareStatement(query)) {
				pst.setInt(1, reservaId);
				try (ResultSet rs = pst.executeQuery()) {
					if (rs.next()) {
						Reserva reserva = new Reserva();
						reserva.setReservaId(rs.getInt("reserva_id"));
						String queryPago = "SELECT * FROM pago WHERE reserva_id = ?";
						try (PreparedStatement pstPago = conn.prepareStatement(queryPago)) {
							pstPago.setInt(1, reservaId);
							try (ResultSet rsPago = pstPago.executeQuery()) {
								if (rsPago.next()) {
									pago = new Pago();
									pago.setReserva(reserva);
									pago.setMontoPago(rsPago.getDouble("monto_pago"));
									pago.setMetodoPago(rsPago.getString("metodo_pago"));
									pago.setIgv(rsPago.getDouble("igv"));
									pago.setFechaPago(rsPago.getString("fecha_pago"));
									pago.obtenerNomApePasajero();
									double montoTotal = obtenerMontoTotal(reservaId);
									pago.setMontoTotal(montoTotal);
								}
							}
						}
					}
				}
			}
		} catch (Exception e) {
			throw e;
		}
		return pago;
	}

	public List<Pago> buscarPorNombreApellido(String search) throws Exception {
		List<Pago> lista = new ArrayList<>();
		if (search == null || search.trim().isEmpty()) {
			System.out.println("El campo de búsqueda está vacío.");
			return lista;
		}
		try (Connection conn = Conexion.Conectar()) {
			String query = "SELECT p.* FROM pago p " + "INNER JOIN reserva r ON p.reserva_id = r.reserva_id "
					+ "WHERE r.reserva_id IN (" + "SELECT reserva_id FROM pasajero "
					+ "WHERE nombres_pasajero LIKE ? OR apellido_pasajero LIKE ?)";
			try (PreparedStatement pst = conn.prepareStatement(query)) {
				pst.setString(1, "%" + search + "%");
				pst.setString(2, "%" + search + "%");
				try (ResultSet rs = pst.executeQuery()) {
					if (!rs.next()) {
						System.out.println("No se encontraron pagos con el nombre o apellido proporcionado.");
						return lista;
					}
					do {
						Pago pago = new Pago();
						Reserva reserva = new Reserva();
						reserva.setReservaId(rs.getInt("reserva_id"));
						pago.setReserva(reserva);
						pago.setMontoPago(rs.getDouble("monto_pago"));
						pago.setMetodoPago(rs.getString("metodo_pago"));
						pago.setIgv(rs.getDouble("igv"));
						pago.setFechaPago(rs.getString("fecha_pago"));
						pago.obtenerNomApePasajero();
						double montoTotal = obtenerMontoTotal(reserva.getReservaId());
						pago.setMontoTotal(montoTotal);
						lista.add(pago);
					} while (rs.next());
				}
			}
		} catch (Exception e) {
			throw e;
		}

		return lista;
	}

	public void eliminarPago(int reservaId) {
		Connection conn = null;
		PreparedStatement pst = null;
		try {
			conn = Conexion.Conectar();
			String sql = "DELETE FROM pago WHERE reserva_id = ?";
			pst = conn.prepareStatement(sql);
			pst.setInt(1, reservaId);
			pst.executeUpdate();
		} catch (SQLException e) {
		} finally {
			try {
				if (pst != null)
					pst.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
			}
		}
	}

	public static void insertarPago() {
		try {
			String reservaIdStr = JOptionPane.showInputDialog("Ingrese el ID de la reserva:");
			if (reservaIdStr == null || reservaIdStr.isEmpty()) {
				JOptionPane.showMessageDialog(null, "El ID de la reserva es obligatorio.");
				return;
			}
			int reservaId = Integer.parseInt(reservaIdStr);
			String fechaPago = JOptionPane.showInputDialog("Ingrese la fecha de pago (YYYY-MM-DD):");
			if (fechaPago == null || fechaPago.isEmpty()) {
				JOptionPane.showMessageDialog(null, "La fecha de pago es obligatoria.");
				return;
			}
			String metodoPago = JOptionPane.showInputDialog("Ingrese el método de pago (Ej: Tarjeta de Débito):");
			if (metodoPago == null || metodoPago.isEmpty()) {
				JOptionPane.showMessageDialog(null, "El método de pago es obligatorio.");
				return;
			}
			System.out.println("Reserva ID: " + reservaId);
			System.out.println("Fecha de Pago: " + fechaPago);
			System.out.println("Método de Pago: " + metodoPago);
			insertarPagoEnBaseDeDatos(reservaId, fechaPago, metodoPago);
			Pago pago = new Pago();
			Reserva reserva = new Reserva();
			reserva.setReservaId(reservaId);
			pago.setReserva(reserva);
			pago.insertarDetalleReserva(reservaId); 
			JOptionPane.showMessageDialog(null, "Pago insertado con éxito.");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error al insertar el pago: " + e.getMessage());
			e.printStackTrace();
		}
	}

	private static void insertarPagoEnBaseDeDatos(int reservaId, String fechaPago, String metodoPago)
			throws SQLException {
		String sql = "{CALL InsertarPago(?, ?, ?)}";
		try (Connection conn = Conexion.Conectar(); CallableStatement cstmt = conn.prepareCall(sql)) {
			cstmt.setInt(1, reservaId);
			cstmt.setString(2, fechaPago);
			cstmt.setString(3, metodoPago);
			cstmt.execute();

			System.out.println("Pago insertado correctamente.");
		} catch (SQLException e) {
			System.err.println("Error al ejecutar el procedimiento InsertarPago: " + e.getMessage());
			throw new SQLException("Error al ejecutar el procedimiento InsertarPago: " + e.getMessage());
		}
	}

	private void insertarDetalleReserva(int reservaId) {
		Connection con = null;
		CallableStatement stmt = null;
		try {
			con = Conexion.Conectar();
			String sql = "{CALL InsertarDetalleReserva(?)}";
			stmt = con.prepareCall(sql);
			stmt.setInt(1, reservaId);
			stmt.execute();
			System.out.println("Detalle de la reserva insertado/actualizado correctamente.");
		} catch (SQLException ex) {
			System.err.println("Error al ejecutar el procedimiento almacenado: " + ex.getMessage());
			ex.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
				if (con != null)
					con.close();
			} catch (SQLException ex) {
				System.err.println("Error al cerrar recursos: " + ex.getMessage());
			}
		}
	}

	public boolean actualizarReserva(int reservaId, String fechaPago, String metodoPago, double montoPago, double igv,
			double montoTotal, String nombresPasajero, String apellidoPasajero) {
		String sql = "UPDATE pago pg " + "JOIN reserva r ON r.reserva_id = pg.reserva_id "
				+ "JOIN pasajero psg ON psg.reserva_id = r.reserva_id "
				+ "JOIN detalle_reserva dr ON dr.reserva_id = r.reserva_id " + "SET " + "pg.fecha_pago = ?, "
				+ "pg.metodo_pago = ?, " + "pg.monto_pago = ?, " + "pg.igv = ?, " + "dr.monto_total = ?, "
				+ "psg.nombres_pasajero = ?, " + "psg.apellido_pasajero = ? " + "WHERE r.reserva_id = ?";
		try (Connection con = Conexion.Conectar(); PreparedStatement ps = con.prepareStatement(sql)) {
			con.setAutoCommit(false);
			ps.setString(1, fechaPago);
			ps.setString(2, metodoPago);
			ps.setDouble(3, montoPago);
			ps.setDouble(4, igv);
			ps.setDouble(5, montoTotal);
			ps.setString(6, nombresPasajero);
			ps.setString(7, apellidoPasajero);
			ps.setInt(8, reservaId);
			int rowsUpdated = ps.executeUpdate();
			if (rowsUpdated > 0) {
				con.commit();
				return true;
			} else {
				con.rollback();
				return false;
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
			return false;
		}
	}
}
