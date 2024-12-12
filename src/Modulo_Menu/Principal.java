package Modulo_Menu;

import java.awt.Color;
import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ImageIcon;

public class Principal extends javax.swing.JPanel {
	private javax.swing.JPanel bg;
	private javax.swing.JLabel image;
	private javax.swing.JLabel jLabel10;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JLabel jLabel3;
	private javax.swing.JLabel jLabel4;
	private javax.swing.JLabel jLabel5;
	private javax.swing.JLabel jLabel6;
	private javax.swing.JLabel jLabel7;
	private javax.swing.JLabel jLabel8;
	private javax.swing.JLabel jLabel9;
	private javax.swing.JLabel title;
	private static final long serialVersionUID = 1L;

	public Principal() {
		initComponents();
		InitStyles();
	}

	private void InitStyles() {
		title.putClientProperty("FlatLaf.style", "font: light $h1.regular.font");
		title.setForeground(Color.black);
		jLabel2.putClientProperty("FlatLaf.styleClass", "large");
		jLabel2.setForeground(Color.black);
		jLabel3.putClientProperty("FlatLaf.styleClass", "large");
		jLabel3.setForeground(Color.black);
		jLabel4.putClientProperty("FlatLaf.styleClass", "large");
		jLabel4.setForeground(Color.black);
		jLabel5.putClientProperty("FlatLaf.styleClass", "large");
		jLabel5.setForeground(Color.black);
		jLabel6.putClientProperty("FlatLaf.styleClass", "large");
		jLabel6.setForeground(Color.black);
		jLabel7.putClientProperty("FlatLaf.styleClass", "large");
		jLabel7.setForeground(Color.black);
		jLabel8.putClientProperty("FlatLaf.styleClass", "large");
		jLabel8.setForeground(Color.black);
		jLabel9.putClientProperty("FlatLaf.styleClass", "large");
		jLabel9.setForeground(Color.black);
		jLabel10.putClientProperty("FlatLaf.styleClass", "large");
		jLabel10.setForeground(Color.black);
	}

	private void initComponents() {
		bg = new javax.swing.JPanel();
		image = new javax.swing.JLabel();
		title = new javax.swing.JLabel();
		jLabel2 = new javax.swing.JLabel();
		jLabel3 = new javax.swing.JLabel();
		jLabel4 = new javax.swing.JLabel();
		jLabel5 = new javax.swing.JLabel();
		jLabel6 = new javax.swing.JLabel();
		jLabel7 = new javax.swing.JLabel();
		jLabel8 = new javax.swing.JLabel();
		jLabel9 = new javax.swing.JLabel();
		jLabel10 = new javax.swing.JLabel();

		setBackground(new java.awt.Color(255, 255, 255));

		bg.setBackground(new Color(240, 240, 240));

		image.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
		image.setIcon(new ImageIcon(Principal.class.getResource("/com/img/freepiks.jpeg"))); // NOI18N

		title.setText("Bienvenido");

		jLabel2.setText("Sistema de Reservas Flyhigh. Gestione y administre de forma eficiente");

		jLabel3.setText(" y sencilla la reserva de vuelos nacionales e internacionales.");

		jLabel4.setText("Procede a llevar un control de sus viajes, accediendo a funciones específicas");

		jLabel5.setText("diseñadas para simplificar su experiencia, como son:");

		jLabel6.setText("• Reserva de vuelos");

		jLabel7.setText("• Registro de nuevos usuarios");

		jLabel8.setText("• Modificación de datos personales y preferencias de viaje");

		jLabel9.setText("• Sección de reportes sobre historial de vuelos");

		jLabel10.setText("• Eliminar todo tipo de Registros");

		javax.swing.GroupLayout bgLayout = new javax.swing.GroupLayout(bg);
		bgLayout.setHorizontalGroup(bgLayout.createParallelGroup(Alignment.LEADING).addGroup(bgLayout
				.createSequentialGroup().addGap(20)
				.addGroup(bgLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(bgLayout.createSequentialGroup().addComponent(jLabel6)
								.addPreferredGap(ComponentPlacement.RELATED, 1172, Short.MAX_VALUE))
						.addGroup(bgLayout.createSequentialGroup().addGroup(bgLayout
								.createParallelGroup(Alignment.LEADING)
								.addComponent(jLabel4, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 408,
										Short.MAX_VALUE)
								.addGroup(bgLayout.createSequentialGroup()
										.addComponent(title, GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE).addGap(338))
								.addGroup(bgLayout.createSequentialGroup().addComponent(jLabel3).addGap(103))
								.addComponent(jLabel2, GroupLayout.DEFAULT_SIZE, 408, Short.MAX_VALUE)
								.addGroup(bgLayout.createSequentialGroup()
										.addGroup(bgLayout.createParallelGroup(Alignment.TRAILING, false)
												.addComponent(jLabel8, Alignment.LEADING, GroupLayout.DEFAULT_SIZE,
														GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(jLabel7, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 282,
														Short.MAX_VALUE))
										.addGap(126))
								.addComponent(jLabel5, GroupLayout.DEFAULT_SIZE, 408, Short.MAX_VALUE)
								.addComponent(jLabel10, GroupLayout.PREFERRED_SIZE, 322, GroupLayout.PREFERRED_SIZE)
								.addComponent(jLabel9, GroupLayout.PREFERRED_SIZE, 397, GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(image, GroupLayout.PREFERRED_SIZE, 296, GroupLayout.PREFERRED_SIZE)
								.addGap(559)))
				.addContainerGap()));
		bgLayout.setVerticalGroup(bgLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(bgLayout.createSequentialGroup().addGap(28)
						.addComponent(title, GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE).addGap(18)
						.addComponent(jLabel2, GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(jLabel3, GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE).addGap(36)
						.addComponent(jLabel4, GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(jLabel5, GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE).addGap(31)
						.addComponent(jLabel6, GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE).addGap(7)
						.addComponent(jLabel7, GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(jLabel8, GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(jLabel9, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(jLabel10, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE).addGap(132))
				.addGroup(bgLayout.createSequentialGroup()
						.addComponent(image, GroupLayout.PREFERRED_SIZE, 622, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		bg.setLayout(bgLayout);

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
		layout.setHorizontalGroup(layout.createParallelGroup(Alignment.LEADING).addComponent(bg,
				GroupLayout.PREFERRED_SIZE, 732, Short.MAX_VALUE));
		layout.setVerticalGroup(layout.createParallelGroup(Alignment.TRAILING).addGroup(Alignment.LEADING,
				layout.createSequentialGroup().addComponent(bg, GroupLayout.PREFERRED_SIZE, 622, Short.MAX_VALUE)
						.addContainerGap()));
		this.setLayout(layout);
	}
}
