package com.netflix.billing.data.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.netflix.billing.data.entity.BillingMonthly;

@Repository
public interface BillingMonthlyRepository extends JpaRepository<BillingMonthly, Long>{
	List<BillingMonthly> findByBillingDateAndPartnerId(Date date, long partnerId);
}
