/**
 * Plays an extended game of tic tac toe, per the rules described in Programming Assignment 2 for EECS491.
 */

/**
 * @author Toby Waite
 * @date December 2nd, 2010 
 */

public class ExtendedTTT {

	// number of games to play when evaluating current policy
	private static final int evalGames = 5;

	// match parameters
	private int numGames;	
	private int boardSize;
	
	// agents that participate in game
	private QLearner agent;
	private RandomBalanced opponent;

	public ExtendedTTT(int size, int games) {
		numGames = games;
		boardSize = size;
		agent = new QLearner(size);
		opponent = new RandomBalanced(size);
	}

	public static void main(String[] args) {
		
		// check number of command line arguments.
		if(args.length != 2){
			printUsage();
			System.exit(0);
		}

		// Create a new match based on the input arguments
		int size = Integer.parseInt(args[0]);
		int games = Integer.parseInt(args[1]);
		ExtendedTTT match = new ExtendedTTT(size,games);
		
		// Initialize output scheme
		System.out.println("Games Played \t\t Avgerage Final Score");
		
		// run match!
		match.run();		
	}

	// Runs a match for the number of games specified during initialization. 
	// Every 50 games (starting at 0) we pause to evaluate the current policy of our Agent. 
	private void run() {

		
		int gamesPlayed = 0;
		while(gamesPlayed < numGames){
		
			// test policy every 50 games
			if(gamesPlayed % 50 == 0){
				testPolicy(gamesPlayed);
			}
			// create a new game and play it.
			Game game = new Game(boardSize, agent, opponent);
			game.play();
			gamesPlayed++;
		}
	}

	// Tests the current policy of our Q learning agent. Runs 5 games and prints the win percentage.
	private void testPolicy(int gamesPlayed) {
		int wins = 0;
		for(int i = 0; i < evalGames; i++){
			Game testGame = new Game(boardSize, agent, opponent);
			testGame.test();
			if(testGame.getState()==GameState.Won)
				wins++;
		}
		System.out.println(gamesPlayed + "\t\t\t\t" + (double)wins/evalGames);
	}

	// Print usage of ExtendedTTT to the terminal.
	private static void printUsage() {
		System.out.println("Usage: java ExtendedTTT <BoardSize> <NumberOfGames>");
		System.out.println("Please see README for details and usage examples.");
	}
}