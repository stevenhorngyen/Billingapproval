package com.netflix.billing.data.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="BILLING_DECEMBER_2018")
public class BillingMonthly {
	@Id
	@Column(name="BILLING_ID")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	@Column(name="BILLING_DATE")
	private Date billingDate;
	@Column(name="USER_ID")
	private long userId;
	@Column(name="PARTNER_ID")
	private long partnerId;
	@Column(name="STATUS")
	private String status;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Date getBillingDate() {
		return billingDate;
	}
	public void setBillingDate(Date billingDate) {
		this.billingDate = billingDate;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public long getPartnerId() {
		return partnerId;
	}
	public void setPartnerId(long partnerId) {
		this.partnerId = partnerId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}
