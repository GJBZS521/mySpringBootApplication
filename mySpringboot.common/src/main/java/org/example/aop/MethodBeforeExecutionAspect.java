package org.example.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.example.annotation.MethodExecutionAnnotation;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.time.LocalDateTime;

@Aspect
@Component
public class MethodBeforeExecutionAspect {

    @Before("@annotation(methodExecutionAnnotation)")
    public void BeforeMethodExecution(JoinPoint joinPoint, MethodExecutionAnnotation methodExecutionAnnotation) {
        PrintMethodInfo(joinPoint, methodExecutionAnnotation, "\n方法执行前，信息打印");
    }

    @After("@annotation(methodExecutionAnnotation)")
    public void AfterMethodExecution(JoinPoint joinPoint, MethodExecutionAnnotation methodExecutionAnnotation) {
        PrintMethodInfo(joinPoint, methodExecutionAnnotation, "\n方法执行后，信息打印");
    }

    private void PrintMethodInfo(JoinPoint joinPoint, MethodExecutionAnnotation methodExecutionAnnotation, String info) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();

        StringBuilder message = new StringBuilder();
        message.append(info);
        message.append("时间:").append(LocalDateTime.now()).append("\n");
        message.append("消息:").append(methodExecutionAnnotation.value()).append("\n");
        message.append("方法:").append(method.getDeclaringClass().getName()).append(".")
                .append(method.getName()).append("\n");

        if (methodExecutionAnnotation.logArguments()) {
            Object[] args = joinPoint.getArgs();
            if (args.length > 0) {
                message.append("参数: ");
                for (int i = 0; i < args.length; i++) {
                    message.append("\n  arg[").append(i).append("]")
                            .append(args[i] != null ? args[i].toString() : "null");
                }
                message.append("\n");
            } else {
                message.append("无参数");
            }
        }

        if (methodExecutionAnnotation.logCaller()) {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();

            for (int i = 3; i < stackTraceElements.length; i++) {
                if (!stackTraceElements[i].getClassName().contains("aspect")) {
                    message.append("调用者").append(stackTraceElements[i].getClassName())
                            .append(".").append(stackTraceElements[i].getMethodName())
                            .append("( 行号(: ").append(stackTraceElements[i].getLineNumber()).append("))\n");
                    break;
                }
            }
        }
        message.append("=========================");
        System.out.println(message);
    }

}
