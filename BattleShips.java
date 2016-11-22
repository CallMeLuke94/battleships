import java.util.Random;
import java.util.Scanner; 

public class BattleShips {

	public static void main(String[] args) {
		
		int size = 12;
		Grid p1 = new Grid(size);
		Grid p2 = new Grid(size);
		
		randomPhaseOne(p1, p2);
		
		phaseTwoAI(p1, p2);

	} //close main
	
	public static void phaseOne(Grid p1, Grid p2) {
		
		Scanner scanner = new Scanner(System.in);
		Boolean p1turn = true;
		int p1Ships = 0;
		int p2Ships = 0;
		int[] lengths = {2, 2, 3, 3, 3, 4, 5};
		
		System.out.println("Player 1");
		p1.display();
		System.out.println("");
		System.out.println("Player 2");
		p2.display();
		System.out.println("=====================");
		System.out.println("");
		
		while (p1Ships < lengths.length || p2Ships < lengths.length) {
			Boolean hasPlaced = false;
			if (p1turn) {
				System.out.println("Player 1 place your next ship.");
				System.out.println("The ship is " + lengths[p1Ships] + " spaces long.");
			} else {
				System.out.println("Player 2 place your next ship.");
				System.out.println("The ship is " + lengths[p2Ships] + " spaces long.");
			}
			
			System.out.print("Enter a heading (N, E, S, W): ");
			char nIn = scanner.next().toUpperCase().charAt(0);
			if (!validHeading(nIn)) {
				System.out.print("That is now a valid heading. Try again. \n");
				System.out.print("Enter a heading (N, E, S, W): ");
				nIn = scanner.next().toUpperCase().charAt(0);
			}
			
			System.out.print("Enter x-coordinate: ");
			while (!scanner.hasNextInt()) {
				   System.out.println("You must enter an integer!");
				   System.out.print("Enter x-coordinate: ");
				   scanner.nextLine();
				}
			int xIn = scanner.nextInt();
			System.out.print("Enter y-coordinate: ");
			while (!scanner.hasNextInt()) {
				   System.out.println("You must enter an integer!");
				   System.out.print("Enter y-coordinate: ");
				   scanner.nextLine();
				}
			int yIn = scanner.nextInt();
			
			if (p1turn) {
				hasPlaced = new Ship(lengths[p1Ships], nIn, xIn, yIn, p1).built;
				if (hasPlaced) p1Ships += 1;
			}
			else {
				hasPlaced = new Ship(lengths[p2Ships], nIn, xIn, yIn, p2).built;
				if (hasPlaced) p2Ships += 1;
			}
			
			System.out.println("");
			System.out.println("Player 1");
			p1.display();
			System.out.println("");
			System.out.println("Player 2");
			p2.display();
			System.out.println("=====================");
			System.out.println("");
			
			if (hasPlaced) p1turn = !p1turn;
		} //close while
		
	} //close phaseOne
	
	public static void randomPhaseOne(Grid p1, Grid p2) {
		Boolean c = p2.addRandomShips();
		if (!c) {
			c = p2.addRandomShips();
		}
		Boolean b = p1.addRandomShips();
		if (!b) {
			b = p1.addRandomShips();
		}
	}
	
	public static void phaseTwo(Grid p1, Grid p2) {
		Scanner scanner = new Scanner(System.in); 
		Boolean p1turn = true;
		
		System.out.println("Player 1");
		p1.display();
		System.out.println("");
		System.out.println("Player 2");
		p2.display();
		System.out.println("=====================");
		System.out.println("");
		
		while (p1.alive && p2.alive) {
			Boolean hasPlayed = false;
			if (p1turn) {
				System.out.println("Player 1's Turn");
			} else {
				System.out.println("Player 2's Turn");
			}
			System.out.print("Enter x-coordinate: ");
			while (!scanner.hasNextInt()) {
				   System.out.println("You must enter an integer!");
				   System.out.print("Enter x-coordinate: ");
				   scanner.nextLine();
				}
			int xIn = scanner.nextInt(); 
			System.out.print("Enter y-coordinate: ");
			while (!scanner.hasNextInt()) {
				   System.out.println("You must enter an integer!");
				   System.out.print("Enter y-coordinate: ");
				   scanner.nextLine();
				}
			int yIn = scanner.nextInt();
			
			if (p1turn) {
				hasPlayed = new Shot(xIn, yIn, p2).fire();
			}
			else {
				hasPlayed = new Shot(xIn, yIn, p1).fire();
			}
			
			System.out.println("");
			System.out.println("Player 1");
			p1.display();
			System.out.println("");
			System.out.println("Player 2");
			p2.display();
			System.out.println("=====================");
			System.out.println("");
			
			
			if (hasPlayed) p1turn = !p1turn;
		}
		
		if (p1.alive && !p2.alive) {
			System.out.println("Player 1 wins!");
		} else if (!p1.alive && p2.alive) {
			System.out.println("Player 2 wins!");
		}
	} //close phaseTwo
	
	public static Boolean validHeading(char c) {
		if (c == 'N') return true;
		else if (c == 'E') return true;
		else if (c == 'S') return true;
		else if (c == 'W') return true;
		else return false;
	}
	
	public static void phaseOneAI(Grid p1, Grid p2) {
		Boolean c = p2.addRandomShips();
		if (!c) {
			c = p2.addRandomShips();
		}
		
		Scanner scanner = new Scanner(System.in);
		int p1Ships = 0;
		int[] lengths = {2, 2, 3, 3, 3, 4, 5};
		
		System.out.println("Player 1");
		p1.display();
		System.out.println("");
		System.out.println("Player 2");
		p2.display();
		System.out.println("=====================");
		System.out.println("");
		
		while (p1Ships < lengths.length) {
			Boolean hasPlaced = false;
			System.out.println("Place your next ship.");
			System.out.println("The ship is " + lengths[p1Ships] + " spaces long.");
			
			System.out.print("Enter a heading (N, E, S, W): ");
			char nIn = scanner.next().toUpperCase().charAt(0);
			if (!validHeading(nIn)) {
				System.out.print("That is now a valid heading. Try again. \n");
				System.out.print("Enter a heading (N, E, S, W): ");
				nIn = scanner.next().toUpperCase().charAt(0);
			}
			
			System.out.print("Enter x-coordinate: ");
			while (!scanner.hasNextInt()) {
				   System.out.println("You must enter an integer!");
				   System.out.print("Enter x-coordinate: ");
				   scanner.nextLine();
				}
			int xIn = scanner.nextInt();
			System.out.print("Enter y-coordinate: ");
			while (!scanner.hasNextInt()) {
				   System.out.println("You must enter an integer!");
				   System.out.print("Enter y-coordinate: ");
				   scanner.nextLine();
				}
			int yIn = scanner.nextInt();
			
			
			hasPlaced = new Ship(lengths[p1Ships], nIn, xIn, yIn, p1).built;
			if (hasPlaced) p1Ships += 1;
			
			
			System.out.println("");
			System.out.println("Player 1");
			p1.display();
			System.out.println("");
			System.out.println("Player 2");
			p2.display();
			System.out.println("=====================");
			System.out.println("");
			
		} //close while
	} //close phaseOneAI
	
	public static void phaseTwoAI(Grid p1, Grid p2) {
		Scanner scanner = new Scanner(System.in); 
		Boolean p1turn = true;
		
		System.out.println("Player 1");
		p1.display();
		System.out.println("");
//		System.out.println("Player 2");
//		p2.display();
		System.out.println("=====================");
		System.out.println("");
		
		while (p1.alive && p2.alive) {
			Boolean hasPlayed = false;
			int xIn;
			int yIn;
			if (p1turn) {
				System.out.println("Player 1's Turn");
				System.out.print("Enter x-coordinate: ");
				while (!scanner.hasNextInt()) {
					   System.out.println("You must enter an integer!");
					   System.out.print("Enter x-coordinate: ");
					   scanner.nextLine();
					}
				xIn = scanner.nextInt();
				System.out.print("Enter y-coordinate: ");
				while (!scanner.hasNextInt()) {
					   System.out.println("You must enter an integer!");
					   System.out.print("Enter y-coordinate: ");
					   scanner.nextLine();
					}
				yIn = scanner.nextInt();
			} else {
				System.out.println("Player 2's Turn");
				Random generator = new Random();
				xIn = generator.nextInt(p1.getSize());
				yIn = generator.nextInt(p1.getSize());
			}
			
			if (p1turn) {
				Shot thisShot = new Shot(xIn, yIn, p2);
				hasPlayed = thisShot.fire();
				thisShot.marker(p1);
			}
			else {
				hasPlayed = new Shot(xIn, yIn, p1).fire();
			}
			
			System.out.println("");
			System.out.println("Player 1");
			p1.display();
			System.out.println("");
//			System.out.println("Player 2");
//			p2.display();
			System.out.println("=====================");
			System.out.println("");
			
			
			if (hasPlayed) p1turn = !p1turn;
		}
		
		if (p1.alive && !p2.alive) {
			System.out.println("Player 1 wins!");
		} else if (!p1.alive && p2.alive) {
			System.out.println("Player 2 wins!");
		}
	} //close phaseTwoAI

} //close BattleShips class
