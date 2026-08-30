package com.example.orchestation.Aspect;

import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.SmartValidator;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RequestBody;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

@RequiredArgsConstructor
@Aspect
@Component
public class GlobalMappingValidationAspect {

    private final SmartValidator smartValidator;

    // Intercepts any method annotated with @PostMapping in your controllers
    @Before("@annotation(org.springframework.web.bind.annotation.PostMapping)")
    public void ValidateRequests(JoinPoint joinPoint) throws Throwable {

        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        Object[] args = joinPoint.getArgs();
        Annotation[][] parameterAnnotation = method.getParameterAnnotations();

        for (int idx = 0; idx < args.length; idx++) {
            Object arg = args[idx];

            if (arg == null) {
                continue;
            }

            // Check if the argument is annotated with @RequestBody
            boolean isRequestBody = false;
            for (Annotation paramAnnotation : parameterAnnotation[idx]) {
                if (paramAnnotation instanceof RequestBody) {
                    isRequestBody = true;
                    break;
                }
            }

            if (isRequestBody) {
                String objectName = arg.getClass().getSimpleName();
                BeanPropertyBindingResult bindingResult = new BeanPropertyBindingResult(arg, objectName);

                // Trigger spring validation programmatically
                smartValidator.validate(arg, bindingResult);

                if (bindingResult.hasErrors()) {
                    // MethodParameter is required to construct MethodArgumentNotValidException
                    MethodParameter methodParameter = new MethodParameter(method, idx);
                    throw new MethodArgumentNotValidException(methodParameter, bindingResult);
                }
            }
        }
    }

}
