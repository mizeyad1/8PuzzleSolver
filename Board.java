package thepackage;
import java.util.*;

public class Board {

	Board previous;
	int depth;
	int hash;
	 int[][] tiles;
	 static int deltas[][] = {{+1, 0}, {0, -1}, {-1, 0}, {0, 1}};
	  public Board (int[][] initial) {
	    tiles = new int[3][3];
	    for (int r = 0; r < 3; r++) {
	      for (int c = 0; c < 3; c++) {
	        tiles[r][c] = initial[r][c];
	      }
	    }
	  }

	  public Board (Board b) {
	    tiles = new int[3][3];
	    for (int r = 0; r < 3; r++) {
	      for (int c = 0; c < 3; c++) {
	        tiles[r][c] = b.tiles[r][c];
	      }
	    }
	  }

	  public String toString() {
	    StringBuilder sb = new StringBuilder();
	    for (int r = 0; r < 3; r++) {
	      for (int c = 0; c < 3; c++) {
	        if (tiles[r][c] == 0) { 
	          sb.append('-');
	        } else {
	          sb.append(tiles[r][c]);
	        }
	      }
	      sb.append('\n');
	    }
	    return sb.toString();
	  }
	  public boolean isAdjacentAndEmpty(int fromR, int fromC, int toR, int toC) {
		    if (tiles[toR][toC] != 0) {  return false; }

		    int dC = Math.abs(fromR-toR);
		    int dR = Math.abs(fromC-toC);
		    if ((dC == -1 && dR == 0)  || (dC == +1 && dR == 0) ||
		        (dC == 0  && dR == -1) || (dC == 0  && dR == +1)) {
		      return true;
		    }

		    return false;
		  }

		  public void swap (int fromR, int fromC, int toR, int toC) {
		    int tmp = tiles[toR][toC];
		    tiles[toR][toC] = tiles[fromR][fromC];
		    tiles[fromR][fromC] = tmp;
		  }
		  public List<SlideMove> validMoves() {
			    int br = -1, bc = -1;
			    
			    for (int r = 0; r < 3; r++) {
			      for (int c = 0; c < 3; c++) {
			        if (tiles[r][c] == 0) {
			          br = r;
			          bc = c;
			        }
			      }
			    }

			    ArrayList<SlideMove> list = new ArrayList<SlideMove>();
			    for (int i = 0; i < deltas.length; i++) {
			      int dr = deltas[i][0];
			      int dc = deltas[i][1];

			      SlideMove sm = new SlideMove (br+dr, bc+dc, br, bc);
			      if (sm.isValid(this)) { list.add(sm); }
			    }

			    return list;
			  }
		  public boolean equals (Object o) {
			    if (o == null) { return false; }
			    if (!(o instanceof Board)) { return false; }
			    Board other = (Board) o;
			    for (int r = 0; r < 3; r++) {
			      for (int c = 0; c < 3; c++) {
			        if (tiles[r][c] != other.tiles[r][c]) { return false; }
			      }
			    }
			    return true;
			  }
			  
			  public int hashCode() {
			    if (hash == 0) {
			      for (int r = 0; r < 3; r++) {
			        for (int c = 0; c < 3; c++) {
			          hash = 31*hash + tiles[r][c];
			        }
			      }
			    }
			    
			    return hash;
			  }
}
