package springinaction5.tacocloud.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import springinaction5.tacocloud.domain.Ingredient;

// Без этого класса не работает POST processDesign(), так как приходит строка (id ингредиента), а нужен сам Ingredient
@Component
public class IngredientByIdConverter implements Converter<String, Ingredient> {

    private final IngredientRepository ingredientRepo;

    @Autowired
    public IngredientByIdConverter(IngredientRepository ingredientRepo) {
        this.ingredientRepo = ingredientRepo;
    }

    @Override
    public Ingredient convert(@Nullable String id) {
        if (id == null) {
            return null;
        }
        return ingredientRepo.findById(id);
    }

}
