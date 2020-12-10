

package Electricity;


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import net.proteanit.sql.DbUtils;

public class DepositDetails extends JFrame implements ActionListener{
 
    JTable t1;
    JButton b1, b2;
    JLabel l1, l2;
    Choice c1, c2;
    String x[] = {"Meter No","Month","Units","Total Bill", "Status"};
    String y[][] = new String[40][8];
    int i=0, j=0;
    DepositDetails(){
        setSize(700,750);
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        setLocation(600,150);
        
        l1 = new JLabel("Sort By Meter Number");
        l1.setBounds(20, 20, 150, 20);
        add(l1);
        
        c1 = new Choice();
        
        l2 = new JLabel("Sort By Month");
        l2.setBounds(400, 20, 100, 20);
        add(l2);
        
        c2 = new Choice();
        t1 = new JTable(y,x);
        
        try{
            Conn c  = new Conn();
            String s1 = "select * from bill";
            ResultSet rs  = c.s.executeQuery(s1);
//            while(rs.next()){
//                y[i][j++]=rs.getString("meter_no");
//                y[i][j++]=rs.getString("month");
//                y[i][j++]=rs.getString("units");
//                y[i][j++]=rs.getString("total_bill");
//                y[i][j++]=rs.getString("status");
//                i++;
//                j=0;
//            }
//            t1 = new JTable(y,x);

            t1.setModel(DbUtils.resultSetToTableModel(rs));
            
            String s2 = "select * from customer";
            rs  = c.s.executeQuery(s2);
            while(rs.next()){
                c1.add(rs.getString("meter_no"));
            }
            
            
        }catch(Exception e){
            e.printStackTrace();
        }
        
        c1.setBounds(180, 20, 120, 20);
        add(c1);
        
        c2.setBounds(520, 20, 120, 20);
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
        add(c2);
        
        
        b1 = new JButton("Search");
        b1.addActionListener(this);
        b1.setBounds(20, 70, 80, 20);
        add(b1);
        
        b2 = new JButton("Print");
        b2.setBounds(120, 70, 80, 20);
        b2.addActionListener(this);
        add(b2);
        
        JScrollPane sp = new JScrollPane(t1);
        sp.setBounds(0, 100, 700, 650);
        add(sp);
        
        
        setVisible(true);
        
    }
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == b1){
            String SQL = "select * from bill where meter_no = '"+c1.getSelectedItem()+"' AND month = '"+c2.getSelectedItem()+"'";
            try{			
                    Conn c = new Conn();
                    ResultSet rs = c.s.executeQuery(SQL);
                        t1.setModel(DbUtils.resultSetToTableModel(rs));
            }catch (Exception ss){
                ss.printStackTrace();
            }
        }else if(ae.getSource() == b2){
            try{
                t1.print();
            }catch(Exception e){}
        }
    }
    
    public static void main(String[] args){
        new DepositDetails().setVisible(true);
    }
    
}
