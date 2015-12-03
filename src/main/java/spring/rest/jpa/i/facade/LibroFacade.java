package spring.rest.jpa.i.facade;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.rest.jpa.i.bean.Libro;
import spring.rest.jpa.i.repository.LibroJpaRepository;


@Service
public class LibroFacade {
	
	@Autowired
	private LibroJpaRepository libroJpaRepository;

	public List<Libro> findAll() {
		return (List<Libro>) libroJpaRepository.findAll();
	}

	public Libro findOne(Integer id) {
		return libroJpaRepository.findOne(id);
	}

	public Libro update(Libro libro) { 
		return libroJpaRepository.save(libro);
	}

	public Integer save(Libro entity) {
		
		Libro l = libroJpaRepository.save(entity);		
		return l.getId();
	}

	public void delete(Integer id) {
		libroJpaRepository.delete(id);		
	}
	
}
