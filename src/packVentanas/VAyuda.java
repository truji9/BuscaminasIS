package packVentanas;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import javax.swing.JTextArea;

public class VAyuda extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VAyuda frame = new VAyuda();
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
	public VAyuda() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 400, 605);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new MigLayout("", "[]", "[][]"));
		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("AYUDA");
		contentPane.add(lblNewLabel, "cell 0 0,alignx center");
		
		JTextArea textArea = new JTextArea();
		textArea.setText("El juego consiste en despejar todas las casillas de una pantalla que no oculten una mina.\n"+
		"Algunas casillas tienen un número, el cual indica la cantidad de minas que hay en las casillas circundantes.\n"+
				"Así, si una casilla tiene el número 3, significa que de las ocho casillas que hay alrededor (si no es\n "
				+ "en una esquina o borde) hay 3 con minas y 5 sin minas.\n"
				+ " Si se descubre una casilla sin número indica que ninguna de las casillas vecinas tiene mina y\n"
				+ " éstas se descubren automáticamente.\n"
				+ "Si se descubre una casilla con una mina se pierde la partida.\n"
				+ "Se puede poner una marca en las casillas que el jugador piensa que hay minas para ayudar a descubrir\n"
				+ " las que están cerca.");
		textArea.setFont(new Font("Roboto Slab", Font.BOLD, 18));
		contentPane.add(textArea, "cell 0 1,grow");
		
		
		
		
		
		
	}

}
