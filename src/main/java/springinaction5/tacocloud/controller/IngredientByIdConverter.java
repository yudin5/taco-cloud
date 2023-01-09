package springinaction5.tacocloud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import springinaction5.tacocloud.domain.Ingredient;
import springinaction5.tacocloud.repository.IngredientRepository;

import java.util.Optional;

// Без этого класса не работает POST processDesign(), так как приходит строка (id ингредиента), а нужен сам Ingredient
@Component
public class IngredientByIdConverter implements Converter<String, Ingredient> {

    private IngredientRepository ingredientRepo;

    @Autowired
    public IngredientByIdConverter(IngredientRepository ingredientRepo) {
        this.ingredientRepo = ingredientRepo;
    }

    @Override
    public Ingredient convert(String id) {
        Optional<Ingredient> optionalIngredient = ingredientRepo.findById(id);
        return optionalIngredient.orElse(null);
    }

}
