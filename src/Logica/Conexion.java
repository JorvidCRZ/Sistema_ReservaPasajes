package Logica;

import java.sql.*;
import javax.swing.JOptionPane;

public class Conexion {
	static String driver = "com.mysql.cj.jdbc.Driver";
	static String cadena = "jdbc:mysql://localhost:3306/Reserva_Pasajes";
	static String usuario = "root";
	static String clave = "JorvixVonmCRZ2023*";
	static Connection conn;
	static PreparedStatement pst;
	static ResultSet sen;

	public static Connection Conectar(Connection conn) {
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(cadena, usuario, clave);
		} catch (ClassNotFoundException e1) {
			JOptionPane.showMessageDialog(null, "Error en el driver: " + e1);
		} catch (SQLException e2) {
			JOptionPane.showMessageDialog(null, "Error en la conexion: " + e2);
		}
		return conn;
	}

	public static PreparedStatement Sentencia(PreparedStatement pst, String cadena) {
		try {
			conn = Conectar(conn);
			pst = conn.prepareStatement(cadena);
		} catch (SQLException e2) {
			JOptionPane.showMessageDialog(null, "Error en el proceso SQL: " + e2);
		}
		return pst;
	}

	public static ResultSet Consulta(String cadena, String parametro) {
		ResultSet res = null;
		try {
			conn = Conectar(conn);
			PreparedStatement pst = conn.prepareStatement(cadena);
			pst.setString(1, parametro);
			res = pst.executeQuery();

		} catch (SQLException e2) {
			JOptionPane.showMessageDialog(null, "Error en el proceso de consulta: " + e2);
		}
		return res;
	}
}
