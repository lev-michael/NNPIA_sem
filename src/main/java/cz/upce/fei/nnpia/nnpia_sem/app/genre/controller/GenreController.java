package cz.upce.fei.nnpia.nnpia_sem.app.genre.controller;

import cz.upce.fei.nnpia.nnpia_sem.app.config.ApiResponse;
import cz.upce.fei.nnpia.nnpia_sem.app.config.StausEnum;
import cz.upce.fei.nnpia.nnpia_sem.app.genre.entity.Genre;
import cz.upce.fei.nnpia.nnpia_sem.app.genre.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/genre")
@CrossOrigin
public class GenreController {

    @Autowired
    private GenreService genreService;

    @GetMapping("/list")
    private ApiResponse<List<Genre>> findAllGenres() {
        return new ApiResponse<>(HttpStatus.OK.value(), StausEnum.SUCCESS, genreService.getAllGenres());
    }

}
