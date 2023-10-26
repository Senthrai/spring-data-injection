package com.senthrai.di;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.stereotype.Component;

import java.lang.reflect.Constructor;
import java.lang.reflect.Parameter;
import java.util.Set;

public class SpringDI {
    public static void main(String[] args) throws Exception{
//        printConstructors(LoanService.class);
        createBeansUsingReflection();
    }

    static void createBeansUsingReflection()  throws Exception{
        MockBackgroundVerificationService bvs =
                (MockBackgroundVerificationService) Class.forName(MockBackgroundVerificationService.class.getName())
                        .getDeclaredConstructors()[0].newInstance();

        LoanRepository loanRepository = (LoanRepository) Class.forName(LoanRepository.class.getName())
                .getDeclaredConstructors()[0].newInstance();

        LoanService loanService = (LoanService) Class.forName(LoanService.class.getName()).getDeclaredConstructors()[0].newInstance(bvs,loanRepository);

        LoanRequest loanRequest = new LoanRequest(1L, "Sen", "senthrai@gmail.com", "P200");

        boolean approved = loanService.applyForLoan(loanRequest);

        if(approved){
            System.out.println("loan is approved");
        }else{
            System.out.println("loan is rejected");
        }
    }

    static void printConstructors(Class<?> type){
        Constructor<?>[] constructors = type.getConstructors();
        Parameter[] parameters = constructors[0].getParameters();
        for (Parameter parameter : parameters) {
            System.out.println("parameterType: " + parameter.getType());
        }
    }

    public static void scanForComponents(){
        ClassPathScanningCandidateComponentProvider scanningCandidateComponentProvider = new ClassPathScanningCandidateComponentProvider(false);

        scanningCandidateComponentProvider.addIncludeFilter(new AnnotationTypeFilter(Component.class));

        Set<BeanDefinition> candidateComponents = scanningCandidateComponentProvider.findCandidateComponents("com.senthrai.di");
        for (BeanDefinition candidateComponent : candidateComponents) {
            System.out.println(candidateComponent.getBeanClassName());
        }
    }
}
