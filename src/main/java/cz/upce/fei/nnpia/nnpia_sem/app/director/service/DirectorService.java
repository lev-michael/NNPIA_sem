package cz.upce.fei.nnpia.nnpia_sem.app.director.service;

import cz.upce.fei.nnpia.nnpia_sem.app.director.repository.DirectorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DirectorService {
    @Autowired
    private DirectorRepository directorRepository;
}
