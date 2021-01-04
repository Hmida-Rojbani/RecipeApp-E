package de.tekup.recipe.data.models;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Recipe {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(length = 50)
	private String description;

	private Integer servings;

	private LocalTime prepTime;

	private LocalTime cookTime;
	@Enumerated(EnumType.STRING)
	private Difficulty difficulty;
	@Lob
	private String directions;

	@OneToMany(mappedBy = "recipe")
	List<Ingredient> ingredients = new ArrayList<>();

	public Recipe addIngredient(Ingredient ingredient) {
		ingredient.setRecipe(this);
		this.ingredients.add(ingredient);
		return this;
	}
}
