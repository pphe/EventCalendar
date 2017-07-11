package view;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ListCellRenderer;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import model.User;
import runApplication.Main;
import utility.XMLHandler;

/**
 * This user class helps creates a new user profile
 * @author Cindy Wang
 * @editor Peter Phe
 * @version 1.0
 */
public class GUI_NewUser extends JFrame {

	/**
	 * This is a auto generated default serial ID
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * jpanel that holds all the content
	 */
	private JPanel contentPane;	
	
	/**
	 * input account id
	 */
	private JTextField accID;	
	
	/** 
	 * input password
	 */
	private JTextField pswd;
	
	/**
	 * input organization/company
	 */
	private JTextField orga;	
	
	/**
	 * input email
	 */
	private JTextField email;
	
	/**
	 * input phone number
	 */
	private JTextField phone;
	
	/**
	 * Role selection
	 */
	@SuppressWarnings("rawtypes")
	private JComboBox role;
	
	private JFrame myParent;
	
	/**
	 * The icon that appears on the window title and on the About message.
	 */
	private final ImageIcon myLogo; 
	
	/**
	 * List of all saved User.
	 */
	List<User> myUsers;

	/**
	 * This is the constructor of this User class. 
	 * This constructor helps set up the frame and the buttons
	 * and saves User to xml database. 
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public GUI_NewUser(JFrame theFrame, List<User> theUsers) {
	    
	    myParent = theFrame;
	    myUsers = theUsers;	
	    
		// set up the frame 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("New User Information");
		setBounds(100, 100, 675, 467);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		contentPane = new JPanel();
		contentPane.setBackground(UIManager.getColor("EditorPane.selectionBackground"));
		contentPane.setName("");
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);		
		
		// Label for the password reset
		JLabel lblNewUser = new JLabel("New User Info");
		lblNewUser.setFont(new Font("Lucida Grande", Font.PLAIN, 24));
		lblNewUser.setBounds(64, 30, 166, 26);
		contentPane.add(lblNewUser);
		
		// Label for the account id
		JLabel lblNewLabel = new JLabel("Account ID");
		lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblNewLabel.setBounds(124, 85, 104, 16);
		contentPane.add(lblNewLabel);
		
		// the text field for entering the account id
		accID = new JTextField();
		accID.setBounds(284, 78, 285, 30);
		contentPane.add(accID);
		accID.setColumns(10);
		
		// Label for the password
		JLabel lblPreferredName = new JLabel("Password");
		lblPreferredName.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblPreferredName.setBounds(124, 124, 114, 16);
		contentPane.add(lblPreferredName);
		
		// the text field for entering the password
		pswd = new JTextField();
		pswd.setColumns(10);
		pswd.setBounds(284, 118, 285, 30);
		contentPane.add(pswd);
		
		// Label for the Company
		JLabel lblOrganization = new JLabel("Company");
		lblOrganization.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblOrganization.setBounds(124, 164, 96, 16);
		contentPane.add(lblOrganization);
		
		// the text field for entering the company name
		orga = new JTextField();
		orga.setColumns(10);
		orga.setBounds(284, 158, 285, 30);
		contentPane.add(orga);
		
		// Label for the email
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblEmail.setBounds(124, 206, 76, 16);
		contentPane.add(lblEmail);
		
		// the text field for entering the email
		email = new JTextField();
		email.setColumns(10);
		email.setBounds(284, 200, 285, 30);
		contentPane.add(email);
		
		// Label for the phone number
		JLabel lblPhone = new JLabel("Phone #");
		lblPhone.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblPhone.setBounds(124, 244, 76, 16);
		contentPane.add(lblPhone);
		
		// Text field for entering the phone number
		phone = new JTextField();
		phone.setColumns(10);
		phone.setBounds(284, 238, 285, 30);
		contentPane.add(phone);
		
		// Label for the role (organization/ Donor/ Recepient)
		JLabel lblAddress = new JLabel("Role");
		lblAddress.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblAddress.setBounds(124, 283, 96, 16);
		contentPane.add(lblAddress);
		
		// Selection button to choose the role
		String[] options = {"Organization", "Donor", "Receipient"};
		role = new JComboBox(options);
		role.setRenderer(new MyComboBoxRenderer("Please Select"));
		role.setSelectedIndex(-1);
		role.setBounds(284, 278, 285, 30);
		contentPane.add(role);
		
		// Submit button
		myLogo = new ImageIcon("resources/husky 2.jpeg");
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		btnSubmit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// Check to make sure that all the required user info is completed
				if(accID.getText().equals("") || pswd.getText().equals("") 
						|| orga.getText().equals("") || phone.getText().equals("")
						|| email.getText().equals("") || role.getSelectedIndex() == -1){
					// info not completed 
				    ImageIcon myErrorLogo = new ImageIcon(Main.class.getResource("/small_Cancel.jpg"));
					JOptionPane.showMessageDialog(null, "Please complete all the requested information",
							"Info Incomplete", JOptionPane.ERROR_MESSAGE, myErrorLogo);
				} else {
					// If the user entered all the required info, then write the user info to an XML file. 
					User newUser = new User(); 
					newUser.setAccountID(accID.getText());
					newUser.setPassword(pswd.getText());
					newUser.setOrganization(orga.getText());
					newUser.setEmail(email.getText());
					newUser.setPhone(phone.getText());
					newUser.setRole(role.getSelectedItem().toString());
					
					// add the new user to the original list
					myUsers.add(newUser);
					
					// rewrite the xml file 
					XMLHandler.writeUserXML(myUsers);
					
					// pop up a message indicating that the new user has been added. 
					JOptionPane.showMessageDialog(null, "Thank you for signing up!", 
							"New User Sign Up Success!", JOptionPane.INFORMATION_MESSAGE, myLogo);
					
					setVisible(false);
					myParent.setVisible(true);
				}				
			}
		});
		btnSubmit.setBounds(190, 343, 247, 35);
		contentPane.add(btnSubmit);
		this.setResizable(false);
	}	
	
	/**
	 * This is an inner for the JComboBox to do the Role selection task. 
	 * This class helps mark the Role (Organization, Donor, Recipient) that the user selects. 
	 * 
	 * @author Cindy Wang
	 */
	@SuppressWarnings("rawtypes")
	class MyComboBoxRenderer extends JLabel implements ListCellRenderer
    {
        /**
		 * A default serial ID
		 */
		private static final long serialVersionUID = 1L;
		
		/**
		 * the role
		 */
		private String _title;

        public MyComboBoxRenderer(String title)
        {
            _title = title;
        }

        @Override
        public Component getListCellRendererComponent(JList list, Object value,
                int index, boolean isSelected, boolean hasFocus)
        {
            if (index == -1 && value == null) setText(_title);
            else setText(value.toString());
            return this;
        }
    }
}
