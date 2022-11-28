package springinaction5.tacocloud.repository;

import org.springframework.data.repository.CrudRepository;
import springinaction5.tacocloud.domain.Ingredient;

public interface IngredientRepository extends CrudRepository<Ingredient, String> {
}
