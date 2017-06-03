package model.potentialFields;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by michael on 5/23/17.
 */
public class TangentialField extends PotentialField {

    public TangentialField(int[] objectLocation, int height, int width) {
        super(objectLocation, height, width, null);
    }

    @Override
    protected void initialize(ArrayList<PotentialField> fields) {
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[0].length; j++) {
                field[i][j] = calcVector(i,j);
            }
        }
    }

    @Override
    protected int[] calcVector(int row, int col) {
        if (row == objectLocation[1] && col == objectLocation[0]) return new int[] {0,0};
        int xValue = objectLocation[0] - col;
        int yValue = row - objectLocation[1];

        if (calcDistance(row, col) > 40) return new int[] {0, 0};

        return getPerpendicular(invert(xValue,yValue));
    }

    @Override
    protected int calcSpeed(int[] vector) {
        double mag = calcMagnitude(vector);
        int value;
        if (mag >= maxSpeed) value = (int)Math.round(maxSpeed);
        else value = (int)Math.round(mag);
        return value;
    }

    private int[] getPerpendicular(int[] vector) {
        int temp = vector[0];
        vector[0] = vector[1];
        vector[1] = -temp;
        return new int[] {vector[0], vector[1]};
    }

    private int[] invert(int xValue, int yValue) {
        int newX = field[0].length - Math.abs(xValue);
        int newY = field.length - Math.abs(yValue);

        if (xValue > 0) newX = -newX;
        if (yValue > 0) newY = -newY;

        if (xValue == 0) newX = 0;
        if (yValue == 0) newY = 0;

        return new int[] {newX, newY};
    }
}
