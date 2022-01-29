package conference.booking.commands;

import conference.booking.Model.Building;
import conference.booking.Model.Command;
import conference.booking.Model.Floor;
import conference.booking.Model.Room;
import conference.booking.Model.Slot;

import java.util.List;

public class ListCommandExecutor extends CommandExecutor {
  public static String COMMAND_NAME = "LIST";

  public ListCommandExecutor(List<Building> buildingList) {
    super(buildingList);
  }

  @Override
  public boolean validate(Command command) {
    final List<String> params = command.getParams();
    if (params.get(0).compareTo("BOOKING") != 0) {
      return false;
    }

    if (params.size() != 3) {
      return false;
    }

    Building building = isBuildingAvailable(params.get(1));

    if (building == null) {
      return false;
    }

    if (isFloorAvailable(building, params.get(2)) == null) {
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
    for (Room room : roomList) {
      List<Slot> slotList = room.getSlotList();
      for (Slot slot : slotList) {
        System.out.println(
            slot.getStartTime()
                + ":"
                + slot.getEndTime()
                + " "
                + floor.getName()
                + " "
                + building.getName()
                + " "
                + room.getName());
      }
    }
  }
}
