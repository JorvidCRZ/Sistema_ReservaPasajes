package Modulo_Menu;

import java.awt.Font;
import java.awt.Color;
import Logica.Pasajero;
import javax.swing.JButton;
import javax.swing.GroupLayout;
import javax.swing.JOptionPane;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.ActionListener;
import javax.swing.GroupLayout.Alignment;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class Pasajeros extends javax.swing.JPanel {

	private static final long serialVersionUID = 1L;
	private javax.swing.JPanel bg;
	private javax.swing.JScrollPane tablaPasajero;
	private javax.swing.JTable jTable1;
	private javax.swing.JButton btnBuscar;
	private javax.swing.JLabel txtPasajero;
	private javax.swing.JTextField txtNavegar;

	public Pasajeros() {
		initComponents();
		InitStyles();
	}

	private void InitStyles() {
		txtPasajero.putClientProperty("FlatLaf.styleClass", "h1");
		txtPasajero.setForeground(Color.black);
	}

	private void initComponents() {

		bg = new javax.swing.JPanel();
		txtPasajero = new javax.swing.JLabel();
		txtPasajero.setText(" Pasajeros");
		txtPasajero.setFont(new Font("Trebuchet MS", Font.BOLD, 25));
		txtNavegar = new javax.swing.JTextField();
		txtNavegar.setFont(new Font("Trebuchet MS", Font.PLAIN, 15));
		txtNavegar.setForeground(new Color(192, 192, 192));
		txtNavegar.setText(" Ingrese el Id de la reserva  ");
		txtNavegar.addFocusListener(new java.awt.event.FocusListener() {
			@Override
			public void focusGained(java.awt.event.FocusEvent evt) {
				if (txtNavegar.getText().trim().equals("Ingrese el Id de la reserva")) {
					txtNavegar.setText("");
					txtNavegar.setForeground(Color.BLACK);
				}
			}

			@Override
			public void focusLost(java.awt.event.FocusEvent evt) {
				if (txtNavegar.getText().isEmpty()) {
					txtNavegar.setForeground(new Color(192, 192, 192));
					txtNavegar.setText(" Ingrese el Id de la reserva  ");
				}
			}
		});

		tablaPasajero = new javax.swing.JScrollPane();
		jTable1 = new javax.swing.JTable();
		setBackground(new java.awt.Color(255, 255, 255));
		bg.setBackground(new Color(240, 240, 240));

		btnBuscar = new javax.swing.JButton();
		btnBuscar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnBuscar.setBackground(new Color(0, 165, 190));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnBuscar.setBackground(new Color(0, 4, 84));
			}
		});
		btnBuscar.setBackground(new Color(0, 4, 84));
		btnBuscar.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
		btnBuscar.setForeground(new java.awt.Color(255, 255, 255));
		btnBuscar.setText("Buscar");
		btnBuscar.setBorderPainted(false);
		btnBuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		btnBuscar.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				try {
					if (txtNavegar.getText().trim().isEmpty()) {
						JOptionPane.showMessageDialog(null, "El campo de búsqueda no está disponible.");
						return;
					}
					String criterio = txtNavegar.getText().trim();
					DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
					model.setRowCount(0);
					if (criterio.matches("\\d+")) {
						int id = Integer.parseInt(criterio);
						List<Pasajero> pasajeros = new Pasajero().obtenerPasajerosPorReservaId(id);
						if (!pasajeros.isEmpty()) {
							for (Pasajero pasajero : pasajeros) {
								model.addRow(new Object[] { pasajero.getReserva().getReservaId(), pasajero.getId(),
										pasajero.getNombresPasajero(), pasajero.getApellidoPasajero(),
										pasajero.getNroAsiento(), pasajero.getNacionalidadPasajero(),
										pasajero.getClase(), pasajero.getPasaporte() });
							}
						} else {
							JOptionPane.showMessageDialog(null,
									"No se encontraron pasajeros con el ID de reserva proporcionado.");
						}
					} else {
						JOptionPane.showMessageDialog(null, "El ID de reserva debe ser un número.");
					}
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "Error al buscar pasajeros: " + ex.getMessage());
				}
			}
		});

		jTable1.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "Reserva ID", "Pasajero ID", "Nombres",
				"Apellidos", "Asiento", "Nacionalidad", "Clase", "Pasaporte" }));
		jTable1.getTableHeader().setReorderingAllowed(false);
		jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mousePressed(java.awt.event.MouseEvent evt) {
				jTable1MousePressed(evt);
			}
		});
		tablaPasajero.setViewportView(jTable1);

		JButton btnMostar = new JButton();
		btnMostar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnMostar.setBackground(new Color(0, 165, 190));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnMostar.setBackground(new Color(0, 4, 84));
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					Pasajero pasajero = new Pasajero();
					DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
					model.setRowCount(0);
					pasajero.listar().forEach((pasajeros) -> {
						try {

							model.addRow(new Object[] { pasajeros.getReserva().getReservaId(), pasajeros.getId(),
									pasajeros.getNombresPasajero(), pasajeros.getApellidoPasajero(),
									pasajeros.getNroAsiento(), pasajeros.getNacionalidadPasajero(),
									pasajeros.getClase(), pasajeros.getPasaporte() });
						} catch (Exception ex) {
							System.out.println("Error al agregar los datos en la tabla" + ex.getMessage());
						}
					});
				} catch (Exception ex) {
					System.out.println("Error al mostrar pasajeros" + ex.getMessage());
				}
			}
		});
		btnMostar.setText("Mostrar");
		btnMostar.setForeground(Color.WHITE);
		btnMostar.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
		btnMostar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		btnMostar.setBorderPainted(false);
		btnMostar.setBackground(new Color(0, 4, 84));

		JButton btnLimpiar = new JButton();
		btnLimpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnLimpiar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnLimpiar.setBackground(new Color(0, 165, 190));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnLimpiar.setBackground(new Color(0, 4, 84));
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
				model.setRowCount(0);
			}
		});
		btnLimpiar.setText("Limpiar");
		btnLimpiar.setForeground(Color.WHITE);
		btnLimpiar.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
		btnLimpiar.setBorderPainted(false);
		btnLimpiar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		btnLimpiar.setBackground(new Color(0, 4, 84));

		javax.swing.GroupLayout bgLayout = new javax.swing.GroupLayout(bg);
		bgLayout.setHorizontalGroup(
			bgLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(bgLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(bgLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(bgLayout.createSequentialGroup()
							.addComponent(btnLimpiar, GroupLayout.PREFERRED_SIZE, 106, GroupLayout.PREFERRED_SIZE)
							.addGap(50)
							.addComponent(btnMostar, GroupLayout.PREFERRED_SIZE, 106, GroupLayout.PREFERRED_SIZE)
							.addGap(66))
						.addGroup(bgLayout.createSequentialGroup()
							.addComponent(txtPasajero, GroupLayout.PREFERRED_SIZE, 160, GroupLayout.PREFERRED_SIZE)
							.addContainerGap(633, Short.MAX_VALUE))
						.addGroup(bgLayout.createSequentialGroup()
							.addGroup(bgLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(tablaPasajero, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 777, Short.MAX_VALUE)
								.addGroup(bgLayout.createSequentialGroup()
									.addComponent(txtNavegar, GroupLayout.DEFAULT_SIZE, 670, Short.MAX_VALUE)
									.addGap(18)
									.addComponent(btnBuscar)))
							.addGap(16))))
		);
		bgLayout.setVerticalGroup(
			bgLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(bgLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(txtPasajero, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
					.addGap(17)
					.addGroup(bgLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtNavegar, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnBuscar, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
					.addGap(21)
					.addComponent(tablaPasajero, GroupLayout.PREFERRED_SIZE, 344, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(bgLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnMostar, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnLimpiar, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
					.addGap(30))
		);
		bg.setLayout(bgLayout);

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
		layout.setHorizontalGroup(
			layout.createParallelGroup(Alignment.LEADING)
				.addComponent(bg, GroupLayout.DEFAULT_SIZE, 803, Short.MAX_VALUE)
		);
		layout.setVerticalGroup(
			layout.createParallelGroup(Alignment.LEADING)
				.addComponent(bg, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
		);
		this.setLayout(layout);
	}
	private void jTable1MousePressed(java.awt.event.MouseEvent evt) {
	}
}