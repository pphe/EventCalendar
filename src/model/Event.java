package model;

/**
 * Simulates an Event on a calendar with all the fields for information on the Event.
 *  
 * @author Arrunn Chhouy
 * @author Matthew Wu
 * @editor Travis Arriola
 * @editor Peter Phe
 */
public class Event {
    
    /** The String Date of the Event */
	private String myDate;
	
	/** The String Time of the Event */
	private String myTime;
	
	/** The String Location of the Event */
	private String myLocation;
	
	/** The String Category of the Event */
	private String myCategory;
	
	/** The String Name of the Event */
	private String myTitle;
	
	/** The String name of sponsor of the Event */
	private String mySponsor;
	
	/** The Data statistics information of the Event */
	private Data myChartInfo;
	
	/**
	 * Initializes Chart data.
	 */
	public Event() {
		myChartInfo = new Data();		
	}
	
	/**
	 * Sets the Date of the Event.
	 * 
	 * @param theDate the String Date to be set
	 */
	public void setDate(String theDate) {
		myDate = theDate;
	}
	/**
	 * returns the Date.
	 * 
	 * @return String Date
	 */
	public String getDate() {
		return myDate;
	}

	/**
	 * Sets the Time of the Event.
	 * 
	 * @param theTime the String Time to be set
	 */
	public void setTime(String theTime) {
		myTime = theTime;
	}
	
	/**
	 * returns the Time of the Event.
	 * 
	 * @return String Time
	 */
	public String getTime() {
		return myTime;
	}
	
	/**
	 * Sets the Location of the Event.
	 * 
	 * @param theLocation String Location to be set
	 */
	public void setLocation(String theLocation) {
		myLocation = theLocation;
	}
	
	/**
	 * returns the Location of the Event.
	 * 
	 * @return String Location.
	 */
	public String getLocation() {
		return myLocation;
	}
	
	/**
	 * Sets the Category of the Event.
	 * 
	 * @param theCategory String category to be set
	 */
	public void setCategory(String theCategory) {
		myCategory = theCategory;
	}
	
	/**
	 * returns the Category of the Event.
	 * 
	 * @return String Category.
	 */
	public String getCategory() {
		return myCategory;
	}
	
	/**
	 * Sets the Name of the Event.
	 * 
	 * @param theTitle String Title to be set
	 */
	public void setTitle(String theTitle) {
		myTitle = theTitle;
	}
	
	/**
	 * returns the Name of the Event.
	 * 
	 * @return String Title
	 */
	public String getTitle() {
		return myTitle;
	}
	
	/**
	 * Sets the Sponsor of the Event.
	 * 
	 * @param theSponsor String Sponsor to be set
	 */
	public void setSponsor(String theSponsor) {
		mySponsor = theSponsor;
	}
	
	/**
	 * returns the sponsor of the Event.
	 * 
	 * @return String Sponsor
	 */
	public String getSponsor() {
		return mySponsor;
	}
	
	/**
	 * returns the Data statistics of the Event.
	 * 
	 * @return Data ChartInfo
	 */
	public Data getInfo() {
		return myChartInfo;
		
	}
}
