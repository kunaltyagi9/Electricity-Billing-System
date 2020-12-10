/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Electricity;



import java.awt.BorderLayout;
import java.awt.*;
import java.awt.EventQueue;

import javax.swing.border.EmptyBorder;

import java.awt.Font;
import java.awt.Image;
import java.sql.*;	
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
public class UpdateInfo extends JFrame {
	private JPanel contentPane;
	private JTextField t1,t2,t3,t4,t5,t6,t7,t8,t9,t10;
	
        public static void main(String[] args) {
		new UpdateInfo("").setVisible(true);
	}

	public UpdateInfo(String meter){
		setBounds(500, 220, 1050, 450);
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);
                
                ImageIcon i1  = new ImageIcon(ClassLoader.getSystemResource("icon/update.jpg"));
                Image i3 = i1.getImage().getScaledInstance(400, 300,Image.SCALE_DEFAULT);
                ImageIcon i2 = new ImageIcon(i3);
                JLabel l1 = new JLabel(i2);
                l1.setBounds(550,50,400,300);
                add(l1);
		
		JLabel lblName = new JLabel("UPDATE CUSTOMER DETAILS");
		lblName.setFont(new Font("Yu Mincho", Font.PLAIN, 20));
		lblName.setBounds(118, 0, 300, 53);
		contentPane.add(lblName);
                
                JLabel l3 = new JLabel("Name :");
		l3.setBounds(35, 70, 200, 14);
		contentPane.add(l3);
                
                JLabel l4 = new JLabel();
		l4.setBounds(271, 70, 200, 14);
		contentPane.add(l4);
                
                JLabel lblId = new JLabel("Meter Number :");
		lblId.setBounds(35, 110, 200, 14);
		contentPane.add(lblId);
                
                JLabel l5 = new JLabel();
		l5.setBounds(271, 110, 200, 14);
		contentPane.add(l5);
                
                JLabel l2 = new JLabel("Address :");
		l2.setBounds(35, 150, 200, 14);
		contentPane.add(l2);
                
                t3 = new JTextField();
		t3.setBounds(271, 150, 150, 20);
		contentPane.add(t3);
		t3.setColumns(10);
		
		JLabel lblName_1 = new JLabel("City :");
		lblName_1.setBounds(35, 190, 200, 14);
		contentPane.add(lblName_1);
		
		t4 = new JTextField();
		t4.setBounds(271, 190, 150, 20);
		contentPane.add(t4);
		t4.setColumns(10);

                
		JLabel lblGender = new JLabel("State :");
		lblGender.setBounds(35, 230, 200, 14);
		contentPane.add(lblGender);
                
                t5 = new JTextField();
		t5.setBounds(271, 230, 150, 20);
		contentPane.add(t5);
		t5.setColumns(10);
                
		JLabel lblCountry = new JLabel("Email :");
		lblCountry.setBounds(35, 270, 200, 14);
		contentPane.add(lblCountry);
                
                t6 = new JTextField();
		t6.setBounds(271, 270, 150, 20);
		contentPane.add(t6);
		t6.setColumns(10);
		
		JLabel lblReserveRoomNumber = new JLabel("Phone :");
		lblReserveRoomNumber.setBounds(35, 310, 200, 14);
		contentPane.add(lblReserveRoomNumber);
                
                t7 = new JTextField();
		t7.setBounds(271, 310, 150, 20);
		contentPane.add(t7);
		t7.setColumns(10);
           	
                try{
                    Conn c = new Conn();
                    ResultSet rs = c.s.executeQuery("select * from customer where meter_no = '"+meter+"'");
                    if(rs.next()){
                        l4.setText(rs.getString(1));  
                        l5.setText(rs.getString(2));
                        t3.setText(rs.getString(3));  
                        t4.setText(rs.getString(4));
                        t5.setText(rs.getString(5));  
                        t6.setText(rs.getString(6));
                        t7.setText(rs.getString(7));
                    }
                }catch(Exception e){ }
		
		

		JButton btnNewButton = new JButton("Update");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                            Conn c = new Conn();
                           
                          
                            try{
	    			String s1 = l4.getText();
                                String s2 = l5.getText(); 
	    			String s3 =  t3.getText();
	    			String s4 =  t4.getText();
                                String s5 =  t5.getText();
	    			String s6 =  t6.getText();
	    			String s7 =  t7.getText();
                                
                                String q1 = "update customer set name = '"+s1+"', address = '"+s3+"', city = '"+s4+"', state = '"+s5+"', email = '"+s6+"', phone = '"+s7+"' where meter_no = '"+s2+"'";
                                c.s.executeUpdate(q1);
                                
	    			JOptionPane.showMessageDialog(null, "Details Updated Successfully");
                                setVisible(false);
	    		}catch(Exception e1){
	    			System.out.println(e1.getMessage());
	    		}
			}
		});
		btnNewButton.setBounds(100, 360, 120, 30);
                btnNewButton.setBackground(Color.BLACK);
                btnNewButton.setForeground(Color.WHITE);
		contentPane.add(btnNewButton);
		
		JButton btnExit = new JButton("Back");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                            setVisible(false);
			}
		}); 
		btnExit.setBounds(260, 360, 120, 30);
                btnExit.setBackground(Color.BLACK);
                btnExit.setForeground(Color.WHITE);
		contentPane.add(btnExit);
                
                getContentPane().setBackground(Color.WHITE);
	}
}
