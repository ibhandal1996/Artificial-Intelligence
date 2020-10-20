//
// GoodHeuristic
//
// This class extends the Heuristic class, providing a reasonable
// implementation of the heuristic function method. The provided "good"
// heuristic function is admissible.
//
// Iqbal Bhandal - 10 / 8 / 18
//

// import java.util.*;


public class GoodHeuristic extends Heuristic {


	// heuristicValue -- Return the appropriate heuristic values for the
	// given search tree node. Note that the given Node should not be
	// modified within the body of this function.
	
	
	StreetMap map; 
	double speed, y1, x1, dist1, dist2, lat, lon, max;
	
	public GoodHeuristic (StreetMap map)
	{
		this.map = map;
		
	}
	
	public double heuristicValue(Node thisNode) {
		double hVal = 0.0;
		
		
		y1 = thisNode.loc.longitude - destination.longitude;
		x1 = thisNode.loc.latitude - destination.latitude;
		y1 = Math.abs(y1);
		x1 = Math.abs(x1);
		dist1 = x1 + y1;
		
		speed = 0;
		for(Location l1 : map.locations)
		{
			for(Road r1 : l1.roads)
			{
				lat = r1.fromLocation.latitude - r1.toLocation.latitude;
				lon = r1.fromLocation.longitude - r1.toLocation.longitude;
				dist2 = Math.sqrt((Math.pow(lat, 2)) + (Math.pow(lon, 2)));
				max = dist2 / r1.cost;
				
				if (speed < max)
				{
					speed = max;
				}
			}
			
		}
		hVal = dist1 / speed;
		return (hVal);
	}

}
