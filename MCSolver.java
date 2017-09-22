
import java.util.ArrayList;
import java.util.Random;

public class MCSolver {

	State current;
	final int MAX_STEPS = 500;
	ArrayList<int[]> conflictedQueens = new ArrayList<int[]>();

	public MCSolver(State state) {
		current = state;
	}

	/**
	 * Method applies min-conflicts algorithm to the generated state. The
	 * algorithm uses a max number of steps to determine how many time the state
	 * should run through the algorithm so that no queens will have conflicts.
	 * The algorithm chooses a random queen with conflicts and moves it to the
	 * row with the least conflicts for the state while in the loop.
	 * 
	 * @return number of steps taken to complete puzzle
	 */
	public int minConflicts() {
		Random r = new Random();
		for (int i = 0; i < MAX_STEPS; i++) {
			if (current.getHValue() == 0)
				return i;
			conflictedQueens.clear();
			for (int j = 0; j < current.SIZE; j++) { // finds conflicting queens
				for (int k = 0; k < current.SIZE; k++) {
					if (current.board[j][k] == 1) {
						int conflicts = countQueenConflicts(j, k);
						int[] tempArr = { j, k, conflicts };
						if (conflicts != 0)
							conflictedQueens.add(tempArr);
					}
				}
			}
			// chooses random queen with conflicts
			int[] randQ = conflictedQueens.get(r.nextInt(conflictedQueens.size()));
			ArrayList<Integer> rows = new ArrayList<Integer>();
			int tempConflicts = countQueenConflicts(0, randQ[1]);
			rows.add(0);
			// finds row with smallest conflicts for queen, chooses randomly if
			// there is more than one row with the same number of smallest
			// conflicts
			for (int l = 1; l < current.SIZE; l++) {
				if (l != randQ[0]) {
					int temp = countQueenConflicts(l, randQ[1]);
					if (temp < tempConflicts) {
						rows.clear();
						rows.add(l);
						tempConflicts = temp;
					}
					if (temp == tempConflicts)
						rows.add(l);
				}
			}
			// updates the board with the new position for the queen
			current.board[randQ[0]][randQ[1]] = 0;
			current.board[rows.get(r.nextInt(rows.size()))][randQ[1]] = 1;
		}
		return MAX_STEPS;
	}

	/**
	 * The method counts conflicts of the queen in the directions of diagonals
	 * and horizontal in relation to the queen's position.
	 * 
	 * @param j
	 *            row of the queen
	 * @param k
	 *            column of the queen
	 * @return number of conflicts the queen at [j][k] has
	 */
	public int countQueenConflicts(int j, int k) {
		int conflicts = 0;
		int row = j;
		int col = k;
		while (row - 1 >= 0 && col + 1 < current.SIZE) {
			row--;
			col++;
			if (current.board[row][col] == 1)
				conflicts++;
		}
		row = j;
		col = k;
		while (row + 1 < current.SIZE && col - 1 >= 0) {
			row++;
			col--;
			if (current.board[row][col] == 1)
				conflicts++;
		}
		row = j;
		col = k;
		while (col + 1 < current.SIZE) {
			col++;
			if (current.board[row][col] == 1)
				conflicts++;
		}
		row = j;
		col = k;
		while (col - 1 >= 0) {
			col--;
			if (current.board[row][col] == 1)
				conflicts++;
		}
		row = j;
		col = k;
		while (row + 1 < current.SIZE && col + 1 < current.SIZE) {
			row++;
			col++;
			if (current.board[row][col] == 1)
				conflicts++;
		}
		row = j;
		col = k;
		while (row - 1 >= 0 && col - 1 >= 0) {
			row--;
			col--;
			if (current.board[row][col] == 1)
				conflicts++;
		}
		return conflicts;
	}
}
