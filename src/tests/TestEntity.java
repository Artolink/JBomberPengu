package tests;

import static org.junit.Assert.*;

import model.player.Player;

public class TestEntity {

	// verifica base di Player() - Emanuele
	@org.junit.Test
	public void testPlayer() {
		final Player player = new Player("ugo");
		assertEquals(player.getName(), "ugo");
		assertEquals(player.getPoints(), new Integer(0));
		
		player.addPoints(10);
		assertEquals(player.getPoints(), new Integer(10));
		
		player.addPoints(-5);
		assertEquals(player.getPoints(), new Integer(5));
	}

}
