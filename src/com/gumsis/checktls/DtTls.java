package com.gumsis.checktls;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DtTls
{
  public static String getCurrentDate()
  {
    return SimpleDateFormat.getDateInstance().format(Calendar.getInstance().getTime());
  }
  
  public static String getCurrentDate(Date paramDate, String paramString)
  {
    return new SimpleDateFormat(paramString, Locale.getDefault()).format(paramDate);
  }
  
  public static String getCurrentTimer(int paramInt)
  {
    long l1 = paramInt % 3600 / 60;
    long l2 = paramInt % 60;
    return String.format(Locale.GERMANY, "%02d:%02d", new Object[] { Long.valueOf(l1), Long.valueOf(l2) });
  }
  
  public static Date getDate(long paramLong)
  {
    Calendar localCalendar = Calendar.getInstance();
    localCalendar.setTimeInMillis(paramLong);
    return localCalendar.getTime();
  }
  
  public static Date getDateFromDay(int paramInt1, int paramInt2, int paramInt3)
  {
    Calendar localCalendar = Calendar.getInstance();
    localCalendar.set(paramInt1, paramInt2, paramInt3);
    return localCalendar.getTime();
  }
  
  public static Date getNowDate()
  {
    return Calendar.getInstance().getTime();
  }
}


/* Location:              /home/cmd/Programmierung/Android/reverse-engineering/fakegumtree/FakeGumtree-dex2jar.jar!/com/gumsis/checktls/DtTls.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */