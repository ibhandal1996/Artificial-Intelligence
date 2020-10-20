import java.util.*;

public class MapBox 
{
	double xmin = 0;
	double xmax = 0;
	double ymin = 0;
	double ymax = 0;
	public List<Location>locations = new ArrayList<Location>();
	public Set<String>name = new HashSet<String>();
		
	public MapBox()
	{
		//locations.name = "";
		//locations.latitude = 0;
		//locations.longitude = 0;
	}
	
	//returns west edge
	public double Westmost() {return xmin;}
	
	//returns east edge
	public double Eastmost() {return xmax;}
		
	//returns south edge
	public double Southmost() {return ymin;}
	
	//returns north edge
	public double Northmost() {return ymax;}

	//saves locations, changes the maximums and minimums, and checks for duplicates
	public boolean recordLocation(Location i) 
	{
		//duplications
		if (name.contains(i.name)) {return false;}
	
		//adds location and gets values
		if (locations.isEmpty()) 
		{
			name.add(i.name);
			locations.add(i);
			
			this.xmax = i.longitude;
			this.xmin = i.longitude;
			this.ymax = i.latitude;
			this.ymin = i.latitude;
		}
		
		//changes values of the boundaries
		else
		{
			name.add(i.name);
			locations.add(i);
			
			if(i.longitude > xmax) {xmax = i.longitude;}
			if(i.longitude < xmin) {xmin = i.longitude;}
			if(i.latitude > ymax) {ymax = i.latitude;}
			if(i.latitude < ymin) {ymin = i.latitude;}
		}
		return true;
	}	
	
}
	