package com.gumsis.api.request;

import android.util.Log;
import java.io.IOException;
import java.net.HttpURLConnection;

public abstract class BaseConnection
{
  protected static final int CONNECT_TIMEOUT = 5000;
  protected static final int DEFAULT_READ_TIMEOUT = 10000;
  protected static final String HTTP_REQ_METHOD_GET = "GET";
  protected static final String HTTP_REQ_METHOD_POST = "POST";
  protected static final String HTTP_REQ_PROPERTY_CHARSET = "Accept-Charset";
  protected static final String HTTP_REQ_PROPERTY_CONTENT_LENGTH = "Content-Length";
  protected static final String HTTP_REQ_PROPERTY_CONTENT_TYPE = "Content-Type";
  protected static final String HTTP_REQ_VALUE_CHARSET = "UTF-8";
  protected static final String HTTP_REQ_VALUE_CONTENT_TYPE = "application/x-www-form-urlencoded";
  private static final String LOG_TAG = "REALTALK REQUEST";
  
  private void setURLConnectionCommonPara()
  {
    HttpURLConnection localHttpURLConnection = getURLConnection();
    if (localHttpURLConnection == null) {
      return;
    }
    localHttpURLConnection.setConnectTimeout(5000);
    localHttpURLConnection.setReadTimeout(10000);
    localHttpURLConnection.setUseCaches(false);
    localHttpURLConnection.setRequestProperty("Accept-Charset", "UTF-8");
    localHttpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
  }
  
  /* Error */
  protected String doGetRequest()
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_1
    //   2: aconst_null
    //   3: astore 10
    //   5: aconst_null
    //   6: astore 9
    //   8: aconst_null
    //   9: astore 7
    //   11: aconst_null
    //   12: astore 5
    //   14: aconst_null
    //   15: astore 8
    //   17: aload 9
    //   19: astore 6
    //   21: aload 5
    //   23: astore_3
    //   24: aload_1
    //   25: astore_2
    //   26: aload 10
    //   28: astore 4
    //   30: aload_0
    //   31: invokevirtual 44	com/gumsis/api/request/BaseConnection:getURLConnection	()Ljava/net/HttpURLConnection;
    //   34: astore 11
    //   36: aload 11
    //   38: ifnonnull +30 -> 68
    //   41: iconst_0
    //   42: ifeq +11 -> 53
    //   45: new 71	java/lang/NullPointerException
    //   48: dup
    //   49: invokespecial 72	java/lang/NullPointerException:<init>	()V
    //   52: athrow
    //   53: iconst_0
    //   54: ifeq +11 -> 65
    //   57: new 71	java/lang/NullPointerException
    //   60: dup
    //   61: invokespecial 72	java/lang/NullPointerException:<init>	()V
    //   64: athrow
    //   65: ldc 74
    //   67: areturn
    //   68: aload 9
    //   70: astore 6
    //   72: aload 5
    //   74: astore_3
    //   75: aload_1
    //   76: astore_2
    //   77: aload 10
    //   79: astore 4
    //   81: aload 11
    //   83: ldc 13
    //   85: invokevirtual 78	java/net/HttpURLConnection:setRequestMethod	(Ljava/lang/String;)V
    //   88: aload 9
    //   90: astore 6
    //   92: aload 5
    //   94: astore_3
    //   95: aload_1
    //   96: astore_2
    //   97: aload 10
    //   99: astore 4
    //   101: aload 11
    //   103: invokevirtual 82	java/net/HttpURLConnection:getInputStream	()Ljava/io/InputStream;
    //   106: astore_1
    //   107: aload_1
    //   108: astore 6
    //   110: aload 5
    //   112: astore_3
    //   113: aload_1
    //   114: astore_2
    //   115: aload_1
    //   116: astore 4
    //   118: new 84	java/io/BufferedReader
    //   121: dup
    //   122: new 86	java/io/InputStreamReader
    //   125: dup
    //   126: aload_1
    //   127: ldc 28
    //   129: invokespecial 89	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;Ljava/lang/String;)V
    //   132: invokespecial 92	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   135: astore 5
    //   137: new 94	java/lang/StringBuilder
    //   140: dup
    //   141: invokespecial 95	java/lang/StringBuilder:<init>	()V
    //   144: astore_2
    //   145: aload 5
    //   147: invokevirtual 98	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   150: astore_3
    //   151: aload_3
    //   152: ifnull +47 -> 199
    //   155: aload_2
    //   156: aload_3
    //   157: invokevirtual 102	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   160: pop
    //   161: goto -16 -> 145
    //   164: astore_2
    //   165: aload 5
    //   167: astore_3
    //   168: aload_1
    //   169: astore_2
    //   170: ldc 34
    //   172: ldc 104
    //   174: invokestatic 110	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   177: pop
    //   178: aload 5
    //   180: ifnull +8 -> 188
    //   183: aload 5
    //   185: invokevirtual 113	java/io/BufferedReader:close	()V
    //   188: aload_1
    //   189: ifnull +7 -> 196
    //   192: aload_1
    //   193: invokevirtual 116	java/io/InputStream:close	()V
    //   196: ldc 74
    //   198: areturn
    //   199: aload_2
    //   200: invokevirtual 119	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   203: astore_2
    //   204: aload 5
    //   206: ifnull +8 -> 214
    //   209: aload 5
    //   211: invokevirtual 113	java/io/BufferedReader:close	()V
    //   214: aload_1
    //   215: ifnull +7 -> 222
    //   218: aload_1
    //   219: invokevirtual 116	java/io/InputStream:close	()V
    //   222: aload_2
    //   223: areturn
    //   224: astore 5
    //   226: aload 6
    //   228: astore_1
    //   229: aload 7
    //   231: astore 4
    //   233: aload 4
    //   235: astore_3
    //   236: aload_1
    //   237: astore_2
    //   238: aload 5
    //   240: invokevirtual 122	java/lang/Exception:printStackTrace	()V
    //   243: aload 4
    //   245: ifnull +8 -> 253
    //   248: aload 4
    //   250: invokevirtual 113	java/io/BufferedReader:close	()V
    //   253: aload_1
    //   254: ifnull +7 -> 261
    //   257: aload_1
    //   258: invokevirtual 116	java/io/InputStream:close	()V
    //   261: ldc 74
    //   263: areturn
    //   264: astore_1
    //   265: aload_2
    //   266: astore_1
    //   267: aload_3
    //   268: ifnull +7 -> 275
    //   271: aload_3
    //   272: invokevirtual 113	java/io/BufferedReader:close	()V
    //   275: aload_1
    //   276: ifnull +7 -> 283
    //   279: aload_1
    //   280: invokevirtual 116	java/io/InputStream:close	()V
    //   283: ldc 74
    //   285: areturn
    //   286: astore_1
    //   287: goto -234 -> 53
    //   290: astore_1
    //   291: goto -226 -> 65
    //   294: astore_3
    //   295: goto -81 -> 214
    //   298: astore_1
    //   299: goto -77 -> 222
    //   302: astore_2
    //   303: goto -115 -> 188
    //   306: astore_1
    //   307: goto -111 -> 196
    //   310: astore_2
    //   311: goto -58 -> 253
    //   314: astore_1
    //   315: goto -54 -> 261
    //   318: astore_2
    //   319: goto -44 -> 275
    //   322: astore_1
    //   323: goto -40 -> 283
    //   326: astore_2
    //   327: aload 5
    //   329: astore_3
    //   330: goto -63 -> 267
    //   333: astore_2
    //   334: aload 5
    //   336: astore 4
    //   338: aload_2
    //   339: astore 5
    //   341: goto -108 -> 233
    //   344: astore_1
    //   345: aload 8
    //   347: astore 5
    //   349: aload 4
    //   351: astore_1
    //   352: goto -187 -> 165
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	355	0	this	BaseConnection
    //   1	257	1	localObject1	Object
    //   264	1	1	localObject2	Object
    //   266	14	1	localObject3	Object
    //   286	1	1	localIOException1	IOException
    //   290	1	1	localIOException2	IOException
    //   298	1	1	localIOException3	IOException
    //   306	1	1	localIOException4	IOException
    //   314	1	1	localIOException5	IOException
    //   322	1	1	localIOException6	IOException
    //   344	1	1	localSSLHandshakeException1	javax.net.ssl.SSLHandshakeException
    //   351	1	1	localObject4	Object
    //   25	131	2	localObject5	Object
    //   164	1	2	localSSLHandshakeException2	javax.net.ssl.SSLHandshakeException
    //   169	97	2	localObject6	Object
    //   302	1	2	localIOException7	IOException
    //   310	1	2	localIOException8	IOException
    //   318	1	2	localIOException9	IOException
    //   326	1	2	localObject7	Object
    //   333	6	2	localException1	Exception
    //   23	249	3	localObject8	Object
    //   294	1	3	localIOException10	IOException
    //   329	1	3	localException2	Exception
    //   28	322	4	localObject9	Object
    //   12	198	5	localBufferedReader	java.io.BufferedReader
    //   224	111	5	localException3	Exception
    //   339	9	5	localObject10	Object
    //   19	208	6	localObject11	Object
    //   9	221	7	localObject12	Object
    //   15	331	8	localObject13	Object
    //   6	83	9	localObject14	Object
    //   3	95	10	localObject15	Object
    //   34	68	11	localHttpURLConnection	HttpURLConnection
    // Exception table:
    //   from	to	target	type
    //   137	145	164	javax/net/ssl/SSLHandshakeException
    //   145	151	164	javax/net/ssl/SSLHandshakeException
    //   155	161	164	javax/net/ssl/SSLHandshakeException
    //   199	204	164	javax/net/ssl/SSLHandshakeException
    //   30	36	224	java/lang/Exception
    //   81	88	224	java/lang/Exception
    //   101	107	224	java/lang/Exception
    //   118	137	224	java/lang/Exception
    //   30	36	264	finally
    //   81	88	264	finally
    //   101	107	264	finally
    //   118	137	264	finally
    //   170	178	264	finally
    //   238	243	264	finally
    //   45	53	286	java/io/IOException
    //   57	65	290	java/io/IOException
    //   209	214	294	java/io/IOException
    //   218	222	298	java/io/IOException
    //   183	188	302	java/io/IOException
    //   192	196	306	java/io/IOException
    //   248	253	310	java/io/IOException
    //   257	261	314	java/io/IOException
    //   271	275	318	java/io/IOException
    //   279	283	322	java/io/IOException
    //   137	145	326	finally
    //   145	151	326	finally
    //   155	161	326	finally
    //   199	204	326	finally
    //   137	145	333	java/lang/Exception
    //   145	151	333	java/lang/Exception
    //   155	161	333	java/lang/Exception
    //   199	204	333	java/lang/Exception
    //   30	36	344	javax/net/ssl/SSLHandshakeException
    //   81	88	344	javax/net/ssl/SSLHandshakeException
    //   101	107	344	javax/net/ssl/SSLHandshakeException
    //   118	137	344	javax/net/ssl/SSLHandshakeException
  }
  
  /* Error */
  protected String doPostRequest(byte[] paramArrayOfByte)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 8
    //   3: aconst_null
    //   4: astore 9
    //   6: aconst_null
    //   7: astore 10
    //   9: aconst_null
    //   10: astore 11
    //   12: aconst_null
    //   13: astore_2
    //   14: aconst_null
    //   15: astore 12
    //   17: aload 8
    //   19: astore 5
    //   21: aload 11
    //   23: astore_3
    //   24: aload 12
    //   26: astore 4
    //   28: aload 10
    //   30: astore 7
    //   32: aload_2
    //   33: astore 6
    //   35: aload_0
    //   36: invokevirtual 44	com/gumsis/api/request/BaseConnection:getURLConnection	()Ljava/net/HttpURLConnection;
    //   39: astore 13
    //   41: aload 13
    //   43: ifnonnull +66 -> 109
    //   46: iconst_0
    //   47: ifeq +11 -> 58
    //   50: new 71	java/lang/NullPointerException
    //   53: dup
    //   54: invokespecial 72	java/lang/NullPointerException:<init>	()V
    //   57: athrow
    //   58: iconst_0
    //   59: ifeq +11 -> 70
    //   62: new 71	java/lang/NullPointerException
    //   65: dup
    //   66: invokespecial 72	java/lang/NullPointerException:<init>	()V
    //   69: athrow
    //   70: iconst_0
    //   71: ifeq +11 -> 82
    //   74: new 71	java/lang/NullPointerException
    //   77: dup
    //   78: invokespecial 72	java/lang/NullPointerException:<init>	()V
    //   81: athrow
    //   82: ldc 74
    //   84: areturn
    //   85: astore_1
    //   86: aload_1
    //   87: invokevirtual 125	java/io/IOException:printStackTrace	()V
    //   90: goto -32 -> 58
    //   93: astore_1
    //   94: aload_1
    //   95: invokevirtual 125	java/io/IOException:printStackTrace	()V
    //   98: goto -28 -> 70
    //   101: astore_1
    //   102: aload_1
    //   103: invokevirtual 125	java/io/IOException:printStackTrace	()V
    //   106: ldc 74
    //   108: areturn
    //   109: aload 8
    //   111: astore 5
    //   113: aload 11
    //   115: astore_3
    //   116: aload 12
    //   118: astore 4
    //   120: aload 10
    //   122: astore 7
    //   124: aload_2
    //   125: astore 6
    //   127: aload 13
    //   129: ldc 16
    //   131: invokevirtual 78	java/net/HttpURLConnection:setRequestMethod	(Ljava/lang/String;)V
    //   134: aload 8
    //   136: astore 5
    //   138: aload 11
    //   140: astore_3
    //   141: aload 12
    //   143: astore 4
    //   145: aload 10
    //   147: astore 7
    //   149: aload_2
    //   150: astore 6
    //   152: aload 13
    //   154: ldc 22
    //   156: aload_1
    //   157: arraylength
    //   158: invokestatic 131	java/lang/String:valueOf	(I)Ljava/lang/String;
    //   161: invokevirtual 61	java/net/HttpURLConnection:setRequestProperty	(Ljava/lang/String;Ljava/lang/String;)V
    //   164: aload 8
    //   166: astore 5
    //   168: aload 11
    //   170: astore_3
    //   171: aload 12
    //   173: astore 4
    //   175: aload 10
    //   177: astore 7
    //   179: aload_2
    //   180: astore 6
    //   182: aload 13
    //   184: invokevirtual 135	java/net/HttpURLConnection:getOutputStream	()Ljava/io/OutputStream;
    //   187: astore_2
    //   188: aload 8
    //   190: astore 5
    //   192: aload 11
    //   194: astore_3
    //   195: aload_2
    //   196: astore 4
    //   198: aload 10
    //   200: astore 7
    //   202: aload_2
    //   203: astore 6
    //   205: aload_2
    //   206: aload_1
    //   207: invokevirtual 141	java/io/OutputStream:write	([B)V
    //   210: aload 8
    //   212: astore 5
    //   214: aload 11
    //   216: astore_3
    //   217: aload_2
    //   218: astore 4
    //   220: aload 10
    //   222: astore 7
    //   224: aload_2
    //   225: astore 6
    //   227: aload 13
    //   229: invokevirtual 145	java/net/HttpURLConnection:getResponseCode	()I
    //   232: sipush 200
    //   235: if_icmpne +204 -> 439
    //   238: aload 8
    //   240: astore 5
    //   242: aload 11
    //   244: astore_3
    //   245: aload_2
    //   246: astore 4
    //   248: aload 10
    //   250: astore 7
    //   252: aload_2
    //   253: astore 6
    //   255: aload 13
    //   257: invokevirtual 82	java/net/HttpURLConnection:getInputStream	()Ljava/io/InputStream;
    //   260: astore_1
    //   261: aload 8
    //   263: astore 5
    //   265: aload_1
    //   266: astore_3
    //   267: aload_2
    //   268: astore 4
    //   270: aload_1
    //   271: astore 7
    //   273: aload_2
    //   274: astore 6
    //   276: new 84	java/io/BufferedReader
    //   279: dup
    //   280: new 86	java/io/InputStreamReader
    //   283: dup
    //   284: aload_1
    //   285: ldc 28
    //   287: invokespecial 89	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;Ljava/lang/String;)V
    //   290: invokespecial 92	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   293: astore 8
    //   295: new 94	java/lang/StringBuilder
    //   298: dup
    //   299: invokespecial 95	java/lang/StringBuilder:<init>	()V
    //   302: astore_3
    //   303: aload 8
    //   305: invokevirtual 98	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   308: astore 4
    //   310: aload 4
    //   312: ifnull +70 -> 382
    //   315: aload_3
    //   316: aload 4
    //   318: invokevirtual 102	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   321: pop
    //   322: goto -19 -> 303
    //   325: astore 4
    //   327: aload 8
    //   329: astore_3
    //   330: aload_2
    //   331: astore 6
    //   333: aload 4
    //   335: astore 8
    //   337: aload_3
    //   338: astore_2
    //   339: aload_2
    //   340: astore 5
    //   342: aload_1
    //   343: astore_3
    //   344: aload 6
    //   346: astore 4
    //   348: aload 8
    //   350: invokevirtual 122	java/lang/Exception:printStackTrace	()V
    //   353: aload 6
    //   355: ifnull +8 -> 363
    //   358: aload 6
    //   360: invokevirtual 146	java/io/OutputStream:close	()V
    //   363: aload_2
    //   364: ifnull +7 -> 371
    //   367: aload_2
    //   368: invokevirtual 113	java/io/BufferedReader:close	()V
    //   371: aload_1
    //   372: ifnull +7 -> 379
    //   375: aload_1
    //   376: invokevirtual 116	java/io/InputStream:close	()V
    //   379: ldc 74
    //   381: areturn
    //   382: aload_3
    //   383: invokevirtual 119	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   386: astore_3
    //   387: aload_2
    //   388: ifnull +7 -> 395
    //   391: aload_2
    //   392: invokevirtual 146	java/io/OutputStream:close	()V
    //   395: aload 8
    //   397: ifnull +8 -> 405
    //   400: aload 8
    //   402: invokevirtual 113	java/io/BufferedReader:close	()V
    //   405: aload_1
    //   406: ifnull +7 -> 413
    //   409: aload_1
    //   410: invokevirtual 116	java/io/InputStream:close	()V
    //   413: aload_3
    //   414: areturn
    //   415: astore_2
    //   416: aload_2
    //   417: invokevirtual 125	java/io/IOException:printStackTrace	()V
    //   420: goto -25 -> 395
    //   423: astore_2
    //   424: aload_2
    //   425: invokevirtual 125	java/io/IOException:printStackTrace	()V
    //   428: goto -23 -> 405
    //   431: astore_1
    //   432: aload_1
    //   433: invokevirtual 125	java/io/IOException:printStackTrace	()V
    //   436: goto -23 -> 413
    //   439: aload_2
    //   440: ifnull +7 -> 447
    //   443: aload_2
    //   444: invokevirtual 146	java/io/OutputStream:close	()V
    //   447: iconst_0
    //   448: ifeq +11 -> 459
    //   451: new 71	java/lang/NullPointerException
    //   454: dup
    //   455: invokespecial 72	java/lang/NullPointerException:<init>	()V
    //   458: athrow
    //   459: iconst_0
    //   460: ifeq -81 -> 379
    //   463: new 71	java/lang/NullPointerException
    //   466: dup
    //   467: invokespecial 72	java/lang/NullPointerException:<init>	()V
    //   470: athrow
    //   471: astore_1
    //   472: aload_1
    //   473: invokevirtual 125	java/io/IOException:printStackTrace	()V
    //   476: goto -97 -> 379
    //   479: astore_1
    //   480: aload_1
    //   481: invokevirtual 125	java/io/IOException:printStackTrace	()V
    //   484: goto -37 -> 447
    //   487: astore_1
    //   488: aload_1
    //   489: invokevirtual 125	java/io/IOException:printStackTrace	()V
    //   492: goto -33 -> 459
    //   495: astore_3
    //   496: aload_3
    //   497: invokevirtual 125	java/io/IOException:printStackTrace	()V
    //   500: goto -137 -> 363
    //   503: astore_2
    //   504: aload_2
    //   505: invokevirtual 125	java/io/IOException:printStackTrace	()V
    //   508: goto -137 -> 371
    //   511: astore_1
    //   512: aload_1
    //   513: invokevirtual 125	java/io/IOException:printStackTrace	()V
    //   516: goto -137 -> 379
    //   519: astore_1
    //   520: aload 4
    //   522: astore_2
    //   523: aload_2
    //   524: ifnull +7 -> 531
    //   527: aload_2
    //   528: invokevirtual 146	java/io/OutputStream:close	()V
    //   531: aload 5
    //   533: ifnull +8 -> 541
    //   536: aload 5
    //   538: invokevirtual 113	java/io/BufferedReader:close	()V
    //   541: aload_3
    //   542: ifnull +7 -> 549
    //   545: aload_3
    //   546: invokevirtual 116	java/io/InputStream:close	()V
    //   549: aload_1
    //   550: athrow
    //   551: astore_2
    //   552: aload_2
    //   553: invokevirtual 125	java/io/IOException:printStackTrace	()V
    //   556: goto -25 -> 531
    //   559: astore_2
    //   560: aload_2
    //   561: invokevirtual 125	java/io/IOException:printStackTrace	()V
    //   564: goto -23 -> 541
    //   567: astore_2
    //   568: aload_2
    //   569: invokevirtual 125	java/io/IOException:printStackTrace	()V
    //   572: goto -23 -> 549
    //   575: astore 4
    //   577: aload 8
    //   579: astore 5
    //   581: aload_1
    //   582: astore_3
    //   583: aload 4
    //   585: astore_1
    //   586: goto -63 -> 523
    //   589: astore 8
    //   591: aload 9
    //   593: astore_2
    //   594: aload 7
    //   596: astore_1
    //   597: goto -258 -> 339
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	600	0	this	BaseConnection
    //   0	600	1	paramArrayOfByte	byte[]
    //   13	379	2	localObject1	Object
    //   415	2	2	localIOException1	IOException
    //   423	21	2	localIOException2	IOException
    //   503	2	2	localIOException3	IOException
    //   522	6	2	localObject2	Object
    //   551	2	2	localIOException4	IOException
    //   559	2	2	localIOException5	IOException
    //   567	2	2	localIOException6	IOException
    //   593	1	2	localObject3	Object
    //   23	391	3	localObject4	Object
    //   495	51	3	localIOException7	IOException
    //   582	1	3	arrayOfByte	byte[]
    //   26	291	4	localObject5	Object
    //   325	9	4	localException1	Exception
    //   346	175	4	localObject6	Object
    //   575	9	4	localObject7	Object
    //   19	561	5	localObject8	Object
    //   33	326	6	localObject9	Object
    //   30	565	7	localObject10	Object
    //   1	577	8	localObject11	Object
    //   589	1	8	localException2	Exception
    //   4	588	9	localObject12	Object
    //   7	242	10	localObject13	Object
    //   10	233	11	localObject14	Object
    //   15	157	12	localObject15	Object
    //   39	217	13	localHttpURLConnection	HttpURLConnection
    // Exception table:
    //   from	to	target	type
    //   50	58	85	java/io/IOException
    //   62	70	93	java/io/IOException
    //   74	82	101	java/io/IOException
    //   295	303	325	java/lang/Exception
    //   303	310	325	java/lang/Exception
    //   315	322	325	java/lang/Exception
    //   382	387	325	java/lang/Exception
    //   391	395	415	java/io/IOException
    //   400	405	423	java/io/IOException
    //   409	413	431	java/io/IOException
    //   463	471	471	java/io/IOException
    //   443	447	479	java/io/IOException
    //   451	459	487	java/io/IOException
    //   358	363	495	java/io/IOException
    //   367	371	503	java/io/IOException
    //   375	379	511	java/io/IOException
    //   35	41	519	finally
    //   127	134	519	finally
    //   152	164	519	finally
    //   182	188	519	finally
    //   205	210	519	finally
    //   227	238	519	finally
    //   255	261	519	finally
    //   276	295	519	finally
    //   348	353	519	finally
    //   527	531	551	java/io/IOException
    //   536	541	559	java/io/IOException
    //   545	549	567	java/io/IOException
    //   295	303	575	finally
    //   303	310	575	finally
    //   315	322	575	finally
    //   382	387	575	finally
    //   35	41	589	java/lang/Exception
    //   127	134	589	java/lang/Exception
    //   152	164	589	java/lang/Exception
    //   182	188	589	java/lang/Exception
    //   205	210	589	java/lang/Exception
    //   227	238	589	java/lang/Exception
    //   255	261	589	java/lang/Exception
    //   276	295	589	java/lang/Exception
  }
  
  public String doRequest(HttpRequest paramHttpRequest)
  {
    if (getURLConnection() == null)
    {
      Log.e("REALTALK REQUEST", "URLConnection is null");
      return "";
    }
    setURLConnectionCommonPara();
    if (paramHttpRequest.data == null) {
      return doGetRequest();
    }
    return doPostRequest(paramHttpRequest.data);
  }
  
  public int getResponseCode()
  {
    if (getURLConnection() == null) {
      return -1;
    }
    try
    {
      int i = getURLConnection().getResponseCode();
      return i;
    }
    catch (IOException localIOException)
    {
      localIOException.printStackTrace();
    }
    return -1;
  }
  
  public String getResponseMessage()
  {
    if (getURLConnection() == null) {
      return "";
    }
    try
    {
      String str = getURLConnection().getResponseMessage();
      return str;
    }
    catch (IOException localIOException)
    {
      localIOException.printStackTrace();
    }
    return "";
  }
  
  protected abstract HttpURLConnection getURLConnection();
}


/* Location:              /home/cmd/Programmierung/Android/reverse-engineering/fakegumtree/FakeGumtree-dex2jar.jar!/com/gumsis/api/request/BaseConnection.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */