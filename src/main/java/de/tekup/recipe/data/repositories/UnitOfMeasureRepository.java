package de.tekup.recipe.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import de.tekup.recipe.data.models.UnitOfMeasure;

public interface UnitOfMeasureRepository extends JpaRepository<UnitOfMeasure, Long>{

}
