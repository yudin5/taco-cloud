package springinaction5.tacocloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import springinaction5.tacocloud.domain.Ingredient;
import springinaction5.tacocloud.domain.Ingredient.Type;
import springinaction5.tacocloud.domain.Order;
import springinaction5.tacocloud.domain.Taco;
import springinaction5.tacocloud.repository.IngredientRepository;
import springinaction5.tacocloud.repository.TacoRepository;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("order")
public class DesignTacoController {

    private final IngredientRepository ingredientRepo;
    private final TacoRepository designRepo;

    @ModelAttribute(name = "order")
    public Order order() {
        return new Order();
    }

    @ModelAttribute(name = "taco")
    public Taco taco() {
        return new Taco();
    }

    @Autowired
    public DesignTacoController(IngredientRepository ingredientRepo,
                                TacoRepository designRepo) {
        this.ingredientRepo = ingredientRepo;
        this.designRepo = designRepo;
    }

    // http://localhost:8080/design
    @GetMapping
    public String showDesignForm(Model model) {

        List<Ingredient> ingredients = new ArrayList<>();
        ingredientRepo.findAll().forEach(ingredients::add);

        Type[] types = Ingredient.Type.values();
        for (Type type : types) {
            model.addAttribute(type.toString().toLowerCase(),
                    filterByType(ingredients, type));
        }
        model.addAttribute("design", new Taco());
        return "design";
    }

    @PostMapping
    public String processDesign(@Valid Taco design,
                                Errors errors,
                                @ModelAttribute Order order
//                                Model model
    ) {

        if (errors.hasErrors()) {
//            log.info("Ошибки: {}", errors);
//
//            // В случае ошибок перерисовываем дизайн
//            List<Ingredient> ingredients = new ArrayList<>();
//            ingredientRepo.findAll().forEach(ingredients::add);
//
//            Type[] types = Ingredient.Type.values();
//            for (Type type : types) {
//                model.addAttribute(type.toString().toLowerCase(),
//                        ingredients.stream().filter(p -> p.getType().equals(type)).collect(Collectors.toList()));
//            }
            return "design";
        }

        Taco saved = designRepo.save(design);
        order.addDesign(saved);

        // Save the taco design...
        // We'll do this in chapter 3
        log.info("Processing design: " + design);
        return "redirect:/orders/current";
    }

    private List<Ingredient> filterByType(List<Ingredient> ingredients, Type type) {
        return ingredients
                .stream()
                .filter(ingredient -> ingredient.getType().equals(type))
                .collect(Collectors.toList());
    }

}
