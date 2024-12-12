package Logica;

import java.sql.*;

import javax.swing.JOptionPane;

public class Reserva extends Conexion {

	private int reservaId;
	private int usuarioId;
	private int vueloId;
	private String fecReserva;
	private int numPasajeros;
	private String estReserva;

	public int getReservaId() {
		return reservaId;
	}

	public void setReservaId(int reservaId) {
		this.reservaId = reservaId;
	}

	public int getUsuarioId() {
		return usuarioId;
	}

	public void setUsuarioId(int usuarioId) {
		this.usuarioId = usuarioId;
	}

	public int getVueloId() {
		return vueloId;
	}

	public void setVueloId(int vueloId) {
		this.vueloId = vueloId;
	}

	public String getFecReserva() {
		return fecReserva;
	}

	public void setFecReserva(String fecReserva) {
		this.fecReserva = fecReserva;
	}

	public int getNumPasajeros() {
		return numPasajeros;
	}

	public void setNumPasajeros(int numPasajeros) {
		this.numPasajeros = numPasajeros;
	}

	public String getEstReserva() {
		return estReserva;
	}

	public void setEstReserva(String estReserva) {
		this.estReserva = estReserva;
	}
	
	public int insertarReserva(int usuarioId, int vueloId, String fecReserva, int numeroPasajeros, String estadoReserva,String Destino)
			throws Exception {
		int idReserva = 0;
		try (Connection conn = Conexion.Conectar()) {
			if (usuarioId <= 0 || vueloId <= 0 || numeroPasajeros <= 0) {
				throw new IllegalArgumentException(
						"ID de usuario, ID de vuelo y número de pasajeros deben ser positivos.");
			}
			if (fecReserva == null || fecReserva.isEmpty() || estadoReserva == null || estadoReserva.isEmpty()) {
				throw new IllegalArgumentException("Fecha de reserva y estado de reserva no pueden estar vacíos.");
			}

			String procedimiento = "{CALL InsertarReserva(?, ?, ?, ?, ?)}";
			try (CallableStatement cst = conn.prepareCall(procedimiento)) {
				cst.setInt(1, usuarioId);
				cst.setInt(2, vueloId);
				cst.setString(3, fecReserva);
				cst.setInt(4, numeroPasajeros);
				cst.setString(5, estadoReserva);
				cst.execute();
			}

			String query = "SELECT LAST_INSERT_ID()";
			try (PreparedStatement pst = conn.prepareStatement(query); ResultSet sen = pst.executeQuery()) {
				if (sen.next()) {
					idReserva = sen.getInt(1);
				}
			}
		} catch (Exception e) {
			throw new Exception("Error al insertar reserva: " + e.getMessage(), e);
		}
		return idReserva;
	}

	public ResultSet obtenerReservas() throws SQLException {
		String query = "SELECT * FROM reserva";
		try (Connection conn = Conexion.Conectar(); PreparedStatement pst = conn.prepareStatement(query)) {
			return pst.executeQuery();
		}
	}

	public Reserva obtenerReservaPorId(int reservaId) throws SQLException {
		String query = "SELECT * FROM reserva WHERE reserva_id = ?";
		try (Connection conn = Conexion.Conectar(); PreparedStatement pst = conn.prepareStatement(query)) {
			pst.setInt(1, reservaId);
			try (ResultSet rs = pst.executeQuery()) {
				if (rs.next()) {
					Reserva reserva = new Reserva();
					reserva.setReservaId(rs.getInt("reserva_id"));
					reserva.setUsuarioId(rs.getInt("usuario_id"));
					reserva.setVueloId(rs.getInt("vuelo_id"));
					reserva.setFecReserva(rs.getString("fecha_reserva"));
					reserva.setNumPasajeros(rs.getInt("numero_pasajeros"));
					reserva.setEstReserva(rs.getString("estado_reserva"));
					return reserva;
				}
			}
		}
		return null;
	}

	public void actualizarReserva(int reservaId, int usuarioId, String destino, String fechaReserva, int numeroPasajeros, String estado) {
	    Connection conn = null;
	    PreparedStatement pstVuelo = null;
	    PreparedStatement pstReserva = null;

	    try {
	        conn = Conexion.Conectar();
	        conn.setAutoCommit(false); // Inicia la transacción

	        // Actualizar la tabla 'vuelo'
	        String sqlVuelo = "UPDATE vuelo SET destino = ? " +
	                          "WHERE vuelo_id = (SELECT vuelo_id FROM reserva WHERE reserva_id = ?)";
	        pstVuelo = conn.prepareStatement(sqlVuelo);
	        pstVuelo.setString(1, destino);
	        pstVuelo.setInt(2, reservaId);
	        int filasVuelo = pstVuelo.executeUpdate();

	        // Actualizar la tabla 'reserva'
	        String sqlReserva = "UPDATE reserva SET usuario_id = ?, fecha_reserva = ?, numero_pasajeros = ?, estado_reserva = ? " +
	                            "WHERE reserva_id = ?";
	        pstReserva = conn.prepareStatement(sqlReserva);
	        pstReserva.setInt(1, usuarioId);
	        pstReserva.setString(2, fechaReserva);
	        pstReserva.setInt(3, numeroPasajeros);
	        pstReserva.setString(4, estado);
	        pstReserva.setInt(5, reservaId);
	        int filasReserva = pstReserva.executeUpdate();

	        if (filasVuelo > 0 && filasReserva > 0) {
	            conn.commit(); // Confirmar transacción
	            JOptionPane.showMessageDialog(null, "Reserva actualizada con éxito.");
	        } else {
	            conn.rollback(); // Deshacer cambios
	            JOptionPane.showMessageDialog(null, "No se pudo actualizar la reserva.");
	        }

	    } catch (SQLException e) {
	        try {
	            if (conn != null) conn.rollback(); // Deshacer cambios en caso de error
	        } catch (SQLException rollbackEx) {
	            JOptionPane.showMessageDialog(null, "Error al realizar rollback: " + rollbackEx.getMessage());
	        }
	        JOptionPane.showMessageDialog(null, "Error al actualizar reserva: " + e.getMessage());
	    } finally {
	        try {
	            if (pstVuelo != null) pstVuelo.close();
	            if (pstReserva != null) pstReserva.close();
	            if (conn != null) conn.close();
	        } catch (SQLException e) {
	            JOptionPane.showMessageDialog(null, "Error al cerrar recursos: " + e.getMessage());
	        }
	    }
	}

	public void eliminarReserva(int reservaId) {
		Connection conn = null;
		PreparedStatement pst = null;
		try {
			conn = Conexion.Conectar();
			String sql = "DELETE FROM reserva WHERE reserva_id = ?";
			pst = conn.prepareStatement(sql);
			pst.setInt(1, reservaId);
			
			int filasAfectadas = pst.executeUpdate();
			if (filasAfectadas > 0) {
				JOptionPane.showMessageDialog(null, "Reserva eliminada con éxito.");
			} else {
				JOptionPane.showMessageDialog(null, "No se encontró una reserva con ese ID.");
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error al eliminar la reserva: " + e.getMessage());
		} finally {
			try {
				if (pst != null)
					pst.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "Error al cerrar los recursos: " + e.getMessage());
			}
		}
	}

	public boolean existeReserva(int reservaId) throws SQLException {
		String query = "SELECT COUNT(*) FROM reserva WHERE reserva_id = ?";
		try (Connection conn = Conexion.Conectar(); PreparedStatement stmt = conn.prepareStatement(query)) {
			stmt.setInt(1, reservaId);
			try (ResultSet rs = stmt.executeQuery()) {
				if (rs.next()) {
					return rs.getInt(1) > 0;
				}
			}
		}
		return false;
	}

	public void eliminarDetalleReserva(int reservaId) {
		Connection conn = null;
		PreparedStatement pst = null;
		try {
			conn = Conexion.Conectar();
			String sql = "DELETE FROM detalle_reserva WHERE reserva_id = ?";
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

}
