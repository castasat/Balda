package com.openyogaland.denis.balda;

import android.content.Context;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;

public class SquareButton extends AppCompatButton
{
  public SquareButton(Context context)
  {
    super(context);
  }
  
  public SquareButton(Context context, AttributeSet attrs)
  {
    super(context, attrs);
  }
  
  public SquareButton(Context context, AttributeSet attrs, int defStyleAttr)
  {
    super(context, attrs, defStyleAttr);
  }
  
  @Override
  public void setWidth(int pixels)
  {
    int height = getHeight();
    int size   = (pixels > height) ? height : pixels;
    super.setWidth(size);
  }
  
  @Override
  public void setHeight(int pixels)
  {
    int width = getWidth();
    int size = (width < pixels) ? width : pixels;
    super.setHeight(size);
  }
  
  @Override
  protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
  {
    super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    int width  = MeasureSpec.getSize(widthMeasureSpec);
    int height = MeasureSpec.getSize(heightMeasureSpec);
    int size   = (width > height) ? height : width;
    setMeasuredDimension(size, size);
  }
}
