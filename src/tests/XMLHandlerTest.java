package tests;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import model.Event;
import model.User;
import utility.XMLHandler;

/**
 * Tests XMLHandler.java
 * 
 * @author Travis Arriola
 */
public class XMLHandlerTest {
	
	@Test
	public void testReadEventXML() {
		// reads SampleEvents.xml
		List<Event> events = XMLHandler.readEventXML();
		assertEquals("2016-12-08", events.get(0).getDate());
		assertEquals("08:00", events.get(0).getTime());
		assertEquals("Seattle", events.get(0).getLocation());
		assertEquals("Red Cross", events.get(0).getSponsor());
		assertEquals("Clothing", events.get(0).getCategory());
		assertEquals("Red Cross Clothing Drive", events.get(0).getTitle());
		assertEquals("2016-12-08", events.get(0).getDate());
		assertEquals(500, events.get(0).getInfo().getCurrent("Clothing"));
		assertEquals(1000, events.get(0).getInfo().getGoal("Clothing"));
	}

	@Test
	public void testReadUserXML() {
		// reads SampleUsers.xml
		List<User> users = XMLHandler.readUserXML();
		assertEquals("donor", users.get(0).getAccountID());
		assertEquals("donor", users.get(0).getPassword());
		assertEquals("UW-T", users.get(0).getOrganization());
		assertEquals("555-555-5555", users.get(0).getPhone());
		assertEquals("husky@uw.edu", users.get(0).getEmail());
		assertEquals("donor", users.get(0).getRole());
	}

	@Test
	public void testWriteEventXML() {
		// writes to Events.xml
		List<Event> events = XMLHandler.readEventXML();
		XMLHandler.writeEventXML(events);
		List<Event> eventsRead = XMLHandler.readEventXML();
		assertEquals("2016-12-08", eventsRead.get(0).getDate());
		assertEquals("08:00", eventsRead.get(0).getTime());
		assertEquals("Seattle", eventsRead.get(0).getLocation());
		assertEquals("Red Cross", eventsRead.get(0).getSponsor());
		assertEquals("Clothing", eventsRead.get(0).getCategory());
		assertEquals("Red Cross Clothing Drive", eventsRead.get(0).getTitle());
		assertEquals("2016-12-08", eventsRead.get(0).getDate());
		assertEquals(500, eventsRead.get(0).getInfo().getCurrent("Clothing"));
		assertEquals(1000, eventsRead.get(0).getInfo().getGoal("Clothing"));		
	}

	@Test
	public void testWriteUserXML() {
		// writes to Users.xml
		List<User> users = XMLHandler.readUserXML();
		XMLHandler.writeUserXML(users);
		List<User> usersRead = XMLHandler.readUserXML();
		assertEquals("donor", usersRead.get(0).getAccountID());
		assertEquals("donor", usersRead.get(0).getPassword());
		assertEquals("UW-T", usersRead.get(0).getOrganization());
		assertEquals("555-555-5555", usersRead.get(0).getPhone());
		assertEquals("husky@uw.edu", usersRead.get(0).getEmail());
		assertEquals("donor", usersRead.get(0).getRole());
	}	
}
