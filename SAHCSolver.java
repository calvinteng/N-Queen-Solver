
import java.util.Comparator;

public class SAHCSolver {

	State current;

	public SAHCSolver(State state) {
		current = state;
	}

	/**
	 * Method performs steepest-ascent hill climbing. Loop runs until there are
	 * no successors with a lower amount of queen conflicts than the current
	 * number of queen conflicts. This algorithm uses a greedy approach to
	 * choose the successor with the least amount of conflicts. There will be (
	 * n * n ) - n successors where n is the number of row/columns in the board.
	 * 
	 * @return number of steps taken to complete puzzle
	 */
	public int steepestAscentHillClimbing() {
		int steps = 0;
		while (true) {
			steps++;
			findSuccessor();
			if (current.successor.getHValue() >= current.getHValue())
				return steps;
			current = current.successor;
		}
	}

	/**
	 * Finds all the queens and calculates the number of conflicts for each
	 * queen's possible successor. If the successor has less conflicts, it
	 * becomes the current state's successor.
	 */
	public void findSuccessor() {
		for (int i = 0; i < current.SIZE; i++) {
			for (int j = 0; j < current.SIZE; j++) {
				if (current.board[i][j] == 1) {
					for (int k = 0; k < current.SIZE; k++) {
						State newState = new State(current.cloneBoard());
						newState.board[i][j] = 0;
						if (k != i) {
							newState.board[k][j] = 1;
							if (current.successor == null || newState.getHValue() < current.successor.getHValue())
								current.successor = newState;
						}
					}
				}
			}
		}
	}

	class HComparator implements Comparator<State> {
		@Override
		public int compare(State o1, State o2) {
			return o1.getHValue() - o2.getHValue();
		}
	}

}
