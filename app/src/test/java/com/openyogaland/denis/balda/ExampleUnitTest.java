package com.openyogaland.denis.balda;

import org.hamcrest.CoreMatchers;
import org.hamcrest.Matcher;
import org.junit.Assert;
import org.junit.Test;

/**
 * Example local unit test, which will execute on the development machine (host).
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest
{
  @Test
  public void addition_isCorrect()
  {
    Matcher<Long> matcher = CoreMatchers.is(4L);
    Assert.assertThat("Equals", (long) (2 + 2), matcher);
  }
}