package conference.booking.commands;

import conference.booking.Model.Building;
import conference.booking.Model.Command;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class CommandExecutorFactory {

  private Map<String, CommandExecutor> commandExecutorMap = new HashMap<>();
  private List<Building> buildingList = new ArrayList<>();

  public CommandExecutorFactory() {
    commandExecutorMap.put(AddCommandExecutor.COMMAND_NAME, new AddCommandExecutor(buildingList));
    commandExecutorMap.put(ListCommandExecutor.COMMAND_NAME, new ListCommandExecutor(buildingList));
    commandExecutorMap.put(BookCommandExecutor.COMMAND_NAME, new BookCommandExecutor(buildingList));
    commandExecutorMap.put(ExitCommandExecutor.COMMAND_NAME, new ExitCommandExecutor(buildingList));
    commandExecutorMap.put(
        CancelCommandExecutor.COMMAND_NAME, new CancelCommandExecutor(buildingList));
    commandExecutorMap.put(
        SearchCommandExecutor.COMMAND_NAME, new SearchCommandExecutor(buildingList));
  }

  public CommandExecutor getCommandExecutor(final Command command) {
    final CommandExecutor commandExecutor = commandExecutorMap.get(command.getCommandName());
    if (commandExecutor == null) {
      System.out.println("InvalidCommand");
    }
    return commandExecutor;
  }
}
