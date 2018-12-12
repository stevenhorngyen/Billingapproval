package com.netflix.billing.data.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="USER_HAS_SUBSCRIPTION")
public class UserHasSubscription {
	@Id
	@Column(name="USER_HAS_SUBSCRIPTION_ID")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	@Column(name="USER_ID")
	private long userId;
	@Column(name="PARTNER_ID")
	private long partnerId;
	@Column(name="START_DATE")
	private Date startDate;
    @Column(name="END_DATE")
    private Date endDate;
    @Column(name="CREATED_DATE")
    private Date createdDate;
    @Column(name="SUBSCRIPTION_TYPE_ID")
    private long subscriptionTypeId;
    
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
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
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public long getSubscriptionTypeId() {
		return subscriptionTypeId;
	}
	public void setSubscriptionTypeId(long subscriptionTypeId) {
		this.subscriptionTypeId = subscriptionTypeId;
	}

}
