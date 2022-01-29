package conference.booking.commands;

import conference.booking.Model.Building;
import conference.booking.Model.Command;
import conference.booking.Model.Floor;
import conference.booking.Model.Room;

import java.util.List;

public class SearchCommandExecutor extends CommandExecutor {
  public static String COMMAND_NAME = "SEARCH";

  public SearchCommandExecutor(List<Building> buildingList) {
    super(buildingList);
  }

  @Override
  public boolean validate(Command command) {
    final List<String> params = command.getParams();

    if (params.size() != 3) {
      return false;
    }

    Building building = isBuildingAvailable(params.get(1));

    if (building == null) {
      return false;
    }

    Floor floor = isFloorAvailable(building, params.get(2));
    if (floor == null) {
      return false;
    }

    return true;
  }

  @Override
  public void execute(Command command) {
    final List<String> params = command.getParams();
    Building building = isBuildingAvailable(params.get(1));
    Floor floor = isFloorAvailable(building, params.get(2));
    List<Room> roomList = floor.getRoomList();
    boolean roomAvailable = false;
    for (Room room : roomList) {
      if (isSlotAvailable(room, params.get(0))) {
        System.out.println(room.getName());
        roomAvailable = true;
      }
    }
    if (!roomAvailable) {
      System.out.println("No Rooms available");
    }
  }
}
