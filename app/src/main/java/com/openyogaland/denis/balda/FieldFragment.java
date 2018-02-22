package com.openyogaland.denis.balda;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class FieldFragment extends Fragment
{
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
  {
    View view = inflater.inflate(R.layout.field_fragment, container, false);
    LinearLayout fieldLayout = view.findViewById(R.id.fieldLayout);
    return view;
  }
}
