package fr.abdel.apiresto.dao;

import fr.abdel.apiresto.model.Menu;
import org.springframework.data.repository.CrudRepository;

public interface MenuRepository extends CrudRepository<Menu, String> {
}
