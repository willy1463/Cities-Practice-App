package com.wmorten.eurovisiontest;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.wmorten.eurovisiontest.dao.DaoService;
import com.wmorten.eurovisiontest.model.City;

@RestController
@CrossOrigin(origins = {"*"}, allowCredentials = "false") 
public class Controller {

	@Autowired
	DaoService daoService;
	
	@Autowired
	DataService dataService;
	
	@ResponseBody
	@RequestMapping("/cities/queryByPage")
	public Page<City> getCities(Pageable pageable){
		
		Page<City> resultPage = daoService.findAll(pageable);

		return resultPage;
	}
	
	@ResponseBody
	@RequestMapping("/cities/sequence")
	public List<City> getBiggestSequence(@RequestParam("sample") Optional<Integer> sampleSize,
			@RequestParam("start") Optional<String> startingLetter){
		
		//Default values of list size=30 and starting letter = a if none provided
		int sampleSizeValue = sampleSize.orElse(30);
		String startingLetterValue = String.valueOf(startingLetter.orElse("a").charAt(0)).toLowerCase(); 

		List<City> citiesList = dataService.getCityListFromStartPoint(sampleSizeValue, startingLetterValue);
				
		return dataService.findBiggestSequence(citiesList);
	}
	
}
