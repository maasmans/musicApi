package com.laszlo.musicApi.controller;

import com.laszlo.musicApi.model.Artist;
import com.laszlo.musicApi.model.Song;
import com.laszlo.musicApi.service.ArtistService;
import com.laszlo.musicApi.service.SongService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/api/song")
public class SongController {
    private final SongService songService;

    public SongController(SongService songService) {
        this.songService = songService;
    }

    @GetMapping
    public Collection<Song> getSongs() {
        return songService.findAll();
    }

    @PostMapping(
        consumes = { MediaType.APPLICATION_JSON_VALUE},
        produces = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity<Song> save(@RequestBody Song song) {
        return ResponseEntity.status(HttpStatus.CREATED).body(songService.save(song));
    }

    @DeleteMapping
    Song delete(@RequestBody Song song) {
        return songService.save(song);
    }

    @GetMapping("/songsByGenre/{genre}")
    List<Song> songsByGenre(@PathVariable(value = "genre") String genre) {
        return songService.songsByGenre(genre);
    }

    @GetMapping("/songsByYear/{year}")
    List<Song> songsByYear(@PathVariable(value = "year") String year) {
        return songService.songsByYear(Integer.parseInt(year));
    }
}
