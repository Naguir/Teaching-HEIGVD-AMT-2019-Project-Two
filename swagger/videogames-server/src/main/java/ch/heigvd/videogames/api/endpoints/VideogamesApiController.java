package ch.heigvd.videogames.api.endpoints;

import ch.heigvd.videogames.api.VideogamesApi;
import ch.heigvd.videogames.api.model.InlineObject;
import ch.heigvd.videogames.entities.PurchaseEntity;
import ch.heigvd.videogames.entities.UserEntity;
import ch.heigvd.videogames.entities.VideogameEntity;
import ch.heigvd.videogames.api.model.Videogame;
import ch.heigvd.videogames.repositories.PurchaseRepository;
import ch.heigvd.videogames.repositories.UserRepository;
import ch.heigvd.videogames.repositories.VideogameRepository;
import ch.heigvd.videogames.utils.VideoGamesUtils;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-07-26T19:36:34.802Z")

@Controller
public class VideogamesApiController implements VideogamesApi {

    @Autowired
    VideogameRepository videogameRepository;
    @Autowired
    PurchaseRepository purchaseRepository;
    @Autowired
    UserRepository userRepository;

    VideoGamesUtils videoGamesUtils = new VideoGamesUtils();

    private final HttpServletRequest req;

    @org.springframework.beans.factory.annotation.Autowired
    public VideogamesApiController(HttpServletRequest req) {
        this.req = req;
    }

    public ResponseEntity<Object> createVideogame(@ApiParam(value = "", required = true) @Valid @RequestBody Videogame videogame) {
        VideogameEntity newVideogameEntity = toVideogameEntity(videogame);
        videogameRepository.save(newVideogameEntity);

        String token = req.getHeader("Authorization");
        long id = newVideogameEntity.getId();
        String user = videoGamesUtils.getMailByToken(token);

        PurchaseEntity purchaseEntity = new PurchaseEntity();
        purchaseEntity.setUser_id(user);
        purchaseEntity.setVideoGames_id((int)id);

        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(user);


        userRepository.save(userEntity);
        purchaseRepository.save(purchaseEntity);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(newVideogameEntity.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    public ResponseEntity<List<Videogame>> getVideogames() {
        List<Videogame> videogames = new ArrayList<>();

        String token = req.getHeader("Authorization");
        String user = videoGamesUtils.getMailByToken(token);

        Iterable<VideogameEntity> vg = videogameRepository.getAllVideoGamesByUser(user);

        for (VideogameEntity videogameEntity : vg) {
            videogames.add(toVideogame(videogameEntity));
        }

        return ResponseEntity.ok(videogames);
    }


    public ResponseEntity<Videogame> deleteVideogame(@ApiParam(value = "" ,required=true )  @Valid @RequestBody InlineObject videogames) {
        videogameRepository.deleteById(videogames.getId());
        return ResponseEntity.ok().build();
    }


    private VideogameEntity toVideogameEntity(Videogame videogame) {
        VideogameEntity entity = new VideogameEntity();
        entity.setKind(videogame.getKind());
        entity.setName(videogame.getName());
        entity.setSupportedOn(videogame.getSupportedOn());
        return entity;
    }

    private Videogame toVideogame(VideogameEntity entity) {
        Videogame videogame = new Videogame();
        videogame.setId(entity.getId());
        videogame.setKind(entity.getKind());
        videogame.setName(entity.getName());
        videogame.setSupportedOn(entity.getSupportedOn());
        return videogame;
    }

}
