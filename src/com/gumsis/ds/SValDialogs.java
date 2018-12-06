package com.gumsis.ds;

import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import com.gumsis.base.dialog.AlertDialog;
import com.gumsis.base.dialog.AlertDialog.Builder;
import com.gumsis.checktls.Print;

public class SValDialogs
{
  public static void showDialog(Context paramContext, String paramString1, String paramString2)
  {
    try
    {
      paramContext = new AlertDialog.Builder(paramContext);
      paramContext.setTitle(paramString1).setMessage(paramString2).setPositiveButton("OK", new DialogInterface.OnClickListener()
      {
        public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
        {
          paramAnonymousDialogInterface.cancel();
          paramAnonymousDialogInterface.dismiss();
        }
      });
      paramContext = paramContext.create();
      paramContext.setCanceledOnTouchOutside(false);
      paramContext.show();
      return;
    }
    catch (Exception paramContext)
    {
      Print.e(paramContext.toString());
    }
  }
}


/* Location:              /home/cmd/Programmierung/Android/reverse-engineering/fakegumtree/FakeGumtree-dex2jar.jar!/com/gumsis/ds/SValDialogs.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */