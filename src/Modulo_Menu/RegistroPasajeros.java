package Modulo_Menu;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import Logica.Conexion;

import javax.swing.table.DefaultTableModel;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.Window;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class RegistroPasajeros extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtreserva;
	private JTextField txtnombre;
	private JTextField textApellidos;
	private JTextField textnroasiento;
	private JTextField textnacionalidad;
	private JTextField textnacimiento;
	private JTextField textclase;
	private JTextField textdni;
	private JTextField textpasaporte;
	private JTable table;
	Connection con;
	Statement sen;
	ResultSet res;
	private int x, y;

	public RegistroPasajeros(int reservaId, int npasajeros, int vueloid) {

		setResizable(false);
		setUndecorated(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 628, 598);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnNuevo = new JButton("Nuevo");
		btnNuevo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpiar();
				txtnombre.requestFocus(); 
			}
		});

		JButton btnCerrar = new JButton("Cerrar");
		btnCerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int confirm = JOptionPane.showConfirmDialog(null, "¿Está seguro de que desea salir?",
						"Confirmar salida", JOptionPane.YES_NO_OPTION);

				if (confirm == JOptionPane.YES_OPTION) {
					Window window = SwingUtilities.windowForComponent(btnCerrar);
					window.dispose();
				}
			}
		});

		JButton btnEditar = new JButton("Editar");
		btnEditar.setFont(new Font("Trebuchet MS", Font.BOLD, 15));
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Connection con = Conexion.Conectar();
					DefaultTableModel modeloTabla = (DefaultTableModel) table.getModel();
					for (int i = 0; i < modeloTabla.getRowCount(); i++) {
						int idPasajero = Integer.parseInt(modeloTabla.getValueAt(i, 0).toString()); // IDPasajero
						String nombre = modeloTabla.getValueAt(i, 1).toString();
						String apellido = modeloTabla.getValueAt(i, 2).toString();
						String nroAsiento = modeloTabla.getValueAt(i, 3).toString();
						String nacionalidad = modeloTabla.getValueAt(i, 4).toString();
						String nacimiento = modeloTabla.getValueAt(i, 5).toString();
						String clase = modeloTabla.getValueAt(i, 6).toString();
						String dni = modeloTabla.getValueAt(i, 7).toString();
						String pasaporte = modeloTabla.getValueAt(i, 8).toString();
						String sql = "UPDATE pasajero SET " + "nombres_pasajero = ?, " + "apellido_pasajero = ?, "
								+ "nro_asiento = ?, " + "nacionalidad_pasajero = ?, " + "fech_nacimiento_pasajero = ?, "
								+ "clase = ?, " + "dni = ?, " + "pasaporte = ? " + "WHERE pasajero_id = ?";

						PreparedStatement ps = con.prepareStatement(sql);
						ps.setString(1, nombre);
						ps.setString(2, apellido);
						ps.setString(3, nroAsiento);
						ps.setString(4, nacionalidad);
						ps.setString(5, nacimiento);
						ps.setString(6, clase);
						ps.setString(7, dni);
						ps.setString(8, pasaporte);
						ps.setInt(9, idPasajero);
						ps.executeUpdate();
					}
					JOptionPane.showMessageDialog(null, "Registros actualizados correctamente.");
					con.close();
				} catch (SQLException ex) {
					JOptionPane.showMessageDialog(null, "Error al actualizar los registros: " + ex.getMessage());
					ex.printStackTrace();
				}
			}
		});

		btnEditar.setForeground(new Color(255, 255, 255));
		btnEditar.setBackground(new Color(0, 160, 195));
		btnEditar.setBounds(376, 547, 112, 33);
		contentPane.add(btnEditar);
		btnCerrar.setFont(new Font("Trebuchet MS", Font.BOLD, 15));
		btnCerrar.setForeground(new Color(255, 255, 255));
		btnCerrar.setBackground(new Color(0, 160, 195));
		btnCerrar.setBounds(496, 546, 119, 32);
		contentPane.add(btnCerrar);
		btnNuevo.setForeground(new Color(255, 255, 255));
		btnNuevo.setBackground(new Color(0, 160, 195));
		btnNuevo.setFont(new Font("Trebuchet MS", Font.BOLD, 15));
		btnNuevo.setBounds(10, 546, 112, 32);
		contentPane.add(btnNuevo);

		JButton btneliminar = new JButton("Eliminar");
		btneliminar.setForeground(new Color(255, 255, 255));
		btneliminar.setBackground(new Color(0, 160, 195));
		btneliminar.setFont(new Font("Trebuchet MS", Font.BOLD, 15));
		btneliminar.setBounds(254, 546, 112, 32);
		contentPane.add(btneliminar);
		btneliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String idPasajeroString = JOptionPane.showInputDialog("Ingrese el ID del pasajero que desea eliminar:");
				if (idPasajeroString != null && !idPasajeroString.isEmpty()) {
					try {
						int idPasajero = Integer.parseInt(idPasajeroString);
						Connection con = Conexion.Conectar();
						Statement sen = con.createStatement();
						String query = "SELECT * FROM pasajero WHERE pasajero_id = " + idPasajero;
						ResultSet res = sen.executeQuery(query);
						if (res.next()) {
							int confirm = JOptionPane.showConfirmDialog(null,
									"¿Está seguro de eliminar el pasajero con ID: " + idPasajero + "?",
									"Confirmar eliminación", JOptionPane.YES_NO_OPTION);
							if (confirm == JOptionPane.YES_OPTION) {
								String deleteQuery = "DELETE FROM pasajero WHERE pasajero_id = " + idPasajero;
								sen.executeUpdate(deleteQuery);
								DefaultTableModel modeloTabla = (DefaultTableModel) table.getModel();
								for (int i = 0; i < modeloTabla.getRowCount(); i++) {
									if (modeloTabla.getValueAt(i, 0).equals(idPasajero)) {
										modeloTabla.removeRow(i);
										break;
									}
								}
								JOptionPane.showMessageDialog(null, "Registro eliminado exitosamente.");
							}
						} else {
							JOptionPane.showMessageDialog(null, "No se encontró un pasajero con ese ID.");
						}
						con.close();
					} catch (NumberFormatException ex) {
						JOptionPane.showMessageDialog(null, "Por favor ingrese un ID válido.");
					} catch (SQLException ex) {
						JOptionPane.showMessageDialog(null, "Error al eliminar el registro.");
						ex.printStackTrace();
					}
				} else {
					JOptionPane.showMessageDialog(null, "Debe ingresar un ID para eliminar.");
				}
			}
		});

		JButton btnRegistrar = new JButton("Registrar");
		btnRegistrar.setForeground(new Color(255, 255, 255));
		btnRegistrar.setBackground(new Color(0, 160, 195));
		btnRegistrar.setFont(new Font("Trebuchet MS", Font.BOLD, 15));
		btnRegistrar.setBounds(132, 546, 112, 32);
		contentPane.add(btnRegistrar);
		btnRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int resp = JOptionPane.showConfirmDialog(null, "¿Desea Guardar el registro?", "Flyhihg", 0);
				if (resp == 0) {
					try {
						int currentCount = table.getRowCount();
						if (currentCount >= npasajeros) {
							JOptionPane.showMessageDialog(null,
									"Límite de pasajeros alcanzado. No puede registrar más pasajeros.");
							return;
						}
						Connection con = Conexion.Conectar();
						Statement sen = con.createStatement();
						String ap = textApellidos.getText();
						String nom = txtnombre.getText();
						String nacionalidad = textnacionalidad.getText();
						String nacimiento = textnacimiento.getText();
						String clase = textclase.getText();
						String dni = textdni.getText();
						String pasaporte = textpasaporte.getText();
						String nasiento = textnroasiento.getText();
						String comando = "INSERT INTO pasajero (reserva_id, vuelo_id, nombres_pasajero, apellido_pasajero, nro_asiento, nacionalidad_pasajero, fech_nacimiento_pasajero, clase, dni, pasaporte) "
								+ "VALUES ('" + reservaId + "','" + vueloid + "','" + nom + "', '" + ap + "', '"
								+ nasiento + "', '" + nacionalidad + "', '" + nacimiento + "', '" + clase + "', '" + dni
								+ "', '" + pasaporte + "')";
						sen.executeUpdate(comando);

						String query = "SELECT LAST_INSERT_ID()";
						ResultSet rs = sen.executeQuery(query);
						int pasajeroid = 0;
						if (rs.next()) {
							pasajeroid = rs.getInt(1);
						}
						DefaultTableModel modeloTabla = (DefaultTableModel) table.getModel();
						modeloTabla.addRow(new Object[] { pasajeroid, nom, ap, nasiento, nacionalidad, nacimiento,
								clase, dni, pasaporte });
						JOptionPane.showMessageDialog(null, "Registro guardado correctamente.");
						con.close();
					} catch (SQLException a) {
						JOptionPane.showMessageDialog(null, "Error al guardar el registro.");
						a.printStackTrace();
					}
				}
			}
		});

		table = new JTable();
		DefaultTableModel modeloTabla = new DefaultTableModel(new Object[] { "IDPasajero", "Nombre", "Apellido",
				"NroAsiento", "Pais", "Nacimiento", "Clase", "DNI", "Pasaporte" }, 0) {
			private static final long serialVersionUID = 1L;
			@Override
			public boolean isCellEditable(int row, int column) {
			
				return column != 0;
			}
		};
		
		table = new JTable(modeloTabla);
		table.setBounds(26, 272, 574, 217);

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(10, 300, 608, 224);
		contentPane.add(scrollPane);

		table.setModel(modeloTabla);
		table.setBounds(26, 272, 574, 217);
	
		textpasaporte = new JTextField();
		textpasaporte.setFont(new Font("Trebuchet MS", Font.PLAIN, 15));
		textpasaporte.setBounds(514, 248, 86, 28);
		contentPane.add(textpasaporte);
		textpasaporte.setColumns(10);

		JLabel lblPasaporte = new JLabel("Pasaporte");
		lblPasaporte.setFont(new Font("Trebuchet MS", Font.BOLD, 15));
		lblPasaporte.setBounds(424, 254, 73, 14);
		contentPane.add(lblPasaporte);

		textdni = new JTextField();
		textdni.setFont(new Font("Trebuchet MS", Font.PLAIN, 15));
		textdni.setBounds(271, 248, 119, 28);
		contentPane.add(textdni);
		textdni.setColumns(10);

		JLabel lbldni = new JLabel("DNI");
		lbldni.setFont(new Font("Trebuchet MS", Font.BOLD, 15));
		lbldni.setBounds(230, 253, 46, 14);
		contentPane.add(lbldni);

		textclase = new JTextField();
		textclase.setFont(new Font("Trebuchet MS", Font.PLAIN, 15));
		textclase.setBounds(86, 248, 104, 28);
		contentPane.add(textclase);
		textclase.setColumns(10);

		JLabel lblClase = new JLabel("Clase");
		lblClase.setFont(new Font("Trebuchet MS", Font.BOLD, 15));
		lblClase.setBounds(26, 258, 50, 14);
		contentPane.add(lblClase);

		textnacimiento = new JTextField();
		textnacimiento.setFont(new Font("Trebuchet MS", Font.PLAIN, 15));
		textnacimiento.setBounds(424, 180, 176, 28);
		contentPane.add(textnacimiento);
		textnacimiento.setColumns(10);

		JLabel lblnacimiento = new JLabel("Nacimiento");
		lblnacimiento.setFont(new Font("Trebuchet MS", Font.BOLD, 15));
		lblnacimiento.setBounds(319, 186, 95, 14);
		contentPane.add(lblnacimiento);

		textnacionalidad = new JTextField();
		textnacionalidad.setFont(new Font("Trebuchet MS", Font.PLAIN, 15));
		textnacionalidad.setBounds(424, 120, 176, 28);
		contentPane.add(textnacionalidad);
		textnacionalidad.setColumns(10);

		JLabel lblNewLabel = new JLabel("Nacionalidad");
		lblNewLabel.setFont(new Font("Trebuchet MS", Font.BOLD, 15));
		lblNewLabel.setBounds(310, 123, 104, 20);
		contentPane.add(lblNewLabel);

		textnroasiento = new JTextField();
		textnroasiento.setFont(new Font("Trebuchet MS", Font.PLAIN, 15));
		textnroasiento.setBounds(424, 63, 176, 28);
		contentPane.add(textnroasiento);
		textnroasiento.setColumns(10);

		JLabel lblnroasiento = new JLabel("Nro de asiento");
		lblnroasiento.setFont(new Font("Trebuchet MS", Font.BOLD, 15));
		lblnroasiento.setBounds(310, 68, 104, 14);
		contentPane.add(lblnroasiento);

		textApellidos = new JTextField();
		textApellidos.setFont(new Font("Trebuchet MS", Font.PLAIN, 15));
		textApellidos.setBounds(114, 180, 176, 28);
		contentPane.add(textApellidos);
		textApellidos.setColumns(10);

		JLabel lblApellidos = new JLabel("Apellidos");
		lblApellidos.setFont(new Font("Trebuchet MS", Font.BOLD, 15));
		lblApellidos.setBounds(26, 186, 78, 14);
		contentPane.add(lblApellidos);

		txtnombre = new JTextField();
		txtnombre.setFont(new Font("Trebuchet MS", Font.PLAIN, 15));
		txtnombre.setBounds(114, 120, 176, 28);
		contentPane.add(txtnombre);
		txtnombre.setColumns(10);

		JLabel lblNombre = new JLabel("Nombres");
		lblNombre.setFont(new Font("Trebuchet MS", Font.BOLD, 15));
		lblNombre.setBounds(26, 126, 73, 14);
		contentPane.add(lblNombre);

		txtreserva = new JTextField();
		txtreserva.setFont(new Font("Trebuchet MS", Font.PLAIN, 15));
		txtreserva.setText(String.valueOf(reservaId));
		txtreserva.setEditable(false);

		txtreserva.setBounds(114, 63, 178, 28);
		contentPane.add(txtreserva);
		txtreserva.setColumns(10);

		JLabel lblidreserva = new JLabel("ID Reserva");
		lblidreserva.setFont(new Font("Trebuchet MS", Font.BOLD, 15));
		lblidreserva.setBounds(26, 69, 83, 14);
		contentPane.add(lblidreserva);

		JLabel lblFondoPasajeros = new JLabel("");
		lblFondoPasajeros.setFont(new Font("Trebuchet MS", Font.BOLD, 15));
		lblFondoPasajeros
				.setIcon(new ImageIcon(RegistroPasajeros.class.getResource("/com/img/fondoRegistroPasajero.jpg")));
		lblFondoPasajeros.setBounds(0, 0, 628, 620);
		contentPane.add(lblFondoPasajeros);

		JPanel barra = new JPanel();
		barra.setBounds(0, 0, 625, 35);
		contentPane.add(barra);
		barra.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mousePressed(java.awt.event.MouseEvent evt) {
				x = evt.getX();
				y = evt.getY();
			}
		});

		barra.addMouseMotionListener(new java.awt.event.MouseAdapter() {
			public void mouseDragged(java.awt.event.MouseEvent evt) {
				int dx = evt.getX() - x;
				int dy = evt.getY() - y;
				setLocation(getX() + dx, getY() + dy);
			}
		});
	}

	public void limpiar() {
		txtnombre.setText("");
		textApellidos.setText("");
		textclase.setText("");
		textdni.setText("");
		textpasaporte.setText("");
		textnroasiento.setText("");
		textnacionalidad.setText("");
		textnacimiento.setText("");
	}
}
