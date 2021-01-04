package de.tekup.recipe.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import de.tekup.recipe.data.models.Ingredient;

public interface IngredientRepository extends JpaRepository<Ingredient, Long>{

}
