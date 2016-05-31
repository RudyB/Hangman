/*
* @Author:  Rudy Bermudez
* @Email:   rudybermudez@me.com
* @Date:    12/26/2015 02:55 PM
* @Version: Hangman
* @File:    Game.java
* @Last Modified time: 12/26/2015 02:55 PM
*/

package co.rudybermudez.models;

/**
 * The model class Game.
 */
public class Game {
    /**
     * The constant MAX_MISSES.
     */
    private static final int MAX_MISSES = 6;

    /**
     * The answer of the game.
     */
    private String mAnswer;

    /**
     * The String of letters of the correct guesses.
     */
    private String mHits;

    /**
     * The String of letters of the incorrect guesses.
     */
    private String mMisses;


    /**
     * Instantiates a new Game.
     *
     * @param answer the answer
     * @throws IllegalArgumentException the illegal argument exception
     */
    public Game(String answer) throws IllegalArgumentException {
        mAnswer = validateAnswer(answer.toLowerCase());
        mHits = "";
        mMisses = "";
    }

    /**
     * Validate a guess by insuring it is a letter.
     *
     * @param letter the letter
     * @return the char
     */
    private char validateGuess(char letter) {
        if (!Character.isLetter(letter)) {
            throw new IllegalArgumentException("A letter is required");
        }
        letter = Character.toLowerCase(letter);
        if (mMisses.indexOf(letter) >= 0 || mHits.indexOf(letter) >= 0) {
            throw new IllegalArgumentException(String.format("'%s' has already been guessed.", String.valueOf(letter).toUpperCase()));
        }
        return letter;
    }

    /**
     * Validate an answer by insuring it is a word.
     *
     * @param answer the answer
     * @return the string
     */
    private String validateAnswer(String answer) {
        if (answer.isEmpty()) {
            throw new IllegalArgumentException("The field is blank.\n\nYou must enter a word");
        } else {
            for (char letter : answer.toCharArray()) {
                if (!Character.isLetter(letter)) {
                    throw new IllegalArgumentException(answer + " is not a valid word.");
                }
            }
            return answer;
        }

    }


    /**
     * Method to apply guess.
     *
     * @param letters the letters
     * @return the boolean
     */
    public boolean applyGuess(String letters) {
        if (letters.length() == 0) {
            throw new IllegalArgumentException("No letter found");
        }
        return applyGuess(letters.charAt(0));
    }

    /**
     * Helper method to apply guess
     *
     * @param letter the letter
     * @return the boolean
     */
    private boolean applyGuess(char letter) {
        letter = validateGuess(letter);
        boolean isHit = mAnswer.indexOf(letter) >= 0;
        if (isHit) {
            mHits += letter;
        } else {
            mMisses += letter;
        }
        return isHit;
    }

    /**
     * Is solved boolean.
     *
     * @return the boolean
     */
    public boolean isSolved() {
        return getCurrentProgress().equalsIgnoreCase(mAnswer);
    }

    /**
     * Gets current progress.
     *
     * @return the current progress
     */
    public String getCurrentProgress() {
        String progress = "";
        for (char letter : mAnswer.toCharArray()) {
            char display = '-';
            if (mHits.indexOf(letter) >= 0) {
                display = letter;
            }
            progress += display;
        }
        return progress;
    }

    /**
     * Gets number of remaining tries.
     *
     * @return the remaining tries
     */
    public int getRemainingTries() {
        return MAX_MISSES - mMisses.length();
    }

    /**
     * Gets answer.
     *
     * @return the answer
     */
    public String getAnswer() {
        String finalAnswer = "Game is not over yet";
        if (isSolved() || mMisses.length() >= MAX_MISSES) {
            finalAnswer = mAnswer;
        }
        return finalAnswer;
    }

    /**
     * Gets misses.
     *
     * @return the misses
     */
    public String getMisses() {
        String misses = "";
        for (Character letter : mMisses.toCharArray()) {
            misses += letter + " ";
        }
        return misses;
    }

    /**
     * Get properly capitalized progress string.
     *
     * @return the string
     */
    public String getProperlyCapitalizedProgress() {
        return getCurrentProgress().substring(0, 1).toUpperCase() + getCurrentProgress().substring(1, getCurrentProgress().length());

    }

    /**
     * Get number of misses for use in displaying the hangman. It subtracts 1 because the first body part in the list is
     * at index 0.
     *
     * @return the integer
     */
    public Integer getHangmanBodyPartNumber() {
        return mMisses.length() - 1;
    }
}
