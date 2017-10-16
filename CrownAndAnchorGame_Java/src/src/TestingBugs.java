/**
 * Created by Sarah Fromming on 4/10/2017.
 */

import javafx.application.Application;
import org.junit.*;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;


public class TestingBugs {
    Player player;
    Game game;
    int playerBalance;
    int playerBet;
    DiceValue playerPick;

    @Before
    public void setUp() throws Exception{
        playerBet = 5;
        playerBalance = 100;
        playerPick = DiceValue.HEART;
        player = new Player("Sarah", playerBalance);
    }

    @Test
    public void testIncorrectPayout() throws IOException {
        Field[] attrs = Dice.class.getDeclaredFields();
        for (Field attr: attrs) {
            if (attr.getName().equals("value")) {
                attr.setAccessible(true);
                Dice d1 = new Dice();
                try {
                    attr.set(d1, DiceValue.CROWN);
                } catch (IllegalArgumentException | IllegalAccessException e2) {
                    e2.printStackTrace();
                }
                Dice d2 = new Dice();
                try {
                    attr.set(d2, DiceValue.ANCHOR);
                } catch (IllegalArgumentException | IllegalAccessException e1) {
                    e1.printStackTrace();
                }
                Dice d3 = new Dice();
                try {
                    attr.set(d3, DiceValue.HEART);
                } catch (IllegalArgumentException | IllegalAccessException e) {
                    e.printStackTrace();
                }
                game = new Game(d1, d2, d3);
                break;
            }
        }


        int winnings = game.playRound(player, playerPick, playerBet);
        System.out.println("after match 1, " + player.getName() + " has " + player.getBalance());
        assertEquals(105, player.getBalance());

    }

    @Test
    public void testGameEndAt5() {
        Player player = new Player("Sarah", 100);
        player.setLimit(0);
        int turn = 0;
        int bet = 5;
        while (player.balanceExceedsLimitBy(bet))
        {
            player.takeBet(bet);
            turn++;
        }
        System.out.println(player.getName() + "'s remaining balance is " + player.getBalance());
        System.out.println("turn is " + turn);
        assertEquals(0, player.getBalance());
    }

    @Test
    public void testWinRatio() {
        //Arrange
        double expectedRatio = 0.42;
        double expectedRatioMin = expectedRatio - (0.1 * expectedRatio);
        double expectedRatioMax = expectedRatio + (0.1 * expectedRatio);
        double winCount = 0;
        double totalGames = 0;

        Dice die1 = new Dice();
        Dice die2 = new Dice();
        Dice die3 = new Dice();
        game = new Game(die1, die2, die3);

        playerBalance = 1000;

        //Execute
        while (player.balanceExceedsLimitBy(playerBet) && player.getBalance() < 2 * playerBalance) {
            int winnings = game.playRound(player, playerPick, playerBet);
            winCount += (winnings > 0) ? 1 : 0;
            totalGames++;
        }
        double ratio = winCount / totalGames;
        double ratioTwoDP = (double) Math.round(ratio * 100) / 100;

        System.out.println("Win Ratio is: " + expectedRatio);
        //Assert
        assertTrue(ratioTwoDP >= expectedRatioMin);
        assertTrue(ratioTwoDP <= expectedRatioMax);
    }
}
