package fi.laiterekisteri.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fi.laiterekisteri.domain.Category;
import fi.laiterekisteri.domain.CategoryRepository;

@Service
public class CategoryService {
	 @Autowired
	 private CategoryRepository crepo;
	 
	 // Get the List of Categories
	 public List<Category> getAllCategories(){
		 List<Category> categorylist = (List<Category>)crepo.findAll();
		 return categorylist;
	 }
	 
	 //Get Categories By keyword
	 public List<Category> getByKeyword(String keyword){
		 return crepo.findByKeyword(keyword);
	 }
}
