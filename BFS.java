package thepackage;
import javax.swing.text.html.HTMLDocument;
import java.util.*;

public class BFS {

    private int nodesExpanded;
    private int searchDepth;
    private Node initialState;
    private Queue<Node> frontier;
    private HashSet<List<Integer>> explored;
    private Analytics analytics;

    public BFS(Integer[] initialState) {
        this.nodesExpanded = 0;
        this.searchDepth = 0;
        this.initialState = new Node(initialState, 0, "", null);

        frontier = new LinkedList<Node>();
        explored = new HashSet<List<Integer>>();
    }

    private void addValidChildren(ArrayList<Node> children)
    {
        boolean shouldAdd = true;
        Node parent ;
        for(int j = 0; j < children.size(); ++j)
        {
            shouldAdd = true;

            if(explored.contains(Arrays.asList(children.get(j).getCurrentState())))
            {
                shouldAdd = false;
            }

            if(shouldAdd) {
                frontier.add(children.get(j));
                //children.get(j).print();
            }
        }
        return;
    }
    public Node solve()
    {
        long start = System.nanoTime();
        Node solution = this.solveHelper();
        long ellapsed = System.nanoTime() - start;
        if(solution == null)
            clearAnalytics();
        else {
            setAnalytics(solution, ellapsed);
            analytics.print();
        }
        return solution;

    }

    private Node solveHelper()
    {
        Node current = this.initialState;
        frontier.add(this.initialState);
        while (!frontier.isEmpty())
        {
            current = frontier.peek();
            this.explored.add(Arrays.asList(current.getCurrentState()));
            this.nodesExpanded++;
            frontier.remove();

            if(current.isGoalState())
            {
                return  current;
            }
            this.searchDepth ++ ;
            addValidChildren(current.generateChildren());
        }
        return null ;
    }

    public void printAnalytics() {
        analytics.print();
    }

    private void setAnalytics(Node solution, long executionTime) {
        this.analytics = new Analytics(
                "Breadth-First-Search",
                solution.getPath(), this.nodesExpanded,
                solution.getDepth(), solution.getDepth(),
                executionTime);
    }
    private void clearAnalytics() {
        this.analytics = new Analytics(
                "Breadth-First-Search-Failed",
                "No-solution-found", -1,
                -1, -1,
                -1);
    }

}