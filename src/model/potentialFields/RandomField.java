package model.potentialFields;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by michael on 5/23/17.
 */
public class RandomField extends PotentialField {

    int[] randomValues;
    int baseValue;
    Random rand;

    public RandomField(int height, int width) {
        super(new int[] {0,0}, height, width, null);
    }

    @Override
    protected void initialize(ArrayList<PotentialField> fields) {
        randomValues = new int[] {-1,0,1};
        baseValue = 3;
        rand = new Random();
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[0].length; j++) {
                field[i][j] = calcVector(i,j);
            }
        }
    }

    @Override
    protected int[] calcVector(int row, int col) {
        int xVal = 0;
        int yVal = 0;
        while (xVal == 0 && yVal == 0) {
            xVal = baseValue * randomValues[rand.nextInt(3)];
            yVal = baseValue * randomValues[rand.nextInt(3)];
        }
        return new int[] {xVal, yVal};
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
