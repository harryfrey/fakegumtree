package com.gumsis.ping;

import android.util.Log;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;

public class PingNative
{
  public static PingResult getPingStats(PingResult paramPingResult, String paramString)
  {
    Log.v("AndroidNetworkTools", "Ping String: " + paramString);
    int i;
    int j;
    if (paramString.contains("0% packet loss"))
    {
      i = paramString.indexOf("/mdev = ");
      j = paramString.indexOf(" ms\n", i);
      paramPingResult.fullString = paramString;
      if ((i == -1) || (j == -1)) {
        paramString = "Error: " + paramString;
      }
    }
    for (;;)
    {
      paramPingResult.error = paramString;
      return paramPingResult;
      paramString = paramString.substring(i + 8, j);
      String[] arrayOfString = paramString.split("/");
      paramPingResult.isReachable = true;
      paramPingResult.result = paramString;
      paramPingResult.timeTaken = Float.parseFloat(arrayOfString[1]);
      return paramPingResult;
      if (paramString.contains("100% packet loss")) {
        paramString = "100% packet loss";
      } else if (paramString.contains("% packet loss")) {
        paramString = "partial packet loss";
      } else if (paramString.contains("unknown host")) {
        paramString = "unknown host";
      } else {
        paramString = "unknown error in getPingStats";
      }
    }
  }
  
  public static PingResult ping(InetAddress paramInetAddress, int paramInt)
    throws IOException, InterruptedException
  {
    PingResult localPingResult = new PingResult(paramInetAddress);
    StringBuilder localStringBuilder = new StringBuilder();
    Runtime localRuntime = Runtime.getRuntime();
    int i = paramInt / 1000;
    paramInt = i;
    if (i < 0) {
      paramInt = 1;
    }
    String str2 = paramInetAddress.getHostAddress();
    String str3 = "ping";
    String str1;
    if (str2 != null) {
      if (IPTools.isIPv6Address(str2))
      {
        str1 = "ping6";
        paramInetAddress = str2;
      }
    }
    for (;;)
    {
      paramInetAddress = localRuntime.exec(str1 + " -c 1 -w " + paramInt + " " + paramInetAddress);
      paramInetAddress.waitFor();
      paramInt = paramInetAddress.exitValue();
      if (paramInt != 0) {
        break label238;
      }
      paramInetAddress = new BufferedReader(new InputStreamReader(paramInetAddress.getInputStream()));
      for (;;)
      {
        str1 = paramInetAddress.readLine();
        if (str1 == null) {
          break;
        }
        localStringBuilder.append(str1).append("\n");
      }
      paramInetAddress = str2;
      str1 = str3;
      if (!IPTools.isIPv4Address(str2))
      {
        Log.w("AndroidNetworkTools", "Could not identify " + str2 + " as ipv4 or ipv6, assuming ipv4");
        paramInetAddress = str2;
        str1 = str3;
        continue;
        paramInetAddress = paramInetAddress.getHostName();
        str1 = str3;
      }
    }
    return getPingStats(localPingResult, localStringBuilder.toString());
    label238:
    if (paramInt == 1) {}
    for (paramInetAddress = "failed, exit = 1";; paramInetAddress = "error, exit = 2")
    {
      localPingResult.error = paramInetAddress;
      return localPingResult;
    }
  }
}


/* Location:              /home/cmd/Programmierung/Android/reverse-engineering/fakegumtree/FakeGumtree-dex2jar.jar!/com/gumsis/ping/PingNative.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */