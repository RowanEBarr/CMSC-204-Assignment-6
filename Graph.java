import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Random;
import java.util.Set;
/** 
 * This is a class that adds, subtracts and returns towns and roads from two lists
 * and maintains a graph of the connections between each town using an adjacency
 * list where each town has a list of all the other towns it's connected to.
 * It also contains an algorithm that calculates the shortest distance between
 * two connected towns based on the total weight of the roads between the towns.
 * 
 * @author Rowan Barr
 *
 */
public class Graph implements GraphInterface<Town, Road>{

	LinkedList<Town> townList = new LinkedList<Town>();
	LinkedList<Road> roadList = new LinkedList<Road>();
	LinkedList[] matrix;
	Random rand = new Random();
	 /**
     * Returns an edge connecting source vertex to target vertex if such
     * vertices and such edge exist in this graph. Otherwise returns
     * null. If any of the specified vertices is null
     * returns null
     *
     * In undirected graphs, the returned edge may have its source and target
     * vertices in the opposite order.
     *
     * @param sourceVertex source vertex of the edge.
     * @param destinationVertex target vertex of the edge.
     *
     * @return an edge connecting source vertex to target vertex.
     */
	public Road getEdge(Town sourceVertex, Town destinationVertex) {
		if (sourceVertex==null)
		{
			return null;
		}
		if (destinationVertex==null)
		{
			return null;
		}
		if (!containsEdge(sourceVertex, destinationVertex))
		{
			return null;
		}
		for (int i = 0; i <roadList.size();i++)
		{
			if (roadList.get(i).getSource().equals(sourceVertex)
					&&roadList.get(i).getDestination().equals(destinationVertex))
			{
				return roadList.get(i);
			}
			else if (roadList.get(i).getSource().equals(destinationVertex)
					&&roadList.get(i).getDestination().equals(sourceVertex))
			{
				return roadList.get(i);
			}
		}
		return null;
	}
	/**
     * Returns an vertex if it exists in the graph based on it's town name,
     * otherwise returns null.
     *
     * @param s String name of the town.
     *
     * @return the town, or null if it doesn't exist.
     */
	public Town getVertex(String s) {
		for (int i = 0; i <townList.size();i++)
		{
			if (townList.get(i).getName().contentEquals(s))
			{
				return townList.get(i);
			}
		}
		return null;
	}
	 /**
     * Creates a new edge in this graph, going from the source vertex to the
     * target vertex, and returns the created edge. 
     * 
     * The source and target vertices must already be contained in this
     * graph. If they are not found in graph IllegalArgumentException is
     * thrown.
     *
     *
     * @param sourceVertex source vertex of the edge.
     * @param destinationVertex target vertex of the edge.
     * @param weight weight of the edge
     * @param description description for edge
     *
     * @return The newly created edge if added to the graph, otherwise null.
     *
     * @throws IllegalArgumentException if source or target vertices are not
     * found in the graph.
     * @throws NullPointerException if any of the specified vertices is null.
     */
	public Road addEdge(Town sourceVertex, Town destinationVertex, int weight, String description) throws IllegalArgumentException, NullPointerException {
		Road r = new Road(sourceVertex, destinationVertex, weight,description);
		if (!containsVertex(sourceVertex))
		{
			throw new IllegalArgumentException();
		}
		if (!containsVertex(destinationVertex))
		{
			throw new IllegalArgumentException();
		}
		if (sourceVertex==null)
		{
			throw new NullPointerException();
		}
		if (destinationVertex==null)
		{
			throw new NullPointerException();
		}
		if (containsEdge(sourceVertex, destinationVertex))
		{
			return null;
		}
		
		else
		{
			roadList.add(r);
			for (int i = 0; i<townList.size();i++)
			{
				if (townList.get(i).equals(sourceVertex))
				{
					townList.get(i).getNearbyTowns().add(destinationVertex);
				}
				else if (townList.get(i).equals(destinationVertex))
				{
					townList.get(i).getNearbyTowns().add(sourceVertex);
				}
			}
			return r;
		}
	}
	 /**
     * Adds the specified vertex, v, to this graph if
     * this graph contains no vertex u such that
     * u.equals(v). If this graph already contains such vertex, the call
     * leaves this graph unchanged and returns false. 
     * 
     * @param v vertex to be added to this graph.
     *
     * @return true if this graph did not already contain the specified
     * vertex.
     *
     * @throws NullPointerException if the specified vertex is null.
     */
	public boolean addVertex(Town v) throws NullPointerException {
		if(v==null)
		{
		
			throw new NullPointerException();
		}
		
		for (int i = 0; i <townList.size();i++)
		{
			if (v.equals(townList.get(i)))
			{
				return false;
			}
		}
		townList.add(v);
		return true;
	}
	  /**
     * Returns true if and only if this graph contains an edge going
     * from the source vertex to the target vertex. In undirected graphs the
     * same result is obtained when source and target are inverted. If any of
     * the specified vertices does not exist in the graph, or if is
     * null, returns false.
     *
     * @param sourceVertex source vertex of the edge.
     * @param destinationVertex target vertex of the edge.
     *
     * @return true if this graph contains the specified edge.
     */
	public boolean containsEdge(Town sourceVertex, Town destinationVertex) {
		Road t = new Road(sourceVertex, destinationVertex);
		if (!containsVertex(sourceVertex))
		{
			return false;
		}
		if (!containsVertex(destinationVertex))
		{
			return false;
		}
		if (sourceVertex==null)
		{
			return false;
		}
		if (destinationVertex==null)
		{
			return false;
		}
		for (int i = 0; i <roadList.size();i++)
		{
			if (roadList.get(i).equals(t))
			{
				return true;
			}
		}
		return false;
	}
	  /**
     * Returns true if and only if this graph contains a
     * vertex u such that u.equals(v). If the
     * specified vertex is null returns false.
     *
     * @param v vertex whose presence in this graph is to be tested.
     *
     * @return true if this graph contains the specified vertex.
     */
	public boolean containsVertex(Town v) {
		if(v==null)
		{
			return false;
		}
		
		for (int i = 0; i <townList.size();i++)
		{
			if (v.equals(townList.get(i)))
			{
				return true;
			}
		}
		return false;
	}
	/**
     * Returns a set of the edges contained in this graph. The set is backed by
     * the graph, so changes to the graph are reflected in the set. If the graph
     * is modified while an iteration over the set is in progress, the results
     * of the iteration are undefined.
     *
     *
     * @return a set of the edges contained in this graph.
     */
	public Set<Road> edgeSet() {
		Set<Road> edgeSet = new HashSet<Road>();
		for (int i = 0; i <roadList.size();i++)
		{
			edgeSet.add(roadList.get(i));
		}
		return edgeSet;
	}
	 /**
     * Returns a set of all edges touching the specified vertex (also
     * referred to as adjacent vertices). If no edges are
     * touching the specified vertex returns an empty set.
     *
     * @param vertex the vertex for which a set of touching edges is to be
     * returned.
     *
     * @return a set of all edges touching the specified vertex.
     *
     * @throws IllegalArgumentException if vertex is not found in the graph.
     * @throws NullPointerException if vertex is null.
     */
	public Set<Road> edgesOf(Town vertex) throws IllegalArgumentException, NullPointerException{
		Set<Road> edgeSet = new HashSet<Road>();
		if (vertex == null)
		{
			throw new NullPointerException();
		}
		if (!containsVertex(vertex))
		{
			throw new IllegalArgumentException();
		}
		for (int o = 0; o<roadList.size(); o++)
		{
			if (roadList.get(o).getSource().equals(vertex))
			{
				edgeSet.add(roadList.get(o));
			}
			else if (roadList.get(o).getDestination().equals(vertex))
			{
				edgeSet.add(roadList.get(o));
			}
			
		}
		return edgeSet;
		
	}
	 /**
     * Removes an edge going from source vertex to target vertex, if such
     * vertices and such edge exist in this graph. 
     * 
     * If weight >- 1 it must be checked
     * If description != null, it must be checked 
     * 
     * Returns the edge if removed
     * or null otherwise.
     *
     * @param sourceVertex source vertex of the edge.
     * @param destinationVertex target vertex of the edge.
     * @param weight weight of the edge
     * @param description description of the edge
     *
     * @return The removed edge, or null if no edge removed.
     */
	public Road removeEdge(Town sourceVertex, Town destinationVertex, int weight, String description) {
		Road r = new Road(sourceVertex, destinationVertex, weight, description);
		if (sourceVertex==null)
		{
			return null;
		}
		if (destinationVertex==null)
		{
			return null;
		}
		if (!containsEdge(sourceVertex,destinationVertex))
		{
			return null;
		}
		if (weight < 0)
		{
			return null;
		}
		if (description == null)
		{
			return null;
		}
		else
		{
			for (int m = 0; m<roadList.size(); m++)
			{
				if ((roadList.get(m)).equals(r))
				{
					roadList.remove(m);
				}
				
			}
			
			
			for (int o = 0; o<townList.size(); o++)
			{
				if (townList.get(o).getNearbyTowns().get(0).equals(sourceVertex))
				{
					townList.get(o).getNearbyTowns().remove(destinationVertex);
				}
				else if (townList.get(o).getNearbyTowns().get(0).equals(destinationVertex))
				{
					townList.get(o).getNearbyTowns().remove(sourceVertex);
				}
				
			}
			return r;
		}
	}
	 /**
     * Removes the specified vertex from this graph including all its touching
     * edges if present. More formally, if the graph contains a vertex 
     * u such that u.equals(v), the call removes all edges
     * that touch u and then removes u itself. If no
     * such u is found, the call leaves the graph unchanged.
     * Returns true if the graph contained the specified vertex. (The
     * graph will not contain the specified vertex once the call returns).
     *
     * If the specified vertex is null returns false.
     *
     * @param v vertex to be removed from this graph, if present.
     *
     * @return true if the graph contained the specified vertex;
     * false otherwise.
     */
	public boolean removeVertex(Town v) {
		if (v==null)
		{
			return false;
		}
		if (!containsVertex(v))
		{
			return false;
		}
		else
		{
			for (int m = 0; m<roadList.size(); m++)
			{
				if ((roadList.get(m)).getSource().equals(v)||(roadList.get(m)).getDestination().equals(v))
				{
					roadList.remove(m);
				}
				
			}
			
			for (int n = 0; n<townList.size(); n++)
			{
				if (townList.get(n).equals(v))
				{
					townList.remove(n);
				}
				else if (townList.get(n).getNearbyTowns().contains(v))
				{
					townList.get(n).getNearbyTowns().remove(v);
				}
				
			}
			return true;
		}
	}
	 /**
     * Returns a set of the vertices contained in this graph. The set is backed
     * by the graph, so changes to the graph are reflected in the set. If the
     * graph is modified while an iteration over the set is in progress, the
     * results of the iteration are undefined.
     *
     *
     * @return a set view of the vertices contained in this graph.
     */
	public Set<Town> vertexSet() {
		Set<Town> vertexSet = new HashSet<Town>();
		for (int i = 0; i <townList.size();i++)
		{
			vertexSet.add(townList.get(i));
		}
		return vertexSet;
	}
	 /**
     * Find the shortest path from the sourceVertex to the destinationVertex
     * call the dijkstraShortestPath with the sourceVertex
     * @param sourceVertex starting vertex
     * @param destinationVertex ending vertex
     * @return An arraylist of Strings that describe the path from sourceVertex
     * to destinationVertex
     * They will be in the format: startVertex "via" Edge "to" endVertex weight
	 * As an example: if finding path from Vertex_1 to Vertex_10, the ArrayList<String>
	 * would be in the following format(this is a hypothetical solution):
	 * Vertex_1 via Edge_2 to Vertex_3 4 (first string in ArrayList)
	 * Vertex_3 via Edge_5 to Vertex_8 2 (second string in ArrayList)
	 * Vertex_8 via Edge_9 to Vertex_10 2 (third string in ArrayList)
     */ 
	public ArrayList<String> shortestPath(Town sourceVertex, Town destinationVertex) {
		dijkstraShortestPath(sourceVertex);
		ArrayList<String> matrixCNVRT = new ArrayList<String>();
		while (!sourceVertex.equals(destinationVertex))
		{
			for (int i = 0; i < matrix.length; i++) //for every town in the matrix
			{
				if (!matrix[i].get(0).equals(sourceVertex))
				{
					if (matrix[i].get(0).equals(destinationVertex)) //if it equals the destination index
					{
						matrixCNVRT.add(matrix[i].get(2).toString() + " via " + getEdge((Town) matrix[i].get(0), (Town) matrix[i].get(2)).toString() + " to " + matrix[i].get(0).toString()+ " "+getEdge((Town) matrix[i].get(0), (Town) matrix[i].get(2)).getWeight() + " mi");
						destinationVertex = (Town) matrix[i].get(2);
					}
				}
				
			}
		}
		ArrayList<String> shortestPath = new ArrayList<String>();
		for (int i = (matrixCNVRT.size() - 1); i >= 0; i--) 
		{
			shortestPath.add(matrixCNVRT.get(i));
		}
		return shortestPath;
		
	}
	/**
     * Dijkstra's Shortest Path Method.  Internal structures are built which
     * hold the ability to retrieve the path, shortest distance from the
     * sourceVertex to all the other vertices in the graph, etc.
     * @param sourceVertex the vertex to find shortest path from
     * 
     */
	public void dijkstraShortestPath(Town sourceVertex) 
	{
		LinkedList<Town> townList2 = new LinkedList<Town>(); //Creates a copy of the list of towns (in case of a disjointed graph)
		for (int i = 0; i < townList.size(); i++)
		{
			townList2.add(townList.get(i)); //Copying over the data
		}
		int size = townList2.size();
		for (int i = 0; i < size; i++) //for each element in the copied list of towns
		{
			if (townList2.get(i)!=sourceVertex) //if it isn't our source vertex
			{
				if (!connectsTo(sourceVertex,townList2.get(i))) //and it doesn't connect to our source vertex
				{
					townList2.remove(townList2.get(i)); //we don't want it in our path algorithm
					i--;
					size--;
				}
			}
			
		}
		matrix = new LinkedList[townList2.size()]; //list of town, distance to start town, previous town
		ArrayList<Town> unvTowns = new ArrayList<Town>(); //unvisited towns	
		ArrayList<Town> vTowns = new ArrayList<Town>();	//visited towns
		int minTownIndex = 0; //index of the next town to visit in the matrix
		Town minTown = null; //the next town to be visited
		LinkedList<Town> minAdjTowns = new LinkedList<Town>(); //the towns adjacent to the next town to be visited
		LinkedList<Town> adjTowns = new LinkedList<Town>(); //the towns adjacent to all the already visited towns
		
		for (int i = 0; i < townList2.size(); i++) //for every town
		{
			matrix[i] = new LinkedList(); //initialize the list at i's position in the matrix
			matrix[i].add(townList2.get(i)); //add the town to the list
			matrix[i].add(-1); //add a placeholder for the towns weight
			matrix[i].add(null); //add a placeholder for its previous town
			unvTowns.add(townList2.get(i)); //add the town to the list of unvisited towns
		}

		for (int i = 0; i < matrix.length; i++) //for every town
		{
			if (matrix[i].get(0).equals(sourceVertex)) //if it equals the source index
			{
				minTownIndex = i; //the index of the next town to visit equals that town
				matrix[i].set(1, 0); //set that towns weight to zero
			}
		}
		
		minTown = (Town) matrix[minTownIndex].get(0); //sets the next town to be visited
		vTowns.add((Town) matrix[minTownIndex].get(0)); //adds it to the list of visited towns
		unvTowns.set(minTownIndex, null); //removes it from the list of unvisited towns
		minAdjTowns = minTown.getNearbyTowns(); //sets the list of towns adjacent to the next town to be visited
		adjTowns.addAll(minAdjTowns); //adds all those towns to the list of all towns adjacent to all the already visited towns
		
		for (int h = 0; h < adjTowns.size(); h++) // for each town in towns adjacent to all the already visited towns
		{
			for (int i = 0; i < matrix.length; i++) //for each town in the matrix
			{
				
				
				if (adjTowns.get(h).getName().equals(((Town)matrix[i].get(0)).getName())) //if the two towns are equal
				{
					if ((int) matrix[i].get(1)==-1) //if the weight of the town is the placeholder
					{
						matrix[i].set(1, getEdge(minTown, adjTowns.get(h)).getWeight()); //set the weight of the town
						matrix[i].set(2, minTown); //set the previous town
					}
				}
			}
			
		}
		
		while (vTowns.size()<townList2.size()) //while not all towns have been visited
		{
			while (vTowns.contains(matrix[minTownIndex].get(0))) //while the minIndex is still at an already visited town in the matrix
			{ 
				
				minTownIndex = rand.nextInt(matrix.length);//randomizes it
				while ((int) matrix[minTownIndex].get(1) == -1) //while the new minTownIndex corresponds to a town with a placeholder weight
				{
					minTownIndex = rand.nextInt(matrix.length);//randomizes it
				
				}
			}
			for (Town adjTown: adjTowns)  // for each town in towns adjacent to all the already visited towns
			{
				if (!vTowns.contains(adjTown)) //if it isn't in the list of visited towns
				{
					
					for (int i = 0; i < matrix.length; i++) //for each town in the matrix
					{
						if (adjTown.equals(matrix[i].get(0))) //if the two towns are equal
						{
							
							if ((int) matrix[i].get(1) < (int) matrix[minTownIndex].get(1)) //if the weight of this town is less then the weight of the town at the minimum index
							{
								minTownIndex = i; //this becomes the minimum index
								
							}
						}
					}
				}
			}
			minTown = (Town) matrix[minTownIndex].get(0); //sets the next town to be visited
			vTowns.add((Town) matrix[minTownIndex].get(0)); //adds it to the list of visited towns
			unvTowns.set(minTownIndex, null); //removes it from the list of unvisited towns
			minAdjTowns = minTown.getNearbyTowns(); //sets the list of towns adjacent to the next town to be visited
			
			for (int i = 0; i < minAdjTowns.size(); i++) // for each town in towns adjacent to the next town
			{
				if (!adjTowns.contains(minAdjTowns.get(i))&&!vTowns.contains(minAdjTowns.get(i))) //if this town isn't in towns adjacent to all the already visited towns and hasn't already been visited
				{
					adjTowns.add(minAdjTowns.get(i)); //add it to the list
				}
			}
		
			for (int h = 0; h < minAdjTowns.size(); h++) // for each town in towns adjacent to the next town
			{
				for (int i = 0; i < matrix.length; i++) //for each town in the matrix
				{
					if (minAdjTowns.get(h).getName().equals(((Town) matrix[i].get(0)).getName())) //if the two towns are equal
					{
						int weight = 0;
						weight += (int) matrix[minTownIndex].get(1); //weight equals the weight of the previous town
						weight += getEdge(minTown, minAdjTowns.get(h)).getWeight(); //plus the weight of the edge between the previous town and this town
						if (containsEdge(minAdjTowns.get(h), sourceVertex)&&getEdge(minAdjTowns.get(h), sourceVertex).getWeight()<weight)
						{
							weight = getEdge(minAdjTowns.get(h), sourceVertex).getWeight();
							matrix[i].set(1, weight); //set the weight of the town
							matrix[i].set(2, sourceVertex); //set the previous town
						}
						if (matrix[i].get(2)==null&&!matrix[i].get(0).equals(sourceVertex)) //if the previous town is the placeholder town
						{
							matrix[i].set(1, weight); //set the weight of the town
							matrix[i].set(2, minTown); //set the previous town
						}
						if ((int) matrix[i].get(1)>weight) //if the path to the town is faster than the previously stored path
						{
							
							matrix[i].set(1, weight); //set the weight of the town
							matrix[i].set(2, minTown); //set the previous town
							
						}
					}
				}
			}	
			
		}	
	}
	 /**
     * Checks to see if there is a path between two towns that is one,
     * two, three or four roads long. Returns true if there is, false if there
     * isn't.
     *@param t1 starting town
     *@param t2 ending town
     * @return Returns true if there is a path between the towns, false if there
     * isn't.
     */
	public boolean connectsTo (Town t1, Town t2) {
		
		if (!containsVertex(t1))
		{
			return false;
		}
		if (!containsVertex(t2))
		{
			return false;
		}
		if (containsEdge(t1, t2))
		{
			return true;
		}
		else {
			Town town1;
			Town town2;
			Town town3;
			Town town4;
			
			for (int i = 0; i < t1.getNearbyTowns().size(); i++)
			{
				town1 = getVertex(t1.getNearbyTowns().get(i).getName());
				for (int j = 0; j < town1.getNearbyTowns().size(); j++)
				{
					town2 = getVertex(town1.getNearbyTowns().get(j).getName());
					if (town2.equals(t2))
					{
						return true;
					}
					for (int k = 0; k < town2.getNearbyTowns().size(); k++)
					{
						town3 = getVertex(town2.getNearbyTowns().get(k).getName());
						if (town3.equals(t2))
						{
							return true;
						}
						for (int l = 0; l < town3.getNearbyTowns().size(); l++)
						{
							town4 = getVertex(town3.getNearbyTowns().get(l).getName());
							if (town4.equals(t2))
							{
								return true;
							}
						}
					}
				}	
			}
			return false;
		}
			
	}
}
	