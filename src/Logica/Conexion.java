package Logica;

import java.sql.*;
import javax.swing.JOptionPane;

public class Conexion {
    static String driver = "com.mysql.cj.jdbc.Driver";
    static String cadena = "jdbc:mysql://localhost:3306/Reserva_Pasajes";
    static String usuario = "root";
    static String clave = "JorvixVonmCRZ2023*";

    public static Connection Conectar() {
        Connection conn = null;
        try {
            Class.forName(driver); 
            conn = DriverManager.getConnection(cadena, usuario, clave); 
            System.out.println("Conexión exitosa");
        } catch (ClassNotFoundException e1) {
            JOptionPane.showMessageDialog(null, "Error en el driver: " + e1);
        } catch (SQLException e2) {
            JOptionPane.showMessageDialog(null, "Error en la conexión: " + e2);
        }
        return conn;
    }

    public static PreparedStatement Sentencia(String sql) {
        PreparedStatement pst = null;
        try {
            Connection conn = Conectar();  
            pst = conn.prepareStatement(sql);  
        } catch (SQLException e2) {
            JOptionPane.showMessageDialog(null, "Error al preparar la sentencia SQL: " + e2);
        }
        return pst;
    }

    public static ResultSet Consulta(String sql, String parametro) {
        ResultSet res = null;
        try {
            Connection conn = Conectar();
            PreparedStatement pst = conn.prepareStatement(sql); 
            pst.setString(1, parametro); 
            res = pst.executeQuery();  
        } catch (SQLException e2) {
            JOptionPane.showMessageDialog(null, "Error en la consulta SQL: " + e2);
        }
        return res;
    }

    public static void Cerrar(Connection conn) {
        if (conn != null) {
            try {
                if (!conn.isClosed()) {
                    conn.close();  
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error al cerrar la conexión: " + e);
            }
        }
    }

    public static void Cerrar(PreparedStatement pst) {
        if (pst != null) {
            try {
                pst.close();  
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error al cerrar el PreparedStatement: " + e);
            }
        }
    }

    public static void Cerrar(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close(); 
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error al cerrar el ResultSet: " + e);
            }
        }
    }
}
