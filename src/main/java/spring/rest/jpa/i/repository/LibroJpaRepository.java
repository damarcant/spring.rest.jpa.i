package spring.rest.jpa.i.repository;

import org.springframework.data.repository.CrudRepository;

import spring.rest.jpa.i.bean.Libro;
 

public interface LibroJpaRepository extends CrudRepository<Libro, Integer> {
	  
}
