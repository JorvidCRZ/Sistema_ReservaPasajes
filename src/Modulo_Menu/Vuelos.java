package Modulo_Menu;

import java.awt.Color;
import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import Logica.Avion;
import Logica.Piloto;
import javax.swing.JOptionPane;

public class Vuelos extends javax.swing.JPanel {

	private static final long serialVersionUID = 1L;
	private javax.swing.JPanel bg;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JScrollPane jScrollPane2;
	private javax.swing.JTable jTable1;
	private javax.swing.JTable jTable2;
	private javax.swing.JLabel title;
	private JTextField txtIngreseElId;
	private JTextField txtIngresarIdDel;
	private JButton planeSearchButton_1;
	private JButton planeSearchButton;

	public Vuelos() {
		initComponents();
		InitStyles();
	}

	private void InitStyles() {
		title.putClientProperty("FlatLaf.styleClass", "h1");
		title.setForeground(Color.black);
	}

	private void initComponents() {
		bg = new javax.swing.JPanel();
		title = new javax.swing.JLabel();
		title.setFont(new Font("Trebuchet MS", Font.BOLD, 20));
		jScrollPane1 = new javax.swing.JScrollPane();
		jTable1 = new javax.swing.JTable();
		jScrollPane2 = new javax.swing.JScrollPane();
		jTable2 = new javax.swing.JTable();
		setBackground(new java.awt.Color(255, 255, 255));
		bg.setBackground(new Color(240, 240, 240));
		title.setText("Vuelos");

		jTable1.setFont(new java.awt.Font("Segoe UI", 0, 11));
		jTable1.setModel(new javax.swing.table.DefaultTableModel(new Object[][] {}, new String[] { "Nombre", "Apellido",
				"Nacimiento ", "Nacionalidad", "Licencia", "N° licencia", "Contratación", "Estado", "DNI" }) {
			private static final long serialVersionUID = 1L;
			Class<?>[] types = { String.class, String.class, String.class, String.class, String.class, String.class,
					String.class, String.class, String.class };
			boolean[] canEdit = { false, true, true, true, true, true, true, true, true };

			@Override
			public Class<?> getColumnClass(int columnIndex) {
				return types[columnIndex];
			}

			@Override
			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return canEdit[columnIndex];
			}
		});
		jTable1.getTableHeader().setReorderingAllowed(false);
		jScrollPane1.setViewportView(jTable1);

		jTable2.setFont(new java.awt.Font("Segoe UI", 0, 11));
		jTable2.setModel(new javax.swing.table.DefaultTableModel(new Object[][] {},
				new String[] { "Avión ID", "Capacidad", "Estado" }) {
			private static final long serialVersionUID = 1L;

			Class<?>[] types = { String.class, String.class, String.class };
			boolean[] canEdit = { false, true, true };

			@Override
			public Class<?> getColumnClass(int columnIndex) {
				return types[columnIndex];
			}

			@Override
			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return canEdit[columnIndex];
			}
		});
		jTable2.getTableHeader().setReorderingAllowed(false);
		jScrollPane2.setViewportView(jTable2);

		txtIngreseElId = new JTextField();
		txtIngreseElId.setForeground(new Color(192, 192, 192));
		txtIngreseElId.setFont(new Font("Trebuchet MS", Font.PLAIN, 15));
		txtIngreseElId.setText("  Ingrese ID Avion");
		txtIngreseElId.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txtIngreseElId.setFocusable(true);
				txtIngreseElId.requestFocus();
			}
		});
		txtIngreseElId.addFocusListener(new java.awt.event.FocusListener() {
			public void focusGained(java.awt.event.FocusEvent evt) {
				if (txtIngreseElId.getText().equals("  Ingrese ID Avion")) {
					txtIngreseElId.setText("");
					txtIngreseElId.setForeground(Color.BLACK);
				}
			}

			public void focusLost(java.awt.event.FocusEvent evt) {
				if (txtIngreseElId.getText().isEmpty()) {
					txtIngreseElId.setForeground(new Color(192, 192, 192));
					txtIngreseElId.setText("  Ingrese ID Avion");
				}
			}
		});
		JButton btnLimpiar1 = new JButton();

		btnLimpiar1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel model = (DefaultTableModel) jTable2.getModel();
				model.setRowCount(0);
			}
		});

		btnLimpiar1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnLimpiar1.setBackground(new Color(0, 165, 190));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnLimpiar1.setBackground(new Color(0, 4, 84));
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
				model.setRowCount(0);
			}
		});

		btnLimpiar1.setText("Limpiar");
		btnLimpiar1.setForeground(Color.WHITE);
		btnLimpiar1.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
		btnLimpiar1.setBorderPainted(false);
		btnLimpiar1.setBackground(new Color(0, 4, 84));

		JButton btnMostrar2 = new JButton();
		btnMostrar2.setText("Mostrar");
		btnMostrar2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnMostrar2.setBackground(new Color(0, 165, 190));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnMostrar2.setBackground(new Color(0, 4, 84));
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					DefaultTableModel model = (DefaultTableModel) jTable2.getModel();
					model.setRowCount(0);
					Avion.mostrarAviones().forEach((aviones) -> {
						try {
							model.addRow(new Object[] { aviones.getAvionId(), aviones.getCapacidadTotal(),
									aviones.getEstadoAvion() });
						} catch (Exception e1) {
							System.out.println("Error al agregar los datos a la tabla: " + e1.getMessage());
						}
					});
				} catch (Exception e1) {
					System.out.println("Error al mostrar el ID del avión: " + e1.getMessage());
				}
			}

		});

		btnMostrar2.setForeground(Color.WHITE);
		btnMostrar2.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
		btnMostrar2.setBorderPainted(false);
		btnMostrar2.setBackground(new Color(0, 4, 84));

		txtIngresarIdDel = new JTextField();
		txtIngresarIdDel.setForeground(new Color(192, 192, 192));
		txtIngresarIdDel.setFont(new Font("Trebuchet MS", Font.PLAIN, 15));
		txtIngresarIdDel.setText("  Ingrese Id Piloto");
		txtIngresarIdDel.setToolTipText("  Ingrese Id Piloto");
		txtIngresarIdDel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txtIngresarIdDel.setFocusable(true);
				txtIngresarIdDel.requestFocus();
			}
		});
		txtIngresarIdDel.addFocusListener(new java.awt.event.FocusListener() {
			public void focusGained(java.awt.event.FocusEvent evt) {
				if (txtIngresarIdDel.getText().equals("  Ingrese Id Piloto")) {
					txtIngresarIdDel.setText("");
					txtIngresarIdDel.setForeground(Color.BLACK);
				}
			}

			public void focusLost(java.awt.event.FocusEvent evt) {
				if (txtIngresarIdDel.getText().isEmpty()) {
					txtIngresarIdDel.setForeground(new Color(192, 192, 192));
					txtIngresarIdDel.setText("  Ingrese Id Piloto");
				}
			}
		});
		btnMostrar2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnMostrar2.setBackground(new Color(0, 165, 190));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnMostrar2.setBackground(new Color(0, 4, 84));
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					DefaultTableModel model2 = (DefaultTableModel) jTable1.getModel();
					model2.setRowCount(0);
					Piloto.mostrarPilotos().forEach((Pilotos) -> {
						try {
							model2.addRow(new Object[] { Pilotos.getNombresPilot(), Pilotos.getApellidosPilot(),
									Pilotos.getFecNaciPiloto(), Pilotos.getNacionalidadPilot(),
									Pilotos.getTipoLicenciaPilot(), Pilotos.getNumeroLicenciaPilot(),
									Pilotos.getFecContratacionPilot(), Pilotos.getEstadoPilot(),
									Pilotos.getDniPilot() });
						} catch (Exception e1) {
							System.out.println("Error al agregar los datos a la tabla: " + e1.getMessage());
						}
					});
				} catch (Exception e1) {
					System.out.println("Error al mostrar los Piloto: " + e1.getMessage());
				}
			}
		});

		planeSearchButton_1 = new JButton();
		planeSearchButton_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {

					String pilotoIdStr = txtIngresarIdDel.getText();

					if (pilotoIdStr.isEmpty()) {
						JOptionPane.showMessageDialog(null, "Por favor, ingresa un ID de piloto.");
						return;
					}

					int pilotoId = Integer.parseInt(pilotoIdStr);

					Piloto piloto = Piloto.getPilotoById(pilotoId);

					if (piloto != null) {
						DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
						model.setRowCount(0);

						model.addRow(new Object[] { piloto.getNombresPilot(), piloto.getApellidosPilot(),
								piloto.getFecNaciPiloto(), piloto.getNacionalidadPilot(), piloto.getTipoLicenciaPilot(),
								piloto.getNumeroLicenciaPilot(), piloto.getFecContratacionPilot(),
								piloto.getEstadoPilot(), piloto.getDniPilot() });
					} else {
						JOptionPane.showMessageDialog(null, "No se encontró un piloto con ese ID.");
					}
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(null, "El ID del piloto debe ser un número válido.");
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "Ocurrió un error: " + ex.getMessage());
				}
			}
		});

		planeSearchButton_1.setText("Buscar");
		planeSearchButton_1.setForeground(Color.WHITE);
		planeSearchButton_1.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
		planeSearchButton_1.setBorderPainted(false);
		planeSearchButton_1.setBackground(new Color(0, 4, 84));

		planeSearchButton = new JButton();
		planeSearchButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {

					String avionIdStr = txtIngreseElId.getText();

					// Validar que el campo no esté vacío
					if (avionIdStr.isEmpty()) {
						JOptionPane.showMessageDialog(null, "Por favor, ingresa un ID de avión.");
						return;
					}

					int avionId = Integer.parseInt(avionIdStr);

					Avion avion = Avion.getAvionById(avionId);

					if (avion != null) {
						DefaultTableModel model = (DefaultTableModel) jTable2.getModel();
						model.setRowCount(0);

						model.addRow(
								new Object[] { avion.getAvionId(), avion.getCapacidadTotal(), avion.getEstadoAvion() });
					} else {
						JOptionPane.showMessageDialog(null, "No se encontró un avión con ese ID.");
					}
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(null, "El ID del avión debe ser un número válido.");
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "Ocurrió un error: " + ex.getMessage());
				}
			}
		});

		planeSearchButton.setText("Buscar");
		planeSearchButton.setForeground(Color.WHITE);
		planeSearchButton.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
		planeSearchButton.setBorderPainted(false);
		planeSearchButton.setBackground(new Color(0, 4, 84));
		javax.swing.GroupLayout bgLayout = new javax.swing.GroupLayout(bg);
		bgLayout.setHorizontalGroup(
			bgLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(bgLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(bgLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, bgLayout.createSequentialGroup()
							.addComponent(btnLimpiar1, GroupLayout.PREFERRED_SIZE, 126, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(btnMostrar2, GroupLayout.PREFERRED_SIZE, 123, GroupLayout.PREFERRED_SIZE)
							.addGap(47))
						.addGroup(Alignment.TRAILING, bgLayout.createSequentialGroup()
							.addComponent(title)
							.addContainerGap(714, Short.MAX_VALUE))
						.addGroup(Alignment.TRAILING, bgLayout.createSequentialGroup()
							.addGroup(bgLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(jScrollPane2, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 740, Short.MAX_VALUE)
								.addGroup(bgLayout.createSequentialGroup()
									.addComponent(txtIngresarIdDel, GroupLayout.DEFAULT_SIZE, 241, Short.MAX_VALUE)
									.addGap(18)
									.addComponent(planeSearchButton_1, GroupLayout.PREFERRED_SIZE, 108, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(txtIngreseElId, GroupLayout.PREFERRED_SIZE, 238, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(planeSearchButton, GroupLayout.PREFERRED_SIZE, 99, GroupLayout.PREFERRED_SIZE))
								.addComponent(jScrollPane1, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 763, GroupLayout.PREFERRED_SIZE))
							.addGap(18))))
		);
		bgLayout.setVerticalGroup(
			bgLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(bgLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(title)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(bgLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtIngresarIdDel, GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
						.addComponent(planeSearchButton_1, GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
						.addComponent(txtIngreseElId, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
						.addComponent(planeSearchButton, GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 239, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(jScrollPane2, GroupLayout.PREFERRED_SIZE, 187, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(bgLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnLimpiar1, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnMostrar2, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE))
					.addGap(15))
		);
		bg.setLayout(bgLayout);
		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
		layout.setHorizontalGroup(
			layout.createParallelGroup(Alignment.LEADING)
				.addComponent(bg, GroupLayout.DEFAULT_SIZE, 791, Short.MAX_VALUE)
		);
		layout.setVerticalGroup(
			layout.createParallelGroup(Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
					.addComponent(bg, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		this.setLayout(layout);
	}
}
