package Electricity;


import java.awt.*;
import javax.swing.*;
import java.sql.*;
import java.awt.event.*;
import javax.swing.border.*;

public class Signup extends JFrame implements ActionListener{

    private JPanel contentPane;
    private JTextField textField;
    private JTextField textField_1;
    private JTextField textField_2;
    private JTextField textField_3;
    private JTextField t1;
    private JButton b1, b2;
    private JComboBox comboBox;
    JLabel l1;


    public static void main(String[] args) {
        new Signup().setVisible(true);
    }

    public Signup() {
        setBounds(600, 250, 700, 406);
	contentPane = new JPanel();
	contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	contentPane.setBackground(Color.WHITE);
	contentPane.setLayout(null);
        
	JLabel lblUsername = new JLabel("Username :");
	lblUsername.setForeground(Color.DARK_GRAY);
	lblUsername.setFont(new Font("Tahoma", Font.BOLD, 14));
	lblUsername.setBounds(99, 86, 92, 26);
	contentPane.add(lblUsername);

	JLabel lblName = new JLabel("Name :");
	lblName.setForeground(Color.DARK_GRAY);
	lblName.setFont(new Font("Tahoma", Font.BOLD, 14));
	lblName.setBounds(99, 123, 92, 26);
	contentPane.add(lblName);

	JLabel lblPassword = new JLabel("Password :");
	lblPassword.setForeground(Color.DARK_GRAY);
	lblPassword.setFont(new Font("Tahoma", Font.BOLD, 14));
	lblPassword.setBounds(99, 160, 92, 26);
	contentPane.add(lblPassword);

	comboBox = new JComboBox(new String[] { "Admin", "Customer"});
	comboBox.setBounds(265, 202, 148, 20);
	contentPane.add(comboBox);
        
        l1 = new JLabel("Meter Number");
        l1.setBounds(99, 240, 152, 26);
        l1.setForeground(Color.DARK_GRAY);
        l1.setFont(new Font("Tahoma", Font.BOLD, 14));
        contentPane.add(l1);
        l1.setVisible(false);

        t1 = new JTextField();
        t1.setBounds(265, 240, 148, 20);
        contentPane.add(t1);
        t1.setVisible(false);
        
        comboBox.addItemListener(new ItemListener(){
            @Override
            public void itemStateChanged(ItemEvent ae){
                String user = (String) comboBox.getSelectedItem();
                if(user.equals("Customer")){
                    l1.setVisible(true);
                    t1.setVisible(true);
                }else{
                    l1.setVisible(false);
                    t1.setVisible(false);
                }
            }
        });

	JLabel lblSecurityQuestion = new JLabel("Create Account As :");
	lblSecurityQuestion.setForeground(Color.DARK_GRAY);
	lblSecurityQuestion.setFont(new Font("Tahoma", Font.BOLD, 14));
	lblSecurityQuestion.setBounds(99, 197, 140, 26);
	contentPane.add(lblSecurityQuestion);
        
        ImageIcon c1 = new ImageIcon(ClassLoader.getSystemResource("icon/signupImage.png"));
        Image i1 = c1.getImage().getScaledInstance(250, 250,Image.SCALE_DEFAULT);
        ImageIcon i2 = new ImageIcon(i1);
        
        JLabel l6 = new JLabel(i2);
        l6.setBounds(430, 50, 250, 250);
        add(l6);

        textField = new JTextField();
	textField.setBounds(265, 91, 148, 20);
	contentPane.add(textField);
	textField.setColumns(10);

	textField_1 = new JTextField();
	textField_1.setColumns(10);
	textField_1.setBounds(265, 128, 148, 20);
	contentPane.add(textField_1);

        textField_2 = new JTextField();
	textField_2.setColumns(10);
	textField_2.setBounds(265, 165, 148, 20);
	contentPane.add(textField_2);

	b1 = new JButton("Create");
	b1.addActionListener(this);
	b1.setFont(new Font("Tahoma", Font.BOLD, 13));
	b1.setBounds(140, 290, 100, 30);
        b1.setBackground(Color.BLACK);
        b1.setForeground(Color.WHITE);
	contentPane.add(b1);

	b2 = new JButton("Back");
	b2.addActionListener(this);
	b2.setFont(new Font("Tahoma", Font.BOLD, 13));
	b2.setBounds(300, 290, 100, 30);
	b2.setBackground(Color.BLACK);
        b2.setForeground(Color.WHITE);
	contentPane.add(b2);

	JPanel panel = new JPanel();
	panel.setForeground(new Color(34, 139, 34));
	panel.setBorder(new TitledBorder(new LineBorder(new Color(173, 216, 230), 2), "Create-Account",
			TitledBorder.LEADING, TitledBorder.TOP, null, new Color(173, 216, 230)));
	panel.setBounds(31, 30, 640, 310);
        panel.setBackground(Color.WHITE);
	contentPane.add(panel);
        
        add(contentPane);

    }
    
    public void actionPerformed(ActionEvent ae){
        try{
            Conn con = new Conn();
            
            if(ae.getSource() == b1){
                
                String username = textField.getText();
                String name = textField_1.getText();
		String password = textField_2.getText();
		String user = (String) comboBox.getSelectedItem();
                String sql = "";
                if(user.equals("Admin")){
                    sql = "insert into login values('','"+username+"', '"+name+"', '"+password+"', '"+user+"')";
                }else{
                    sql = "update login set username = '"+username+"', name = '"+name+"', password = '"+password+"', user = '"+user+"' where meter_no = " + t1.getText();
                }
                con.s.executeUpdate(sql);
		JOptionPane.showMessageDialog(null, "Account Created Successfully ");
                
                new Login().setVisible(true);
                setVisible(false);
            }
            if(ae.getSource() == b2){
                this.setVisible(false);
                new Login().setVisible(true);
			
            }
        }catch(Exception e){
                System.out.println(e);
        }
    }
}