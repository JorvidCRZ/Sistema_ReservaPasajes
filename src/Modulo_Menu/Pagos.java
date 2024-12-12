package Modulo_Menu;

import java.awt.Color;
import javax.swing.table.DefaultTableModel;
import Logica.Pago;
import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class Pagos extends javax.swing.JPanel {

	private static final long serialVersionUID = 1L;
	private javax.swing.JPanel bg;
	private javax.swing.JButton btnEditar;
	private javax.swing.JScrollPane tablaPago;
	private javax.swing.JTable jTable1;
	private javax.swing.JButton btnBuscar;
	private javax.swing.JLabel txtPago;
	private javax.swing.JTextField txtNavegar;
	private JButton btnInsertar_1;

	public Pagos() {
		initComponents();
		InitStyles();
	}

	private void InitStyles() {
		txtPago.putClientProperty("FlatLaf.styleClass", "h1");
		txtPago.setForeground(Color.black);
	}

	private void initComponents() {
		bg = new javax.swing.JPanel();
		txtPago = new javax.swing.JLabel();
		txtPago.setText(" Pagos");
		txtPago.setFont(new Font("Trebuchet MS", Font.BOLD, 25));
		txtNavegar = new javax.swing.JTextField();
		txtNavegar.setFont(new Font("Trebuchet MS", Font.PLAIN, 15));
		txtNavegar.setForeground(new Color(192, 192, 192));
		txtNavegar.setText(" Ingrese el Id del pago , nombre  o apellido del reservante ");
		txtNavegar.addFocusListener(new java.awt.event.FocusListener() {
			@Override
			public void focusGained(java.awt.event.FocusEvent evt) {
				if (txtNavegar.getText().trim().equals("Ingrese el Id del pago , nombre  o apellido del reservante")) {
					txtNavegar.setText("");
					txtNavegar.setForeground(Color.BLACK);
				}
			}

			@Override
			public void focusLost(java.awt.event.FocusEvent evt) {
				if (txtNavegar.getText().isEmpty()) {
					txtNavegar.setForeground(new Color(192, 192, 192));
					txtNavegar.setText(" Ingrese el Id del pago , nombre  o apellido del reservante ");
				}
			}
		});

		tablaPago = new javax.swing.JScrollPane();
		jTable1 = new javax.swing.JTable();
		btnEditar = new javax.swing.JButton();
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnEditar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnEditar.setBackground(new Color(0, 165, 190));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnEditar.setBackground(new Color(0, 4, 84));
			}
		});

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
					if (txtNavegar == null) {
						JOptionPane.showMessageDialog(null, "El campo de búsqueda no está disponible.");
						return;
					}
					String criterio = txtNavegar.getText().trim();
					DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
					model.setRowCount(0);
					if (criterio.matches("\\d+")) {
						int id = Integer.parseInt(criterio);
						Pago pago = new Pago();
						pago = pago.buscarPorID(id);
						if (pago != null) {
							model.addRow(new Object[] { pago.getReserva().getReservaId(), pago.getApellido(),
									pago.getNombre(), pago.getFechaPago(), pago.getMetodoPago(), pago.getMontoPago(),
									pago.getIgv(), pago.getMontoTotal() });
						} else {
							JOptionPane.showMessageDialog(null, "No se encontró el pago con el ID proporcionado.");
						}
					} else {
						Pago pagos = new Pago();
						List<Pago> pagosEncontrados = pagos.buscarPorNombreApellido(criterio);
						if (pagosEncontrados.isEmpty()) {
							JOptionPane.showMessageDialog(null,
									"No se encontraron pagos con el nombre o apellido proporcionado.");
						} else {
							pagosEncontrados.forEach((pago) -> {
								try {
									model.addRow(new Object[] { pago.getReserva().getReservaId(), pago.getApellido(),
											pago.getNombre(), pago.getFechaPago(), pago.getMetodoPago(),
											pago.getMontoPago(), pago.getIgv(), pago.getMontoTotal() });
								} catch (Exception e) {
									System.out.println("Error al agregar los datos a la tabla: " + e.getMessage());
								}
							});
						}
					}
				} catch (Exception e) {
					System.out.println("Error al mostrar los pagos: " + e.getMessage());
				}
			}
		});

		jTable1.setModel(new javax.swing.table.DefaultTableModel(new Object[][] {}, new String[] { "ID Reserva",
				"Apellido", "Nombre", "Fecha de pago", "Metodo de pago", "Monto pago", "IGV", "Monto total" }) {
			private static final long serialVersionUID = 1L;

			Class<?>[] types = { String.class, String.class, String.class, String.class, String.class, String.class,
					String.class, String.class };

			boolean[] canEdit = { false, true, true, true, true, true, false, false };

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
		jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mousePressed(java.awt.event.MouseEvent evt) {
				jTable1MousePressed(evt);
			}
		});
		tablaPago.setViewportView(jTable1);

		btnEditar.setBackground(new Color(0, 4, 84)); 
		btnEditar.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
		btnEditar.setForeground(new java.awt.Color(255, 255, 255)); 
		btnEditar.setText("Editar"); 
		btnEditar.setBorderPainted(false);
		btnEditar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		btnEditar.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        int filaSeleccionada = jTable1.getSelectedRow(); 
		        if (filaSeleccionada == -1) {
		            JOptionPane.showMessageDialog(null, "Selecciona una fila para editar.");
		            return;
		        }
		        int reservaId = (int) jTable1.getValueAt(filaSeleccionada, 0); 
		        String apellidoPasajero = (String) jTable1.getValueAt(filaSeleccionada, 1);
		        String nombresPasajero = (String) jTable1.getValueAt(filaSeleccionada, 2); 
		        String fechaPago = (String) jTable1.getValueAt(filaSeleccionada, 3); 
		        String metodoPago = (String) jTable1.getValueAt(filaSeleccionada, 4); 
		        double montoPago = Double.parseDouble(jTable1.getValueAt(filaSeleccionada, 5).toString());
		        double igv = Double.parseDouble(jTable1.getValueAt(filaSeleccionada, 6).toString());
		        double montoTotal = Double.parseDouble(jTable1.getValueAt(filaSeleccionada, 7).toString()) ; 
		        Pago pago = new Pago();
		        boolean resultado = pago.actualizarReserva(
		            reservaId, fechaPago, metodoPago, montoPago, igv, montoTotal, nombresPasajero, apellidoPasajero
		        );
		        if (resultado) {
		            JOptionPane.showMessageDialog(null, "Registro actualizado correctamente.");
		        } else {
		            JOptionPane.showMessageDialog(null, "Error al actualizar el registro.");
		        }
		    }
		});

		JButton btnMostar = new JButton();
		btnMostar.setText("Mostar");
		btnMostar.setForeground(Color.WHITE);
		btnMostar.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
		btnMostar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		btnMostar.setBorderPainted(false);
		btnMostar.setBackground(new Color(0, 4, 84));

		btnMostar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
			}
		});
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
					Pago pagos = new Pago();
					DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
					model.setRowCount(0);
					pagos.mostrar().forEach((pago) -> {
						try {
							model.addRow(new Object[] { pago.getReserva().getReservaId(), pago.getApellido(),
									pago.getNombre(), pago.getFechaPago(), pago.getMetodoPago(), pago.getMontoPago(),
									pago.getIgv(), pago.getMontoTotal() });
						} catch (Exception e1) {
							System.out.println("Error al agregar los datos a la tabla: " + e1.getMessage());
						}
					});
				} catch (Exception e1) {
					System.out.println("Error al mostrar los pagos: " + e1.getMessage());
				}

			}
		});

		JButton btnLimpiar = new JButton();
		btnLimpiar.setText("Limpiar");
		btnLimpiar.setForeground(Color.WHITE);
		btnLimpiar.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
		btnLimpiar.setBorderPainted(false);
		btnLimpiar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		btnLimpiar.setBackground(new Color(0, 4, 84));
		btnLimpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
				model.setRowCount(0); 
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
		});

		JButton btnEliminar = new JButton();
		btnEliminar.setText("Eliminar");
		btnEliminar.setForeground(Color.WHITE);
		btnEliminar.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
		btnEliminar.setBorderPainted(false);
		btnEliminar.setBackground(new Color(0, 4, 84));
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String reservaIdStr = JOptionPane.showInputDialog("Ingrese el ID de la reserva que desea eliminar:");
				if (reservaIdStr == null || reservaIdStr.isEmpty()) {
					JOptionPane.showMessageDialog(null, "El ID de la reserva es obligatorio.");
					return; 
				}

				try {
					int reservaId = Integer.parseInt(reservaIdStr);
					Pago pago = new Pago();
					pago.eliminarPago(reservaId);
					JOptionPane.showMessageDialog(null, "Pago eliminado con éxito.");
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(null, "El ID de la reserva debe ser un número válido.");
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "Error al eliminar el pago: " + ex.getMessage());
				}
			}
		});

		btnInsertar_1 = new JButton();
		btnInsertar_1.setText("Insertar");
		btnInsertar_1.setForeground(Color.WHITE);
		btnInsertar_1.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
		btnInsertar_1.setBorderPainted(false);
		btnInsertar_1.setBackground(new Color(0, 4, 84));
		btnInsertar_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Pago.insertarPago();
			}
		});

		javax.swing.GroupLayout bgLayout = new javax.swing.GroupLayout(bg);
		bgLayout.setHorizontalGroup(bgLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(bgLayout.createSequentialGroup().addContainerGap()
						.addGroup(bgLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(bgLayout.createSequentialGroup()
										.addComponent(tablaPago, GroupLayout.PREFERRED_SIZE, 721,
												GroupLayout.PREFERRED_SIZE)
										.addContainerGap())
								.addGroup(bgLayout.createSequentialGroup()
										.addComponent(txtPago, GroupLayout.PREFERRED_SIZE, 76,
												GroupLayout.PREFERRED_SIZE)
										.addContainerGap(668, Short.MAX_VALUE))
								.addGroup(bgLayout.createSequentialGroup()
										.addComponent(txtNavegar, GroupLayout.DEFAULT_SIZE, 621, Short.MAX_VALUE)
										.addPreferredGap(ComponentPlacement.RELATED).addComponent(btnBuscar).addGap(28))
								.addGroup(Alignment.TRAILING,
										bgLayout.createSequentialGroup()
												.addComponent(btnInsertar_1, GroupLayout.PREFERRED_SIZE, 106,
														GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.UNRELATED)
												.addComponent(btnEliminar, GroupLayout.PREFERRED_SIZE, 106,
														GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.UNRELATED)
												.addComponent(btnLimpiar, GroupLayout.PREFERRED_SIZE, 106,
														GroupLayout.PREFERRED_SIZE)
												.addGap(18)
												.addComponent(btnMostar, GroupLayout.PREFERRED_SIZE, 106,
														GroupLayout.PREFERRED_SIZE)
												.addGap(15).addComponent(btnEditar, GroupLayout.PREFERRED_SIZE, 106,
														GroupLayout.PREFERRED_SIZE)
												.addGap(26)))));
		bgLayout.setVerticalGroup(bgLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(bgLayout.createSequentialGroup().addContainerGap()
						.addComponent(txtPago, GroupLayout.PREFERRED_SIZE, 61, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(bgLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(txtNavegar, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnBuscar, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
						.addGap(21).addComponent(tablaPago, GroupLayout.PREFERRED_SIZE, 266, GroupLayout.PREFERRED_SIZE)
						.addGroup(bgLayout.createParallelGroup(Alignment.LEADING).addGroup(bgLayout
								.createSequentialGroup().addGap(18)
								.addGroup(bgLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(btnLimpiar, GroupLayout.PREFERRED_SIZE, 40,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(btnEditar, GroupLayout.PREFERRED_SIZE, 40,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(btnMostar, GroupLayout.PREFERRED_SIZE, 40,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(btnEliminar, GroupLayout.PREFERRED_SIZE, 40,
												GroupLayout.PREFERRED_SIZE)))
								.addGroup(bgLayout.createSequentialGroup().addGap(18).addComponent(btnInsertar_1,
										GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)))
						.addGap(97)));
		bg.setLayout(bgLayout);

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
		layout.setHorizontalGroup(layout.createParallelGroup(Alignment.LEADING).addComponent(bg,
				GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
		layout.setVerticalGroup(layout.createParallelGroup(Alignment.LEADING).addComponent(bg, GroupLayout.DEFAULT_SIZE,
				592, Short.MAX_VALUE));
		this.setLayout(layout);
	}

	private void jTable1MousePressed(java.awt.event.MouseEvent evt) {
	}
}