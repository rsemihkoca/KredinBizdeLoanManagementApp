package com.rsemihkoca.garantiservice.repository;

import com.rsemihkoca.garantiservice.entity.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface LoanRepository extends JpaRepository<Loan, Long> {

    Loan getLoanById(Long loanId);
}

