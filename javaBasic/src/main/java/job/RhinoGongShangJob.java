package job;


import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

@Slf4j
public class RhinoGongShangJob extends RhinoBaseJob {

    @Override
    public String getJobName() {
        return "GongShang-Job";
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
                saveGongShangDb((RhinoGongShangResult) result);
            }
        });
    }

    private RhinoGongShangResult getResult(String name) {

        log.info("jobName={} begin send Request ", getJobName());

        try {
            //模拟耗时
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            log.error("error, ", e);
        }

        log.info("jobName={} end send Request ", getJobName());

        return RhinoGongShangResult
                .builder()
                .gongShangInfo(name + "-gongshang")
                .build();
    }

    private void saveGongShangDb(RhinoGongShangResult result) {
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
