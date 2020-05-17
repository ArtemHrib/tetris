

public class ShapeS extends Shape{
	public ShapeS() {
		shapeColor = 2;
		status = 1;
		curPosition = new int[4][2] ;
		curPosition[0][0] = 4;
		curPosition[0][1] = 1;
		
		curPosition[1][0] = 5;
		curPosition[1][1] = 1;
		
		curPosition[2][0] = 3;
		curPosition[2][1] = 2;
		
		curPosition[3][0] = 4;
		curPosition[3][1] = 2;
	}
	
	@Override
	protected boolean shapeTimeMovementY(int mass[][]) {
		if(curPosition[2][1]+1 < 20 && checkYMovement(mass)) {
			curPosition[0][1]++;
			curPosition[1][1]++;
			curPosition[2][1]++;
			curPosition[3][1]++;
			}else {
				return false;
			}
			return true;
	}

	@Override
	protected void shapeMovementX(int direction, int mass[][]) {
		switch(direction) {
		case 1:{
			if(curPosition[2][0]-1 >= 0 && checkXMovement(1,mass)) {
				curPosition[0][0]--;
				curPosition[1][0]--;
				curPosition[2][0]--;
				curPosition[3][0]--;
			}
		}break;
		case 2:{
			if(curPosition[1][0]+1 < 10 && checkXMovement(2,mass)) {
				curPosition[0][0]++;
				curPosition[1][0]++;
				curPosition[2][0]++;
				curPosition[3][0]++;
			}
		}break;
		}
		
	}

	@Override
	protected void shapeRotation(int mass[][]) {
		try {
		if(status == 1 ) {
			if(mass[curPosition[1][0]-1][curPosition[1][1]-1] == 0  &&
					mass[curPosition[3][0]+1][curPosition[3][1]-1] == 0  &&
					mass[curPosition[2][0]+2][curPosition[2][1]] == 0  ) 
			{
			status = 2;
			curPosition[1][0] -= 1;
			curPosition[1][1] -= 1;
			
			curPosition[3][0] += 1;
			curPosition[3][1] -= 1;
			
			curPosition[2][0] +=2;
			}
		}else if(status ==2 && curPosition[2][0]-2 >= 0) {
			if(mass[curPosition[1][0]+1][curPosition[1][1]+1] == 0  &&
					mass[curPosition[3][0]-1][curPosition[3][1]+1] == 0 &&
					mass[curPosition[2][0]-2][curPosition[2][1]] == 0 ) 
			{
			status = 1;
			curPosition[1][0] += 1;
			curPosition[1][1] += 1;
			
			curPosition[3][0] -= 1;
			curPosition[3][1] += 1;
			
			curPosition[2][0] -=2;
			}
		}
	}catch(ArrayIndexOutOfBoundsException e) {
		
	}
	}

}
