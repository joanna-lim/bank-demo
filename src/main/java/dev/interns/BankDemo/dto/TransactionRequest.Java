package dev.interns.BankDemo.dto;

public class TransactionRequest {
    private Long fromBankAccNum;
    private Long toBankAccNum;
    private Double amount;
    private String description;

    // Getters and setters
    public Long getFromBankAccNum() {
        return fromBankAccNum;
    }

    public void setFromBankAccNum(Long fromBankAccNum) {
        this.fromBankAccNum = fromBankAccNum;
    }

    public Long getToBankAccNum() {
        return toBankAccNum;
    }

    public void setToBankAccNum(Long toBankAccNum) {
        this.toBankAccNum = toBankAccNum;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
