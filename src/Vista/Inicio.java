package Vista;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseMotionAdapter;

public class Inicio extends JFrame {

	int xMouse, yMouse;
	private JPanel contentPane;
	private static final long serialVersionUID = 1L;

	public Inicio() {
		setBounds(100, 100, 452, 417);
		setUndecorated(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		contentPane = new JPanel();
		contentPane.setLayout(null);
		setContentPane(contentPane);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		JPanel panelExit = new JPanel();
		panelExit.setBounds(0, 0, 48, 35);
		contentPane.add(panelExit);
		panelExit.setLayout(null);

		JLabel lblX = new JLabel("X");
		lblX.setBounds(0, 0, 48, 35);
		panelExit.add(lblX);
		lblX.setOpaque(true);
		lblX.setBackground(new Color(0, 4, 84));
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
				lblX.setBackground(new Color(0, 165, 190));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				lblX.setBackground(new Color(0, 4, 84));
			}
		});

		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setBounds(394, 346, 48, 60);
		lblNewLabel_1.setIcon(new ImageIcon(Inicio.class.getResource("/com/img/avion2.png")));
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(375, 11, 67, 87);
		lblNewLabel.setIcon(new ImageIcon(Inicio.class.getResource("/com/img/LOGO3.png")));
		contentPane.add(lblNewLabel);

		JPanel button_inicia = new JPanel();		
		button_inicia.setBorder(null);
		button_inicia.setLayout(null);
		button_inicia.setBounds(152, 227, 150, 40);
		button_inicia.setBackground(new Color(0,4,84));
		button_inicia.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		button_inicia.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Login loginFrame = new Login();
				loginFrame.setVisible(true);
				Inicio.this.dispose();
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				button_inicia.setBackground(new Color(0,165,190));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				button_inicia.setBackground(new Color(0,4,84));
			}
		});
		contentPane.add(button_inicia);

		JLabel lblNewLabel_3 = new JLabel(" Iniciar sesión");
		lblNewLabel_3.setBounds(0, 0, 150, 40);
		lblNewLabel_3.setForeground(new Color(255, 255, 255));
		lblNewLabel_3.setFont(new Font("Dialog", Font.BOLD, 15));
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		button_inicia.add(lblNewLabel_3);

		JLabel lblFrase = new JLabel("\"Más que un vuelo, una experiencia única.\"");
		lblFrase.setBounds(10, 381, 221, 25);
		lblFrase.setForeground(new Color(27, 31, 94));
		lblFrase.setBackground(new Color(27, 31, 94));
		lblFrase.setFont(new Font("Roboto", Font.ITALIC, 10));
		contentPane.add(lblFrase);
		
		JPanel button_registro = new JPanel();
		button_registro.setBorder(null);
		button_registro.setLayout(null);
		button_registro.setBounds(152, 305, 150, 40);
		button_registro.setBackground(new Color(0, 4, 84));
		button_registro.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		button_registro.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Registro registroFrame = new Registro();
				registroFrame.setVisible(true);
				Inicio.this.dispose();
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				button_registro.setBackground(new Color(0,165,190));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				button_registro.setBackground(new Color(0,4,84));
			}
		});
		contentPane.add(button_registro);

		JLabel lblNewLabel_3_1 = new JLabel("Regístrate");
		lblNewLabel_3_1.setBounds(0, 0, 150, 40);
		lblNewLabel_3_1.setForeground(Color.WHITE);
		lblNewLabel_3_1.setFont(new Font("Dialog", Font.BOLD, 15));
		lblNewLabel_3_1.setHorizontalAlignment(SwingConstants.CENTER);
		button_registro.add(lblNewLabel_3_1);

		JLabel user_logo = new JLabel("");
		user_logo.setBounds(170, 75, 102, 124);
		user_logo.setBackground(new Color(255, 255, 255));
		user_logo.setHorizontalAlignment(SwingConstants.CENTER);
		user_logo.setIcon(new ImageIcon(Inicio.class.getResource("/com/img/Icon.png")));
		contentPane.add(user_logo);

		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setBounds(192, 191, 67, 26);
		lblNewLabel_2.setIcon(new ImageIcon(Inicio.class.getResource("/com/img/TITLE.png")));
		contentPane.add(lblNewLabel_2);

		JLabel Fondo = new JLabel("");
		Fondo.setBounds(0, 0, 452, 417);
		Fondo.setIcon(new ImageIcon(Inicio.class.getResource("/com/img/nubes.png")));
		contentPane.add(Fondo);

		JPanel panelHeader = new JPanel();
		panelHeader.setLayout(null);
		panelHeader.setBounds(46, 0, 406, 35);
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
				Inicio.this.setLocation(x - xMouse, y - yMouse);
			}
		});
		getContentPane().add(panelHeader);

	}
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Inicio frame = new Inicio();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
