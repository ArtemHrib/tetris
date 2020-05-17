

public class ShapeO extends Shape{
	public ShapeO() {
		shapeColor = 4;
		curPosition = new int[4][2] ;
		curPosition[0][0] = 4;
		curPosition[0][1] = 1;
		
		curPosition[1][0] = 5;
		curPosition[1][1] = 1;
		
		curPosition[2][0] = 4;
		curPosition[2][1] = 2;
		
		curPosition[3][0] = 5;
		curPosition[3][1] = 2;
	}
	@Override
	protected boolean shapeTimeMovementY(int mass[][]) {
		if(curPosition[3][1]+1<20 && checkYMovement(mass)) {
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
			if(curPosition[0][0]-1>=0 && checkXMovement(1,mass)) {
				curPosition[0][0]--;
				curPosition[1][0]--;
				curPosition[2][0]--;
				curPosition[3][0]--;
			}
		}break;
		case 2:{
			if(curPosition[1][0]+1<10 && checkXMovement(2,mass)) {
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
		// TODO Auto-generated method stub
		
	}
}
