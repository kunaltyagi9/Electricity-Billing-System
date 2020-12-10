///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
package Electricity;


import java.awt.*;
import java.awt.event.*;
import java.awt.print.PrinterException;
import javax.swing.*;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumnModel;
import javax.swing.table.*;


public class Practice extends JFrame implements ActionListener,MouseListener
{
  JFrame f;
  JLabel l1,l2,l3;
  JTable t1;
  JButton b1,b2,b3,b4;
  JTextField t2;
 
  String x[] = {"Event Name","Gender","First Player","Second Player","Third Player","ID","Phone no","Amount"};
  String y[][] = new String[50][16];
  int i=0, j=0;
 
  Practice ()
  {
        f=new JFrame("Player Details");
        setSize(1680,800);
        setLocation(120,120);
        setLayout(null);
       
        l1 = new JLabel("Enter roll number to delete Team Record : ");
        l1.setBounds(50,450,530,30);
        l1.setFont(new Font("serif",Font.BOLD,25));
        add(l1);
       
        t2 = new JTextField();
        t2.setBounds(510,455,200,30);
        t2.setFont(new Font("Tahoma",Font.PLAIN,20));
        add(t2);
       
        b1 = new JButton("Delete");
        b1.setBackground(Color.BLACK);
        b1.setForeground(Color.WHITE);
        b1.setBounds(740,455,150,30);
        b1.setFont(new Font("Tahoma",Font.BOLD,15));
        add(b1);
           
        l2 = new JLabel("Add New Player");
        l2.setBounds(50,530,400,30);
        l2.setFont(new Font("serif",Font.BOLD,23));
        add(l2);
       
        b2 = new JButton("Add Player");
        b2.setBackground(Color.BLACK);
        b2.setForeground(Color.WHITE);
        b2.setBounds(340, 530, 150 ,30);
        b2.setFont(new Font("Tahoma",Font.BOLD,15));
        add(b2);
   
        l3 = new JLabel("Update Solo Player Details");
        l3.setBounds(50,580,400,30);
        l3.setFont(new Font("serif",Font.BOLD,23));
        add(l3);
       
        b3 = new JButton("Update Player");
        b3.setBackground(Color.BLACK);
        b3.setForeground(Color.WHITE);
        b3.setBounds(340, 580, 150 ,30);
        b3.setFont(new Font("Tahoma",Font.BOLD,15));
        add(b3);
       
        b4 = new JButton("Print");
        b4.setBackground(Color.BLACK);
        b4.setForeground(Color.WHITE);  
        b4.setBounds(1500,485,150,30);
        b4.setFont(new Font("Tahoma",Font.BOLD,15));
        add(b4);
       
        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);
        b4.addActionListener(this);
        
        DefaultTableModel dm = new DefaultTableModel();
        
         try{
            Conn c1  = new Conn();
            String q = "select * from team";
            ResultSet rs  = c1.s.executeQuery(q);
            while(rs.next()){
               
                y[i][j++]=rs.getString("event");
                y[i][j++]=rs.getString("gender");
                y[i][j++]=rs.getString("firstplayer");
                y[i][j++]=rs.getString("secondplayer");
                y[i][j++]=rs.getString("thirdplayer");
                i++; j = 2;
                y[i][j++]=rs.getString("fourthplayer");
                y[i][j++]=rs.getString("fifthplayer");
                y[i][j++]=rs.getString("sixthplayer");
                i++; j = 2;
                y[i][j++]=rs.getString("seventhplayer");
                y[i][j++]=rs.getString("firstsubstitute");
                y[i][j++]=rs.getString("secondsubstitute");
                i++; j = 2;
                y[i][j++]=rs.getString("thirdsubstitute");
                i = i - 3; j = 5;
                y[i][j++]=rs.getString("teamid");
                y[i][j++]=rs.getString("contactno");
                y[i][j++]=rs.getString("amount");
                y[i][j++]=rs.getString("status");
                System.out.println(i);
                i = i + 4; 
                j=0;
                
                // 0 * 1 + 4 = 4
                // 
            }
               
            t1 = new JTable(y,x){
   @Override
   public boolean isCellEditable(int rowIndex, int colIndex)
   {
     return false;//Disallow the editing of any cell
   }
  };
            t1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
            t1.setRowHeight(25);
            t1.setFont(new Font("times new roman",Font.BOLD,16));
            t1.getTableHeader().setFont(new Font("DialogInput",Font.BOLD,16));
            t1.getTableHeader().setReorderingAllowed(false);
            t1.getTableHeader().setResizingAllowed(false);
            t1.addMouseListener(this);
           
            TableColumnModel cm1=t1.getColumnModel();
            cm1.getColumn(0).setPreferredWidth(150);
            cm1.getColumn(1).setPreferredWidth(140);
            cm1.getColumn(2).setPreferredWidth(190);
            cm1.getColumn(3).setPreferredWidth(190);
            cm1.getColumn(4).setPreferredWidth(190);
            cm1.getColumn(5).setPreferredWidth(190);
            cm1.getColumn(6).setPreferredWidth(190);
            cm1.getColumn(7).setPreferredWidth(190);
            cm1.getColumn(8).setPreferredWidth(190);
            cm1.getColumn(9).setPreferredWidth(190);
            cm1.getColumn(10).setPreferredWidth(190);
            cm1.getColumn(11).setPreferredWidth(190);
            cm1.getColumn(12).setPreferredWidth(100);
            cm1.getColumn(13).setPreferredWidth(120);
            cm1.getColumn(14).setPreferredWidth(100);
            cm1.getColumn(15).setPreferredWidth(150);
           
            DefaultTableCellRenderer rndr=new DefaultTableCellRenderer();
            rndr.setHorizontalAlignment(SwingConstants.CENTER);
            t1.getColumnModel().getColumn(0).setCellRenderer(rndr);
            t1.getColumnModel().getColumn(1).setCellRenderer(rndr);
           
            t1.getColumnModel().getColumn(12).setCellRenderer(rndr);
            t1.getColumnModel().getColumn(13).setCellRenderer(rndr);
            t1.getColumnModel().getColumn(14).setCellRenderer(rndr);
            t1.getColumnModel().getColumn(15).setCellRenderer(rndr);
       
           
           
        }catch(Exception e){
            e.printStackTrace();
        }
       
        JScrollPane sp = new JScrollPane(t1);
       
        sp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        sp.setBounds(20,20,1600,400);
        add(sp);
       
       
        getContentPane().setBackground(Color.WHITE);
       
        b1.addActionListener(this);
   
    }
  public void actionPerformed(ActionEvent ae)
{
       
        Conn c1 = new Conn();
   
        if(ae.getSource() == b1)
        {
            try{
                String a = t2.getText();
                String q = "delete from team where teamid = '"+a+"'";
                c1.s.executeUpdate(q);
               
                this.setVisible(false);
                //new View_Team_Records().setVisible(true);
               
            }catch(Exception e){}
   
        }else if(ae.getSource() == b2)
        {
            //new Team_Reg_Form().f.setVisible(true);
            this.setVisible(true);
           
        }else if(ae.getSource() == b3)
        {
            //new Update_Team_Details().f.setVisible(true);
            this.setVisible(true);
        }else if(ae.getSource() == b4)
        {
            try {
                t1.print();
            } catch (PrinterException ex) {
                //Logger.getLogger(View_Solo_Records.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
}
  public static void main(String[] args)
      {
        new Practice().setVisible(true);
      }

    @Override
    public void mouseClicked(MouseEvent e)
    {
           int row=t1.getSelectedRow();
           t2.setText(t1.getModel().getValueAt(row,12).toString());
    }

    @Override
    public void mousePressed(MouseEvent e) {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseExited(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   
}
