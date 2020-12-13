
package Electricity;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import java.util.*;

public class MeterInfo extends JFrame implements ActionListener{
    JLabel l1,l2,l3,l4,l5,l6,l7,l8,l9, l10, l11;
    Choice c1, c2, c3,c4, c5;
    JButton b1,b2;
    MeterInfo(String meter){
        setLocation(600,200);
        setSize(700,500);
        
        JPanel p = new JPanel();
        p.setLayout(null);
        p.setBackground(new Color(173,216,230));
        
        JLabel title = new JLabel("Meter Information");
        title.setBounds(180, 10, 200, 26);
        title.setFont(new Font("Tahoma", Font.PLAIN, 24));
        p.add(title);
        
        l1 = new JLabel("Meter Number");
        l1.setBounds(100, 80, 100, 20);
        
        l11 = new JLabel(meter);
        l11.setBounds(240, 80, 200, 20);
        p.add(l1);
        p.add(l11);
        
        l2 = new JLabel("Meter Location");
        l2.setBounds(100, 120, 100, 20);
        c1 = new Choice();
        c1.add("Outside");
        c1.add("Inside");
        c1.setBounds(240, 120, 200, 20);
        p.add(l2);
        p.add(c1);
        
        l3 = new JLabel("Meter Type");
        l3.setBounds(100, 160, 100, 20);
        c2 = new Choice();
        c2.add("Electric Meter");
        c2.add("Solar Meter");
        c2.add("Smart Meter");
        c2.setBounds(240, 160, 200, 20);
        p.add(l3);
        p.add(c2);
        
        l5 = new JLabel("Phase Code");
        l5.setBounds(100, 200, 100, 20);
        c3 = new Choice();
        c3.add("011");
        c3.add("022");
        c3.add("033");
        c3.add("044");
        c3.add("055");
        c3.add("066");
        c3.add("077");
        c3.add("088");
        c3.add("099");
        c3.setBounds(240, 200, 200, 20);
        p.add(l5);
        p.add(c3);
        
        l4 = new JLabel("Bill Type");
        l4.setBounds(100, 240, 100, 20);
        c4 = new Choice();
        c4.add("Normal");
        c4.add("Industrial");
        c4.setBounds(240, 240, 200, 20);
        p.add(l4);
        p.add(c4);
        
        l6 = new JLabel("Days");
        l6.setBounds(100, 280, 100, 20);
        
        l9 = new JLabel("30 Days");
        l9.setBounds(240, 280, 200, 20);
        p.add(l6);
        p.add(l9);
        
        l7 = new JLabel("Note");
        l7.setBounds(100, 320, 100, 20);
        l10 = new JLabel("By Default Bill is calculated for 30 days only");
        l10.setBounds(240, 320, 300, 20);
        p.add(l7);
        p.add(l10);
        
        b1 = new JButton("Submit");
        b1.setBounds(120, 390, 100, 25);
        b2 = new JButton("Cancel");
        b2.setBounds(250, 390, 100, 25);
        
        b1.setBackground(Color.BLACK);
        b1.setForeground(Color.WHITE);
        
        b2.setBackground(Color.BLACK);
        b2.setForeground(Color.WHITE);
        
        p.add(b1);
        p.add(b2);
        setLayout(new BorderLayout());
        
        add(p,"Center");
        
        ImageIcon ic1 = new ImageIcon(ClassLoader.getSystemResource("icon/hicon1.jpg"));
        Image i3 = ic1.getImage().getScaledInstance(150, 300,Image.SCALE_DEFAULT);
        ImageIcon ic2 = new ImageIcon(i3);
        l8 = new JLabel(ic2);
        
        
        add(l8,"West");
        //for changing the color of the whole Frame
        getContentPane().setBackground(Color.WHITE);
        
        b1.addActionListener(this);
        b2.addActionListener(this);
    }
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == b1){
            String meter_number = l11.getText();
            String meter_location = c1.getSelectedItem();
            String meter_type = c2.getSelectedItem();
            String phase_code = c3.getSelectedItem();
            String bill_type = c4.getSelectedItem();
            String days = "30";

            String q1 = "insert into meter_info values('"+meter_number+"','"+meter_location+"','"+meter_type+"','"+phase_code+"','"+bill_type+"','"+days+"')";
            try{
                Conn c1 = new Conn();
                c1.s.executeUpdate(q1);
                JOptionPane.showMessageDialog(null,"Meter Info Added Successfully");
                this.setVisible(false);

            }catch(Exception ex){
                 ex.printStackTrace();
            }
        }else if(ae.getSource() == b2){
            this.setVisible(false);
        }
    }
    
    
    public static void main(String[] args){
        new MeterInfo("").setVisible(true);
    }
}
