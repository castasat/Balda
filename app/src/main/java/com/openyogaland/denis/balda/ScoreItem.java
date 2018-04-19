package com.openyogaland.denis.balda;

import android.support.annotation.NonNull;

class ScoreItem
{
  /**
   * fields
   */
  private String word;
  private String scoreString;
  
  /**
   * constructor
   * @param word - String word to calculate it's score
   */
  ScoreItem(@NonNull String word)
  {
    setWord(word);
  }
  
  /**
   * getter
   * @return String scoreString
   */
  @NonNull
  public String getScoreString()
  {
    return scoreString;
  }
  
  /**
   * getter
   * @return String word
   */
  @NonNull
  public String getWord()
  {
    return word;
  }
  
  /**
   * setter
   * @param word - score item to calculate it's score
   */
  private void setWord(@NonNull String word)
  {
    this.word   = word;
    int score   = getScore(word);
    scoreString = String.valueOf(score);
  }
  
  /**
   * Calculates score from given word
   * @param word - String to calculate score
   * @return int score
   */
  public int getScore(@NonNull String word)
  {
    return word.length();
  }
}
