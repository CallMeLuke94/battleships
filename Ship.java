import java.util.ArrayList;


public class Ship {
	private int length;
	private char heading = 'N'; //North, South, East, West
	private int x;
	private int y;
	ArrayList<ShipSection> sections = new ArrayList<ShipSection>();
	private Grid g;
	public boolean sunk = false; 
	public boolean built = false;
	
	Ship(Grid g_) {
		g = g_;
		length = 2;
		heading = 'N';
		x = 0;
		y = -length + 1;
		
		buildShip();
		g.ships.add(this);
	} //close constructor
	
	Ship(int l, char h, int xPos, int yPos, Grid g_) {
		g = g_;
		length = l;
		heading = h;
		x = setXPos(xPos);
		y = -setYPos(yPos);

		buildShip();
		
	} //close constructor
	
	public void setLength(int l) {
		if (l > 1 && l < 6) {
			length = l;
		} else if (l < 2) {
			l = 2;
		} else if (l > 5) {
			l = 5;
		}
	} //close setLength
	
	public int getLength() {
		return length;
	}
	
	public void setHeading(char h) { //maybe make an exception here?
		if (h == 'N' || h == 'E' || h == 'S' || h == 'W') {
			heading = h;
		} else {
			heading = 'N';
		}
	} //close setHeading
	
	public char getHeading() {
		return heading;
	}
	
	public int setXPos(int xPos) {
		if (heading == 'N' || heading == 'S') {
			if (xPos >= 0 && xPos < g.getSize()) {
				x = xPos;
			} else if (xPos < 0) {
				x = 0;
			} else if (xPos >= g.getSize()) {
				x = g.getSize() - 1;
			}
		} else if (heading == 'E') {
			if (xPos >= length - 1 && xPos < g.getSize()) {
				x = xPos;
			} else if (xPos < length - 1) {
				x = length - 1;
			} else if (xPos >= g.getSize()) {
				x = g.getSize() - 1;
			}
		} else if (heading == 'W') {
			if (xPos > 0 && xPos <= g.getSize() - length) {
				x = xPos;
			} else if (xPos < 0) {
				x = 0;
			} else if (xPos > g.getSize() - length) {
				x = g.getSize() - length;
			}
		} //close outer if-else block
		return x;
	} //close setXPos
	
	public int getXPos() {
		return x;
	}
	
	public int setYPos(int yPos) {
		if (heading == 'E' || heading == 'W') {
			if (yPos >= 0 && yPos < g.getSize()) {
				y = yPos;
			} else if (yPos < 0) {
				y = 0;
			} else if (yPos >= g.getSize()) {
				y = g.getSize() - 1;
			}
		} else if (heading == 'N') {
			if (yPos >= length - 1 && yPos < g.getSize()) {
				y = yPos;
			} else if (yPos < length - 1) {
				y = length - 1;
			} else if (yPos >= g.getSize()) {
				y = g.getSize() - 1;
			}
		} else if (heading == 'S') {
			if (yPos > 0 && yPos <= g.getSize() - length) {
				y = yPos;
			} else if (yPos < 0) {
				y = 0;
			} else if (yPos > g.getSize() - length) {
				y = g.getSize() - length;
			}
		} //close outer if-else block
		return y;
	} //close setYPos
	
	public int getYPos() {
		return y;
	}
	
	public Boolean cellClear(int x_, int y_) { //checks if a given cell is clear
		if (g.grid[y_ + g.getSize() - 1][x_].getC() == '-') {
			return true;
		} else {
			return false;
		}
		
	}
	
	public Boolean canBuild() { //checks if ships cells are clear
		Boolean[] cells = new Boolean[length];
		switch (heading) {
			case 'N':
				for (int i = 0; i < length; i++) {
					cells[i] = cellClear(x, y + i);
				}
				break;
			case 'E':
				for (int i = 0; i < length; i++) {
					cells[i] = cellClear(x - i, y);
				}
				break;
			case 'S':
				for (int i = 0; i < length; i++) {
					cells[i] = cellClear(x, y - i);
				}
				break;
			case 'W':
				for (int i = 0; i < length; i++) {
					cells[i] = cellClear(x + i, y);
				}
				break;		
		} //close switch
		for (Boolean b : cells) {
			if (b == false) {
				return false;
			}
		}
		return true;
	}
	
	public void buildShip() { //builds ship if placement is legal
		if (canBuild() == true){
			sections.add(new ShipSection(x, y, this, g));
			switch (heading) {
				case 'N':
					for (int i = 1; i < length; i++) {
						sections.add(new ShipSection(x, y + i, this, g));
					}
					break;
				case 'E':
					for (int i = 1; i < length; i++) {
						sections.add(new ShipSection(x - i, y, this, g));
					}
					break;
				case 'S':
					for (int i = 1; i < length; i++) {
						sections.add(new ShipSection(x, y - i, this, g));
					}
					break;
				case 'W':
					for (int i = 1; i < length; i++) {
						sections.add(new ShipSection(x + i, y, this, g));
					}
					break;		
			} //close switch
			built = true;
		} else {
			System.out.println("Illegal Placement! Try Again!");
			built = false;
		}
		g.ships.add(this);
	} //close buildShip

	public Boolean hasSunk() {
		for (ShipSection s : sections) {
			if (!s.isHit()) {
				sunk = false;
				return false;
			}
		}
		sunk = true;
		System.out.println("You've sunk a battle ship!");
		g.notDead();
		return true;
	}
} //close Ship class

