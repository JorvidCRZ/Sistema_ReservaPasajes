package Logica;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class Usuario extends Conexion {
	private int idUsuario;
	private String nombres;
	private String apellidos;
	private String direccion;
	private String nacionalidad;
	private String email;
	private String telefono;
	private String contrasena;
	private String userName;
	private static Usuario usuarioAutenticado;
	PreparedStatement pst;
	Connection con;
	ResultSet res;

	public static Usuario getUsuarioAutenticado() {
		return usuarioAutenticado;
	}

	public static void setUsuarioAutenticado(Usuario usuario) {
		usuarioAutenticado = usuario;
	}

	public Usuario(String nombres, String apellidos, String direccion, String nacionalidad, String email,
			String telefono, String contrasena, String userName) {
		this.nombres = nombres;
		this.apellidos = apellidos;
		this.direccion = direccion;
		this.nacionalidad = nacionalidad;
		this.email = email;
		this.telefono = telefono;
		this.contrasena = contrasena;
		this.userName = userName;
		;
	}

	public Usuario() {
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getNacionalidad() {
		return nacionalidad;
	}

	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public boolean validarUsuario(String usuario, String clave) {
		try {
			con = Conexion.Conectar();
			String consulta = "SELECT * FROM usuario WHERE user_name = ? AND contraseña = ?";
			pst = con.prepareStatement(consulta);
			pst.setString(1, usuario);
			pst.setString(2, clave);
			res = pst.executeQuery();
			if (res.next()) {
				// Si la validación es correcta, creamos el objeto Usuario y lo almacenamos
				Usuario usuarioAutenticado = new Usuario();
				usuarioAutenticado.setIdUsuario(res.getInt("id_usuario"));
				usuarioAutenticado.setNombres(res.getString("nombres"));
				usuarioAutenticado.setApellidos(res.getString("apellidos"));
				usuarioAutenticado.setDireccion(res.getString("direccion"));
				usuarioAutenticado.setNacionalidad(res.getString("nacionalidad"));
				usuarioAutenticado.setEmail(res.getString("email"));
				usuarioAutenticado.setTelefono(res.getString("telefono"));
				usuarioAutenticado.setUserName(res.getString("user_name"));
				usuarioAutenticado.setContrasena(res.getString("contraseña"));
				// Guardamos al usuario autenticado en la variable estática
				Usuario.setUsuarioAutenticado(usuarioAutenticado);
				return true; // Usuario validado correctamente
			} else {
				return false; // Credenciales incorrectas
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
			return false;
		} finally {
			try {
				if (res != null)
					res.close();
				if (pst != null)
					pst.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "Error finalizando recursos: " + e.toString());
			}
		}
	}

	public boolean emailRegistrado(String email) {
		try {
			con = Conexion.Conectar();
			String cheksql = "SELECT COUNT(*) FROM usuario WHERE email = ?";
			PreparedStatement checkStmt = con.prepareStatement(cheksql);
			checkStmt.setString(1, email);
			ResultSet res = checkStmt.executeQuery();
			res.next();
			boolean exists = res.getInt(1) > 0;
			res.close();
			checkStmt.close();
			return exists;
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return false;
	}

	public boolean registrarUsuario(String nombres, String apellidos, String direccion, String nacionalidad,
			String email, String telefono, String contrasena, String user_name) {
		try {
			con = Conexion.Conectar();
			String sql = "INSERT INTO usuario(nombres, apellidos, direccion, nacionalidad, email, telefono, contraseña, user_name) "
					+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, nombres);
			pst.setString(2, apellidos);
			pst.setString(3, direccion);
			pst.setString(4, nacionalidad);
			pst.setString(5, email);
			pst.setString(6, telefono);
			pst.setString(7, contrasena);
			pst.setString(8, user_name);
			int resultado = pst.executeUpdate();
			return resultado > 0;
		} catch (SQLException e2) {
			e2.printStackTrace();
			return false;
		} finally {
			try {
				if (pst != null)
					pst.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
