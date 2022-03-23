package cz.upce.fei.nnpia.nnpia_sem.app.actor.controller;

import cz.upce.fei.nnpia.nnpia_sem.app.actor.service.ActorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping(value = "/actor")
public class ActorController {

    @Autowired
    private ActorService actorService;

    @GetMapping("/list")
    private void findAllActors() {
    }

    @GetMapping("/{id}")
    private void findActor(@PathVariable Long id) {
    }
}
