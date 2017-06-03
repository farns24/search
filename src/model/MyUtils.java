package model;

import java.sql.ClientInfoStatus;

public class MyUtils {

	public static MotionState getOrders(double robTheta, double goalTheta) {
		
		robTheta -= Math.PI;
		robTheta = makePositive(robTheta);
		goalTheta = makePositive(goalTheta);
	
		//System.out.println("Robot Direction : " +robTheta);
		//System.out.println("Goal Direction : " + goalTheta);
		//assert(robTheta>=0.0);
		//assert(robTheta<= 2.0 * Math.PI);
		double diff = robTheta - goalTheta;
		
		//Test for equality
		if (Math.abs(diff)<Math.PI/6.0)
		{
			return new GoForwardTask();
		}
		
		
		//Quadrent 4
		if ( goalTheta > 3.0 * Math.PI/2.0)
		{
			if (diff<0.0)
			{
				return new GoClockWiseTask(diff);
			}
		}
		//Quadrent 3
		else if ( goalTheta > Math.PI)
		{
			if (diff>0.0)
			{
				return new GoClockWiseTask(diff);
			}
		}
		//Quadrent 2
		else if ( goalTheta > Math.PI/2.0)
		{
			if (diff<0.0)
			{
				return new GoClockWiseTask(diff);
			}
		}
		//Quadrent 1
		else
		{
			if (diff>0.0)
			{
				return new GoClockWiseTask(diff);
			}
		}
		
		
		
		
		return new GoCounterClockWiseTask(diff);//MotionState.GO_COUNTER_CLOCKWISE;
	}

	private static double makePositive(double robTheta) {
		while(robTheta<0)
		{
			robTheta = robTheta + 2.0 * Math.PI;
		}
		return robTheta;
	}

	public static boolean madeIt(int[] vect, boolean lastStep) {
		double threshold = 0.5;
		if (lastStep)
		{
			threshold = 1.0;
		}
		boolean result = Math.sqrt(Math.pow(vect[0],2)+ Math.pow( vect[1],2))<threshold;
		if (result)
		{
			System.out.println("Looks like we made it, Left each other on the way...");
			
		}
		return result;
		
	}

}
