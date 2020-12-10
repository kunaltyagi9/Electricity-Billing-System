/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Electricity;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class ViewInfo extends JFrame implements ActionListener{
    JButton b2;
    ViewInfo(String meter){
        setBounds(600, 250, 870, 625);
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        
        JLabel lblName = new JLabel("VIEW CUSTOMER DETAILS");
        lblName.setFont(new Font("Yu Mincho", Font.PLAIN, 20));
        lblName.setBounds(300, 0, 300, 53);
        add(lblName);
        
        JLabel l1 = new JLabel("Name : ");
        l1.setBounds(30, 80, 150, 25);
        add(l1);
        
        JLabel l11 = new JLabel();
        l11.setBounds(220, 80, 150, 25);
        add(l11);
        
        JLabel l2 = new JLabel("Meter Number : ");
        l2.setBounds(30, 140, 150, 25);
        add(l2);
        
        JLabel l12 = new JLabel();
        l12.setBounds(220, 140, 150, 25);
        add(l12);
        
        JLabel l3 = new JLabel("Address : ");
        l3.setBounds(30, 200, 150, 25);
        add(l3);
        
        JLabel l13 = new JLabel();
        l13.setBounds(220, 200, 150, 25);
        add(l13);
        
        JLabel l4 = new JLabel("City : ");
        l4.setBounds(30, 260, 150, 25);
        add(l4);
        
        JLabel l14 = new JLabel();
        l14.setBounds(220, 260, 150, 25);
        add(l14);
        
        JLabel l5 = new JLabel("State : ");
        l5.setBounds(500, 80, 150, 25);
        add(l5);
        
        JLabel l15 = new JLabel();
        l15.setBounds(690, 80, 150, 25);
        add(l15);
        
        JLabel l6 = new JLabel("Email : ");
        l6.setBounds(500, 140, 150, 25);
        add(l6);
        
        JLabel l16 = new JLabel();
        l16.setBounds(690, 140, 150, 25);
        add(l16);
        
        JLabel l7 = new JLabel("Phone : ");
        l7.setBounds(500, 200, 150, 25);
        add(l7);
        
        JLabel l17 = new JLabel();
        l17.setBounds(690, 200, 150, 25);
        add(l17);
        
        
        b2 = new JButton("Back");
        b2.setBackground(Color.BLACK);
        b2.setForeground(Color.WHITE);
        b2.setBounds(350,300, 100, 25);
        b2.addActionListener(this);
        add(b2);
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/viewcustomer.jpg"));
        Image i2 = i1.getImage().getScaledInstance(600, 300, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel l10 = new JLabel(i3);
        l10.setBounds(20, 310, 600, 300);
        add(l10);
        
        try{
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from customer where meter_no = '"+meter+"'");
            while(rs.next()){
                l11.setText(rs.getString("name"));
                l12.setText(rs.getString("meter_no"));
                l13.setText(rs.getString("address"));
                l14.setText(rs.getString("city"));
                l15.setText(rs.getString("state"));
                l16.setText(rs.getString("email"));
                l17.setText(rs.getString("phone"));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == b2){
            this.setVisible(false);
        }
    }
    
    public static void main(String[] args){
        new ViewInfo("").setVisible(true);
    }
}
