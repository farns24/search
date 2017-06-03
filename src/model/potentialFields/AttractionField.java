package model.potentialFields;

import java.util.ArrayList;

/**
 * Created by michael on 5/23/17.
 */
public class AttractionField extends PotentialField {

    public AttractionField(int[] objectLocation, int height, int width) {
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
        int xVal = objectLocation[0] - col;
        int yVal = row - objectLocation[1];
        if (calcDistance(row, col) <= safeArea) {
            xVal = 0;
            yVal = 0;
        }

        double mag = calcMagnitude(new int[] {xVal, yVal});
        if (mag > 20) {
            xVal /= (mag/20);
            yVal /= (mag/20);
        }

        return new int[] {xVal*2, yVal*2};
    }

    @Override
    protected int calcSpeed(int[] vector) {
        double mag = calcMagnitude(vector);
        int value;
        if (mag >= maxSpeed) value = (int)Math.round(maxSpeed);
        else value = (int)Math.round(mag);
        return value;
    }
}
