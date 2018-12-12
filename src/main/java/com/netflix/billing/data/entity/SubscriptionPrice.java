package com.netflix.billing.data.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="SUBSCRIPTION_PRICE")
public class SubscriptionPrice {
	@Id
	@Column(name="SUBSCRIPTION_TYPE_ID")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	@Column(name="COUNTRY_ID")
	private long countryId;
	@Column(name="PRICE")
	private double price;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getCountryId() {
		return countryId;
	}
	public void setCountryId(long countryId) {
		this.countryId = countryId;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
}
