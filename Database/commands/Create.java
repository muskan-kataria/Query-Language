package commands;
import java.io.*;
import java.util.regex.*;

public class Create
{
String syntax1[]={"create","table","table_name","(column_name)"};
String keywords[]={"create","delete","table","print","drop","select","as","alter","update","where","into","from","load","store","exit","quit"};
String tablename;
String columns[];
    Pattern pattern = Pattern.compile("[a-z]*");
public boolean validate(String cmd[])
{

    if(cmd[1].equals(syntax1[1]))
    {
        this.tablename=cmd[2];

        if(cmd[3].charAt(0)=='(')
        {
            if(cmd[cmd.length-1].charAt(cmd[cmd.length-1].length()-1)==')')
            {
String cols=cmd[3];
for(int i=4;i<cmd.length;i++)
{
    cols+=cmd[i];
}
               cols=cols.trim().replaceAll("\\s{1,}","");
                cols=cols.replace("(","");
                cols=cols.replace(")","");

                this.columns=cols.split(",");

            }
            else
            {
                System.out.println("missing keyword )");
                return false;
            }
        }
        else
        {
            System.out.println("missing keyword (");
            return false;
        }

    }

    else
    {
        System.out.println("missing keyword syntax");
        return false;
    }
    return true;
}

public boolean validTableName()
{
    for(int i=0;i<keywords.length;i++)
    {
        if(this.tablename.equals(keywords[i]))
            return false;
    }

    Matcher matcher = pattern.matcher(this.tablename);
    if (!matcher.matches()) {
        System.out.println("Tablename cannot contain special characters");
        return false;
    }

    return true;
}

public boolean checkFileExistance()
{
    String path="tables/"+this.tablename+".txt";
    File temp=new File(path);
    boolean exists=temp.exists();
    return exists;
}

public boolean createTable()
{
    String path="tables/"+this.tablename+".txt";
    File obj=new File(path);

    try {

        if (obj.createNewFile()) {
            return true;
        }
        else
        {
            System.out.println("File already exists");
            return false;
        }

    }
    catch(IOException e)
    {
        System.out.println("error occurred");
        return false;
    }
}
public boolean addColumns()
{
    try {
        String path="tables/"+this.tablename+".txt";
        String rowOne="";
        for(int i=0;i<this.columns.length-1;i++)
        {
            rowOne+=columns[i]+",";
        }
        rowOne+=this.columns[columns.length-1];

        FileWriter myWriter = new FileWriter(path);
        myWriter.write(rowOne);
        myWriter.close();
        BufferedWriter writer=new BufferedWriter(new FileWriter(path,true));
        writer.write("\n");
        for(int i=0;i<this.columns.length;i++)
        {
            String text;
            text="|"+columns[i];
            for(int j=columns[i].length()+1;j<19;j++)
                text+=" ";
            writer.write(text);
        }
        writer.write("|");
        writer.close();

        return true;
    } catch (IOException e) {
        System.out.println("An error occurred.");
        return false;
    }
}
}

