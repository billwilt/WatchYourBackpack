package co.grandcircus.WatchYourBackpack.Daos;

import org.springframework.data.jpa.repository.JpaRepository;

import co.grandcircus.WatchYourBackpack.Entities.Item;

public interface ItemDao extends JpaRepository<Item, Long> {

}
