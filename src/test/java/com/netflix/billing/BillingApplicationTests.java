package com.netflix.billing;

import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.netflix.billing.controller.web.service.BillingServiceController;
import com.netflix.billing.dto.BillingDetails;
import com.netflix.billing.service.BillingService;

/**
 * Billing Service Tests
 * 
 * @author shuang
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(BillingServiceController.class)
public class BillingApplicationTests {
	@MockBean
	private BillingService billingService;
	@Autowired
	private MockMvc mockMvc;
	private static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

	/**
	 * Unit Test - testing Billing Service api calls
	 * @throws Exception when test result is not as expected
	 */
	@Test
	public void testBillingDetails() throws Exception{
		Date date = DATE_FORMAT.parse("2018-12-01");
		List<BillingDetails> mockBillingDetails = new ArrayList<BillingDetails>();
		BillingDetails mockBillingDetail = new BillingDetails();
		mockBillingDetail.setBillingDate(date);
		mockBillingDetail.setBillingId(2);
		mockBillingDetail.setPrice(8.62);
		mockBillingDetail.setStatus("Pending");
		mockBillingDetail.setSubscriptionType("Standard");
		mockBillingDetail.setUserId(1002);
		mockBillingDetail.setPartnerId(2);
		mockBillingDetail.setCurrencyType("Pound");
		mockBillingDetails.add(mockBillingDetail);
		
		given(billingService.getBillingDetails(new Long(2), "2018-12-01")).willReturn(mockBillingDetails);
		mockMvc.perform(get("/api/details/partner/2/billing-date/2018-12-01")).andExpect(status().isOk()).andExpect(content().string(containsString("Standard")));
		mockMvc.perform(post("/api/approve/partner/2/billing/2")).andExpect(status().isOk());
		mockMvc.perform(post("/api/decline/partner/2/billing/2")).andExpect(status().isOk());
		mockMvc.perform(post("/api/approve-all/partner/2/billing-date/2018-12-01")).andExpect(status().isOk());
		mockMvc.perform(post("/api/decline-all/partner/2/billing-date/2018-12-01")).andExpect(status().isOk());
	}
}
