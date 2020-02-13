package commands;
import java.util.*;
import java.io.*;

public class Select
{
    String tablename;

    public boolean validate(String cmd[])
    {
        String syntax1[]={"select","*","from","table_name"};
        String syntax2[]={"select","col1,col2,","from","table_name"};

        if(cmd.length==4)
        {
         if(cmd[2].equals(syntax1[2]))
         {
             this.tablename=cmd[3];
             return true;
         }
         else
         {
             System.out.println("Missing Keyword");
             return false;
         }
        }
        else
        {
            System.out.println("incomplete command");
            return false;
        }

    }

    public boolean checkTableExistance()
    {
        String path="tables/"+this.tablename+".txt";
        File obj=new File(path);
        if(obj.exists())
        {
            return true;
        }
        else
        {
            System.out.println("Table not exists");
            return false;
        }
    }

    public void runSelectQuery(String cmd[])
    {
        String syntax1[]={"select","*","from","table_name"};
        String syntax2[]={"select","col1,col2,","from","table_name"};

        if(cmd[1].equals(syntax1[1]))
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

        else
        {
            String cols[]=cmd[1].split(",");
            int indexes[]=new int[cols.length];
            String path="tables/"+this.tablename+".txt";
            File obj=new File(path);
            try {
                Scanner sc = new Scanner(obj);

                String actualCols[] = sc.nextLine().split(",");

                List<String> list = Arrays.asList(actualCols);


                for(int i=0;i<cols.length;i++)
                {
                    indexes[i]=list.indexOf(cols[i]);
                }

                while(sc.hasNextLine())
                { String line=sc.nextLine();
                line=line.replaceAll(" ","");
                line=line.substring(1,line.length());

                    String data[]=line.split("|");

                    List<String> myList = new ArrayList<String>(Arrays.asList(data));

                    for(int i=0;i<data.length;i++)
                    myList.remove("|");
                    data = myList.toArray(new String[0]);


                    String out="";

                    for(int i=0;i<indexes.length;i++)
                    {
                        out+="|"+data[indexes[i]];

                    }
                    out+="|";

                    String output=out.toString();
                   System.out.println(output);
                }

            }
            catch(Exception e)
            {
                System.out.println("Error Occurred");
            }
        }
    }
}