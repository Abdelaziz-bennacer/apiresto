package fr.abdel.apiresto.service;

import fr.abdel.apiresto.model.Menu;

import java.util.Map;
import java.util.Set;


public interface MenuService {

    Set<Menu> findAllMenusOfOneResto(String id);

    Menu findById(String id);

    String create(String idRestaurant, Menu menu);

    void update(String id, Menu menu);

    void updatePart(String id, Map<String, Object> updates);

    void deleteById(String id, String idMenu);
}
