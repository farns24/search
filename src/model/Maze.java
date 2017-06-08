package model;

public class Maze implements IMaze {

	private static final int K = 1;
	private static final int YCOL = 0;
	private static final int XCOL = 1;
	private GridSpace[][] data;
	private int xMin;
	private int xMax;
	private int yMin;
	private int yMax;
	private int roboX;
	private int roboY;
	private int goalX;
	private int goalY;

//	@Override
//	public void initMaze(LocationScape location, int goalId) {
//		
//		for (String key: location.keySet())
//		{
//			RobotLocation val = location.get(key);
//			
//			//Find the range of x and y values
//			int[] cent = val.getCenter();
//			
//			updateRange(cent);
//			
//		}
//		
//		data = new GridSpace[xMax-xMin][yMax-yMin];
//		
//		for (int i = xMin; i< xMax ; i++)
//		{
//			for (int j = yMin; j<yMax; j++ )
//			{
//				data[i][j] = GridSpace.CLEAR;
//			}
//		}
//		
//		for (String key: location.keySet())
//		{
//			RobotLocation val = location.get(key);
//			
//			//Find the range of x and y values
//			int[] cent = val.getCenter();
//			int x =cent[XCOL];
//			int y = cent[YCOL];		
//			GridSpace type = builtType(key,x,y);
//			if (y-1>=0)
//			{
//				data[x][y-1] = type;
//				if (x+1<data.length)
//				data[x+1][y-1] = type;
//				data[x-1][y-1] = type;
//			}
//			data[x][y] = type;
//			if (x+1<data.length)
//			{
//			data[x+1][y] = type;
//			if(y+1<data[0].length)
//			data[x+1][y+1] = type;
//			}
//			if(y+1<data[0].length)
//			data[x][y+1] = type;
//			if (x-1>=0)
//			{
//			data[x-1][y] = type;
//			if(y+1<data[0].length)
//			data[x-1][y+1] = type;
//			}
//			
//		}
//		
//
//	}
//
//	private GridSpace builtType(String key, int x , int y) {
//		if (key.equals("robot"))
//		{
//			roboX = x;
//			roboY = y;
//			return GridSpace.ROBOT;
//		}
//		else if (key.equals("3"))
//		{
//			goalX = x;
//			goalY = y;
//			return GridSpace.GOAL;
//		}
//		return GridSpace.OBSTICAL;
//	}
//
//	public void updateRange(int[] cent) {
//		if (cent[XCOL]> xMax)
//		{
//			xMax = cent[XCOL]+1;
//		}
//		if (cent[XCOL]< xMin)
//		{
//			xMin = cent[XCOL];
//		}
//		if (cent[YCOL] > yMax)
//		{
//			yMax = cent[YCOL]+1;
//		}
//		if (cent[YCOL]< yMin)
//		{
//			yMin = cent[YCOL];
//		}
//		
//	}

	@Override
	public void initMaze(LocationScape location, int goalId) {
		data = new GridSpace[14][8];
		for (int i = 0; i < data.length; i++) {
			for (int j = 0; j < data[0].length; j++) {
				data[i][j] = GridSpace.CLEAR;
			}
		}
		
		for (String key: location.keySet())
		{
			RobotLocation val = location.get(key);
			
			try {
				//Find the range of x and y values
				int[] cent = val.getCenter();
				
				if(key.equals("robot")) {
					roboX = cent[0];
					roboY = cent[1];
					data[cent[0]][cent[1]] = GridSpace.ROBOT;
				} else if(key.equals("3")) {
					goalX = cent[0];
					goalY = cent[1];
					data[cent[0]][cent[1]] = GridSpace.GOAL;
				} else {
					int yUp = cent[1]-1;
					int yDown = cent[1]+1;
					int xRight = cent[0]+1;
					int xLeft = cent[0]-1;
					if(yUp >= 0 && yUp < data[0].length) {
						data[cent[0]][yUp] = GridSpace.OBSTICAL;
					}
					if(yDown >= 0 && yDown < data[0].length) {
						data[cent[0]][yDown] = GridSpace.OBSTICAL;
					}
					if(xLeft >= 0 && xLeft < data.length) {
						data[xLeft][cent[1]] = GridSpace.OBSTICAL;
					}
					if(xRight >= 0 && xRight < data.length) {
						data[xRight][cent[1]] = GridSpace.OBSTICAL;
					}
					data[cent[0]][cent[1]] = GridSpace.OBSTICAL;
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				
			}
			
			
//			double width = getMarkerWidth(val.getCorners());
//			System.out.printf("cent[0]: %d\n", cent[0]);
//			System.out.printf("cent[1]: %d\n\n", cent[1]);
//			System.out.printf("width: %f", width);
		}
		
	}
	

	@Override
	public GridSpace[][] getData() {
		return data;
	}

	@Override
	public void draw() {
		for (int i = 0; i < data[0].length; i++)
		{
			for (int j = 0; j < data.length; j++)
			{
				GridSpace space = data[j][i];
				switch (space){
				case CLEAR:
					System.out.print("•");
					
					break;
				case GOAL:
					System.out.print("G");
					
					break;
				case OBSTICAL:
					System.out.print("X");
					
					break;
				case ROBOT:
					System.out.print("R");
					
					break;
				default:
					break;
				
				}
			}
			System.out.println();
		}
		
	}

	@Override
	public int[] getRobotPos() {
		// TODO Auto-generated method stub
		return new int[]{roboX,roboY};
	}

	@Override
	public int[] getGoalPos() {
		return new int[]{goalX,goalY};
	}
	
	private double getMarkerWidth(double[][] corners) {
		double max = 0;
		double min = Double.MAX_VALUE;
		for (int i = 0; i < corners.length; i++) {
			if(corners[i][0] > max) max = corners[i][0];
			if(corners[i][0] < min) min = corners[i][0];
		}
		return max - min;
	}
}
