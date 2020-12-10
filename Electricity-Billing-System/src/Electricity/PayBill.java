package Electricity;

import java.sql.*;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class PayBill extends JFrame implements ActionListener{
    
    Choice c1;
    JButton b1, b2;
    public static void main(String[] args) {
        new PayBill("").setVisible(true);
    }
    String meter;
    PayBill(String meter) {
        this.meter = meter;
        setBounds(550, 220, 900, 600);

        JPanel p1 = new JPanel();
        p1.setLayout(null);
        p1.setBackground(Color.WHITE);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/bill.png"));
        Image i3 = i1.getImage().getScaledInstance(600, 300, Image.SCALE_DEFAULT);
        ImageIcon i2 = new ImageIcon(i3);
        JLabel l51 = new JLabel(i2);
        l51.setBounds(400, 120, 600, 300);
        p1.add(l51);

        JLabel lblName = new JLabel("Electricity Bill");
        lblName.setFont(new Font("Yu Mincho", Font.PLAIN, 24));
        lblName.setBounds(120, 5, 350, 53);
        p1.add(lblName);

        JLabel l1 = new JLabel("Meter Number");
        l1.setFont(new Font("Yu Mincho", Font.PLAIN, 15));
        l1.setBounds(35, 80, 200, 15);
        p1.add(l1);
        
        JLabel l11 = new JLabel();
        l11.setFont(new Font("Yu Mincho", Font.PLAIN, 15));
        l11.setBounds(300, 80, 200, 15);
        p1.add(l11);

        JLabel lblId = new JLabel("Name");
        lblId.setFont(new Font("Yu Mincho", Font.PLAIN, 15));
        lblId.setBounds(35, 140, 200, 15);
        p1.add(lblId);

        JLabel l12 = new JLabel();
        l12.setFont(new Font("Yu Mincho", Font.PLAIN, 15));
        l12.setBounds(300, 140, 200, 15);
        p1.add(l12);

        JLabel la1 = new JLabel("Month");
        la1.setFont(new Font("Yu Mincho", Font.PLAIN, 15));
        la1.setBounds(35, 200, 200, 15);
        p1.add(la1);
        
        c1 = new Choice();
        c1.add("January");
        c1.add("February");
        c1.add("March");
        c1.add("April");
        c1.add("May");
        c1.add("June");
        c1.add("July");
        c1.add("August");
        c1.add("September");
        c1.add("October");
        c1.add("November");
        c1.add("December");
        c1.setBounds(300, 200, 200, 15);
        add(c1);
        
        JLabel l3 = new JLabel("Units");
        l3.setFont(new Font("Yu Mincho", Font.PLAIN, 15));
        l3.setBounds(35, 260, 200, 15);
        p1.add(l3);
        
        JLabel l13 = new JLabel();
        l13.setFont(new Font("Yu Mincho", Font.PLAIN, 15));
        l13.setBounds(300, 260, 200, 15);
        p1.add(l13);
        
        JLabel l4 = new JLabel("Total Bill");
        l4.setFont(new Font("Yu Mincho", Font.PLAIN, 15));
        l4.setBounds(35, 320, 200, 15);
        p1.add(l4);
        
        JLabel l14 = new JLabel();
        l14.setFont(new Font("Yu Mincho", Font.PLAIN, 15));
        l14.setBounds(300, 320, 200, 15);
        p1.add(l14);
        
        JLabel l5 = new JLabel("Status");
        l5.setFont(new Font("Yu Mincho", Font.PLAIN, 15));
        l5.setBounds(35, 380, 200, 15);
        p1.add(l5);
        
        JLabel l15 = new JLabel();
        l15.setFont(new Font("Yu Mincho", Font.PLAIN, 15));
        l15.setBounds(300, 380, 200, 15);
        l15.setForeground(Color.RED);
        p1.add(l15);

        add(p1);

        try {
            Conn c = new Conn();
            String name = "";
            ResultSet rs = c.s.executeQuery("select * from customer where meter_no = '"+meter+"'");
            while (rs.next()) {
                l11.setText(rs.getString("meter_no"));
                l12.setText(rs.getString("name"));
            }
            rs = c.s.executeQuery("select * from bill where meter_no = '"+meter+"' AND month = 'January'");
            while(rs.next()){
                l13.setText(rs.getString("units"));
                l14.setText("Rs " + rs.getString("total_bill"));
                l15.setText(rs.getString("status"));
            }

        } catch (Exception e) {
        }
        
        c1.addItemListener(new ItemListener(){
            @Override
            public void itemStateChanged(ItemEvent ae){
                try{
                    Conn c = new Conn();
                    ResultSet rs = c.s.executeQuery("select * from bill where meter_no = '"+meter+"' AND month = '"+c1.getSelectedItem()+"'");
                    while(rs.next()){
                        l13.setText(rs.getString("units"));
                        l14.setText("Rs " + rs.getString("total_bill"));
                        l15.setText(rs.getString("status"));
                    }
                }catch(Exception e){}
            }
        });
        
        b1 = new JButton("Pay");
        b1.setBackground(Color.BLACK);
        b1.setForeground(Color.WHITE);
        b1.setBounds(100, 440, 120, 24);
        b1.addActionListener(this);
        p1.add(b1);
        
        b2 = new JButton("Back");
        b2.setBackground(Color.BLACK);
        b2.setForeground(Color.WHITE);
        b2.setBounds(300, 440, 120, 24);
        b2.addActionListener(this);
        p1.add(b2);
    }
    
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == b1){
            try{
                Conn c = new Conn();
                c.s.executeUpdate("update bill set status = 'Paid' where meter_no = '"+meter+"' AND month = '"+c1.getSelectedItem()+"'");
            }catch(Exception e){}
            this.setVisible(false);
            new Paytm(meter).setVisible(true);
        }else if(ae.getSource() == b2){
            this.setVisible(false);
        }
    }
}
