package model;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * A class that organizes all categories with two values.
 * 
 * @author Travis Arriola
 * @version 1.0
 *
 */
public class Data {
	
	/** Map to store the goal value */
	Map<String, Integer> myGoal;
	
	/** Map to store the current value */
	Map<String, Integer> myCurrent;	
	
	/**
	 * Initializes all fields.
	 */
	public Data() {		
		
		myCurrent = new HashMap<String, Integer>();		
		myGoal = new HashMap<String, Integer>();				
	}
	
	/**
	 * Adds a category with a corresponding current and goal value.
	 * 
	 * @param category the String to be added as the key
	 * @param current the integer value to be added as the value
	 * @param goal the integer value to be added as the value
	 */
	public void addCategory(String category, int current, int goal) {
		myCurrent.put(category, current);
		myGoal.put(category, goal);
	}
	
	/**
	 * Removes a current category from the Data object and its current and goal values.
	 * 
	 * @param category String for the category to be removed
	 */
	public void removeCategory(String category) {
		myCurrent.remove(category);
		myGoal.remove(category);		
	}
	
	/**
	 * Updates the Current category with new current and goal values.
	 * 
	 * @param category a String category to be updated
	 * @param current an integer value to replace old current
	 * @param goal an integer value to replace old goal
	 */
	public void updateCategory(String category, int current, int goal) {
		myCurrent.put(category, current);
		myGoal.put(category, goal);		
	}
	
	/**
	 * returns the current value associated with the category passed.
	 * 
	 * @param category a String category to be passed
	 * @return an integer the corresponding current value 
	 */
	public int getCurrent(String category) {
		return myCurrent.get(category);		
	}
	
	/**
	 * returns the goal value associated with the category passed.
	 * 
	 * @param category a String category to be passed
	 * @return an integer the corresponding goal value 
	 */
	public int getGoal(String category) {
		return myGoal.get(category);
	}
	
	/**
	 * returns the all the categories in the Data Object.
	 * 
	 * @return a Set of String categories.
	 */
	public Set<String> getKeys() {		
		return myGoal.keySet();
		
	}
}
