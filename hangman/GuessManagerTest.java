package hangman;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import hangman.GuessManagerContract.GuessResponse;

/*
Assignment number : 3
File Name : GuessManagerTest.java
Name : Ilay Serr
Email : ilay92@gmail.com
*/

public class GuessManagerTest {
	GuessManager game;

	@Before 
	public void setUp() {
		this.game = new GuessManager("hey", 3);
	}
	
	@Test
	public void testGetBadGuessesLeft1() {
		assertEquals(3 , this.game.getBadGuessesLeft());
		//True guess
		this.game.getGuessResponse('h');
		assertEquals(3 , this.game.getBadGuessesLeft());
		//False guess
		this.game.getGuessResponse('a');
		assertEquals(2, this.game.getBadGuessesLeft());
	}
	
	@Test
	public void testGetBadGuessesLeft2() {
		assertEquals(3 , this.game.getBadGuessesLeft());
		this.game.getGuessResponse('a');
		assertEquals(2 , this.game.getBadGuessesLeft());
		this.game.getGuessResponse('b');
		assertEquals(1, this.game.getBadGuessesLeft());
		this.game.getGuessResponse('c');
		assertEquals(0, this.game.getBadGuessesLeft());
		assertEquals(GuessResponse.GUESS_LOSE, this.game.getGuessResponse('b'));
	}
	
	@Test
	public void testGuessManagerNegative() {
		  boolean thrown = false;
		  try {
			  GuessManager game1 = new GuessManager("hey", -1);
			  
		  } catch (IllegalArgumentException e) {
		    thrown = true;
		  }
		  assertTrue(thrown);
	}
	
	@Test
	public void testGetCurrentHint1() {
		assertEquals("___" , this.game.getCurrentHint());
		
		// True guesses
		this.game.getGuessResponse('h');
		assertEquals("h__" , this.game.getCurrentHint());
		this.game.getGuessResponse('y');
		assertEquals("h_y" , this.game.getCurrentHint());
		this.game.getGuessResponse('e');
		assertEquals("hey" , this.game.getCurrentHint());
	}
	
	@Test
	public void testGetCurrentHint2() { 
		assertEquals("___" , this.game.getCurrentHint());
		
		// false guesses
		this.game.getGuessResponse('a');
		assertEquals("___" , this.game.getCurrentHint());
		this.game.getGuessResponse('H');
		assertEquals("___" , this.game.getCurrentHint());
		this.game.getGuessResponse('_');
		assertEquals("___" , this.game.getCurrentHint());
	}
	
	@Test
	public void testGuessManager() {
		assertNotEquals(null, this.game);
		assertEquals(3, this.game.guesses);
		assertEquals("___", this.game.hint);
		assertEquals("hey", this.game.word);
		GuessManager game1 = new GuessManager("hello" , 4);
		assertNotEquals(this.game.guesses, game1.guesses);
		assertNotEquals(this.game.hint, game1.hint);
		assertNotEquals(this.game.word, game1.word);
	}
	
	@Test
	public void testGetGuessRespone() {
		assertEquals(GuessResponse.GUESS_GOOD, this.game.getGuessResponse('h'));
		assertEquals(GuessResponse.GUESS_BAD, this.game.getGuessResponse('a'));
		GuessManager game1 = new GuessManager("hi", 1);
		game1.getGuessResponse('a');
		assertEquals(GuessResponse.GUESS_LOSE, game1.getGuessResponse('b'));
		game1 = new GuessManager("hi", 2);
		game1.getGuessResponse('i');
		assertEquals(GuessResponse.GUESS_WIN, game1.getGuessResponse('h'));
	}
	
	@Test
	public void testGetGuessResponeCaseInsensitive() {
		//good guess
		GuessManager game1 = new GuessManager("HiYa", 2);
		assertEquals(GuessResponse.GUESS_GOOD,
							game1.getGuessResponseCaseInsensitive('H'));
		assertEquals(GuessResponse.GUESS_GOOD,
							game1.getGuessResponseCaseInsensitive('i'));
		assertEquals(GuessResponse.GUESS_GOOD,
							game1.getGuessResponseCaseInsensitive('a'));
		//bad  guess
		assertEquals(GuessResponse.GUESS_BAD,
							game1.getGuessResponseCaseInsensitive('T'));
		assertEquals(GuessResponse.GUESS_BAD,
							game1.getGuessResponseCaseInsensitive('B'));
		//guess win
		assertEquals(GuessResponse.GUESS_WIN,
							game1.getGuessResponseCaseInsensitive('y'));
		//new game - lose
		GuessManager game2 = new GuessManager("hi", 1);
		game2.getGuessResponseCaseInsensitive('a');
		assertEquals(GuessResponse.GUESS_LOSE, 
							game2.getGuessResponseCaseInsensitive('D'));
						
							
							
	}
}
