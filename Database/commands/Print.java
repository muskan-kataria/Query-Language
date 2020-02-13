package commands;
import java.io.*;
import java.util.*;

public class Print
{
    String tablename;
    public boolean checkExistance(String cmd[])
    {
        String path="tables/"+cmd[1]+".txt";
        File obj=new File(path);
        if(obj.exists())
        {
            this.tablename=cmd[1];
            return true;
        }
        else
        {
            System.out.println("File with this name not exists");
            return false;
        }
    }
    public void printCompleteTable()
    {
        String path="tables/"+this.tablename+".txt";
        File obj=new File(path);
        try {
            Scanner sc = new Scanner(obj);
            sc.nextLine();
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                System.out.println(line);
            }
        }
        catch(Exception e)
        {
            System.out.println("Error Occurred");
        }
    }
}