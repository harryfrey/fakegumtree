package com.gumsis.injs.g.val.models;

import java.util.ArrayList;

public final class Card
{
  private String cardName;
  private int drawable;
  private ArrayList<Integer> possibleLengths;
  
  public Card(String paramString, ArrayList<Integer> paramArrayList, int paramInt)
  {
    this.cardName = paramString;
    this.possibleLengths = paramArrayList;
    this.drawable = paramInt;
  }
  
  public String getCardName()
  {
    return this.cardName;
  }
  
  public int getDrawable()
  {
    return this.drawable;
  }
  
  public int getMaxLength()
  {
    return ((Integer)this.possibleLengths.get(this.possibleLengths.size() - 1)).intValue();
  }
  
  public ArrayList<Integer> getPossibleLengths()
  {
    return this.possibleLengths;
  }
}


/* Location:              /home/cmd/Programmierung/Android/reverse-engineering/fakegumtree/FakeGumtree-dex2jar.jar!/com/gumsis/injs/g/val/models/Card.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */