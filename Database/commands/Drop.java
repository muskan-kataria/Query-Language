package commands;
import java.io.*;


public class Drop
{
    String syntax[]={"drop","table","table_name"};
String tablename;

    public boolean checkFileExistance()
    {
        String path="tables/"+this.tablename+".txt";
        File temp=new File(path);
        boolean exists=temp.exists();
        return exists;
    }
public boolean validate(String cmd[])
{
if(cmd.length==3)
{
if(cmd[1].equals(syntax[1]))
{
    this.tablename=cmd[2];

    if(checkFileExistance())
        return true;
    else
    {
        System.out.println("table with this name not exist.");
        return false;
    }
}
else
    return false;
}
return false;
}

public boolean dropTable()
{
   String path="tables/"+this.tablename+".txt";
    File temp=new File(path);
    boolean result=temp.delete();
    return result;
}
}