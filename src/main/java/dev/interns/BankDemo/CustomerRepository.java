package dev.interns.BankDemo;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.io.ObjectInput;
import java.util.Optional;

public interface CustomerRepository extends MongoRepository<Customer, ObjectId> {
    Optional<Customer> findCustomerBySSN(String SSN);
}
