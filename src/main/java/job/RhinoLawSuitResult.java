package job;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class RhinoLawSuitResult extends RhinoBaseResult {

    private List<String> suitList;

}
