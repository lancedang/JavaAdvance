package concurrent.core2.weakreference.demo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
@AllArgsConstructor
@NoArgsConstructor
public class Apple {
    private String name;

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        log.info("apple finalize={}", name);
    }
}
