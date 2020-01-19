package ch.heigvd.videogames.repositories;

import ch.heigvd.videogames.entities.VideogameEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Olivier Liechti on 26/07/17.
 */
@Repository
public interface VideogameRepository extends CrudRepository<VideogameEntity, Integer>{

   // SELECT id,kind,name,supported_on FROM `videogame_entity` INNER JOIN purchase_entity ON purchase_entity.videogames_id = id INNER JOIN user_entity ON user_entity.email = purchase_entity.user_id WHERE user_entity.email = "robel_eteeete@ererer.com"

    @Query("SELECT new ch.heigvd.videogames.entities.VideogameEntity(v.id, v.kind, v.name, v.supportedOn) FROM VideogameEntity v INNER JOIN PurchaseEntity p ON p.videogames_id = v.id INNER JOIN UserEntity e ON p.user_id = e.email WHERE e.email = :userId ")
    List<VideogameEntity> getAllVideoGamesByUser(@Param("userId") String userId);
}
