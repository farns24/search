package model.potentialFields;

import java.util.ArrayList;

/**
 * Created by michael on 5/23/17.
 */
public class Main {
    public static void main(String[] args) {
        Main m = new Main();
        int height = 50;
        int width = 50;

        ArrayList<PotentialField> fields = new ArrayList<>();
        fields.add(new AttractionField(new int[] {15,8}, height, width));
        m.addRepulsives(fields,height,width);
        CompositeField field = new CompositeField(height, width, fields);

//        PotentialField field = new AttractionField(new int[] {25, 25},height, width);


//        field.printVectors();
//        System.out.println();
//        field.printMagnitudes();
//        System.out.println();
//        field.printSpeeds();
//        System.out.println();
//        field.printDistances();
//        System.out.println();
        field.printArrows();
        
    }

    public void addRepulsives(ArrayList<PotentialField> fields, int height, int width) {
        for (int i = 0; i < (5*2); i+=2) {
            fields.add(new RepulsionField(new int[] {i+10,20},height,width));
        }
    }

}
