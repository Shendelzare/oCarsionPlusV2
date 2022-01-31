
package com.grupolemon.ocarsionplus.repository;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.grupolemon.ocarsionplus.model.Coche;
import com.grupolemon.ocarsionplus.model.Precio;

@Repository
public interface PrecioRepository extends CrudRepository<Precio, Long> {

	@Query("select p from Precio p where p.coche = ?1 "
			+ "and ((?2 BETWEEN p.iniFechaVigor AND p.finFechaVigor) "
			+ "or(?2> p.iniFechaVigor AND p.finFechaVigor is null)) "
			+ "order by p.iniFechaVigor desc")
	public Optional<Precio> findByCocheAndFinFechaVigorAfterAndIniFechaVigorBefore(Coche coche, LocalDate fecha);
	
}
