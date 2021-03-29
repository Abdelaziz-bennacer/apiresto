package fr.abdel.apiresto.service.impl;

import fr.abdel.apiresto.dao.MenuRepository;
import fr.abdel.apiresto.dao.RestaurantRepository;
import fr.abdel.apiresto.model.Menu;
import fr.abdel.apiresto.model.Restaurant;
import fr.abdel.apiresto.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Set;

@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private MenuRepository menuRepository;

    @Override
    public Set<Menu> findAllMenusOfOneResto(String id) {

        return restaurantRepository.findById(id).get().getMenus();
    }

    @Override
    public Menu findById(String id) {
        if(menuRepository.findById(id).isPresent()) {
        return menuRepository.findById(id).get();
        }
        return null;
    }

    @Override
    public String create(String idRestaurant, Menu menu) {
        Restaurant restoEntity = restaurantRepository.findById(idRestaurant).get();
        restoEntity.getMenus().add(menu);
        restaurantRepository.save(restoEntity);
        Menu menuEntity = restoEntity.getMenus().stream().filter(m -> m.equals(menu)).findFirst().get();
        return menuEntity.getIdMenu();
    }

    @Override
    public void update(String id, Menu menu) {
        menu.setIdMenu(id);
        menuRepository.save(menu);
    }

    @Override
    public void updatePart(String id, Map<String, Object> updates) {

        Menu menuToUpdate = menuRepository.findById(id).get();

        for(String key: updates.keySet()) {
            switch (key) {
                case "entrees": {
                    menuToUpdate.setEntrees((String) updates.get(key));
                    break;
                }
                case "plats": {
                    menuToUpdate.setPlats((String) updates.get(key));
                    break;
                }
                case "desserts": {
                    menuToUpdate.setDesserts((String) updates.get(key));
                    break;
                }
            }
        }

        menuRepository.save(menuToUpdate);
    }

    @Override
    public void deleteById(String id, String idMenu) {

        Restaurant restoToUpdate = restaurantRepository.findById(id).get();
        Set<Menu> menus = restoToUpdate.getMenus();
        Menu menu = menus.stream().filter(m -> m.getIdMenu().equals(idMenu)).findFirst().get();
        menus.remove(menu);
        restoToUpdate.setMenus(menus);
        restaurantRepository.save(restoToUpdate);
        menuRepository.delete(menu);
    }

}
