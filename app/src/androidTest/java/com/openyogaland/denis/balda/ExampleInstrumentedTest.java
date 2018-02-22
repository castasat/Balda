package com.openyogaland.denis.balda;

import android.content.Context;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.hamcrest.CoreMatchers;
import org.hamcrest.Matcher;

/**
 * Instrumented test, which will execute on an Android device.
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest
{
  @Test
  public void useAppContext() 
  {
    // Context of the app under test.
    Context appContext = InstrumentationRegistry.getTargetContext();
  
    String packageName = appContext.getPackageName();
    Matcher<String> matcher = CoreMatchers.is("com.openyogaland.denis.balda");
    Assert.assertThat("Equals", packageName, matcher);
  }
}
