package com.netflix.billing.data.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.netflix.billing.data.entity.User;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Long>{

}
