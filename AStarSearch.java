import java.util.*;

public class AStarSearch
{
	StreetMap search;
	public String start;
	public String end;
	int limit;
	public int expansionCount;
	
	
	public AStarSearch(StreetMap search, String start, String end, int limit) 
	{
		this.start = start;
		this.end = end;
		this.search = search;
		this.limit = 9999;
	}
	
	public Node search(boolean check)
	{
		Location startingLoc = search.findLocation(start);
		Location endingLoc = search.findLocation(end);
		
		if (startingLoc == null)
		{
			return null;
		}
		
		GoodHeuristic GoodHeur = new GoodHeuristic(search);
		GoodHeur.setDestination(endingLoc);
		Node init = new Node(startingLoc);
		SortedFrontier f = new SortedFrontier(SortBy.f);
		f.addSorted(init);
		Set<String> explored = null;
		expansionCount = 0;
		Node node_1;
		
		if(check == true)
		{
			explored = new HashSet<String>();
		}
		
		while(f.isEmpty() == false)
		{
			node_1 = f.removeTop();
			if(limit <= node_1.depth)
			{
				return null;
			}
			else if(node_1.isDestination(end))
			{
				return node_1;
			}
			else
			{
				if(check == true)
				{
					explored.add(node_1.loc.name);
				}
				
				node_1.expand(GoodHeur);
				expansionCount += 1;
	
				for(Node i : node_1.children)
				{
					if (f.contains(i) && i.partialPathCost < f.find(i).partialPathCost)
					{
						f.remove(i);
						f.addSorted(i);
					}
					else if ((check == false) || (f.contains(i) == false) && (explored.contains(i.loc.name)== false))
					{
						f.addSorted(i);
					}
					
				}
			}
		}
		return null;
	}
}
