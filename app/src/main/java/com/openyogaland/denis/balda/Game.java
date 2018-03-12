package com.openyogaland.denis.balda;

import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;

/**
 * Main activity class Game
 */
public class Game extends AppCompatActivity implements OnClickListener
{
  private static final String TAG_FIELD = "field_fragment_tag";
  
  @Override
  protected void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    this.setContentView(R.layout.game_layout);
          
    // during initial program start
    if (savedInstanceState == null)
    {
      FragmentManager     fragmentManager = getSupportFragmentManager();
      FragmentTransaction transaction     = fragmentManager.beginTransaction();
      
      FieldFragment       fieldFragment   = new FieldFragment();
      
      transaction.add(R.id.gameFrame, fieldFragment, TAG_FIELD);
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
    
    // TODO обработать события hardware клавиатуры - onKeyDown() в Game.java
  
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
  public void onClick(DialogInterface dialogInterface, int which)
  {
    if (which == DialogInterface.BUTTON_POSITIVE)
    {
      finish();
    }
    else if(which == DialogInterface.BUTTON_NEGATIVE)
    {
      dialogInterface.dismiss();
    }
  }
}
