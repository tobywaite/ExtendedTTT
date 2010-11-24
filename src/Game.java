import java.util.Arrays;


public class Game {

	private TTTMove[] gameBoard;
	private GameState status;
	private int movesExecuted;
	
	private QLearner agent;
	private RandomBalanced opponent;
	private Agent current;
	
	public Game(int boardSize, QLearner qLearner, RandomBalanced randBal) {
		// initialize game board to boarSize^2 length array of Empty squares. 
		gameBoard = new TTTMove[boardSize*boardSize];
		Arrays.fill(gameBoard, TTTMove.Empty);
		
		status = GameState.InProgress;
		movesExecuted = 0;
		
		agent = qLearner;
		opponent = randBal;
	}

	public void play() {

		while(status == GameState.InProgress){
			int move = current.pickMove(this);
			try{
			executeMove(move);
			movesExecuted++;
			updateGameState();
			current.reportMove(move, this);
			current = (current == agent) ? opponent : agent;
			} catch(InvalidMoveException e){
				System.out.println("Agent tried to make invalid move!");
				System.exit(1);
			}
		}
	}

	private void updateGameState() {
		if(status == GameState.Invalid)
			return; // don't update status if game is already invalid!
		else if(movesExecuted == gameBoard.length)
			status = (calcScore(agent.moveType) > calcScore(opponent.moveType) ? GameState.XWin : GameState.OWin);
		else
			status = GameState.InProgress;
	}

	private int calcScore(TTTMove moveType) {
		// TODO Auto-generated method stub
		return 0;
	}

	private void executeMove(int move) throws InvalidMoveException{
		// attempt to execute move. If move cannot be executed, throw exception.
		if(move < 0 || move > gameBoard.length && gameBoard[move] != TTTMove.Empty)
			throw new InvalidMoveException("Invalid Move!");

		gameBoard[move] = (current == agent) ? agent.moveType : opponent.moveType;
	}

	public GameState getState() {
		return status;
	}

	public void test() {
		// run game without altering agents.
		
	}
}
