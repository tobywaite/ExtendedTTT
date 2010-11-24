import java.util.Arrays;


public class Game {

	private TTTMove[] gameBoard;
	private GameState status;
	
	private QLearner agent;
	private RandomBalanced opponent;
	private Agent current;
	
	public Game(int boardSize, QLearner qLearner, RandomBalanced randBal) {
		// initialize game board to boarSize^2 length array of Empty squares. 
		gameBoard = new TTTMove[boardSize*boardSize];
		Arrays.fill(gameBoard, TTTMove.Empty);
		
		status = GameState.InProgress;
		
		agent = qLearner;
		opponent = randBal;
		
	}

	public void play() {

		while(status == GameState.InProgress){
			int move = current.pickMove(this);
			try{
			executeMove(move);
			updateGameState();
			} catch(InvalidMoveException e){
				System.out.println("Agent tried to make invalid move!");
				System.exit(1);
			}
		}
		
	}

	private void updateGameState() {
		// examine game state, update game state accordingly.
		// includes testing for termination conditions, then determining final score.
		
	}

	private void executeMove(int move) throws InvalidMoveException{
		// attempt to execute move. If move cannot be executed, throw exception.
		
	}

	public GameState getState() {
		// TODO Auto-generated method stub
		return null;
	}

	public void test() {
		// run game without altering agents.
		
	}
}
