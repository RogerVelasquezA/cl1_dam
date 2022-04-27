package com.cibertec.cl1.vista;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.DecimalFormat;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.cibertec.cl1.dao.LibroDao;
import com.cibertec.cl1.dao.TemaDao;
import com.cibertec.cl1.model.Libro;
import com.cibertec.cl1.model.Tema;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class FormCl1 extends JFrame implements ActionListener, MouseListener {

	private JPanel contentPane;
	private JTextField txtTitulo;
	private JTextField txtPrecio;
	private JTextField txtCantidad;
	private JTable table;
	private JButton btnRegistrar;
	private JComboBox<Tema> cboTema;
	List<Tema> temas = null;

	LibroDao daoLibro = new LibroDao();

	int idSeleccionado = -1;
	private JTextField txtOrigen;
	private JLabel lblTitulo;

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormCl1 frame = new FormCl1();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public FormCl1() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 473, 508);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		JLabel lblMantenimientoEstudiante = new JLabel("Mantenimiento Libro");
		lblMantenimientoEstudiante.setHorizontalAlignment(SwingConstants.CENTER);
		lblMantenimientoEstudiante.setForeground(Color.RED);
		lblMantenimientoEstudiante.setFont(new Font("Tahoma", Font.BOLD, 19));
		lblMantenimientoEstudiante.setBounds(10, 11, 414, 35);
		contentPane.add(lblMantenimientoEstudiante);
		JLabel lblNombre = new JLabel("Titulo");
		lblNombre.setBounds(40, 57, 84, 26);
		contentPane.add(lblNombre);
		JLabel lblApellidos = new JLabel("Precio");
		lblApellidos.setBounds(40, 88, 84, 26);
		contentPane.add(lblApellidos);
		JLabel lblEmail = new JLabel("Cantidad");
		lblEmail.setBounds(40, 119, 84, 26);
		contentPane.add(lblEmail);
		JLabel lblCarrera = new JLabel("Tema");
		lblCarrera.setBounds(40, 181, 46, 26);
		contentPane.add(lblCarrera);
		// Atributos
		txtTitulo = new JTextField();
		txtTitulo.addKeyListener(new KeyAdapter() {

			@Override
			public void keyTyped(KeyEvent arg0) {
				String c=String.valueOf(arg0.getKeyChar());
						
				if(!(c.matches("[aA-zZ, Ò—, ·¡, È…, ÌÕ, Û”, ˙⁄, \\s]{1,45}"))){
					arg0.consume();
					JOptionPane.showMessageDialog(null,
							 "Solo se admite valores Alfabeticos");
					
				}
			}
		});
		txtTitulo.setBounds(152, 60, 211, 20);
		contentPane.add(txtTitulo);
		txtTitulo.setColumns(10);
		txtPrecio = new JTextField();
		txtPrecio.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c=e.getKeyChar();				
				if((c<'0' || c>'9') && txtPrecio.getText().contains(".")
						&&(c!=(char)KeyEvent.VK_BACK_SPACE)){
					e.consume();
					JOptionPane.showMessageDialog(null,
							 "Solo se admite valores numericos","Validar numeros", JOptionPane.INFORMATION_MESSAGE );
				}else if((c<'0' || c>'9')&&(c!='.')&&(c!=(char)KeyEvent.VK_BACK_SPACE)){
					e.consume();
					JOptionPane.showMessageDialog(null,
							 "Solo se admite valores numericos","Validar numeros", JOptionPane.INFORMATION_MESSAGE );
				}
			}
		});
		txtPrecio.setBounds(152, 91, 211, 20);
		contentPane.add(txtPrecio);
		txtPrecio.setColumns(10);
		txtCantidad = new JTextField();
		txtCantidad.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c=e.getKeyChar();
				
				if((c<'0' || c>'9') 
						&&(c!=(char)KeyEvent.VK_BACK_SPACE)){
					e.consume();
					JOptionPane.showMessageDialog(null,
							 "Solo se admite valores numericos","Validar numeros", JOptionPane.INFORMATION_MESSAGE );
				}else if((c<'0' || c>'9')&&(c!=(char)KeyEvent.VK_BACK_SPACE)){
					e.consume();
					JOptionPane.showMessageDialog(null,
							 "Solo se admite valores numericos","Validar numeros", JOptionPane.INFORMATION_MESSAGE );
				}
			}
		});
		txtCantidad.setBounds(152, 122, 211, 20);
		contentPane.add(txtCantidad);
		txtCantidad.setColumns(10);
		cboTema = new JComboBox();
		cboTema.setBounds(152, 184, 211, 20);
		contentPane.add(cboTema);

		btnRegistrar = new JButton("Registrar");
		btnRegistrar.addActionListener(this);
		btnRegistrar.setBounds(10, 225, 114, 23);
		contentPane.add(btnRegistrar);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 261, 433, 187);
		contentPane.add(scrollPane);
		table = new JTable();
		table.addMouseListener(this);
		table.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "Id", "Titulo", "Precio", "Cantidad", "Origen", "Tema" }));
		scrollPane.setViewportView(table);

		JLabel lblOrigen = new JLabel("Origen");
		lblOrigen.setBounds(40, 145, 46, 26);
		contentPane.add(lblOrigen);

		txtOrigen = new JTextField();
		txtOrigen.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				String c=String.valueOf(e.getKeyChar());
				
				if(!(c.matches("[aA-zZ, Ò—, ·¡, È…, ÌÕ, Û”, ˙⁄, \\s]{1,45}"))){
					e.consume();
					JOptionPane.showMessageDialog(null,
							 "Solo se admite valores Alfabeticos");
					
				}
			}
		});
		txtOrigen.setColumns(10);
		txtOrigen.setBounds(152, 151, 211, 20);
		contentPane.add(txtOrigen);

		lblTitulo = new JLabel("");
		lblTitulo.setForeground(Color.RED);
		lblTitulo.setBounds(375, 59, 56, 16);
		contentPane.add(lblTitulo);
		listaLibro();
		;
		cargaComboTema();
		// lblCantidad.setText("");

	}

	public FormCl1(GraphicsConfiguration gc) {
		super(gc);
		// TODO Auto-generated constructor stub
	}

	public FormCl1(String title, GraphicsConfiguration gc) {
		super(title, gc);
		// TODO Auto-generated constructor stub
	}

	public FormCl1(String title) throws HeadlessException {
		super(title);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == btnRegistrar) {
			do_btnRegistrar_actionPerformed(arg0);
		}

	}
	
	void mensaje(){
		lblTitulo.setText("");
	}

	protected void do_btnRegistrar_actionPerformed(ActionEvent arg0) {

		if(txtTitulo.getText().length()==0 || txtPrecio.getText().length()==0 || txtCantidad.getText().length()==0 || txtOrigen.getText().length()==0){
			JOptionPane.showMessageDialog(null, "No pueden haber espacios vacios");
		}else{
			String titulo = txtTitulo.getText().trim();
			double precio = Double.parseDouble(txtPrecio.getText().trim());
			int cantidad = Integer.parseInt(txtCantidad.getText().trim());
			String origen = txtOrigen.getText().trim();
			validarTexto(titulo);
			DecimalFormat df = new DecimalFormat("#.00");
			double precioformat = Double.parseDouble(df.format(precio));
			

			Tema tem = (Tema) cboTema.getSelectedItem();
			Libro libro = new Libro(titulo, precioformat, cantidad, origen, tem);
			daoLibro.guardarLibro(libro);
			listaLibro();
		}
			 	
		

	}

	public void mouseClicked(MouseEvent arg0) {
		if (arg0.getSource() == table) {
			do_table_mouseClicked(arg0);
		}
	}

	protected void do_table_mouseClicked(MouseEvent arg0) {
	}

	private void listaLibro() {
		List<Libro> data = daoLibro.obtTodosLosLibros();
		DefaultTableModel dtm = (DefaultTableModel) table.getModel();
		dtm.setRowCount(0);
		for (Libro l : data) {
			Object[] fila = { l.getIdLibro(), l.getTitulo(), l.getPrecio(), l.getCantidad(), l.getOrigen(),
					l.getTema().getNombre() };
			dtm.addRow(fila);
		}
		dtm.fireTableDataChanged();
	}

	private void cargaComboTema() {
		Tema c1 = null;
		Tema c2 = null;
		TemaDao temaDao = new TemaDao();
		c1 = new Tema("Matematica");
		c2 = new Tema("comic");
		temaDao.guardarTema(c1);
		temaDao.guardarTema(c2);
		temas = temaDao.traerTodosTemas();
		for (Tema t : temas) {
			cboTema.addItem(t);
		}

	}

	private Tema obtTemaFromCombo(String t) {
		Tema tema = null;
		for (Tema tem : temas) {
			if (tem.getNombre().equals(t)) {
				tema = tem;
			}
		}
		return tema;
	}

	boolean validarNumero(String numero) {
		return numero.matches("[0-9]");
	}

	String leerCantidad() {
		if (!txtCantidad.getText().matches("[0-9]")) {
			lblTitulo.setText("*");
		}
		return txtCantidad.getText();

	}

	String validarEntero(String entero) {
		if (!(entero.matches("[0-9]"))) {
			// JOptionPane.showMessageDialog(null, "Entrada incorrecta");
			lblTitulo.setText("*");
		}
		return entero;
	}

	String validarTexto(String texto) {
		if ((texto.matches("[a-zA-Z]"))) {
			// JOptionPane.showMessageDialog(null, "Entrada incorrecta");
			lblTitulo.setText("*");
		}
		return texto;
	}
}
