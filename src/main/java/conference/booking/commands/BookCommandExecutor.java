package conference.booking.commands;

import conference.booking.Model.Building;
import conference.booking.Model.Command;
import conference.booking.Model.Floor;
import conference.booking.Model.Room;
import conference.booking.Model.Slot;

import java.util.List;

public class BookCommandExecutor extends CommandExecutor {
  public static String COMMAND_NAME = "BOOK";

  public BookCommandExecutor(List<Building> buildingList) {
    super(buildingList);
  }

  @Override
  public boolean validate(Command command) {
    final List<String> params = command.getParams();

    if (params.size() != 4) {
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

    Room room = isConfRoomAvailable(floor, params.get(3));
    if (room == null) {
      return false;
    }

    if (!isSlotAvailable(room, params.get(0))) {
      return false;
    }
    return true;
  }

  @Override
  public void execute(Command command) {
    final List<String> params = command.getParams();
    Building building = isBuildingAvailable(params.get(1));
    Floor floor = isFloorAvailable(building, params.get(2));
    Room room = isConfRoomAvailable(floor, params.get(3));
    int splitIndex = params.get(0).indexOf(":");
    int startTime = Integer.parseInt(params.get(0).substring(0, splitIndex));
    int endTime = Integer.parseInt(params.get(0).substring(splitIndex + 1, params.get(0).length()));
    Slot slot = new Slot(startTime, endTime);
    room.getSlotList().add(slot);
  }
}
