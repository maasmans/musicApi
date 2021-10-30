package com.laszlo.musicApi.controller;

import com.laszlo.musicApi.model.Artist;
import com.laszlo.musicApi.model.Song;
import com.laszlo.musicApi.service.ArtistService;
import com.laszlo.musicApi.service.SongService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/api/song")
public class SongController {
    private final SongService songService;

    public SongController(SongService songService) {
        this.songService = songService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Collection<Artist>> getSongs() {
        return new ResponseEntity(songService.findAll(), HttpStatus.OK);
    }

    @PostMapping(
        consumes = { MediaType.APPLICATION_JSON_VALUE},
        produces = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity<Song> save(@RequestBody Song song) {
        return ResponseEntity.status(HttpStatus.CREATED).body(songService.save(song));
    }

    @DeleteMapping
    ResponseEntity<Song> delete(@RequestBody Song song) {
        return ResponseEntity.status(HttpStatus.CREATED).body(songService.save(song));
    }
}
