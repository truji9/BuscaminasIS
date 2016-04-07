package packVentanas;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import net.miginfocom.swing.MigLayout;
import packCodigo.Buscaminas;

import javax.swing.JTextField;
import java.awt.Choice;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JLabel;

public class VLogin extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private Choice choice;
	private JButton btnOk;
	private JLabel lblNombre;
	private JLabel lblNivel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VLogin frame = new VLogin();
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
	public VLogin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[grow]", "[50][50][50][50][50]"));
		contentPane.add(getLblNombre(), "cell 0 0,alignx center");
		contentPane.add(getTextField(), "cell 0 1,alignx center");
		contentPane.add(getLblNivel(), "cell 0 2,alignx center");
		contentPane.add(getChoice(), "cell 0 3,alignx center");
		contentPane.add(getBtnOk(), "cell 0 4,alignx center");
	}

	private JTextField getTextField() {
		if (textField == null) {
			textField = new JTextField();
			textField.setColumns(10);
			textField.setMaximumSize(new Dimension(200,50));
		}
		return textField;
	}
	private Choice getChoice() {
		if (choice == null) {
			choice = new Choice();
			String arr [] = {"1","2","3"};
			for(int i=0; i<arr.length; i++){
				choice.add(arr[i]);
			}
		}
		
		return choice;
	}
	private JButton getBtnOk() {
		if (btnOk == null) {
			btnOk = new JButton("Aceptar");
			btnOk.addMouseListener(new MouseAdapter(){
				public void mouseClicked(MouseEvent e){
					 if (e.getButton() == MouseEvent.BUTTON1) {
						 Buscaminas.getBuscaminas().establecerNombreJugador(getTextField().getText());
						 Buscaminas.getBuscaminas().establecerNivel(getChoice().getSelectedItem());
	                  }
				}
			});
		}
		return btnOk;
	}
	private JLabel getLblNombre() {
		if (lblNombre == null) {
			lblNombre = new JLabel("Introduzca su nombre:");
		}
		return lblNombre;
	}
	private JLabel getLblNivel() {
		if (lblNivel == null) {
			lblNivel = new JLabel("Seleccione el nivel:");
		}
		return lblNivel;
	}
}
