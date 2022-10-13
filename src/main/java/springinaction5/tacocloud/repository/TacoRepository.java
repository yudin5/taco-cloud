package springinaction5.tacocloud.repository;

import springinaction5.tacocloud.domain.Taco;

public interface TacoRepository {

    Taco save(Taco design);

}
