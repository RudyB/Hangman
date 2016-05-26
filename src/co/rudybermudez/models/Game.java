/*
* @Author:  Rudy Bermudez
* @Email:   rudybermudez@me.com
* @Date:    12/26/2015 02:55 PM
* @Version: Hangman
* @File:    Game.java
* @Last Modified time: 12/26/2015 02:55 PM
*/

package co.rudybermudez.models;

public class Game {
    public static final int MAX_MISSES = 7;
    private String mAnswer;
    private String mHits;
    private String mMisses;


    public Game(String answer) {
        answer = answer.toLowerCase();
        mAnswer = answer;
        mHits = "";
        mMisses = "";
    }

    private char validateGuess(char letter) {
        if (!Character.isLetter(letter)) {
            throw new IllegalArgumentException("A letter is required");
        }
        letter = Character.toLowerCase(letter);
        if (mMisses.indexOf(letter) >= 0 || mHits.indexOf(letter) >= 0) {
            throw new IllegalArgumentException(letter + " has already been guessed.");
        }
        return letter;
    }

    public boolean applyGuess(String letters) {
        if (letters.length() == 0) {
            throw new IllegalArgumentException("No letter found");
        }
        return applyGuess(letters.charAt(0));
    }

    public boolean applyGuess(char letter) {
        letter = validateGuess(letter);
        boolean isHit = mAnswer.indexOf(letter) >= 0;
        if (isHit) {
            mHits += letter;
        } else {
            mMisses += letter;
        }
        return isHit;
    }

    public boolean isSolved() {
        return getCurrentProgress().equalsIgnoreCase(mAnswer);
    }

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

    public int getRemainingTries() {
        return MAX_MISSES - mMisses.length();
    }

    public String getAnswer() {
        String finalAnswer = "Game is not over yet";
        if (isSolved() || mMisses.length() >= MAX_MISSES) {
            finalAnswer = mAnswer;
        }
        return finalAnswer;
    }

    public String getMisses() {
        return mMisses;
    }
}
