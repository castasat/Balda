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
  
  /** alert dialogs **/
  private AlertDialog quitDialog     = null;
  private AlertDialog skipTurnDialog = null;
  
  /** fragment transaction **/
  private FragmentTransaction transaction;
  
  /** cell pressed in FieldFragment id **/
  private int cellPressedId;
  
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
    if ((quitDialog != null) && (quitDialog == dialogInstance))
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
    if ((skipTurnDialog != null) && (skipTurnDialog == dialogInstance))
    {
      if(which == DialogInterface.BUTTON_POSITIVE)
      {
        dialogInstance.dismiss();
        // TODO player skips his turn
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
    this.cellPressedId = cellPressedId;
    showKeyboard();
  }
  
  @Override
  public void onKeyPressed(int keyPressedId)
  {
    Cell cell = findViewById(cellPressedId);
    if (cell != null)
    {
      // case cell has no letters
      keyPressedIfCellHasInitialState(cell, keyPressedId);
      // case cell has letter choosed state
      keyPressedIfCellHasLetterChoosedState(cell, keyPressedId);
      // case cell has letter entered state
      keyPressedIfCellHasLetterEnteredState(cell, keyPressedId);
      // case cell has word choosed state
      keyPressedIfCellHasWordChoosedState(cell, keyPressedId);
      // case cell has word entered state
      keyPressedIfCellHasWordEnteredState(cell,keyPressedId);
    }
  }
  
  private void keyPressedIfCellHasInitialState(@NonNull @NotNull Cell cell, int keyPressedId)
  {
    if(cell.getState() == Cell.INITIAL_STATE)
    {
      if((keyPressedId == R.id.done) || (keyPressedId == R.id.delete))
      {
        // cell has no letters
        cell.setSelected(false);
        hideKeyboard();
        // set state flag
        cell.setState(Cell.INITIAL_STATE);
      }
      else if(keyPressedId == R.id.back)
      {
        // ask player if he wants to skip his turn
        openSkipTurnDialog();
        cell.setLetter("");
        cell.setText("");
        cell.setSelected(false);
        hideKeyboard();
        // set state flag
        cell.setState(Cell.INITIAL_STATE);
      }
      else // if letter pressed on keyboard
      {
        Button key = findViewById(keyPressedId);
        if(key != null)
        {
          // set the text of the cell equal to the text on the key
          String letter = key.getText().toString();
          cell.setText(letter);
          cell.setLetter(letter);
          // set state flag
          cell.setState(Cell.LETTER_CHOOSED);
        }
      }
    }
  }
  
  private void keyPressedIfCellHasLetterChoosedState(@NonNull @NotNull Cell cell, int keyPressedId)
  {
    if(cell.getState() == Cell.LETTER_CHOOSED)
    {
      if(keyPressedId == R.id.done)
      {
        // cell has a letter
        cell.setSelected(true);
        cell.setText(cell.getLetter());
        hideKeyboard();
        // set state flag
        cell.setState(Cell.LETTER_ENTERED);
      }
      else if(keyPressedId == R.id.back)
      {
        // ask player if he wants to skip his turn
        openSkipTurnDialog();
        cell.setLetter("");
        cell.setText("");
        cell.setSelected(false);
        hideKeyboard();
        // set state flag
        cell.setState(Cell.INITIAL_STATE);
      }
      else if(keyPressedId == R.id.delete)
      {
        cell.setLetter("");
        cell.setText("");
        // set state flag
        cell.setState(Cell.INITIAL_STATE);
      }
      else // if letter pressed on keyboard
      {
        // change letter with a new one
        Button key = findViewById(keyPressedId);
        if(key != null)
        {
          // set the text of the cell equal to the text on the key
          String letter = key.getText().toString();
          cell.setText(letter);
          cell.setLetter(letter);
          // set state flag
          cell.setState(Cell.LETTER_CHOOSED);
        }
      }
    }
  }
  
  private void keyPressedIfCellHasLetterEnteredState(@NonNull @NotNull Cell cell, int keyPressedId)
  {
    if(cell.getState() == Cell.LETTER_ENTERED)
    {
      if(keyPressedId == R.id.done)
      {
        // TODO enter the selected word
      }
      else if(keyPressedId == R.id.back)
      {
        // ask player if he wants to skip his turn
        openSkipTurnDialog();
        cell.setLetter("");
        cell.setText("");
        cell.setSelected(false);
        hideKeyboard();
        // set state flag
        cell.setState(Cell.INITIAL_STATE);
      }
      else if(keyPressedId == R.id.delete)
      {
        cell.setLetter("");
        cell.setText("");
        // set state flag
        cell.setState(Cell.INITIAL_STATE);
      }
      else // if letter pressed on keyboard
      {
        // change letter with a new one
        Button key = findViewById(keyPressedId);
        if(key != null)
        {
          // set the text of the cell equal to the text on the key
          String letter = key.getText().toString();
          cell.setText(letter);
          cell.setLetter(letter);
          // set state flag
          cell.setState(Cell.LETTER_CHOOSED);
        }
      }
    }
  }
  
  private void keyPressedIfCellHasWordChoosedState(@NonNull @NotNull Cell cell, int keyPressedId)
  {
  }
  
  private void keyPressedIfCellHasWordEnteredState(@NonNull @NotNull Cell cell, int keyPressedId)
  {
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
    ScoreFragment scoreFragment = (ScoreFragment) getSupportFragmentManager().findFragmentByTag(TAG_SCORE);
    
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
}
