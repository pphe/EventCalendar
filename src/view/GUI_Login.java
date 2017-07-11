package view;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import model.User;
import runApplication.Main;
import utility.XMLHandler;

/**
 * This is the class for the log in 
 * @author Cindy Wang
 * @editor Peter Phe
 * @version 1.0
 */
public class GUI_Login extends JFrame {

	/** Auto generated serial ID */
	private static final long serialVersionUID = 1585632631858645018L;

	/** the panel for holding all the contents */
	private JPanel contentPane;
	
	/** text field for the account id */
	private JTextField accountID;
	
	/** Text field for the password */
	private JTextField password;

	/** The icon that appears on the window title and on the About message. */
	private final ImageIcon myLogo; 
	
	/** The icon that shows the warning */
	private final ImageIcon myErrorLogo; 
	
	/** the input account id from the user */
	private String acc;
	
	/** the input password from the user */
	private String pwd;
	
	/** List of all Users */
	private List<User> myUsers;
	
	/** My current User */
	private User myUser;
	
	/** GUI to display information about the team. */
	private GUI_About myGUI_About;
	
	/** GUI to display the map with the regions to select */
	private GUI_Map myGUI_Map;
	
	/** GUI for adding a new User */
	private GUI_NewUser myGUI_NewUser;
	
	/**
	 * Constructor initializes all fields and Creates the frame.
	 */
	public GUI_Login() {
		
	    myUsers = XMLHandler.readUserXML();
	    setVisible(true);
	    
	    myGUI_About = new GUI_About(this);
	    myGUI_NewUser = new GUI_NewUser(this, myUsers);
	    myGUI_Map = new GUI_Map(this);    
	    
		// Set up the frame 
		setTitle("Peter & Sons plus One");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 675, 417);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		contentPane = new JPanel();
		contentPane.setBackground(UIManager.getColor("EditorPane.selectionBackground"));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		// This is the Easter egg
		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			    setVisible(false);
				myGUI_About.setVisible(true);
			}
		});

		// Label for Sign In
		JLabel lblNewLabel = new JLabel("Sign In");
		lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 24));
		lblNewLabel.setBounds(60, 52, 129, 29);
		contentPane.add(lblNewLabel);
		
		// Label for Account ID
		JLabel lblAccountId = new JLabel("Account ID");
		lblAccountId.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblAccountId.setBounds(146, 121, 103, 21);
		contentPane.add(lblAccountId);
		
		// The text field for the account id
		accountID = new JTextField();
		accountID.setBounds(308, 115, 263, 35);
		contentPane.add(accountID);
		accountID.setColumns(10);
		
		// label for Password
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblPassword.setBounds(146, 193, 103, 21);
		contentPane.add(lblPassword);
		
		// The text fireld for the password
		password = new JTextField();
		password.setColumns(10);
		password.setBounds(308, 187, 263, 35);
		contentPane.add(password);
		
		// Set New User button 
		JButton btnNewButton = new JButton("New User");
		btnNewButton.setBackground(Color.WHITE);
		btnNewButton.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setVisible(false);
			    myGUI_NewUser.setVisible(true);
			}
		});
		btnNewButton.setBounds(60, 293, 137, 35);
		contentPane.add(btnNewButton);
		
		
		// requires more implementation
//		JButton btnForgetPassword = new JButton("Forget Password");
//		btnForgetPassword.setBackground(Color.WHITE);
//		btnForgetPassword.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
//		btnForgetPassword.addMouseListener(new MouseAdapter() {
//			@Override
//			public void mouseClicked(MouseEvent e) {
//				setVisible(false);
//				myGUI_PwdReset.setVisible(true);
//			}
//		});
//		btnForgetPassword.setBounds(260, 293, 151, 35);
//		contentPane.add(btnForgetPassword);
		
		myLogo = new ImageIcon(Main.class.getResource("/husky 2.jpeg"));
		myErrorLogo = new ImageIcon(Main.class.getResource("/small_Cancel.jpg"));
		
		// Set the submit button
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.setBackground(Color.WHITE);
		btnSubmit.setFont(new Font("Lucida Grande", Font.BOLD, 15));
		btnSubmit.setForeground(Color.BLACK);
		btnSubmit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				// The input text from the user
				acc = accountID.getText();
				pwd = password.getText();
				int index = -1;
				
				// check whether the user exists in the file
				for(int i = 0; i < myUsers.size(); i ++) {
					if(myUsers.get(i).getAccountID().equals(acc)) {
						
						index = i; 
						myUser = myUsers.get(i);
						break; 
					}
				}
				
				if(index == -1) {
					JOptionPane.showMessageDialog(null, "Account does not exist!", "LogIn Error", JOptionPane.INFORMATION_MESSAGE, myErrorLogo);
				} else if (!myUsers.get(index).getPassword().equals(pwd)) {
					JOptionPane.showMessageDialog(null, "Wrong Password!", "LogIn Error", JOptionPane.ERROR_MESSAGE, myErrorLogo);
				} else {
					JOptionPane.showMessageDialog(null, "Welcome to Peter & Sons + One", "LogIn Success!", JOptionPane.INFORMATION_MESSAGE, myLogo);
					accountID.setText("");
					password.setText("");
					myGUI_Map.setUser(myUser);
					setVisible(false);
					myGUI_Map.setVisible(true);
				}	
			}
		});
		btnSubmit.setBounds(434, 293, 137, 35);
		contentPane.add(btnSubmit);
		this.setResizable(false);		
	}
}
