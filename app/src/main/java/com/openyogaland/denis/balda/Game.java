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
public class Game extends AppCompatActivity implements OnClickListener, OnCellPressedListener
{
  // constants
  private static final String TAG_FIELD    = "field_fragment_tag";
  private static final String TAG_KEYBOARD = "keyboard_fragment_tag";
  
  // fragments to insert
  private FieldFragment    fieldFragment;
  private KeyboardFragment keyboardFragment;
  
  // fragment transaction
  private FragmentTransaction transaction;
  
  // current cell
  private Button cellPressed;
  
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
      //TODO show only if needed // transaction.add(R.id.switchableFragmentFrame,keyboardFragment,TAG_KEYBOARD);
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
    // super.onBackPressed();
    openQuitDialog();
  }
  
  private void openQuitDialog()
  {
    Builder quitDialog = new Builder(this);
    quitDialog.setTitle(R.string.quit_dialog_message);
    quitDialog.setPositiveButton(R.string.positive_button_text, this);
    quitDialog.setNegativeButton(R.string.negative_button_text, this);
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
  public void onCellPressed(Button cellPressed)
  {
    this.cellPressed = cellPressed;
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
}
