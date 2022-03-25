package cz.upce.fei.nnpia.nnpia_sem.app.actor.controller;

import cz.upce.fei.nnpia.nnpia_sem.app.actor.dto.CreateActorDto;
import cz.upce.fei.nnpia.nnpia_sem.app.actor.dto.UpdateActorDto;
import cz.upce.fei.nnpia.nnpia_sem.app.actor.entity.Actor;
import cz.upce.fei.nnpia.nnpia_sem.app.actor.service.ActorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping(value = "/actor")
public class ActorController {

    @Autowired
    private ActorService actorService;

    @GetMapping("/list")
    private List<Actor> findAllActors() {
        return actorService.findAllActors();
    }

    @GetMapping("/{id}")
    private Actor findActor(@PathVariable Long id) {
        return actorService.findActor(id);
    }

    @PostMapping("/add")
    private Actor addActor(@RequestBody() CreateActorDto createActorDto) {
        return actorService.addActor(createActorDto);
    }


    @PostMapping("/update")
    private Actor updateActor(@RequestBody() UpdateActorDto updateActorDto) {
        return actorService.updateActor(updateActorDto);
    }

    @PostMapping("/delete")
    private void deleteActor(@RequestBody() Long id) {
        actorService.deleteActor(id);
    }

}
