import java.util.LinkedList;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class Town_STUDENT_Test {

	private Town town;
	private String name;
	private LinkedList linkedTowns;
	
	@Before
	public void setUp() throws Exception {
		  name = "Rockville";
		  town = new Town(name);
		  linkedTowns = new LinkedList<Town>();
		  linkedTowns.add(new Town("Silver Spring"));
		  linkedTowns.add(new Town("Takoma Park"));
		  town.setNearbyTowns(linkedTowns);
	}
	
	@After
	public void tearDown() throws Exception {
		town = null;
		name = null;
		linkedTowns = null;
	}
	
	@Test
	public void testGetName() {
		assertEquals("Rockville", town.getName());
	}
	
	@Test
	public void testToString() {
		assertEquals("Rockville", town.toString());
	}
	
	@Test
	public void testGetNearbyTowns() {
		assertEquals("Silver Spring", town.getNearbyTowns().get(0).toString());
		assertEquals("Takoma Park", town.getNearbyTowns().get(1).toString());
	}
	
	@Test
	public void testCompareTo() {
		assertEquals(0, town.compareTo(new Town("Rockville")));
		assertEquals(1, town.compareTo(town.getNearbyTowns().get(0)));
	}
	
	@Test
	public void testEquals() {
		assertTrue(town.equals(new Town("Rockville")));
		assertFalse(town.equals(town.getNearbyTowns().get(0)));
	}
}

