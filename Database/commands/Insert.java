package commands;
import java.io.*;
import java.util.Scanner;

public class Insert
{
    String tablename;
    String values[];
    String syntax[]={"insert","into","table_name","values","( , )"};


    public boolean checkFileExistance()
    {
        String path="tables/"+this.tablename+".txt";
        File temp=new File(path);
        boolean exists=temp.exists();
        return exists;
    }

    public boolean validate(String cmd[])
    {
        if(cmd[1].equals(syntax[1]))
        {
           this.tablename=cmd[2];
           if(checkFileExistance())
           {
               if(cmd[3].equals(syntax[3]))
               {
                   if(cmd[4].charAt(0)=='('&& cmd[cmd.length-1].charAt(cmd[cmd.length-1].length()-1)==')')
                   {
                       String val=cmd[4];
                       for(int i=5;i<cmd.length;i++)
                       {
                           val+=cmd[i];
                       }
                       val=val.trim().replaceAll("\\s{1,}","");
                       val=val.replace("(","");
                       val=val.replace(")","");

                       this.values=val.split(",");
                       return true;

                   }
                   else
                   {
                       System.out.println("missing parenthesis");
                       return false;
                   }
               }
               else
               {
                   System.out.println("missing keyword");
                   return false;
               }
           }
           else
           {
               System.out.println("table name does not exists.");
               return false;
           }
        }
        else
        {
            System.out.println("missing keyword");
            return false;
        }
    }

    public boolean checkValuesCount()
    {
        String path="tables/"+this.tablename+".txt";
        File temp=new File(path);
try{
        Scanner myReader = new Scanner(temp);
    int count=0;
    myReader.nextLine();
        while (myReader.hasNextLine()) {
            String data = myReader.nextLine();

            for(int i=0;i<data.length();i++)
            {
                if(data.charAt(i)=='|')
                    count++;
            }
            count--;
            break;
        }
        myReader.close();
        if(count==this.values.length)
        return true;
        else if(count>this.values.length)
    {
       System.out.println("less values");
       return false;
    }
        else
        {
            System.out.println("More values");
            return false;
        }

    } catch (FileNotFoundException e) {
    System.out.println("An error occurred.");
    return false;
}

}

public boolean insertValues()
{
    String data="\n";
    for(int i=0;i<this.values.length;i++)
    {
      data+="|"+values[i];
      for(int j=values[i].length();j<18;j++)
          data+=" ";

    }
    data+="|";

    try {
        String path="tables/"+this.tablename+".txt";
        // Open given file in append mode.
        BufferedWriter out = new BufferedWriter(
                new FileWriter(path, true));

        out.write(data);
        out.close();
        return true;
    }
    catch (IOException e) {
        System.out.println("exception occoured" + e);
        return false;
    }

}
}