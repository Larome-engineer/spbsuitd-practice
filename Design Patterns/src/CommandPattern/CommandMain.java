package Command;

import CommandPattern.Database;
import CommandPattern.DeleteCommand;
import CommandPattern.Developer;
import CommandPattern.InsertCommand;
import CommandPattern.SelectCommand;
import CommandPattern.UpdateCommand;

public class CommandMain {
    public static void main(String[] args) {
        Database database = new Database();

        Developer developer = new Developer(
            new InsertCommand(database), new UpdateCommand(database), 
                new SelectCommand(database), new DeleteCommand(database)
        );

        developer.insertRecord();
        developer.updateRecord();
        developer.selectRecord();
        developer.deleteRecord();
    }
}
