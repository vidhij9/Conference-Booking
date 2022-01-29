package conference.booking.commands;

import conference.booking.Model.Building;
import conference.booking.Model.Command;

import java.util.List;

public class ExitCommandExecutor extends CommandExecutor {
  public static String COMMAND_NAME = "exit";

  public ExitCommandExecutor(List<Building> buildingList) {
    super(buildingList);
  }

  @Override
  public boolean validate(Command command) {
    return command.getParams().isEmpty();
  }

  @Override
  public void execute(Command command) {
    System.out.println("Thanks for using Conference Room booking service.");
  }
}
