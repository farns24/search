package a.star;

import model.GridSpace;
import model.IMaze;
import model.IRoboInstruction;

import java.util.*;

/**
 * Created by michael on 6/2/17.
 */
public class RRT implements IPathFinder {

    private ArrayList<int[]> pool;
    private Node root;
    private Node goalNode;
    private int[] goal;
    private final int row = 0;
    private final int col = 1;

    public RRT() {
        pool = new ArrayList<>();
    }

    @Override
    public List<IRoboInstruction> findPath(IMaze maze) {
        GridSpace[][] spaces = maze.getData();
        initPool(spaces);
        goal = new int[2];
        goal[row] = maze.getGoalPos()[1];
        goal[col] = maze.getGoalPos()[0];

        System.out.printf("Goal: x = %d, y = %d\n", goal[col], goal[row]);

        int[] nearest;
        do {
            int[] selected = getRandom();
            nearest = getNearest(selected, spaces); //Adds to Path as well as returns it
        } while (nearest[row] != goal[row] && nearest[col] != goal[col]);
        
        return translatePath();
    }

    private int[] getRandom() {
        Random rand = new Random();
        int r = rand.nextInt(pool.size());
        return pool.get(r);
    }

    private int[] getNearest(int[] selected, GridSpace[][] spaces) {
        Node nearestOnPath = recursiveGetNearest(root, selected, Double.MAX_VALUE);
        int[] nearestNeighborSoFar = null;
        for (int i = nearestOnPath.getLocation()[row]-1; i <= nearestOnPath.getLocation()[row]+1; i++) {
            if (i < 0 || i >= spaces.length) continue;
            for (int j = nearestOnPath.getLocation()[col]-1; j <= nearestOnPath.getLocation()[col]+1; j++) {
                if (j < 0 || j >= spaces[row].length) continue;
                if (i == nearestOnPath.getLocation()[row] && j == nearestOnPath.getLocation()[col]) continue;
                if (nearestNeighborSoFar == null) {
                    nearestNeighborSoFar = new int[] {i,j};
                } else {
                    if (calcDistance(new int[] {i,j}, selected) < calcDistance(nearestNeighborSoFar, selected)) {
                        nearestNeighborSoFar = new int[] {i,j};
                    }
                }

            }
        }
        addToPath(nearestOnPath, nearestNeighborSoFar);
        pool.remove(getPoolIndex(nearestNeighborSoFar));
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
                    pool.add(new int[] {i,j});
                } else if (spaces[i][j] == GridSpace.ROBOT) {
                    root = new Node(i, j);
                }
            }
        }
    }

    private double calcDistance(int[] a, int[] b) {
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
        while (currentNode != null) {
            if (currentNode.getParent() == null) break;
            instructions.add(0, getInstruction(currentNode.getLocation(), currentNode.getParent().getLocation()));
            currentNode = currentNode.getParent();
        }
        return instructions;
    }

    private IRoboInstruction getInstruction(int[] dest, int[] pos) {
        IRoboInstruction instruction = null;
        int yDiff = dest[row] - pos[row];
        int xDiff = dest[col] - pos[col];
        if (Math.abs(xDiff) > 1 || Math.abs(yDiff) > 1) {
            System.out.println("Problem in RRT getInstruction");
            return null;
        }
        if (yDiff == 1) {
            if (xDiff == 1) {
                //return up right instruction
            } else if (xDiff == 0) {
                //up
            } else {
                //up left
            }
        } else if (yDiff == 0) {
            if (xDiff == 1) {
                //right
            } else if (xDiff == 0) {
                System.out.println("Problem in RRT getInstruction: duplicate pos in path");
                return null;
            } else {
                //left
            }
        } else {
            if (xDiff == 1) {
                //down right
            } else if (xDiff == 0) {
                //down
            } else {
                //down left
            }
        }
        return instruction;
    }

    private class Node {
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
