package conference.booking.commands;

import conference.booking.Model.Building;
import conference.booking.Model.Command;
import conference.booking.Model.Floor;
import conference.booking.Model.Room;
import conference.booking.Model.Slot;

import java.util.ArrayList;
import java.util.List;

public abstract class CommandExecutor {
  List<Building> buildingList = new ArrayList<>();

  public CommandExecutor(List<Building> buildingList) {
    this.buildingList = buildingList;
  }

  public abstract boolean validate(Command command);

  public abstract void execute(Command command);

  public Building isBuildingAvailable(String buildingName) {
    for (Building building : buildingList) {
      if (building.getName().compareTo(buildingName) == 0) {
        return building;
      }
    }
    return null;
  }

  public Floor isFloorAvailable(Building building, String floorName) {
    if (building.getFloorList() == null) {
      return null;
    }
    for (Floor floor : building.getFloorList()) {
      if (floor.getName().compareTo(floorName) == 0) {
        return floor;
      }
    }
    return null;
  }

  public Room isConfRoomAvailable(Floor floor, String confRoomName) {
    for (Room room : floor.getRoomList()) {
      if (room.getName().compareTo(confRoomName) == 0) {
        return room;
      }
    }
    return null;
  }

  public boolean isSlotAvailable(Room room, String slot) {
    int splitIndex = slot.indexOf(":");
    int startTime = Integer.parseInt(slot.substring(0, splitIndex));
    int endTime = Integer.parseInt(slot.substring(splitIndex + 1, slot.length()));
    List<Slot> slotList = room.getSlotList();
    for (Slot slotCheck : slotList) {
      if (startTime >= slotCheck.getStartTime() && startTime < slotCheck.getEndTime()) {
        return false;
      }
      if (endTime > slotCheck.getStartTime() && endTime <= slotCheck.getEndTime()) {
        return false;
      }
    }
    return true;
  }
}
