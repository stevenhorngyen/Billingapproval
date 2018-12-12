package com.netflix.billing.data.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.netflix.billing.data.entity.SubscriptionPrice;

@Repository
public interface SubscriptionPriceRepository extends PagingAndSortingRepository<SubscriptionPrice, Long>{
	SubscriptionPrice findByIdAndCountryId(Long subscriptionTypeId, Long countryId);
}
