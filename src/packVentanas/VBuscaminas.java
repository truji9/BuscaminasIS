package packVentanas;

import java.awt.Color;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Observable;
import java.util.Observer;

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
import java.awt.Label;

@SuppressWarnings("serial")
public class VBuscaminas extends JFrame implements ActionListener, Observer{

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
	private JLabel[] lcasillas;
	
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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setBounds(100, 100, 262, 300);
		setTitle("Buscaminas");
		
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		menu1 = new JMenu("Juego");
		menuBar.add(menu1);
		
		menu2 = new JMenu("Ayuda");
		menuBar.add(menu2);
		
		item1 = new JMenuItem("Nuevo");
		item1.addActionListener(this);
		menu1.add(item1);
		
		item1.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e){
				Buscaminas.getBuscaminas().reset();
			}
		});
		
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
		Banderas.setEditable(false);
		
		lblNewLabel = new JLabel("Reset");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBackground(new Color(255, 255, 0));
		panel_2.add(lblNewLabel, "cell 2 0");
		
		lblNewLabel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e){
				Buscaminas.getBuscaminas().reset();
			}
		});
		Tiempo = new JTextField();
		panel_2.add(Tiempo, "cell 4 0,growx");
		Tiempo.setColumns(10);
		Tiempo.setEditable(false);
		
		panel = new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);
		contentPane.add(panel, "cell 0 1,grow");
		
		iniciarCasillas(nivel);
		Buscaminas.getBuscaminas().inicioJuego(nivel);
		Buscaminas.getBuscaminas().anadirObservador(this);
		fil=Buscaminas.getBuscaminas().obtenerNumFilas();
		col=Buscaminas.getBuscaminas().obtenerNumColumnas();
		mostrarTablero();
		anadirCasillas();	
	}


	
	private void iniciarCasillas(int pNivel) {
		if(pNivel == 1){
			lcasillas = new JLabel[70];
		}else if(pNivel == 2){
			lcasillas = new JLabel[150];
		}else if(pNivel == 3){
			lcasillas = new JLabel[300];
		}
		
	}

	public void mostrarTablero(){
		
		String SFila = "";
		String SCol = "";
		for(int i=0;i<=fil;i++){
			SFila=SFila+"[]";
			for(int j=0;j<=col;j++){
				SCol=SCol+"[]";
			}
		}
		panel.setLayout(new MigLayout("", SCol, SFila));
	}
	
	public void anadirCasillas(){
		String f="";
		String c="";
		int cont=0;
		for(int i=0; i<=col; i++){
			f= Integer.toString(i);
			for(int j=0; j<=fil; j++){
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
			}
		}
		imprimir();
	}
	
	private int gety(int pPos) {
		// TODO Auto-generated method stub
		System.out.println("gety: "+ pPos+ " res: "+(pPos/fil)+" fila: "+fil);
		return (pPos/(fil+1));

	}

	private int getx(int pPos) {
		// TODO Auto-generated method stub
			if(pPos>10){
				System.out.println("getx: "+ pPos+ " res: "+(pPos%fil)+" fila: "+fil);
				return pPos%(fil+1);
			}
			else{
				System.out.println("getx: "+ pPos+ " res: "+(pPos%fil)+" fila: "+fil);
				return (pPos%(fil+1));
			}
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

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		if(o instanceof Buscaminas){ 
			String[]p = arg.toString().split(",");
			   if(p.length==2){
				   Tiempo.setText(p[0]);
				   Banderas.setText(p[1]);
			   }else if(arg instanceof Boolean){
				   if(arg.toString().equals("false")){
					   deshabilitarCasillas(); 
				   }
				   else {
					   habilitarCasillas();
				   }
			   }
		}
	}

	public void actionPerformed(ActionEvent e) {
    	Container f=this.getContentPane();
        if (e.getSource()==item1) {
            f.setBackground(new Color(255,0,0));
        }
   }
	
	public void deshabilitarCasillas(){
		for(int i=0;i<lcasillas.length;i++){
			lcasillas[i].setEnabled(false);
		}
	}
	
	public void habilitarCasillas(){
		for(int i=0;i<lcasillas.length;i++){
			lcasillas[i].setEnabled(true);
		}
	}
}
