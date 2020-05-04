package thepackage;
import java.util.*;

public class AStarSolve {
    AStarBoard first_state;
    int nodes_expanded=0;
    int max_search_depth=0;
    int path_cost=0;
    int nodes_expand=0;
    int search_depth=0;
    Comparator<AStarBoard> AStarBoardComparator;
	public AStarSolve(AStarBoard first_board)
	{
		 first_state=first_board;
	}
	class AStarBoardComparator implements Comparator<AStarBoard> 
	{ 
	  
	    public int compare(AStarBoard a, AStarBoard b) 
	    { 
	        return a.total_cost-b.total_cost; 
	    } 
	}
	
	public Stack<AStarBoard>solvePuzzle()
	{
		Stack <AStarBoard> solution_stack=new Stack<AStarBoard>();
		
		 PriorityQueue<AStarBoard> frontier = new PriorityQueue<AStarBoard>( new AStarBoardComparator());
		 Set<AStarBoard> explored=new HashSet<AStarBoard>();
		 AStarBoard root_board=first_state;
		 frontier.add(root_board);
		 while(!frontier.isEmpty())
		 {
			 AStarBoard current_board=frontier.remove();
		
	
			 explored.add(current_board);
			 if(current_board.is_goal_reached()) {
				 path_cost=current_board.total_cost;
				 search_depth=current_board.boardDepth;
				 System.out.println("Search depth is "+search_depth);
				 while (current_board.parento_board!=null)
				 {
					 solution_stack.push(current_board);
					 current_board=current_board.parento_board;
				 }
				 
				 break;
				 
			 }
			 nodes_expanded+=1;
			 
			 List<AStarBoard> children_boards=current_board.getBoardChildren(current_board.getZeroPosition(), current_board.getSwapPositions(), current_board);
			 for(int i=0;i<children_boards.size();i++)
			 {
				 if(current_board.boardDepth<children_boards.get(i).boardDepth)
				 {
					 max_search_depth=children_boards.get(i).boardDepth;
				 }
				 if(!(foundInSet(explored,children_boards.get(i).boardState)) && (foundinFrontier(frontier,children_boards.get(i).boardState)==null))
				 {
				 frontier.add(children_boards.get(i));
				 
				 nodes_expand++;
				 }
				 AStarBoard returnedBoard=foundinFrontier(frontier,children_boards.get(i).boardState);
				 if(returnedBoard!=null) {
					 if(children_boards.get(i).total_cost<returnedBoard.total_cost)
					 {
						 frontier.remove(returnedBoard);
						 frontier.add(children_boards.get(i));
					 }
				 }
				 
			 }
			 
			 nodes_expanded=nodes_expanded+nodes_expand;
			 nodes_expand=0;
			 
		 }
		 System.out.println("No. of nodes expanded is "+nodes_expanded);
		 System.out.println("Max search depth is "+max_search_depth);
		 System.out.println("Cost of path is "+path_cost);
		 solution_stack.push(first_state);
		 return solution_stack;
		
	}
	public boolean foundInSet(Set<AStarBoard> boards_in_set,int[][]check_in_set)
	{
	boolean found_in_set=false;
	for(AStarBoard aBoard:boards_in_set)
	{
		if(areMatricesEqual(aBoard.boardState,check_in_set))
		{
			found_in_set=true;
		}
	}
		return found_in_set;
	}
	public AStarBoard foundinFrontier(PriorityQueue<AStarBoard> the_frontier,int[][]check_in_frontier)
	{
		AStarBoard returnedBoard=null;
		boolean found_in_pqueue=false;
		for(AStarBoard aBoard:the_frontier)
		{
			if(areMatricesEqual(aBoard.boardState,check_in_frontier)){
				
				found_in_pqueue=true;
				returnedBoard=aBoard;
				break;
				
			}
		}
		
		
		return returnedBoard;
	}
	public boolean areMatricesEqual(int[][] mat1,int[][]mat2)
	{
		for(int i=0;i<3;i++)
		{
			for(int j=0;j<3;j++)
			{
				if(mat1[i][j]!=mat2[i][j])
					return false;
			}
		}
		return true;
	}
	
	
}
