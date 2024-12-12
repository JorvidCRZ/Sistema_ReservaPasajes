package Vista;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.util.Locale;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import java.time.LocalDate;
import java.awt.BorderLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

import Modulo_Menu.*;

import java.time.format.DateTimeFormatter;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.LayoutStyle.ComponentPlacement;

@SuppressWarnings("serial")
public class Menu extends javax.swing.JFrame {

	private int xMouse, yMouse;
	private javax.swing.JPanel menu;
	private javax.swing.JPanel header;
	private javax.swing.JLabel appName;
	private javax.swing.JLabel navText;
	private javax.swing.JLabel dateText;
	private javax.swing.JButton btn_Pagos;
	private javax.swing.JPanel background;
	private javax.swing.JButton btn_Vuelos;
	private javax.swing.JButton btn_Reserva;
	private javax.swing.JButton btn_Principal;
	private javax.swing.JButton btn_Pasajeros;
	private static javax.swing.JPanel content;
	private javax.swing.JSeparator jSeparator1;
	private JLabel lblNewLabel_1;

	public Menu() {
		setUndecorated(true); 
		initComponents();
		InitStyles();
		SetDate();
		InitContent();
	
	}

	private void InitStyles() {
		navText.putClientProperty("FlatLaf.style", "font: bold 30 $h3.regular.font");
		navText.setForeground(Color.white);
		dateText.putClientProperty("FlatLaf.style", "font: 17 $light.font");
		dateText.setForeground(Color.white);
		appName.putClientProperty("FlatLaf.style", "font: bold italic 30 $h1.regular.font");
		appName.setForeground(Color.white);
	}

	private void SetDate() {
		LocalDate now = LocalDate.now();
		@SuppressWarnings("deprecation")
		Locale spanishLocale = new Locale("es", "ES");
		dateText.setText(
				now.format(DateTimeFormatter.ofPattern("'Hoy es' EEEE dd 'de' MMMM 'de' yyyy", spanishLocale)));
	}

	private void InitContent() {
		Principal principal = new Principal();
		principal.setBackground(new Color(240, 240, 240));
		ShowJPanel(principal);
	}

	public static void ShowJPanel(JPanel p) {
		p.setSize(750, 430);
		p.setLocation(0, 0);

		content.removeAll();
		content.add(p, BorderLayout.CENTER);
		content.revalidate();
		content.repaint();
	}

	private void initComponents() {

		menu = new javax.swing.JPanel();
		header = new javax.swing.JPanel();
		navText = new javax.swing.JLabel();
		content = new javax.swing.JPanel();
		appName = new javax.swing.JLabel();
		appName.setText("FLYHIGH");
		dateText = new javax.swing.JLabel();
		background = new javax.swing.JPanel();
		btn_Pagos = new javax.swing.JButton();
		btn_Vuelos = new javax.swing.JButton();
		btn_Reserva = new javax.swing.JButton();
		btn_Principal = new javax.swing.JButton();
		btn_Pasajeros = new javax.swing.JButton();
		jSeparator1 = new javax.swing.JSeparator();

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		setMinimumSize(new java.awt.Dimension(1050, 660));

		background.setBackground(new Color(240, 240, 240));

		menu.setBackground(new Color(0, 134, 190));
		menu.setPreferredSize(new java.awt.Dimension(270, 640));

		appName.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

		jSeparator1.setPreferredSize(new java.awt.Dimension(50, 5));

		btn_Principal.setBackground(new Color(0, 134, 190));
		btn_Principal.setFont(new Font("Segoe UI", Font.BOLD, 18));
		btn_Principal.setForeground(new java.awt.Color(255, 255, 255));
		btn_Principal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/img/home-outline.png")));
		btn_Principal.setText("Principal");
		btn_Principal.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 13, 1, 1, new java.awt.Color(0, 0, 0)));
		btn_Principal.setBorderPainted(false);
		btn_Principal.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		btn_Principal.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
		btn_Principal.setIconTextGap(13);
		btn_Principal.setInheritsPopupMenu(true);
		btn_Principal.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btn_prinActionPerformed(evt);
			}
		});

		btn_Reserva.setBackground(new Color(0, 134, 190));
		btn_Reserva.setFont(new Font("Segoe UI", Font.BOLD, 18)); // NOI18N
		btn_Reserva.setForeground(new java.awt.Color(255, 255, 255));
		btn_Reserva.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/img/calendar-plus.png")));
		btn_Reserva.setText("Reserva");
		btn_Reserva.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 13, 1, 1, new java.awt.Color(0, 0, 0)));
		btn_Reserva.setBorderPainted(false);
		btn_Reserva.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		btn_Reserva.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
		btn_Reserva.setIconTextGap(13);
		btn_Reserva.setInheritsPopupMenu(true);
		btn_Reserva.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btn_lendsActionPerformed(evt);
			}
		});

		btn_Pagos.setBackground(new Color(0, 134, 190));
		btn_Pagos.setFont(new Font("Segoe UI", Font.BOLD, 18));
		btn_Pagos.setForeground(new java.awt.Color(255, 255, 255));
		btn_Pagos.setIcon(
				new javax.swing.ImageIcon(getClass().getResource("/com/img/calendar-multiple-check.png")));
		btn_Pagos.setText("Pagos");
		btn_Pagos.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 13, 1, 1, new java.awt.Color(0, 0, 0)));
		btn_Pagos.setBorderPainted(false);
		btn_Pagos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		btn_Pagos.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
		btn_Pagos.setIconTextGap(13);
		btn_Pagos.setInheritsPopupMenu(true);
		btn_Pagos.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btn_returnsActionPerformed(evt);
			}
		});

		btn_Pasajeros.setBackground(new Color(0, 134, 190));
		btn_Pasajeros.setFont(new Font("Segoe UI", Font.BOLD, 18));
		btn_Pasajeros.setForeground(new java.awt.Color(255, 255, 255));
		btn_Pasajeros.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/img/account-multiple.png")));
		btn_Pasajeros.setText("Pasajeros");
		btn_Pasajeros.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 13, 1, 1, new java.awt.Color(0, 0, 0)));
		btn_Pasajeros.setBorderPainted(false);
		btn_Pasajeros.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		btn_Pasajeros.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
		btn_Pasajeros.setIconTextGap(13);
		btn_Pasajeros.setInheritsPopupMenu(true);
		btn_Pasajeros.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btn_usersActionPerformed(evt);
			}
		});

		btn_Vuelos.setBackground(new Color(0, 134, 190));
		btn_Vuelos.setFont(new Font("Segoe UI", Font.BOLD, 18));
		btn_Vuelos.setForeground(new java.awt.Color(255, 255, 255));
		btn_Vuelos.setIcon(
				new javax.swing.ImageIcon(getClass().getResource("/com/img/book-open-page-variant.png")));
		btn_Vuelos.setText("Vuelos");
		btn_Vuelos.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 13, 1, 1, new java.awt.Color(0, 0, 0)));
		btn_Vuelos.setBorderPainted(false);
		btn_Vuelos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		btn_Vuelos.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
		btn_Vuelos.setIconTextGap(13);
		btn_Vuelos.setInheritsPopupMenu(true);
		btn_Vuelos.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btn_booksActionPerformed(evt);
			}
		});
		
		JPanel panelHeader = new JPanel();
		panelHeader.setLayout(null);
		panelHeader.setBounds(0, 0, 1100, 80);
		panelHeader.setBackground(new Color(0, 0, 0, 0));
		panelHeader.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mousePressed(java.awt.event.MouseEvent evt) {
				xMouse = evt.getX();
				yMouse = evt.getY();
			}
		});
		panelHeader.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
			@Override
			public void mouseDragged(java.awt.event.MouseEvent evt) {
				int x = evt.getXOnScreen();
				int y = evt.getYOnScreen();
				Menu.this.setLocation(x - xMouse, y - yMouse);
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
		lblX.setBackground(new Color(0, 134, 190));
		lblX.setForeground(new Color(240, 240, 240));
		lblX.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblX.setHorizontalAlignment(SwingConstants.CENTER);
		lblX.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblX.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mouseClicked(java.awt.event.MouseEvent e) {
				System.exit(0);
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				lblX.setBackground(new Color(0, 4, 84));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				lblX.setBackground(new Color(0, 134, 190));
			}
		});
		panelExit.add(lblX);

		JPanel panelAtras = new JPanel();
		panelAtras.setLayout(null);
		panelAtras.setBounds(46, 0, 48, 35);
		panelAtras.setBackground(new Color(0, 134, 190)); // Color predeterminado
		panelHeader.add(panelAtras);

		JLabel lblAtras = new JLabel("<");
		lblAtras.setBounds(0, 0, 48, 35);
		lblAtras.setBackground(new Color(255, 255, 255)); // Fondo no visible debido a JPanel
		lblAtras.setForeground(new Color(240, 240, 240)); // Color de texto predeterminado
		lblAtras.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblAtras.setHorizontalAlignment(SwingConstants.CENTER);
		lblAtras.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		// Manejo de eventos para cambios de colores
		lblAtras.addMouseListener(new java.awt.event.MouseAdapter() {
		    @Override
		    public void mouseClicked(java.awt.event.MouseEvent e) {
		        Login LoginFrame = new Login();
		        LoginFrame.setVisible(true);
		        Menu.this.dispose();
		    }

		    @Override
		    public void mouseEntered(java.awt.event.MouseEvent e) {
		        panelAtras.setBackground(new Color(0, 4, 84)); // Cambia el fondo al pasar el cursor
		        lblAtras.setForeground(new Color(240, 240, 240)); // Mantiene el color del texto
		    }

		    @Override
		    public void mouseExited(java.awt.event.MouseEvent e) {
		        panelAtras.setBackground(new Color(0, 134, 190)); // Regresa al color inicial
		        lblAtras.setForeground(new Color(240, 240, 240)); // Regresa al color inicial del texto
		    }

		    @Override
		    public void mousePressed(java.awt.event.MouseEvent e) {
		        panelAtras.setBackground(new Color(0, 165, 190)); // Color al hacer clic
		    }

		    @Override
		    public void mouseReleased(java.awt.event.MouseEvent e) {
		        panelAtras.setBackground(new Color(0, 165, 190)); // Mantiene el color del hover después de clic
		    }
		});

		panelAtras.add(lblAtras);
		
		lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setBounds(944, 11, 60, 56);
		panelHeader.add(lblNewLabel_1);
		lblNewLabel_1.setIcon(new ImageIcon(Menu.class.getResource("/com/img/LOGO1.png")));
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(Menu.class.getResource("/com/img/LOGO2.png")));

		javax.swing.GroupLayout menuLayout = new javax.swing.GroupLayout(menu);
		menuLayout.setHorizontalGroup(
			menuLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(menuLayout.createSequentialGroup()
					.addGap(10)
					.addComponent(appName, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE))
				.addGroup(menuLayout.createSequentialGroup()
					.addGap(40)
					.addComponent(jSeparator1, GroupLayout.PREFERRED_SIZE, 190, GroupLayout.PREFERRED_SIZE))
				.addGroup(menuLayout.createSequentialGroup()
					.addGap(56)
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE))
				.addGroup(menuLayout.createSequentialGroup()
					.addComponent(btn_Vuelos, GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE)
					.addContainerGap())
				.addGroup(menuLayout.createSequentialGroup()
					.addComponent(btn_Pasajeros, GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE)
					.addContainerGap())
				.addGroup(menuLayout.createSequentialGroup()
					.addComponent(btn_Pagos, GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE)
					.addContainerGap())
				.addGroup(menuLayout.createSequentialGroup()
					.addComponent(btn_Reserva, GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE)
					.addContainerGap())
				.addGroup(menuLayout.createSequentialGroup()
					.addComponent(btn_Principal, GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE)
					.addContainerGap())
		);
		menuLayout.setVerticalGroup(
			menuLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(menuLayout.createSequentialGroup()
					.addGap(52)
					.addComponent(appName, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
					.addGap(4)
					.addComponent(jSeparator1, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
					.addGap(60)
					.addComponent(btn_Principal, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btn_Reserva, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btn_Pagos, GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btn_Pasajeros, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btn_Vuelos, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
					.addGap(78)
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
					.addGap(101))
		);
		menu.setLayout(menuLayout);

		header.setBackground(new Color(0, 134, 190));
		header.setPreferredSize(new java.awt.Dimension(744, 150));

		navText.setText("Sistema de Administración de Pasajes Aereos ");

		dateText.setText("Hoy es {dayname} {day} de {month} de {year}");

		javax.swing.GroupLayout headerLayout = new javax.swing.GroupLayout(header);
		headerLayout.setHorizontalGroup(
			headerLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(headerLayout.createSequentialGroup()
					.addGap(66)
					.addGroup(headerLayout.createParallelGroup(Alignment.LEADING, false)
						.addComponent(navText, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(dateText, GroupLayout.DEFAULT_SIZE, 434, Short.MAX_VALUE))
					.addContainerGap(280, Short.MAX_VALUE))
		);
		headerLayout.setVerticalGroup(
			headerLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(headerLayout.createSequentialGroup()
					.addGap(47)
					.addComponent(navText, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(dateText, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(31, Short.MAX_VALUE))
		);
		header.setLayout(headerLayout);

		content.setBackground(new Color(240, 240, 240));
		content.setLayout(new java.awt.BorderLayout());

		javax.swing.GroupLayout backgroundLayout = new javax.swing.GroupLayout(background);
		backgroundLayout.setHorizontalGroup(
			backgroundLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(backgroundLayout.createSequentialGroup()
					.addComponent(menu, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGroup(backgroundLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(header, GroupLayout.DEFAULT_SIZE, 780, Short.MAX_VALUE)
						.addComponent(content, GroupLayout.DEFAULT_SIZE, 780, Short.MAX_VALUE)))
		);
		backgroundLayout.setVerticalGroup(
			backgroundLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(menu, GroupLayout.DEFAULT_SIZE, 843, Short.MAX_VALUE)
				.addGroup(backgroundLayout.createSequentialGroup()
					.addGap(85)
					.addComponent(header, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(0)
					.addComponent(content, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(1))
		);
		background.setLayout(backgroundLayout);

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(background,
						javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
		layout.setVerticalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(background,
						javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));

		pack();
		setLocationRelativeTo(null);
	}

	private void btn_prinActionPerformed(java.awt.event.ActionEvent evt) {
		ShowJPanel(new Principal());
	}

	private void btn_lendsActionPerformed(java.awt.event.ActionEvent evt) {
		ShowJPanel(new Reservas());
	}

	private void btn_returnsActionPerformed(java.awt.event.ActionEvent evt) {
		ShowJPanel(new Pagos());
	}

	private void btn_usersActionPerformed(java.awt.event.ActionEvent evt) {
		ShowJPanel(new Pasajeros());
	}

	private void btn_booksActionPerformed(java.awt.event.ActionEvent evt) {
		ShowJPanel(new Vuelos());
	}
}