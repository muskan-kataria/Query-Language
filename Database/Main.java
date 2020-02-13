import java.util.*;
import commands.*;

class Query {
	String cmd;

	Query(String cmd) {
		this.cmd = cmd;
	}


	public void convertStringIntoQuery() {
		int length;
		length = this.cmd.length();

		if (this.cmd.charAt(length - 1) == ';') {
			this.cmd = this.cmd.substring(0, length - 1);
		} else
			this.cmd.trim();
        this.cmd=this.cmd.replace("("," (");
		this.cmd = this.cmd.replaceAll("\\s{2,}", " ");
		this.cmd = this.cmd.toLowerCase();


	}


	public void validation() {
		//options

		String options[] = {"create", "insert", "select", "print", "drop", "delete"};

		String command[] = this.cmd.split(" ");

		switch (command[0]) {
			case "create": {
				Create obj = new Create();
				boolean result = obj.validate(command);
				if (!result)
					System.out.println("Error");
				else {
					result = obj.validTableName();
					if (result) {
						boolean exist = obj.checkFileExistance();
						if (exist) {
							System.out.println("table name already exists");
						} else {
							result = obj.createTable();
							if (result) {
								result = obj.addColumns();
								if (result)
									System.out.println("Table Created.");
							}
						}
					} else {
						System.out.println("please enter valid table name or do not use keywords as a table name");
					}
				}

				break;
			}

			case "insert": {
				Insert obj=new Insert();
				if(obj.validate(command))
				{
					if(obj.checkValuesCount())
					{
						if(obj.insertValues())
						{
							System.out.println("values Successfully inserted");
						}
					}
				}
				break;
			}

			case "select": {
				Select obj=new Select();
				if(obj.validate(command))
				{
					if(obj.checkTableExistance()) {
						obj.runSelectQuery(command);
					}

				}
				break;
			}

			case "print": {
			    Print obj=new Print();
			    if(command.length==2) {
                    if (obj.checkExistance(command)) {
                        obj.printCompleteTable();
                    }
                }
			    else
			        System.out.println("Invalid Command");
                break;
            }

			case "drop": {
				Drop obj = new Drop();
				if (obj.validate(command)) {
					if (obj.dropTable()) {
						System.out.println("table successfully dropped");
					} else {

						System.out.println("Error Occurred");
					}
				} else {
					System.out.println("Invalid command");
				}
				break;
			}


			default:
				System.out.println("Invalid command");


		}


	}

}



class Main
{
public static void main(String args[])
{
Scanner sc=new Scanner(System.in);

String input;

System.out.println("Enter your Query");

input=sc.nextLine();

Query  query=new Query(input);
	
	
	
	
	query.convertStringIntoQuery();
	
	query.validation();
	



}
}