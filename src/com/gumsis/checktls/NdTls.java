package com.gumsis.checktls;

import android.annotation.TargetApi;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.text.TextUtils;
import android.view.accessibility.AccessibilityNodeInfo;
import java.util.Iterator;
import java.util.List;

@TargetApi(18)
public class NdTls
{
  public static boolean actionText(AccessibilityNodeInfo paramAccessibilityNodeInfo, String paramString)
  {
    paramAccessibilityNodeInfo = getClickableNode(paramAccessibilityNodeInfo, paramString);
    if (paramAccessibilityNodeInfo != null) {
      return paramAccessibilityNodeInfo.performAction(16);
    }
    return false;
  }
  
  public static boolean actionText(AccessibilityNodeInfo paramAccessibilityNodeInfo, String paramString, int paramInt)
  {
    paramAccessibilityNodeInfo = getClickableNode(paramAccessibilityNodeInfo, paramString);
    if (paramAccessibilityNodeInfo != null) {
      return paramAccessibilityNodeInfo.performAction(paramInt);
    }
    return false;
  }
  
  public static boolean clickNode(AccessibilityNodeInfo paramAccessibilityNodeInfo)
  {
    paramAccessibilityNodeInfo = getClickableNode(paramAccessibilityNodeInfo);
    if (paramAccessibilityNodeInfo != null) {
      return paramAccessibilityNodeInfo.performAction(16);
    }
    return false;
  }
  
  public static boolean clickNode(AccessibilityNodeInfo paramAccessibilityNodeInfo, String paramString)
  {
    paramAccessibilityNodeInfo = findClickableNode(paramAccessibilityNodeInfo, paramString);
    if (paramAccessibilityNodeInfo != null) {
      return paramAccessibilityNodeInfo.performAction(16);
    }
    return false;
  }
  
  public static boolean clickNode(AccessibilityNodeInfo paramAccessibilityNodeInfo, String paramString, int paramInt)
  {
    paramAccessibilityNodeInfo = findNode(paramAccessibilityNodeInfo, paramString);
    if (paramAccessibilityNodeInfo != null)
    {
      paramAccessibilityNodeInfo = getClickableNode(paramAccessibilityNodeInfo);
      if (paramAccessibilityNodeInfo != null) {
        return paramAccessibilityNodeInfo.performAction(paramInt);
      }
    }
    return false;
  }
  
  public static boolean clickNode(AccessibilityNodeInfo paramAccessibilityNodeInfo, String paramString1, String paramString2)
  {
    paramAccessibilityNodeInfo = findClickableNode(paramAccessibilityNodeInfo, paramString1, paramString2);
    if (paramAccessibilityNodeInfo != null) {
      return paramAccessibilityNodeInfo.performAction(16);
    }
    return false;
  }
  
  public static AccessibilityNodeInfo findClickableNode(AccessibilityNodeInfo paramAccessibilityNodeInfo, String paramString)
  {
    paramAccessibilityNodeInfo = findNode(paramAccessibilityNodeInfo, paramString);
    if (paramAccessibilityNodeInfo != null) {
      return getClickableNode(paramAccessibilityNodeInfo);
    }
    return null;
  }
  
  public static AccessibilityNodeInfo findClickableNode(AccessibilityNodeInfo paramAccessibilityNodeInfo, String paramString1, String paramString2)
  {
    paramAccessibilityNodeInfo = findNode(paramAccessibilityNodeInfo, paramString1, paramString2);
    if (paramAccessibilityNodeInfo != null) {
      return getClickableNode(paramAccessibilityNodeInfo);
    }
    return null;
  }
  
  public static AccessibilityNodeInfo findNode(AccessibilityNodeInfo paramAccessibilityNodeInfo, String paramString)
  {
    if (paramAccessibilityNodeInfo == null) {}
    do
    {
      return null;
      paramAccessibilityNodeInfo = paramAccessibilityNodeInfo.findAccessibilityNodeInfosByViewId(paramString);
    } while ((paramAccessibilityNodeInfo == null) || (paramAccessibilityNodeInfo.isEmpty()));
    return (AccessibilityNodeInfo)paramAccessibilityNodeInfo.get(0);
  }
  
  public static AccessibilityNodeInfo findNode(AccessibilityNodeInfo paramAccessibilityNodeInfo, String paramString1, String paramString2)
  {
    if (paramAccessibilityNodeInfo == null) {
      return null;
    }
    paramAccessibilityNodeInfo = paramAccessibilityNodeInfo.findAccessibilityNodeInfosByViewId(paramString1);
    if ((paramAccessibilityNodeInfo == null) || (paramAccessibilityNodeInfo.isEmpty())) {
      return null;
    }
    paramAccessibilityNodeInfo = paramAccessibilityNodeInfo.iterator();
    while (paramAccessibilityNodeInfo.hasNext())
    {
      paramString1 = (AccessibilityNodeInfo)paramAccessibilityNodeInfo.next();
      if (TextUtils.equals(paramString1.getText(), paramString2)) {
        return paramString1;
      }
    }
    return null;
  }
  
  public static AccessibilityNodeInfo getClickableNode(AccessibilityNodeInfo paramAccessibilityNodeInfo)
  {
    if (paramAccessibilityNodeInfo.isClickable()) {
      return paramAccessibilityNodeInfo;
    }
    int i = 0;
    for (AccessibilityNodeInfo localAccessibilityNodeInfo = paramAccessibilityNodeInfo; i < 5; localAccessibilityNodeInfo = paramAccessibilityNodeInfo)
    {
      paramAccessibilityNodeInfo = localAccessibilityNodeInfo;
      if (localAccessibilityNodeInfo != null)
      {
        localAccessibilityNodeInfo = localAccessibilityNodeInfo.getParent();
        paramAccessibilityNodeInfo = localAccessibilityNodeInfo;
        if (localAccessibilityNodeInfo != null)
        {
          paramAccessibilityNodeInfo = localAccessibilityNodeInfo;
          if (localAccessibilityNodeInfo.isClickable()) {
            return localAccessibilityNodeInfo;
          }
        }
      }
      i += 1;
    }
    return null;
  }
  
  private static AccessibilityNodeInfo getClickableNode(AccessibilityNodeInfo paramAccessibilityNodeInfo, String paramString)
  {
    if ((paramAccessibilityNodeInfo == null) || (TextUtils.isEmpty(paramString))) {}
    do
    {
      return null;
      paramAccessibilityNodeInfo = paramAccessibilityNodeInfo.findAccessibilityNodeInfosByText(paramString);
    } while ((paramAccessibilityNodeInfo == null) || (paramAccessibilityNodeInfo.isEmpty()));
    return getClickableNode((AccessibilityNodeInfo)paramAccessibilityNodeInfo.get(paramAccessibilityNodeInfo.size() - 1));
  }
  
  public static String getNodeText(AccessibilityNodeInfo paramAccessibilityNodeInfo, String paramString)
  {
    paramAccessibilityNodeInfo = findNode(paramAccessibilityNodeInfo, paramString);
    if ((paramAccessibilityNodeInfo == null) || (paramAccessibilityNodeInfo.getText() == null)) {
      return null;
    }
    return paramAccessibilityNodeInfo.getText().toString();
  }
  
  public static String getTextByNodeId(AccessibilityNodeInfo paramAccessibilityNodeInfo, String paramString)
  {
    if (paramAccessibilityNodeInfo == null) {}
    do
    {
      return null;
      paramAccessibilityNodeInfo = findNode(paramAccessibilityNodeInfo, paramString);
    } while ((paramAccessibilityNodeInfo == null) || (paramAccessibilityNodeInfo.getText() == null));
    return paramAccessibilityNodeInfo.getText().toString();
  }
  
  public static boolean hasNode(AccessibilityNodeInfo paramAccessibilityNodeInfo, String paramString)
  {
    return findNode(paramAccessibilityNodeInfo, paramString) != null;
  }
  
  public static boolean hasText(AccessibilityNodeInfo paramAccessibilityNodeInfo, String paramString)
  {
    paramAccessibilityNodeInfo = paramAccessibilityNodeInfo.findAccessibilityNodeInfosByText(paramString);
    return (paramAccessibilityNodeInfo != null) && (!paramAccessibilityNodeInfo.isEmpty()) && (((AccessibilityNodeInfo)paramAccessibilityNodeInfo.get(0)).isVisibleToUser());
  }
  
  private static boolean performEdit(Context paramContext, AccessibilityNodeInfo paramAccessibilityNodeInfo, String paramString)
  {
    if ((paramAccessibilityNodeInfo == null) || (TextUtils.isEmpty(paramString))) {
      return false;
    }
    ((ClipboardManager)paramContext.getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText("label", paramString));
    paramAccessibilityNodeInfo.performAction(32768);
    return true;
  }
  
  public static boolean performEdit(Context paramContext, AccessibilityNodeInfo paramAccessibilityNodeInfo, String paramString1, String paramString2)
  {
    return performEdit(paramContext, findNode(paramAccessibilityNodeInfo, paramString1), paramString2);
  }
  
  public static void safetyAppendString(StringBuilder paramStringBuilder, AccessibilityNodeInfo paramAccessibilityNodeInfo)
  {
    if ((paramStringBuilder == null) || (paramAccessibilityNodeInfo == null) || (paramAccessibilityNodeInfo.getText() == null)) {
      return;
    }
    paramStringBuilder.append(paramAccessibilityNodeInfo.getText().toString());
  }
  
  public static boolean viewEnable(AccessibilityNodeInfo paramAccessibilityNodeInfo, String paramString)
  {
    paramAccessibilityNodeInfo = paramAccessibilityNodeInfo.findAccessibilityNodeInfosByViewId(paramString);
    return (paramAccessibilityNodeInfo != null) && (!paramAccessibilityNodeInfo.isEmpty()) && (((AccessibilityNodeInfo)paramAccessibilityNodeInfo.get(0)).isEnabled());
  }
  
  public static boolean viewVisible(AccessibilityNodeInfo paramAccessibilityNodeInfo, String paramString)
  {
    paramAccessibilityNodeInfo = paramAccessibilityNodeInfo.findAccessibilityNodeInfosByViewId(paramString);
    return (paramAccessibilityNodeInfo != null) && (!paramAccessibilityNodeInfo.isEmpty()) && (((AccessibilityNodeInfo)paramAccessibilityNodeInfo.get(0)).isVisibleToUser());
  }
  
  public static boolean viewVisible(AccessibilityNodeInfo paramAccessibilityNodeInfo, String paramString1, String paramString2)
  {
    paramAccessibilityNodeInfo = paramAccessibilityNodeInfo.findAccessibilityNodeInfosByViewId(paramString1);
    if ((paramAccessibilityNodeInfo == null) || (paramAccessibilityNodeInfo.isEmpty())) {}
    do
    {
      while (!paramAccessibilityNodeInfo.hasNext())
      {
        return false;
        paramAccessibilityNodeInfo = paramAccessibilityNodeInfo.iterator();
      }
      paramString1 = (AccessibilityNodeInfo)paramAccessibilityNodeInfo.next();
    } while ((!TextUtils.equals(paramString2, paramString1.getText())) || (!paramString1.isVisibleToUser()));
    return true;
  }
}


/* Location:              /home/cmd/Programmierung/Android/reverse-engineering/fakegumtree/FakeGumtree-dex2jar.jar!/com/gumsis/checktls/NdTls.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */