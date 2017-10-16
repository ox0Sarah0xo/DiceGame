/**
 * Created by Sarah Fromming on 4/10/2017.
 */

import org.junit.*;

import static org.mockito.Mockito.mock;

public class TestingBugs {
    Player player;
    Game game;
    Player player1 = new Player("Sarah", 100);
    int winnings;

    @Before
    public void setUp() throws Exception{
        player = mock(Player.class);
        game = mock(Game.class);
    }

    @Test
    public void testWinningAmountBug() {
        while (player.balanceExceedsLimitBy(5) && player.getBalance() < 200) {

            winnings = game.playRound(player1, DiceValue.getRandom(), 5);
            if (winnings > 0) {
                Assert.assertEquals(100, player1.getBalance());
            } else {
                Assert.assertEquals(95, player1.getBalance());
            }
        }
    }
}
