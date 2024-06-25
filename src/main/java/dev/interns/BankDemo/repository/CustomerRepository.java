package dev.interns.BankDemo.repository;

import dev.interns.BankDemo.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    // Can remove this function
    Optional<Customer> findCustomerBySsn(String ssn);
}