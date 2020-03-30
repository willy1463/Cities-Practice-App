package com.wmorten.eurovisiontest.dao;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.wmorten.eurovisiontest.model.City;

@Repository
public interface DaoService extends PagingAndSortingRepository<City, Integer> {}
