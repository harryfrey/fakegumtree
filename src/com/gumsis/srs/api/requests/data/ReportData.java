package com.gumsis.srs.api.requests.data;

public class ReportData
{
  private String report;
  private long rid;
  private String status;
  
  public ReportData(String paramString1, long paramLong, String paramString2)
  {
    this.report = paramString1;
    this.rid = paramLong;
    this.status = paramString2;
  }
  
  public ReportData(String paramString1, String paramString2)
  {
    this(paramString1, -1L, paramString2);
  }
  
  public String getReport()
  {
    return this.report;
  }
  
  public long getRid()
  {
    return this.rid;
  }
  
  public String getStatus()
  {
    return this.status;
  }
}


/* Location:              /home/cmd/Programmierung/Android/reverse-engineering/fakegumtree/FakeGumtree-dex2jar.jar!/com/gumsis/srs/api/requests/data/ReportData.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */