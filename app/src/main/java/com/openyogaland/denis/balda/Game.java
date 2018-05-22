package com.openyogaland.denis.balda;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;

/**
 * Main activity class Game
 */
public class Game extends AppCompatActivity implements OnClickListener, OnCellPressedListener,
                                                       OnKeyPressedListener
{
  /** constants **/
  private static final String TAG_FIELD    = "field_fragment_tag";
  private static final String TAG_KEYBOARD = "keyboard_fragment_tag";
  private static final String TAG_SCORE    = "score_fragment_tag";
  
  static final int PLAYER_CHOOSING_LETTER = 0;
  static final int PLAYER_CHOOSING_WORD   = 1;
  static final int OPPONENT_TURN          = 2;
  
  /** alert dialogs **/
  private AlertDialog quitDialog     = null;
  private AlertDialog skipTurnDialog = null;
  /** fragment transaction **/
  private FragmentTransaction transaction;
  /** cell pressed in FieldFragment id **/
  private int cellPressedId;
  /**  **/
  private int state = PLAYER_CHOOSING_LETTER;
  
  @Override
  protected void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    this.setContentView(R.layout.game_layout);
    
    // create new instance of fragments
    FieldFragment fieldFragment = new FieldFragment();
    ScoreFragment scoreFragment = new ScoreFragment();
    
    // during the first program start
    if (savedInstanceState == null)
    {
      transaction = getSupportFragmentManager().beginTransaction();
      transaction.add(R.id.fieldFragmentFrame, fieldFragment, TAG_FIELD);
      transaction.add(R.id.switchableFragmentFrame, scoreFragment, TAG_SCORE);
      transaction.commit();
      
      // start player's turn
      state = PLAYER_CHOOSING_LETTER;
    }
    
    // TODO save and restore saved instance state
  }
  
  @Override
  public void onKeyPressed(int keyPressedId)
  {
    Cell cell = findViewById(cellPressedId);
    if (cell != null)
    {
    }
  }
  
  private void opponentTurn()
  {
    // TODO
  }
  
  private void playerSkipsHisTurn()
  {
    state = OPPONENT_TURN;
    opponentTurn();
  }
  
  @Override
  public void onClick(DialogInterface dialogInstance, int which)
  {
    // back on phone pressed
    if((quitDialog != null) && (quitDialog == dialogInstance))
    {
      if(which == DialogInterface.BUTTON_POSITIVE)
      {
        finish();
      }
      else if(which == DialogInterface.BUTTON_NEGATIVE)
      {
        dialogInstance.dismiss();
      }
    }
    
    // back in keyboard fragment pressed
    if((skipTurnDialog != null) && (skipTurnDialog == dialogInstance))
    {
      if(which == DialogInterface.BUTTON_POSITIVE)
      {
        dialogInstance.dismiss();
        playerSkipsHisTurn();
      }
      else if(which == DialogInterface.BUTTON_NEGATIVE)
      {
        dialogInstance.dismiss();
      }
    }
  }
  
  @Override
  public void onCellPressed(int cellPressedId)
  {
    switch(state)
    {
      case PLAYER_CHOOSING_LETTER:
      case PLAYER_CHOOSING_WORD:
      {
        this.cellPressedId = cellPressedId;
        showKeyboard();
        break;
      }
      case OPPONENT_TURN:
      default:
      {
        break;
      }
    }
  }
  
  private void showKeyboard()
  {
    KeyboardFragment keyboardFragment = (KeyboardFragment) getSupportFragmentManager().findFragmentByTag(TAG_KEYBOARD);
    
    if(keyboardFragment == null)
    {
      KeyboardFragment fragment = new KeyboardFragment();
      transaction = getSupportFragmentManager().beginTransaction();
      transaction.replace(R.id.switchableFragmentFrame, fragment, TAG_KEYBOARD);
      transaction.commit();
    }
  }
  
  private void hideKeyboard()
  {
    ScoreFragment    scoreFragment    = (ScoreFragment)    getSupportFragmentManager().findFragmentByTag(TAG_SCORE);
    KeyboardFragment keyboardFragment = (KeyboardFragment) getSupportFragmentManager().findFragmentByTag(TAG_KEYBOARD);
    
    transaction = getSupportFragmentManager().beginTransaction();
    
    if(scoreFragment != null)
    {
      transaction.replace(R.id.switchableFragmentFrame, scoreFragment, TAG_SCORE);
    }
    else
    {
      scoreFragment = new ScoreFragment();
      if(keyboardFragment != null)
      {
        transaction.remove(keyboardFragment);
      }
      transaction.add(R.id.switchableFragmentFrame, scoreFragment, TAG_SCORE);
    }
    transaction.commit();
  }
  
  private void openQuitDialog()
  {
    Builder quitDialogBuilder = new Builder(this);
    quitDialogBuilder.setTitle(R.string.quit_dialog_message);
    quitDialogBuilder.setPositiveButton(R.string.exit_yes, this);
    quitDialogBuilder.setNegativeButton(R.string.exit_no, this);
    quitDialog = quitDialogBuilder.create();
    quitDialog.show();
  }
  
  private void openSkipTurnDialog()
  {
    Builder skipTurnDialogBuilder = new Builder(this);
    skipTurnDialogBuilder.setTitle(R.string.skip_turn_dialog_message);
    skipTurnDialogBuilder.setPositiveButton(R.string.skip_turn_yes, this);
    skipTurnDialogBuilder.setNegativeButton(R.string.skip_turn_no, this);
    skipTurnDialog = skipTurnDialogBuilder.create();
    skipTurnDialog.show();
  }
  
  @Override
  public boolean onKeyDown(int keyCode, KeyEvent event)
  {
    if(keyCode == KeyEvent.KEYCODE_BACK)
    {
      openQuitDialog();
      return true;
    }
    return super.onKeyDown(keyCode, event);
  }
  
  /**
   * Take care of popping the fragment back stack or finishing the activity
   * as appropriate.
   */
  @Override
  public void onBackPressed()
  {
    openQuitDialog();
  }
}
