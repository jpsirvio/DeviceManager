package fi.laiterekisteri.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import fi.laiterekisteri.domain.Category;
import fi.laiterekisteri.domain.CategoryRepository;

@Controller
public class CategoryController {
	
	@Autowired
	private CategoryRepository crepo;
	
	// List all Categories
	@GetMapping("/categories")
	public String list(Model model) {
		model.addAttribute("categories", crepo.findAll());
		return "categories";
	}
	
    // Add a new Category
    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value= {"/categories/addcategory"})
    public String addCategory(Model model) {	
        model.addAttribute("category", new Category());
        return "addcategory";
    }
    
    // Save a new added Category
    @RequestMapping(value = "/categories/savecategory", method = RequestMethod.POST)
    public String save(Category category){
        crepo.save(category);
        return "redirect:/categories";
    }
    
    // Delete Category
    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/categories/delete/{id}", method = RequestMethod.GET)
    public String deleteCategory(@PathVariable("id") Long categoryId, Model model) {
    	crepo.deleteById(categoryId);
        return "redirect:/categories";
    }
    
    // Edit Category
    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value= {"/categories/editcategory/{categoryId}"}, method = RequestMethod.GET)
    public String editCategory(@PathVariable("categoryId") Long categoryId, Model model) {	
        model.addAttribute("editCategory", crepo.findById(categoryId));
        return "editcategory";
    }
}
