/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bankdemo.Login.Account;
import java.awt.*;
import java.awt.event.*;
import javax.swing.JOptionPane;
import bankdemo.Login.Account.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.sql.ResultSet;
import bankdemo.Login.Account.DataBase.*;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 *
 * @author MD SHIHAB UDDIN
 */
class MyTable extends JFrame{
    private Container C;
    private Font f;
    private JTable table;
    private String[] cols={"id","pass","fname","lname","address","pnumber"};
    private String rows[][]=new String[100][6];
    private JScrollPane scroll;
    public MyTable()
    {
        components();
    }
    public void components()
    {
        DataAccess da=new DataAccess();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBounds(100,100,750,450);
        this.setTitle("All Accounts");
        C=this.getContentPane();
        C.setLayout(null);
        C.setBackground(Color.red);
        f=new Font("Arial",Font.BOLD,22);
        JLabel InLabel=new JLabel("All Information");
        InLabel.setBounds(250,20,200,50);
        InLabel.setFont(f);
        C.add(InLabel);
        try{
            String ab="select * from accountdb";
            ResultSet rs=da.getData(ab);
            for(int i=0;rs.next();i++)
            {
                rows[i][0]=Integer.toString(rs.getInt(1));
                rows[i][1]=rs.getString(2);
                rows[i][2]=rs.getString(3);
                rows[i][3]=rs.getString(4);
                rows[i][4]=rs.getString(5);
                rows[i][5]=rs.getString(6);
            }
        }
            catch(Exception ex)
                {
                   System.out.println(ex);
                }
        table=new JTable(rows,cols);
        scroll=new JScrollPane(table);
        scroll.setBounds(50,80,600,150);
        C.add(scroll);
    
}
}
public class AllInfo{
    
    public AllInfo()
    {
        MyTable mt=new MyTable();
        mt.setVisible(true);
        
                
    }
}
