package thepackage;
import java.util.ArrayList;

public class Node {

    private Integer[] goalState = {0, 1, 2, 3, 4, 5, 6, 7, 8};
    private Integer[] currentState;
    private String path;
    private int depth;
    private Node parent;

    public Node(Integer[] currentState, int depth, String path, Node parent) {
        this.currentState = currentState.clone();
        this.depth = depth;
        this.path = new String(path);
        this.parent = parent;
    }


    public Boolean isGoalState()
    {
        return (goalState[0] == currentState[0] &&
                goalState[1] == currentState[1] &&
                goalState[2] == currentState[2] &&
                goalState[3] == currentState[3] &&
                goalState[4] == currentState[4] &&
                goalState[5] == currentState[5] &&
                goalState[6] == currentState[6] &&
                goalState[7] == currentState[7] &&
                goalState[8] == currentState[8] );
    }
    private int getBlank(Integer[] arr)
    {
       int j = -1;
        for(int i = 0; i < arr.length; ++i)
            if(arr[i] == 0)
            {
                j = i;
                break;
            }
        return j;
    }
    public ArrayList<Node> generateChildren()
    {
        int blank = getBlank(this.currentState);
        Integer[] state ;
        ArrayList<Node> children = new ArrayList<Node>();

        /* Moving the blank to : */
        int left = blank - 1;
        int right = blank + 1;
        int up = blank - 3;
        int down = blank + 3;
        int temp;
        /* LEFT */
        if(blank != 0 && blank != 3 && blank != 6)
        {
            state = this.currentState.clone();

            temp = state[blank];
            state[blank] = state[left];
            state[left] = temp;

            children.add(new Node(state, this.depth + 1, path + "L", this));

        }
        /* RIGHT */
        if(blank != 2 && blank != 5 && blank != 8)
        {
            state = this.currentState.clone();

            temp = state[blank];
            state[blank] = state[right];
            state[right] = temp;

            children.add(new Node(state, this.depth + 1, path + "R", this));

        }
        /* UP */
        if(blank != 0 && blank != 1 && blank != 2)
        {
            state = this.currentState.clone();

            temp = state[blank];
            state[blank] = state[up];
            state[up] = temp;

            children.add(new Node(state, this.depth + 1, path + "U", this));

        }
        /* DOWN */
        if(blank != 6 && blank != 7 && blank != 8)
        {
            state = this.currentState.clone();

            temp = state[blank];
            state[blank] = state[down];
            state[down] = temp;

            children.add(new Node(state, this.depth + 1, path + "D", this));

        }
        return children;
    }
    public boolean isSame(Integer[] state)
    {
        for (int i = 0; i < state.length; ++i)
            if(state[i] != this.currentState[i])
                return false;
         return true;
    }

    public Node getParent() {
        return parent;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Integer[] getCurrentState() {
        return currentState;
    }

    public void setCurrentState(Integer[] currentState) {
        this.currentState = currentState;
    }

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public void print()
    {
        for(int i = 0; i < 9; ++i)
        {
            System.out.print(this.currentState[i]);
            if(i == 2 || i == 5 || i == 8)
                System.out.println();
        }
        System.out.println("-----------");
    }
}