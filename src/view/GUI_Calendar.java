package view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableModel;

import model.Event;
import model.User;
import runApplication.Main;
import utility.XMLHandler;

/**
 * Simulates a standard Gregorian Calendar.
 * 
 * @author Arrunn Chhouy 
 * @author Peter Phe
 * @author Matthew Wu
 * @editor Travis Arriola
 * @editor Cindy Wang
 * 
 */
public class GUI_Calendar extends JFrame{
	
    /** Auto generated serial ID	 */
	private static final long serialVersionUID = 9132703718627875382L;

	/** Width of the Calendar */
    public static final int WIDTH = 1000;

    /** Height of the Calendar*/
    public static final int HEIGHT = 800;

    /** The Main JFrame*/
    JFrame myMainFrame;

    /** Month Label and Year Label */
    JLabel myLabelMonth, myLabelYear;

    /** Next Button and Previous Button */
    JButton myButtonPrev, myButtonNext, myAddEvent;

    /** Table */
    JTable myTableCalendar;

    /** Combo box of the Year */
    JComboBox<String> myComboYear;

    /** Container of the Calendar */
    Container myPane;

    /** Table Model */
    DefaultTableModel mtblCalendar; //Table model

    /** Scroll Pane */
    JScrollPane stblCalendar; //The scrollpane

    /** Panel to hold calendar */
    JPanel myPanelCalendar; //The panel

    /** Parent JFrame */
    JFrame myParent;

    /** Information on the Calendar */
    int myRealDay, myRealMonth, myRealYear, myCurrentMonth, myCurrentYear;
    
    /** Map of String Dates with corresponding Events. */
    HashMap<String, Event> myEvents;
    
    /** The Current User viewing the Calendar. */
    private User myUser;

    /**
     * Contrustor initializes all fields and sets up calendar.
     * 
     * @param theParent JFrame 
     * @param theUser
     */
    public GUI_Calendar(JFrame theParent, User theUser){
        myParent = theParent;
        myUser = theUser;

        setupEvents();
        // Set Look and Feel of the window
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch (ClassNotFoundException e) {}
        catch (InstantiationException e) {}
        catch (IllegalAccessException e) {}
        catch (UnsupportedLookAndFeelException e) {}

        String[] headers = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};

        // Instantiate all Calendar components
        myMainFrame = this;
        myLabelMonth = new JLabel ("January");
        myLabelYear = new JLabel ("Change year:");
        myComboYear = new JComboBox<String>();
        myButtonPrev = new JButton ("<");
        myButtonNext = new JButton (">");
        myAddEvent = new JButton("Add Event");

        // if the role is not organization, deactivate the "Add Event" button

        // Constructor(Object[], row count)
        mtblCalendar = new DefaultTableModel(headers, 7);
        myTableCalendar = new JTable(mtblCalendar); 		//Table using the above model
        stblCalendar = new JScrollPane(myTableCalendar); 	//The scrollpane of the above table
        myPanelCalendar = new JPanel(null); 				//Create the "panel" to place components

        setUpFrame();

        myMainFrame.addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowClosing(WindowEvent e)
            {
                Set<String> keys = myEvents.keySet();
                List<Event> events = new ArrayList<Event>();
                for (String key : keys) {
                    Event event = myEvents.get(key);
                    events.add(event);
                    XMLHandler.writeEventXML(events);
                }
                //                e.getWindow().dispose();
            }
        });
    }

    private void setupEvents() {
        myEvents = new HashMap<String, Event>();
        List<Event> events = XMLHandler.readEventXML();    	
        for (Event e : events) {         	
            myEvents.put(e.getDate(), e);
        }
    }

    private void setUpFrame() {
        // Sets the frame to a border layout
        myMainFrame.getContentPane().setLayout(new BorderLayout());

        // Sets the default size of the JFrame
        myMainFrame.setSize(WIDTH, HEIGHT);

        // Get content of main frame 
        myPane = myMainFrame.getContentPane();
        

        // Apply the null layout
        myPane.setLayout(null); 

        // DOES NOT WORK!!!!!!!!!!!!!
        myPanelCalendar.setBorder(BorderFactory.createTitledBorder("Calendar"));

        // Add controls to pane
        myPane.add(myPanelCalendar);
        myPanelCalendar.add(myLabelMonth);
        myPanelCalendar.add(myLabelYear);
        myPanelCalendar.add(myComboYear);
        myPanelCalendar.add(myButtonPrev);
        myPanelCalendar.add(myButtonNext);
        if (myUser.getRole().toLowerCase().equals("organization"))
            myPanelCalendar.add(myAddEvent);
        myPanelCalendar.add(stblCalendar);

        addAction();

        setControlSize();

        calendarSetting();

        // Adds the year to the combo box at the bottom of the window
        populateComboBox();

        // Packs and allows visibility
        adjustFrame();



        ////// Menu starts here /////
        
        //Where the GUI is created:
        JMenuBar menuBar;
        JMenu menu;
        JMenuItem HomeMenuItem;
        JMenuItem LogOutMenuItem;

        //Create the menu bar.
        menuBar = new JMenuBar();

        //Build the first menu.
        menu = new JMenu("Navigation");
        menu.setMnemonic(KeyEvent.VK_N);
        menuBar.add(menu);

        //This is the Home menu item 
        HomeMenuItem = new JMenuItem("Back to Region", 
                new ImageIcon(Main.class.getResource("/mapIcon.jpg")));
        HomeMenuItem.setMnemonic(KeyEvent.VK_R);
        HomeMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                myParent.setVisible(true);
            }

        });
        menu.add(HomeMenuItem);

        // This is the log out menu item
        LogOutMenuItem = new JMenuItem("Log Out",
                new ImageIcon(Main.class.getResource("/Logout.jpg")));
        LogOutMenuItem.setMnemonic(KeyEvent.VK_L);
        LogOutMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                myParent.getParent().setVisible(true);
            }

        });

        menu.add(LogOutMenuItem);
        setJMenuBar(menuBar);

        //		this.setResizable(false);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    /**
     * Add mouse listener actions. 
     */
    private void addAction() {
        // When user clicks on one of the day 
        myTableCalendar.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int row = myTableCalendar.rowAtPoint(evt.getPoint());
                int column = myTableCalendar.columnAtPoint(evt.getPoint());

                // handles NullPointerException if non-calendar cells are selected
                try {
                    loadEvents((String) myTableCalendar.getValueAt(row, column)); 
                } catch (NullPointerException e) {
                    return;
                }
            }
        });

        //Register action listeners
        myButtonPrev.addActionListener(new btnPrev_Action());
        myButtonNext.addActionListener(new btnNext_Action());
        myComboYear.addActionListener(new cmbYear_Action());
        myAddEvent.addActionListener(new addNewEvent_Action());
    }

    /**
     * Loads the Event corresponding to the Day and creates a pop up window of the
     * event details on that day.
     * 
     * @param theEventDay String Day of the Event.
     */
    private void loadEvents(String theEventDay) {
        try {
            Date date = new SimpleDateFormat("MMMM").parse(myLabelMonth.getText());
            Calendar cal = Calendar.getInstance();			
            cal.setTime(date);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        int i = theEventDay.indexOf(' ');
        theEventDay = theEventDay.substring(0, i);  
        int day = Integer.parseInt(theEventDay);

        String key = myCurrentYear + "-";
        int realMonth = myCurrentMonth + 1;
        if (realMonth < 10) {        	
            key += "0" + realMonth;
        } else {
            key += realMonth;
        }
        key += "-";
        if (day < 10) {
            key += "0" + day;
        } else {
            key += day;
        }
        
        Event event = myEvents.get(key);
        
        if (event != null) {			
            GUI_Event event2 = new GUI_Event(event); 
            event2.setVisible(true);
        }
    }

    /**
     * Sets up the sizes for everything.
     */
    private void setControlSize() {
        // Set Outer bound of calendar
        myPanelCalendar.setBounds(10, 10, WIDTH - 40, HEIGHT - 70);

        // Set Month label 
        myLabelMonth.setBounds(475 - myLabelMonth.getPreferredSize().width / 2, 25, 100, 25);

        // Set Year label
        myLabelYear.setBounds(716, 700, 121, 20);

        // Set combo box year
        myComboYear.setBounds(829, 700, 121, 20);

        // Set location for add new event button
        myAddEvent.setBounds(10, HEIGHT - 110, 100, 30);

        // Set previous button bounds
        myButtonPrev.setBounds(10, 25, 50, 25);

        // Set next button bounds
        myButtonNext.setBounds(WIDTH - 100, 25, 50, 25);

        // Set Calendar bound
        stblCalendar.setBounds(10, 70, WIDTH - 60, HEIGHT - 199);
    }

    /**
     * Sets up the JTable as well as the Default Table Model.
     */
    private void calendarSetting() {
        //Single cell selection
        myTableCalendar.setColumnSelectionAllowed(true);
        myTableCalendar.setRowSelectionAllowed(true);
        myTableCalendar.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        //Set row/column count
        myTableCalendar.setRowHeight(95);

        //mtblCalendar.setColumnCount(7);
        mtblCalendar.setRowCount(6);
    }

    /**
     * Sets up the Combo box at the bottom of the GUI.
     */
    private void populateComboBox() {
        //Get real month/year
        GregorianCalendar cal = new GregorianCalendar(); //Create calendar
        myRealDay = cal.get(GregorianCalendar.DAY_OF_MONTH); //Get day
        myRealMonth = cal.get(GregorianCalendar.MONTH); //Get month
        myRealYear = cal.get(GregorianCalendar.YEAR); //Get year
        myCurrentMonth = myRealMonth; //Match month and year
        myCurrentYear = myRealYear;

        //Populate combo box
        for (int i = myRealYear; i <= myRealYear + 50; i++){
            myComboYear.addItem(String.valueOf(i));        	
        }
    }

    /**
     * Compacts and centers the frames and allows visibility.
     */
    private void adjustFrame() {        
        // Centers the window to the screen
        myMainFrame.setLocationRelativeTo(null);

        // Allows visibility of the window
        myMainFrame.setVisible(true);
        
        myMainFrame.setResizable(false);
    }

    /**
     * Inner Action class for the previous button.
     * 
     * @author Arrunn Chhouy
     * @author Matthew Wu
     */
    private class btnPrev_Action implements ActionListener{
        public void actionPerformed (ActionEvent e){
            if (myCurrentMonth == 0){ //Back one year
                myCurrentMonth = 11;
                myCurrentYear -= 1;
            } else { //Back one month
                myCurrentMonth -= 1;
            }
            refreshCalendar(myCurrentMonth, myCurrentYear);
        }
    }

    /**
     * Inner Action class for the next button.
     * 
     * @author Arrunn Chhouy
     * @author Matthew Wu
     */
    private class btnNext_Action implements ActionListener{
        public void actionPerformed (ActionEvent e){
            if (myCurrentMonth == 11){ //Foward one year
                myCurrentMonth = 0;
                myCurrentYear += 1;
            } else { //Foward one month
                myCurrentMonth += 1;
            }
            refreshCalendar(myCurrentMonth, myCurrentYear);
        }
    }
    
    /**
     * Inner Action class for the ComboBox button.
     * 
     * @author Arrunn Chhouy
     * @author Matthew Wu
     */
    private class cmbYear_Action implements ActionListener {
        public void actionPerformed (ActionEvent e){
            if (myComboYear.getSelectedItem() != null){
                String b = myComboYear.getSelectedItem().toString();
                myCurrentYear = Integer.parseInt(b); //Get the numeric value
                refreshCalendar(myCurrentMonth, myCurrentYear); //Refresh
            }
        }
    }

    /**
     * Inner Action class for the previous button.
     * 
     * @author Arrunn Chhouy
     * @author Matthew Wu
     */
    private class addNewEvent_Action implements ActionListener {
        public void actionPerformed (ActionEvent e){
            GUI_Event event = new GUI_Event(GUI_Calendar.this, myCurrentMonth, myCurrentYear, myEvents);
            event.setVisible(true);
        }
    }

    /**
     * Refreshes the Calendar when called to display newly added events.
     * 
     * @param month Integer of current month.
     * @param year Integer of current year.
     */
    public void refreshCalendar(int month, int year){
        String[] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};

        int nod, som; //Number Of Days, Start Of Month

        myButtonPrev.setEnabled(true); //Enable buttons at first
        myButtonNext.setEnabled(true);
        //Too early
        if ( month == 0 && year <= myRealYear - 10 ){
            myButtonPrev.setEnabled(false);
        }
        //Too late
        if ( month == 11 && year >= myRealYear + 100){
            myButtonNext.setEnabled(false);
        } 

        //Refresh the month label (at the top)
        myLabelMonth.setText(months[month]); 

        //Re-align label with calendar
        myLabelMonth.setBounds(475 - myLabelMonth.getPreferredSize().width / 2, 25, 100, 25); 

        //Select the correct year in the combo box
        myComboYear.setSelectedItem(String.valueOf(year)); 

        //Get number of days and start of month
        GregorianCalendar cal = new GregorianCalendar(year, month, 1);
        nod = cal.getActualMaximum(GregorianCalendar.DAY_OF_MONTH);    	
        som = cal.get(GregorianCalendar.DAY_OF_WEEK);
        //Clear table
        for (int i = 0; i < 6; i++){
            for (int j = 0; j < 7; j++){
                mtblCalendar.setValueAt(null, i, j);
            }
        }

        for (int i = 1; i <= nod; i++){
            int row = new Integer((i + som - 2) / 7);
            int column = (i + som - 2) % 7;
            String key = myCurrentYear + "-";
            int actualMonth = myCurrentMonth + 1;
            if (actualMonth < 10) {
                key += "0" + actualMonth;
            } else {
                key += actualMonth;
            }
            key += "-";
            if (i < 10) {
                key += "0" + i;
            } else {
                key += i;
            }             
            Event event = myEvents.get(key);
            String title = "";
            if (event != null) {
                title = event.getTitle();
            }

            mtblCalendar.setValueAt(Integer.toString(i) + " --" + title, row, column);


        }    	
    }
}
