package com.pedrosantos.workshopmongo.resources;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pedrosantos.workshopmongo.domain.Post;
import com.pedrosantos.workshopmongo.resources.util.URL;
import com.pedrosantos.workshopmongo.services.PostService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/posts")
public class PostResource {

	@Autowired
	private PostService service;

	@ApiOperation(value = "Find Post by id")
	@GetMapping(value = "/{id}")
	public ResponseEntity<Post> findById(@PathVariable String id) {
		Post obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}

	@ApiOperation(value = "Find Post by title")
	@GetMapping(value = "/titlesearch")
	public ResponseEntity<List<Post>> findByTitle(@RequestParam(value = "text", defaultValue = "") String text) {
		text = URL.decodeParam(text);
		List<Post> list = service.findByTitle(text);
		return ResponseEntity.ok().body(list);
	}

	@ApiOperation(value = "Return all Posts with pagination")
	@GetMapping(value = "/fullsearch")
	public ResponseEntity<List<Post>> fullSearch(@RequestParam(value = "text", defaultValue = "") String text,
			@RequestParam(value = "minDate", defaultValue = "") String minDate,
			@RequestParam(value = "maxDate", defaultValue = "") String maxDate) {
		text = URL.decodeParam(text);

		Date min = URL.convertDate(minDate, new Date(0L));
		Date max = URL.convertDate(maxDate, new Date());

		LocalDate minLocalDate = min.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		LocalDate maxLocalDate = max.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

		List<Post> list = service.fullSearch(text, minLocalDate, maxLocalDate);
		return ResponseEntity.ok().body(list);
	}
}
