import java.util.LinkedList;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class Road_STUDENT_Test {

	private Town t1;
	private Town t2;
	private int distance;
	private String name;
	private Road road;
	
	@Before
	public void setUp() throws Exception {
		name = "Philadelphia";
		distance = 5;
		t1 = new Town("Silver Spring");
		t2 = new Town ("Takoma Park");
		road = new Road(t1,t2,distance, name);
	}
	
	@After
	public void tearDown() throws Exception {
		road = null;
		name = null;
		distance = 0;
		t1 = null;
		t2 = null;	
	}
	
	@Test
	public void testGetName() {
		assertEquals("Philadelphia", road.getName());
	}
	
	@Test
	public void testToString() {
		assertEquals("Philadelphia", road.toString());
	}
	
	@Test
	public void testContains() {
		assertTrue(road.contains(new Town("Silver Spring")));
		assertFalse(road.contains(new Town("Rockville")));
	}
	
	@Test
	public void testCompareTo() {
		assertEquals(0, road.compareTo(new Road(t1,t2,distance, "Philadelphia")));
		assertEquals(1, road.compareTo(new Road(t1,t2,distance, "Georgia")));
	}
	
	@Test
	public void testEquals() {
		assertTrue(road.equals(new Road(new Town("Silver Spring"),new Town("Takoma Park"))));
		assertFalse(road.equals(new Road(new Town("Silver Spring"),new Town("Rockville"))));
	}
	
	@Test
	public void testGetWeight() {
		assertEquals(road.getWeight(), 5);
	}
	
	@Test
	public void testSetWeight() {
		road.setWeight(4);
		assertEquals(road.getWeight(), 4);
	}
	
	@Test
	public void testGetSource() {
		assertEquals(road.getSource(), new Town("Silver Spring"));
	}
	
	@Test
	public void testGetDestination() {
		assertEquals(road.getDestination(), new Town("Takoma Park"));
	}
}

