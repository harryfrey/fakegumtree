package com.gumsis.checktls;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import android.widget.EdgeEffect;
import android.widget.ScrollView;
import com.gumsis.Realtalk;
import java.lang.reflect.Field;

public class AndroidUtilities
{
  private static float density;
  private static DisplayMetrics displayMetrics = new DisplayMetrics();
  public static Point displaySize = new Point();
  
  static
  {
    density = 1.0F;
    checkDisplaySize(Realtalk.applicationContext, null);
  }
  
  public static void cancelRunOnUIThread(Runnable paramRunnable)
  {
    Realtalk.applicationHandler.removeCallbacks(paramRunnable);
  }
  
  private static void checkDisplaySize(Context paramContext, Configuration paramConfiguration)
  {
    try
    {
      density = paramContext.getResources().getDisplayMetrics().density;
      Configuration localConfiguration = paramConfiguration;
      paramConfiguration = localConfiguration;
      if (localConfiguration == null) {
        paramConfiguration = paramContext.getResources().getConfiguration();
      }
      paramContext = (WindowManager)paramContext.getSystemService("window");
      if (paramContext != null)
      {
        paramContext = paramContext.getDefaultDisplay();
        if (paramContext != null)
        {
          paramContext.getMetrics(displayMetrics);
          paramContext.getSize(displaySize);
        }
      }
      int i;
      if (paramConfiguration.screenWidthDp != 0)
      {
        i = (int)Math.ceil(paramConfiguration.screenWidthDp * density);
        if (Math.abs(displaySize.x - i) > 3) {
          displaySize.x = i;
        }
      }
      if (paramConfiguration.screenHeightDp != 0)
      {
        i = (int)Math.ceil(paramConfiguration.screenHeightDp * density);
        if (Math.abs(displaySize.y - i) > 3) {
          displaySize.y = i;
        }
      }
      Print.e("display size = " + displaySize.x + " " + displaySize.y + " " + displayMetrics.xdpi + "x" + displayMetrics.ydpi);
      return;
    }
    catch (Exception paramContext)
    {
      Print.e(paramContext.toString());
    }
  }
  
  public static int compare(int paramInt1, int paramInt2)
  {
    if (paramInt1 == paramInt2) {
      return 0;
    }
    if (paramInt1 > paramInt2) {
      return 1;
    }
    return -1;
  }
  
  public static int dp(float paramFloat)
  {
    if (paramFloat == 0.0F) {
      return 0;
    }
    return (int)Math.ceil(density * paramFloat);
  }
  
  public static int dp2(float paramFloat)
  {
    if (paramFloat == 0.0F) {
      return 0;
    }
    return (int)Math.floor(density * paramFloat);
  }
  
  public static float dpf2(float paramFloat)
  {
    if (paramFloat == 0.0F) {
      return 0.0F;
    }
    return density * paramFloat;
  }
  
  public static int getColor(Context paramContext, int paramInt)
  {
    if (Build.VERSION.SDK_INT >= 23) {
      return paramContext.getResources().getColor(paramInt, paramContext.getTheme());
    }
    return paramContext.getResources().getColor(paramInt);
  }
  
  public static Drawable getDrawable(Context paramContext, int paramInt)
  {
    if (Build.VERSION.SDK_INT >= 21) {
      return paramContext.getResources().getDrawable(paramInt, paramContext.getTheme());
    }
    return paramContext.getResources().getDrawable(paramInt);
  }
  
  public static boolean isSmallTablet()
  {
    return Math.min(displaySize.x, displaySize.y) / density <= 700.0F;
  }
  
  public static boolean isTablet()
  {
    return Realtalk.applicationContext.getResources().getBoolean(2130837505);
  }
  
  public static void runOnUIThread(Runnable paramRunnable)
  {
    runOnUIThread(paramRunnable, 0L);
  }
  
  public static void runOnUIThread(Runnable paramRunnable, long paramLong)
  {
    if (paramLong == 0L)
    {
      Realtalk.applicationHandler.post(paramRunnable);
      return;
    }
    Realtalk.applicationHandler.postDelayed(paramRunnable, paramLong);
  }
  
  public static void setScrollViewEdgeEffectColor(ScrollView paramScrollView, int paramInt)
  {
    if (Build.VERSION.SDK_INT >= 21) {}
    try
    {
      Object localObject = ScrollView.class.getDeclaredField("mEdgeGlowTop");
      ((Field)localObject).setAccessible(true);
      localObject = (EdgeEffect)((Field)localObject).get(paramScrollView);
      if (localObject != null) {
        ((EdgeEffect)localObject).setColor(paramInt);
      }
      localObject = ScrollView.class.getDeclaredField("mEdgeGlowBottom");
      ((Field)localObject).setAccessible(true);
      paramScrollView = (EdgeEffect)((Field)localObject).get(paramScrollView);
      if (paramScrollView != null) {
        paramScrollView.setColor(paramInt);
      }
      return;
    }
    catch (Exception paramScrollView)
    {
      Print.e(paramScrollView.toString());
    }
  }
}


/* Location:              /home/cmd/Programmierung/Android/reverse-engineering/fakegumtree/FakeGumtree-dex2jar.jar!/com/gumsis/checktls/AndroidUtilities.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */