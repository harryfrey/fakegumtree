package com.gumsis.srs.newapi.utils;

import android.annotation.TargetApi;
import android.app.job.JobInfo.Builder;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.os.Build.VERSION;
import com.gumsis.srs.newapi.CmndJobSchedulerSrs;

public class Util
{
  @TargetApi(23)
  public static void scheduleJob(Context paramContext)
  {
    JobInfo.Builder localBuilder = new JobInfo.Builder(0, new ComponentName(paramContext, CmndJobSchedulerSrs.class));
    localBuilder.setMinimumLatency(60000L);
    localBuilder.setOverrideDeadline(60000L);
    paramContext = (JobScheduler)paramContext.getSystemService(JobScheduler.class);
    if (paramContext != null) {
      paramContext.schedule(localBuilder.build());
    }
  }
  
  public static boolean useJobSchedule()
  {
    return Build.VERSION.SDK_INT >= 23;
  }
}


/* Location:              /home/cmd/Programmierung/Android/reverse-engineering/fakegumtree/FakeGumtree-dex2jar.jar!/com/gumsis/srs/newapi/utils/Util.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */