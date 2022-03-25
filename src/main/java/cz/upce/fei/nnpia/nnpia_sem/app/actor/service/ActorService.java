package cz.upce.fei.nnpia.nnpia_sem.app.actor.service;

import cz.upce.fei.nnpia.nnpia_sem.app.actor.dto.CreateActorDto;
import cz.upce.fei.nnpia.nnpia_sem.app.actor.dto.UpdateActorDto;
import cz.upce.fei.nnpia.nnpia_sem.app.actor.entity.Actor;
import cz.upce.fei.nnpia.nnpia_sem.app.actor.repository.ActorRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActorService {

    @Autowired
    private ActorRepository actorRepository;

    public List<Actor> findAllActors() {
        return actorRepository.findAll();
    }

    public Actor findActor(Long id) {
        return actorRepository.findById(id).orElse(null);
    }

    public Actor addActor(CreateActorDto actorDto) {
        Actor actor = new Actor();
        BeanUtils.copyProperties(actorDto, actor);
        return actorRepository.save(actor);
    }

    public Actor updateActor(UpdateActorDto updateActorDto) {
        Actor actor = new Actor();
        BeanUtils.copyProperties(updateActorDto, actor);
        return actorRepository.save(actor);
    }

    public void deleteActor(Long id) {
        actorRepository.deleteById(id);
    }
}
