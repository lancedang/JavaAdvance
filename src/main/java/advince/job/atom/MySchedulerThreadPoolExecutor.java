// Copyright (C) 2020 Meituan
// All rights reserved
package advince.job.atom;

import java.util.concurrent.atomic.AtomicLong;

/**
 * @author qiankai07
 * @version 1.0
 * Created on 10/24/20 8:47 PM
 **/
public class MySchedulerThreadPoolExecutor {

    private static final AtomicLong sequencer = new AtomicLong();


    private class MySchedulerFutureTask{

        private final long sequenceNumber;

        private final String name;

        MySchedulerFutureTask() {
            name = "task";
            sequenceNumber = sequencer.getAndIncrement();
        }


        MySchedulerFutureTask(String name) {
            this.name = name;
            sequenceNumber = sequencer.getAndIncrement();
        }

    }
}