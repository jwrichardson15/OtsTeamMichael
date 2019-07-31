package com.credera.parks.common.service;


import com.credera.parks.common.model.Category;
import com.credera.parks.common.model.Park;
import com.credera.parks.common.model.Status;
import com.credera.parks.common.model.Ticket;
import com.credera.parks.common.repository.CategoryRepository;
import com.credera.parks.common.repository.ParkRepository;
import com.credera.parks.common.repository.StatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DefaultService {

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    ParkRepository parkRepository;

    @Autowired
    StatusRepository statusRepository;

    public List<Category> getAllCategories(){
        return categoryRepository.findAll();
    }

    public List<Park> getAllParks(){
        return parkRepository.findAll();
    }

    public List<Status> getAllStatuses() { return statusRepository.findAll(); }
}
