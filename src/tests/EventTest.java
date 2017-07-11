package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import model.Event;

/**
 * Tests Event.java
 * 
 * @author Cindy Wang
 *
 */
public class EventTest {	
	
	@Test
	public void testEvent() {
		Event event = new Event();
		assertNotNull(event);
	}

	@Test
	public void testSetDate() {
		Event event = new Event();
		event.setDate("2016-12-02");
		assertEquals("2016-12-02", event.getDate());
	}

	@Test
	public void testSetTime() {
		Event event = new Event();
		event.setTime("12:00");
		assertEquals("12:00", event.getTime());
	}

	@Test
	public void testSetLocation() {
		Event event = new Event();
		event.setLocation("Tacoma");
		assertEquals("Tacoma", event.getLocation());
	}

	@Test
	public void testSetCategory() {
		Event event = new Event();
		event.setCategory("Toys");
		assertEquals("Toys", event.getCategory());
	}

	@Test
	public void testSetTitle() {
		Event event = new Event();
		event.setTitle("Christmas for kids");
		assertEquals("Christmas for kids", event.getTitle());
	}

	@Test
	public void testSetSponsor() {
		Event event = new Event();
		event.setSponsor("Red Cross");
		assertEquals("Red Cross", event.getSponsor());
	}
	
	@Test
	public void testGetInfo() {
		Event event = new Event();
		event.getInfo().addCategory("Food", 300, 500);
		assertEquals(event.getInfo().getCurrent("Food"), 300);
		assertEquals(event.getInfo().getGoal("Food"), 500);
	}

}
