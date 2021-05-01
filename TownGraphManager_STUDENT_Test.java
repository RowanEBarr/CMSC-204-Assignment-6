


import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class TownGraphManager_STUDENT_Test {
	private TownGraphManagerInterface graph;
	  
	@Before
	public void setUp() throws Exception {
		graph = new TownGraphManager();
		graph.addTown("t1");
		graph.addTown("t2");
		graph.addTown("t3");
		graph.addTown("t4");
		graph.addTown("t5");
		graph.addRoad("t1", "t2", 1, "r1");
		graph.addRoad("t1", "t3", 2, "r2");
		graph.addRoad("t2", "t4", 3, "r3");
		graph.addRoad("t3", "t5", 4, "r4");
		graph.addRoad("t4", "t5", 5, "r5");
		 
	}

	@After
	public void tearDown() throws Exception {
		graph = null;
	}

	@Test
	public void testAddRoad() {
		ArrayList<String> roads = graph.allRoads();
		assertEquals("r1", roads.get(0));
		assertEquals("r2", roads.get(1));
		assertEquals("r3", roads.get(2));
		assertEquals("r4", roads.get(3));
		graph.addRoad("t1", "t5", 6,"r6");
		roads = graph.allRoads();
		assertEquals("r6", roads.get(5));
		
	}

	@Test
	public void testGetRoad() {
		assertEquals("r1", graph.getRoad("t1", "t2"));
		assertEquals("r3", graph.getRoad("t2", "t4"));
	}

	@Test
	public void testAddTown() {
		assertEquals(false, graph.containsTown("t6"));
		graph.addTown("t6");
		assertEquals(true, graph.containsTown("t6"));
	}
	
	@Test
	public void testDisjointGraph() {
		assertEquals(false, graph.containsTown("t6"));
		graph.addTown("t6");
		ArrayList<String> path = graph.getPath("t1","t6");
		assertFalse(path.size() > 0);
	}

	@Test
	public void testContainsTown() {
		assertEquals(true, graph.containsTown("t2"));
		assertEquals(false, graph.containsTown("t6"));
	}

	@Test
	public void testContainsRoadConnection() {
		assertEquals(true, graph.containsRoadConnection("t1", "t3"));
		assertEquals(false, graph.containsRoadConnection("t2", "t3"));
	}

	@Test
	public void testAllRoads() {
		ArrayList<String> roads = graph.allRoads();
		assertEquals("r1", roads.get(0));
		assertEquals("r2", roads.get(1));
		assertEquals("r3", roads.get(2));
		assertEquals("r4", roads.get(3));
		assertEquals("r5", roads.get(4));
	}

	@Test
	public void testDeleteRoadConnection() {
		assertEquals(true, graph.containsRoadConnection("t1", "t2"));
		graph.deleteRoadConnection("t1", "t2", "r1");
		assertEquals(false, graph.containsRoadConnection("t1", "t2"));
	}

	@Test
	public void testDeleteTown() {
		assertEquals(true, graph.containsTown("t2"));
		graph.deleteTown("t2");
		assertEquals(false, graph.containsTown("t2"));
	}
	
	@Test
	public void testAllTowns() {
		ArrayList<String> roads = graph.allTowns();
		assertEquals("t1", roads.get(0));
		assertEquals("t2", roads.get(1));
		assertEquals("t3", roads.get(2));
		assertEquals("t4", roads.get(3));
		assertEquals("t5", roads.get(4));
	}

	@Test
	public void testGetPathA() {
		ArrayList<String> path = graph.getPath("t1","t5");
		  assertNotNull(path);
		  assertTrue(path.size() > 0);
		  assertEquals("t1 via r2 to t3 2 mi",path.get(0).trim());
		  assertEquals("t3 via r4 to t5 4 mi",path.get(1).trim());

	}
	
	@Test
	public void testGetPathB() {
		ArrayList<String> path = graph.getPath("t2","t5");
		  assertNotNull(path);
		  assertTrue(path.size() > 0);
		  System.out.println(path.toString());
		  assertEquals("t2 via r1 to t1 1 mi",path.get(0).trim());
		  assertEquals("t1 via r2 to t3 2 mi",path.get(1).trim());
		  assertEquals("t3 via r4 to t5 4 mi",path.get(2).trim());
	}
	


}
