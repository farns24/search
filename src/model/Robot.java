package model;

/**
 * Created by michael on 5/24/17.
 */
public class Robot {
    double[] currentPos;
    double[] currentOrientation;

    public Robot(double[] currentPos, double[] currentOrientation) {
        this.currentPos = currentPos;
        this.currentOrientation = currentOrientation;
    }

    public double[] getCurrentPos() {
        return currentPos;
    }

    public void setCurrentPos(double[] currentPos) {
        this.currentPos = currentPos;
    }

    public double[] getCurrentOrientation() {
        return currentOrientation;
    }

    public void setCurrentOrientation(double[] currentOrientation) {
        this.currentOrientation = currentOrientation;
    }
}
