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

    @RequestMapping(method= RequestMethod.GET, value="/details/{date}")
    ResponseEntity<List<BillingDetails>> getAllBillingDetailsForDate(@PathVariable(value="date")String dateString){
        return new ResponseEntity<>(billingService.getBillingDetails(dateString),
        		HttpStatus.OK);
    }
    
    @RequestMapping(method= RequestMethod.POST, value="/approve/{billingId}")
    ResponseEntity<?> approveBillingDetail(@PathVariable(value="billingId")Long billingId){
    	try{
    		billingService.updateBillingDetail(billingId, "Approval");
    		return new ResponseEntity<>(HttpStatus.OK);
    	}catch(Exception e){
    		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    	}
    }
    
    @RequestMapping(method= RequestMethod.POST, value="/decline/{billingId}")
    ResponseEntity<?> declineBillingDetail(@PathVariable(value="billingId")Long billingId){
    	try{
    		billingService.updateBillingDetail(billingId, "Decline");
    		return new ResponseEntity<>(HttpStatus.OK);
    	}catch(Exception e){
    		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    	}
    }
    
    @RequestMapping(method= RequestMethod.POST, value="/approve-all/{date}")
    ResponseEntity<?> approveAllBillingDetail(@PathVariable(value="date")String dateString){
    	try{
    		billingService.updateBillingDetail(dateString, "Approval");
    		return new ResponseEntity<>(HttpStatus.OK);
    	}catch(Exception e){
    		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    	}
    }
    
    @RequestMapping(method= RequestMethod.POST, value="/decline-all/{date}")
    ResponseEntity<?> declineAllBillingDetail(@PathVariable(value="date")String dateString){
    	try{
    		billingService.updateBillingDetail(dateString, "Decline");
    		return new ResponseEntity<>(HttpStatus.OK);
    	}catch(Exception e){
    		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    	}
    }

}
