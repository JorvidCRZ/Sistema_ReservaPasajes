package Interfaces;

import Logica.Procesos;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.Insets;
import java.awt.Color;
import java.awt.Cursor;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import javax.swing.JPasswordField;
import javax.swing.BorderFactory;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.FlowLayout;

public class Login extends JFrame {
	private static final long serialVersionUID = 1L;
	private final JLabel lblLogo = new JLabel("");
	private JTextField txtNomUsuario;
	private JPasswordField TxtClave;
	private boolean isPasswordVisible = false;
	int xMouse, yMouse;
	private final JTextField textField = new JTextField();

	public Login() {
		textField.setColumns(10);
		setTitle("Login");
		setType(Type.UTILITY);
		getContentPane().setBackground(new Color(240, 240, 240));
		setBounds(100, 100, 864, 500);
		setUndecorated(true);
		setResizable(false);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		lblLogo.setBounds(644, 115, 157, 158);
		lblLogo.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogo.setIcon(new ImageIcon(Login.class.getResource("/com/img/LOGO2.png")));
		getContentPane().add(lblLogo);

		JLabel lbFondo = new JLabel("");
		lbFondo.setBounds(568, 0, 297, 501);
		lbFondo.setHorizontalAlignment(SwingConstants.CENTER);
		lbFondo.setIcon(new ImageIcon(Login.class.getResource("/com/img/city.png")));
		getContentPane().add(lbFondo);

		JLabel lblTitulo = new JLabel("Flyhigh");
		lblTitulo.setForeground(new Color(0, 165, 190));
		lblTitulo.setBounds(207, 49, 163, 56);
		lblTitulo.setFont(new Font("Arial Rounded MT Bold", Font.BOLD | Font.ITALIC, 35));
		getContentPane().add(lblTitulo);

		JLabel lblIcono = new JLabel("");
		lblIcono.setBounds(59, 41, 58, 64);
		lblIcono.setHorizontalAlignment(SwingConstants.CENTER);
		lblIcono.setIcon(new ImageIcon(Login.class.getResource("/com/img/LOGO1.png")));
		getContentPane().add(lblIcono);

		JLabel lblInicio = new JLabel("INICIAR SESIÓN");
		lblInicio.setForeground(new Color(0, 0, 0));
		lblInicio.setBounds(59, 110, 195, 56);
		lblInicio.setFont(new Font("Arial Black", Font.BOLD, 20));
		getContentPane().add(lblInicio);

		JLabel lblSubtitulo1 = new JLabel("USUARIO");
		lblSubtitulo1.setBounds(59, 177, 68, 14);
		lblSubtitulo1.setForeground(new Color(64, 64, 64));
		lblSubtitulo1.setFont(new Font("Trebuchet MS", Font.BOLD, 15));
		lblSubtitulo1.setBackground(new Color(0, 0, 0));
		getContentPane().add(lblSubtitulo1);

		JSeparator separator1 = new JSeparator();
		separator1.setBounds(59, 253, 430, 5);
		separator1.setForeground(new Color(0, 0, 0));
		getContentPane().add(separator1);

		txtNomUsuario = new JTextField();
		txtNomUsuario.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
		txtNomUsuario.setBounds(59, 216, 430, 35);
		txtNomUsuario.setBackground(new Color(255, 255, 255));
		txtNomUsuario.setText("Ingrese su nombre de usuario");
		txtNomUsuario.setFont(new Font("Trebuchet MS", Font.PLAIN, 15));
		txtNomUsuario.setForeground(new Color(192, 192, 192));
		txtNomUsuario.setFocusable(false);
		getContentPane().add(txtNomUsuario);
		txtNomUsuario.addFocusListener(new java.awt.event.FocusListener() {
			public void focusGained(java.awt.event.FocusEvent evt) {

				if (txtNomUsuario.getText().equals("Ingrese su nombre de usuario")) {
					txtNomUsuario.setText("");
					txtNomUsuario.setForeground(Color.BLACK);
				}
			}

			public void focusLost(java.awt.event.FocusEvent evt) {

				if (txtNomUsuario.getText().isEmpty()) {
					txtNomUsuario.setText("Ingrese su nombre de usuario");
					txtNomUsuario.setForeground(new Color(192, 192, 192));
				}
			}
		});

		txtNomUsuario.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txtNomUsuario.setFocusable(true);
				txtNomUsuario.requestFocus();
			}
		});

		JLabel lblSubtitulo2 = new JLabel("CONTRASEÑA");
		lblSubtitulo2.setBounds(59, 283, 95, 14);
		lblSubtitulo2.setForeground(Color.DARK_GRAY);
		lblSubtitulo2.setFont(new Font("Trebuchet MS", Font.BOLD, 15));
		lblSubtitulo2.setBackground(Color.BLACK);
		getContentPane().add(lblSubtitulo2);

		JSeparator separator2 = new JSeparator();
		separator2.setBounds(59, 356, 430, 5);
		separator2.setForeground(Color.BLACK);
		getContentPane().add(separator2);

		JPanel panelBoton = new JPanel();
		panelBoton.setBounds(59, 400, 135, 46);
		getContentPane().add(panelBoton);
		panelBoton.setLayout(null);

		JLabel lblEntrar = new JLabel("ENTRAR");
		lblEntrar.setBounds(0, 0, 135, 46);
		panelBoton.add(lblEntrar);
		lblEntrar.setBackground(new Color(0, 165, 190));
		lblEntrar.setOpaque(true);
		lblEntrar.setForeground(new Color(0, 0, 0));
		lblEntrar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblEntrar.setHorizontalAlignment(SwingConstants.CENTER);
		lblEntrar.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblEntrar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblEntrar.setBackground(new Color(0, 4, 84));
				lblEntrar.setForeground(Color.white);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				lblEntrar.setBackground(new Color(0, 165, 190));
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				String usuario = txtNomUsuario.getText();
				String contraseña = String.valueOf(TxtClave.getPassword());
				if (usuario.isEmpty() || usuario.equals("Ingrese su nombre de usuario") || contraseña.isEmpty()) {
					JOptionPane.showMessageDialog(lblEntrar, "Por favor, complete todos los campos.", "Error",
							JOptionPane.ERROR_MESSAGE);
				} else {
					Procesos procesos = new Procesos();
					boolean usuarioValido = procesos.validarUsuario(usuario, contraseña);
					if (usuarioValido) {
						JOptionPane.showMessageDialog(null, "El Usuario es Correcto");
						System.out.print("Ingreso al menu con exito");
						dispose();
					} else {
						JOptionPane.showMessageDialog(null,
								"El Usuario o la Contraseña son INCORRECTOS, VUELVA A INTENTAR");
					}
				}
			}
		});

		JPanel panelExit = new JPanel();
		panelExit.setLayout(null);
		panelExit.setBounds(0, 0, 48, 35);

		getContentPane().add(panelExit);

		JLabel lblX = new JLabel("X");
		lblX.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				lblX.setBackground(new Color(0, 165, 190));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				lblX.setBackground(null);
			}
		});
		lblX.setBounds(0, 0, 46, 35);
		panelExit.add(lblX);
		lblX.setOpaque(true);
		lblX.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblX.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblX.setHorizontalAlignment(SwingConstants.CENTER);

		JPanel panelHeader = new JPanel();
		panelHeader.setBounds(0, 0, 865, 35);
		getContentPane().add(panelHeader);
		panelHeader.setLayout(null);

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
				Login.this.setLocation(x - xMouse, y - yMouse);
			}
		});

		JPanel passwordPanel = new JPanel();
		passwordPanel.setBounds(59, 320, 430, 36);
		passwordPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 0));

		TxtClave = new JPasswordField();
		TxtClave.setFocusable(false);
		TxtClave.setFont(new Font("Trebuchet MS", Font.PLAIN, 15));
		TxtClave.setBounds(59, 320, 430, 36);
		TxtClave.setForeground(new Color(192, 192, 192));
		TxtClave.setBackground(new Color(255, 255, 255));
		TxtClave.setEchoChar('•');
		TxtClave.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
		TxtClave.setMargin(new Insets(0, 10, 0, 0));
		TxtClave.setHorizontalAlignment(SwingConstants.LEFT);
		TxtClave.setColumns(10);
		getContentPane().add(TxtClave);
		TxtClave.addFocusListener(new java.awt.event.FocusListener() {
			public void focusGained(java.awt.event.FocusEvent evt) {
				if (String.valueOf(TxtClave.getPassword()).isEmpty()) {
					TxtClave.setText("");
					TxtClave.setForeground(Color.BLACK);
				}
			}

			public void focusLost(java.awt.event.FocusEvent evt) {
				if (String.valueOf(TxtClave.getPassword()).isEmpty()) {
					TxtClave.setText("");
					TxtClave.setForeground(new Color(192, 192, 192));
				}
			}
		});
		TxtClave.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				TxtClave.setFocusable(true);
				TxtClave.requestFocus();
			}

		});

		JLabel lblVerClave = new JLabel("Mostrar");
		lblVerClave.setBounds(417, 372, 48, 27);
		lblVerClave.setFont(new Font("Arial", Font.PLAIN, 14));
		lblVerClave.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblVerClave.setForeground(new Color(0, 165, 190));
		getContentPane().add(lblVerClave);
		lblVerClave.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblVerClave.setForeground(new Color(0, 4, 84));
			}

			public void mouseExited(MouseEvent e) {
				lblVerClave.setForeground(new Color(0, 165, 190));
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				if (isPasswordVisible) {
					TxtClave.setEchoChar('•');
					lblVerClave.setText("Mostrar");
					lblVerClave.setBackground(new Color(0, 4, 84));
				} else {
					TxtClave.setEchoChar((char) 0);
					lblVerClave.setText("Ocultar");
				}
				isPasswordVisible = !isPasswordVisible;
			}
		});
	}
}