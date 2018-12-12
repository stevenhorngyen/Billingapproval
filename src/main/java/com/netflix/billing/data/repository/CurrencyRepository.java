package com.netflix.billing.data.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.netflix.billing.data.entity.Currency;

@Repository
public interface CurrencyRepository extends PagingAndSortingRepository<Currency, Long>{
	Currency findByCountryId(Long countryId);
}
