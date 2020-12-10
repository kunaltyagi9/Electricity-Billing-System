package Electricity;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class CalculateBill extends JFrame implements ActionListener{
    JLabel l1,l2,l3,l4,l5;
    JTextField t1;
    Choice c1,c2;
    JButton b1,b2;
    JPanel p;
    CalculateBill(){
        
        p = new JPanel();
        p.setLayout(null);
        p.setBackground(new Color(173, 216, 230));
        
        l1 = new JLabel("Calculate Electricity Bill");
        l1.setBounds(80, 10, 400, 30);
        l2 = new JLabel("Meter No");
        l2.setBounds(100, 70, 100, 20);
        JLabel l6 = new JLabel("Name");
        l6.setBounds(100, 120, 100, 20);
        p.add(l6);
        JLabel l7 = new JLabel("Address");
        l7.setBounds(100, 170, 100, 20);
        p.add(l7);
        l3 = new JLabel("Units Cosumed");
        l3.setBounds(100, 220, 100, 20);
        l5 = new JLabel("Month");
        l5.setBounds(100, 270, 100, 20);
        
        t1 = new JTextField();
        t1.setBounds(250, 220, 180, 20);
        
        c1 = new Choice();
        c1.setBounds(250, 70, 180, 20);
        try{
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from customer");
            while(rs.next()){
                c1.add(rs.getString("meter_no"));
            }
            c.c.close();
            c.s.close();
        }catch(Exception e){}
        
        JLabel l8 = new JLabel();
        l8.setBounds(250, 120, 180, 20);
        p.add(l8);
        
        JLabel l9 = new JLabel("");
        l9.setBounds(250, 170, 180, 20);
        p.add(l9);
        
        try{
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from customer where meter_no = " + c1.getSelectedItem());
            while(rs.next()){
                l8.setText(rs.getString("name"));
                l9.setText(rs.getString("address"));
            }
        }catch(Exception e){}
        
        c1.addItemListener(new ItemListener(){
            @Override
            public void itemStateChanged(ItemEvent ae){
                try{
                    Conn c = new Conn();
                    ResultSet rs = c.s.executeQuery("select * from customer where meter_no = " + c1.getSelectedItem());
                    while(rs.next()){
                        l8.setText(rs.getString("name"));
                        l9.setText(rs.getString("address"));
                    }
                    c.c.close();
                    c.s.close();
                }catch(Exception e){}
            }
        });
        
        c2 = new Choice();
        c2.setBounds(250, 270, 180, 20);
        c2.add("January");
        c2.add("February");
        c2.add("March");
        c2.add("April");
        c2.add("May");
        c2.add("June");
        c2.add("July");
        c2.add("August");
        c2.add("September");
        c2.add("October");
        c2.add("November");
        c2.add("December");
        
        b1 = new JButton("Submit");
        b1.setBounds(130, 350, 100, 25);
        b2 = new JButton("Cancel");
        b2.setBounds(280, 350, 100, 25);
        
        b1.setBackground(Color.BLACK);
        b1.setForeground(Color.WHITE);

        b2.setBackground(Color.BLACK);
        b2.setForeground(Color.WHITE);
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/hicon2.jpg"));
        Image i2 = i1.getImage().getScaledInstance(200, 300,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        l4 = new JLabel(i3);
        
        
        
        l1.setFont(new Font("Senserif",Font.PLAIN,26));
        //Move the label to center
        l1.setHorizontalAlignment(JLabel.CENTER);
        
        
        p.add(l1);
        p.add(l2);
        p.add(c1);
        p.add(l5);
        p.add(c2);
        p.add(l3);
        p.add(t1);
        p.add(b1);
        p.add(b2);
        
        setLayout(new BorderLayout(30,30));
        
        //add(l1,"North");
        add(p,"Center");
        add(l4,"West");
        
        
        b1.addActionListener(this);
        b2.addActionListener(this);
        
        getContentPane().setBackground(Color.WHITE);        
        setSize(800,500);
        setLocation(550,220);
    }
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == b1){
            String meter_no = c1.getSelectedItem();
            String units = t1.getText();
            String month = c2.getSelectedItem();

            int consumed_units = Integer.parseInt(units);
            
            //Tax Structure ------------------------------
            int total_bill = 0;
            try{
                Conn c = new Conn();
                ResultSet rs = c.s.executeQuery("select * from tax");
                while(rs.next()){
                    total_bill = consumed_units * Integer.parseInt(rs.getString("cost_per_unit"));
                    total_bill += Integer.parseInt(rs.getString("meter_rent"));
                    total_bill += Integer.parseInt(rs.getString("service_charge"));
                    total_bill += Integer.parseInt(rs.getString("service_tax"));
                    total_bill += Integer.parseInt(rs.getString("swacch_bharat_cess"));
                    total_bill += Integer.parseInt(rs.getString("gst"));
                }
                c.c.close();
                c.s.close();
            }catch(Exception e){}

            String q = "insert into bill values('"+meter_no+"','"+month+"','"+units+"','"+total_bill+"', 'Not Paid')";

            try{
                Conn c1 = new Conn();
                c1.s.executeUpdate(q);
                JOptionPane.showMessageDialog(null,"Bill Updated");
                this.setVisible(false);
            }catch(Exception e){
                e.printStackTrace();
            }
        }if(ae.getSource() == b2){
            this.setVisible(false);
        }
        
    }
    
       
    public static void main(String[] args){
        new CalculateBill().setVisible(true);
    }
}
