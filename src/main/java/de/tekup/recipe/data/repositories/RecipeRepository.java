package de.tekup.recipe.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import de.tekup.recipe.data.models.Recipe;

public interface RecipeRepository extends JpaRepository<Recipe, Long>{

}
