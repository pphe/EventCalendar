package view;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

/**
 * This class introduces the members of our team (Peter & Sons + One)
 * 
 * @author Cindy Wang
 * @editor Peter Phe
 * @version 1.0
 */
public class GUI_About extends JFrame {

	/**
	 * This is the default serial ID
	 */
	private static final long serialVersionUID = 1L;
	
	private JFrame myParent;
	
	/**
	 * JPanel that contains all the buttons and labels. 
	 */
	private JPanel contentPane;

	/**
	 * This is the constructor of the About class
	 * This constructor sets up the frame, labels, and buttons. 
	 */
	public GUI_About(GUI_Login theParent) {
	    myParent = theParent;
	    
		// Set up the frame 
		setTitle("About");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 650, 450);
		contentPane = new JPanel();
		contentPane.setBackground(UIManager.getColor("EditorPane.selectionBackground"));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		
		
		// Label for the team name 
		JLabel lblPeterSons = new JLabel("Peter & Sons + One");
		lblPeterSons.setHorizontalAlignment(SwingConstants.CENTER);
		lblPeterSons.setFont(new Font("Lucida Grande", Font.BOLD, 20));
		lblPeterSons.setBounds(201, 32, 218, 25);
		contentPane.add(lblPeterSons);
		
		// Introduce each of the team member and his/her position
		// Team Member #1
		JLabel lblNewLabel = new JLabel("Cindy Wang");
		lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblNewLabel.setBounds(130, 98, 117, 16);
		contentPane.add(lblNewLabel);
		
		JLabel lblSoftwareDeveloper = new JLabel("Web Designer, Software Developer");
		lblSoftwareDeveloper.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblSoftwareDeveloper.setBounds(308, 98, 279, 16);
		contentPane.add(lblSoftwareDeveloper);
		
		// Team Member #2
		JLabel lblPeterPhe = new JLabel("Peter Phe");
		lblPeterPhe.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblPeterPhe.setBounds(130, 138, 117, 16);
		contentPane.add(lblPeterPhe);
		
		JLabel lblTitle = new JLabel("Boss, Scrum Master, Captain");
		lblTitle.setForeground(Color.RED);
		lblTitle.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		lblTitle.setBounds(308, 133, 250, 25);
		contentPane.add(lblTitle);
		
		// Team Member #3
		JLabel lblTravis = new JLabel("Travis Arrola");
		lblTravis.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblTravis.setBounds(130, 178, 117, 16);
		contentPane.add(lblTravis);
		
		JLabel lblTitle_1 = new JLabel("Secretary, Software Engineer");
		lblTitle_1.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblTitle_1.setBounds(308, 178, 250, 16);
		contentPane.add(lblTitle_1);
		
		// Team Member #4
		JLabel lblMatt = new JLabel("Matthew Wu");
		lblMatt.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblMatt.setBounds(130, 218, 117, 16);
		contentPane.add(lblMatt);
		
		JLabel lblTitle_2 = new JLabel("Software Engineer");
		lblTitle_2.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblTitle_2.setBounds(308, 218, 250, 16);
		contentPane.add(lblTitle_2);
		
		// Team Member #5
		JLabel lblAaron = new JLabel("Arrunn Chhouy");
		lblAaron.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblAaron.setBounds(130, 258, 132, 16);
		contentPane.add(lblAaron);
		
		JLabel lblTitle_3 = new JLabel("Software Engineer");
		lblTitle_3.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblTitle_3.setBounds(308, 258, 250, 16);
		contentPane.add(lblTitle_3);
		
		
		
		// This is the button that closes the current window
		JButton btnOk = new JButton("OK");
		btnOk.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			    setVisible(false);
			    myParent.setVisible(true);
			}
		});
		btnOk.setBounds(201, 337, 218, 40);
		contentPane.add(btnOk);
		this.setResizable(false);
	}
}
