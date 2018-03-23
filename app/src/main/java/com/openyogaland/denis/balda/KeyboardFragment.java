package com.openyogaland.denis.balda;

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
  private static final double INTERPOLATOR_AMPLITUDE = 0.2;
  private static final double INTERPOLATOR_FREQUENCY = 2.0;
  
  @Nullable @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState)
  {
    View view = inflater.inflate(R.layout.keyboard_fragment, container, false);
    
    String language = Locale.getDefault().getDisplayLanguage();
    
    if (language.equalsIgnoreCase("English"))
    {
      Button buttonA      = view.findViewById(R.id.A);
      Button buttonB      = view.findViewById(R.id.B);
      Button buttonC      = view.findViewById(R.id.C);
      Button buttonD      = view.findViewById(R.id.D);
      Button buttonE      = view.findViewById(R.id.E);
      Button buttonF      = view.findViewById(R.id.F);
      Button buttonG      = view.findViewById(R.id.G);
      Button buttonH      = view.findViewById(R.id.H);
      Button buttonI      = view.findViewById(R.id.I);
      Button buttonJ      = view.findViewById(R.id.J);
      Button buttonK      = view.findViewById(R.id.K);
      Button buttonL      = view.findViewById(R.id.L);
      Button buttonM      = view.findViewById(R.id.M);
      Button buttonN      = view.findViewById(R.id.N);
      Button buttonO      = view.findViewById(R.id.O);
      Button buttonP      = view.findViewById(R.id.P);
      Button buttonQ      = view.findViewById(R.id.Q);
      Button buttonR      = view.findViewById(R.id.R);
      Button buttonS      = view.findViewById(R.id.S);
      Button buttonT      = view.findViewById(R.id.T);
      Button buttonU      = view.findViewById(R.id.U);
      Button buttonV      = view.findViewById(R.id.V);
      Button buttonW      = view.findViewById(R.id.W);
      Button buttonX      = view.findViewById(R.id.X);
      Button buttonY      = view.findViewById(R.id.Y);
      Button buttonZ      = view.findViewById(R.id.Z);
      Button buttonDelete = view.findViewById(R.id.delete);
      buttonA.setOnClickListener(this);
      buttonB.setOnClickListener(this);
      buttonC.setOnClickListener(this);
      buttonD.setOnClickListener(this);
      buttonE.setOnClickListener(this);
      buttonF.setOnClickListener(this);
      buttonG.setOnClickListener(this);
      buttonH.setOnClickListener(this);
      buttonI.setOnClickListener(this);
      buttonJ.setOnClickListener(this);
      buttonK.setOnClickListener(this);
      buttonL.setOnClickListener(this);
      buttonM.setOnClickListener(this);
      buttonN.setOnClickListener(this);
      buttonO.setOnClickListener(this);
      buttonP.setOnClickListener(this);
      buttonQ.setOnClickListener(this);
      buttonR.setOnClickListener(this);
      buttonS.setOnClickListener(this);
      buttonT.setOnClickListener(this);
      buttonU.setOnClickListener(this);
      buttonV.setOnClickListener(this);
      buttonW.setOnClickListener(this);
      buttonX.setOnClickListener(this);
      buttonY.setOnClickListener(this);
      buttonZ.setOnClickListener(this);
      buttonDelete.setOnClickListener(this);
    }
    
    else if (language.equalsIgnoreCase("Русский"))
    {
      Button button_ru_A          = view.findViewById(R.id.ruA);
      Button button_ru_Be         = view.findViewById(R.id.ruBe);
      Button button_ru_Ve         = view.findViewById(R.id.ruVe);
      Button button_ru_Ge         = view.findViewById(R.id.ruGe);
      Button button_ru_De         = view.findViewById(R.id.ruDe);
      Button button_ru_Ye         = view.findViewById(R.id.ruYe);
      Button button_ru_Yo         = view.findViewById(R.id.ruYo);
      Button button_ru_Je         = view.findViewById(R.id.ruJe);
      Button button_ru_Ze         = view.findViewById(R.id.ruZe);
      Button button_ru_I          = view.findViewById(R.id.ruI);
      Button button_ru_Iy         = view.findViewById(R.id.ruIy);
      Button button_ru_Ka         = view.findViewById(R.id.ruKa);
      Button button_ru_El         = view.findViewById(R.id.ruEl);
      Button button_ru_Em         = view.findViewById(R.id.ruEm);
      Button button_ru_En         = view.findViewById(R.id.ruEn);
      Button button_ru_O          = view.findViewById(R.id.ruO);
      Button button_ru_Pe         = view.findViewById(R.id.ruPe);
      Button button_ru_Er         = view.findViewById(R.id.ruEr);
      Button button_ru_Es         = view.findViewById(R.id.ruEs);
      Button button_ru_Te         = view.findViewById(R.id.ruTe);
      Button button_ru_U          = view.findViewById(R.id.ruU);
      Button button_ru_Ef         = view.findViewById(R.id.ruEf);
      Button button_ru_Ha         = view.findViewById(R.id.ruHа);
      Button button_ru_Tce        = view.findViewById(R.id.ruTce);
      Button button_ru_Cha        = view.findViewById(R.id.ruCha);
      Button button_ru_Sha        = view.findViewById(R.id.ruSha);
      Button button_ru_Shcha      = view.findViewById(R.id.ruShcha);
      Button button_ru_SoftSymbol = view.findViewById(R.id.ruSoftSymbol);
      Button button_ru_Iii        = view.findViewById(R.id.ruIii);
      Button button_ru_HardSymbol = view.findViewById(R.id.ruHardSymbol);
      Button button_ru_Ee         = view.findViewById(R.id.ruEe);
      Button button_ru_Yu         = view.findViewById(R.id.ruYu);
      Button button_ru_Ya         = view.findViewById(R.id.ruYa);
      Button button_ru_Delete     = view.findViewById(R.id.ruDelete);
      button_ru_A.setOnClickListener(this);
      button_ru_Be.setOnClickListener(this);
      button_ru_Ve.setOnClickListener(this);
      button_ru_Ge.setOnClickListener(this);
      button_ru_De.setOnClickListener(this);
      button_ru_Ye.setOnClickListener(this);
      button_ru_Yo.setOnClickListener(this);
      button_ru_Je.setOnClickListener(this);
      button_ru_Ze.setOnClickListener(this);
      button_ru_I.setOnClickListener(this);
      button_ru_Iy.setOnClickListener(this);
      button_ru_Ka.setOnClickListener(this);
      button_ru_El.setOnClickListener(this);
      button_ru_Em.setOnClickListener(this);
      button_ru_En.setOnClickListener(this);
      button_ru_O.setOnClickListener(this);
      button_ru_Pe.setOnClickListener(this);
      button_ru_Er.setOnClickListener(this);
      button_ru_Es.setOnClickListener(this);
      button_ru_Te.setOnClickListener(this);
      button_ru_U.setOnClickListener(this);
      button_ru_Ef.setOnClickListener(this);
      button_ru_Ha.setOnClickListener(this);
      button_ru_Tce.setOnClickListener(this);
      button_ru_Cha.setOnClickListener(this);
      button_ru_Sha.setOnClickListener(this);
      button_ru_Shcha.setOnClickListener(this);
      button_ru_SoftSymbol.setOnClickListener(this);
      button_ru_Iii.setOnClickListener(this);
      button_ru_HardSymbol.setOnClickListener(this);
      button_ru_Ee.setOnClickListener(this);
      button_ru_Yu.setOnClickListener(this);
      button_ru_Ya.setOnClickListener(this);
      button_ru_Delete.setOnClickListener(this);
    }
    
    return view;
  }

  @Override
  public void onClick(View view)
  {
    if(view instanceof Button)
    {
      Button keyboardButton = (Button) view;
      
      final Animation zoomButtonAnimation = AnimationUtils.loadAnimation(getContext(), R.anim.zoom);
      keyboardButton.bringToFront();
      zoomButtonAnimation.setInterpolator(this);
      keyboardButton.startAnimation(zoomButtonAnimation);
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
    
    return (float) ((-1.0 * Math.pow(Math.E, -time / INTERPOLATOR_AMPLITUDE) *
                     Math.cos(INTERPOLATOR_FREQUENCY * time)) + 1);
  }
}
