package view;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import runApplication.Main;


/**
 * The class does the password reset
 * (meant to send to user email, so it is not fully implemented yet.
 * used for future implementation) * 
 * 
 * @author Cindy Wang
 * @editor Peter Phe
 * @version 1.0
 */
public class GUI_PwdReset extends JFrame {

	/**
	 * This is the default generated serial ID.
	 */
	private static final long serialVersionUID = -7164636288514631097L;

	/**
	 * the panel that holds the contents
	 */
	private JPanel contentPane;
	
	/**
	 * the text field for entering the account id
	 */
	private JTextField textField;
	
	/**
	 * the text field for entering the email
	 */
	private JTextField textField_1;
	
	private JFrame myParent;

	/**
	 * This is the constructor for the PwdReset class. 
	 * This constructor helps set up the frame and the buttons. 
	 */
	public GUI_PwdReset(JFrame theParent) {
	    myParent = theParent;
	    
		// set up the frame
		setResizable(false);
		setTitle("Password Reset");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 675, 467);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		contentPane = new JPanel();
		contentPane.setBackground(UIManager.getColor("EditorPane.selectionBackground"));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		// Lebel for password reset
		JLabel lblPasswordReset = new JLabel("Password Reset");
		lblPasswordReset.setFont(new Font("Lucida Grande", Font.PLAIN, 24));
		lblPasswordReset.setBounds(46, 49, 216, 35);
		contentPane.add(lblPasswordReset);
		
		// Label for the Account id
		JLabel lblNewLabel = new JLabel("Account ID");
		lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblNewLabel.setBounds(114, 123, 117, 27);
		contentPane.add(lblNewLabel);
		
		// Text field for entering the account id 
		textField = new JTextField();
		textField.setBounds(277, 120, 291, 35);
		contentPane.add(textField);
		textField.setColumns(10);
		
		// Level for the email
		JLabel lblAccountName = new JLabel("Email");
		lblAccountName.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblAccountName.setBounds(114, 183, 97, 21);
		contentPane.add(lblAccountName);
		
		// Text field for entering the email
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(277, 177, 291, 35);
		contentPane.add(textField_1);
		
		// the submit button 
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		ImageIcon myLogo = new ImageIcon(Main.class.getResource("/husky 2.jpeg"));
		btnSubmit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// Check to make sure that all the required info has been filled. 
				if(textField.getText().equals("") || textField_1.getText().equals("")) {
				    ImageIcon myErrorLogo = new ImageIcon(Main.class.getResource("/small_Cancel.jpg"));
					JOptionPane.showMessageDialog(null, "Please complete all the requested information",
							"Info Incomplete", JOptionPane.ERROR_MESSAGE, myErrorLogo);
				} else {
					// if all the info has been filled, 
					// close the current window and ask for restarting the application
					JOptionPane.showMessageDialog(null, "Please restart the application",
							"Password sent!", JOptionPane.INFORMATION_MESSAGE, myLogo);
										
					//TODO need to handle password reset
					
				}
			}
		});
		btnSubmit.setBounds(392, 260, 176, 35);
		contentPane.add(btnSubmit);
		
		// The return button 
		JButton btnReturn = new JButton("Return");
		btnReturn.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		btnReturn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setVisible(false);
			    myParent.setVisible(true);
			}
		});
		
		btnReturn.setBounds(135, 260, 176, 35);
		contentPane.add(btnReturn);
		
		this.setResizable(false);

	}
}
