package fr.abdel.apiresto.service.impl;

import fr.abdel.apiresto.dao.RestaurantRepository;
import fr.abdel.apiresto.model.Restaurant;
import fr.abdel.apiresto.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class RestaurantServiceImpl implements RestaurantService {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Override
    public List<Restaurant> findAll() {

        List<Restaurant> list = new ArrayList<>();

        restaurantRepository.findAll().forEach(list::add);

        return list;
    }

    @Override
    public Restaurant findById(String id) {
        if(restaurantRepository.findById(id).isPresent()) {
            return restaurantRepository.findById(id).get();
        }
        return null;

    }

    @Override
    public String create(Restaurant restaurant) {
        return restaurantRepository.save(restaurant).getId();
    }

    @Override
    public void update(String id, Restaurant restaurant) {
        restaurant.setId(id);
        restaurantRepository.save(restaurant);
    }

    @Override
    public void partialUpdate(String id, Map<String, Object> updates) {

        Restaurant restoToUpdate = restaurantRepository.findById(id).get();
        for(String key: updates.keySet()){
            switch (key) {
                case "nom": {
                    restoToUpdate.setNom((String) updates.get(key));
                    break;
                }
                case "adresse": {
                    restoToUpdate.setAdresse((String) updates.get(key));
                    break;
                }
            }

        }
        restaurantRepository.save(restoToUpdate);
    }

    @Override
    public void deleteById(String id) {
        restaurantRepository.deleteById(id);
    }
}
