package view;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import model.User;
import runApplication.Main;


/**
 * This is a class for displaying the map and for selecting the region. 
 * 
 * @author Cindy Wang
 * @editor Peter Phe
 * @version 1.0
 */

public class GUI_Map extends JFrame {

    /**
     * A default serial ID
     */
    private static final long serialVersionUID = 1L;

    /**
     * jpanel that holds all the content
     */
    private JPanel contentPane;

    /**
     * allows the user to select different regions on the map
     */
    @SuppressWarnings("rawtypes")
    private JComboBox comboBox;

    /**
     * Main JFrame.
     */
    private JFrame myParent;
    
    /**
     * GUI Calendar to be displayed.
     */
    private GUI_Calendar myGUI_Calendar;

    /**
     * The current User.
     */
    private User myUser;

    /**
     * Constructor initializes all fields and creates the Map.
     * 
     * @param theParent the JFrame to be used.
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public GUI_Map(JFrame theParent) {

        myParent = theParent;

        // create the frame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(0,0,1000,800);
        this.setTitle("Welcome to Peter & Sons + One");
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        contentPane = new JPanel();
        contentPane.setBackground(Color.WHITE);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        // add region selection functionality
        String[] options = {"NE", "NW", "MA", "MW", "SC", "SE", "SW"};
        comboBox = new JComboBox(options);
        comboBox.setRenderer(new MyComboBoxRenderer("Select Region"));
        comboBox.setSelectedIndex(-1);
        comboBox.setBounds(165, 635, 300, 43);
        contentPane.add(comboBox);

        // the submit button and write to json file


        /**
         * The icon that appears on the window title and on the About message.
         */

        JButton btnSubmit = new JButton("Submit");
        btnSubmit.setBackground(Color.WHITE);
        btnSubmit.setForeground(Color.BLACK);
        btnSubmit.setFont(new Font("Lucida Grande", Font.BOLD, 16));
        btnSubmit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // check if the region has been selected before pressing submit
                if (comboBox.getSelectedIndex() != -1) {
                    // this is the region that the user selected 
                    String region = comboBox.getSelectedItem().toString();

                    /* TODO
                     * XMLHandler class - handle Region for read/writes
                     * Calendar and Event class - filter and include only the events pertaining to the specific region.
                     */

                    JOptionPane.showMessageDialog(null, "Region: " + region + " has been selected!",
                            "Peter & Sons + One", JOptionPane.INFORMATION_MESSAGE, 
                            new ImageIcon(Main.class.getResource("/calendar.jpg")));

                    // transition to the calendar once the user clicks submit.
                    setVisible(false);
                    
                    myGUI_Calendar.setVisible(true);
                }
            }
        });
        btnSubmit.setBounds(560, 635, 300, 37);
        contentPane.add(btnSubmit);


        // The label indicating the region to donate
        JLabel lblPleaseSelectA = new JLabel("Please Select a Region to Donate");
        lblPleaseSelectA.setHorizontalAlignment(SwingConstants.CENTER);
        lblPleaseSelectA.setFont(new Font("Lucida Grande", Font.PLAIN, 50));
        lblPleaseSelectA.setBounds(95, 69, 830, 70);
        contentPane.add(lblPleaseSelectA);

        // Insert the map into the frame
        JLabel lblNewLabel = new JLabel("");
        lblNewLabel.setForeground(Color.LIGHT_GRAY);
        lblNewLabel.setBackground(Color.WHITE);
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setIcon(new ImageIcon(Main.class.getResource("/USRegion4.jpg")));
        //		lblNewLabel.setBounds(93, 80, 483, 298);
        lblNewLabel.setBounds(0, 0, 1000, 800);
        contentPane.add(lblNewLabel);

        this.setResizable(false);


        ////// Menu starts here /////
        //Where the GUI is created:
        JMenuBar menuBar;
        JMenu menu;
        JMenuItem LogOutMenuItem;

        //Create the menu bar.
        menuBar = new JMenuBar();

        //Build the first menu.
        menu = new JMenu("Navigation");
        menu.setMnemonic(KeyEvent.VK_N);
        menuBar.add(menu);

        // This is the log out menu item 
        LogOutMenuItem = new JMenuItem("Log Out",new ImageIcon(Main.class.getResource("/Logout.jpg")));
        LogOutMenuItem.setMnemonic(KeyEvent.VK_L);
        LogOutMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                myParent.setVisible(true);
            }
        });

        menu.add(LogOutMenuItem);
        setJMenuBar(menuBar);
    }
    
    /**
     * returns the current User.
     * 
     * @return User object
     */
    public User getUser()
    {
        return myUser;
    }
    
    /**
     * Sets the current user.
     * 
     * @param theUser User to be set as
     */
    public void setUser(User theUser)
    {
        myUser = theUser;
        myGUI_Calendar = new GUI_Calendar(this, myUser);
        myGUI_Calendar.setVisible(false);
    }
    
    /**
     * returns the parent JFrame.
     * 
     * @return JFrame parent
     */
    public JFrame getParent()
    {
        return myParent;
    }


    /**
     * This is an inner class that allows the user to select 
     * different regions on the map. 
     * @author Cindy Wang
     * @version 1.0
     */
    @SuppressWarnings("rawtypes")
    class MyComboBoxRenderer extends JLabel implements ListCellRenderer
    {
        /** Auto generated user ID */
		private static final long serialVersionUID = 5770174839455466367L;
		
		/**
         * this is the name of the selected region. 
         */
        private String _title;

        /**
         * This is the constructor for the inner class. 
         * @param title is the name of the selected region
         */
        public MyComboBoxRenderer(String title)
        {
            _title = title;
        }


        /**
         * This is an helper method that turns the selected comboBox into 
         * a string. 
         */
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
