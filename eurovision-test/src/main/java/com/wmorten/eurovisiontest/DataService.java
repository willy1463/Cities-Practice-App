package com.wmorten.eurovisiontest;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.wmorten.eurovisiontest.dao.DaoService;
import com.wmorten.eurovisiontest.model.City;

@Service
public class DataService {

	@Autowired
	DaoService daoService;
	
	/**
	 * Returns a list of City objects of size 'listSize' sorted alphabetically starting
	 *  from the first city whose 'name' property starts with'startletter' 
	 * @param listSize
	 * @param startletter
	 * @return
	 */
	public List<City> getCityListFromStartPoint(int listSize, String startletter) {
		
		List<City> allCities = (List<City>) daoService.findAll(Sort.by("name").ascending());
				
		return allCities.stream()
				.filter(c -> Character.toLowerCase(c.getName().charAt(0)) >= startletter.charAt(0))
				.limit(listSize)
				.collect(Collectors.toList());	
	}

	/**
	 * Method used to calculate the longest possible sequence of cities whose id values are always increasing 
	 * from a list of cities sorted alphabetically by name.
	 * @param citiesSortedByName - list of cities sorted alphabeticcally by name
	 * @return - ordered list of cities with increasing id values which are also subsequent (but not necessarily adjacent) in the input list 
	 */
	public List<City> findBiggestSequence(List<City> citiesSortedByName) {

		List<City> sequenceList = new ArrayList<City>();
		
		//Create second list equal to the input list but ordered by id
		List<City> citiesSortedById = new ArrayList<City>();
		citiesSortedById.addAll(citiesSortedByName);
		citiesSortedById.sort(Comparator.comparing(City::getId));
		
		for (int i = 0; i < citiesSortedById.size(); i++) {
			
			//starting with the  lowest numerical value for id, we create a list adding only elements which are alphabetically after
			List<City> currentSequence = new ArrayList<City>();
			City startCity = citiesSortedById.get(i);
			currentSequence.add(startCity);
			
			for(int j=0; j<citiesSortedById.size(); j++) {
				
				if(j <= i) {
					continue;
				}
				
				//Calulate the index of the current element in the name oredered list
				int namesIndexCurrent =	citiesSortedByName.indexOf(currentSequence.get(currentSequence.size()-1));
				//Calculate the index in the name ordered list of the next numerically orered element		
				int namesIndexNext = citiesSortedByName.indexOf(citiesSortedById.get(j));
				
				//Add it to the current sequence if appears after the current value in the names list
				if(namesIndexNext > namesIndexCurrent) {
					currentSequence.add(citiesSortedById.get(j));
				}
				else {
					//Temporarily store the longest list
					if(currentSequence.size() > sequenceList.size()) {
						sequenceList = currentSequence;
					}
					
					break;
				}
			}			
		}
		
		return sequenceList;
	}
	
	
}
