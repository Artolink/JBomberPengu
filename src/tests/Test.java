package tests;

import static org.junit.Assert.*;

import model.player.Player;

public class Test {

	// verifica base di Player()
	@org.junit.Test
	public void testPlayer() {
		final Player player = new Player("ugo");
		assertEquals(player.getName(), "ugo");
	}

}
