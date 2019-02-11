package pack;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JRadioButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Panel;
import java.awt.Button;
import java.awt.Color;

import javax.swing.JPasswordField;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class loginFrame extends JFrame {
	JRadioButton radioButton;
	Connection con;
	Statement st;
	ResultSet rs;
	String id;
	String pass;
	private JTextField idTf;
	private JPasswordField passwordField;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					loginFrame frame = new loginFrame();
				} catch (Exception e) {
					System.out.println(e);
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public loginFrame() {
		setVisible(true);
		setSize(569,384);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 799, 500);
		setResizable(false);
		setBackground(Color.LIGHT_GRAY);
		
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label_id = new JLabel("Id:");
		label_id.setHorizontalAlignment(SwingConstants.CENTER);
		label_id.setFont(new Font("Dialog", Font.BOLD, 18));
		label_id.setBounds(109, 106, 60, 23);
		contentPane.add(label_id);
		
		idTf = new JTextField();
		idTf.setHorizontalAlignment(SwingConstants.CENTER);
		idTf.setFont(new Font("Dialog", Font.PLAIN, 14));
		idTf.setBounds(219, 98, 341, 41);
		contentPane.add(idTf);
		idTf.setColumns(10);
		
		JLabel lblPassword = new JLabel("password:");
		lblPassword.setHorizontalAlignment(SwingConstants.CENTER);
		lblPassword.setFont(new Font("Dialog", Font.BOLD, 18));
		lblPassword.setBounds(37, 167, 136, 26);
		contentPane.add(lblPassword);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(219, 151, 341, 41);
		contentPane.add(passwordField);
		
		JButton cancelBtn = new JButton("Cancel");
		cancelBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		cancelBtn.setBounds(446, 244, 114, 41);
		contentPane.add(cancelBtn);
		
		radioButton = new JRadioButton("");
		radioButton.setBounds(0, 0, 123, 23);
		contentPane.add(radioButton);
		
		radioButton.setEnabled(false);
		radioButton.setText("not Connected");
		
		JButton loginBtn = new JButton("LogIn");
		loginBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				id=idTf.getText().toString();
				pass=new String(passwordField.getPassword());
				if(id.equals("admin"))
				{
					if(pass.equals("admin"))
						{
						//////////	
						new MainFrame();
						dispose();
						}
				}else {
					JOptionPane.showMessageDialog(null, "Wrong ID or Password", 
							"Dialog",
					        JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
		loginBtn.setBounds(219, 244, 114, 41);
		contentPane.add(loginBtn);
			
		Connect();
	}
	
	public void Connect()
	{
		/*Connection con;
		Statement st;
		ResultSet rs;*/
		try {
			con=DriverManager.getConnection("jdbc:postgresql://localhost:5432/hospital","postgres","");
			st=con.createStatement();
			radioButton.setSelected(true);
			radioButton.setText("Connected");
		}catch(Exception e)
		{
			System.out.println(e);
		}
		
	}
}
