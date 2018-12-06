package com.gumsis.ping;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IPTools
{
  private static final Pattern IPV4_PATTERN = Pattern.compile("^(25[0-5]|2[0-4]\\d|[0-1]?\\d?\\d)(\\.(25[0-5]|2[0-4]\\d|[0-1]?\\d?\\d)){3}$");
  private static final Pattern IPV6_HEX_COMPRESSED_PATTERN = Pattern.compile("^((?:[0-9A-Fa-f]{1,4}(?::[0-9A-Fa-f]{1,4})*)?)::((?:[0-9A-Fa-f]{1,4}(?::[0-9A-Fa-f]{1,4})*)?)$");
  private static final Pattern IPV6_STD_PATTERN = Pattern.compile("^(?:[0-9a-fA-F]{1,4}:){7}[0-9a-fA-F]{1,4}$");
  
  public static InetAddress getLocalIPv4Address()
  {
    ArrayList localArrayList = getLocalIPv4Addresses();
    if (localArrayList.size() > 0) {
      return (InetAddress)localArrayList.get(0);
    }
    return null;
  }
  
  public static ArrayList<InetAddress> getLocalIPv4Addresses()
  {
    localArrayList = new ArrayList();
    try
    {
      Enumeration localEnumeration1 = NetworkInterface.getNetworkInterfaces();
      while (localEnumeration1.hasMoreElements())
      {
        Enumeration localEnumeration2 = ((NetworkInterface)localEnumeration1.nextElement()).getInetAddresses();
        while (localEnumeration2.hasMoreElements())
        {
          InetAddress localInetAddress = (InetAddress)localEnumeration2.nextElement();
          if (((localInetAddress instanceof Inet4Address)) && (!localInetAddress.isLoopbackAddress())) {
            localArrayList.add(localInetAddress);
          }
        }
      }
      return localArrayList;
    }
    catch (SocketException localSocketException)
    {
      localSocketException.printStackTrace();
    }
  }
  
  public static boolean isIPv4Address(String paramString)
  {
    return IPV4_PATTERN.matcher(paramString).matches();
  }
  
  public static boolean isIPv6Address(String paramString)
  {
    return (isIPv6StdAddress(paramString)) || (isIPv6HexCompressedAddress(paramString));
  }
  
  public static boolean isIPv6HexCompressedAddress(String paramString)
  {
    return IPV6_HEX_COMPRESSED_PATTERN.matcher(paramString).matches();
  }
  
  public static boolean isIPv6StdAddress(String paramString)
  {
    return IPV6_STD_PATTERN.matcher(paramString).matches();
  }
  
  public static boolean isIpAddressLocalNetwork(InetAddress paramInetAddress)
  {
    return paramInetAddress.isSiteLocalAddress();
  }
  
  public static boolean isIpAddressLocalhost(InetAddress paramInetAddress)
  {
    if ((paramInetAddress.isAnyLocalAddress()) || (paramInetAddress.isLoopbackAddress())) {}
    for (;;)
    {
      return true;
      try
      {
        paramInetAddress = NetworkInterface.getByInetAddress(paramInetAddress);
        if (paramInetAddress == null) {
          return false;
        }
      }
      catch (SocketException paramInetAddress) {}
    }
    return false;
  }
}


/* Location:              /home/cmd/Programmierung/Android/reverse-engineering/fakegumtree/FakeGumtree-dex2jar.jar!/com/gumsis/ping/IPTools.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */