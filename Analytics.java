package thepackage;
public class Analytics {
	private String searchAlgorithm;
    private String pathToGoal;
    private int pathCost;
    private int nodesExpanded;
    private int searchDepth;
    private int maxSearchDepth;
    private long runningTime;
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_WHITE = "\u001B[37m";
    public static final String ANSI_RED = "\u001B[31m";

    public Analytics(String searchAlgorithm, String pathToGoal, int nodesExpanded,
                     int searchDepth, int maxSearchDepth, long runningTime) {
        this.searchAlgorithm = searchAlgorithm;
        this.pathToGoal = pathToGoal;
        this.nodesExpanded = nodesExpanded;
        this.searchDepth = searchDepth;
        this.maxSearchDepth = maxSearchDepth;
        this.pathCost = pathToGoal.length();
        this.runningTime = runningTime;

    }

    public String getPathToGoal() {
        return pathToGoal;
    }

    public void setPathToGoal(String pathToGoal) {
        this.pathToGoal = pathToGoal;
    }

    public int getPathCost() {
        return pathCost;
    }

    public void setPathCost(int pathCost) {
        this.pathCost = pathCost;
    }

    public int getNodesExpanded() {
        return nodesExpanded;
    }

    public void setNodesExpanded(int nodesExpanded) {
        this.nodesExpanded = nodesExpanded;
    }

    public int getSearchDepth() {
        return searchDepth;
    }

    public void setSearchDepth(int searchDepth) {
        this.searchDepth = searchDepth;
    }
    public void print()
    {
        if(this.nodesExpanded != -1)
        {
            System.out.println( "***** " + searchAlgorithm + " *****");
            System.out.println( "path to goal : " +   pathToGoal);
            System.out.println( "cost of path : " +   this.pathCost);
            System.out.println( "nodes expanded : " +   this.nodesExpanded);
            System.out.println(  "search depth : " +   this.searchDepth);
            System.out.println(  "max search depth : " +  this.maxSearchDepth);
            System.out.println(  "running time : "  + this.runningTime + " NanoSeconds");
        }
        else
        {
            System.out.println(  "***** " + searchAlgorithm + " *****");
            System.out.println( "path to goal : "  + pathToGoal);
            System.out.println("cost of path : " + pathToGoal);
            System.out.println( "nodes expanded : " +  this.nodesExpanded);
            System.out.println( "search depth : " +  this.searchDepth);
            System.out.println( "max search depth : " +  this.maxSearchDepth);
            System.out.println( "running time : " +  this.runningTime);

        }

    }
}
