package conference.booking.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.Nullable;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class Building {

  @Nullable private String name;
  List<Floor> floorList;
}
