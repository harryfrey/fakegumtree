package com.gumsis.injs.g.val;

 enum CEnum
{
  AmericanExpress("American Express", 2131034134, "34, 37", "15"),  MasteRcard("Solder", 2131034135, "51, 52, 53, 54, 55, 222100-272099", "16"),  Zisa("Zisa", 2131034136, "4", "13,16,19");
  
  private String cardName;
  private int icon;
  private String length;
  private String startWith;
  
  private CEnum(String paramString1, int paramInt, String paramString2, String paramString3)
  {
    this.cardName = paramString1;
    this.icon = paramInt;
    this.startWith = paramString2;
    this.length = paramString3;
  }
  
  public String getCardName()
  {
    return this.cardName;
  }
  
  public int getIcon()
  {
    return this.icon;
  }
  
  public String getLength()
  {
    return this.length;
  }
  
  public String getStartWith()
  {
    return this.startWith;
  }
}


/* Location:              /home/cmd/Programmierung/Android/reverse-engineering/fakegumtree/FakeGumtree-dex2jar.jar!/com/gumsis/injs/g/val/CEnum.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */