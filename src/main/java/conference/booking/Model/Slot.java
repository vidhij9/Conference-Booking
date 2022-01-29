package conference.booking.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.lang.Nullable;

@Getter
@AllArgsConstructor
public class Slot {

  @Nullable private int startTime;

  @Nullable private int endTime;

  public void setStartTime(int startTime) {
    if (startTime < 1) {
      System.out.println("InvalidCommand");
    }
    this.startTime = startTime;
  }

  public void setEndTime(int endTime) {
    if (endTime > 24) {
      System.out.println("InvalidCommand");
    }
    this.endTime = endTime;
  }
}
