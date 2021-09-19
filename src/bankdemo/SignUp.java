import java.awt.*;
import java.awt.event.*;
import javax.swing.JOptionPane;
import javax.swing.JFrame;
import bankdemo.Login.*;
import bankdemo.Login.Account.*;
import bankdemo.Login.Account.DataBase.*;
import java.sql.ResultSet;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

class MyFrame extends JFrame{
	public String msg;
	public JTextField fName,lName,address,pNum;
        public JPasswordField pass,cpass;
        private Container C;
        
	public MyFrame(){
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                this.setBounds(100,50,450,500);
                this.setTitle("SignUP");
		C=this.getContentPane();
                C.setLayout(null);
                C.setBackground(Color.orange);
		JLabel fNameLabel=new JLabel("First Name");
		JLabel lNameLabel=new JLabel("Last Name");
		JLabel addLabel=new JLabel("Address");
		JLabel pNumLabel=new JLabel("Phone Number");
		JLabel passLabel=new JLabel("Password");
		JLabel cpassLabel=new JLabel("Confirm Password");
		
		JButton signupButton=new JButton("Sign Up");JButton loginButton=new JButton("Login");
		
		fName=new JTextField(30);lName=new JTextField(30);address=new JTextField(30);pNum=new JTextField(30);pass=new JPasswordField(30);cpass=new JPasswordField(30);
		
                fNameLabel.setBounds(190,50,70,10);
		fName.setBounds(125,70,200,25);
		lNameLabel.setBounds(190,100,70,10);
		lName.setBounds(125,120,200,25);
		addLabel.setBounds(200,150,70,10);
		address.setBounds(125,170,200,25);
		pNumLabel.setBounds(180,200,100,10);
		pNum.setBounds(125,220,200,25);
		passLabel.setBounds(195,250,70,10);
		pass.setBounds(125,270,200,25);
                pass.setEchoChar('*');
                cpass.setEchoChar('*');
		cpassLabel.setBounds(170,300,120,10);
		cpass.setBounds(125,320,200,25);
                
		C.add(fNameLabel);C.add(fName);
		C.add(lNameLabel);C.add(lName);
		C.add(addLabel);C.add(address);
		C.add(pNumLabel);C.add(pNum);
		C.add(passLabel);C.add(pass);
		C.add(cpassLabel);C.add(cpass);
		
                signupButton.setBounds(180,350,80,20);
		loginButton.setBounds(180,400,80,20);
                
		C.add(signupButton);
		C.add(loginButton);
		
		ButtonSensor bs=new ButtonSensor(this);
		signupButton.addActionListener(bs);
		loginButton.addActionListener(bs);
		
	}


}

class ButtonSensor implements ActionListener{
	MyFrame mf;
	//Utility u;
	public ButtonSensor(MyFrame f){
		mf=f;
		//u=new Utility();
	}
	public void actionPerformed(ActionEvent ae){
		boolean flag=true;
		String fn=mf.fName.getText();
		String ln=mf.lName.getText();
		String a=mf.address.getText();
		String pn=mf.pNum.getText();
		String p=mf.pass.getText();
		String cp=mf.cpass.getText();
		
		String s2=ae.getActionCommand();
		if(s2.equals("Login")){
			System.out.println("Open Login");
                        mf.setVisible(false);
			Login log=new Login();
		}
		else{
			if(fn.length()==0||ln.length()==0||a.length()==0||pn.length()==0||p.length()==0||cp.length()==0){
				JOptionPane.showMessageDialog(mf,"You must fill up the form.");
				System.out.println("You must fill up the form.");
				flag=false;
			}else if(!(p.equals(cp))){
				JOptionPane.showMessageDialog(mf,"Password didn't match.");
				System.out.println("Password didn't match.");
				System.out.println(mf.cpass.getText());
			}else{
				if(flag && s2.equals("Sign Up")){
					System.out.println("Working");
					DataAccess da=new DataAccess();
					try{
						String adb="insert into AccountDB (Password,FName,LName,Address,PNumber,balance) values ('"+p+"','"+fn+"','"+ln+"','"+a+"','"+pn+"','100')";
						da.updateDB(adb);
					}	
					catch (Exception e){
						System.out.println(e);
					}
					finally {
						System.out.println("Update completed\n");
                                              JOptionPane.showMessageDialog(mf,"CONGATULATIONS YOUR ACCOUNT HAS BEEN CREATED!!"); 
					}
					try{
                                            String query="SELECT * FROM AccountDB";
                                            ResultSet rs=da.getData(query);
                                            while(rs.next()){
						System.out.println(rs.getInt(1)+"\t"+rs.getString(3)+"\t"+rs.getString(4)+"\t"+rs.getString(5)+"\t"+rs.getString(6));
                                            }
					}
					catch(Exception ex){
						ex.printStackTrace();
					}
				}
			}
		}
		System.out.println("button pressed");
	}
}

public class SignUp{
    public static void main(String str[]){
        MyFrame mf=new MyFrame();
        mf.setVisible(true);
        //DBCheck dbc1=new DBCheck();
    }
}