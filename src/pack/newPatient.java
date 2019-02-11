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

public class newPatient extends JFrame {
	static int i,j;
	private JPanel contentPane;
	Connection con;
	Statement st;
	ResultSet rs;
	int id,ward;
	String name,dis;
	private JTextField nameTf;
	private JTextField disTf;
	private JTextField wardTf;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					newPatient frame = new newPatient();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public newPatient() {
		
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
		
		JLabel lblNewLabel = new JLabel("Add Patient");
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 25));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(12, 32, 773, 69);
		contentPane.add(lblNewLabel);
		
		JLabel lblDoctorName = new JLabel("Patient Name:");
		lblDoctorName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDoctorName.setFont(new Font("Dialog", Font.BOLD, 17));
		lblDoctorName.setBounds(12, 182, 138, 43);
		contentPane.add(lblDoctorName);
		
		nameTf = new JTextField();
		nameTf.setColumns(10);
		nameTf.setBounds(193, 182, 397, 41);
		contentPane.add(nameTf);
		
		JLabel lblSpeciality = new JLabel("disease:");
		lblSpeciality.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSpeciality.setFont(new Font("Dialog", Font.BOLD, 17));
		lblSpeciality.setBounds(12, 252, 138, 43);
		contentPane.add(lblSpeciality);
		
		disTf = new JTextField();
		disTf.setColumns(10);
		disTf.setBounds(193, 252, 397, 41);
		contentPane.add(disTf);
		
		JButton addBtn = new JButton("Add");
		addBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
			id=++i;
			name=nameTf.getText().toString();
			dis=disTf.getText().toString();
			ward=Integer.parseInt(wardTf.getText().toString());
			
			add_patient(id, name, dis, ward);
			}
		});
		addBtn.setBounds(193, 416, 138, 41);
		contentPane.add(addBtn);
		
		JButton cnclBtn = new JButton("Cancel");
		cnclBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new MainFrame();
				dispose();
			}
		});
		cnclBtn.setBounds(452, 416, 138, 41);
		contentPane.add(cnclBtn);
		
		JLabel lblWard = new JLabel("Ward:");
		lblWard.setHorizontalAlignment(SwingConstants.RIGHT);
		lblWard.setFont(new Font("Dialog", Font.BOLD, 17));
		lblWard.setBounds(12, 325, 138, 48);
		contentPane.add(lblWard);
		
		wardTf = new JTextField();
		wardTf.setColumns(10);
		wardTf.setBounds(193, 330, 397, 43);
		contentPane.add(wardTf);
	}
	
	
	public void Connect()
	{
		/*Connection con;
		Statement st;
		ResultSet rs;*/
		try {
			con=DriverManager.getConnection("jdbc:postgresql://localhost:5432/hospital","postgres","");
			st=con.createStatement();
			
			rs=st.executeQuery("select MAX(pid) from patient;");
			while(rs.next()){
				i=rs.getInt(1);
			}
			System.out.println("i: "+i);
			
			rs=st.executeQuery("select MAX(bid) from bill;");
			while(rs.next()){
				j=rs.getInt(1);
			}
			System.out.println("j: "+j);
		}catch(Exception e)
		{
			System.out.println(e);
		}
		
		
		
	}
	
	void add_patient(int id,String name,String dis,int ward)
	{
	try {
		st.executeUpdate("insert into patient values("
					+id+",'"
					+name+"','"
					+dis+"',"
					+ward+");");
		
		int charges = 0;
		rs=st.executeQuery("select charges from ward where wno="+ward+";");
		while(rs.next())
		{
			charges=rs.getInt(1);
		}
		
		
	st.executeUpdate("insert into bill values("
					+ ++j +",'"
					+charges+"','"
					+i+"',"
					+charges+");");
		
		JOptionPane.showMessageDialog(null, "patient Added", "Done !",JOptionPane.INFORMATION_MESSAGE );
	} catch (Exception e) 
	{
		System.out.println(e);
	}
	
	}
}
