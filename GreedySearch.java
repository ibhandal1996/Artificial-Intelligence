import java.util.*;

public class GreedySearch
{
	StreetMap search;
	public String start;
	public String end;
	int limit;
	public int expansionCount;
	
	
	public GreedySearch(StreetMap search, String start, String end, int limit) 
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
		SortedFrontier f = new SortedFrontier(SortBy.h);
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
				
				expansionCount += 1;
				node_1.expand(GoodHeur);
				
				for(Node i : node_1.children)
				{
					if ((check == false) || (f.contains(i) == false) && (explored.contains(i.loc.name)== false))
					{
						f.addSorted(i);
					}
				}
			}
		}
		return null;
		
	}
}