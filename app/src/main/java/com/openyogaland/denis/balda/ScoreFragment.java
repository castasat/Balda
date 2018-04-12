package com.openyogaland.denis.balda;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ScoreFragment extends Fragment
{
  @Override @Nullable
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState)
  {
    View view = inflater.inflate(R.layout.score_fragment, container, false);
    return view;
  }
}
