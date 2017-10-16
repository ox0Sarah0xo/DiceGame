/**
 * Created by Sarah Fromming on 4/10/2017.
 */

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
}
