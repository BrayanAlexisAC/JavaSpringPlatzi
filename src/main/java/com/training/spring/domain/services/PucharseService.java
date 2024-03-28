package com.training.spring.domain.services;

import com.training.spring.domain.data.PucharseData;

import java.util.List;
import java.util.Optional;

public interface PucharseService {
    /**
     * Get back all orders
     * @return List<PucharseData>
     */
    List<PucharseData> getAll();

    /**
     * Get back all orders by Client
     * @return Optional<List<PucharseData>>
     */
    Optional<List<PucharseData>> getPucharsesByClient(String idCustomer);

    /**
     * Save an order
     * @param pucharseData PucharseData
     * @return PucharseData
     */
    PucharseData save(PucharseData pucharseData);
}
