package com.openyogaland.denis.balda;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import android.widget.Button;
import java.util.Locale;

public class KeyboardFragment extends Fragment implements OnClickListener, Interpolator
{
  private static final String[] RU_KEY_IDS = new String[]
      {
          "A", "Be", "Ve", "Ge", "De", "Ye", "Yo", "Je",
          "Ze", "I", "Iy", "Ka", "El", "Em", "En", "O",
          "Pe", "Er", "Es", "Te", "U", "Ef", "Ha", "Tce",
          "Cha", "Sha", "Shcha", "SoftSymbol", "Iii", "HardSymbol", "Ee", "Yu",
          "Ya", "Delete", "Done"
      };
  
  private static final String[] EN_KEY_IDS = new String[]
      {
          "A", "B", "C", "D", "E", "F", "G", "H",
          "I", "J", "K", "L", "M", "N", "O", "P",
          "Q", "R", "S", "T", "U", "V", "W", "X",
          "Y", "Z", "delete", "done"
      };
  
  private static final double INTERPOLATOR_AMPLITUDE = 0.2;
  private static final double INTERPOLATOR_FREQUENCY = 2.0;
  
  private Animation   keyZoomAnimation;
  private AnimatorSet keyColorAnimatorSet;
  
  @Nullable @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState)
  {
    View view = inflater.inflate(R.layout.keyboard_fragment, container, false);
    
    keyZoomAnimation    = AnimationUtils.loadAnimation(getContext(), R.anim.animate_key_zoom);
    keyColorAnimatorSet = (AnimatorSet) 
        AnimatorInflater.loadAnimator(getContext(), R.animator.animate_key_color);
    
    String language = Locale.getDefault().getDisplayLanguage();
    if ("English".equalsIgnoreCase(language))
    {
      for(String en_id_string : EN_KEY_IDS)
      {
        Button key = view.findViewById(getIdByStringValue(en_id_string));
        if (key != null)
        {
          key.setOnClickListener(this);
        }
      }
    }
    else if ("Русский".equalsIgnoreCase(language))
    {
      for (String ru_id_string : RU_KEY_IDS)
      {
        Button key = view.findViewById(getIdByStringValue("ru" + ru_id_string));
        if(key != null)
        {
          key.setOnClickListener(this);
        }
      }
    }
    return view;
  }
  
  private int getIdByStringValue(String id_string)
  {
    if(getContext() != null)
    {
      return getResources().getIdentifier(id_string, "id", getContext().getPackageName());
    }
    return -1;
  }
  
  @Override
  public void onClick(View view)
  {
    if(view instanceof Button)
    {
      
      // make animation
      Button keyboardButton = (Button) view;
      keyboardButton.bringToFront();
      
      keyColorAnimatorSet.setTarget(keyboardButton);
      keyColorAnimatorSet.setInterpolator(this);
      keyZoomAnimation.setInterpolator(this);
      
      keyColorAnimatorSet.start();
      keyboardButton.startAnimation(keyZoomAnimation);
      
      // TODO get key button id
      // TODO add String keyPressedText
      // TODO if done pressed and keyPressedText isEmpty(), hide keyboard
      // TODO elseif not empty hide keyboard and send keyPressedText to Game
      // TODO if delete pressed set keyPressedText to empty and send to Game
      
    }
  }
  
  /**
   * Maps a value representing the elapsed fraction of an animation to a value that represents
   * the interpolated fraction. This interpolated value is then multiplied by the change in
   * value of an animation to derive the animated value at the current elapsed animation time.
   * @param time A value between 0 and 1.0 indicating our current point in the animation where 0
   * represents the start and 1.0 represents the end
   * @return The interpolation value. This value can be more than 1.0 for interpolators which
   * overshoot their targets, or less than 0 for interpolators that undershoot their targets.
   */
  @Override
  public float getInterpolation(float time)
  {
    
    return (float) ((-1.0 * Math.pow(Math.E, (double) -time / INTERPOLATOR_AMPLITUDE) *
                     Math.cos(INTERPOLATOR_FREQUENCY * (double) time)) + 1.0);
  }
}
