/**
 * Created by Sarah Fromming on 4/10/2017.
 */

import org.junit.*;
import org.mockito.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;


public class TestingBugs {
    Player player;
    Game game;
    int playerBalance;
    int playerBet;
    Dice mock = org.mockito.Mockito.mock(Dice.class);

    @Before
    public void setUp() throws Exception{
        playerBet = 5;
        playerBalance = 100;
        game = new Game(mock, mock, mock);
        player = new Player("Sarah", playerBalance);
    }

    @Test
    public void testIncorrectPayoutBug() {
        //Arrange
        DiceValue playerPick = DiceValue.ANCHOR;
        when(mock.getValue()).thenReturn(DiceValue.ANCHOR);
        when(mock.getValue()).thenReturn(DiceValue.CLUB);
        when(mock.getValue()).thenReturn(DiceValue.CLUB);

        int expectedPlayerBalance = playerBalance + playerBet;

        //Execution
        game.playRound(player, playerPick, playerBet);

        //Result
        assertFalse(player.getBalance() == expectedPlayerBalance);
    }
}
