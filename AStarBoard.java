package thepackage;
import java.util.*;


public class AStarBoard {
	int[][] boardState;
	int boardDepth;
    int[][] goalState= {{0,1,2},{3,4,5},{6,7,8}};
    int total_cost;
    AStarBoard parento_board;
    float manhatten_count=0;
	double sample;
	int euclidean;
	public AStarBoard(int newBoardState[][], AStarBoard parentBoard,int euclidean)
	{
		 this.euclidean=euclidean;
		 boardState =  newBoardState;
		 if(parentBoard==null) {
			 boardDepth=0;
		 }
		 else {
			 boardDepth=parentBoard.boardDepth+1;
			 parento_board=parentBoard;
		 }
		 if(euclidean==0)
		 total_cost=(int)getManhattenDistance()+boardDepth;
		 else
		 total_cost=getEuclideanDistance()+boardDepth;
		
	}
	public double  getManhattenDistance()
	{
	 
		for (int i=1;i<9;i++) {
				
					double x1=getColumnPosition(boardState,i);
					double x0=getColumnPosition(goalState,i);
					double y1=getRowPosition(boardState,i);
					double y0=getRowPosition(goalState,i);
					sample=Math.abs(x1-x0)+Math.abs(y1-y0);
					manhatten_count+=sample;
				   
				
		}
			
		
		return manhatten_count;
	}
	
	public int getEuclideanDistance()
	{
		int euclidean_count=0;
		for(int i=1;i<9;i++) {
		
				double x1=getColumnPosition(boardState,i);
				double x0=getColumnPosition(goalState,i);
				double y1=getRowPosition(boardState,i);
				double y0=getRowPosition(goalState,i);
				
					euclidean_count+=Math.sqrt(Math.pow(y1-y0,2.0))+Math.sqrt(Math.pow(x1-x0,2.0));
		
		}
		
		return euclidean_count;
	}
	public boolean is_goal_reached()
	{
		for(int i=0;i<3;i++)
			for(int j=0;j<3;j++)
				if(boardState[i][j]!=goalState[i][j])
					return false;
		return true;
	}
	public double getRowPosition(int [][] boardinstance,int tile)
	{
		for(int i=0;i<3;i++)
			for(int j=0;j<3;j++)
				if(boardinstance[i][j]==tile)
					return Double.valueOf(i);
				return -1;
	}
	public double getColumnPosition(int[][] boardinstance,int tile)
	{
		for(int i=0;i<3;i++)
			for(int j=0;j<3;j++)
				if(boardinstance[i][j]==tile)
					return Double.valueOf(j);
				return -1;
	}
	
	public List<Integer> getZeroPosition()
	{
		List<Integer> zero_position=new ArrayList<>();
		zero_position.add((int)getRowPosition(boardState,0));
		zero_position.add((int)getColumnPosition(boardState,0));
		return zero_position;
	}
	public List<Integer> getSwapPositions()
	{
		List<Integer> swap_positions=new ArrayList<>();
		List<Integer> zero_position;
	    zero_position=getZeroPosition();
	    
		
		if(zero_position.get(1)+1<3)//check right
		{
			swap_positions.add(zero_position.get(0));
		    swap_positions.add(zero_position.get(1)+1);
		}
		 if(zero_position.get(0)+1<3)//check down
			{
				swap_positions.add(zero_position.get(0)+1);
				swap_positions.add(zero_position.get(1));
			}
	    if(zero_position.get(0)-1>-1)//check up
		{
			swap_positions.add(zero_position.get(0)-1);
			swap_positions.add(zero_position.get(1));
		}
	    if(zero_position.get(1)-1>-1)// check left
		{
			swap_positions.add(zero_position.get(0));
			swap_positions.add(zero_position.get(1)-1);
		}
		
		
		return swap_positions;
			
	}
	public List<AStarBoard> getBoardChildren(List<Integer> zero_position,List<Integer> swap_positions,AStarBoard parent_board)
	{
		List<AStarBoard> swapped_boards=new ArrayList<>();
		int j=0;
		for(int i=0;i<swap_positions.size()/2;i++)
		{
			int[][] swappedMatrix=new int[3][3];
			
			swappedMatrix=swapMatrix(zero_position,swap_positions,swappedMatrix,j,j+1);
			AStarBoard swapped_board=new AStarBoard(swappedMatrix,parent_board,euclidean);
			swapped_boards.add(swapped_board);
			j=j+2;
		}
		return swapped_boards;
	}
	public int[][] swapMatrix(List<Integer> zero_position,List<Integer> swap_positions, int [][]swappedMatrix,int x,int y)
	{
		int x_zero_position=zero_position.get(0);
		int y_zero_position=zero_position.get(1);
		int temp;
		for(int i=0;i<3;i++) {
			for(int j=0;j<3;j++)
			{
				swappedMatrix[i][j]=boardState[i][j];
			}
		}
		temp=swappedMatrix[x_zero_position][y_zero_position];
		swappedMatrix[x_zero_position][y_zero_position]=swappedMatrix[swap_positions.get(x)][swap_positions.get(y)];
		swappedMatrix[swap_positions.get(x)][swap_positions.get(y)]=temp;
		return swappedMatrix;
	}
	public void printBoard()
	{
		for(int i=0;i<3;i++)
		{
			for(int j=0;j<3;j++)
			{
				System.out.print(boardState[i][j]+" ");
			}
			System.out.println();
		}
		System.out.println();
	}
	public String toString()
	{
		String result="";
		for(int i=0;i<3;i++)
		{
			for(int j=0;j<3;j++)
			{
				result=result+boardState[i][j]+" ";
			}
			result=result+"\n";
		}
		//result=result+"\n";
		
		return result;
	}
	public List<AStarBoard> getBoardChildrenDFS(List<Integer> zero_position,List<Integer> swap_positions,AStarBoard parent_board)
	{
		List<AStarBoard> swapped_boards=new ArrayList<>();
		int j=0;
		for(int i=0;i<swap_positions.size()/2;i++)
		{
			int[][] swappedMatrix=new int[3][3];
			
			swappedMatrix=swapMatrix(zero_position,swap_positions,swappedMatrix,j,j+1);
			AStarBoard swapped_board=new AStarBoard(swappedMatrix,parent_board,euclidean);
			swapped_boards.add(swapped_board);
			j=j+2;
		}
		Collections.reverse(swapped_boards);
		return swapped_boards;
	}
	
	
	
	
}
