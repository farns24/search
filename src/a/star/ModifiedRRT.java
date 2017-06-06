package a.star;

import java.util.ArrayList;

public class ModifiedRRT extends RRT {

	int size = 100;
	
	int[][] weightedPaths = new int[size][size];
	
	@Override
	protected int[] getRandom() {
		//Add duplicates of nodes that are more likely to be part of the path.
		for (int i = 0; i<size;i++)
			for(int j= 0;i<size;i++)
			{
				int weight = weightedPaths[i][j];
				
				for (int k = 0; k<weight; k++)
				{
					pool.add(new int[]{i,j});
				}
				
			}
		
		
		
		
		return super.getRandom();
	}

	@Override
	protected void loadLocation(ArrayList<int[]> pathNodes) {
		
		for(int[] node: pathNodes)
		{
			weightedPaths[node[0]][node[1]] +=1;
		}
		
		
		
	}

	

	
}
