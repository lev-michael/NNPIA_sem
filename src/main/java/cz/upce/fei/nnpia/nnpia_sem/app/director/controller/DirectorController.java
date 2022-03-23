package cz.upce.fei.nnpia.nnpia_sem.app.director.controller;

import cz.upce.fei.nnpia.nnpia_sem.app.director.service.DirectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping(value = "/director")
public class DirectorController {

    @Autowired
    private DirectorService directorService;

    @GetMapping("/list")
    private void findAllDirectors() {
    }

    @GetMapping("/{id}")
    private void findDirector(@PathVariable Long id) {
    }
}