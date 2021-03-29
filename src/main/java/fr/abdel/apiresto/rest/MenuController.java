package fr.abdel.apiresto.rest;

import fr.abdel.apiresto.model.Menu;
import fr.abdel.apiresto.service.MenuService;
import fr.abdel.apiresto.service.RestaurantService;
import fr.abdel.apiresto.util.ConditionCtrl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Set;

@RestController
public class MenuController {

    @Autowired
    public MenuService menuService;

    @Autowired
    public RestaurantService restaurantService;

    @GetMapping("/restaurants/{id}/menus")
    public Set<Menu> findAllMenusOfOneResto(@PathVariable("id") String id) {

        ConditionCtrl.checkFound(restaurantService.findById(id));

        return menuService.findAllMenusOfOneResto(id);
    }

    @GetMapping("/menus/{idMenu}")
    public Menu findById(@PathVariable("idMenu") String id) {

        Menu response = menuService.findById(id);
        ConditionCtrl.checkFound(response);
        return response;
    }

    @PostMapping("/restaurants/{idResto}/menus")
    @ResponseStatus(HttpStatus.CREATED)
    public String create(@PathVariable("idResto") String idRestaurant, @RequestBody Menu menu) {

        ConditionCtrl.checkFound(restaurantService.findById(idRestaurant));
        return menuService.create(idRestaurant, menu);

    }

    @PutMapping("/menus/{idMenu}")
    @ResponseStatus(HttpStatus.CREATED)
    public void update(@PathVariable("idMenu") String id, @RequestBody Menu menu) {

        ConditionCtrl.checkFound(menuService.findById(id));

        menuService.update(id, menu);
    }

    @PatchMapping("/menus/{idMenu}")
    @ResponseStatus(HttpStatus.CREATED)
    public void updatePart(@PathVariable("idMenu") String id, @RequestBody Map<String, Object> updates) {

        ConditionCtrl.checkFound(menuService.findById(id));

        menuService.updatePart(id, updates);
    }

    @DeleteMapping("/restaurants/{idResto}/menus/{idMenu}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("idResto") String id, @PathVariable("idMenu") String idMenu) {

        ConditionCtrl.checkFound(restaurantService.findById(id));
        ConditionCtrl.checkFound(menuService.findById(idMenu));

        menuService.deleteById(id, idMenu);

    }
}
