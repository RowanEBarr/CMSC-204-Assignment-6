


import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class Graph_STUDENT_Test {
	private Graph graph;
	private Town t1;
	private Town t2;
	private Town t3;
	private Town t4;
	private Town t5;

	@Before
	public void setUp() throws Exception {
		graph = new Graph();
		t1 = new Town("t1");
		t2 = new Town("t2");
		t3 = new Town("t3");
		t4 = new Town("t4");
		t5 = new Town("t5");
		graph.addVertex(t1);
		graph.addVertex(t2);
		graph.addVertex(t3);
		graph.addVertex(t4);
		graph.addVertex(t5);
		graph.addEdge(t1, t2, 1, "r1");
		graph.addEdge(t1, t3, 2, "r2");
		graph.addEdge(t2, t4, 3, "r3");
		graph.addEdge(t3, t5, 4, "r4");
		graph.addEdge(t4, t5, 5, "r5");
	}

	@After
	public void tearDown() throws Exception {
		graph = null;
		t1 = null;
		t2 = null;
		t3 = null;
		t4 = null;
		t5 = null;
	}

	@Test
	public void testGetEdge() {
		assertEquals(new Road(t1,t2,1,"r1"), graph.getEdge(t1,t2));
		assertEquals(new Road(t1,t3,2,"r2"), graph.getEdge(t1,t3));
	}

	@Test
	public void testAddEdge() {
		assertEquals(false, graph.containsEdge(t2, t3));
		graph.addEdge(t2, t3, 6, "r6");
		assertEquals(true, graph.containsEdge(t2, t3));
	}

	@Test
	public void testAddVertex() {
		Town newTown = new Town("t6");
		assertEquals(false, graph.containsVertex(newTown));
		graph.addVertex(newTown);
		assertEquals(true, graph.containsVertex(newTown));
	}

	@Test
	public void testContainsEdge() {
		assertEquals(true, graph.containsEdge(t1, t2));
		assertEquals(false, graph.containsEdge(t1, t4));
	}

	@Test
	public void testContainsVertex() {
		assertEquals(true, graph.containsVertex(new Town("t1")));
		assertEquals(false, graph.containsVertex(new Town("t7")));
	}

	@Test
	public void testEdgeSet() {
		Set<Road> roads = graph.edgeSet();
		ArrayList<String> roadArrayList = new ArrayList<String>();
		for(Road road : roads)
			roadArrayList.add(road.getName());
		Collections.sort(roadArrayList);
		assertEquals("r1", roadArrayList.get(0));
		assertEquals("r2", roadArrayList.get(1));
		assertEquals("r3", roadArrayList.get(2));
		assertEquals("r4", roadArrayList.get(3));
		assertEquals("r5", roadArrayList.get(4));
	}

	@Test
	public void testEdgesOf() {
		Set<Road> roads = graph.edgesOf(t1);
		ArrayList<String> roadArrayList = new ArrayList<String>();
		for(Road road : roads)
			roadArrayList.add(road.getName());
		Collections.sort(roadArrayList);
		assertEquals("r1", roadArrayList.get(0));
		assertEquals("r2", roadArrayList.get(1));
	}
	
	@Test
	public void testRemoveEdge() {
		assertEquals(true, graph.containsEdge(t1, t2));
		graph.removeEdge(t1,t2,1,"r1");
		assertEquals(false, graph.containsEdge(t1, t2));
	}
	
	@Test
	public void testRemoveVertex() {
		assertEquals(true, graph.containsVertex(t4));
		graph.removeVertex(t4);
		assertEquals(false, graph.containsVertex(t4));
	}

	@Test
	public void testVertexSet() {
		Set<Town> roads = graph.vertexSet();
		assertEquals(true,roads.contains(t1));
		assertEquals(true, roads.contains(t2));
		assertEquals(true, roads.contains(t3));
		assertEquals(true, roads.contains(t4));
		assertEquals(true, roads.contains(t5));
	}

	 @Test
	  public void testT2toT3() {
		  String beginTown = "t2", endTown = "t3";
		  Town beginIndex=null, endIndex=null;
		  Set<Town> towns = graph.vertexSet();
		  Iterator<Town> iterator = towns.iterator();
		  while(iterator.hasNext())
		  {    	
			  Town town = iterator.next();
			  if(town.getName().equals(beginTown))
				  beginIndex = town;
			  if(town.getName().equals(endTown))
				  endIndex = town;		
		  }
		  if(beginIndex != null && endIndex != null)
		  {

			  ArrayList<String> path = graph.shortestPath(beginIndex,endIndex);
			  assertNotNull(path);
			  assertTrue(path.size() > 0);
			  assertEquals("t2 via r1 to t1 1 mi",path.get(0).trim());
			  assertEquals("t1 via r2 to t3 2 mi",path.get(1).trim());
		  }
		  else
			  fail("Town names are not valid");

	  }
	  
	  
	  @Test
	  public void testT2toT5() {
		  String beginTown = "t2", endTown = "t5";
		  Town beginIndex=null, endIndex=null;
		  Set<Town> towns = graph.vertexSet();
		  Iterator<Town> iterator = towns.iterator();
		  while(iterator.hasNext())
		  {    	
			  Town town = iterator.next();
			  if(town.getName().equals(beginTown))
				  beginIndex = town;
			  if(town.getName().equals(endTown))
				  endIndex = town;		
		  }
		  if(beginIndex != null && endIndex != null)
		  {

			  ArrayList<String> path = graph.shortestPath(beginIndex,endIndex);
			  assertNotNull(path);
			  assertTrue(path.size() > 0);
			  assertEquals("t2 via r1 to t1 1 mi",path.get(0).trim());
			  assertEquals("t1 via r2 to t3 2 mi",path.get(1).trim());
			  assertEquals("t3 via r4 to t5 4 mi",path.get(2).trim());
		  }
		  else
			  fail("Town names are not valid");

	  }
	  
	
}
