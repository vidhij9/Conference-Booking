package conference.booking.commands;

import conference.booking.Model.Building;
import conference.booking.Model.Command;
import conference.booking.Model.Floor;
import conference.booking.Model.Room;

import java.util.ArrayList;
import java.util.List;

public class AddCommandExecutor extends CommandExecutor {
  public static String COMMAND_NAME = "ADD";

  public AddCommandExecutor(List<Building> buildingList) {
    super(buildingList);
  }

  @Override
  public boolean validate(Command command) {
    final List<String> params = command.getParams();
    if (params.get(0).compareTo("BUILDING") == 0 && params.size() == 2) {
      return true;
    }
    if (params.get(0).compareTo("FLOOR") == 0 && params.size() == 3) {
      return true;
    }
    if (params.get(0).compareTo("CONFROOM") == 0 && params.size() == 4) {
      return true;
    }
    return false;
  }

  @Override
  public void execute(Command command) {
    final List<String> params = command.getParams();
    if (params.get(0).compareTo("BUILDING") == 0) {
      Building building = isBuildingAvailable(params.get(0));
      if (building == null) {
        Building newBuilding = new Building(params.get(1), new ArrayList<>());
        buildingList.add(newBuilding);
        System.out.println("Added building " + newBuilding.getName() + " into the system.");
      } else {
        System.out.println("InvalidCommand");
      }
    }
    if (params.get(0).compareTo("FLOOR") == 0) {
      Building building = isBuildingAvailable(params.get(1));
      if (building != null) {
        Floor floor = isFloorAvailable(building, params.get(2));
        if (floor != null) {
          System.out.println("InvalidCommand");
        }
        Floor newFloor = new Floor(params.get(2), new ArrayList<>());
        building.getFloorList().add(newFloor);
        System.out.println("Added floor " + newFloor.getName() + " into the system.");
      } else {
        System.out.println("InvalidCommand");
      }
    }
    if (params.get(0).compareTo("CONFROOM") == 0) {
      Building building = isBuildingAvailable(params.get(1));
      if (building == null) {
        System.out.println("InvalidCommand");
      }
      Floor floor = isFloorAvailable(building, params.get(2));
      if (floor == null) {
        System.out.println("InvalidCommand");
      }
      Room room = isConfRoomAvailable(floor, params.get(3));
      if (room != null) {
        System.out.println("InvalidCommand");
      }
      Room newRoom = new Room(params.get(3), new ArrayList<>());
      floor.getRoomList().add(newRoom);
      System.out.println("Added room " + newRoom.getName() + " into the system.");
    }
  }
}
