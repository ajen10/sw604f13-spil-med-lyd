package dk.aau.cs.giraf.cars.gamecode;

import java.util.Random;

public class ObjectPlacement {

	// 0 = ingen objekt 1 = ikke nåbart område  2 = objekt
	public static int[][] objectPlacement(int numberOfObjects){
		int i, j;
		boolean availablePathToEnd = false;

		int[][] roadObstacles;
		roadObstacles = new int [5][6];

		int[][] ObstacleArray;
		ObstacleArray = new int [numberOfObjects][2];

		for (i=0;i<5;i++){
			for (j=0;j<6;j++){
				roadObstacles[i][j] = 0;
			}
		}
		while (availablePathToEnd==false){
			Random rand = new Random();
			for (i=0;i<numberOfObjects;i++){
				int randomRow = Math.abs((rand.nextInt()%3)) + 1;
				int randomColoum = Math.abs((rand.nextInt()%4)) + 1;
				ObstacleArray[i][0] = randomRow;
				ObstacleArray[i][1] = randomColoum;

				roadObstacles[randomRow][randomColoum] = 2;
			}


			//find dead ends
			int k;
			for (k=0;k<4;k++){  //Find flere dead ends
				for (i=1;i<4;i++){
					for (j=1;j<5;j++){
						if (roadObstacles[i][j] != 0 && roadObstacles[i-1][j-1]!=0){
							if (roadObstacles[i-1][j] == 0){
								roadObstacles[i-1][j] = 1;
							}
						}
						if (roadObstacles[i][j] != 0 && roadObstacles[i-1][j+1]!=0){
							if (roadObstacles[i-1][j] == 0){
								roadObstacles[i-1][j] = 1;
							}
						}

						if (roadObstacles[i][j] != 0 && roadObstacles[i+1][j-1]!=0){
							if (roadObstacles[i+1][j] == 0){
								roadObstacles[i+1][j] = 1;
							}
						}
						if (roadObstacles[i][j] != 0 && roadObstacles[i+1][j+1]!=0){
							if (roadObstacles[i+1][j] == 0){
								roadObstacles[i+1][j] = 1;
							}
						}

					} //end for loop
				} //end for loop
			} //end for loop

			//Test om der er en route
			int[] availablePath;
			availablePath = new int [4];
			availablePath[0]=0;
			availablePath[1]=0;
			availablePath[2]=0;
			availablePath[3]=0;
			for (i=1;i<4;i++){
				for (j=1;j<4;j++){
					if (roadObstacles[i][j] == 0){
						availablePath[j-1]=1;
					}

				}//end for loop
			}//end for loop
			//Test om der er en samlet route

			if (availablePath[0] <= 1 && availablePath[1] <= 1 && availablePath[2] <= 1 && availablePath[3] <= 1){
				availablePathToEnd = true;
			}
		}

		return ObstacleArray;
	}
}