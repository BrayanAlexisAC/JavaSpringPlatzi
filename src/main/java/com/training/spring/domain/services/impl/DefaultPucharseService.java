package com.training.spring.domain.services.impl;

import com.training.spring.domain.data.PucharseData;
import com.training.spring.domain.services.PucharseService;
import com.training.spring.persistence.repository.PucharseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DefaultPucharseService implements PucharseService {

    @Autowired
    private PucharseRepository repository;

    @Override
    public List<PucharseData> getAll() {
        return repository.getAll();
    }

    @Override
    public Optional<List<PucharseData>> getPucharsesByClient(String idCustomer) {
        return repository.getByClient(idCustomer);
    }

    @Override
    public PucharseData save(PucharseData pucharseData) {
        return repository.save(pucharseData);
    }
}
