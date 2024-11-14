package job;

import lombok.*;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RhinoGongShangResult extends RhinoBaseResult {
    private String gongShangInfo;
}
