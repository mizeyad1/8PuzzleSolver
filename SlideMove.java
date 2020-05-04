package thepackage;

public class SlideMove {
	final int fromR, fromC;
	  final int toR, toC;

	  public SlideMove (int fromR, int fromC, int toR, int toC) {
	    this.fromR = fromR;
	    this.fromC = fromC;
	    this.toR = toR;
	    this.toC = toC;
	  }

	  public boolean execute(Board b) {
	    if (!isValid(b)) { return false; }
	    b.swap(fromR, fromC, toR, toC);
	    return true;
	  }
	  
	  public boolean isValid(Board b) {
	    if (fromR < 0 || fromR >= 3) { return false; }
	    if (fromC < 0 || fromC >= 3) { return false; }
	    if (toR < 0 || toR >= 3) { return false; }
	    if (toC < 0 || toC >= 3) { return false; }

	    return b.isAdjacentAndEmpty(fromR, fromC, toR, toC);
	  }
	  
}
