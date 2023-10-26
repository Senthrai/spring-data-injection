package com.senthrai.di;

import java.util.HashMap;
import java.util.Map;

public class MyBeanFactory {
    private final Map<Class<?>, Object> BEANS = init();

    //scan all the classes and identify beans annotated with @Component
    //Identify what are the dependencies for each component using Reflection

    private Map<Class<?>, Object> init() {
        Map<Class<?>, Object> beanMap = new HashMap<>();
        IBackgroundVerificationService bvs = new MockBackgroundVerificationService();
        LoanRepository loanRepository = new LoanRepository();
        LoanService loanService = new LoanService(bvs, loanRepository);

        beanMap.put(IBackgroundVerificationService.class, bvs);
        beanMap.put(LoanRepository.class, loanRepository);
        beanMap.put(LoanService.class, loanService);

        return beanMap;
    }

    public <T> T getBean(Class<?> type){
        return (T)BEANS.get(type);
    }
}
