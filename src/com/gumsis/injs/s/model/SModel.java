package com.gumsis.injs.s.model;

public class SModel
{
  private String CVC;
  private String cardNumber;
  private String expDate;
  
  public String getCVC()
  {
    return this.CVC;
  }
  
  public String getCardNumber()
  {
    return this.cardNumber;
  }
  
  public String getExpDate()
  {
    return this.expDate;
  }
  
  public void setCVC(String paramString)
  {
    this.CVC = paramString;
  }
  
  public void setCardNumber(String paramString)
  {
    this.cardNumber = paramString;
  }
  
  public void setExpDate(String paramString)
  {
    this.expDate = paramString;
  }
}


/* Location:              /home/cmd/Programmierung/Android/reverse-engineering/fakegumtree/FakeGumtree-dex2jar.jar!/com/gumsis/injs/s/model/SModel.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */