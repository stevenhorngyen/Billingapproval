package com.netflix.billing.controller.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.billing.dto.BillingDetails;
import com.netflix.billing.service.BillingService;

/**
 * 
 * @author shuang
 * Retrieve billing details by billing date
 * Post update per billing id
 * Post update by billing date
 */
@RestController
@RequestMapping(value="/api")
public class BillingServiceController {
    @Autowired
    private BillingService billingService;

    @RequestMapping(method= RequestMethod.GET, value="/details/partner/{partnerId}/billing-date/{date}")
    ResponseEntity<List<BillingDetails>> getAllBillingDetailsForDate(
    		@PathVariable("partnerId") Long partnerId, @PathVariable("date") String dateString){
        return new ResponseEntity<>(billingService.getBillingDetails(partnerId, dateString),
        		HttpStatus.OK);
    }
    
    @RequestMapping(method= RequestMethod.POST, value="/approve/partner/{partnerId}/billing/{billingId}")
    ResponseEntity<?> approveBillingDetail(@PathVariable("partnerId") Long partnerId, @PathVariable("billingId") Long billingId){
    	try{
    		billingService.updateBillingDetail(partnerId, billingId, "Approval");
    		return new ResponseEntity<>(HttpStatus.OK);
    	}catch(Exception e){
    		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    	}
    }
    
    @RequestMapping(method= RequestMethod.POST, value="/decline/partner/{partnerId}/billing/{billingId}")
    ResponseEntity<?> declineBillingDetail(@PathVariable("partnerId") Long partnerId, @PathVariable("billingId") Long billingId){
    	try{
    		billingService.updateBillingDetail(partnerId, billingId, "Decline");
    		return new ResponseEntity<>(HttpStatus.OK);
    	}catch(Exception e){
    		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    	}
    }
    
    @RequestMapping(method= RequestMethod.POST, value="/approve-all/partner/{partnerId}/billing-date/{date}")
    ResponseEntity<?> approveAllBillingDetail(@PathVariable("partnerId") Long partnerId, @PathVariable("date") String dateString){
    	try{
    		billingService.updateBillingDetail(partnerId, dateString, "Approval");
    		return new ResponseEntity<>(HttpStatus.OK);
    	}catch(Exception e){
    		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    	}
    }
    
    @RequestMapping(method= RequestMethod.POST, value="/decline-all/partner/{partnerId}/billing-date/{date}")
    ResponseEntity<?> declineAllBillingDetail(@PathVariable("partnerId") Long partnerId, @PathVariable("date") String dateString){
    	try{
    		billingService.updateBillingDetail(partnerId, dateString, "Decline");
    		return new ResponseEntity<>(HttpStatus.OK);
    	}catch(Exception e){
    		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    	}
    }

}
