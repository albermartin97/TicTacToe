package es.codeurjc.ais.tictactoe;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


import org.mockito.ArgumentCaptor;

import es.codeurjc.ais.tictactoe.TicTacToeGame.EventType;
import es.codeurjc.ais.tictactoe.TicTacToeGame.WinnerResult;
import es.codeurjc.ais.tictactoe.TicTacToeGame.WinnerValue;
import static org.mockito.Mockito.*;

import org.hamcrest.*;


public class TictacToeGameTest {
	TicTacToeGame game;
	Connection c1;
	Connection c2;
	Player p1;
	Player p2;
	
	@Before
	public void setUp() throws Exception {
		game = new TicTacToeGame();
		c1 = mock(Connection.class);
		this.game.addConnection(c1);
		c2 = mock(Connection.class);
		
		p1 = new Player(1,"X","Alberto");
		p2 = new Player(2,"O","Alejandro");
		this.game.addPlayer(p1);
		verify(c1,times(1)).sendEvent(EventType.JOIN_GAME, this.game.getPlayers());
		this.game.addConnection(c2);
		this.game.addPlayer(p2);
	}
	
	/*
	 * X | X | X
	 * O | O | 
	 *   |   | O
	 */
	@Test
	public void ganaJugador1() {
		
		/*Verificamos que los jugadores se hayan introducido en la lista, se ha llamado al evento*/
		verify(c2,times(1)).sendEvent(EventType.JOIN_GAME, this.game.getPlayers());
		verify(c1,times(2)).sendEvent(EventType.JOIN_GAME, this.game.getPlayers());
		
		/*Verificamos que los turnos iniciales sean los correctos*/
		verify(c1,times(1)).sendEvent(EventType.SET_TURN, this.game.getPlayers().get(0));
		verify(c2,times(0)).sendEvent(EventType.SET_TURN, this.game.getPlayers().get(1));
		
		this.game.mark(4);
		/*Verificamos que el primer turno sea el correcto*/
		verify(c1,times(1)).sendEvent(EventType.SET_TURN, this.game.getPlayers().get(0));
		verify(c2,times(1)).sendEvent(EventType.SET_TURN, this.game.getPlayers().get(1));
		
		this.game.mark(0);
		this.game.mark(3);
		this.game.mark(1);
		
		/*Verificamos que los turnos sigan siendo los adecuados.*/
		verify(c1,times(3)).sendEvent(EventType.SET_TURN, this.game.getPlayers().get(0));
		verify(c2,times(2)).sendEvent(EventType.SET_TURN, this.game.getPlayers().get(1));

		ArgumentCaptor<WinnerValue> argument = ArgumentCaptor.forClass(WinnerValue.class);
		verify(c1,times(0)).sendEvent(eq(EventType.GAME_OVER), argument.capture());
		verify(c2,times(0)).sendEvent(eq(EventType.GAME_OVER), argument.capture());
		
		this.game.mark(8);
		this.game.mark(2);
		
		int [] posicionesGanadoras ={0,1,2};
		
		assertArrayEquals(posicionesGanadoras,this.game.checkWinner().pos);
		/*Comprobamos que se ha efectuado la victoria correctamente*/
		verify(c2,times(1)).sendEvent(eq(EventType.GAME_OVER), argument.capture());
		verify(c1).sendEvent(eq(EventType.GAME_OVER), argument.capture());
		
	}
	/*
	 * O | O | O
	 * X | X | 
	 *   |   | X
	 */
	@Test
	public void pierdeJugador1() {
			
		/*Verificamos que los jugadores se hayan introducido en la lista, se ha llamado al evento*/
		verify(c2,times(1)).sendEvent(EventType.JOIN_GAME, this.game.getPlayers());
		verify(c1,times(2)).sendEvent(EventType.JOIN_GAME, this.game.getPlayers());
		
		/*Verificamos que los turnos iniciales sean los correctos*/
		verify(c1,times(1)).sendEvent(EventType.SET_TURN, this.game.getPlayers().get(0));
		verify(c2,times(0)).sendEvent(EventType.SET_TURN, this.game.getPlayers().get(1));
		
		this.game.mark(0);
		/*Verificamos que el primer turno sea el correcto*/
		verify(c1,times(1)).sendEvent(EventType.SET_TURN, this.game.getPlayers().get(0));
		verify(c2,times(1)).sendEvent(EventType.SET_TURN, this.game.getPlayers().get(1));
		
		this.game.mark(4);
		this.game.mark(1);
		this.game.mark(3);
		
		/*Verificamos que los turnos sigan siendo los adecuados.*/
		verify(c1,times(3)).sendEvent(EventType.SET_TURN, this.game.getPlayers().get(0));
		verify(c2,times(2)).sendEvent(EventType.SET_TURN, this.game.getPlayers().get(1));

		ArgumentCaptor<WinnerValue> argument = ArgumentCaptor.forClass(WinnerValue.class);
		verify(c1,times(0)).sendEvent(eq(EventType.GAME_OVER), argument.capture());
		verify(c2,times(0)).sendEvent(eq(EventType.GAME_OVER), argument.capture());
		
		this.game.mark(2);
		this.game.mark(8);
		
		int [] posicionesGanadoras ={0,1,2};
		
		assertArrayEquals(posicionesGanadoras,this.game.checkWinner().pos);
		/*Comprobamos que se ha efectuado la victoria correctamente*/
		verify(c1,times(1)).sendEvent(eq(EventType.GAME_OVER), argument.capture());
		verify(c2).sendEvent(eq(EventType.GAME_OVER), argument.capture());
		
	}
	/*
	 * O | X | X
	 * O | O | X
	 * X | O | O
	 */
	@Test
	public void ganaJugador1CompletandoTablero() {
		
		/*Verificamos que los jugadores se hayan introducido en la lista, se ha llamado al evento*/
		verify(c2,times(1)).sendEvent(EventType.JOIN_GAME, this.game.getPlayers());
		verify(c1,times(2)).sendEvent(EventType.JOIN_GAME, this.game.getPlayers());
		
		/*Verificamos que los turnos iniciales sean los correctos*/
		verify(c1,times(1)).sendEvent(EventType.SET_TURN, this.game.getPlayers().get(0));
		verify(c2,times(0)).sendEvent(EventType.SET_TURN, this.game.getPlayers().get(1));
		
		this.game.mark(4);
		/*Verificamos que el primer turno sea el correcto*/
		verify(c1,times(1)).sendEvent(EventType.SET_TURN, this.game.getPlayers().get(0));
		verify(c2,times(1)).sendEvent(EventType.SET_TURN, this.game.getPlayers().get(1));
		
		this.game.mark(1);
		this.game.mark(0);
		this.game.mark(2);
		
		/*Verificamos que los turnos sigan siendo los adecuados.*/
		verify(c1,times(3)).sendEvent(EventType.SET_TURN, this.game.getPlayers().get(0));
		verify(c2,times(2)).sendEvent(EventType.SET_TURN, this.game.getPlayers().get(1));

		ArgumentCaptor<WinnerValue> argument = ArgumentCaptor.forClass(WinnerValue.class);
		verify(c1,times(0)).sendEvent(eq(EventType.GAME_OVER), argument.capture());
		verify(c2,times(0)).sendEvent(eq(EventType.GAME_OVER), argument.capture());
		
		this.game.mark(3);
		this.game.mark(5);
		this.game.mark(7);
		this.game.mark(6);
		this.game.mark(8);
		
		assertTrue(this.game.checkDraw());
		int [] posicionesGanadoras ={0,4,8};
		
		assertArrayEquals(posicionesGanadoras,this.game.checkWinner().pos);
		/*Comprobamos que se ha efectuado la victoria correctamente*/
		verify(c2,times(1)).sendEvent(eq(EventType.GAME_OVER), argument.capture());
		verify(c1).sendEvent(eq(EventType.GAME_OVER), argument.capture());
	}
	
	/*
	 * O | X | O
	 * O | X | X
	 * X | O | X
	 */
	@Test
	public void empate() {
		
		/*Verificamos que los jugadores se hayan introducido en la lista, se ha llamado al evento*/
		verify(c2,times(1)).sendEvent(EventType.JOIN_GAME, this.game.getPlayers());
		verify(c1,times(2)).sendEvent(EventType.JOIN_GAME, this.game.getPlayers());
		
		/*Verificamos que los turnos iniciales sean los correctos*/
		verify(c1,times(1)).sendEvent(EventType.SET_TURN, this.game.getPlayers().get(0));
		verify(c2,times(0)).sendEvent(EventType.SET_TURN, this.game.getPlayers().get(1));
		
		this.game.mark(4);
		/*Verificamos que el primer turno sea el correcto*/
		verify(c1,times(1)).sendEvent(EventType.SET_TURN, this.game.getPlayers().get(0));
		verify(c2,times(1)).sendEvent(EventType.SET_TURN, this.game.getPlayers().get(1));
		
		this.game.mark(0);
		this.game.mark(1);
		this.game.mark(2);
		
		/*Verificamos que los turnos sigan siendo los adecuados.*/
		verify(c1,times(3)).sendEvent(EventType.SET_TURN, this.game.getPlayers().get(0));
		verify(c2,times(2)).sendEvent(EventType.SET_TURN, this.game.getPlayers().get(1));

		ArgumentCaptor<WinnerValue> argument = ArgumentCaptor.forClass(WinnerValue.class);
		verify(c1,times(0)).sendEvent(eq(EventType.GAME_OVER), argument.capture());
		verify(c2,times(0)).sendEvent(eq(EventType.GAME_OVER), argument.capture());
		
		this.game.mark(5);
		this.game.mark(3);
		this.game.mark(6);
		this.game.mark(7);
		this.game.mark(8);
		
		assertTrue(this.game.checkDraw());
		
		assertFalse(this.game.checkWinner().win);
		/*Comprobamos que se ha efectuado la victoria correctamente*/
		verify(c2,times(1)).sendEvent(eq(EventType.GAME_OVER), argument.capture());
		verify(c1).sendEvent(eq(EventType.GAME_OVER), argument.capture());
		
	}
}
	
