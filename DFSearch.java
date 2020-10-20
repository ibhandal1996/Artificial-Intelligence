//Iqbal Bhandal
//CSE 175

import java.util.*;

public class DFSearch
{
	Map mapSearched;
	String begin;
	String end;
	int lim;
	public int expansionCount;
	
	public DFSearch(Map i, String j, String k, int l) 
	{
		mapSearched = i;
		begin = j;
		end = k;
		lim = l;
	}
	
	public Node search(boolean check) 
	{
		Node location;
		Frontier f;
		f = new Frontier();
		location = new Node(mapSearched.findLocation(begin));
		
		f.addToBottom(location);
		expansionCount = 0;
		
		if (check == true)
		{
			HashSet<String> checked = new HashSet<String>();
			while (f.isEmpty() == false && location.depth < lim - 1)
			{ 
				location = f.removeTop();
				if(location.isDestination(end))
				{
					return location;
				}
				checked.add(location.loc.name);
				location.expand();
				expansionCount++;
				for(Node a : location.children)
				{
					if((checked.contains(a.loc.name) || f.contains(a)) == false)
					{
						f.addToTop(a);
					}
				}
				
			}
			return null;
		}
		
		else
		{
			while (f.isEmpty() == false && location.depth < lim - 1)
			{
				location = f.removeTop();
				if(location.isDestination(end))
				{
					return location;
				}
				location.expand();
				expansionCount++;
				f.addToTop(location.children);
			}
			return null;
		}
	}
}