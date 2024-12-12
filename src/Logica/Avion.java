package Logica;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Avion extends Conexion {

	private int avionId;
	private double capacidadTotal;
	private String estadoAvion;

	public int getAvionId() {
		return avionId;
	}

	public void setAvionId(int avionId) {
		this.avionId = avionId;
	}

	public double getCapacidadTotal() {
		return capacidadTotal;
	}

	public void setCapacidadTotal(double capacidadTotal) {
		this.capacidadTotal = capacidadTotal;
	}

	public String getEstadoAvion() {
		return estadoAvion;
	}

	public void setEstadoAvion(String estadoAvion) {
		this.estadoAvion = estadoAvion;
	}

	public static List<Avion> mostrarAviones() {
		List<Avion> aviones = new ArrayList<>();
		try (Connection con = Conexion.Conectar()) {
			String sql = "SELECT * FROM avion";
			try (PreparedStatement pst = con.prepareStatement(sql)) {
				try (ResultSet rs = pst.executeQuery()) {
					while (rs.next()) {
						Avion avion = new Avion();
						avion.setAvionId(rs.getInt("avion_id"));
						avion.setCapacidadTotal(rs.getDouble("capacidad_total"));
						avion.setEstadoAvion(rs.getString("estado_avion"));
						aviones.add(avion);
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return aviones;
	}

	public static Avion getAvionById(int avionId) {
		Avion avion = null;

		try (Connection con = Conexion.Conectar()) {
			String sql = "SELECT * FROM avion WHERE avion_id = ?";
			try (PreparedStatement pst = con.prepareStatement(sql)) {
				pst.setInt(1, avionId);
				try (ResultSet rs = pst.executeQuery()) {
					if (rs.next()) {
						avion = new Avion();
						avion.setAvionId(rs.getInt("avion_id"));
						avion.setCapacidadTotal(rs.getDouble("capacidad_total"));
						avion.setEstadoAvion(rs.getString("estado_avion"));
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return avion;
	}
}
