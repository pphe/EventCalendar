package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import model.User;

/**
 * Tests User.java
 * 
 * @author Cindy Wang
 *
 */
public class UserTest {

	@Test
	public void testUser() {
		User u = new User();
		assertNotNull(u);
	}

	@Test
	public void testSetAccountID() {
		User u = new User();
		u.setAccountID("Travis");
		assertEquals("Travis", u.getAccountID());
	}

	@Test
	public void testSetPassword() {
		User u = new User();
		u.setPassword("bestTestEver");
		assertEquals("bestTestEver", u.getPassword());
	}

	@Test
	public void testSetOrganization() {
		User u = new User();
		u.setOrganization("Travis Corporation");
		assertEquals("Travis Corporation", u.getOrganization());
	}

	@Test
	public void testSetPhone() {
		User u = new User();
		u.setPhone("253-420-9239");
		assertEquals("253-420-9239", u.getPhone());
	}

	@Test
	public void testSetEmail() {
		User u = new User();
		u.setEmail("travisIsAnAwesomeTest@uw.edu");
		assertEquals("travisIsAnAwesomeTest@uw.edu", u.getEmail());
	}

	@Test
	public void testSetRole() {
		User u = new User();
		u.setRole("Donor");
		assertEquals("Donor", u.getRole());
	}

}
