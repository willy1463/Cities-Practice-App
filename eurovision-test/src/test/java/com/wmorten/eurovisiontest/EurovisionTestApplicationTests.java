package com.wmorten.eurovisiontest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import com.wmorten.eurovisiontest.dao.DaoService;
import com.wmorten.eurovisiontest.model.City;

@WebMvcTest(value = Controller.class)
class EurovisionTestApplicationTests {

	@Autowired
	MockMvc controller;
	
	@MockBean
	DaoService daoService;
	
	@MockBean
	DataService dataService;

	@BeforeEach
	public void setup() {
		
		City c1 = new City();
		c1.setId(1);
		c1.setName("Test City");
		ArrayList<City> cityList = new ArrayList<City>();
		cityList.add(c1);
		
		Page<City> resultPage = new PageImpl<City>(cityList);
		
		Pageable pageable = PageRequest.of(0,20);
		
		Mockito.when(daoService.findAll(pageable)).thenReturn(resultPage);
	}
	
	/**
	 * Tests that upon call to api endpoint where data is returned from databse, 200 OK status is given
	 * @throws Exception
	 */
	@Test
	public void getCitiesTest() throws Exception{
				
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/cities/queryByPage?page=4&size=100").accept(
				MediaType.APPLICATION_JSON);

		
		MvcResult result = controller.perform(requestBuilder).andReturn();

		assertEquals(200, result.getResponse().getStatus());
	}

}
