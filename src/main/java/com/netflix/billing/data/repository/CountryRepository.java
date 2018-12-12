package com.netflix.billing.data.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.netflix.billing.data.entity.Country;

@Repository
public interface CountryRepository extends PagingAndSortingRepository<Country, Long>{

}
