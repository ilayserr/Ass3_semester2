package hangman;

import hangman.GuessManagerContract.GuessResponse;

/*
Assignment number : 3
File Name : GuessManager.java
Name : Ilay Serr
Email : ilay92@gmail.com
*/

public class GuessManager implements GuessManagerContract,
								GuessManagerContractCaseInsensitive	{
		int guesses;
		String hint;
		String word;
		
		/**
		 * a constructor that get as input the number of guesses and
		 * the word to guess 
		 * @param word - the word enter to guess
		 * @param numOfGuesses - number of guesses left
		 */
		public GuessManager(String word, int numOfGuesses) {
			if (numOfGuesses < 0) {
				throw new IllegalArgumentException();
			}
			this.word = word;
			this.guesses = numOfGuesses;
			this.hint = "";
			for (int i = 0; i < word.length(); i++) { 
				this.hint += NON_MATCH;
			}
		}
		
		/** 
		 * Return the number of guesses left before the player loses.
		 */
		@Override
		public int getBadGuessesLeft() {	
			return this.guesses;
		}
		
		/**
		 * Return a string that has the letters guessed so far in their 
		 * correct positions and {@link #NON_MATCH} in the other positions.
		 * Note that even if the guess was case-insensitive, the 
		 */
		@Override
		public String getCurrentHint() {
			return this.hint;
		}
		
		/**
		 * This method checks whether a letter is part of the word. If it was,
		 * it updates the current hint, otherwise it decrement the number of guesses left.
		 * 
		 * @param letter The letter that was guessed.
		 * @return One of the {@link GuessResponse} responses, as specified in their documentation.
		 */
		@Override
		public GuessResponse getGuessResponse(char letter) {
			StringBuilder it = new StringBuilder();
			if (word.indexOf(letter) == -1) {
				this.guesses--;
				if (guesses < 0) return GuessResponse.GUESS_LOSE;
				else return GuessResponse.GUESS_BAD;
			}
			for (int i = 0; i < word.length(); i++) {
				if (word.charAt(i) == letter) {
					it.append(letter);
				} else {
					it.append(this.hint.charAt(i));
				}
			}
			this.hint = it.toString();
			if (this.hint.indexOf(NON_MATCH) == -1) 
				return GuessResponse.GUESS_WIN;
			else return GuessResponse.GUESS_GOOD;
		}

		/**
		 * This method should work identically to {@link #getGuessResponse(char)}, except that the 
		 * letter is considered found even if the case doesn't match (i.e., if the word contains 'A' but
		 * the input letter was 'a'). Note that the hint returned by the {@link #getCurrentHint()} method 
		 * should reflect the original word case (regardless of the guess's case).

		 * @param letter the letter that was guessed.
		 * @return the appropriate {@link GuessResponse}
		 */
		@Override
		public GuessResponse getGuessResponseCaseInsensitive(char letter) {
			StringBuilder it = new StringBuilder();
			if (this.word.indexOf(Character.toUpperCase(letter)) == -1
					&& this.word.indexOf(Character.toLowerCase(letter)) == -1) {
				this.guesses--;
				if (guesses < 0) return GuessResponse.GUESS_LOSE;
				else return GuessResponse.GUESS_BAD;
			}
			
			for (int i = 0; i < word.length(); i++) {
				char temp = word.charAt(i); 
				if ((Character.toUpperCase(letter) == temp) || 
							(Character.toLowerCase(letter) == temp)) {
					it.append(temp);
				} else {
					it.append(this.hint.charAt(i));
				}
			}
			this.hint = it.toString();
			if (this.hint.indexOf(NON_MATCH) == -1) 
				return GuessResponse.GUESS_WIN;
			else return GuessResponse.GUESS_GOOD;
		}
}
