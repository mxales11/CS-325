package frs.hotgammon;
import frs.hotgammon.framework.Game;
import frs.hotgammon.variants.*;

public interface RollDeterminer {

public void setGame(Game game);
public int[] diceThrown();
public void rollDice(int turnNumber);
public void setRandomDiceGenerator(RandomDiceGenerator randomDiceGenarator);

}