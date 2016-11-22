
class Cell {
	public final int x;
	public final int y;
	private char c;
	private ShipSection ss;
	private Shot sh;
	
	Cell(int x_, int y_, char c_) {
		x = x_;
		y = y_;
		c = c_;
	}
	
	public char getC() {
		return c;
	}
	
	public void setC(char c_) {
		c = c_;
	}
	
	public ShipSection getSS() {
		return ss;
	}
	
	public Shot getSH() {
		return sh;
	}
	
	public void addShipSection(ShipSection section) {
		ss = section;
		c = '#';
	}
	
	public void addShot(Shot shot) {
		sh = shot;
		c = 'X';
	}
	
	public void hit() {
		if (ss != null && sh != null) {
			ss.setHit(true);
		}
	}
}
