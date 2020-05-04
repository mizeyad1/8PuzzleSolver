package thepackage;
import java.util.*;
public class Search {
	
	 static int nodes_expanded=0;
	static int nodes_expand=0;
	static int path_cost=0;
	static int max_depth=0;
	public static Board depthFirst(Board initial, Board goal, int maxDepth) {
	    if (initial.equals(goal)) { return initial; }
	    
	    Stack<Board> open = new Stack<Board>();
	    HashSet<Board> closed = new HashSet<Board>();
	    open.add(initial);
	    while (!open.isEmpty()) {
	      Board b = open.pop();
	      closed.add(b);
	      nodes_expanded+=1;
	      for (SlideMove sm : b.validMoves()) {
	        Board next = new Board(b);
	        sm.execute(next);
	        
	        next.previous = b;
	        next.depth = b.depth + 1;
	        if(next.depth>max_depth)
	        {
	        	max_depth=next.depth;
	        }
	        if (next.equals(goal)) { 
	        	System.out.println("Depth is "+ next.depth);
	        	System.out.println("Max Depth is "+max_depth);
	        	System.out.println("Nodes expanded "+nodes_expanded);
	        	return next; }
	        
	        if (!closed.contains(next)) {
	          if (next.depth < maxDepth) {
	            open.add(next);
	            nodes_expand++;
	          }
	          
	        }
	      }
	      nodes_expanded=nodes_expanded+nodes_expand;
			 nodes_expand=0;
	    }
	    
	    return null;
	  }
}
