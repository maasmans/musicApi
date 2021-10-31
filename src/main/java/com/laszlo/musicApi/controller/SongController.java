package com.laszlo.musicApi.controller;

import com.laszlo.musicApi.model.Song;
import com.laszlo.musicApi.service.SongService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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

    @PostMapping
    Song save(@RequestBody Song song) {
        return songService.save(song);
    }

    @DeleteMapping("/{id}")
    void deleteById(@PathVariable String id) {
        songService.deleteById(Integer.valueOf(id));
    }

    @PutMapping("/{id}")
    public Song updateSong(@PathVariable(value = "id") String id, @RequestBody Song song) {
        return songService.updateSong(Integer.valueOf(id), song);
    }

    @GetMapping("/songsByGenre/{genre}")
    List<Song> songsByGenre(@PathVariable(value = "genre") String genre) {
        return songService.songsByGenre(genre);
    }

    @GetMapping("/songsByYear/{year}")
    List<Song> songsByYear(@PathVariable(value = "year") String year) {
        return songService.songsByYear(Integer.parseInt(year));
    }
    @GetMapping("/bulkInsert")
    public void bulkInsert() {
        songService.bulkInsert();
    }
}
