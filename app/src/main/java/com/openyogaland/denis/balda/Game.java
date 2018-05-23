package com.openyogaland.denis.balda;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.Button;
import android.widget.Toast;

import org.jetbrains.annotations.NotNull;

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
  /** id of cell pressed in FieldFragment **/
  private int cellPressedId  = 0;
  /** id of previous pressed cell**/
  private int previousCellId = 0;
  /** game state **/
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
      switch (state)
      {
        case PLAYER_CHOOSING_LETTER:
        {
          switch(keyPressedId)
          {
            case R.id.back:
            {
              openSkipTurnDialog();
              clearCell(cell);
              hideKeyboard();
              break;
            }
            case R.id.delete:
            {
              clearCell(cell);
              break;
            }
            case R.id.done:
            {
              state = PLAYER_CHOOSING_WORD;
              hideKeyboard();
              break;
            }
            default:
            {
              Button key    = findViewById(keyPressedId);
              String letter = key.getText().toString();
              cell.setLetter(letter);
              cell.setText(cell.getLetter());
              cell.setSelected(true);
              cell.setState(Cell.LETTER_ENTERED);
              break;
            }
          }
          break;
        }
        case PLAYER_CHOOSING_WORD:
        {
          // TODO choose word
          switch (keyPressedId)
          {
            case R.id.back:
              // TODO back pressed while choosing word
              break;
            case R.id.delete:
              // TODO delete pressed while choosing word
              break;
            case R.id.done:
              // TODO done pressed while choosing word
              state = OPPONENT_TURN;
              break;
            default:
              // TODO default behaviour while choosing word
              break;
          }
          break;
        }
        case OPPONENT_TURN:
          // TODO behaviour while opponent turn
        default:
        {
          // TODO default behaviour while key pressed
          break;
        }
      }
    }
  }
  
  @Override
  public void onCellPressed(int cellPressedId)
  {
    Cell cell = findViewById(cellPressedId);
    switch(state)
    {
      default:
      case PLAYER_CHOOSING_LETTER:
      {
        Toast.makeText(this, "CHOOSE A LETTER", Toast.LENGTH_SHORT).show();
        if(cell.getState() != Cell.WORD_ENTERED)
        {
          this.cellPressedId  = cellPressedId;
          
          // if the cell was pressed after previous one
          if((previousCellId != 0) && (previousCellId != cellPressedId))
          {
            Cell previousCell = findViewById(previousCellId);
            if(previousCell != null)
            {
              clearCell(previousCell);
            }
          }
          previousCellId = cellPressedId;
          cell.setSelected(true);
          showKeyboard();
        }
        else // cell.getState() == Cell.WORD_ENTERED
        {
          cell.setSelected(false);
        }
        break;
      }
      case PLAYER_CHOOSING_WORD:
      {
        // TODO check
        Toast.makeText(this, "CHOOSE A WORD", Toast.LENGTH_SHORT).show();
        this.cellPressedId = cellPressedId;
        showKeyboard();
        break;
      }
      case OPPONENT_TURN:
      {
        // TODO check
        Toast.makeText(this, "OPPONENT TURN", Toast.LENGTH_SHORT).show();
        break;
      }
    }
  }
  
  private void opponentTurn()
  {
    Toast.makeText(this, "OPPONENT TURN", Toast.LENGTH_SHORT).show();
    // TODO opponent turn
  }
  
  private void clearCell(@NonNull @NotNull Cell cell)
  {
    cell.setText("");
    cell.setLetter("");
    cell.setSelected(false);
    cell.setState(Cell.INITIAL_STATE);
    state = PLAYER_CHOOSING_LETTER;
  }
  
  private void playerSkipsHisTurn()
  {
    state = OPPONENT_TURN;
    opponentTurn();
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
