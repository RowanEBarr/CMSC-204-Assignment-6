import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
/** 
 * This is a class that converts the inputs from the client interface into
 * something that can go into the graph. It's an intermediary class.
 * 
 * @author Rowan Barr
 *
 */
public class TownGraphManager implements TownGraphManagerInterface {
	
	Graph townGraph = new Graph();

	/**
	 * Adds a road with 2 towns, a road weight and a road name
	 * @param town1 name of town 1 
	 * @param town2 name of town 2
	 * @param weight weight of road
	 * @param roadName name of road
	 * @return true if the road was added successfully
	 */
	public boolean addRoad(String town1, String town2, int weight, String roadName) {
		Town t1 = new Town(town1);
		Town t2 = new Town(town2);
		Road r = townGraph.addEdge(t1, t2, weight, roadName);
		if (r!=null)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	/**
	 * Returns the name of the road that both towns are connected through
	 * @param town1 name of town 1 
	 * @param town2 name of town 2 
	 * @return name of road if town 1 and town2 have a connecting road, returns null if not
	 */
	public String getRoad(String town1, String town2)  {
		Town t1 = new Town(town1);
		Town t2 = new Town(town2);
		String s = null;
		if (townGraph.getEdge(t1, t2)!=null)
		{
			s = townGraph.getEdge(t1, t2).getName();
			return s;
		}
		else
		{
			return null;
		}
	}

	/**
	 * Adds a town to the graph
	 * @param v the town's name  
	 * @return true if the town was successfully added, false if not
	 */
	public boolean addTown(String v) throws NullPointerException {
		Town town = new Town(v);
		return townGraph.addVertex(town);
	}

	/**
	 * Determines if a town is already in the graph
	 * @param v the town's name 
	 * @return true if the town is in the graph, false if not
	 */
	public boolean containsTown(String v) {
		Town town = new Town(v);
		return townGraph.containsVertex(town);
	}

	/**
	 * Determines if a road is in the graph
	 * @param town1 name of town 1 
	 * @param town2 name of town 2 
	 * @return true if the road is in the graph, false if not
	 */
	public boolean containsRoadConnection(String town1, String town2) {
		Town t1 = new Town(town1);
		Town t2 = new Town(town2);
		return townGraph.containsEdge(t1, t2);
	}

	/**
	 * Creates an arraylist of all road titles in sorted order by road name
	 * @return an arraylist of all road titles in sorted order by road name
	 */
	public ArrayList<String> allRoads() {
		ArrayList <String> roads = new ArrayList <String>();
		Object [] roads2 = townGraph.edgeSet().toArray();
		for (int i = 0; i < roads2.length; i++)
		{
			roads.add(roads2[i].toString());
		}
		Collections.sort(roads);
		return roads;
	}

	/**
	 * Deletes a road from the graph
	 * @param town1 name of town 1 
	 * @param town2 name of town 2 
	 * @param roadName the road name
	 * @return true if the road was successfully deleted, false if not
	 */
	public boolean deleteRoadConnection(String town1, String town2, String road) {
		Town t1 = new Town(town1);
		Town t2 = new Town(town2);
		Road r = townGraph.getEdge(t1, t2);
		Road s = townGraph.removeEdge(t1, t2, r.getWeight(), road);
		if (s!=null)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	/**
	 * Deletes a town from the graph
	 * @param v name of town 
	 * @return true if the town was successfully deleted, false if not
	 */
	public boolean deleteTown(String v) {
		Town town = new Town(v);
		return townGraph.removeVertex(town);
	}

	/**
	 * Creates an arraylist of all towns in alphabetical order 
	 * @return an arraylist of all towns in alphabetical order 
	 */
	public ArrayList<String> allTowns() {
		ArrayList <String> towns = new ArrayList <String>();
		Object [] towns2 = townGraph.vertexSet().toArray();
		for (int i = 0; i < towns2.length; i++)
		{
			towns.add(towns2[i].toString());
		}
		Collections.sort(towns);
		return towns;
	}

	/**
	 * Returns the shortest path from town 1 to town 2
	 * @param town1 name of town 1 
	 * @param town2 name of town 2 
	 * @return an Arraylist of roads connecting the two towns together, null if the
	 * towns have no path to connect them.
	 */
	public ArrayList<String> getPath(String town1, String town2) {
		Town t1 = getTown(town1);
		Town t2 = getTown(town2);
		ArrayList<String> path = new ArrayList<String>();
		if (townGraph.connectsTo(t1,t2))
		{
			path = townGraph.shortestPath(townGraph.getVertex(town1), townGraph.getVertex(town2));
		}
		return path;
		
	}

	/**
	 * Gets a town with a given name
	 * @param name the town's name 
	 * @return the Town specified by the name, or null if town does not exist
	 */
	public Town getTown(String name) {
		return townGraph.getVertex(name);
	}

	/**
	 * Reads strings from a file and parses them into road names and weights
	 * and town names, then adds them to the graph
	 * @param input the file to read from
	 */
	public void populateTownGraph(File input) throws FileNotFoundException, IOException {
		Scanner sc;
		try 
		{
			if (input!=null) {
				sc = new Scanner(input);
			}
			else
			{
				throw new FileNotFoundException();
			}
		}
		catch (IOException E)
		{
			throw new IOException();
		}
		
		Town t1;
		Town t2;
		int weight;
		String fileLine;
		String[] roadTowns;
		String[] roadWeight;
		while (sc.hasNextLine())
		{
			fileLine= sc.nextLine();
			roadTowns = fileLine.split(";");
			roadWeight = roadTowns[0].split(",");
			weight = Integer.parseInt(roadWeight[1]);
			t1 = new Town(roadTowns[1]);
			t2 = new Town(roadTowns[2]);
			townGraph.addVertex(t1);
			townGraph.addVertex(t2);
			townGraph.addEdge(t1, t2, weight, roadWeight[0]);
			
		}
		
	}
	
	

}
