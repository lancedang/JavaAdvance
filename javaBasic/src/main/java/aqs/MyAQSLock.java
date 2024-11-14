package aqs;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

public class MyAQSLock {

    private class Sync extends AbstractQueuedSynchronizer {

        @Override
        protected boolean tryAcquire(int arg) {

            if (compareAndSetState(0, 1)) {
                setExclusiveOwnerThread(Thread.currentThread());
                return true;
            }

            return false;
        }

        @Override
        protected boolean tryRelease(int arg) {
            if (!isHeldExclusively()) {
                throw new RuntimeException("not hold lock");
            }
            setState(0);
            setExclusiveOwnerThread(null);

            return true;
        }

        @Override
        protected boolean isHeldExclusively() {
            return getExclusiveOwnerThread() == Thread.currentThread();
        }
    }


}
