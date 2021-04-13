package frontend;
import javax.swing.*;

import validation.ValidationServer;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

/**
 * UI for the login system
 * @author Group 20
 */
public class LoginUI extends JFrame {
	
	/**
	 * the login button (initiates validation)
	 */
  JButton buttonLogin;
  
  /**
   * The login panel
   */
  JPanel loginpanel;
  
  /**
   * The field for username
   */
  JTextField user;
  
  /**
   * The field for password
   */
  JTextField pass;
  
  /**
   * A label for the username field
   */
  JLabel username;
  
  /**
   * A label for the password field
   */
  JLabel password;
  
  /**
   * The validation server
   */
  ValidationServer valid;
  
  /**
   * LoginUI employs singleton
   */
  private static LoginUI instance;

  /**
   * a way to invoke the constructor without allowing the client to make more than one LoginUI
   * @return the instance of loginUI
   */
  public static LoginUI getInstance() {
	  if (instance==null) {
		  instance = new LoginUI();
	  }
	  return instance;
  }
  
  /**
   * Constructor; creates the panel and all associated elements
   */
  private LoginUI(){
    super("Login Verification");
    
    valid = ValidationServer.getInstance();
    final JPanel panel = new JPanel();
    buttonLogin = new JButton("Login");
    loginpanel = new JPanel();
    user = new JTextField(10);
    pass = new JPasswordField(10);
    username = new JLabel("Username: ");
    password = new JLabel("Password: ");

    setSize(250,175);
    setResizable(false);
    setLocation(550,300);
    loginpanel.setLayout (null); 


    user.setBounds(80,25,140,15);
    pass.setBounds(85,60,140,15);
    buttonLogin.setBounds(100,95,75,15);
    username.setBounds(20,25,75,15);
    password.setBounds(20,60,75,15);

    loginpanel.add(buttonLogin);
    loginpanel.add(user);
    loginpanel.add(pass);
    loginpanel.add(username);
    loginpanel.add(password);

    getContentPane().add(loginpanel);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setVisible(true);
    
    buttonLogin.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {
    	String username = user.getText();
    	String pw = pass.getText();
    	if(valid.validate(username, pw)) {
    		dispose();
    		JFrame frame = MainUI.getInstance();
    		frame.setSize(1920, 1080);
    		frame.pack();
    		frame.setVisible(true);
    	}
    	else {
    		JOptionPane.showMessageDialog(panel, "Invalid Credentials", "Error", JOptionPane.ERROR_MESSAGE);
    		System.exit(0);
    	}
    }
    });
  }
  

}
