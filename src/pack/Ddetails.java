package pack;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

public class Ddetails extends JFrame {

	private JPanel contentPane;
	Connection con;
	Statement st;
	ResultSet rs;
	private JTable table;
	DefaultTableModel dmodel=null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Ddetails frame = new Ddetails();
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
	public Ddetails() {
		Connect();
		setVisible(true);
		setSize(266,143);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 799, 500);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		
		try
		{
			rs=st.executeQuery("select did,dnam,spec from doctor;");
			
			
			dmodel = new DefaultTableModel(new String[] {
					"id", "name", "specialist"
				},0);
			
			while(rs.next())
			{
			    int id = rs.getInt("did");
			    String name = rs.getString("dnam");
			    String spec = rs.getString("spec");
			    
			    dmodel.addRow(new Object[]{id, name, spec});
			}
			
			System.out.println(dmodel);
		
		}catch(Exception e)
		{
			System.out.println(e);
		}
		
		table = new JTable();
		table.setCellSelectionEnabled(true);
		table.setEnabled(false);
		table.setColumnSelectionAllowed(true);
		table.setFont(new Font("Tahoma", Font.PLAIN, 16));
		table.setBounds(10, 463, 773, -432);
		table.setToolTipText("");
		table.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
	
		contentPane.setLayout(null);
		table.setBorder(new LineBorder(Color.BLACK, 1));
		table.setBackground(Color.WHITE);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(5, 11, 783, 455);
		contentPane.add(scrollPane);
			table.setModel(dmodel);	
		
	}

	public void Connect()
	{
		/*Connection con;
		Statement st;
		ResultSet rs;*/
		try {
			con=DriverManager.getConnection("jdbc:postgresql://localhost:5432/hospital","postgres","");
			st=con.createStatement();
			System.out.println("connected...");
		}catch(Exception e)
		{
			System.out.println(e);
		}
		
	}
	
}
