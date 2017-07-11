package tests;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import model.Data;

/**
 * Tests Data.java
 * 
 * @author Travis Arriola
 *
 */
public class DataTest {

	@Test
	public void testData() {
		Data d = new Data();
		assertNotNull(d);
	}

	@Test
	public void testAddCategory() {
		Data d = new Data();
		d.addCategory("Clothing", 200, 600);
		assertEquals(200, d.getCurrent("Clothing"));
		assertEquals(600, d.getGoal("Clothing"));
	}

	@Test
	public void testRemoveCategory() {
		Data d = new Data();
		d.addCategory("Clothing", 200, 600);
		assertEquals(200, d.getCurrent("Clothing"));
		assertEquals(600, d.getGoal("Clothing"));
		d.removeCategory("Clothing");
		assertNotEquals(200, 600);	
	}

	@Test
	public void testUpdateCategory() {
		Data d = new Data();
		d.addCategory("Clothing", 200, 600);
		assertEquals(200, d.getCurrent("Clothing"));
		assertEquals(600, d.getGoal("Clothing"));
		d.updateCategory("Clothing", 400, 800);
		assertEquals(400, d.getCurrent("Clothing"));
		assertEquals(800, d.getGoal("Clothing"));
	}

	@Test
	public void testGetCurrent() {
		Data d = new Data();
		d.addCategory("Clothing", 200, 600);
		assertEquals(200, d.getCurrent("Clothing"));		
	}

	@Test
	public void testGetGoal() {
		Data d = new Data();
		d.addCategory("Clothing", 200, 600);
		assertEquals(600, d.getGoal("Clothing"));
	}

	@Test
	public void testGetKeys() {
		Data d = new Data();
		d.addCategory("Clothing", 200, 600);
		d.addCategory("Toys", 400, 600);
		Set<String> test = new HashSet<String>();
		test.add("Clothing");
		test.add("Toys");
		Set<String> s = d.getKeys();
		assertEquals(test, s);		
	}
	
	

}
