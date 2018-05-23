package es.codeurjc.ais.tictactoe;


import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import es.codeurjc.ais.tictactoe.Board;

public class BoardUnitTest {
	private Board mesa;
	
	public void mark(String label, int cell) {
		mesa.getCell(cell).value = label;
		mesa.getCell(cell).active = false;
	}
	
	@Before
	public void setUp() {
		this.mesa = new Board();
		this.mesa.enableAll();
	}
	/*
	 * O | O | O
	 * X | X | 
	 *   |   | X
	 */
	@Test
	public void ganaJugadorO() {
		assertFalse(mesa.checkDraw());
		assertNull(mesa.getCellsIfWinner("X"));
		assertNull(mesa.getCellsIfWinner("O"));
		mark("O",0);
		mark("X",4);
		mark("O",1);
		mark("X",3);
		assertFalse(mesa.checkDraw());
		assertNull(mesa.getCellsIfWinner("X"));
		assertNull(mesa.getCellsIfWinner("O"));
		mark("O",2);
		mark("X",8);
		assertFalse(mesa.checkDraw());
		assertNull(mesa.getCellsIfWinner("X"));
		assertArrayEquals(new int[] {0,1,2}, mesa.getCellsIfWinner("O"));
	}
	/*
	 * X | X | X
	 * O | O | 
	 *   |   | O
	 */
	@Test
	public void PierdeJugadorO() {
		assertFalse(mesa.checkDraw());
		assertNull(mesa.getCellsIfWinner("X"));
		assertNull(mesa.getCellsIfWinner("O"));
		mark("O",4);
		mark("X",0);
		mark("O",3);
		mark("X",1);
		assertFalse(mesa.checkDraw());
		assertNull(mesa.getCellsIfWinner("X"));
		assertNull(mesa.getCellsIfWinner("O"));
		mark("O",8);
		mark("X",2);
		assertFalse(mesa.checkDraw());
		assertNull(mesa.getCellsIfWinner("O"));
		assertArrayEquals(new int[] {0,1,2}, mesa.getCellsIfWinner("X"));
	}

	/*
	 * O | X | X
	 * O | O | X
	 * X | O | O
	 */
	@Test
	public void pierdeJugadorXCompletandoTablero() {
		assertFalse(mesa.checkDraw());
		assertNull(mesa.getCellsIfWinner("X"));
		assertNull(mesa.getCellsIfWinner("O"));
		mark("O",4);
		mark("X",1);
		mark("O",0);
		mark("X",2);
		assertFalse(mesa.checkDraw());
		assertNull(mesa.getCellsIfWinner("X"));
		assertNull(mesa.getCellsIfWinner("O"));
		mark("O",3);
		mark("X",5);
		mark("O",7);
		mark("X",6);
		mark("O",8);
		assertTrue(mesa.checkDraw());
		assertNull(mesa.getCellsIfWinner("X"));
		assertArrayEquals(new int[] {0,4,8}, mesa.getCellsIfWinner("O"));
	}
	
	/*
	 * O | X | O
	 * O | X | X
	 * X | O | X
	 */
	@Test
	public void empate() {
		assertFalse(mesa.checkDraw());
		assertNull(mesa.getCellsIfWinner("X"));
		assertNull(mesa.getCellsIfWinner("O"));
		mark("X",4);
		mark("O",0);
		mark("X",1);
		mark("O",2);
		assertFalse(mesa.checkDraw());
		assertNull(mesa.getCellsIfWinner("X"));
		assertNull(mesa.getCellsIfWinner("O"));
		mark("X",5);
		mark("O",3);
		mark("X",6);
		mark("O",7);
		mark("X",8);
		assertTrue(mesa.checkDraw());
		assertNull(mesa.getCellsIfWinner("X"));
		assertNull(mesa.getCellsIfWinner("O"));
	}

}
