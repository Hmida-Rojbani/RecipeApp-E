package de.tekup.recipe.data.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import de.tekup.recipe.data.services.RecipeService;
import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class IndexController {
	
	private RecipeService recipeService;
	
	@GetMapping({"","/","index"})
	public String getIndex(Model model) {
		model.addAttribute("recipes", recipeService.getAllRecipe());
		return "index";
	}

}
