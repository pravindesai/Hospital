package pack;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.ButtonGroup;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class BillGen extends JFrame {

	private JPanel contentPane;
	Connection con;
	Statement st;
	ResultSet rs;
	static String RdButton;
	private JTextField textField;
	private JTextField testTf;
	private JTextField medTf;
	private JTextField othTf;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BillGen frame = new BillGen();
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
	public BillGen() {
		Connect();
		setVisible(true);
		setSize(266,143);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 799, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Bill");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 31));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 11, 773, 44);
		contentPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(10, 72, 332, 26);
		contentPane.add(textField);
		textField.setColumns(10);
		
		
		JLabel nameLabel = new JLabel("");
		nameLabel.setFont(new Font("Tahoma", Font.ITALIC, 14));
		nameLabel.setBounds(185, 149, 157, 26);
		contentPane.add(nameLabel);
		
		JLabel roomChargelbl = new JLabel("");
		roomChargelbl.setFont(new Font("Tahoma", Font.PLAIN, 15));
		roomChargelbl.setBounds(554, 317, 158, 26);
		contentPane.add(roomChargelbl);
		
		JLabel wardlabel = new JLabel("");
		wardlabel.setFont(new Font("Tahoma", Font.ITALIC, 14));
		wardlabel.setBounds(185, 198, 157, 26);
		contentPane.add(wardlabel);
		
		JLabel disLabel = new JLabel("");
		disLabel.setFont(new Font("Tahoma", Font.ITALIC, 14));
		disLabel.setBounds(185, 253, 157, 26);
		contentPane.add(disLabel);
		
		JLabel Totallbl = new JLabel("Toatal : ");
		Totallbl.setFont(new Font("Tahoma", Font.PLAIN, 18));
		Totallbl.setBounds(10, 433, 79, 26);
		contentPane.add(Totallbl);
		
		JLabel TotalAmt = new JLabel("");
		TotalAmt.setFont(new Font("Tahoma", Font.PLAIN, 18));
		TotalAmt.setBounds(99, 433, 158, 26);
		contentPane.add(TotalAmt);
		
		testTf = new JTextField();
		testTf.setBounds(580, 151, 132, 26);
		contentPane.add(testTf);
		testTf.setColumns(10);
		
		medTf = new JTextField();
		medTf.setColumns(10);
		medTf.setBounds(580, 198, 132, 26);
		contentPane.add(medTf);
		
		othTf = new JTextField();
		othTf.setColumns(10);
		othTf.setBounds(580, 252, 132, 26);
		contentPane.add(othTf);
		
		JLabel testCharges = new JLabel("Test charges: ");
		testCharges.setFont(new Font("Tahoma", Font.PLAIN, 14));
		testCharges.setBounds(413, 151, 157, 26);
		contentPane.add(testCharges);
		
		JLabel lblMedicinCharges = new JLabel("Medicin charges: ");
		lblMedicinCharges.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblMedicinCharges.setBounds(413, 204, 157, 26);
		contentPane.add(lblMedicinCharges);
		
		JLabel lblOtherCharges = new JLabel("Other Charges:");
		lblOtherCharges.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblOtherCharges.setBounds(413, 253, 157, 26);
		contentPane.add(lblOtherCharges);
		
		
		JRadioButton idBtn = new JRadioButton("id");
		
		buttonGroup.add(idBtn);
		idBtn.setBounds(417, 75, 59, 21);
		contentPane.add(idBtn);
		
		JRadioButton nameBtn = new JRadioButton("name");
		nameBtn.setSelected(true);
	
		buttonGroup.add(nameBtn);
		nameBtn.setBounds(478, 75, 59, 21);
		contentPane.add(nameBtn);
		
		JButton btnFind = new JButton("Find");
		btnFind.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					nameLabel.setText("");
					wardlabel.setText("");
					disLabel.setText("");
					TotalAmt.setText("");
					roomChargelbl.setText("");
					
					
					
				try{
					int lclid=0;
					int roomcharges=0;
					if(idBtn.isSelected())
					{
						int x=Integer.parseInt(textField.getText().toString());
						rs=st.executeQuery("select pid,pname,disease,wno from patient where pid="+x+";");
					}
					if(nameBtn.isSelected())
					{
						String x=textField.getText().toString();
						rs=st.executeQuery("select pname,disease,pid,wno from patient where pname='"+x+"';");
						
					}
					
					while(rs.next())
					{
						lclid =rs.getInt("pid");
						String name=rs.getString("pname");
						nameLabel.setText(name);
						String disease=rs.getString("disease");
						disLabel.setText(disease);
						int wno=rs.getInt("wno");
						wardlabel.setText(""+wno);
					}
					
					rs=st.executeQuery("select total,roomcharges from bill where pid="+lclid+";");

					while(rs.next())
					{
					roomcharges=rs.getInt("roomcharges");
					roomChargelbl.setText("+ "+roomcharges);
					}
					
					int finalTotal=Integer.parseInt(medTf.getText().toString())
							+Integer.parseInt(testTf.getText().toString())
							+Integer.parseInt(othTf.getText().toString())
							+roomcharges;
							TotalAmt.setText(Integer.toString(finalTotal));
					
				}catch(Exception e1)
				{
					System.out.println("Radio btn Exception: "+e1);
				}
				
			}
		});
		btnFind.setBounds(575, 73, 137, 24);
		contentPane.add(btnFind);
		
		
		JButton CancelBtn = new JButton("Cancel");
		CancelBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new MainFrame();
				dispose();
			}
		});
		CancelBtn.setFont(new Font("Tahoma", Font.BOLD, 17));
		CancelBtn.setBounds(631, 415, 152, 44);
		contentPane.add(CancelBtn);
		
		JButton PrintBtn = new JButton("Print");
		PrintBtn.setFont(new Font("Tahoma", Font.BOLD, 17));
		PrintBtn.setBounds(418, 415, 152, 44);
		contentPane.add(PrintBtn);
		
		JLabel lblName = new JLabel("Name:");
		lblName.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblName.setBounds(10, 149, 157, 26);
		contentPane.add(lblName);
		
		JLabel lblWard = new JLabel("Ward:");
		lblWard.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblWard.setBounds(10, 198, 157, 26);
		contentPane.add(lblWard);
		
		JLabel lblDisease = new JLabel("Disease:");
		lblDisease.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblDisease.setBounds(10, 253, 157, 26);
		contentPane.add(lblDisease);
		
		
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
