package com.credera.parks.common.service;


import com.credera.parks.common.model.Category;
import com.credera.parks.common.model.Park;
import com.credera.parks.common.model.Ticket;
import com.credera.parks.common.repository.CategoryRepository;
import com.credera.parks.common.repository.ParkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DefaultService {

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    ParkRepository parkRepository;

    public List<Category> getAllCategories(){
        return categoryRepository.findAll();
    }

    public List<Park> getAllParks(){
        return parkRepository.findAll();
    }
}
