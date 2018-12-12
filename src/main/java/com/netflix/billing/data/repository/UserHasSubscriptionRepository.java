package com.netflix.billing.data.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.netflix.billing.data.entity.UserHasSubscription;

@Repository
public interface UserHasSubscriptionRepository extends PagingAndSortingRepository<UserHasSubscription, Long>{
	UserHasSubscription findByUserId(Long userId);
}
