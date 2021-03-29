package fr.abdel.apiresto.service;

import fr.abdel.apiresto.model.Restaurant;

import java.util.List;
import java.util.Map;

public interface RestaurantService {

    public List<Restaurant> findAll();

    public Restaurant findById(String id);

    String create(Restaurant restaurant);

    void update(String id, Restaurant restaurant);

    void partialUpdate(String id, Map<String, Object> updates);

    void deleteById(String id);
}
