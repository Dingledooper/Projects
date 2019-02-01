class Move {
	int x;
	int y;
	
	public Move(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

public class TicTacToe {
	
	static char player = 'X';
	static char opponent = 'O';

	public static void main(String[] args) {
		char[][] board = new char[][] {
			{' ', ' ', ' '},
			{' ', ' ', ' '},
			{' ', ' ', ' '}
		};
		
		Move optimum = optimum(board);
		System.out.println("Row: " + optimum.x);
		System.out.println("Col: " + optimum.y);
	}
	
	private static Move optimum(char[][] board) {
		Move bestMove = new Move(-1, -1);
		int bestVal = -1000;
		
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (board[i][j] == ' ') {
					board[i][j] = player;
					int val = minimax(board, 0, false);
					board[i][j] = ' ';
					
					if (val > bestVal) {
						bestMove.x = i;
						bestMove.y = j;
						bestVal = val;
					}
				}
			}
		}
		return bestMove;
	}
	
	private static int minimax(char[][] board, int depth, boolean isMax) {
		int score = eval(board);
		if (score == 10 || score == -10) {
			return score;
		}
		
		if (!isMoves(board)) {
			return 0;
		}

		int best = isMax ? -1000 : 1000;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (board[i][j] == ' ') {
					board[i][j] = isMax ? player : opponent;
					best = isMax ? Math.max(best, minimax(board, depth + 1, !isMax)) : 
								   Math.min(best, minimax(board, depth + 1, !isMax));
					board[i][j] = ' ';
				}
			}
		}
		return best;
	}

	private static int eval(char[][] board) {
		for (int r = 0; r < 3; r++) {
			if (board[r][0] == board[r][1] && board[r][1] == board[r][2]) {
				return board[r][0] == player ? 10 : -10;
			}
		}
		
		for (int c = 0; c < 3; c++) {
			if (board[0][c] == board[1][c] && board[1][c] == board[2][c]) {
				return board[0][c] == player ? 10 : -10;
			}
		}
		
		if (board[0][0] == board[1][1] && board[1][1] == board[2][2]) {
			return board[0][0] == player ? 10 : -10;
		} 
		
		if (board[0][2] == board[1][1] && board[1][1] == board[2][0]) {
			return board[0][0] == player ? 10 : -10;
		} 
		
		return 0;
	}
	
	private static boolean isMoves(char[][] board) {
		for (char[] r : board) {
			for (char c : r) {
				if (c == ' ') {
					return true;
				}
			}
		}
		return false;
	}

}