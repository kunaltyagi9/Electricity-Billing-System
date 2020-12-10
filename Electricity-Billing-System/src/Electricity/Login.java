package Electricity;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class Login extends JFrame implements ActionListener{
    JLabel l1,l2,l3,l4;
    Choice c;
    JTextField tf1;
    JPasswordField pf2;
    JButton b1,b2,b3;
    JPanel p1,p2,p3,p4;
    Login(){
        super("Login Page");
        
        l1 = new JLabel("Username");
        l1.setBounds(0, 20, 100, 25);
        l2 = new JLabel("Password");
        l2.setBounds(0, 60, 100, 30);
        tf1 = new JTextField(15);
        tf1.setBounds(100, 20, 200, 25);
        pf2 = new JPasswordField(15);
        pf2.setBounds(100, 60, 200, 25);
        
        ImageIcon ic1 = new ImageIcon(ClassLoader.getSystemResource("icon/login.png"));
        Image i1 = ic1.getImage().getScaledInstance(16, 16,Image.SCALE_DEFAULT);
        b1 = new JButton("Login", new ImageIcon(i1));
        b1.setBounds(50, 140, 100, 25);
        
        ImageIcon ic2 = new ImageIcon(ClassLoader.getSystemResource("icon/cancel.jpg"));
        Image i2 = ic2.getImage().getScaledInstance(16, 16,Image.SCALE_DEFAULT);
        b2 = new JButton("Cancel",new ImageIcon(i2));
        b2.setBounds(180, 140, 100, 25);
        
        ImageIcon ic4 = new ImageIcon(ClassLoader.getSystemResource("icon/signup.png"));
        Image i4 = ic4.getImage().getScaledInstance(20, 20,Image.SCALE_DEFAULT);
        b3 = new JButton("Signup",new ImageIcon(i4));
        b3.setBounds(90, 180, 150, 25);
        
        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);


        ImageIcon ic3 = new ImageIcon(ClassLoader.getSystemResource("icon/second.jpg"));
        Image i3 = ic3.getImage().getScaledInstance(250, 250,Image.SCALE_DEFAULT);
        ImageIcon icc3 = new ImageIcon(i3);
        l3 = new JLabel(icc3);
        
        setLayout(new BorderLayout());
        
                
        //p1 = new JPanel();
        p2 = new JPanel();
        p2.setLayout(null);
        //p3 = new JPanel();
        //p4 = new JPanel();
        
        l4 = new JLabel("Logging in as");
        l4.setBounds(0, 100, 100, 25);
        c = new Choice();
        c.add("Admin");
        c.add("Customer");
        c.setBounds(100, 100, 200, 25);
        
        add(l3,BorderLayout.WEST);
        p2.add(l1);
        p2.add(tf1);
        p2.add(l2);
        p2.add(pf2);
        p2.add(l4);
        p2.add(c);
        
        p2.add(b1);
        p2.add(b2);
        p2.add(b3);
        add(p2,BorderLayout.CENTER);
        
        
        //add(p4,BorderLayout.SOUTH);
        
        p2.setBackground(Color.WHITE);
        //p4.setBackground(Color.WHITE);
        
       
        setSize(640,300);
        setLocation(600,300);
        setVisible(true);
        

        
    }
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == b1){
            try{        
                Conn c1 = new Conn();
                String username  = tf1.getText();
                String password  = pf2.getText();
                String user = c.getSelectedItem();
                String q  = "select * from login where username = '"+username+"' and password = '"+password+"' and user = '"+user+"'" ;
                ResultSet rs = c1.s.executeQuery(q);
                if(rs.next()){
                    String meter_no = rs.getString("meter_no");
                    new Project(user, meter_no).setVisible(true);
                    this.setVisible(false);

                }else{
                    JOptionPane.showMessageDialog(null, "Invalid login");
                    tf1.setText("");
                    pf2.setText("");
                }
            }catch(Exception e){
                e.printStackTrace();
                System.out.println("error: "+e);
            }
        }else if(ae.getSource() == b2){
            this.setVisible(false);
        }else if(ae.getSource() == b3){
            this.setVisible(false);
            new Signup().setVisible(true);
        }
    }
    
    public static void main(String[] args){
        new Login().setVisible(true);
    }

    
}



