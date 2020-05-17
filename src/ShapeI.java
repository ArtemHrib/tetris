
public class ShapeI extends Shape{
	public ShapeI() {
		shapeColor = 3;
		status = 2;
		curPosition = new int[4][2] ;
		curPosition[0][0] = 3;
		curPosition[0][1] = 1;
		
		curPosition[1][0] = 4;
		curPosition[1][1] = 1;
		
		curPosition[2][0] = 5;
		curPosition[2][1] = 1;
		
		curPosition[3][0] = 6;
		curPosition[3][1] = 1;
	}
	@Override
	protected boolean shapeTimeMovementY(int mass[][]) {
		if(curPosition[3][1]+1<20 && checkYMovement(mass)) {
			curPosition[0][1]++;
			curPosition[1][1]++;
			curPosition[2][1]++;
			curPosition[3][1]++;
			return true;
			}else {
				return false;
			}
			
	}

	@Override
	protected void shapeMovementX(int direction, int mass[][]) {
		switch(direction) {
		case 1:{
			if(curPosition[3][0]-1>=0 && checkXMovement(1,mass)) {
				
				curPosition[0][0]--;
				curPosition[1][0]--;
				curPosition[2][0]--;
				curPosition[3][0]--;
				
			}
		}break;
		case 2:{
			if(curPosition[0][0]+1<10  && checkXMovement(2,mass)) {
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
		
		if(status == 2 && curPosition[3][0]-2>=0 && curPosition[0][0]+1 < 10) {
			if(mass[curPosition[3][0]-2][curPosition[3][1]-2] == 0  && 
					mass[curPosition[2][0]-1][curPosition[2][1]-1] == 0  &&
					mass[curPosition[0][0]+1][curPosition[0][1]+1] == 0 )
			{
				
			status = 1;
			curPosition[3][0] -= 2;
			curPosition[3][1] -= 2;
			
			curPosition[2][0] -= 1;
			curPosition[2][1] -= 1;
			
			curPosition[0][0] += 1;
			curPosition[0][1] += 1;
			}
		}else if(status == 1 && curPosition[3][1]+2>=0 ) {
			if(mass[curPosition[3][0]+2][curPosition[3][1]+2] == 0  &&
					mass[curPosition[2][0]+1][curPosition[2][1]+1] == 0  &&
					mass[curPosition[0][0]-1][curPosition[0][1]-1] == 0 ) 
			{
			status = 2;
			curPosition[3][0] += 2;
			curPosition[3][1] += 2;
			
			curPosition[2][0] += 1;
			curPosition[2][1] += 1;
			
			curPosition[0][0] -= 1;
			curPosition[0][1] -= 1;
			}
		}
		
	}
	
}
