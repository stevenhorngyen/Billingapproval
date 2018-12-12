package com.netflix.billing.data.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.netflix.billing.data.entity.Partner;

@Repository
public interface PartnerRepository extends PagingAndSortingRepository<Partner, Long> {

}
