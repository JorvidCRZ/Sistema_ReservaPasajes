package Logica;
import java.sql.*;
import javax.swing.JOptionPane;
public class Procesos extends Conexion {
	PreparedStatement pst; 
	Connection con;
	ResultSet res;

	public boolean validarUsuario(String usuario, String clave) {
        try {     
            con = Conexion.Conectar(con);  
            String consulta = "SELECT * FROM USUARIO WHERE user_name = '" + usuario 
            + "' AND contraseña = '" + clave + "';";
            pst = con.prepareStatement(consulta);
            res = pst.executeQuery();
            if (res.next()) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "error");
            return false;
        } finally {
            try {
                if (res != null) res.close();
                if (pst != null) pst.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error finalizando recursos: " + e.toString());
            }
        }
	}
	
}
	
