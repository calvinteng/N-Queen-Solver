
import java.util.Random;

public class State {
	public final int SIZE = 22;
	public int[][] board = new int[SIZE][SIZE];
	public State successor;
	private int h;

	public State() {
		initializeState();
		getHValue();
	}

	public State(int[][] b) {
		board = b;
		getHValue();
	}

	/**
	 * @return clone of the current board
	 */
	public int[][] cloneBoard() {
		int[][] clone = new int[SIZE][SIZE];
		for (int i = 0; i < SIZE; i++)
			for (int j = 0; j < SIZE; j++)
				clone[i][j] = board[i][j];
		return clone;
	}

	/**
	 * Creates random state after the initial state(empty board). 1s represent
	 * Queens, while 0s represent empty spaces on the board.
	 */
	private void initializeState() {
		Random rand = new Random();
		for (int j = 0; j < SIZE; j++) {
			int row = rand.nextInt(SIZE);
			board[row][j] = 1;
		}
	}

	/**
	 * @return the h value, or number of collisions of queens on board
	 */
	public int getHValue() {
		h = 0;
		for (int i = 0; i < SIZE; i++)
			for (int j = 0; j < SIZE; j++)
				if (board[i][j] == 1) {
					h += checkCollisions(i, j);
				}
		return h;
	}

	/**
	 * Method checks the upper right and lower left diagonals and right
	 * horizontal for collisions.
	 * 
	 * @param row
	 *            parameter for row position of queen
	 * @param col
	 *            parameter for column position of queen
	 * @return number of collisions for one queen
	 */
	private int checkCollisions(int row, int col) {
		int i = row, j = col;
		int collisions = 0;
		while (i - 1 >= 0 && j + 1 < SIZE) {
			i--;
			j++;
			if (board[i][j] == 1)
				collisions++;
		}
		i = row;
		j = col;
		while (j + 1 < SIZE) {
			j++;
			if (board[row][j] == 1)
				collisions++;
		}
		i = row;
		j = col;
		while (i + 1 < SIZE && j + 1 < SIZE) {
			i++;
			j++;
			if (board[i][j] == 1)
				collisions++;
		}
		return collisions;
	}

	public String toString() {
		String str = "";
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++)
				str += board[i][j] + " ";
			str += "\n";
		}
		return str;
	}
}
