/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Electricity;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.awt.event.*;

public class ViewInformation extends JFrame implements ActionListener{
    JButton b1;
    ViewInformation(String meter){
        setBounds(600,250, 850, 650);
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        
        JLabel title = new JLabel("VIEW CUSTOMER INFORMATION");
        title.setBounds(250, 0, 500, 40);
        title.setFont(new Font("Tahoma", Font.PLAIN, 20));
        add(title);
        
        JLabel l1 = new JLabel("Name");
        l1.setBounds(70, 80, 100, 20);
        add(l1);
        
        JLabel l11 = new JLabel();
        l11.setBounds(250, 80, 100, 20);
        add(l11);
        
        JLabel l2 = new JLabel("Meter Number");
        l2.setBounds(70, 140, 100, 20);
        add(l2);
        
        JLabel l12 = new JLabel();
        l12.setBounds(250, 140, 100, 20);
        add(l12);
        
        JLabel l3 = new JLabel("Address");
        l3.setBounds(70, 200, 100, 20);
        add(l3);
        
        JLabel l13 = new JLabel();
        l13.setBounds(250, 200, 100, 20);
        add(l13);
        
        JLabel l4 = new JLabel("City");
        l4.setBounds(70, 260, 100, 20);
        add(l4);
        
        JLabel l14 = new JLabel();
        l14.setBounds(250, 260, 100, 20);
        add(l14);
        
        JLabel l5 = new JLabel("State");
        l5.setBounds(500, 80, 100, 20);
        add(l5);
        
        JLabel l15 = new JLabel();
        l15.setBounds(650, 80, 100, 20);
        add(l15);
        
        JLabel l6 = new JLabel("Email");
        l6.setBounds(500, 140, 100, 20);
        add(l6);
        
        JLabel l16 = new JLabel();
        l16.setBounds(650, 140, 150, 20);
        add(l16);
        
        JLabel l7 = new JLabel("Phone");
        l7.setBounds(500, 200, 100, 20);
        add(l7);
        
        JLabel l17 = new JLabel();
        l17.setBounds(650, 200, 100, 20);
        add(l17);
        
        try{
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from customer where meter = '"+meter+"'");
            while(rs.next()){
                l11.setText(rs.getString(1));
                l12.setText(rs.getString(2));
                l13.setText(rs.getString(3));
                l14.setText(rs.getString(4));
                l15.setText(rs.getString(5));
                l16.setText(rs.getString(6));
                l17.setText(rs.getString(7));
                
            }
        }catch(Exception e){}
        
        b1 = new JButton("Back");
        b1.setBackground(Color.BLACK);
        b1.setForeground(Color.WHITE);
        b1.setBounds(350, 340, 100, 25);
        b1.addActionListener(this);
        add(b1);
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/viewcustomer.jpg"));
        Image i2 = i1.getImage().getScaledInstance(600, 300, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel l8  = new JLabel(i3);
        l8.setBounds(20, 350, 600, 300);
        add(l8);
    }
    
    public void actionPerformed(ActionEvent ae){
        this.setVisible(false);
    }
    
    public static void main(String[] args){
        new ViewInformation("").setVisible(true);
    }
}
