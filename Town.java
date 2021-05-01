import java.util.LinkedList;
/** 
 * This is a class that defines a Town object which has a String name, 
 * and a LinkedList of all the Towns it's connected to by a Road object. 
 * It also has getter and setter, toString, and comparing methods.
 * 
 * @author Rowan Barr
 *
 */
public class Town implements Comparable<Town>{

	private String name = "";
	private LinkedList nearbyTowns = new LinkedList<Town>();
	
	public Town(String string) {
		name = string;
	}
	
	public Town(Town T) {
		name = T.getName();
		nearbyTowns = T.getNearbyTowns();
	}

	/** 
	 *This returns the list of adjacent towns.
	 *@return the list of adjacent towns
	 */
	public LinkedList<Town> getNearbyTowns() {
		return nearbyTowns;
	}
	
	/** 
	 *This sets the list of adjacent towns.
	 *@param linkedTowns the list of adjacent towns
	 */
	public void setNearbyTowns(LinkedList<Town> linkedTowns) {
		nearbyTowns = linkedTowns;
	}

	/** 
	 *This returns the string name of the town.
	 *@return the name of the town
	 */
	public String getName() {
		return name;
	}

	/** 
	 *This compares the names of two towns, and returns 0 if they are equal,
	 *1 if they aren't.
	 *@return 0 if the names of the towns are equal, 1 if they aren't
	 */
	public int compareTo(Town t) {
		if (name.contentEquals(t.getName()))
		{
			return 0;
		}
		else
		{
			return 1;
		}
	}
	
	/** 
	 *This toString method returns the string name of the town.
	 *@return the name of the town
	 */
	public String toString()
	{
		return name;
	}
	
	/** 
	 *This compares the names of two towns, and returns true if they are equal,
	 *false if they aren't.
	 *@return true if the names of the towns are equal, false if they aren't
	 */
	@Override
	public boolean equals(Object o)
	{
		Town t = (Town) o;
		if (name.contentEquals(t.getName()))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	/** 
	 *This returns a hashcode for the town object.
	 *@return a hashcode for the town object
	 */
	public int hashCode()
	{
		int e = 0;
		for (int i = 0; i <name.length();i++)
		{
			e +=name.charAt(i);
		}
		int a = e % 200;
		return a;
			
	}

}
