package a.star;

import model.*;

import java.util.*;

/**
 * Created by michael on 6/2/17.
 */
public class RRT implements IPathFinder {

    protected ArrayList<int[]> pool;
    protected Node root;
    protected Node goalNode;
    protected int[] goal;
    private final int row = 0;
    private final int col = 1;
    private int rows;
    private int cols;
	

    public RRT() {
        pool = new ArrayList<>();
    }

    @Override
    public List<IRoboInstruction> findPath(IMaze maze) {
        GridSpace[][] spaces = maze.getData();
        initPool(spaces);
        cols = spaces.length;
        rows = spaces[0].length;
        goal = new int[2];
        goal[row] = maze.getGoalPos()[1];
        goal[col] = maze.getGoalPos()[0];

        System.out.printf("Goal: x = %d, y = %d\n", goal[col], goal[row]);

        int[] nearest;
        do {
            int[] selected = getRandom();
            nearest = getNearest(selected, spaces); //Adds to Path as well as returns it
            if (nearest == null) nearest = new int[] {-1,-1};
            if(nearest[row] == goal[row] && nearest[col] == goal[col]) System.out.println("found goal!");
        } while (!(nearest[row] == goal[row] && nearest[col] == goal[col]));

        return translatePath();
    }

    protected int[] getRandom() {
        Random rand = new Random();
        int r = rand.nextInt(pool.size());
        return pool.get(r);
    }

    private int[] getNearest(int[] selected, GridSpace[][] spaces) {
        Node nearestOnPath = recursiveGetNearest(root, selected, Double.MAX_VALUE);
        int[] nearestNeighborSoFar = null;
        for (int i = nearestOnPath.getLocation()[row]-1; i <= nearestOnPath.getLocation()[row]+1; i++) {
            if (i < 0 || i >= spaces[row].length) continue;
            for (int j = nearestOnPath.getLocation()[col]-1; j <= nearestOnPath.getLocation()[col]+1; j++) {
                if (j < 0 || j >= spaces.length) continue;
                if (spaces[j][i] == GridSpace.OBSTICAL) continue;
                if (i == nearestOnPath.getLocation()[row] && j == nearestOnPath.getLocation()[col]) continue;
                if (getPoolIndex(new int[] {i,j}) == -1) continue;
                if (nearestNeighborSoFar == null) {
                    nearestNeighborSoFar = new int[] {i,j};
                } else {
                    if (calcDistance(new int[] {i,j}, selected) < calcDistance(nearestNeighborSoFar, selected)) {
                        nearestNeighborSoFar = new int[] {i,j};
                    }
                }

            }
        }
        if (nearestNeighborSoFar != null) {
            addToPath(nearestOnPath, nearestNeighborSoFar);
            pool.remove(getPoolIndex(nearestNeighborSoFar));
            if (nearestNeighborSoFar[0] == goal[0] && nearestNeighborSoFar[1] == goal[1]) {
                goalNode = new Node(nearestNeighborSoFar);
                goalNode.setParent(nearestOnPath);
            }
        }
        return nearestNeighborSoFar;
    }

    private Node recursiveGetNearest(Node node, int[] selected, double smallestDist) {
        double distance = calcDistance(node.getLocation(), selected);
        Node nearestSoFar = null;
        if (distance < smallestDist) {
            smallestDist = distance;
            nearestSoFar = node;
        }

        if (node.getChildren() != null) {
            for (Node child : node.getChildren()) {
                Node result = recursiveGetNearest(child, selected, smallestDist);
                if (result == null) continue;
                double resultsDistance = calcDistance(result.getLocation(), selected);
                if (resultsDistance < smallestDist) {
                    nearestSoFar = result;
                    smallestDist = resultsDistance;
                }
            }
        }

        return nearestSoFar;
    }

    private void addToPath(Node nearestOnPath, int[] locOfNewNode) {
        Node newNode = new Node(locOfNewNode);
        newNode.setParent(nearestOnPath);
        nearestOnPath.addChild(newNode);
    }

    private void initPool(GridSpace[][] spaces) {
        for (int i = 0; i < spaces.length; i++) {
            for (int j = 0; j < spaces[row].length; j++) {
                if (spaces[i][j] == GridSpace.CLEAR || spaces[i][j] == GridSpace.GOAL) {
                    pool.add(new int[] {j,i});
                } else if (spaces[i][j] == GridSpace.ROBOT) {
                    root = new Node(j, i);
                }
            }
        }
    }

    private double calcDistance(int[] a, int[] b) {
        if (a == null || b == null) {
            System.out.println("weird");
        }
        double firstTerm = Math.pow(b[col] - a[col], 2);
        double secTerm = Math.pow(b[row] - a[row], 2);
        return Math.sqrt(firstTerm + secTerm);
    }

    private int getPoolIndex(int[] item) {
        for (int i = 0; i < pool.size(); i++) {
            int[] poolItem = pool.get(i);
            if (poolItem[row] == item[row] && poolItem[col] == item[col]) {
                return i;
            }
        }
        return -1;
    }

    private List<IRoboInstruction> translatePath() {
        Node currentNode = goalNode;
        List<IRoboInstruction> instructions = new ArrayList<>();
        ArrayList<int[]> pathNodes  = new ArrayList<int[]>();
        while (currentNode != null) {
            if (currentNode.getParent() == null) break;
            instructions.add(0, getInstruction(currentNode.getLocation(), currentNode.getParent().getLocation()));
            
			pathNodes.add(currentNode.getLocation());
            currentNode = currentNode.getParent();
        }
        loadLocation(pathNodes);
        return instructions;
    }

    protected void loadLocation(ArrayList<int[]> pathNodes) {
		
		
	}

	private IRoboInstruction getInstruction(int[] dest, int[] pos) {
        IRoboInstruction instruction = null;
        int yDiff = pos[row] - dest[row];
        int xDiff = dest[col] - pos[col];
        if (Math.abs(xDiff) > 1 || Math.abs(yDiff) > 1) {
            System.out.println("Problem in RRT getInstruction");
            return null;
        }
        if (yDiff == 1) {
            if (xDiff == 1) {
                //return up right instruction
                return new MoveNorthEastCommand();
            } else if (xDiff == 0) {
                //up
                return new MoveNorthCommand();
            } else {
                //up left
                return new MoveNorthWestCommand();
            }
        } else if (yDiff == 0) {
            if (xDiff == 1) {
                //right
                return new MoveEastCommand();
            } else if (xDiff == 0) {
                System.out.println("Problem in RRT getInstruction: duplicate pos in path");
                return null;
            } else {
                //left
                return new MoveWestCommand();
            }
        } else {
            if (xDiff == 1) {
                //down right
                return new MoveSouthEastCommand();
            } else if (xDiff == 0) {
                //down
                return new MoveSouthCommand();
            } else {
                //down left
                return new MoveSouthWestCommand();
            }
        }
    }

    public void printGraph() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (findNodeInGraph(i,j)) {
                    System.out.print("•");
                } else {
                    System.out.print("-");
                }
            }
            System.out.println();
        }
    }

    private boolean findNodeInGraph(int row, int col) {
        return recursiveFindNodeInGraph(root, new int[] {row,col});
    }

    private boolean recursiveFindNodeInGraph(Node currentNode, int[] targetLoc) {
        boolean result = false;
        int[] thisLocation = currentNode.getLocation();
        if (thisLocation[0] == targetLoc[0] && thisLocation[1] == targetLoc[1]) return true;
        if (currentNode.getChildren() == null) return false;
        for (Node child : currentNode.getChildren()) {
            result = recursiveFindNodeInGraph(child, targetLoc);
            if (result) return true;
        }
        return result;
    }

    public void printPath() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (findNodeInPath(i,j)) {
                    System.out.print("•");
                } else {
                    System.out.print("-");
                }
            }
            System.out.println();
        }
    }

    private boolean findNodeInPath(int row, int col) {
        Node currentNode = goalNode;
        boolean result = false;
        do {
            int[] thisLocation = currentNode.getLocation();
            result = (thisLocation[0] == row && thisLocation[1] == col);
            if (result) return true;
            currentNode = currentNode.getParent();
        } while(currentNode != null);
        return result;
    }

    class Node {
        int[] location;
        Node parent;
        ArrayList<Node> children;

        public Node(int[] location) {
            this.location = location;
        }

        public Node(int row, int col) {
            this.location = new int[] {row, col};
        }

        public int[] getLocation() {
            return location;
        }

        public Node getParent() {
            return parent;
        }

        public void setParent(Node parent) {
            this.parent = parent;
        }

        public ArrayList<Node> getChildren() {
            return children;
        }

        public void addChild(Node child) {
            if (children == null) children = new ArrayList<>();
            children.add(child);
        }
    }
}
