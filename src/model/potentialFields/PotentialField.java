package model.potentialFields;

import java.util.ArrayList;

/**
 * Created by michael on 5/23/17.
 */
public abstract class PotentialField {
    /**
     * int[rows][columns][vectors]
     * vector = [xVal,yVal]
     */
    protected int[][][] field;
    protected int[] objectLocation;
    protected double safeArea = 1.0;
    protected double maxSpeed = 8.0;
    protected double cushion = 15.0;
    
    public PotentialField(int[] objectLocation, int height, int width, ArrayList<PotentialField> fields) {
        field = new int[height][width][2];
        this.objectLocation = objectLocation;
        initialize(fields);
    }

    public int[][][] getField() {
        return field;
    }

    public int[] getObjectLocation() {
        return objectLocation;
    }

    public void setObjectLocation(int[] objectLocation) {
        this.objectLocation = objectLocation;
    }

    public void printVectors() {//
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[0].length; j++) {
                System.out.printf("[%3d, %3d]  ", field[i][j][0], field[i][j][1]);
            }
            System.out.println();
        }
    }

    public void printMagnitudes() {
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[0].length; j++) {
                System.out.printf("%6.2f  ", calcMagnitude(field[i][j]));
            }
            System.out.println();
        }
    }

    public void printSpeeds() {
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[0].length; j++) {
                int value = calcSpeed(field[i][j]);
                System.out.printf("%2d ", value);
            }
            System.out.println();
        }
    }

    public void printDistances() {
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[0].length; j++) {
                System.out.printf("%6.2f  ", calcDistance(i,j));
            }
            System.out.println();
        }
    }

    public void printArrows() {
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[0].length; j++) {
                if (j == objectLocation[0] && i == objectLocation[1]) {
                    System.out.printf("G ");
                } else if (calcDistance(i, j) <= safeArea && getClass() == AttractionField.class) {
                    System.out.printf("G ");
                } else if (field[i][j][0] == 0 && field[i][j][1] == 0) {
                    System.out.printf("%d ", 0);
                }  else {
                    String arrow = getArrow(calcAngle(field[i][j]), field[i][j][1]);
                    System.out.printf("%s ", arrow);
                }
            }
            System.out.println();
        }
    }

    protected abstract void initialize(ArrayList<PotentialField> fields);

    protected abstract int[] calcVector(int row, int col);

    protected abstract int calcSpeed(int[] vector);

    protected double calcMagnitude(int[] vector) {
        int adjustment = 0;
        if (vector[0] == 0 || vector[1] == 0) adjustment = 4;
        if (vector[0] == 0 && vector[1] == 0) adjustment = 0;
        double firstTerm = Math.pow((vector[0]),2);
        double secondTerm = Math.pow((vector[1]),2);
        return Math.sqrt(firstTerm+secondTerm) + adjustment;
    }

    protected double calcDistance(int row, int col) {
        double firstTerm = Math.pow((objectLocation[0] - col), 2);
        double secTerm = Math.pow((objectLocation[1] - row), 2);
        double distance = Math.sqrt(firstTerm+secTerm);
        return distance;
    }

    private double calcAngle(int[] vector) {
        int[] a = new int[] {1,0};
        double numerator = dotProduct(vector, a);
        double denominator = calcLength(vector) * calcLength(a);
        return Math.acos(numerator/denominator);
    }

    private double dotProduct(int[] a, int[] b) {
        double sum = 0;
        for (int i = 0; i < 2; i++) {
            sum += a[i] * b[i];
        }
        return sum;
    }

    private double calcLength(int[] vector) {
        double firstTerm = Math.pow(vector[0], 2);
        double secTerm = Math.pow(vector[1], 2);
        return Math.sqrt(firstTerm+secTerm);
    }

    private String getArrow(double angle, int y) {
        if (y <= 0) {
            if (angle < .4) {
                return "\u2192";
            } else if (angle < 1.2) {
                return "\u2198";
            } else if (angle < 1.9) {
                return "\u2193";
            } else if (angle < 2.5) {
                return "\u2199";
            } else {
                return "\u2190";
            }
        } else {
            if (angle < .4) {
                return "\u2192";
            } else if (angle < 1.2) {
                return "\u2197";
            } else if (angle < 1.9) {
                return "\u2191";
            } else if (angle < 2.5) {
                return "\u2196";
            } else {
                return "\u2190";
            }
        }
    }
}
