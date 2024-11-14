package advance.job.main;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MainFindDemo {
    public static void main(String[] args) {

        RuntimeException exception = getException();

        StackTraceElement[] stackTrace = exception.getStackTrace();

        log.error("error, ", exception);

        //每个异常栈都是若干个方法体"调用顺序的组合,栈是一个容器
        //异常栈item元素是StackTraceElement
        for (StackTraceElement stackTraceElement : stackTrace) {

            String methodName = stackTraceElement.getMethodName();
            int lineNumber = stackTraceElement.getLineNumber();
            String fileName = stackTraceElement.getFileName();
            String className = stackTraceElement.getClassName();

            log.info("methodName={}, lineNumber={}, fileName={}, className={}", methodName, lineNumber, fileName, className);

            if ("main".equals(methodName)) {
                log.info("main 类={}", className);
            }

        }

    }

    public static RuntimeException getException() {
        return new RuntimeException();
    }

    public static StackTraceElement[] getStackTraceElement() {
        return new RuntimeException().getStackTrace();
    }
}
