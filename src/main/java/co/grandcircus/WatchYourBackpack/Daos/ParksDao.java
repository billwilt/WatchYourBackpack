package co.grandcircus.WatchYourBackpack.Daos;

import org.springframework.data.jpa.repository.JpaRepository;

import co.grandcircus.WatchYourBackpack.Entities.DBPark;

public interface ParksDao extends JpaRepository<DBPark, Long>{

	
}
