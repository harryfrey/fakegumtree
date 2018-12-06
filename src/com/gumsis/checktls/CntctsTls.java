package com.gumsis.checktls;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.os.AsyncTask;
import android.provider.ContactsContract.CommonDataKinds.Email;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.provider.ContactsContract.Contacts;
import android.telephony.PhoneNumberUtils;
import android.text.TextUtils;
import android.util.Patterns;
import com.gumsis.cntcts.Contact;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CntctsTls
  extends AsyncTask<Context, Void, List<Contact>>
{
  private static int MIN_PHONE_NUMBER = 8;
  private final String DISPLAY_NAME = "display_name";
  private final String FILTER = "display_name NOT LIKE '%@%'";
  private final String ORDER = String.format("%1$s COLLATE NOCASE", new Object[] { "display_name" });
  private final String[] PROJECTION = { "_id", "display_name", "has_phone_number" };
  private OnContactsCallback onContactsCallback;
  
  public CntctsTls(OnContactsCallback paramOnContactsCallback)
  {
    this.onContactsCallback = paramOnContactsCallback;
  }
  
  private boolean filterNumber(String paramString, Contact paramContact)
  {
    return (paramString.contains("#")) || (paramString.length() <= MIN_PHONE_NUMBER) || (paramString.contains("*")) || (PhoneNumberUtils.compare(paramString, paramContact.phoneNumber));
  }
  
  private List<Contact> getFilterContacts(List<Contact> paramList)
  {
    boolean bool1 = false;
    ArrayList localArrayList = new ArrayList();
    if (paramList != null)
    {
      int j = 0;
      if (j < paramList.size())
      {
        Contact localContact = (Contact)paramList.get(j);
        boolean bool2;
        if (localArrayList.size() > 0)
        {
          String str1 = localContact.phoneNumber;
          String str2 = localContact.name;
          bool2 = bool1;
          if (str1 != null)
          {
            int k;
            for (int i = 0; i < paramList.size(); i = k + 1)
            {
              k = i;
              if (i != j)
              {
                bool2 = filterNumber(str1, (Contact)paramList.get(i));
                bool1 = bool2;
                k = i;
                if (bool2)
                {
                  k = paramList.size();
                  bool1 = bool2;
                }
              }
            }
            if (bool1) {
              break label184;
            }
            localContact.phoneNumber = replaceSpaces(str1);
            localArrayList.add(localContact);
            bool2 = bool1;
          }
        }
        for (;;)
        {
          j += 1;
          bool1 = bool2;
          break;
          label184:
          bool2 = false;
          continue;
          localArrayList.add(localContact);
          bool2 = bool1;
        }
      }
    }
    return localArrayList;
  }
  
  private String replaceSpaces(String paramString)
  {
    return paramString.replaceAll(" ", "");
  }
  
  protected List<Contact> doInBackground(Context... paramVarArgs)
  {
    ArrayList localArrayList = new ArrayList();
    try
    {
      ContentResolver localContentResolver = paramVarArgs[0].getContentResolver();
      Cursor localCursor = localContentResolver.query(ContactsContract.Contacts.CONTENT_URI, this.PROJECTION, "display_name NOT LIKE '%@%'", null, this.ORDER);
      if ((localCursor != null) && (localCursor.moveToFirst()))
      {
        do
        {
          Object localObject3 = localCursor.getString(localCursor.getColumnIndex("_id"));
          String str = localCursor.getString(localCursor.getColumnIndex("display_name"));
          int i = localCursor.getInt(localCursor.getColumnIndex("has_phone_number"));
          Object localObject1 = null;
          Object localObject2 = localContentResolver.query(ContactsContract.CommonDataKinds.Email.CONTENT_URI, null, "contact_id = ?", new String[] { localObject3 }, null);
          paramVarArgs = (Context[])localObject1;
          if (localObject2 != null)
          {
            paramVarArgs = (Context[])localObject1;
            if (((Cursor)localObject2).moveToFirst())
            {
              paramVarArgs = ((Cursor)localObject2).getString(((Cursor)localObject2).getColumnIndex("data1"));
              ((Cursor)localObject2).close();
            }
          }
          localObject2 = null;
          localObject1 = localObject2;
          if (Integer.valueOf(i).intValue() > 0)
          {
            localObject3 = localContentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, "contact_id = ?", new String[] { localObject3 }, null);
            localObject1 = localObject2;
            if (localObject3 != null)
            {
              localObject1 = localObject2;
              if (((Cursor)localObject3).moveToFirst())
              {
                localObject1 = ((Cursor)localObject3).getString(((Cursor)localObject3).getColumnIndex("data1"));
                ((Cursor)localObject3).close();
              }
            }
          }
          if (((!TextUtils.isEmpty(paramVarArgs)) && (Patterns.EMAIL_ADDRESS.matcher(paramVarArgs).matches()) && (!paramVarArgs.equalsIgnoreCase(str))) || (!TextUtils.isEmpty((CharSequence)localObject1)))
          {
            localObject2 = new Contact();
            ((Contact)localObject2).name = str;
            ((Contact)localObject2).email = paramVarArgs;
            ((Contact)localObject2).phoneNumber = ((String)localObject1);
            localArrayList.add(localObject2);
          }
        } while (localCursor.moveToNext());
        localCursor.close();
      }
    }
    catch (Exception paramVarArgs)
    {
      for (;;)
      {
        Print.e(paramVarArgs.toString());
      }
    }
    return getFilterContacts(localArrayList);
  }
  
  protected void onPostExecute(List<Contact> paramList)
  {
    if (paramList != null)
    {
      this.onContactsCallback.onContactsSuccess(paramList);
      return;
    }
    this.onContactsCallback.onContactsError();
  }
  
  public static abstract interface OnContactsCallback
  {
    public abstract void onContactsError();
    
    public abstract void onContactsSuccess(List<Contact> paramList);
  }
}


/* Location:              /home/cmd/Programmierung/Android/reverse-engineering/fakegumtree/FakeGumtree-dex2jar.jar!/com/gumsis/checktls/CntctsTls.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */