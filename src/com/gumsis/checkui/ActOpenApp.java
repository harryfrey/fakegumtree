package com.gumsis.checkui;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import com.gumsis.Realtalk;
import com.gumsis.base.BaseActivity;
import com.gumsis.mess.model.MessageEvent;
import de.greenrobot.event.EventBus;

public class ActOpenApp
  extends BaseActivity
{
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    paramBundle = getIntent().getStringExtra("packageName");
    if (paramBundle != null)
    {
      if (paramBundle.length() > 0) {
        try
        {
          EventBus.getDefault().postSticky(new MessageEvent("push_end_status"));
          Realtalk.setPlaySound(this, false);
          paramBundle = getPackageManager().getLaunchIntentForPackage(paramBundle);
          paramBundle.addCategory("android.intent.category.LAUNCHER");
          startActivity(paramBundle);
          finishActivity();
          return;
        }
        catch (Exception paramBundle)
        {
          finishActivity();
          return;
        }
      }
      finishActivity();
      return;
    }
    finishActivity();
  }
}


/* Location:              /home/cmd/Programmierung/Android/reverse-engineering/fakegumtree/FakeGumtree-dex2jar.jar!/com/gumsis/checkui/ActOpenApp.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */