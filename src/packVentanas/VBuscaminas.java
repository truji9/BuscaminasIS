package packVentanas;

import java.awt.Color;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import net.miginfocom.swing.MigLayout;
import packCodigo.Buscaminas;

import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Font;

@SuppressWarnings("serial")
public class VBuscaminas extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JMenuBar menuBar;
	private JMenu menu1, menu2;
	private JMenuItem item1, item2;
	private JPanel panel_2;
	private JLabel lblNewLabel;
	private JTextField Tiempo;
	private JTextField Banderas;
	private JPanel panel;
	private int fil=Buscaminas.getBuscaminas().obtenerNumFilas();
	private int col=Buscaminas.getBuscaminas().obtenerNumColumnas();
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VBuscaminas frame = new VBuscaminas();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public VBuscaminas() {
		
		setBounds(100, 100, 262, 300);
		setTitle("Buscaminas");
		setIconImage(new ImageIcon(getClass().getResource("/packImagenes/icono.png")).getImage());
		this.setResizable(false);
		
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		menu1 = new JMenu("Juego");
		menuBar.add(menu1);
		
		menu2 = new JMenu("Ayuda");
		menuBar.add(menu2);
		
		item1 = new JMenuItem("Nuevo");
		item1.addActionListener(this);
		menu1.add(item1);
		
		item2 = new JMenuItem("Ver");
		item2.addActionListener(this);
		menu2.add(item2);
		
		contentPane = new JPanel();
		contentPane.setBackground(Color.GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[]", "[40.00][grow]"));
		
		panel_2 = new JPanel();
		panel_2.setBackground(Color.LIGHT_GRAY);
		contentPane.add(panel_2, "cell 0 0,grow");
		panel_2.setLayout(new MigLayout("", "[65.00][20.00][65.00][20][65.00]", "[]"));
		
		Banderas = new JTextField();
		panel_2.add(Banderas, "cell 0 0,growx");
		Banderas.setColumns(10);
		
		lblNewLabel = new JLabel("Reset");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBackground(new Color(255, 255, 0));
		panel_2.add(lblNewLabel, "cell 2 0");
		
		Tiempo = new JTextField();
		panel_2.add(Tiempo, "cell 4 0,growx");
		Tiempo.setColumns(10);
		
		panel = new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);
		contentPane.add(panel, "cell 0 1,grow");
//		panel.setLayout(new MigLayout("", "[grow]", "[][]"));
		
		mostrarTablero();
		anadirCasillas();
	}

	public void actionPerformed(ActionEvent e) {
    	Container f=this.getContentPane();
        if (e.getSource()==item1) {
            f.setBackground(new Color(255,0,0));
        }
   }
	
	public void mostrarTablero(){
		
		String SFila = "";
		String SCol = "";
		for(int i=0;i<fil;i++){
			SFila=SFila+"[]";
			for(int j=0;j<col;j++){
				SCol=SCol+"[]";
			}
		}
		panel.setLayout(new MigLayout("", SFila, SCol));
	}
	
	public void anadirCasillas(){
		String f="";
		String c="";
		for(int i=0; i<fil; i++){
			f= Integer.toString(i);
			for(int j=0; j<col; j++){
				c= Integer.toString(j);
				lblNewLabel = new JLabel("");
				lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 11));
				lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
				lblNewLabel.setBackground(new Color(255, 255, 0));
				panel.add(lblNewLabel, "cell"+f+" "+c);
			}
		}
	}
}
