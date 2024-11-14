package job;

import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.concurrent.TimeUnit;

@Slf4j
public class RhinoLawSuitJob extends RhinoBaseJob {

    @Override
    public String getJobName() {
        return "LawSuit-Job";
    }

    @Override
    protected void realDo() {
        super.doWithProvider(new Type1Provider() {
            @Override
            public RhinoBaseResult sendRequest(String companyName) {
                return getResult(companyName);
            }

            @Override
            public void handleRequestResult(RhinoBaseResult result) {
                saveLawSuitDb((RhinoLawSuitResult) result);
            }
        });
    }

    private RhinoLawSuitResult getResult(String name) {

        log.info("jobName={} begin send Request ", getJobName());

        try {
            //模拟耗时
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            log.error("error, ", e);
        }

        log.info("jobName={} end send Request ", getJobName());

        return RhinoLawSuitResult
                .builder()
                .suitList(Collections.emptyList())
                .build();
    }

    private void saveLawSuitDb(RhinoLawSuitResult result) {
        log.info("jobName={} begin handleRequestResult ", getJobName());
        try {
            //模拟耗时
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            log.error("error, ", e);
        }
        log.info("jobName={} end handleRequestResult ", getJobName());
    }
}
