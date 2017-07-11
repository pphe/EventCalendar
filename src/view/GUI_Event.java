package view;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import model.Event;

/**
 * This class Simulates the GUI version of an Event.
 *  
 * @author Cindy Wang
 * @author Arrunn Chhouy
 * @author Matthew Wu  
 * @editor Travis Arriola
 * @editor Peter Phe
 */
public class GUI_Event extends JFrame {

	/** This is the default serial ID  */
	private static final long serialVersionUID = 1L;
	
	/** JPanel that contains all the buttons and labels. */
	private JPanel contentPane;
	
	/** The Event used to display in the GUI */
	private Event myEvent;
	
	/** The TextFields used when creating an Event */
	private JTextField myDate, myTime, mySponsor, myLocation, myCategory, myTitle;
	
	/** The Map of all the events currently saved */
	private HashMap<String, Event> myEvents;
	
	/**
	 * The constructor for displaying a previously saved Event.
	 * 
	 * @param theEvent an Event object use for display
	 */
	public GUI_Event(Event theEvent) {
		// Set up the frame 
		myEvent = theEvent;
		setTitle(myEvent.getTitle());
		setBounds(100, 100, 650, 450);
		contentPane = new JPanel();
		contentPane.setBackground(UIManager.getColor("EditorPane.selectionBackground"));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);		
		
		JLabel dateLabel = new JLabel("Date");
		dateLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		dateLabel.setBounds(130, 98, 117, 16);
		contentPane.add(dateLabel);		
		JLabel date = new JLabel(myEvent.getDate());
		date.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		date.setBounds(308, 98, 250, 16);
		contentPane.add(date);
		
		JLabel timeLabel = new JLabel("Time");
		timeLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		timeLabel.setBounds(130, 138, 117, 16);
		contentPane.add(timeLabel);		
		JLabel time = new JLabel(myEvent.getTime());
		time.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		time.setBounds(308, 138, 250, 16);
		contentPane.add(time);		
		
		JLabel locationLabel = new JLabel("Location");
		locationLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		locationLabel.setBounds(130, 178, 117, 16);
		contentPane.add(locationLabel);		
		JLabel location = new JLabel(myEvent.getLocation());
		location.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		location.setBounds(308, 178, 250, 16);
		contentPane.add(location);		
		
		JLabel sponsorLabel = new JLabel("Sponsor");
		sponsorLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		sponsorLabel.setBounds(130, 218, 117, 16);
		contentPane.add(sponsorLabel);		
		JLabel sponsor = new JLabel(myEvent.getSponsor());
		sponsor.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		sponsor.setBounds(308, 218, 250, 16);
		contentPane.add(sponsor);		
		
		JLabel categoryLabel = new JLabel("Category");
		categoryLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		categoryLabel.setBounds(130, 258, 132, 16);
		contentPane.add(categoryLabel);		
		JLabel category = new JLabel(myEvent.getCategory());
		category.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		category.setBounds(308, 258, 250, 20);
		contentPane.add(category);
		
		JLabel title = new JLabel(myEvent.getTitle());
		title.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		title.setBounds(205, 46, 132, 36);
		contentPane.add(title);
		
		JButton btnNewButton = new JButton("Back");
		btnNewButton.setBounds(6, 6, 117, 29);
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
			}
		});
		btnNewButton.setEnabled(true);
		contentPane.add(btnNewButton);
		
		// not implemented yet 
//		JButton btnNewButton_1 = new JButton("Edit Event");
//		btnNewButton_1.setBounds(527, 6, 117, 29);
//		btnNewButton_1.addMouseListener(new MouseAdapter() {
//			@Override
//			public void mouseClicked(MouseEvent e) {
//				// write something
//			}
//		});
//		btnNewButton_1.setEnabled(false);
//		contentPane.add(btnNewButton_1);
		
		JLabel lblQty = new JLabel("Qty");
		lblQty.setBounds(308, 287, 29, 16);
		contentPane.add(lblQty);		
		JLabel qty = new JLabel(Integer.toString(myEvent.getInfo().getCurrent(myEvent.getCategory())));
		qty.setBounds(349, 287, 61, 16);
		contentPane.add(qty);
		
		JLabel lblGoal = new JLabel("Goal");
		lblGoal.setBounds(394, 287, 39, 16);		
		contentPane.add(lblGoal);		
		JLabel goal = new JLabel(Integer.toString(myEvent.getInfo().getGoal(myEvent.getCategory())));
		System.out.println(Integer.toString(myEvent.getInfo().getGoal(myEvent.getCategory())));
		goal.setBounds(445, 286, 61, 16);
		contentPane.add(goal);
		
		// adds actionlistener for displaying bar chart
		JButton btnStatistics = new JButton("Statistics");
		btnStatistics.setBounds(19, 373, 592, 29);
		btnStatistics.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {				
//				GUI_BarChart chart new GUI_BarChart(myEvent.getInfo());
			    new GUI_BarChart(myEvent.getInfo());
			}
		});
		contentPane.add(btnStatistics);
		this.setResizable(false);
	}
	
	/**
	 * Constructor for creating a new Event.
	 * 
	 * @param theEvents Map of all events currently saved
	 */
	public GUI_Event(JFrame theParent, int theMonth, int theYear, HashMap<String, Event> theEvents) {
		myEvents = theEvents;		
		setTitle("Event");
		setBounds(100, 100, 650, 450);
		contentPane = new JPanel();
		contentPane.setBackground(UIManager.getColor("EditorPane.selectionBackground"));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		
		JLabel lblTitle = new JLabel("Title");
		lblTitle.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblTitle.setBounds(130, 62, 69, 20);
		contentPane.add(lblTitle);		
		myTitle = new JTextField();
		myTitle.setBounds(308, 59, 250, 26);
		contentPane.add(myTitle);
		myTitle.setColumns(10);		
		
		JLabel dateLabel = new JLabel("Date");
		dateLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		dateLabel.setBounds(130, 98, 117, 16);
		contentPane.add(dateLabel);		
		myDate = new JTextField();		
		myDate.setToolTipText("YYYY-MM-DD");
		myDate.setBounds(308, 98, 250, 24);
		contentPane.add(myDate);		
		
		JLabel timeLabel = new JLabel("Time");
		timeLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		timeLabel.setBounds(130, 138, 117, 16);
		contentPane.add(timeLabel);		
		myTime = new JTextField();
		myTime.setToolTipText("00:00");
		myTime.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		myTime.setBounds(308, 138, 250, 24);
		contentPane.add(myTime);
		
		JLabel locationLabel = new JLabel("Location");
		locationLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		locationLabel.setBounds(130, 178, 117, 16);
		contentPane.add(locationLabel);		
		myLocation = new JTextField();
		myLocation.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		myLocation.setBounds(308, 178, 250, 24);
		contentPane.add(myLocation);		
		
		JLabel sponsorLabel = new JLabel("Sponsor");
		sponsorLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		sponsorLabel.setBounds(130, 218, 117, 16);
		contentPane.add(sponsorLabel);		
		mySponsor = new JTextField();
		mySponsor.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		mySponsor.setBounds(308, 213, 250, 26);
		contentPane.add(mySponsor);
		
		JLabel categoryLabel = new JLabel("Category");
		categoryLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		categoryLabel.setBounds(130, 258, 132, 16);
		contentPane.add(categoryLabel);		
		myCategory = new JTextField();
		myCategory.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		myCategory.setBounds(308, 252, 250, 26);
		contentPane.add(myCategory);
		
		// back button with actionlistener
		JButton btnNewButton = new JButton("Back");
		btnNewButton.setBounds(6, 6, 117, 29);
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {				
				dispose();
			}
		});
		contentPane.add(btnNewButton);
		
		// not implemented yet
//		JButton btnNewButton_1 = new JButton("Edit Event");
//		btnNewButton_1.setBounds(527, 6, 117, 29);
//		
//		btnNewButton_1.addMouseListener(new MouseAdapter() {
//			@Override
//			public void mouseClicked(MouseEvent e) {
//				// write something
//			}
//		});
//		btnNewButton_1.setEnabled(false);
//		contentPane.add(btnNewButton_1);
		
		JLabel lblQty = new JLabel("Qty");
		lblQty.setBounds(308, 287, 29, 16);
		contentPane.add(lblQty);		
		JTextField qty = new JTextField();
		qty.setBounds(349, 287, 61, 26);
		contentPane.add(qty);
		
		JLabel lblGoal = new JLabel("Goal");
		lblGoal.setBounds(420, 287, 39, 16);		
		contentPane.add(lblGoal);		
		JTextField goal = new JTextField();		
		goal.setBounds(474, 287, 61, 27);
		contentPane.add(goal);		
		
		// button that creates and saves Event
		JButton saveBtn = new JButton("Save Event");
		saveBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String t = myTime.getText();
		        System.out.println(t);        		
		        String d = myDate.getText();
		        System.out.println(d);
		        if (d.length() == 10) {
		        	if (d.charAt(4) == '-' && d.charAt(7) == '-' || t.length() == 5 && t.charAt(2) == ':') {
		        		myEvent = new Event();
		        		myEvent.setTime(t);
		        		myEvent.setDate(d);
		        		myEvent.setTitle(myTitle.getText());
		        		myEvent.setLocation(myLocation.getText());
		        		myEvent.setSponsor(mySponsor.getText());
		        		myEvent.setCategory(myCategory.getText());		        		
		        		myEvent.getInfo().addCategory(myCategory.getText(), Integer.parseInt(qty.getText()), Integer.parseInt(goal.getText()));
		        		myEvents.put(d, myEvent);
		        		saveBtn.setEnabled(false);
		        		((GUI_Calendar) theParent).refreshCalendar(theMonth, theYear);
		        	}
		        } else {
		        	JOptionPane.showMessageDialog(null, "Date or Time is invalid",
									"Event Error", JOptionPane.INFORMATION_MESSAGE);		        			        			
		        		}
					}
				});
				saveBtn.setEnabled(true);				
				saveBtn.setBounds(19, 337, 592, 29);
				contentPane.add(saveBtn);
				
		// button for showing bar chart statistics.
		JButton btnStatistics = new JButton("Statistics");
		btnStatistics.setBounds(19, 373, 592, 29);
		btnStatistics.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
//				GUI_BarChart chart = new GUI_BarChart(myEvent.getInfo());
			    new GUI_BarChart(myEvent.getInfo());
			}
		});
		contentPane.add(btnStatistics);		
		
		this.setResizable(false);		
	}
}
