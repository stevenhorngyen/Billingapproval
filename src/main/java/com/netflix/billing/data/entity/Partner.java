package com.netflix.billing.data.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="PARTNER")
public class Partner {
	
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="PARTNER_ID")
    private long id;
    @Column(name="NAME")
    private String name;
    @Column(name="COUNTRY_ID")
    private long countryId;
    
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getCountryId() {
		return countryId;
	}
	public void setCountryId(long countryId) {
		this.countryId = countryId;
	}
}
