package com.InvestoTracker.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Investment {
    @Id @GeneratedValue
    private Long id;

    private String company;
    private double amountInvested;
    private String investmentDate;
    private String withdrawalDate;
    private Double resultantAmount;
    private Double profit;
    private Double tax;

    @ManyToOne
    private User user;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public double getAmountInvested() {
		return amountInvested;
	}

	public void setAmountInvested(double amountInvested) {
		this.amountInvested = amountInvested;
	}

	public String getInvestmentDate() {
		return investmentDate;
	}

	public void setInvestmentDate(String investmentDate) {
		this.investmentDate = investmentDate;
	}

	public String getWithdrawalDate() {
		return withdrawalDate;
	}

	public void setWithdrawalDate(String withdrawalDate) {
		this.withdrawalDate = withdrawalDate;
	}

	public Double getResultantAmount() {
		return resultantAmount;
	}

	public void setResultantAmount(Double resultantAmount) {
		this.resultantAmount = resultantAmount;
	}

	public Double getProfit() {
		return profit;
	}

	public void setProfit(Double profit) {
		this.profit = profit;
	}

	public Double getTax() {
		return tax;
	}

	public void setTax(Double tax) {
		this.tax = tax;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Investment [id=" + id + ", company=" + company + ", amountInvested=" + amountInvested
				+ ", investmentDate=" + investmentDate + ", withdrawalDate=" + withdrawalDate + ", resultantAmount="
				+ resultantAmount + ", profit=" + profit + ", tax=" + tax + ", user=" + user + "]";
	}

	public Investment(Long id, String company, double amountInvested, String investmentDate, String withdrawalDate,
			Double resultantAmount, Double profit, Double tax, User user) {
		super();
		this.id = id;
		this.company = company;
		this.amountInvested = amountInvested;
		this.investmentDate = investmentDate;
		this.withdrawalDate = withdrawalDate;
		this.resultantAmount = resultantAmount;
		this.profit = profit;
		this.tax = tax;
		this.user = user;
	}

	public Investment() {
		super();
		// TODO Auto-generated constructor stub
	}
    
}
