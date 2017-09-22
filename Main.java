
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		boolean done = false;
		while (!done) {
			try {
				System.out.println(
						"[1] Solve random 22-Queen Puzzle with Steepest-Ascent Hill Climbing and Min-Conflicts.");
				System.out.println("[0] Exit.");
				int input = sc.nextInt();
				if (input == 1) {
					State state = new State();
					System.out.println("Initial State: ");
					System.out.println(state.toString());
					System.out.println("Conflicting Queens: " + state.getHValue());
					System.out.println();

					SAHCSolver sha = new SAHCSolver(state);
					sha.steepestAscentHillClimbing();
					System.out.println("Steepest-Ascent Hill Climbing Final Configuration: ");
					System.out.println(sha.current.toString());
					System.out.println("Conflicting Queens: " + sha.current.getHValue());
					System.out.println();

					MCSolver mc = new MCSolver(state);
					mc.minConflicts();
					System.out.println("Min-Conflicts Final Configuration: ");
					System.out.println(mc.current.toString());
					System.out.println("Conflicting Queens: " + mc.current.getHValue());
				} else if (input == 0) {
					done = true;
				} else if (input != 1 || input != 0) {
					System.out.println("Incorrect input.");
				}
			} catch (Exception e) {
				System.out.println("Incorrect input.");
				sc.next();
			}
		}
		System.out.println("Exiting...");
		sc.close();
		System.exit(0);
	}
}
