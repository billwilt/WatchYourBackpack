package co.grandcircus.WatchYourBackpack.Daos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import co.grandcircus.WatchYourBackpack.Entities.DBPark;
import co.grandcircus.WatchYourBackpack.Models.NPSModel.Park;

public interface ParksDao extends JpaRepository<DBPark, Long>{

	@Query(value = "SELECT DISTINCT entrance_fee FROM dbpark ORDER BY entrance_fee", nativeQuery = true)
	List<Double> findDistinctEntranceFees();
	
	@Query(value = "SELECT DISTINCT state_code FROM dbpark ORDER BY state_code", nativeQuery = true)
	List<String> findDistinctStateCodes();
	
	List<DBPark> findByStateCode(String stateCode);
	
	List<DBPark> findAllByOrderByName();
	
	List<DBPark> findAllByOrderByStateCode();
	
	List<DBPark> findAllByOrderByEntranceFee();
}
