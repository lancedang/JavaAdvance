package advance.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;

@Aspect
@Slf4j
public class MyAspect {

    @Pointcut("@annotation(advance.aspect.MyAnnotation)")
    public void cut() {

    }

    @Before("cut()")
    public void before(JoinPoint joinPoint) throws NoSuchMethodException {
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        String name = methodSignature.getName();
        log.info("name={}", name);

        Object target = joinPoint.getTarget();
        Method method = target.getClass().getMethod(name);
        log.info("method={}", method);
    }
}
