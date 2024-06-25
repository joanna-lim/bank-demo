package dev.interns.BankDemo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BankAccount {

    @Id
    private Long bankAccNum;

    private Double balance;
    private Long customerId;
}
