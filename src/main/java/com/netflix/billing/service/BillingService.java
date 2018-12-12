package com.netflix.billing.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.netflix.billing.data.entity.BillingMonthly;
import com.netflix.billing.data.entity.Currency;
import com.netflix.billing.data.entity.SubscriptionPrice;
import com.netflix.billing.data.entity.SubscriptionType;
import com.netflix.billing.data.entity.User;
import com.netflix.billing.data.entity.UserHasSubscription;
import com.netflix.billing.data.repository.BillingMonthlyRepository;
import com.netflix.billing.data.repository.CurrencyRepository;
import com.netflix.billing.data.repository.SubscriptionPriceRepository;
import com.netflix.billing.data.repository.SubscriptionTypeRepository;
import com.netflix.billing.data.repository.UserHasSubscriptionRepository;
import com.netflix.billing.data.repository.UserRepository;
import com.netflix.billing.dto.BillingDetails;

@Service
public class BillingService {
	private UserRepository userRepository;
	private UserHasSubscriptionRepository userHasSubscriptionRepository;
	private BillingMonthlyRepository billingMonthlyRepository;
	private SubscriptionTypeRepository subscriptionTypeRepository;
	private SubscriptionPriceRepository subscriptionPriceRepository;
	private CurrencyRepository currencyRepository;
	private static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
	
	@Autowired
	public BillingService(UserRepository userRepository, 
			UserHasSubscriptionRepository userHasSubscriptionRepository,
			BillingMonthlyRepository billingMonthlyRepository,
			SubscriptionTypeRepository subscriptionTypeRepository,
			SubscriptionPriceRepository subscriptionPriceRepository,
			CurrencyRepository currencyRepository){
		this.userRepository = userRepository;
		this.userHasSubscriptionRepository = userHasSubscriptionRepository;
		this.billingMonthlyRepository = billingMonthlyRepository;
		this.subscriptionTypeRepository = subscriptionTypeRepository;
		this.subscriptionPriceRepository = subscriptionPriceRepository;
		this.currencyRepository = currencyRepository;
	}
	
	public List<BillingDetails> getBillingDetails(Long partnerId, String dateString){
		Date date = this.createDateFromDateString(dateString);
		List<BillingDetails> billingDetails = new ArrayList<BillingDetails>();
		Iterable<BillingMonthly> billingMonthly = billingMonthlyRepository.findByBillingDateAndPartnerId(
				new java.sql.Date(date.getTime()), partnerId);

		UserHasSubscription userHasSubscription1 = userHasSubscriptionRepository.findByUserId(new Long(1001));
		System.out.println(userHasSubscription1.getPartnerId());
		System.out.println(date);
		if(billingMonthly != null){
			for (BillingMonthly bm : billingMonthly){
				BillingDetails billingDetail = new BillingDetails();

				UserHasSubscription userHasSubscription = userHasSubscriptionRepository.findByUserId(bm.getUserId());
                if(bm != null){
                	billingDetail.setBillingId(bm.getId());
                	billingDetail.setBillingDate(date);
                	billingDetail.setUserId(bm.getUserId());
                	billingDetail.setPartnerId(partnerId);
                	billingDetail.setStatus(bm.getStatus());
                	if(userHasSubscription != null) {
        				Optional<User> user = userRepository.findById(userHasSubscription.getUserId());
        				if(user != null){//user info
            				Currency currency = currencyRepository.findByCountryId(user.get().getCountryId());
            				Optional<SubscriptionType> subscriptionType = subscriptionTypeRepository.findById(
            						userHasSubscription.getSubscriptionTypeId());
            				SubscriptionPrice subscriptionPrice = subscriptionPriceRepository.findByIdAndCountryId(
            						userHasSubscription.getSubscriptionTypeId(), user.get().getCountryId());
              				if(subscriptionType != null){
            					billingDetail.setSubscriptionType(subscriptionType.get().getDescription());
            				}
              				if(subscriptionPrice != null){
                				billingDetail.setPrice(subscriptionPrice.getPrice());
            				}
              				if(currency != null){
              					billingDetail.setCurrencyType(currency.getDescription());
              				}
        				}
                	}
                }
                billingDetails.add(billingDetail);
            };
        }
		return billingDetails;		
	}
	
	public void updateBillingDetail(Long partnerId, Long billingId, String status) throws Exception{
		BillingMonthly billingMonthlyUpdate = billingMonthlyRepository.getOne(billingId);
		if(billingMonthlyUpdate != null){
			if(billingMonthlyUpdate.getPartnerId() != partnerId){
				throw new Exception();
			}else{
				billingMonthlyUpdate.setStatus(status);
				billingMonthlyRepository.save(billingMonthlyUpdate);
			}
		}
	}
	
	public void updateBillingDetail(Long partnerId, String dateString, String status){
		Date date = this.createDateFromDateString(dateString);
		Iterable<BillingMonthly> billingMonthly = billingMonthlyRepository.findByBillingDateAndPartnerId
				(new java.sql.Date(date.getTime()), partnerId);
		if(billingMonthly != null){
			for (BillingMonthly bm : billingMonthly){
				bm.setStatus(status);
				billingMonthlyRepository.save(bm);
			}
		}
	}
	
    private Date createDateFromDateString(String dateString){
        Date date = null;
        if(dateString != null) {
            try {
                date = DATE_FORMAT.parse(dateString);
            }catch(ParseException pe){
                date = new Date();
            }
        }else{
            date = new Date();
        }
        return date;
    }
}
