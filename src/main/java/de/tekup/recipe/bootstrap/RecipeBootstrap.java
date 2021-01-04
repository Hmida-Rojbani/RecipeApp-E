package de.tekup.recipe.bootstrap;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import de.tekup.recipe.data.models.Difficulty;
import de.tekup.recipe.data.models.Ingredient;
import de.tekup.recipe.data.models.Recipe;
import de.tekup.recipe.data.models.UnitOfMeasure;
import de.tekup.recipe.data.repositories.IngredientRepository;
import de.tekup.recipe.data.repositories.RecipeRepository;
import de.tekup.recipe.data.repositories.UnitOfMeasureRepository;
import lombok.AllArgsConstructor;

@Component
@Profile({"dev"})
@AllArgsConstructor
public class RecipeBootstrap implements ApplicationListener<ContextRefreshedEvent> {

  
    private final RecipeRepository recipeRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;
    private final IngredientRepository ingredientRepository;

   
    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {
    	loadUom();
        recipeRepository.saveAll(getRecipes());
    }
    
    private void loadUom(){
        UnitOfMeasure uom1 = new UnitOfMeasure();
        uom1.setDescription("Teaspoon");
        unitOfMeasureRepository.save(uom1);

        UnitOfMeasure uom2 = new UnitOfMeasure();
        uom2.setDescription("Tablespoon");
        unitOfMeasureRepository.save(uom2);

        UnitOfMeasure uom3 = new UnitOfMeasure();
        uom3.setDescription("Cup");
        unitOfMeasureRepository.save(uom3);

        UnitOfMeasure uom4 = new UnitOfMeasure();
        uom4.setDescription("Pinch");
        unitOfMeasureRepository.save(uom4);

        UnitOfMeasure uom5 = new UnitOfMeasure();
        uom5.setDescription("Ounce");
        unitOfMeasureRepository.save(uom5);

        UnitOfMeasure uom6 = new UnitOfMeasure();
        uom6.setDescription("Each");
        unitOfMeasureRepository.save(uom6);

        UnitOfMeasure uom7 = new UnitOfMeasure();
        uom7.setDescription("Pint");
        unitOfMeasureRepository.save(uom7);

        UnitOfMeasure uom8 = new UnitOfMeasure();
        uom8.setDescription("Dash");
        unitOfMeasureRepository.save(uom8);
    }

    private List<Recipe> getRecipes() {

        List<Recipe> recipes = new ArrayList<>(2);

        //get UOMs
        Optional<UnitOfMeasure> eachUomOptional = unitOfMeasureRepository.findByDescription("Each");

        if(!eachUomOptional.isPresent()){
            throw new RuntimeException("Expected UOM Not Found");
        }

        Optional<UnitOfMeasure> tableSpoonUomOptional = unitOfMeasureRepository.findByDescription("Tablespoon");

        if(!tableSpoonUomOptional.isPresent()){
            throw new RuntimeException("Expected UOM Not Found");
        }

        Optional<UnitOfMeasure> teaSpoonUomOptional = unitOfMeasureRepository.findByDescription("Teaspoon");

        if(!teaSpoonUomOptional.isPresent()){
            throw new RuntimeException("Expected UOM Not Found");
        }

        Optional<UnitOfMeasure> dashUomOptional = unitOfMeasureRepository.findByDescription("Dash");

        if(!dashUomOptional.isPresent()){
            throw new RuntimeException("Expected UOM Not Found");
        }

        Optional<UnitOfMeasure> pintUomOptional = unitOfMeasureRepository.findByDescription("Pint");

        if(!pintUomOptional.isPresent()){
            throw new RuntimeException("Expected UOM Not Found");
        }

        Optional<UnitOfMeasure> cupsUomOptional = unitOfMeasureRepository.findByDescription("Cup");

        if(!cupsUomOptional.isPresent()){
            throw new RuntimeException("Expected UOM Not Found");
        }

        //get optionals
        UnitOfMeasure eachUom = eachUomOptional.get();
        UnitOfMeasure tableSpoonUom = tableSpoonUomOptional.get();
        UnitOfMeasure teapoonUom = tableSpoonUomOptional.get();
        UnitOfMeasure dashUom = dashUomOptional.get();
        UnitOfMeasure pintUom = dashUomOptional.get();
        UnitOfMeasure cupsUom = cupsUomOptional.get();

        

        //Yummy Guac
        Recipe guacRecipe = new Recipe();
        guacRecipe.setDescription("Perfect Guacamole");
        guacRecipe.setPrepTime(LocalTime.of(0,10));
        guacRecipe.setCookTime(LocalTime.of(0,0));
        guacRecipe.setDifficulty(Difficulty.EASY);
        guacRecipe.setDirections("1 Cut avocado, remove flesh: Cut the avocados in half. Remove seed. Score the inside of the avocado with a blunt knife and scoop out the flesh with a spoon" +
                "\n" +
                "2 Mash with a fork: Using a fork, roughly mash the avocado. (Don't overdo it! The guacamole should be a little chunky.)" +
                "\n" +
                "3 Add salt, lime juice, and the rest: Sprinkle with salt and lime (or lemon) juice. The acid in the lime juice will provide some balance to the richness of the avocado and will help delay the avocados from turning brown.\n" +
                "Add the chopped onion, cilantro, black pepper, and chiles. Chili peppers vary individually in their hotness. So, start with a half of one chili pepper and add to the guacamole to your desired degree of hotness.\n" +
                "Remember that much of this is done to taste because of the variability in the fresh ingredients. Start with this recipe and adjust to your taste.\n" +
                "4 Cover with plastic and chill to store: Place plastic wrap on the surface of the guacamole cover it and to prevent air reaching it. (The oxygen in the air causes oxidation which will turn the guacamole brown.) Refrigerate until ready to serve.\n" +
                "Chilling tomatoes hurts their flavor, so if you want to add chopped tomato to your guacamole, add it just before serving.\n" +
                "\n" +
                "\n" +
                "Read more: http://www.simplyrecipes.com/recipes/perfect_guacamole/#ixzz4jvpiV9Sd");

       

        //very redundent - could add helper method, and make this simpler
        guacRecipe.addIngredient(new Ingredient("ripe avocados", 2.0, eachUom));
        guacRecipe.addIngredient(new Ingredient("Kosher salt", 0.5, teapoonUom));
        guacRecipe.addIngredient(new Ingredient("fresh lime juice or lemon juice", 2.0, tableSpoonUom));
        guacRecipe.addIngredient(new Ingredient("minced red onion or thinly sliced green onion", 2.0, tableSpoonUom));
        guacRecipe.addIngredient(new Ingredient("serrano chiles, stems and seeds removed, minced", 2.0, eachUom));
        guacRecipe.addIngredient(new Ingredient("Cilantro", 2.0, tableSpoonUom));
        guacRecipe.addIngredient(new Ingredient("freshly grated black pepper", 2.0, dashUom));
        guacRecipe.addIngredient(new Ingredient("ripe tomato, seeds and pulp removed, chopped", 0.5, eachUom));

        ingredientRepository.saveAll(guacRecipe.getIngredients());
        

        guacRecipe.setServings(4);
       
        //add to return list
        recipes.add(guacRecipe);

        //Yummy Tacos
        Recipe tacosRecipe = new Recipe();
        tacosRecipe.setDescription("Spicy Grilled Chicken Taco");
        tacosRecipe.setCookTime(LocalTime.of(0,9));
        tacosRecipe.setPrepTime(LocalTime.of(0,20));
        tacosRecipe.setDifficulty(Difficulty.MODERATE);

        tacosRecipe.setDirections("1 Prepare a gas or charcoal grill for medium-high, direct heat.\n" +
                "2 Make the marinade and coat the chicken: In a large bowl, stir together the chili powder, oregano, cumin, sugar, salt, garlic and orange zest. Stir in the orange juice and olive oil to make a loose paste. Add the chicken to the bowl and toss to coat all over.\n" +
                "Set aside to marinate while the grill heats and you prepare the rest of the toppings.\n" +
                "\n" +
                "\n" +
                "3 Grill the chicken: Grill the chicken for 3 to 4 minutes per side, or until a thermometer inserted into the thickest part of the meat registers 165F. Transfer to a plate and rest for 5 minutes.\n" +
                "4 Warm the tortillas: Place each tortilla on the grill or on a hot, dry skillet over medium-high heat. As soon as you see pockets of the air start to puff up in the tortilla, turn it with tongs and heat for a few seconds on the other side.\n" +
                "Wrap warmed tortillas in a tea towel to keep them warm until serving.\n" +
                "5 Assemble the tacos: Slice the chicken into strips. On each tortilla, place a small handful of arugula. Top with chicken slices, sliced avocado, radishes, tomatoes, and onion slices. Drizzle with the thinned sour cream. Serve with lime wedges.\n" +
                "\n" +
                "\n" +
                "Read more: http://www.simplyrecipes.com/recipes/spicy_grilled_chicken_tacos/#ixzz4jvtrAnNm");

       
        tacosRecipe.addIngredient(new Ingredient("Ancho Chili Powder", 2.0, tableSpoonUom));
        tacosRecipe.addIngredient(new Ingredient("Dried Oregano", 1.0, teapoonUom));
        tacosRecipe.addIngredient(new Ingredient("Dried Cumin", 1.0, teapoonUom));
        tacosRecipe.addIngredient(new Ingredient("Sugar", 1.0, teapoonUom));
        tacosRecipe.addIngredient(new Ingredient("Salt",0.5, teapoonUom));
        tacosRecipe.addIngredient(new Ingredient("Clove of Garlic, Choppedr", 1.0, eachUom));
        tacosRecipe.addIngredient(new Ingredient("finely grated orange zestr", 1.0, tableSpoonUom));
        tacosRecipe.addIngredient(new Ingredient("fresh-squeezed orange juice", 3.0, tableSpoonUom));
        tacosRecipe.addIngredient(new Ingredient("Olive Oil", 2.0, tableSpoonUom));
        tacosRecipe.addIngredient(new Ingredient("boneless chicken thighs", 4.0, tableSpoonUom));
        tacosRecipe.addIngredient(new Ingredient("small corn tortillasr", 8.0, eachUom));
        tacosRecipe.addIngredient(new Ingredient("packed baby arugula", 3.0, cupsUom));
        tacosRecipe.addIngredient(new Ingredient("medium ripe avocados, slic", 2.0, eachUom));
        tacosRecipe.addIngredient(new Ingredient("radishes, thinly sliced", 4.0, eachUom));
        tacosRecipe.addIngredient(new Ingredient("cherry tomatoes, halved", 0.5, pintUom));
        tacosRecipe.addIngredient(new Ingredient("red onion, thinly sliced", 0.25, eachUom));
        tacosRecipe.addIngredient(new Ingredient("Roughly chopped cilantro", 4.0, eachUom));
        tacosRecipe.addIngredient(new Ingredient("cup sour cream thinned with 1/4 cup milk", 4.0, cupsUom));
        tacosRecipe.addIngredient(new Ingredient("lime, cut into wedges", 4.0, eachUom));

        ingredientRepository.saveAll(tacosRecipe.getIngredients());

        tacosRecipe.setServings(4);
      
        recipes.add(tacosRecipe);
        return recipes;
    }
}
