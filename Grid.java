import java.util.ArrayList;
import java.util.Random;

class Grid {
	private int gridSize = 3;
	public Cell[][] grid = new Cell[gridSize][gridSize];
	ArrayList<Ship> ships = new ArrayList<Ship>();
	public boolean alive = true;
	private int[] shipLengths = {2, 2, 3, 3, 3, 4, 5};
	private char[] headings = {'N', 'S', 'E', 'W'};
	private Random generator = new Random();
	
	Grid() {
		gridSize = 3;
		grid = new Cell[gridSize][gridSize];
		
		setup();
	}
	
	Grid(int size_) {
		gridSize = size_;
		grid = new Cell[gridSize][gridSize];
		
		setup();
	}
	
	public void setGridSize(int x) {
		if (x >= 3) {
			gridSize = x;
			grid = new Cell[gridSize][gridSize];
		} else {
			gridSize = 3;
			grid = new Cell[gridSize][gridSize];
		}
		
		setup();
	} //close method
	
	public int getSize(){
		return gridSize;
	} //close method
	
	public void setup() {
		for (int i = 0; i < gridSize; i++) { //set every cell to '-'
			for (int j = 0; j < gridSize; j++) {
				grid[i][j] = new Cell(i, j, '-');
			}
		}
	} //close setup
	
	public void display() {
		
		char[][] chars = new char[gridSize][gridSize];
		for (int i = 0; i < gridSize; i ++) {
			for (int j = 0; j < gridSize; j++) {
				chars[i][j] = grid[i][j].getC();
			}
		}
		String[] output = new String[gridSize];
		
		for (int i = 0; i < gridSize; i++) {
			output[i] = new String(chars[i]);
		}
		
		for (String line : output) {
			System.out.println(line);
		}
	} //close display

	public Boolean notDead() {
		for (Ship s : ships) {
			if (!s.sunk) {
				alive = true;
				return true;
			}
		}
		alive = false;
		return false;
	}
	
	public Boolean addRandomShips() {
		for (int i : shipLengths) {
			int randomHeading = generator.nextInt(4);
			int randomX = generator.nextInt(gridSize);
			int randomY = generator.nextInt(gridSize);
			new Ship(i, headings[randomHeading], randomX, randomY, this);
		}
		for (Ship j : ships) {
			if (j.built == false) return false;
		}
		return true;
	}
}//close Grid class
