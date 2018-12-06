package com.gumsis.base.dialog;

import android.widget.FrameLayout.LayoutParams;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout.LayoutParams;
import com.gumsis.checktls.AndroidUtilities;

public class LayoutHelper
{
  public static final int MATCH_PARENT = -1;
  public static final int WRAP_CONTENT = -2;
  
  public static FrameLayout.LayoutParams createFrame(int paramInt, float paramFloat)
  {
    return new FrameLayout.LayoutParams(getSize(paramInt), getSize(paramFloat));
  }
  
  public static FrameLayout.LayoutParams createFrame(int paramInt1, float paramFloat1, int paramInt2, float paramFloat2, float paramFloat3, float paramFloat4, float paramFloat5)
  {
    FrameLayout.LayoutParams localLayoutParams = new FrameLayout.LayoutParams(getSize(paramInt1), getSize(paramFloat1), paramInt2);
    localLayoutParams.setMargins(AndroidUtilities.dp(paramFloat2), AndroidUtilities.dp(paramFloat3), AndroidUtilities.dp(paramFloat4), AndroidUtilities.dp(paramFloat5));
    return localLayoutParams;
  }
  
  public static FrameLayout.LayoutParams createFrame(int paramInt1, int paramInt2, int paramInt3)
  {
    return new FrameLayout.LayoutParams(getSize(paramInt1), getSize(paramInt2), paramInt3);
  }
  
  public static LinearLayout.LayoutParams createLinear(int paramInt1, int paramInt2)
  {
    return new LinearLayout.LayoutParams(getSize(paramInt1), getSize(paramInt2));
  }
  
  public static LinearLayout.LayoutParams createLinear(int paramInt1, int paramInt2, float paramFloat)
  {
    return new LinearLayout.LayoutParams(getSize(paramInt1), getSize(paramInt2), paramFloat);
  }
  
  public static LinearLayout.LayoutParams createLinear(int paramInt1, int paramInt2, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4)
  {
    LinearLayout.LayoutParams localLayoutParams = new LinearLayout.LayoutParams(getSize(paramInt1), getSize(paramInt2));
    localLayoutParams.setMargins(AndroidUtilities.dp(paramFloat1), AndroidUtilities.dp(paramFloat2), AndroidUtilities.dp(paramFloat3), AndroidUtilities.dp(paramFloat4));
    return localLayoutParams;
  }
  
  public static LinearLayout.LayoutParams createLinear(int paramInt1, int paramInt2, float paramFloat, int paramInt3)
  {
    LinearLayout.LayoutParams localLayoutParams = new LinearLayout.LayoutParams(getSize(paramInt1), getSize(paramInt2), paramFloat);
    localLayoutParams.gravity = paramInt3;
    return localLayoutParams;
  }
  
  public static LinearLayout.LayoutParams createLinear(int paramInt1, int paramInt2, float paramFloat, int paramInt3, int paramInt4, int paramInt5, int paramInt6)
  {
    LinearLayout.LayoutParams localLayoutParams = new LinearLayout.LayoutParams(getSize(paramInt1), getSize(paramInt2), paramFloat);
    localLayoutParams.setMargins(AndroidUtilities.dp(paramInt3), AndroidUtilities.dp(paramInt4), AndroidUtilities.dp(paramInt5), AndroidUtilities.dp(paramInt6));
    return localLayoutParams;
  }
  
  public static LinearLayout.LayoutParams createLinear(int paramInt1, int paramInt2, float paramFloat, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7)
  {
    LinearLayout.LayoutParams localLayoutParams = new LinearLayout.LayoutParams(getSize(paramInt1), getSize(paramInt2), paramFloat);
    localLayoutParams.setMargins(AndroidUtilities.dp(paramInt4), AndroidUtilities.dp(paramInt5), AndroidUtilities.dp(paramInt6), AndroidUtilities.dp(paramInt7));
    localLayoutParams.gravity = paramInt3;
    return localLayoutParams;
  }
  
  public static LinearLayout.LayoutParams createLinear(int paramInt1, int paramInt2, int paramInt3)
  {
    LinearLayout.LayoutParams localLayoutParams = new LinearLayout.LayoutParams(getSize(paramInt1), getSize(paramInt2));
    localLayoutParams.gravity = paramInt3;
    return localLayoutParams;
  }
  
  public static LinearLayout.LayoutParams createLinear(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7)
  {
    LinearLayout.LayoutParams localLayoutParams = new LinearLayout.LayoutParams(getSize(paramInt1), getSize(paramInt2));
    localLayoutParams.setMargins(AndroidUtilities.dp(paramInt4), AndroidUtilities.dp(paramInt5), AndroidUtilities.dp(paramInt6), AndroidUtilities.dp(paramInt7));
    localLayoutParams.gravity = paramInt3;
    return localLayoutParams;
  }
  
  public static RelativeLayout.LayoutParams createRelative(float paramFloat1, float paramFloat2, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6)
  {
    return createRelative(paramFloat1, paramFloat2, paramInt1, paramInt2, paramInt3, paramInt4, -1, paramInt5, paramInt6);
  }
  
  public static RelativeLayout.LayoutParams createRelative(float paramFloat1, float paramFloat2, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7)
  {
    RelativeLayout.LayoutParams localLayoutParams = new RelativeLayout.LayoutParams(getSize(paramFloat1), getSize(paramFloat2));
    if (paramInt5 >= 0) {
      localLayoutParams.addRule(paramInt5);
    }
    if ((paramInt6 >= 0) && (paramInt7 >= 0)) {
      localLayoutParams.addRule(paramInt6, paramInt7);
    }
    localLayoutParams.leftMargin = AndroidUtilities.dp(paramInt1);
    localLayoutParams.topMargin = AndroidUtilities.dp(paramInt2);
    localLayoutParams.rightMargin = AndroidUtilities.dp(paramInt3);
    localLayoutParams.bottomMargin = AndroidUtilities.dp(paramInt4);
    return localLayoutParams;
  }
  
  public static RelativeLayout.LayoutParams createRelative(int paramInt1, int paramInt2)
  {
    return createRelative(paramInt1, paramInt2, 0, 0, 0, 0, -1, -1, -1);
  }
  
  public static RelativeLayout.LayoutParams createRelative(int paramInt1, int paramInt2, int paramInt3)
  {
    return createRelative(paramInt1, paramInt2, 0, 0, 0, 0, paramInt3, -1, -1);
  }
  
  public static RelativeLayout.LayoutParams createRelative(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    return createRelative(paramInt1, paramInt2, 0, 0, 0, 0, -1, paramInt3, paramInt4);
  }
  
  public static RelativeLayout.LayoutParams createRelative(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5)
  {
    return createRelative(paramInt1, paramInt2, 0, 0, 0, 0, paramInt3, paramInt4, paramInt5);
  }
  
  public static RelativeLayout.LayoutParams createRelative(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6)
  {
    return createRelative(paramInt1, paramInt2, paramInt3, paramInt4, paramInt5, paramInt6, -1, -1, -1);
  }
  
  public static RelativeLayout.LayoutParams createRelative(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7)
  {
    return createRelative(paramInt1, paramInt2, paramInt3, paramInt4, paramInt5, paramInt6, paramInt7, -1, -1);
  }
  
  public static FrameLayout.LayoutParams createScroll(int paramInt1, int paramInt2, int paramInt3)
  {
    return new FrameLayout.LayoutParams(getSize(paramInt1), getSize(paramInt2), paramInt3);
  }
  
  private static int getSize(float paramFloat)
  {
    if (paramFloat < 0.0F) {}
    for (;;)
    {
      return (int)paramFloat;
      paramFloat = AndroidUtilities.dp(paramFloat);
    }
  }
}


/* Location:              /home/cmd/Programmierung/Android/reverse-engineering/fakegumtree/FakeGumtree-dex2jar.jar!/com/gumsis/base/dialog/LayoutHelper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */