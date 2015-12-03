package spring.rest.jpa.i.controller;

import java.net.URI;
import java.util.List;

import javax.inject.Inject;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import spring.rest.jpa.i.bean.Libro;
import spring.rest.jpa.i.facade.LibroFacade;

@RestController("libroController.v1")
@RequestMapping("libros")
public class LibroController {

 
	@Inject
	private LibroFacade libroFacade;
	 
	@RequestMapping(method = RequestMethod.GET)	 
	@ResponseStatus(HttpStatus.OK) 
	public List<Libro> findAll() {		
		return libroFacade.findAll();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)	 
	public Libro findOne(@PathVariable(value = "id") Integer id) {	
		return libroFacade.findOne(id);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = "application/json")
	@ResponseStatus(HttpStatus.OK) 
	public Libro update(@RequestBody Libro user, @PathVariable(value = "id") Integer id) { 
		return libroFacade.update(user);	 
	}
	
	@RequestMapping(method = RequestMethod.POST, consumes = "application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Libro> save(@RequestBody Libro entity)   {
		
			entity.setId(null);
			Integer createdId = -1;
			
			createdId = libroFacade.save(entity);
			if (createdId != null) {		 
				entity.setId(createdId);  
			}
			
			HttpHeaders headers = new HttpHeaders();
			URI locationUri = ServletUriComponentsBuilder.fromCurrentRequest().path("/").path(String.valueOf(entity.getId())).build().toUri();
			headers.setLocation(locationUri);	 
			ResponseEntity<Libro> responseEntity = new ResponseEntity<Libro>(entity, headers, HttpStatus.CREATED);
		
			return responseEntity;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.OK)  
	public void delete(@PathVariable(value = "id") Integer id) {			 
		libroFacade.delete(id);		 
	}
	 
 
}
