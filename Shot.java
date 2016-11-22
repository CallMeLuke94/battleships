
public class Shot {
	private final int x;
	private final int y;
	Grid g;
	private boolean hasHit = false;
	
	Shot(int x_, int y_, Grid g_) {
		g = g_;
		x = setX(y_); //transform coordinates for usability
		y = setY(x_);
		
	} //close constructor
	
	private int setY(int x_) {
		int z = 0;
		if (x_ >= 0 && x_ < g.getSize()) {
			z = x_;
		} else if (x_ < 0) {
			z = 0;
		} else if (x_ >= g.getSize() - 1) {
			z = g.getSize() - 1;
		}
		return z;
	} //close setY
	
	private int setX(int y_) {
		int z = 0;
		if (y_ > -1 && y_ < g.getSize()) {
			z = g.getSize() - y_ - 1;
			return z;
		} else if (y_ <= -1) {
			z = g.getSize() - 1;
			return z;
		} else if (y_ >= g.getSize()) {
			z = 0;
			return z;
		} else {
			return z;
		}
	} //close setX
	
	public Boolean fire() {
		if (g.grid[x][y].getSH() != null) {
			System.out.println("You've already shot here, try again!");
			return false;
		} else if (g.grid[x][y].getSS() != null) {
			g.grid[x][y].addShot(this);
			g.grid[x][y].hit();
			System.out.println("Hit! You get another shot!");
			hasHit = true;
			g.grid[x][y].getSS().getShip().hasSunk();
			return false;
		} else {
			g.grid[x][y].addShot(this);
			System.out.println("Miss! That's the end of your turn!");
			return true;
		}
	} //close fire
	
	public void marker(Grid p) {
		if (this.hasHit) p.grid[x][y].setC('$');
		else p.grid[x][y].setC('O');
	}
	
} //close class
