package fi.laiterekisteri.web;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import fi.laiterekisteri.domain.Category;
import fi.laiterekisteri.domain.CategoryRepository;

@RestController
public class RestCategoryController {
		
	@Autowired
	private CategoryRepository crepo;
	
	// REST CATEGORY
	// REST List all Categories
	@GetMapping("/categories/api")
	public Iterable<Category> getCategory() {
		return crepo.findAll();
	}
	
	// REST Find a Category by id
	@GetMapping("/categories/api/{id}")
	public Optional<Category> findCategoryRest(@PathVariable("id") Long categoryId) {
		return crepo.findById(categoryId);
	}
	
	// REST Add a Category
	@PostMapping("/categories/api")
	Category newCategory(@RequestBody Category newCategory) {
		return crepo.save(newCategory);
	}
	
	// REST Update Category
	@PutMapping("/categories/api/{id}")
	Category editCategory(@RequestBody Category editCategory, @PathVariable Long categoryId) {
		editCategory.setCategoryId(categoryId);
		return crepo.save(editCategory);
	}
}
