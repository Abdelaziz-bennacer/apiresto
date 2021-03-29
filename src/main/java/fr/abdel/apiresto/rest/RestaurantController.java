package fr.abdel.apiresto.rest;

import fr.abdel.apiresto.model.Restaurant;
import fr.abdel.apiresto.service.RestaurantService;
import fr.abdel.apiresto.util.ConditionCtrl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/restaurants")
public class RestaurantController {

    @Autowired
    private RestaurantService restoService;


    @GetMapping
    public List<Restaurant> findAll() {

        List<Restaurant> restaurants = restoService.findAll();
        for (Restaurant restaurant: restaurants) {
            Link selfLink = WebMvcLinkBuilder.linkTo(RestaurantController.class).slash(restaurant.getId()).withSelfRel();
            restaurant.add(selfLink);
        }

       return restoService.findAll();
    }

    @GetMapping("/{id}")
    public Restaurant findById(@PathVariable("id") String id) {

        Restaurant response = restoService.findById(id);
        ConditionCtrl.checkFound(response);

        Link menusLink= WebMvcLinkBuilder.linkTo(RestaurantController.class).slash(response.getId()).slash("menus").withRel("menus");
        response.add(menusLink);
        return response;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String create(@RequestBody Restaurant restaurant){
        return restoService.create(restaurant);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void update(@RequestBody Restaurant restaurant, @PathVariable("id") String id) {

        ConditionCtrl.checkFound(restoService.findById(id));
        /*if(restoService.findById(id) == null) {
            throw new ResourceNotFoundException();
        }*/
        restoService.update(id, restaurant);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void partialUpdate(@RequestBody Map<String, Object> updates, @PathVariable("id") String id) {

        ConditionCtrl.checkFound(restoService.findById(id));

        /*if(restoService.findById(id) == null) {
            throw new ResourceNotFoundException();
        }*/
        restoService.partialUpdate(id,updates);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable("id") String id) {

        ConditionCtrl.checkFound(restoService.findById(id));

        /*if(restoService.findById(id) == null) {
            throw new ResourceNotFoundException();
        }*/
        restoService.deleteById(id);
    }
}
