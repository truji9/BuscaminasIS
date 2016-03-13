package packVentanas;

import java.awt.Color;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
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
	private int fil;
	private int col;
	private JLabel lblNewLabel1;
	private JLabel[] lcasillas = new JLabel[300];
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VBuscaminas frame = new VBuscaminas(2);
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
	public VBuscaminas(int nivel) {
		
		
		setBounds(100, 100, 262, 300);
		setTitle("Buscaminas");
	//	setIconImage(new ImageIcon(getClass().getResource("/packImagenes/icono.png")).getImage());
		//this.setResizable(false);
		
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
		
		Buscaminas.getBuscaminas().inicioJuego(nivel);
		fil=Buscaminas.getBuscaminas().obtenerNumFilas();
		col=Buscaminas.getBuscaminas().obtenerNumColumnas();
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
		panel.setLayout(new MigLayout("", SCol, SFila));
	}
	
	public void anadirCasillas(){
		String f="";
		String c="";
		int cont=0;
		for(int i=0; i<col; i++){
			f= Integer.toString(i);
			for(int j=0; j<fil; j++){
				c= Integer.toString(j);
				
				JLabel l1 = new JLabel("("+f+","+c+")");
				 System.out.println("f: "+ f+" c: "+c);
				lcasillas[cont]=l1;
				cont++;
				l1.setFont(new Font("Tahoma", Font.PLAIN, 11));
				l1.setHorizontalAlignment(SwingConstants.CENTER);
				l1.setBackground(new Color(255, 255, 255));
				panel.add(l1, "cell"+f+" "+c);
								
				l1.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent e){
						 if (e.getButton() == MouseEvent.BUTTON3) {
							 int a;
							 int b;
							 a=getx(buscarPosCasilla((JLabel)e.getSource()));
							 b=gety(buscarPosCasilla((JLabel)e.getSource()));
							 System.out.println("a: "+ a+" b: "+b);
		                     Buscaminas.getBuscaminas().ponerBandera(a,b);
		                  }
						 else if(e.getButton() == MouseEvent.BUTTON1){
							 int a;
							 int b;
							 a=getx(buscarPosCasilla((JLabel)e.getSource()));
							 b=gety(buscarPosCasilla((JLabel)e.getSource()));
							 System.out.println("a: "+ a+" b: "+b);
		                     Buscaminas.getBuscaminas().descubrirCasilla(a,b);
						 }
					}
				});
				
/*				JButton button = new JButton(""+f+","+c);
				panel.add(button, "cell"+f+" "+c);
				
				button.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent e){
						 if (e.getButton() == MouseEvent.BUTTON3) {
		                     System.out.println("Right Button Pressed");
		                  }
						 else if(e.getButton() == MouseEvent.BUTTON1){
		                     System.out.println("Left Button Pressed");
						 }
					}
				});*/
			}
		}
		imprimir();

	}
	
	private int gety(int pPos) {
		// TODO Auto-generated method stub
		
		return pPos/fil;
	}

	private int getx(int pPos) {
		// TODO Auto-generated method stub
		return pPos%fil;
	}

	private int buscarPosCasilla(JLabel source) {
		// TODO Auto-generated method stub
		int pos=0;
		while(lcasillas[pos]!=source){
			pos++;
		}
		return pos;
	}
	
	private void imprimir(){
		for(int i=0;i<lcasillas.length;i++){
			System.out.println(lcasillas[i]);
		}
	}
}
