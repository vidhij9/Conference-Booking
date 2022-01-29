package conference.booking.Model;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class Command {

  private static final String SPACE = " ";
  private String commandName;
  private List<String> params;

  public Command(String inputLine) {
    inputLine = inputLine.toUpperCase();
    final List<String> bookingList =
        Arrays.stream(inputLine.trim().split(SPACE))
            .map(String::trim)
            .filter(booking -> (booking.length() > 0))
            .collect(Collectors.toList());

    if (bookingList.size() == 0) {
      System.out.println("InvalidCommand");
    }

    commandName = bookingList.get(0);
    bookingList.remove(0);
    params = bookingList;
  }
}
