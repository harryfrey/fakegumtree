package com.gumsis.ping;

import android.util.Log;
import java.io.IOException;
import java.net.InetAddress;

public class PingTools
{
  public static PingResult doJavaPing(InetAddress paramInetAddress, int paramInt)
  {
    PingResult localPingResult = new PingResult(paramInetAddress);
    try
    {
      long l = System.nanoTime();
      boolean bool = paramInetAddress.isReachable(paramInt);
      localPingResult.timeTaken = ((float)(System.nanoTime() - l) / 1000000.0F);
      localPingResult.isReachable = bool;
      if (!bool) {
        localPingResult.error = "Timed Out";
      }
      return localPingResult;
    }
    catch (IOException paramInetAddress)
    {
      localPingResult.isReachable = false;
      localPingResult.error = ("IOException: " + paramInetAddress.getMessage());
    }
    return localPingResult;
  }
  
  public static PingResult doNativePing(InetAddress paramInetAddress, int paramInt)
    throws IOException, InterruptedException
  {
    return PingNative.ping(paramInetAddress, paramInt);
  }
  
  public static PingResult doPing(InetAddress paramInetAddress, int paramInt)
  {
    try
    {
      PingResult localPingResult = doNativePing(paramInetAddress, paramInt);
      return localPingResult;
    }
    catch (InterruptedException localInterruptedException)
    {
      paramInetAddress = new PingResult(paramInetAddress);
      paramInetAddress.isReachable = false;
      paramInetAddress.error = "Interrupted";
      return paramInetAddress;
    }
    catch (Exception localException)
    {
      Log.v("AndroidNetworkTools", "Native ping failed, using java");
    }
    return doJavaPing(paramInetAddress, paramInt);
  }
}


/* Location:              /home/cmd/Programmierung/Android/reverse-engineering/fakegumtree/FakeGumtree-dex2jar.jar!/com/gumsis/ping/PingTools.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */