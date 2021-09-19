package bankdemo.Login;
import bankdemo.Login.Account.*;
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
//import SignUp.*;

class WindowSensor extends WindowAdapter{
	public void windowClosing(WindowEvent we){
        System.out.println("Window is closing");
		System.exit(0);
	}
}
class MyFrame extends JFrame{
	public JTextField id;
        public JPasswordField pass;
	public JLabel message;
        private Container C;
	public MyFrame(){
                this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                this.setBounds(100,50,450,500);
                this.setTitle("SignUP");
		C=this.getContentPane();
                C.setLayout(null);
                C.setBackground(Color.orange);
		JLabel idLabel=new JLabel("ID");
		JLabel passLabel=new JLabel("Password");
		//Label signupLabel=new Label("If you don't have any account then : ");
		
		JButton loginButton=new JButton("Login");
                JButton cancelButton=new JButton("Cancel");
		//Button signupButton=new Button("Sign Up");
		
		id=new JTextField();
		pass=new JPasswordField();
                pass.setEchoChar('*');
                
                idLabel.setBounds(100,100,70,25);id.setBounds(180,100,120,25);
		passLabel.setBounds(100,150,70,25);pass.setBounds(180,150,120,25);

		C.add(idLabel);add(id);
		C.add(passLabel);add(pass);
		
                loginButton.setBounds(170,200,80,25);
		cancelButton.setBounds(250,200,80,25);
                
                C.add(loginButton);
		C.add(cancelButton);
		//add(signupLabel);
		//add(signupButton);
		//signupButton.setBounds(330,200,50,25);
                
		ButtonSensor bs=new ButtonSensor(this);
		//signupButton.addActionListener(bs);
		loginButton.addActionListener(bs);
		cancelButton.addActionListener(bs);
                JButton Admin=new JButton("Admin");
                Admin.setBounds(310,200,80,25);
                C.add(Admin);
                Admin.addActionListener(bs);
                
		
	}
}

class ButtonSensor implements ActionListener{
        int idt,balancet;
        String passt,fnamet,lnamet,addresst,pnumbert;
	MyFrame mf;
	//Utility u;
	public ButtonSensor(MyFrame f){
		mf=f;
		//u=new Utility();
	}
	public void actionPerformed(ActionEvent ae){
		boolean flag=true;
		String i=mf.id.getText();
		String p=mf.pass.getText();
		
		String s3=ae.getActionCommand();
                
                /*if(s3.equals("Sign Up")){
			System.out.println("Open SignUp");
                        mf.setVisible(false);
			SignUp su=new SignUp();
		}*/
                if("Admin".equals(s3))
                {
                    mf.setVisible(false);
                    AllInfo info=new AllInfo();
                }
                if("Cancel".equals(s3)){
                    System.out.println("Window is closing");
                    System.exit(0);
		}
		if(i.length()==0||p.length()==0){
                    JOptionPane.showMessageDialog(mf,"You must fill up the form.");
                    System.out.println("You must fill up the form.");
                    flag=false;
		}else{
                    if(flag && s3.equals("Login")){
                        System.out.println("Login Working");
                        
                        System.out.println(mf.id.getText());
                        System.out.println(mf.pass.getText());
						
						
						int ide = Integer.parseInt(i);
						
						DataAccess da=new DataAccess();
						try{
							System.out.println("Working Try");
							String ab="select * from accountdb where id='"+ide+"'";
							ResultSet rs=da.getData(ab);
							while(rs.next()){
							System.out.println(rs.getInt(1)+"\t"+rs.getString(3)+"\t"+rs.getString(4)+"\t"+rs.getString(5)+"\t"+rs.getString(6));
                                                                idt=rs.getInt(1);
                                                                passt=rs.getString(2);
                                                                fnamet=rs.getString(3);
                                                                lnamet=rs.getString(4);
                                                                addresst=rs.getString(5);
                                                                pnumbert=rs.getString(6);
                                                                balancet=rs.getInt(7);
                                                                
                                                                    }
                                                        if(p.equals(passt))
                                                                {
                                                                       mf.setVisible(false);
                                                                       Account A=new Account(idt,passt,fnamet,lnamet,addresst,pnumbert,balancet);
                                                                }
                                                        else{
                                                            System.out.println("Pass prob");
                                                        }
                                                }
                                                                    catch(Exception ex){
                                                                        System.out.println(ex);
                                                                           }
                                                              }	
								
							else{
								System.out.println("if is not working");
							}
							}
						}
}



public class Login{
	public Login(){
            MyFrame mf=new MyFrame();
            mf.addWindowListener(new WindowSensor());
            mf.setVisible(true);
	}
}