
class ShipSection {
	private final int x;
	private final int y;
	private boolean hit = false;
	private Grid g;
	private Ship s;
	
 	ShipSection(int x_, int y_, Ship s_, Grid g_) { 
		g = g_;
		x = y_ + g.getSize() - 1; //transform coordinates for usability
		y = x_;
		s = s_;
		
		placeOnGrid();
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public Ship getShip() {
		return s;
	}
	
	private void placeOnGrid() {
		g.grid[x][y].addShipSection(this);
	}
	
 	public void setHit(Boolean h) {
		hit = h;
	}
	
	public Boolean isHit() {
		return hit;
	}
} //close ShipSection class
