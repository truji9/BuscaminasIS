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
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.miginfocom.swing.MigLayout;
import packCodigo.Buscaminas;
import packCodigo.Ranking;
import packCodigo.Tablero;

import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
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
	private JMenuItem item1, item2, item3;
	private JPanel panel_2;
	private JLabel lblNewLabel;
	private JTextField Tiempo;
	private JTextField Banderas;
	private JPanel panel;
	private int fil;
	private int col;
	private JLabel[] lcasillas;
	private VBuscaminas vBusca = this;
	private Boolean juego = true;
	private Boolean finalizado = false;
	private int contP;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VBuscaminas frame = new VBuscaminas(1);
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
		if(nivel == 1){
			setBounds(100, 100, 500, 450);
		}else if(nivel == 2){
			setBounds(100, 100, 730, 600);
		}else if(nivel == 3){
			setBounds(100, 100, 1150, 710);
		}
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
		
		
		item2 = new JMenuItem("Ver");
		item2.addActionListener(this);
		menu2.add(item2);
		
		item3 = new JMenuItem("Ranking");
		item3.addActionListener(this);
		item3.setEnabled(false);
		menu1.add(item3);
		
		
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
		
		lblNewLabel = new JLabel();
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBackground(new Color(255, 255, 0));
		panel_2.add(lblNewLabel, "cell 2 0");
		lblNewLabel.setIcon(new ImageIcon(VBuscaminas.class.getResource("/Reset.png")));
		
		
		lblNewLabel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e){
				item3.setEnabled(false);
				Buscaminas.getBuscaminas().reset(vBusca);
				lblNewLabel.setIcon(new ImageIcon(VBuscaminas.class.getResource("/Reset.png")));
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
		
		autoGuardadoRank();
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
				
				JLabel l1 = new JLabel();
				System.out.println("f: "+ f+" c: "+c);
				lcasillas[cont]=l1;
				cont++;
				l1.setFont(new Font("Tahoma", Font.PLAIN, 11));
				l1.setHorizontalAlignment(SwingConstants.CENTER);
				l1.setBackground(new Color(255, 255, 255));
				panel.add(l1, "cell"+f+" "+c);
								
				l1.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent e){
						 if (e.getButton() == MouseEvent.BUTTON3 && juego && !finalizado) {
							 int a;
							 int b;
							 a=getx(buscarPosCasilla((JLabel)e.getSource()));
							 b=gety(buscarPosCasilla((JLabel)e.getSource()));
							 System.out.println("a: "+ a+" b: "+b);
		                     Buscaminas.getBuscaminas().ponerBandera(a,b);
		                     //Buscaminas.getBuscaminas().comprobarJuego();
		                  }
						 else if(e.getButton() == MouseEvent.BUTTON1 && juego && !finalizado){
							 int a;
							 int b;
							 a=getx(buscarPosCasilla((JLabel)e.getSource()));
							 b=gety(buscarPosCasilla((JLabel)e.getSource()));
							 System.out.println("a: "+ a+" b: "+b);
		                     Buscaminas.getBuscaminas().descubrirCasilla(a,b);

		                     contP++;

		                     //Buscaminas.getBuscaminas().comprobarJuego();
		                     //   l1.setIcon(new ImageIcon(VBuscaminas.class.getResource("/CasillaVacia.png")));
						 }
	                     Buscaminas.getBuscaminas().comprobarJuego();
					}
				});
				l1.setIcon(new ImageIcon(VBuscaminas.class.getResource("/Casilla.png")));
			}
		}
		imprimir();
	}
	
	private int gety(int pPos) {
		System.out.println("gety: "+ pPos+ " res: "+(pPos/fil)+" fila: "+fil);
		return (pPos/(fil+1));

	}

	private int getx(int pPos) {
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
		String[]p = arg.toString().split(",");
		if(o instanceof Buscaminas){ 
			   if(p.length==2){
				   Tiempo.setText(p[0]);
				   Banderas.setText(p[1]);
			   }else if(arg instanceof Boolean){
				   if(arg.toString().equals("false")){
					   juego = false;
					   lblNewLabel.setIcon(new ImageIcon(VBuscaminas.class.getResource("/Perder.png")));
					  // deshabilitarCasillas(); 
					   JOptionPane.showMessageDialog(null, "OOOHHHHH QUE PENA, HAS ENCONTRADO UNA MINA!!!");
						item3.setVisible(true);
				   }
				   else {
					   juego = true;
					   finalizado = false;
					   habilitarCasillas();
				   }
			   } else if(p.length ==3){
				   System.out.println("Fila: "+Integer.parseInt(p[0]) +" Col: "+ Integer.parseInt(p[1]));
				   int pos = calcularPosicion(Integer.parseInt(p[0]), Integer.parseInt(p[1]));
				   if(p[2].toString().equals("PonerBandera")){
					   lcasillas[pos].setIcon(new ImageIcon(VBuscaminas.class.getResource("/CasillaBandera.png")));
				   } else {
					   lcasillas[pos].setIcon(new ImageIcon(VBuscaminas.class.getResource("/Casilla.png"))); 
				   } 

			   } else if(arg.equals("FINALIZADO")){
				   System.out.println("Se ha terminado");
				   finalizado = true;
				   lblNewLabel.setIcon(new ImageIcon(VBuscaminas.class.getResource("/Victoria.png"))); 
				   //////////
				   item3.setEnabled(true);
				   Ranking.getRanking().guardarLista();
				   mostrarRanking();
				   JOptionPane.showMessageDialog(null, "HAS RESUELTO CORRECTAMENTE!!!");
			   }
			} else if(o instanceof Tablero){
				System.out.println("He descubierto");
				if (p.length == 3){
				int pos = calcularPosicion(Integer.parseInt(p[0]), Integer.parseInt(p[1]));
				  if(1<=Integer.parseInt(p[2]) && Integer.parseInt(p[2])<=8){
					  lcasillas[pos].setIcon(new ImageIcon(VBuscaminas.class.getResource("/Casilla"+Integer.parseInt(p[2])+".png")));
				    }else if(Integer.parseInt(p[2])==0){
				    	   lcasillas[pos].setIcon(new ImageIcon(VBuscaminas.class.getResource("/CasillaVacia.png")));
				    }else if(Integer.parseInt(p[2])==10){
				    	   lcasillas[pos].setIcon(new ImageIcon(VBuscaminas.class.getResource("/CasillaMina.png")));
				    }
			}
		}
	}
	

	public void actionPerformed(ActionEvent e) {
        if (e.getSource()==item1) {
        	Buscaminas.getBuscaminas().reset(vBusca);
        } else if (e.getSource() == item2){
        	VAyuda vA = new VAyuda();
			vA.setVisible(true);
        }else if (e.getSource() == item3){
        	mostrarRanking();
//        	Buscaminas.getBuscaminas().calcularPuntos(contP);
//        	VRanking vR = new VRanking();
//			vR.setVisible(true);
        }
   }
	
	public void habilitarCasillas(){
		for(int i=0;i<lcasillas.length;i++){
			lcasillas[i].setEnabled(true);
			lcasillas[i].setIcon(new ImageIcon(VBuscaminas.class.getResource("/Casilla.png")));
		}
	}
	
	public int calcularPosicion(int pFila, int pCol){
		int pos = 0; 
		pos = (pCol*(fil+1))+pFila;
				System.out.println("Posicion: " +pos);
			return pos;	
	}
	
	public void mostrarRanking(){
		Buscaminas.getBuscaminas().calcularPuntos(contP);
    	VRanking vR = new VRanking();
		vR.setVisible(true);
	}
	
	
	public void autoGuardadoRank(){
		Timer timer;
		TimerTask  timerTask = new TimerTask() {
			int p =0;
			@Override
			public void run() {
				try{
		    		 Thread.sleep(10000); 
		    	  }catch (Exception e) {}
				//Ranking.getRanking().guardarLista();
				System.out.println("GUARDADO");
			}
		};
		timer = new Timer();
		timer.scheduleAtFixedRate(timerTask, 0, 50);
	}
}
