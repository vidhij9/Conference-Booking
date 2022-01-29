package conference.booking.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.Nullable;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class Floor {

  @Nullable private String name;
  List<Room> roomList;
}
