package Logica;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Pasajero extends Conexion {

	private int id;
	private String nombresPasajero;
	private String apellidoPasajero;
	private String nroAsiento;
	private String nacionalidadPasajero;
	private Date fecNac;
	private String clase;
	private String dni;
	private String pasaporte;
	private Reserva reserva;
	private Vuelo vuelo;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombresPasajero() {
		return nombresPasajero;
	}

	public void setNombresPasajero(String nombresPasajero) {
		this.nombresPasajero = nombresPasajero;
	}

	public String getApellidoPasajero() {
		return apellidoPasajero;
	}

	public void setApellidoPasajero(String apellidoPasajero) {
		this.apellidoPasajero = apellidoPasajero;
	}

	public String getNroAsiento() {
		return nroAsiento;
	}

	public void setNroAsiento(String nroAsiento) {
		this.nroAsiento = nroAsiento;
	}

	public String getNacionalidadPasajero() {
		return nacionalidadPasajero;
	}

	public void setNacionalidadPasajero(String nacionalidadPasajero) {
		this.nacionalidadPasajero = nacionalidadPasajero;
	}

	public Date getFecNa() {
		return fecNac;
	}

	public void setFecNac(Date fecNac) {
		this.fecNac = fecNac;
	}

	public String getClase() {
		return clase;
	}

	public void setClase(String clase) {
		this.clase = clase;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getPasaporte() {
		return pasaporte;
	}

	public void setPasaporte(String pasaporte) {
		this.pasaporte = pasaporte;
	}

	public Reserva getReserva() {
		return reserva;
	}

	public void setReserva(Reserva reserva) {
		this.reserva = reserva;
	}

	public Vuelo getVuelo() {
		return vuelo;
	}

	public void setVuelo(Vuelo vuelo) {
		this.vuelo = vuelo;
	}

	public void registrar(Pasajero pasajero) throws SQLException {
		String query = "INSERT INTO Pasajero(reserva_id, vuelo_id, nombres_pasajero, apellido_pasajero, nro_asiento, "
				+ "nacionalidad_pasajero, fech_nacimiento_pasajero, clase, dni, pasaporte) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

		try (Connection conn = Conexion.Conectar(); PreparedStatement pst = conn.prepareStatement(query)) {
			pst.setInt(1, pasajero.getReserva().getReservaId());
			pst.setInt(2, pasajero.getVuelo().getIdVuelo());
			pst.setString(3, pasajero.getNombresPasajero());
			pst.setString(4, pasajero.getApellidoPasajero());
			pst.setString(5, pasajero.getNroAsiento());
			pst.setString(6, pasajero.getNacionalidadPasajero());
			pst.setDate(7, pasajero.getFecNa());
			pst.setString(8, pasajero.getClase());
			pst.setString(9, pasajero.getDni());
			pst.setString(10, pasajero.getPasaporte());
			pst.executeUpdate();
		} catch (SQLException e) {
			throw new SQLException("Error al registrar el pasajero: " + e.getMessage(), e);
		}
	}

	public void modificar(Pasajero pasajero) throws SQLException {
		String query = "UPDATE Pasajero SET reserva_id = ?, vuelo_id = ?, nombres_pasajero = ?, apellido_pasajero = ?, "
				+ "nro_asiento = ?, nacionalidad_pasajero = ?, fech_nacimiento_pasajero = ?, clase = ?, dni = ?, pasaporte = ? WHERE id = ?;";

		try (Connection conn = Conexion.Conectar(); PreparedStatement pst = conn.prepareStatement(query)) {
			pst.setInt(1, pasajero.getReserva().getReservaId());
			pst.setInt(2, pasajero.getVuelo().getIdVuelo());
			pst.setString(3, pasajero.getNombresPasajero());
			pst.setString(4, pasajero.getApellidoPasajero());
			pst.setString(5, pasajero.getNroAsiento());
			pst.setString(6, pasajero.getNacionalidadPasajero());
			pst.setDate(7, pasajero.getFecNa());
			pst.setString(8, pasajero.getClase());
			pst.setString(9, pasajero.getDni());
			pst.setString(10, pasajero.getPasaporte());
			pst.setInt(11, pasajero.getId());
			pst.executeUpdate();
		} catch (SQLException e) {
			throw new SQLException("Error al modificar el pasajero: " + e.getMessage(), e);
		}
	}

	public void eliminarPasajero(int reservaId) {
		Connection conn = null;
		PreparedStatement pst = null;
		try {
			conn = Conexion.Conectar();
			String sql = "DELETE FROM pasajero WHERE reserva_id = ?";
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

	public List<Pasajero> listar() throws SQLException {
		List<Pasajero> lista = new ArrayList<>();
		try (Connection conn = Conexion.Conectar();
				PreparedStatement pst = conn
						.prepareStatement("SELECT pasajero_id, reserva_id, nombres_pasajero, apellido_pasajero,"
								+ " nro_asiento, nacionalidad_pasajero, clase, pasaporte FROM pasajero")) {
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				Pasajero pasajero = new Pasajero();
				Reserva reserva = new Reserva();
				pasajero.setId(rs.getInt("pasajero_id"));
				reserva.setReservaId(rs.getInt("reserva_id"));
				pasajero.setReserva(reserva);
				pasajero.setNombresPasajero(rs.getString("nombres_pasajero"));
				pasajero.setApellidoPasajero(rs.getString("apellido_pasajero"));
				pasajero.setNroAsiento(rs.getString("nro_asiento"));
				pasajero.setNacionalidadPasajero(rs.getString("nacionalidad_pasajero"));
				pasajero.setClase(rs.getString("clase"));
				pasajero.setPasaporte(rs.getString("pasaporte"));
				lista.add(pasajero);
			}

		} catch (SQLException e) {
			throw e;
		}
		return lista;

	}

	public List<Pasajero> obtenerPasajerosPorReservaId(int reservaId) throws SQLException {
		String query = "SELECT * FROM pasajero WHERE reserva_id = ?";
		List<Pasajero> pasajeros = new ArrayList<>();
		try (Connection conn = Conexion.Conectar(); PreparedStatement pst = conn.prepareStatement(query)) {
			pst.setInt(1, reservaId);
			try (ResultSet rs = pst.executeQuery()) {
				while (rs.next()) {
					Pasajero pasajero = new Pasajero();
					pasajero.setId(rs.getInt("pasajero_id"));
					Reserva reserva = new Reserva();
					reserva.setReservaId(rs.getInt("reserva_id"));
					pasajero.setReserva(reserva);
					Vuelo vuelo = new Vuelo();
					vuelo.setIdVuelo(rs.getInt("vuelo_id"));
					pasajero.setVuelo(vuelo);
					pasajero.setNombresPasajero(rs.getString("nombres_pasajero"));
					pasajero.setApellidoPasajero(rs.getString("apellido_pasajero"));
					pasajero.setNroAsiento(rs.getString("nro_asiento"));
					pasajero.setNacionalidadPasajero(rs.getString("nacionalidad_pasajero"));
					pasajero.setFecNac(rs.getDate("fech_nacimiento_pasajero"));
					pasajero.setClase(rs.getString("clase"));
					pasajero.setDni(rs.getString("dni"));
					pasajero.setPasaporte(rs.getString("pasaporte"));
					pasajeros.add(pasajero);
				}
			}
		} catch (SQLException e) {
			throw new SQLException("Error al obtener los pasajeros: " + e.getMessage(), e);
		}
		return pasajeros;
	}
}
