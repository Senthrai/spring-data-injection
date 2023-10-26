package com.senthrai.di;

import org.springframework.stereotype.Component;

@Component
public class LoanService {
    private IBackgroundVerificationService bvService;
    private LoanRepository loanRepository;

    public LoanService(IBackgroundVerificationService bvService,LoanRepository loanRepository) {
        this.bvService = bvService;
        this.loanRepository = loanRepository;
    }

    public boolean applyForLoan(LoanRequest loanRequest) {
        int score = bvService.getScore(loanRequest.uniqueId);
        if(score < 3) {
            System.out.println("Sanction loan");
            return true;
        } else {
            System.out.println("Reject loan. Reason: Poor credibility score");
            return false;
        }
    }
}