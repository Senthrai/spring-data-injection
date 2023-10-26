package com.senthrai.di;

public class App {
    public static void main( String[] args ) {
//        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("com.senthrai");

//        LoanService loanService = context.getBean(LoanService.class);
//        LoanService loanService = new LoanService(new BackgroundVerificationService());

        MyBeanFactory myBeanFactory = new MyBeanFactory();
        LoanService loanService = myBeanFactory.getBean(LoanService.class);

        LoanRequest loanRequest = new LoanRequest(1L, "Sen", "senthrai@gmail.com", "P200");

        boolean approved = loanService.applyForLoan(loanRequest);

        if(approved){
            System.out.println("loan is approved");
        }else{
            System.out.println("loan is rejected");
        }
    }
}
