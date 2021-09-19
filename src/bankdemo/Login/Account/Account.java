package bankdemo.Login.Account;
import java.awt.*;
import bankdemo.Login.Account.*;
import java.awt.event.*;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import bankdemo.Login.Account.DataBase.*;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import javax.swing.*;
//import javax.swing.JTable;

class WindowSensor extends WindowAdapter{
	public void windowClosing(WindowEvent we){
        System.out.println("Window is closing");
		System.exit(0);
	}
}

class MyFrame extends JFrame{
        public JTextField amount1,tid,amount2;
        private Container C;
        public int idt,balancet;
        public String passt,fnamet,lnamet,addresst,pnumbert;
        public int UpdatedSenderBalance;
        public int UpdatedRecieverBalance;
        public int UpdatedUserBalance;
        public String NowTime=this.getDate();
        public String TypeSender="Sent";
        public String TypeReciever="Recieved";
        public String TypeUserWithdraw="Withdrawed";
        int TransID;
	//public TextField id,fName,lName,address,pNum,tBalance,transfer,withdraw;
        String getDate()
        {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
            LocalDateTime now = LocalDateTime.now();  
            return dtf.format(now);
        }
       int transfer(int SenderID,int RecieverID,int Amount)
            {
                
                int State = 0;               
                   DataAccess da=new DataAccess();
                   try{
                   ResultSet rs1=da.getData("select * from accountdb where id='"+SenderID+"'");
                   while(rs1.next())
                   {
                       int bal=rs1.getInt(7);
                       if(bal>Amount)
                       {
                            
                            TransID =  (int )(Math.random() * 50 + 1);
                             UpdatedSenderBalance=(bal-Amount);
                             System.out.println("Balance Sent");
                             State= 1;
                       }
                       else{
                             System.out.println("Not Suuficient");
                             State= 0;
                       }
                   }
                   }
                   catch(Exception ex)
                   {
                       System.out.println(ex);
                   }
                   
                   //for reciever
                   
                   if(State==1)
                   {
                       try{
                       ResultSet rs2=da.getData("select * from accountdb where id='"+RecieverID+"'");
                   while(rs2.next())
                   {
                       int bal=rs2.getInt(7);
                       UpdatedRecieverBalance=(bal+Amount);
                       System.out.println("Updated RecieverBalance");
                       
                   }
                       
                   }
                   catch(Exception ex)
                   {
                       System.out.println(ex);
                   }
                    }
                   
                   //Update Sender Balance in AccountDB
                  if(State==1)
                  {
                   try{
				String adb="update accountdb set balance='"+UpdatedSenderBalance+"' where id='"+SenderID+"'";
				da.updateDB(adb);
                                System.out.println("q3");
                            }	
                    catch (Exception e){
			System.out.println(e);
			}
                  }
                   
                   //Update Reciever Balance in AccountDB
                   if(State==1)
                   {
                    try{
				String adb="update accountdb set balance='"+UpdatedRecieverBalance+"' where id='"+RecieverID+"'";
				da.updateDB(adb);
                                System.out.println("q4");
                            }	
                    catch (Exception e){
			System.out.println(e);
			}
                   }
                   //Updated Transaction for sender
                   if(State==1)
                   {
                   try{
				String adb="insert into transaction (id,trid,tamount,tdate,ttype) values ('"+SenderID+"','"+TransID+"','"+Amount+"','"+NowTime+"','"+TypeSender+"')";
				da.updateDB(adb);
                                System.out.println("q5");
                            }	
                    catch (Exception e){
			System.out.println(e);
			}
                   }
                   
                   //Updated Transaction for Reciever
                   if(State==1)
                   {
                       try{
				String adb="insert into transaction (id,trid,tamount,tdate,ttype) values ('"+RecieverID+"','"+TransID+"','"+Amount+"','"+NowTime+"','"+TypeReciever+"')";
				da.updateDB(adb);
                                System.out.println("q6");
                            }	
                    catch (Exception e){
			System.out.println(e);
			}
                   }
                   
                   return State;
            }
       int withdraw(int ID,int Amount)
       {
            int State=0;
           DataAccess da=new DataAccess();
                   try{
                   ResultSet rs3=da.getData("select * from accountdb where id='"+ID+"'");
                   while(rs3.next())
                   {
                       int bal=rs3.getInt(7);
                       if(bal>Amount)
                       {
                            
                            TransID =  (int )(Math.random() * 50 + 1);
                             UpdatedUserBalance=(bal-Amount);
                             System.out.println("Balance Withdrawed");
                             State= 1;
                       }
                       else{
                             System.out.println("Not Suuficient");
                             State= 0;
                       }
                   }
                   }
                   catch(Exception ex)
                   {
                       System.out.println(ex);
                   }
                   //updated User Balance
                  
                   if(State==1)
                   {
                    try{
				String adb="update accountdb set balance='"+UpdatedUserBalance+"' where id='"+ID+"'";
				da.updateDB(adb);
                                System.out.println("q7");
                            }	
                    catch (Exception e){
			System.out.println(e);
			}
                   }
                   //transaction for user
                   if(State==1)
                   {
                       try{
				String adb="insert into transaction (id,trid,tamount,tdate,ttype) values ('"+ID+"','"+TransID+"','"+Amount+"','"+NowTime+"','"+TypeUserWithdraw+"')";
				da.updateDB(adb);
                                System.out.println("q8");
                            }	
                    catch (Exception e){
			System.out.println(e);
			}
                   }
                   return State;
       }
       
	public MyFrame(int idt,String passt,String fnamet,String lnamet,String addresst,String pnumbert,int balancet){
        this.idt=idt;
        this.passt=passt;
        this.fnamet=fnamet;
        this.lnamet=lnamet;
        this.addresst=addresst;
        this.pnumbert=pnumbert;
        this.balancet=balancet;
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBounds(100,50,750,850);
        this.setTitle("Account");
	C=this.getContentPane();
        C.setLayout(null);
        C.setBackground(Color.orange);
        JLabel idLabel=new JLabel("ID : "+idt);
        //JTextField idLabel2=new JTextField();
        //idLabel2.setText(idt);
        JLabel fNameLabel=new JLabel("First Name : "+fnamet);
        //JTextField fNameLabel2=new JTextField();
        //fNameLabel2.setText(fnamet);
	JLabel lNameLabel=new JLabel("Last Name : "+lnamet);
        //JTextField lNameLabel2=new JTextField();
        //lNameLabel2.setText(lnamet);
	JLabel addressLabel=new JLabel("Address : "+addresst);
        //JTextField addressLabel2=new JTextField();
        //addressLabel2.setText(addresst);
	JLabel pNumLabel=new JLabel("Phone Number : "+pnumbert);
        //JTextField pNumLabel2=new JTextField();
        //pNumLabel2.setText(pnumbert);
        JLabel tBalanceLabel=new JLabel("Total Balance : "+balancet);
        //JTextField tBalanceField=new JTextField("");
	JLabel transferLabel=new JLabel("Transfer money : ");
	JLabel withdeawLabel=new JLabel("Withdraw money : ");
		
	JButton transferButton=new JButton("Transfer");JButton withdrawButton=new JButton("Withdraw");
        JButton TransOfID=new JButton("All Transaction");
               
	JTextField amount1 =new JTextField("amount");JTextField tid = new JTextField("transferid");JTextField amount2 = new JTextField("amount2");
        
        idLabel.setBounds(100,100,200,25);//id.setBounds(180,100,120,25);
	fNameLabel.setBounds(100,150,200,25);//pass.setBounds(180,150,120,25);
        lNameLabel.setBounds(100,200,200,25);
        addressLabel.setBounds(100,250,200,25);
        pNumLabel.setBounds(100,300,200,25);
        tBalanceLabel.setBounds(100,350,200,25);
        
        //idLabel2.setBounds(190,100,200,25);//id.setBounds(180,100,120,25);
	//fNameLabel2.setBounds(190,150,200,25);//pass.setBounds(180,150,120,25);
        //lNameLabel2.setBounds(190,200,200,25);
        //addressLabel2.setBounds(190,250,200,25);
        //pNumLabel2.setBounds(190,300,200,25);
        //tBalanceField.setBounds(190,350,200,25);
			C.add(idLabel);//C.add();
			C.add(fNameLabel);
			C.add(lNameLabel);
			C.add(addressLabel);
			C.add(pNumLabel);
			C.add(tBalanceLabel);
          //              C.add(idLabel2);
	//		C.add(fNameLabel2);
	//		C.add(lNameLabel2);
	//		C.add(addressLabel2);
	//		C.add(pNumLabel2);
          //              C.add(tBalanceField);
                        
		
            transferLabel.setBounds(100,400,150,50);
            tid.setBounds(300,450,150,25);
            amount1.setBounds(100,450,150,25);
            transferButton.setBounds(175,500,200,25);
				
            C.add(transferLabel);
            C.add(tid);
            C.add(amount1);
            C.add(transferButton);
            C.add(TransOfID);
                
            withdeawLabel.setBounds(100,550,150,25);
			amount2.setBounds(100,600,150,25);
            withdrawButton.setBounds(300,600,200,25);
              TransOfID.setBounds(175,650,200,25);
              
            C.add(amount2);
            C.add(withdrawButton);
          
            transferButton.addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent ae)
                    {
                        String amount1Text=amount1.getText();
                        String tidText=tid.getText();
                        String amount2Text=amount2.getText();
                        int CurrentState=0;
                         boolean flag=true;
                        if((amount1Text.length()==0 || tidText.length()==0)|| (amount1Text.equals("amount")||tidText.equals("transferid")))
                        {
                            flag=false;
                            JOptionPane.showMessageDialog(null,"Why empty");
                            
                        }
                        else if(flag && (amount1Text.length()!=0 && tidText.length()!=0))
                        {
                            CurrentState=transfer(idt,Integer.parseInt(tidText),Integer.parseInt(amount1Text));
                            if(CurrentState==1)
                            {
                                JOptionPane.showMessageDialog(null,"Sent "+amount1Text);
                            }
                            else{
                                JOptionPane.showMessageDialog(null,"Insufficient Balance");
                            }

                        }
                        else{
                            System.out.println("Do nothing");
                     }
            
            
            
            }
            });
            
            withdrawButton.addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent ae)
                    {
                        String amount2Text=amount2.getText();
                        int CurrentState=0;
                        boolean flag=true;
                        if(amount2Text.equals("amount2")||amount2Text.length()==0)
                        {
                            JOptionPane.showMessageDialog(null,"Put amount Man");
                            flag=false;
                        }
                        else if(flag && (amount2Text.length()!=0))
                        {
                            CurrentState=withdraw(idt,Integer.parseInt(amount2Text));
                            if(CurrentState==1)
                            {
                                JOptionPane.showMessageDialog(null,"Withdrawed "+amount2Text);
                            }
                            else{
                                JOptionPane.showMessageDialog(null,"Insufficient Balance");
                            }
                        }
                        else{
                            System.out.println("Do nothing");
                        }
   
            }
            });
	
            
            TransOfID.addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent ae)
                    {
                        TransactionInfo TIF=new TransactionInfo(idt);
                           
            }
            });
}
}

/*class ButtonSensor implements ActionListener{
	MyFrame mf;
	public ButtonSensor(MyFrame f){
		mf=f;
	}
	public void actionPerformed(ActionEvent ae){
		
	}
}*/




public class Account{
    //public int idt;
    //public String passt,fnamet,lnamet,addresst,pnumbert;

    public Account(int idt,String passt,String fnamet,String lnamet,String addresst,String pnumbert,int balancet){
        /*this.idt=idt;
        this.passt=passt;
        this.fnamet=fnamet;
        this.lnamet=lnamet;
        this.addresst=addresst;
        this.pnumbert=pnumbert;*/
        
        MyFrame mf=new MyFrame(idt,passt,fnamet,lnamet,addresst,pnumbert,balancet);
        mf.addWindowListener(new WindowSensor());
        mf.setVisible(true);   
       }
}


