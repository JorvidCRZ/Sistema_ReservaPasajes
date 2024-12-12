package Modulo_Menu;

import Logica.Conexion;
import Logica.Pago;
import Logica.Pasajero;
import Logica.Reserva;
import Logica.Usuario;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.EtchedBorder;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.*;
import java.awt.Font;

public class Reservas extends javax.swing.JPanel {
	private javax.swing.JPanel bg;
	private JTextField textVuelo;
	private JTextField textfechareserva;
	private final JButton Registrar = new JButton("REGISTRAR");
	private JTable tablaregistros;
	private JButton btnLimpiar;
	private JButton btnEditar;
	private JButton btnMostrar;
	private JButton btnBuscar;
	private JButton btnEliminar;
	private JComboBox<String> comboNpasajero;
	private JComboBox<String> comboDestino;
	private JComboBox<String> comboEstado;
	private JTextField textPrecio;
	private JLabel lblPrecio;
	private static final long serialVersionUID = 1L;
	private JLabel lblDestino;

	private void limpiarCampos() {
		textVuelo.setText("");
		textfechareserva.setText("");
	}

	public Reservas() {
		initComponents();
	}

	private void initComponents() {

		bg = new javax.swing.JPanel();
		bg.setBounds(0, 0, 783, 600);
		setBackground(new java.awt.Color(255, 255, 255));

		bg.setBackground(new Color(240, 240, 240));
		setLayout(null);
		add(bg);
		bg.setLayout(null);

		JLabel lblVueloID = new JLabel("VueloID");
		lblVueloID.setForeground(new Color(0, 0, 0));
		lblVueloID.setFont(new Font("Trebuchet MS", Font.BOLD, 15));
		lblVueloID.setHorizontalAlignment(SwingConstants.CENTER);
		lblVueloID.setBounds(35, 102, 65, 20);
		bg.add(lblVueloID);

		JLabel lblFecha = new JLabel("Fecha Reservada");
		lblFecha.setForeground(new Color(0, 0, 0));
		lblFecha.setFont(new Font("Trebuchet MS", Font.BOLD, 15));
		lblFecha.setBounds(334, 159, 121, 14);
		bg.add(lblFecha);

		JLabel lblnpasajeros = new JLabel(" N°pasajeros");
		lblnpasajeros.setForeground(new Color(0, 0, 0));
		lblnpasajeros.setFont(new Font("Trebuchet MS", Font.BOLD, 15));
		lblnpasajeros.setBounds(334, 54, 98, 20);
		bg.add(lblnpasajeros);

		JLabel lblEstado = new JLabel("Estado Reserva");
		lblEstado.setForeground(new Color(0, 0, 0));
		lblEstado.setFont(new Font("Trebuchet MS", Font.BOLD, 15));
		lblEstado.setBounds(334, 103, 111, 18);
		bg.add(lblEstado);

		textVuelo = new JTextField();
		textVuelo.setEditable(false);
		textVuelo.setBackground(new Color(206, 239, 255));
		textVuelo.setBounds(143, 97, 158, 35);
		bg.add(textVuelo);
		textVuelo.setColumns(10);

		textfechareserva = new JTextField();
		textfechareserva.setBackground(new Color(206, 239, 255));
		textfechareserva.setBounds(465, 143, 158, 36);
		bg.add(textfechareserva);
		textfechareserva.setColumns(10);

		Registrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int usuarioId = obtenerUsuarioId();
					if (usuarioId <= 0) {
						JOptionPane.showMessageDialog(null, "El ID del usuario no es válido.");
						return;
					}
					int vueloId;
					try {
						vueloId = Integer.parseInt(textVuelo.getText().trim());
						if (vueloId <= 0) {
							JOptionPane.showMessageDialog(null, "El ID del vuelo debe ser positivo.");
							return;
						}
					} catch (NumberFormatException ex) {
						JOptionPane.showMessageDialog(null, "El ID del vuelo debe ser un número válido.");
						return;
					}
					int numPasajeros;
					try {
						numPasajeros = Integer.parseInt(comboNpasajero.getSelectedItem().toString());
						if (numPasajeros <= 0) {
							JOptionPane.showMessageDialog(null, "El número de pasajeros debe ser positivo.");
							return;
						}
					} catch (NumberFormatException ex) {
						JOptionPane.showMessageDialog(null, "El número de pasajeros debe ser un número válido.");
						return;
					}
					String fechaReserva = textfechareserva.getText().trim();
					if (fechaReserva.isEmpty()) {
						JOptionPane.showMessageDialog(null, "La fecha de reserva no puede estar vacía.");
						return;
					}

					String estadoReserva = comboEstado.getSelectedItem().toString();
					if (estadoReserva.isEmpty()) {
						JOptionPane.showMessageDialog(null, "El estado de reserva no puede estar vacío.");
						return;
					}
					String texto = comboDestino.getSelectedItem().toString();
					int end = texto.indexOf(",");
					String subcadena = texto.substring(end).trim();
					String Destino = subcadena;
					if (Destino.isEmpty()) {
						JOptionPane.showMessageDialog(null, "El destino no puede estar vacío.");
						return;
					}
					Reserva reserva = new Reserva();
					int reservaId = reserva.insertarReserva(usuarioId, vueloId, fechaReserva, numPasajeros,
							estadoReserva, Destino);

					if (reservaId > 0) {
						JOptionPane.showMessageDialog(null, "Reserva registrada con éxito. ID: " + reservaId);
						limpiarCampos(); 
						RegistroPasajeros ventanaPasajeros = new RegistroPasajeros(reservaId, numPasajeros, vueloId);
						ventanaPasajeros.setVisible(true); 
					} else {
						JOptionPane.showMessageDialog(null, "Hubo un error al registrar la reserva.");
					}

				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "Error al registrar la reserva: " + ex.getMessage());
				}
			}
		});

		Registrar.setBackground(new Color(0, 160, 195));
		Registrar.setForeground(new Color(255, 255, 255));
		Registrar.setFont(new Font("Tahoma", Font.BOLD, 12));
		Registrar.setBounds(635, 87, 127, 52);
		bg.add(Registrar);

		tablaregistros = new JTable();
        tablaregistros.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
        DefaultTableModel modelo = new DefaultTableModel() {
            private static final long serialVersionUID = 1L;

            @Override
            public boolean isCellEditable(int row, int column) {
                return true;
            }
        };
		modelo.addColumn("ID Reserva");
		modelo.addColumn("Usuario ID");
		modelo.addColumn("Destino");
		modelo.addColumn("Fecha Reserva");
		modelo.addColumn("Nro Pasajeros");
		modelo.addColumn("Estado");
		modelo.addRow(
				new Object[] { "ID Reserva", "Usuario ID", "Destino", "Fecha Reserva", "Nro Pasajeros", "Estado" });

		tablaregistros.setModel(modelo);

		bg.add(tablaregistros);
		tablaregistros.getTableHeader().setReorderingAllowed(false);
		tablaregistros.getTableHeader().setResizingAllowed(false);
		tablaregistros.setBounds(10, 204, 752, 297); 
		tablaregistros.setModel(modelo);
		JScrollPane scrollPane = new JScrollPane(tablaregistros);
		scrollPane.setBounds(10, 204, 752, 297);
        bg.add(scrollPane);
     
		btnLimpiar = new JButton("Limpiar");
		btnLimpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpiarCampos();
				limpiarTabla(); 
			}
		});
		btnLimpiar.setForeground(new Color(255, 255, 255));
		btnLimpiar.setBackground(new Color(0, 4, 84));
		btnLimpiar.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnLimpiar.setBounds(35, 523, 110, 40);
		bg.add(btnLimpiar);

		btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int filaSeleccionada = tablaregistros.getSelectedRow();
				if (filaSeleccionada == -1) {
					JOptionPane.showMessageDialog(null, "Selecciona una fila para editar.");
					return;
				}

				int reservaId = (int) tablaregistros.getValueAt(filaSeleccionada, 0);
				int usuarioId = (int) tablaregistros.getValueAt(filaSeleccionada, 1);
				String destino = (String) tablaregistros.getValueAt(filaSeleccionada, 2);
				String fechaReserva = (String) tablaregistros.getValueAt(filaSeleccionada, 3);
				int numeroPasajeros = (int) tablaregistros.getValueAt(filaSeleccionada, 4);
				String estado = (String) tablaregistros.getValueAt(filaSeleccionada, 5);
				Reserva reserva = new Reserva();
				reserva.actualizarReserva(reservaId, usuarioId, destino, fechaReserva, numeroPasajeros, estado);
			}
		});
		btnEditar.setForeground(new Color(255, 255, 255));
		btnEditar.setBackground(new Color(0, 4, 84));
		btnEditar.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnEditar.setBounds(318, 523, 114, 40);
		bg.add(btnEditar);
		btnMostrar = new JButton("Mostrar");
		btnMostrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpiarTabla();
				cargarReservas(); 
			}
		});
		btnMostrar.setForeground(new Color(255, 255, 255));
		btnMostrar.setBackground(new Color(0, 4, 84));
		btnMostrar.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnMostrar.setBounds(179, 523, 108, 40);
		bg.add(btnMostrar);

		btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String inputId = JOptionPane.showInputDialog("Ingrese el ID de la reserva a buscar:");
				if (inputId != null && !inputId.trim().isEmpty()) {
					try {
						int reservaId = Integer.parseInt(inputId.trim());
						buscarReservaPorId(reservaId);
					} catch (NumberFormatException ex) {
						JOptionPane.showMessageDialog(null, "Por favor ingrese un número válido.");
					}
				} else {
					JOptionPane.showMessageDialog(null, "Debe ingresar un ID de reserva.");
				}
			}
		});
		btnBuscar.setForeground(new Color(255, 255, 255));
		btnBuscar.setBackground(new Color(0, 4, 84));
		btnBuscar.setBounds(471, 524, 110, 40);
		bg.add(btnBuscar);

		btnEliminar = new JButton("Eliminar");
		btnEliminar.setBackground(new Color(0, 4, 84));
		btnEliminar.setForeground(new Color(255, 255, 255));
		btnEliminar.setBounds(643, 524, 110, 40);
		bg.add(btnEliminar);

		JLabel lblNewLabel = new JLabel("Reservas ");
		lblNewLabel.setForeground(new Color(0, 0, 0));
		lblNewLabel.setFont(new Font("Trebuchet MS", Font.BOLD, 25));
		lblNewLabel.setBounds(10, 11, 121, 35);
		bg.add(lblNewLabel);

		comboDestino = new JComboBox<>();
		comboDestino.setBackground(new Color(206, 239, 255));
		comboDestino.setBounds(143, 54, 158, 35);
		bg.add(comboDestino);
		comboDestino.addItem("Río de Janeiro, Brasil");
		comboDestino.addItem("Cusco, Perú");
		comboDestino.addItem("Santiago, Chile");
		comboDestino.addItem("La Paz, Bolivia");
		comboDestino.addItem("Caracas, Venezuela");
		comboDestino.addItem("Ciudad de México, México");
		comboDestino.addItem("Barcelona, España");
		comboDestino.addItem("Brasilia, Brasil");
		comboDestino.addItem("Quito, Ecuador");
		comboDestino.addItem("Bogotá, Colombia");
		comboDestino.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String destinoSeleccionado = (String) comboDestino.getSelectedItem();
				actualizarVueloYPrecio(destinoSeleccionado);
			}
		});

		comboNpasajero = new JComboBox<>();
		comboNpasajero.setBackground(new Color(206, 239, 255));
		comboNpasajero.setBounds(465, 49, 158, 35);
		bg.add(comboNpasajero);
		comboNpasajero.addItem("1");
		comboNpasajero.addItem("2");
		comboNpasajero.addItem("3");
		comboNpasajero.addItem("4");
		comboNpasajero.addItem("5");
		comboNpasajero.addItem("6");
		comboNpasajero.addItem("7");
		comboNpasajero.addItem("8");
		comboNpasajero.addItem("9");
		comboNpasajero.addItem("10");
		comboNpasajero.addItem("11");
		comboNpasajero.addItem("12");

		textPrecio = new JTextField();
		textPrecio.setEditable(false);
		textPrecio.setColumns(10);
		textPrecio.setBackground(new Color(206, 239, 255));
		textPrecio.setBounds(143, 143, 158, 36);
		bg.add(textPrecio);

		lblPrecio = new JLabel("Precio Vuelo");
		lblPrecio.setForeground(new Color(0, 0, 0));
		lblPrecio.setFont(new Font("Trebuchet MS", Font.BOLD, 15));
		lblPrecio.setBounds(43, 158, 88, 17);
		bg.add(lblPrecio);

		lblDestino = new JLabel("Destino");
		lblDestino.setHorizontalAlignment(SwingConstants.CENTER);
		lblDestino.setForeground(Color.BLACK);
		lblDestino.setFont(new Font("Trebuchet MS", Font.BOLD, 15));
		lblDestino.setBounds(35, 59, 65, 20);
		bg.add(lblDestino);

		comboEstado = new JComboBox<>();
		comboEstado.setBackground(new Color(206, 239, 255));
		comboEstado.setBounds(465, 97, 158, 35);
		bg.add(comboEstado);
		comboEstado.addItem("I");
		comboEstado.addItem("A");

		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String inputId = JOptionPane.showInputDialog("Ingrese el ID de la reserva a eliminar:");
				
				if (inputId != null && !inputId.trim().isEmpty()) {
					try {
						int reservaId = Integer.parseInt(inputId.trim());
						Reserva reserva = new Reserva();
						reserva.eliminarReserva(reservaId);
						Pasajero pasajero = new Pasajero();
						pasajero.eliminarPasajero(reservaId);
						Pago pago = new Pago();
						pago.eliminarPago(reservaId);
						reserva.eliminarDetalleReserva(reservaId);
					} catch (NumberFormatException ex) {
						JOptionPane.showMessageDialog(null,
								"Por favor ingrese un número válido para el ID de reserva.");
					}
				} else {
					JOptionPane.showMessageDialog(null, "Debe ingresar un ID de reserva.");
				}
			}
		});
	}

	private void cargarReservas() {
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			conn = Conexion.Conectar();
			String sql = "SELECT r.reserva_id, r.usuario_id, v.destino, r.fecha_reserva, r.numero_pasajeros, r.estado_reserva\r\n"
					+ "FROM reserva r JOIN vuelo v ON r.vuelo_id = v.vuelo_id Order by reserva_id;"; 
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();
			DefaultTableModel modelo = (DefaultTableModel) tablaregistros.getModel();
			while (rs.next()) {
				modelo.addRow(new Object[] { rs.getInt("reserva_id"), rs.getInt("usuario_id"), rs.getString("destino"),
						rs.getString("fecha_reserva"), rs.getInt("numero_pasajeros"), rs.getString("estado_reserva") });
			}
			tablaregistros.setModel(modelo);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error al cargar reservas: " + e.getMessage());
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pst != null)
					pst.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "Error al cerrar los recursos: " + e.getMessage());
			}
		}
	}

	private void buscarReservaPorId(int reservaId) {
	    Connection conn = null;
	    PreparedStatement pst = null;
	    ResultSet rs = null;
	    try {
	        conn = Conexion.Conectar();
	        String sql = "SELECT r.reserva_id, r.usuario_id, v.destino, r.fecha_reserva, r.numero_pasajeros, r.estado_reserva "
	                   + "FROM reserva r JOIN vuelo v ON r.vuelo_id = v.vuelo_id WHERE r.reserva_id = ?";
	        pst = conn.prepareStatement(sql);
	        pst.setInt(1, reservaId);
	        rs = pst.executeQuery();

	        DefaultTableModel modelo = new DefaultTableModel();
	        modelo.addColumn("ID Reserva");
	        modelo.addColumn("Usuario ID");
	        modelo.addColumn("Destino");
	        modelo.addColumn("Fecha Reserva");
	        modelo.addColumn("Nro Pasajeros");
	        modelo.addColumn("Estado");

	        if (rs.next()) {
	            modelo.addRow(new Object[] {
	                rs.getInt("reserva_id"),
	                rs.getInt("usuario_id"),
	                rs.getString("destino"),
	                rs.getString("fecha_reserva"),
	                rs.getInt("numero_pasajeros"),
	                rs.getString("estado_reserva")
	            });
	            tablaregistros.setModel(modelo);
	        } else {
	            JOptionPane.showMessageDialog(null, "No se encontró ninguna reserva con el ID especificado.");
	        }
	    } catch (SQLException e) {
	        JOptionPane.showMessageDialog(null, "Error al buscar la reserva: " + e.getMessage());
	    } finally {
	        try {
	            if (rs != null) rs.close();
	            if (pst != null) pst.close();
	            if (conn != null) conn.close();
	        } catch (SQLException e) {
	            JOptionPane.showMessageDialog(null, "Error al cerrar recursos: " + e.getMessage());
	        }
	    }
	}

	private void limpiarTabla() {
		DefaultTableModel modelo = (DefaultTableModel) tablaregistros.getModel();
		modelo.setRowCount(0); 
	}

	private int obtenerUsuarioId() {
		Usuario usuarioAutenticado = Usuario.getUsuarioAutenticado();
		if (usuarioAutenticado != null) {
			return usuarioAutenticado.getIdUsuario(); 
		} else {
			return -1; 
		}
	}

	private void actualizarVueloYPrecio(String destino) {
	    String[] partes = destino.split(", ");
	    String ciudadDestino = partes[0];

	    String sql = "SELECT vuelo_id, precio FROM vuelo WHERE destino = ?";

	    try (Connection conn = Conexion.Conectar(); PreparedStatement pst = conn.prepareStatement(sql)) {

	        pst.setString(1, ciudadDestino);

	        ResultSet rs = pst.executeQuery();
	        if (rs.next()) {
	            int vueloId = rs.getInt("vuelo_id");
	            double precio = rs.getDouble("precio");
	            textVuelo.setText(String.valueOf(vueloId));
	            textPrecio.setText(String.valueOf(precio)); 
	        } else {
	            JOptionPane.showMessageDialog(null, "No se encontró un vuelo para el destino seleccionado.");
	        }
	    } catch (SQLException e) {
	        JOptionPane.showMessageDialog(null, "Error al recuperar los datos: " + e.getMessage());
	    }
	}
}
