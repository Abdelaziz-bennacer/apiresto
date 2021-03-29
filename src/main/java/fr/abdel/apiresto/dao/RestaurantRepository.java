package fr.abdel.apiresto.dao;

import fr.abdel.apiresto.model.Restaurant;
import org.springframework.data.repository.CrudRepository;

public interface RestaurantRepository extends CrudRepository<Restaurant, String> {
}
