package pack;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class newDoctor extends JFrame {

	private JPanel contentPane;
	Connection con;
	Statement st;
	ResultSet rs;
	int id;
	String name,spec;
	private JTextField idTf;
	private JTextField nameTf;
	private JTextField specialityTf;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					newDoctor frame = new newDoctor();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public newDoctor() {
		
		setVisible(true);
		setSize(266,143);
		setResizable(false);
		setBackground(Color.LIGHT_GRAY);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 799, 500);
		Connect(); 
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Add Doctor");
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 25));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(12, 32, 773, 69);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Doctor Id:");
		lblNewLabel_1.setFont(new Font("Dialog", Font.BOLD, 17));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setBounds(12, 137, 138, 31);
		contentPane.add(lblNewLabel_1);
		
		idTf = new JTextField();
		idTf.setBounds(193, 142, 375, 24);
		contentPane.add(idTf);
		idTf.setColumns(10);
		
		JLabel lblDoctorName = new JLabel("Doctor Name:");
		lblDoctorName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDoctorName.setFont(new Font("Dialog", Font.BOLD, 17));
		lblDoctorName.setBounds(12, 194, 138, 31);
		contentPane.add(lblDoctorName);
		
		nameTf = new JTextField();
		nameTf.setColumns(10);
		nameTf.setBounds(193, 199, 375, 24);
		contentPane.add(nameTf);
		
		JLabel lblSpeciality = new JLabel("Speciality:");
		lblSpeciality.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSpeciality.setFont(new Font("Dialog", Font.BOLD, 17));
		lblSpeciality.setBounds(12, 264, 138, 31);
		contentPane.add(lblSpeciality);
		
		specialityTf = new JTextField();
		specialityTf.setColumns(10);
		specialityTf.setBounds(193, 269, 375, 24);
		contentPane.add(specialityTf);
		
		JButton addBtn = new JButton("Add");
		addBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			id=Integer.parseInt(idTf.getText().toString());
			name=nameTf.getText().toString();
			spec=specialityTf.getText().toString();
			add_doctor(id, name, spec);
			}
		});
		addBtn.setBounds(193, 351, 138, 41);
		contentPane.add(addBtn);
		
		JButton cnclBtn = new JButton("Cancel");
		cnclBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new MainFrame();
				dispose();
			}
		});
		cnclBtn.setBounds(430, 351, 138, 41);
		contentPane.add(cnclBtn);
	}
	
	
	public void Connect()
	{
		/*Connection con;
		Statement st;
		ResultSet rs;*/
		try {
			con=DriverManager.getConnection("jdbc:postgresql://localhost:5432/hospital","postgres","");
			st=con.createStatement();
		}catch(Exception e)
		{
			System.out.println(e);
		}
		
	}
	
	void add_doctor(int id,String name,String spec)
	{
	try {
		st.executeUpdate("insert into doctor values("
				+ id
				+ ",'"+name+"'"
				+ ",'"+spec+"'"
				+ ")");
		JOptionPane.showMessageDialog(null, "Doctor Added", "Done !",JOptionPane.INFORMATION_MESSAGE );
	} catch (Exception e) {
		e.printStackTrace();
	}
	}
}
