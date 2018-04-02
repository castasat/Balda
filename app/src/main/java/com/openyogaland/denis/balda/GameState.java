package com.openyogaland.denis.balda;

public interface GameState
{
  boolean isPlayerSelectedCell();
  void setPlayerSelectedCell(boolean playerSelectedCell);
  
  boolean isPlayerEnteredLetter();
  void setPlayerEnteredLetter(boolean playerEnteredLetter);
  
  boolean isPlayerEnteredWord();
  void setPlayerEnteredWord(boolean playerEnteredWord);
}
