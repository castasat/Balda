package com.openyogaland.denis.balda;

import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.Button;

/**
 * Main activity class Game
 */
public class Game extends AppCompatActivity implements OnClickListener, OnCellPressedListener, OnKeyPressedListener
{
  // constants
  private static final String TAG_FIELD    = "field_fragment_tag";
  private static final String TAG_KEYBOARD = "keyboard_fragment_tag";
  
  // fragments to insert
  private FieldFragment    fieldFragment;
  private KeyboardFragment keyboardFragment;
  
  // fragment transaction
  private FragmentTransaction transaction;
  
  /** cell pressed in FieldFragment id **/
  private int cellPressedId;
  /** key pressed in KeyboardFragment id **/
  private int keyPressedId;
  
  @Override
  protected void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    this.setContentView(R.layout.game_layout);
    
    // create new instance of fragment
    fieldFragment    = new FieldFragment();
    
    // during the first program start
    if (savedInstanceState == null)
    {
      transaction = getSupportFragmentManager().beginTransaction();
      transaction.add(R.id.fieldFragmentFrame, fieldFragment, TAG_FIELD);
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
    Builder quitDialog = new Builder(this);
    quitDialog.setTitle(R.string.quit_dialog_message);
    quitDialog.setPositiveButton(R.string.exit_yes, this);
    quitDialog.setNegativeButton(R.string.exit_no, this);
    quitDialog.show();
  }
  
  @Override
  public void onClick(DialogInterface dialogInstance, int which)
  {
    if (which == DialogInterface.BUTTON_POSITIVE)
    {
      finish();
    }
    else if(which == DialogInterface.BUTTON_NEGATIVE)
    {
      dialogInstance.dismiss();
    }
  }
  
  @Override
  public void onCellPressed(int cellPressedId)
  {
    this.cellPressedId = cellPressedId;
    showKeyboard();
  }
  
  private void showKeyboard()
  {
    keyboardFragment = (KeyboardFragment) getSupportFragmentManager().findFragmentByTag(TAG_KEYBOARD);
  
    if(keyboardFragment == null)
    {
      this.keyboardFragment = new KeyboardFragment();
      transaction = getSupportFragmentManager().beginTransaction();
      transaction.add(R.id.switchableFragmentFrame, this.keyboardFragment, TAG_KEYBOARD);
      transaction.commit();
    }
  }
  
  @Override
  public void onKeyPressed(int keyPressedId)
  {
    // TODO check delete and done
    
    Button cell = findViewById(cellPressedId);
    
    // check if cell to set text is here
    if (cell != null)
    {
      // if the user pressed delete on keyboard
      if(keyPressedId == R.id.delete)
      {
        // empty cell text
        cell.setText("");
        // очищаем id
        this.keyPressedId = 0;
      }
      // if the user pressed done on keyboard
      else if(keyPressedId == R.id.done)
      {
        // if previous key was not delete key
        if (this.keyPressedId != 0)
        {
          // find a key by previous memorized keyPressedId
          Button key = findViewById(this.keyPressedId);
          // if the key was found
          if(key != null)
          {
            // set the text of the cell equal to the text on the previous key (not done key)
            cell.setText(key.getText());
            // TODO call waitForWord()
            // TODO disable button
          }
        }
        // if previous key was delete
        else
        {
          // empty cell text
          cell.setText("");
        }
      }
      // letter key pressed
      else
      {
        // memorize key id
        this.keyPressedId = keyPressedId;
        // find a key by id
        Button key = findViewById(this.keyPressedId);
        // if the key was found
        if (key != null)
        {
          // set the text of the cell equal to the text on the key
          cell.setText(key.getText());
        }
      }
    }
  }
}
