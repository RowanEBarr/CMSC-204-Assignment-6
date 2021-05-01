/** 
 * This is a class that defines a Road object which has a String name, 
 * an integer weight or distance value, and the two Town objects that it 
 * connects, and some getter and setter, toString, and comparing methods.
 * 
 * @author Rowan Barr
 *
 */
public class Road implements Comparable <Road> {
	
	private Town A;
	private Town B;
	private int distance;
	private String name;

	public Road(Town a, Town b, int distance, String name) {
		A = a;
		B = b;
		this.distance = distance;
		this.name = name;
	}

	public Road(Town a, Town b, String name) {
		A = a;
		B = b;
		distance = 1;
		this.name = name;
	}
	
	public Road(Town a, Town b) {
		A = a;
		B = b;
		distance = 1;
		name = "";
	}
	
	/** 
	 * This returns the name of the road.
	 *@return the String name of the road
	 */
	public String getName() {
		return name;
	}

	/** 
	 * This compares the String names of two roads, returns 0 if they're 
	 * equal and 1 if they aren't.
	 * @param r the road to compare to
	 *@return 0 if the road names are equal and 1 if they aren't
	 */
	public int compareTo(Road r) {
		if (name.equals(r.getName()))
		{
			return 0;
		}
		else
		{
			return 1;
		}
	}
	
	/** 
	 * This checks to see if one of the towns in the road equals the 
	 * parameter town
	 * @param t the town it checks
	 *@return true if the road contains the town and false if it doesn't
	 */
	public boolean contains (Town t)
	{
		if (A.equals(t)||B.equals(t))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	/** 
	 * This toString method returns the name of the road.
	 *@return the String name of the road
	 */
	public String toString()
	{
		return name;
	}
	/** 
	 *This returns the source town of the road.
	 *@return the source town of the road
	 */
	public Town getSource()
	{
		return A;
	}
	/** 
	 *This returns the destination town of the road.
	 *@return the destination town of the road
	 */
	public Town getDestination()
	{
		return B;
	}
	
	/** 
	 *This returns the weight of the road.
	 *@return the weight of the road
	 */
	public int getWeight()
	{
		return distance;
	}
	
	/** 
	 * This compares the sources and destinations of two roads, returns true
	 * if they're equal and false if they aren't.
	 * @param o the road to compare to
	 *@return true if the roads towns are equal and false if they aren't
	 */
	@Override
	public boolean equals (Object o)
	{
		Road r = (Road) o;
		if (A.equals(r.getSource())&&B.equals(r.getDestination()))
		{
			return true;
		}
		else if (A.equals(r.getDestination())&&B.equals(r.getSource()))
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	/** 
	 *This sets the weight of the road.
	 *@param weight the integer weight of the road
	 */
	public void setWeight(int weight) {
		distance = weight;
		
	}

}
