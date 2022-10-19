package fi.laiterekisteri.web;

import java.util.List;

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
import fi.laiterekisteri.service.CategoryService;

@Controller
public class CategoryController {
	
	@Autowired
	private CategoryRepository crepo;
	@Autowired
	private CategoryService cservice;
	
	/* Old version
	// List all Categories
	@GetMapping("/categories")
	public String list(Model model) {
		model.addAttribute("categories", crepo.findAll());
		return "categories";
	}
	*/
	
	// List and search categories
	@GetMapping(value = {"/categories","/categories/search"})
	public String listcategories(Category category, Model model, String keyword) {
		model.addAttribute("categories", crepo.findAll());
		if(keyword!=null) {
			List<Category> categorylist = cservice.getByKeyword(keyword);
			model.addAttribute("categorylist", categorylist);
		} else {
			List<Category> categorylist = cservice.getAllCategories();
			model.addAttribute("categorylist", categorylist);
		} return "categories";
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
    
    // Soft Delete category
    // See SQLDelete statement in class object
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
