

public abstract class Shape {
	public static final int LEFT = 1,RIGHT = 2;
	public int status;
	public int shapeColor;
	public int speed;
	 protected int curPosition[][] ;										//позиция фигуры
	 
	 protected abstract boolean shapeTimeMovementY(int mass[][]); 					// движение по вертикали / изменение координат по Y. Возращает false, если фигура внизу карты
	 
	 protected abstract void shapeMovementX(int direction, int mass[][]);			// горизонтальное движение игроком / Х
	 
	 protected abstract void shapeRotation(int mass[][]) throws ArrayIndexOutOfBoundsException;						// поворот фигуры 
	 
	 public int[][] getCurPosition(){								
		 return curPosition;
	 }
	 
	 protected boolean checkXMovement(int direction,int mass[][]) {
		 try {
		 switch(direction) {
		 case 1:{
			 			return
						(mass[curPosition[0][0]-1][curPosition[0][1]]==0  &&
						mass[curPosition[1][0]-1][curPosition[1][1]]==0 &&
						mass[curPosition[2][0]-1][curPosition[2][1]]==0 &&
						mass[curPosition[3][0]-1][curPosition[3][1]]==0 );
						
		 }
		 case 2:{
			 return
						(mass[curPosition[0][0]+1][curPosition[0][1]]==0  &&
						mass[curPosition[1][0]+1][curPosition[1][1]]==0  &&
						mass[curPosition[2][0]+1][curPosition[2][1]]==0  &&
						mass[curPosition[3][0]+1][curPosition[3][1]]==0 );
		 }
		 
		 }
		 return false;
		 }catch(ArrayIndexOutOfBoundsException e) {
			 return false;
		 }
	 }
	 
	 protected boolean checkYMovement(int mass[][]) {
		 try {
		 return
					(mass[curPosition[0][0]][curPosition[0][1]+1]==0  &&
					mass[curPosition[1][0]][curPosition[1][1]+1]==0  &&
					mass[curPosition[2][0]][curPosition[2][1]+1]==0  &&
					mass[curPosition[3][0]][curPosition[3][1]+1]==0  );
		 }catch(ArrayIndexOutOfBoundsException e) {
			 return false;
		 }
	 }
}
