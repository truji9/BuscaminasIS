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
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

@SuppressWarnings("serial")
public class VBuscaminas extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JMenuBar menuBar;
	private JMenu menu1, menu2;
	private JMenuItem item1, item2;
	private JPanel panel_2;
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
		
		setBounds(100, 100, 212, 300);
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
		contentPane.setLayout(new MigLayout("", "[grow]", "[40.00][grow]"));
		
		panel_2 = new JPanel();
		panel_2.setBackground(Color.LIGHT_GRAY);
		contentPane.add(panel_2, "cell 0 0,grow");
		panel_2.setLayout(new MigLayout("", "[][][]", "[]"));
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);
		contentPane.add(panel, "cell 0 1,grow");
		panel.setLayout(new MigLayout("", "[grow]", "[][]"));
	}

	public void actionPerformed(ActionEvent e) {
    	Container f=this.getContentPane();
        if (e.getSource()==item1) {
            f.setBackground(new Color(255,0,0));
        }
   }
}
