package Interfaces;

import Logica.Procesos;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Insets;
import javax.swing.JTextField;
import javax.swing.JSeparator;
import javax.swing.BorderFactory;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;
import java.awt.FlowLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseMotionAdapter;

public class Login extends JFrame {
	int xMouse, yMouse;
	private JPasswordField TxtClave;
	private JTextField txtNomUsuario;
	private boolean isPasswordVisible = false;
	private final JLabel lblLogo = new JLabel("");
	private static final long serialVersionUID = 1L;
	private final JTextField textField = new JTextField();

	public Login() {
		setBounds(100, 100, 864, 500);
		setTitle("Login");
		setResizable(false);
		setUndecorated(true);
		setType(Type.UTILITY);
		textField.setColumns(10);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		lblLogo.setBounds(644, 115, 157, 158);
		lblLogo.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().setBackground(new Color(240, 240, 240));
		lblLogo.setIcon(new ImageIcon(Login.class.getResource("/com/img/LOGO2.png")));
		getContentPane().add(lblLogo);

		JLabel lbFondo = new JLabel("");
		lbFondo.setBounds(568, 0, 297, 501);
		lbFondo.setHorizontalAlignment(SwingConstants.CENTER);
		lbFondo.setIcon(new ImageIcon(Login.class.getResource("/com/img/city.png")));
		getContentPane().add(lbFondo);

		JLabel lblTitulo = new JLabel("Flyhigh");
		lblTitulo.setBounds(207, 49, 163, 56);
		lblTitulo.setForeground(new Color(0, 165, 190));
		lblTitulo.setFont(new Font("Arial Rounded MT Bold", Font.BOLD | Font.ITALIC, 35));
		getContentPane().add(lblTitulo);

		JLabel lblIcono = new JLabel("");
		lblIcono.setBounds(59, 41, 58, 64);
		lblIcono.setHorizontalAlignment(SwingConstants.CENTER);
		lblIcono.setIcon(new ImageIcon(Login.class.getResource("/com/img/LOGO1.png")));
		getContentPane().add(lblIcono);

		JLabel lblInicio = new JLabel("INICIAR SESIÓN");
		lblInicio.setBounds(59, 110, 195, 56);
		lblInicio.setForeground(new Color(0, 0, 0));
		lblInicio.setFont(new Font("Arial Black", Font.BOLD, 20));
		getContentPane().add(lblInicio);

		JLabel lblSubtitulo1 = new JLabel("USUARIO");
		getContentPane().add(lblSubtitulo1);
		lblSubtitulo1.setBounds(59, 177, 68, 14);
		lblSubtitulo1.setBackground(new Color(0, 0, 0));
		lblSubtitulo1.setForeground(new Color(64, 64, 64));
		lblSubtitulo1.setFont(new Font("Trebuchet MS", Font.BOLD, 15));

		JSeparator separator1 = new JSeparator();
		separator1.setBounds(59, 253, 430, 5);
		separator1.setForeground(new Color(0, 0, 0));
		getContentPane().add(separator1);
		
		JPanel panelHeader = new JPanel();
		panelHeader.setLayout(null);
		panelHeader.setBounds(0, 0, 865, 35);
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
		getContentPane().add(panelHeader);

		JPanel panelExit = new JPanel();
		panelExit.setLayout(null);
		panelExit.setBounds(0, 0, 48, 35);
		panelHeader.add(panelExit);

		JLabel lblX = new JLabel("X");
		lblX.setOpaque(true);
		lblX.setBounds(0, 0, 48, 35);
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

		JPanel panelAtras = new JPanel();
		panelAtras.setLayout(null);
		panelAtras.setBounds(46, 0, 48, 35);
		panelAtras.setBackground(new Color(0, 165, 190));
		panelHeader.add(panelAtras);

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
				Login.this.dispose();
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

		txtNomUsuario = new JTextField();
		txtNomUsuario.setFocusable(false);
		txtNomUsuario.setBounds(59, 216, 430, 35);
		txtNomUsuario.setBackground(new Color(255, 255, 255));
		txtNomUsuario.setForeground(new Color(192, 192, 192));
		txtNomUsuario.setText("Ingrese su nombre de usuario");
		txtNomUsuario.setFont(new Font("Trebuchet MS", Font.PLAIN, 15));
		txtNomUsuario.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
		txtNomUsuario.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txtNomUsuario.setFocusable(true);
				txtNomUsuario.requestFocus();
			}
		});
		txtNomUsuario.addFocusListener(new java.awt.event.FocusListener() {
			public void focusGained(java.awt.event.FocusEvent evt) {
				if (txtNomUsuario.getText().equals("Ingrese su nombre de usuario")) {
					txtNomUsuario.setText("");
					txtNomUsuario.setForeground(Color.BLACK);
				}
			}

			public void focusLost(java.awt.event.FocusEvent evt) {
				if (txtNomUsuario.getText().isEmpty()) {
					txtNomUsuario.setForeground(new Color(192, 192, 192));
					txtNomUsuario.setText("Ingrese su nombre de usuario");
				}
			}
		});
		getContentPane().add(txtNomUsuario);

		JLabel lblSubtitulo2 = new JLabel("CONTRASEÑA");
		getContentPane().add(lblSubtitulo2);
		lblSubtitulo2.setBackground(Color.BLACK);
		lblSubtitulo2.setBounds(59, 283, 103, 14);
		lblSubtitulo2.setForeground(Color.DARK_GRAY);
		lblSubtitulo2.setFont(new Font("Trebuchet MS", Font.BOLD, 15));

		JSeparator separator2 = new JSeparator();
		separator2.setBounds(59, 356, 430, 5);
		separator2.setForeground(Color.BLACK);
		getContentPane().add(separator2);

		JPanel passwordPanel = new JPanel();
		passwordPanel.setBounds(59, 320, 430, 36);
		passwordPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 0));

		TxtClave = new JPasswordField();
		TxtClave.setColumns(10);
		TxtClave.setEchoChar('•');
		TxtClave.setFocusable(false);
		TxtClave.setBounds(59, 320, 430, 36);
		TxtClave.setMargin(new Insets(0, 10, 0, 0));
		TxtClave.setForeground(new Color(192, 192, 192));
		TxtClave.setBackground(new Color(255, 255, 255));
		TxtClave.setHorizontalAlignment(SwingConstants.LEFT);
		TxtClave.setFont(new Font("Trebuchet MS", Font.PLAIN, 15));
		TxtClave.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
		TxtClave.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				TxtClave.setFocusable(true);
				TxtClave.requestFocus();
			}

		});
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
		getContentPane().add(TxtClave);

		JPanel panelBoton = new JPanel();
		panelBoton.setLayout(null);
		panelBoton.setBounds(59, 400, 135, 46);
		getContentPane().add(panelBoton);

		JLabel lblEntrar = new JLabel("ENTRAR");
		lblEntrar.setOpaque(true);
		lblEntrar.setBounds(0, 0, 135, 46);
		lblEntrar.setForeground(new Color(0, 0, 0));
		lblEntrar.setBackground(new Color(0, 165, 190));
		lblEntrar.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblEntrar.setHorizontalAlignment(SwingConstants.CENTER);
		lblEntrar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblEntrar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblEntrar.setForeground(Color.white);
				lblEntrar.setBackground(new Color(0, 4, 84));
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
						System.out.print("Ingreso al menu con exito");
						JOptionPane.showMessageDialog(null, "El Usuario es Correcto");
						dispose();
					} else {
						JOptionPane.showMessageDialog(null,
								"El Usuario o la Contraseña son INCORRECTOS, VUELVA A INTENTAR");
					}
				}
			}
		});
		panelBoton.add(lblEntrar);

		JLabel lblVerClave = new JLabel("Mostrar");
		lblVerClave.setBounds(417, 372, 58, 27);
		lblVerClave.setForeground(new Color(0, 165, 190));
		lblVerClave.setFont(new Font("Arial", Font.PLAIN, 14));
		lblVerClave.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblVerClave.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblVerClave.setForeground(new Color(0, 4, 84));
			}

			@Override
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
		getContentPane().add(lblVerClave);
	}
}