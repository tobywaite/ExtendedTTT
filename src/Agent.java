import java.util.Random;

public abstract class Agent {

	protected static Random r;
	
	public TTTMove moveType;

	public abstract int pickMove(Game game);

	public abstract void reportMove(int move, Game game);
	
}
