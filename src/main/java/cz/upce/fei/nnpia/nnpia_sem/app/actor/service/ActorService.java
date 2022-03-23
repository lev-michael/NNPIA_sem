package cz.upce.fei.nnpia.nnpia_sem.app.actor.service;

import cz.upce.fei.nnpia.nnpia_sem.app.actor.repository.ActorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActorService {

    @Autowired
    private ActorRepository actorRepository;
}
