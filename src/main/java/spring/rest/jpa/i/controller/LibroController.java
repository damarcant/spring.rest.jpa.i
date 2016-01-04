package spring.rest.jpa.i.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import spring.rest.jpa.i.bean.Libro;
import spring.rest.jpa.i.facade.LibroFacade;

@RestController("libroController.v1")
@RequestMapping("libros")
public class LibroController {
 
	@Autowired
	private LibroFacade libroFacade;
	 
	@RequestMapping(method = RequestMethod.GET)	 
	public List<Libro> findAll() {		
		return libroFacade.findAll();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)	 
	public Libro findOne(@PathVariable(value = "id") Integer id) {	
		return libroFacade.findOne(id);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = "application/json")
	public Libro update(@RequestBody Libro libro, @PathVariable(value = "id") Integer id) {
		libro.setId(id);
		return libroFacade.update(libro);	 
	}
	
	@RequestMapping(method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<Libro> save(@RequestBody Libro libro)   {
			 
		libro = libroFacade.save(libro);
			
			HttpHeaders headers = new HttpHeaders();
			URI locationUri = ServletUriComponentsBuilder.fromCurrentRequest().path("/").path(String.valueOf(libro.getId())).build().toUri();
			headers.setLocation(locationUri);	 
			ResponseEntity<Libro> responseEntity = new ResponseEntity<Libro>(libro, headers, HttpStatus.CREATED);
		
			return responseEntity;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable(value = "id") Integer id) {			 
		libroFacade.delete(id);		 
	}
 
}
