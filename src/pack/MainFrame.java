package pack;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.Font;

public class MainFrame extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();				
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainFrame() {
		setVisible(true);
		setSize(266,143);
		setResizable(false);
		setBackground(Color.LIGHT_GRAY);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 799, 500);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("Menu");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Add Patient");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new newPatient();
			}
		});
		mnNewMenu.add(mntmNewMenuItem);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Add Doctor");
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new newDoctor();
			}
		});
		mnNewMenu.add(mntmNewMenuItem_2);
		
		JMenuItem mntmNewMenuItem_4 = new JMenuItem("Patient Details");
		mntmNewMenuItem_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Pdetails();
			}
		});
		mnNewMenu.add(mntmNewMenuItem_4);
		
		JMenuItem mntmNewMenuItem_3 = new JMenuItem("Available Doctors");
		mntmNewMenuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Ddetails();
			}
		});
		mnNewMenu.add(mntmNewMenuItem_3);
		
		JMenuItem mntmNewMenuItem_5 = new JMenuItem("Generate Bill");
		mntmNewMenuItem_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new BillGen();
				dispose();
			}
		});
		mnNewMenu.add(mntmNewMenuItem_5);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Prescription");
		mnNewMenu.add(mntmNewMenuItem_1);
		
		JMenuItem mntmExit = new JMenuItem("Log out");
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new loginFrame();
				dispose();
			}
		});
		mnNewMenu.add(mntmExit);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("Add Patient");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 17));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new newPatient();
			}
		});
		btnNewButton.setBounds(107, 62, 191, 65);
		contentPane.add(btnNewButton);
		
		JButton button = new JButton("Patient Details");
		button.setFont(new Font("Tahoma", Font.BOLD, 17));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new Pdetails();
			}
		});
		button.setBounds(107, 179, 191, 65);
		contentPane.add(button);
		
		JButton button_1 = new JButton("Generate Bill");
		button_1.setFont(new Font("Tahoma", Font.BOLD, 17));
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new BillGen();
				dispose();
			}
		});
		button_1.setBounds(107, 297, 191, 65);
		contentPane.add(button_1);
		
		JButton button_2 = new JButton("Add Doctor");
		button_2.setFont(new Font("Tahoma", Font.BOLD, 17));
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new newDoctor();
			}
		});
		button_2.setBounds(425, 62, 191, 65);
		contentPane.add(button_2);
		
		JButton button_3 = new JButton("Available Doctors");
		button_3.setFont(new Font("Tahoma", Font.BOLD, 17));
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new Ddetails();
			}
		});
		button_3.setBounds(425, 179, 191, 65);
		contentPane.add(button_3);
		
		JButton btnPric = new JButton("Prescription ");
		btnPric.setFont(new Font("Tahoma", Font.BOLD, 17));
		btnPric.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnPric.setBounds(425, 297, 191, 65);
		contentPane.add(btnPric);
	}
}
