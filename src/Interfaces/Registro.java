package Interfaces;
import Logica.Conexion;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JCheckBox;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.awt.Font;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseMotionAdapter;
import java.awt.CardLayout;
public class Registro extends JFrame {

	ResultSet rs;
	Statement sen;
	Connection conn;
	int xMouse, yMouse;
	private JLabel lblEmail;
	private JLabel lblUsuario;
	private JPanel contentPane;
	private JLabel lblTelefono;
	private JLabel lblDireccion;
	private JLabel lblNacionalidad;
	private JTextField txtEmail;
	private JTextField txtNombres;
	private JTextField txtUsuario;
	private JTextField txtTelefono;
	private JTextField txtApellidos;
	private JTextField txtDireccion;
	private JTextField txtNacionalidad;
	private JPasswordField passwordConfirmar;
	private JPasswordField passwordClave;
	private static final long serialVersionUID = 1L;
	DefaultTableModel tabla = new DefaultTableModel();

	public Registro() {
		setBounds(100, 100, 770, 715);
		setResizable(false);
		setUndecorated(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		contentPane = new JPanel();
		contentPane.setLayout(null);
		setContentPane(contentPane);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		JLabel lblIcon = new JLabel("");
		lblIcon.setIcon(new ImageIcon(Registro.class.getResource("/com/img/IMG1.png")));
		lblIcon.setBounds(700, 639, 60, 65);
		contentPane.add(lblIcon);

		JPanel panelAtras = new JPanel();
		panelAtras.setLayout(null);
		contentPane.add(panelAtras);
		panelAtras.setBounds(48, 0, 48, 35);
		panelAtras.setBackground(new Color(0, 165, 190));

		JLabel lblAtras = new JLabel("<");
		lblAtras.setBounds(0, 0, 48, 35);
		lblAtras.setBackground(new Color(0, 165, 190));
		lblAtras.setForeground(new Color(240, 240, 240));
		lblAtras.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblAtras.setHorizontalAlignment(SwingConstants.CENTER);
		lblAtras.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		panelAtras.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Inicio prinFrame = new Inicio();
				prinFrame.setVisible(true);
				Registro.this.dispose();
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				panelAtras.setBackground(new Color(0, 4, 84));
				lblAtras.setForeground(new Color(240, 240, 240));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				panelAtras.setBackground(new Color(0, 165, 190));
			}
		});
		panelAtras.add(lblAtras);

		JPanel panelExit = new JPanel();
		panelExit.setLayout(null);
		panelExit.setBounds(0, 0, 48, 35);
		contentPane.add(panelExit);

		JLabel lblX = new JLabel("X");
		lblX.setBounds(0, 0, 48, 35);
		lblX.setOpaque(true);
		lblX.setBackground(new Color(0, 165, 190));
		lblX.setForeground(new Color(240, 240, 240));
		lblX.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblX.setHorizontalAlignment(SwingConstants.CENTER);
		lblX.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		panelExit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				lblX.setBackground(new Color(0, 4, 84));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				lblX.setBackground(new Color(0, 165, 190));
			}
		});
		panelExit.add(lblX);

		JLabel lblApellidos = new JLabel("Apellidos");
		lblApellidos.setBounds(200, 115, 84, 15);
		lblApellidos.setFont(new Font("Trebuchet MS", Font.BOLD, 15));
		contentPane.add(lblApellidos);

		JLabel lblNombres = new JLabel("Nombres");
		lblNombres.setBounds(390, 115, 70, 15);
		lblNombres.setFont(new Font("Trebuchet MS", Font.BOLD, 15));
		contentPane.add(lblNombres);

		txtApellidos = new JTextField();
		txtApellidos.setBounds(200, 140, 179, 25);
		txtApellidos.setFont(new Font("Trebuchet MS", Font.BOLD, 15));
		contentPane.add(txtApellidos);
		txtApellidos.setColumns(10);

		txtNombres = new JTextField();
		txtNombres.setBounds(391, 140, 179, 25);
		txtNombres.setFont(new Font("Trebuchet MS", Font.BOLD, 15));
		contentPane.add(txtNombres);
		txtNombres.setColumns(10);

		lblUsuario = new JLabel("Usuario");
		lblUsuario.setBounds(200, 415, 60, 15);
		lblUsuario.setFont(new Font("Trebuchet MS", Font.BOLD, 15));
		contentPane.add(lblUsuario);

		txtUsuario = new JTextField();
		txtUsuario.setColumns(10);
		txtUsuario.setBounds(200, 440, 370, 25);
		txtUsuario.setFont(new Font("Tahoma", Font.BOLD, 11));
		contentPane.add(txtUsuario);

		lblDireccion = new JLabel("Dirección");
		lblDireccion.setBounds(200, 175, 83, 13);
		lblDireccion.setFont(new Font("Trebuchet MS", Font.BOLD, 15));
		contentPane.add(lblDireccion);

		txtDireccion = new JTextField();
		txtDireccion.setColumns(10);
		txtDireccion.setBounds(200, 200, 370, 25);
		txtDireccion.setFont(new Font("Trebuchet MS", Font.BOLD, 15));
		contentPane.add(txtDireccion);

		lblNacionalidad = new JLabel("Nacionalidad");
		lblNacionalidad.setBounds(200, 295, 96, 15);
		lblNacionalidad.setFont(new Font("Trebuchet MS", Font.BOLD, 15));
		contentPane.add(lblNacionalidad);

		txtNacionalidad = new JTextField();
		txtNacionalidad.setColumns(10);
		txtNacionalidad.setBounds(200, 320, 370, 25);
		txtNacionalidad.setFont(new Font("Tahoma", Font.BOLD, 11));
		contentPane.add(txtNacionalidad);

		lblTelefono = new JLabel("Teléfono");
		lblTelefono.setBounds(200, 235, 83, 15);
		lblTelefono.setFont(new Font("Trebuchet MS", Font.BOLD, 15));
		contentPane.add(lblTelefono);

		txtTelefono = new JTextField();
		txtTelefono.setColumns(10);
		txtTelefono.setBounds(200, 260, 370, 25);
		txtTelefono.setFont(new Font("Tahoma", Font.BOLD, 11));
		contentPane.add(txtTelefono);

		lblEmail = new JLabel("Email");
		lblEmail.setBounds(200, 355, 45, 15);
		lblEmail.setFont(new Font("Trebuchet MS", Font.BOLD, 15));
		contentPane.add(lblEmail);

		txtEmail = new JTextField();
		txtEmail.setColumns(10);
		txtEmail.setBounds(200, 380, 370, 25);
		txtEmail.setFont(new Font("Tahoma", Font.BOLD, 11));
		contentPane.add(txtEmail);

		JLabel lblContrasena = new JLabel("Contraseña");
		lblContrasena.setBounds(200, 475, 83, 15);
		lblContrasena.setFont(new Font("Trebuchet MS", Font.BOLD, 15));
		contentPane.add(lblContrasena);

		passwordClave = new JPasswordField();
		passwordClave.setBounds(200, 500, 370, 25);
		passwordClave.setFont(new Font("Tahoma", Font.BOLD, 11));
		contentPane.add(passwordClave);

		JLabel lblConfirmar = new JLabel("Confirmar contraseña");
		lblConfirmar.setBounds(200, 535, 166, 15);
		lblConfirmar.setFont(new Font("Trebuchet MS", Font.BOLD, 15));
		contentPane.add(lblConfirmar);

		passwordConfirmar = new JPasswordField();
		passwordConfirmar.setBounds(200, 560, 370, 25);
		passwordConfirmar.setFont(new Font("Tahoma", Font.BOLD, 11));
		contentPane.add(passwordConfirmar);

		JCheckBox chckbxTerminos = new JCheckBox("Aceptar los Términos y Condiciones");
		chckbxTerminos.setOpaque(false); 
		chckbxTerminos.setBorderPainted(false); 
		chckbxTerminos.setContentAreaFilled(false);
		chckbxTerminos.setBounds(200, 595, 201, 25);
		contentPane.add(chckbxTerminos);

		JLabel lblRegistrar = new JLabel("Registrarse");
		lblRegistrar.setOpaque(true);
		lblRegistrar.setBounds(400, 627, 170, 60);
		lblRegistrar.setBackground(new Color(0, 165, 190));
		lblRegistrar.setForeground(new Color(255, 255, 255));
		lblRegistrar.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblRegistrar.setHorizontalAlignment(SwingConstants.CENTER);
		lblRegistrar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblRegistrar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				lblRegistrar.setBackground(new Color(0, 120, 140));
				String email = txtEmail.getText();
				String nombres = txtNombres.getText();
				String usuario = txtUsuario.getText();
				String telefono = txtTelefono.getText();
				String direccion = txtDireccion.getText();
				String apellidos = txtApellidos.getText();
				String nacionalidad = txtNacionalidad.getText();
				String contrasena = new String(passwordClave.getPassword());
				String confirmar = new String(passwordConfirmar.getPassword());

				if (apellidos.isEmpty() || nombres.isEmpty() || usuario.isEmpty() || direccion.isEmpty()
						|| contrasena.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Por favor, complete todos los campos obligatorios.", "Error",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
				if (!contrasena.equals(confirmar)) {
					JOptionPane.showMessageDialog(null, "Las contraseñas no coinciden.", "Error",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
				try {
					Connection conn = Conexion.Conectar(null);
					String checkSql = "SELECT COUNT(*) FROM usuario WHERE email = ?";
					PreparedStatement checkStmt = conn.prepareStatement(checkSql);
					checkStmt.setString(1, email);
					ResultSet rs = checkStmt.executeQuery();
					rs.next();
					if (rs.getInt(1) > 0) {
						JOptionPane.showMessageDialog(null, "Ya existe un usuario registrado con este correo: " + email,
								"Advertencia", JOptionPane.WARNING_MESSAGE);
					} else {
						String sql = "INSERT INTO usuario (nombres, apellidos, direccion, nacionalidad, telefono, contraseña, user_name,email) VALUES (?, ?, ?, ?, ?, ?, ?,?)";
						PreparedStatement stmt = conn.prepareStatement(sql);
						stmt.setString(1, nombres);
						stmt.setString(2, apellidos);
						stmt.setString(3, direccion);
						stmt.setString(4, nacionalidad);
						stmt.setString(5, telefono);
						stmt.setString(6, contrasena);
						stmt.setString(7, usuario);
						stmt.setString(8, email);

						int resultado = stmt.executeUpdate();
						if (resultado > 0) {
							JOptionPane.showMessageDialog(null, "Registro exitoso.");
							txtApellidos.setText("");
							txtNombres.setText("");
							txtUsuario.setText("");
							txtNacionalidad.setText("");
							Inicio prinFrame = new Inicio();
							prinFrame.setVisible(true);
							Registro.this.dispose();
						}
					}
				} catch (SQLException ex) {
					JOptionPane.showMessageDialog(null, "Error al registrar: " + ex.getMessage(), "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				lblRegistrar.setBackground(new Color(0, 120, 140));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				lblRegistrar.setBackground(new Color(0, 120, 140));
			}
		});
		contentPane.add(lblRegistrar);

		JLabel lblLogo = new JLabel("");
		lblLogo.setBounds(610, 9, 150, 136);
		lblLogo.setIcon(new ImageIcon(Registro.class.getResource("/com/img/LOGO2.png")));
		contentPane.add(lblLogo);

		JLabel lblIImgUser = new JLabel("");
		lblIImgUser.setBounds(328, 9, 96, 95);
		lblIImgUser.setBackground(new Color(0, 0, 0));
		lblIImgUser.setForeground(new Color(255, 0, 0));
		lblIImgUser.setIcon(new ImageIcon(Registro.class.getResource("/com/img/Icon.png")));
		contentPane.add(lblIImgUser);

		JLabel lblFondo = new JLabel("");
		lblFondo.setBounds(0, 0, 770, 715);
		lblFondo.setBackground(new Color(27, 31, 94));
		lblFondo.setFont(new Font("Dialog", Font.BOLD, 16));
		lblFondo.setIcon(new ImageIcon(Registro.class.getResource("/com/img/city01.png")));
		contentPane.add(lblFondo);

		JPanel panelHeader = new JPanel();
		panelHeader.setBounds(96, 0, 674, 35);
		panelHeader.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent evt) {
				xMouse = evt.getX();
				yMouse = evt.getY();
			}
		});
		panelHeader.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent evt) {
				int x = evt.getXOnScreen();
				int y = evt.getYOnScreen();
				Registro.this.setLocation(x - xMouse, y - yMouse);
			}
		});
		getContentPane().add(panelHeader);
		panelHeader.setLayout(new CardLayout(0, 0));
	}
}
