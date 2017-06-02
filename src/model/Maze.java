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

	@Override
	public void initMaze(LocationScape location, int goalId) {
		
		for (String key: location.keySet())
		{
			RobotLocation val = location.get(key);
			
			//Find the range of x and y values
			int[] cent = val.getCenter();
			
			updateRange(cent);
			
		}
		
		data = new GridSpace[xMax-xMin][yMax-yMin];
		
		for (int i = xMin; i< xMax ; i++)
		{
			for (int j = yMin; j<yMax; j++ )
			{
				data[i][j] = GridSpace.CLEAR;
			}
		}
		
		for (String key: location.keySet())
		{
			RobotLocation val = location.get(key);
			
			//Find the range of x and y values
			int[] cent = val.getCenter();
			int x =cent[XCOL];
			int y = cent[YCOL];		
			data[x][y] = builtType(key,x,y);
			
		}
		

	}

	private GridSpace builtType(String key, int x , int y) {
		if (key.equals("Robot"))
		{
			roboX = x;
			roboY = y;
			return GridSpace.ROBOT;
		}
		else if (key.equals("3"))
		{
			goalX = x;
			goalY = y;
			return GridSpace.GOAL;
		}
		return GridSpace.OBSTICAL;
	}

	public void updateRange(int[] cent) {
		if (cent[XCOL]> xMax)
		{
			xMax = cent[XCOL]+1;
		}
		if (cent[XCOL]< xMin)
		{
			xMin = cent[XCOL];
		}
		if (cent[YCOL] > yMax)
		{
			yMax = cent[YCOL]+1;
		}
		if (cent[YCOL]< yMin)
		{
			yMin = cent[YCOL];
		}
		
	}

	@Override
	public GridSpace[][] getData() {
		return data;
	}

	@Override
	public void draw() {
		for (GridSpace[] row : data)
		{
			for (GridSpace space: row)
			{
				switch (space){
				case CLEAR:
					System.out.print("   ");
					
					break;
				case GOAL:
					System.out.print(" G ");
					
					break;
				case OBSTICAL:
					System.out.print("XXX");
					
					break;
				case ROBOT:
					System.out.print(" R ");
					
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
}
