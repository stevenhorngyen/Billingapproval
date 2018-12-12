package com.netflix.billing.data.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.netflix.billing.data.entity.SubscriptionType;

@Repository
public interface SubscriptionTypeRepository extends PagingAndSortingRepository<SubscriptionType, Long>{

}
