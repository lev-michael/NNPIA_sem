package cz.upce.fei.nnpia.nnpia_sem.app.genre.controller;

import cz.upce.fei.nnpia.nnpia_sem.app.config.ApiResponse;
import cz.upce.fei.nnpia.nnpia_sem.app.config.StausEnum;
import cz.upce.fei.nnpia.nnpia_sem.app.genre.entity.Genre;
import cz.upce.fei.nnpia.nnpia_sem.app.genre.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/genre")
@CrossOrigin
public class GenreController {

    @Autowired
    private GenreService genreService;

    @GetMapping("/list")
    public ApiResponse<List<Genre>> findAllGenres() {
        return new ApiResponse<>(HttpStatus.OK.value(), StausEnum.SUCCESS, genreService.getAllGenres());
    }

    @ExceptionHandler({Exception.class})
    public StausEnum handleException() {
        return StausEnum.NOT_FOUND;
    }

}
