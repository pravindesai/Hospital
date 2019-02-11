package pack;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.ListSelectionModel;

import java.awt.CardLayout;
import java.awt.Font;

public class Pdetails extends JFrame {

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
						try {
					Pdetails frame = new Pdetails();
				} catch (Exception e) {
					System.out.print("Exception: "+e);
				}	
	}

	/**
	 * Create the frame.
	 */
	public Pdetails() {
		Connect();
		setVisible(true);
		setSize(266,143);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 799, 500);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		
		

		try
		{
			rs=st.executeQuery("select pid,pname,disease,wno from patient;");
			
			
			dmodel = new DefaultTableModel(new String[] {
					"id", "name", "disease", "ward no"
				},0);
			
			while(rs.next())
			{
			    int id = rs.getInt("pid");
			    String name = rs.getString("pname");
			    String dis = rs.getString("disease");
			    int wno = rs.getInt("wno");
			    
			    dmodel.addRow(new Object[]{id, name, dis, wno});
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
