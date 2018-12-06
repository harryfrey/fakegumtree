package com.gumsis.base;

import android.app.Activity;
import com.gumsis.base.dialog.AlertDialog;

public abstract class BaseActivity
  extends Activity
{
  public void destroyDialog(AlertDialog paramAlertDialog)
  {
    if (paramAlertDialog != null) {}
    try
    {
      paramAlertDialog.cancel();
      paramAlertDialog.dismiss();
      finishActivity();
      return;
    }
    catch (Exception paramAlertDialog)
    {
      paramAlertDialog.printStackTrace();
    }
  }
  
  public void finishActivity()
  {
    finish();
  }
}


/* Location:              /home/cmd/Programmierung/Android/reverse-engineering/fakegumtree/FakeGumtree-dex2jar.jar!/com/gumsis/base/BaseActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */