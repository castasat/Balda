package com.openyogaland.denis.balda;

import android.support.annotation.NonNull;

class ScoreItem
{
  /**
   * fields
   */
  private String word;
  private String score;
  
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
   * @return String score
   */
  @NonNull
  public String getScore()
  {
    return score;
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
    this.word = word;
    score = calculateScore(word);
  }
  
  /**
   * Calculates score from given word
   * @param word - String to calculate score
   * @return String score
   */
  @NonNull
  private String calculateScore(@NonNull String word)
  {
    return String.valueOf(word.length());
  }
}
