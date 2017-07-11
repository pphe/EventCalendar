package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.Data;

/**
 * Creates a bar chart of the categories.
 * 
 * @author Travis Arriola
 */
public class GUI_BarChart extends JPanel {
	
	/** Auto generated ID */
	private static final long serialVersionUID = -345800670971635049L;

	/** int array of goals */
	private int[] myGoal;

	/** int array of current values  */
	private int[] myCurrent;
	
	/** String array of category names  */
	private String[] names;
	
	/** String name of bar chart  */
	private String title;
	
	/**
	 * Initializes all fields.
	 * 
	 * @param theInfo Data object used to create the bar chart
	 */
	public GUI_BarChart(Data theInfo) {
		int len = theInfo.getKeys().size();
		int i = 0;
		names = new String[len];
		myGoal = new int[len];
		myCurrent = new int[len];
	  
		for (String c : theInfo.getKeys()) {
			names[i] = c;
			myCurrent[i] = theInfo.getCurrent(c);
			myGoal[i] = theInfo.getGoal(c);
			i++;
		}  
	   
		title = "Statistics";
	 
		generateChart();
	 
	}
	
	/**
	 * Sets up the JFrame and creates the chart.
	 */
	private void generateChart() {
		JFrame f = new JFrame();
		f.setSize(400, 500);
	    
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		JLabel current = new JLabel("Current");		
	
		ColorIcon currIcon = new ColorIcon(Color.GREEN);
		current.setIcon(currIcon);
		panel.add(current);
		
		JPanel panelD = new JPanel();
		panelD.setBackground(Color.WHITE);		
		panel.add(panelD); 
		
		JLabel goalJ = new JLabel("Goal");		
		ColorIcon goalIcon = new ColorIcon(Color.BLUE);
		goalJ.setIcon(goalIcon);
		panel.add(goalJ); 
	  
		f.add(panel, BorderLayout.SOUTH);
		this.setBackground(Color.WHITE);
		f.add(this, BorderLayout.CENTER);
	  
		WindowListener wndCloser = new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				f.dispose();
			}
	    	};
	    	f.addWindowListener(wndCloser);
	    
	    	final Toolkit kit = Toolkit.getDefaultToolkit();
	    
	    	f.setLocation(
	    			(int) (kit.getScreenSize().getWidth() / 2 - f.getWidth() / 2),
	    			(int) (kit.getScreenSize().getHeight() / 2 - f.getHeight() / 2));
	    
	    	f.setResizable(false);
	    	f.setVisible(true);
	  
	}
	/**
	 * paints the bar chart. 
	 * 
	 * @param Graphics graphics object to be used
	 */
	public void paintComponent(Graphics theg) {
		super.paintComponent(theg);
		Graphics2D g = (Graphics2D) theg;
		if (myGoal == null || myGoal.length == 0)
			return;
		double minValue = 0;
		double maxValue = 0;
		for (int i = 0; i < myGoal.length; i++) {
			if (minValue > myGoal[i])
				minValue = myGoal[i];
			if (maxValue < myGoal[i])
				maxValue = myGoal[i];
		}

		Dimension d = getSize();
		int clientWidth = d.width;
		int clientHeight = d.height;
		int barWidth = clientWidth / myGoal.length;

		Font titleFont = new Font("SansSerif", Font.BOLD, 20);
		FontMetrics titleFontMetrics = g.getFontMetrics(titleFont);
		Font labelFont = new Font("SansSerif", Font.PLAIN, 15);
		FontMetrics labelFontMetrics = g.getFontMetrics(labelFont);

		int titleWidth = titleFontMetrics.stringWidth(title);
		int y = titleFontMetrics.getAscent();
		int x = (clientWidth - titleWidth) / 2;
		g.setFont(titleFont);
		g.drawString(title, x, y);

		int top = titleFontMetrics.getHeight() + 50;
		int bottom = labelFontMetrics.getHeight();
		if (maxValue == minValue)
			return;
		double scale = (clientHeight - top - bottom) / (maxValue - minValue);    
		y = clientHeight - labelFontMetrics.getDescent();
		g.setFont(labelFont);
    
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
            RenderingHints.VALUE_ANTIALIAS_ON);
    
		Font numberFont = new Font("SansSerif", Font.BOLD, 15);

		for (int i = 0; i < myGoal.length; i++) {
			int valueX = i * barWidth + 5;
			int valueY = top;
			int height = (int) (myGoal[i] * scale);
			if (myGoal[i] >= 0)
				valueY += (int) ((maxValue - myGoal[i]) * scale);
			else {
				valueY += (int) (maxValue * scale);
				height = -height;
			}

			g.setColor(Color.BLUE);
			g.fillRect(valueX, valueY, barWidth - 10, height);
			g.setColor(Color.black);
			g.drawRect(valueX, valueY, barWidth - 10, height);
			g.setFont(labelFont);
			int labelWidth = labelFontMetrics.stringWidth(names[i]);
			x = i * barWidth + (barWidth - labelWidth) / 2;
			g.drawString(names[i], x, y);
			g.setFont(numberFont);
			g.setColor(Color.WHITE);
			g.drawString(Integer.toString((int) (myGoal[i])) + " Units", valueX + 10, valueY + 20);
		}
    
		g.setFont(numberFont);       
        
		for (int i = 0; i < myCurrent.length; i++) {
			int valueX = i * barWidth + 5;
			int valueY = top;
			int height = (int) (myCurrent[i] * scale + 1);
			if (myCurrent[i] >= 0)
				valueY += (int) ((maxValue - myCurrent[i]) * scale);
			else {
				valueY += (int) (maxValue * scale);
				height = -height;
			}

			g.setColor(Color.GREEN);
			g.fillRect(valueX, valueY, barWidth - 10, height);
			g.setColor(Color.black);
			g.drawRect(valueX, valueY, barWidth - 10, height);
			g.setColor(Color.BLACK); 
			g.drawString(Integer.toString((int) (myCurrent[i])) + " Units", valueX + 10, valueY + 20);       
        
		}
	}


}

