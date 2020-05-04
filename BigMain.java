package thepackage;
import java.util.*;

public class BigMain {
	
	

	public static void main(String[] args) {
		Scanner scanner=new Scanner(System.in);
		System.out.println("Please Write 1 for A Star or 2 for DFS or 3 For BFS");
		int choice=scanner.nextInt();
		if(choice==1)
		{
			int euclidean =0;
			System.out.println("Choose 1 for euclidean or 0 for Manhatten");
			euclidean=scanner.nextInt();
			System.out.println("Please enter the 2D Matrix row by row with values separated by spaces");
			int [][] first_state=new int[3][3];
			for(int i=0;i<3;i++)
				for(int j=0;j<3;j++)
					first_state[i][j]=scanner.nextInt();
			
			
			AStarBoard trial_board=new AStarBoard(first_state,null,euclidean);
			AStarSolve solve=new AStarSolve(trial_board);
			
			long startTime = System.nanoTime();	
			Stack<AStarBoard> the_solution=solve.solvePuzzle();
			long endTime   = System.nanoTime();
			
			while(!the_solution.isEmpty())
			{
				AStarBoard item=the_solution.pop();
				item.printBoard();
			}
			long totalTime = endTime - startTime;
			System.out.println("Total Running Time is "+totalTime+" Nano second");
			System.out.println("Goal Reached!");
			
		}
		else if(choice==2)
		{
			
			Board initial = new Board(new int[][]{{1,2,5}, {3,4,8}, {6,7,0}});
		    Board goal = new Board(new int[][]{{0,1,2}, {3,4,5}, {6,7,8}});
		    
		    int maxDepth = 1000; 
		    long start_time=System.nanoTime();
		    Board result = Search.depthFirst(initial, goal, maxDepth);
		    long end_time=System.nanoTime();
		    printSolution(result);
		    long time=end_time-start_time;
		    System.out.println("Running time is "+time+" Nano Second");
			
			
			
		}
		else if(choice==3)
		{
			
			 BFS bfs = new BFS(new Integer[]{8, 0, 6, 5, 4, 7, 2, 3, 1});
		        Node solution = bfs.solve();

			
			
			
		}
		

	}
	  public static void printSolution(Board goal) {
		    if (goal == null) {
		      System.out.println("No Solution reached");
		    } else {
		      int count = -1;
		      while (goal != null) {
		        System.out.println(goal);
		        
		        goal = goal.previous;
		        count++;
		      }
		      System.out.println("Cost of path is " + count);
		    }      
		  }

}
