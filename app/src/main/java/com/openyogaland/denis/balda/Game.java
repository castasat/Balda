package com.openyogaland.denis.balda;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

/**
 * Main activity class Game
 */
public class Game extends AppCompatActivity
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
}
