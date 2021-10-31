package com.laszlo.musicApi.controller;

import com.laszlo.musicApi.model.Artist;
import com.laszlo.musicApi.service.ArtistService;
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
@RequestMapping("/api/artist")
public class ArtistController {
    private final ArtistService artistService;

    public ArtistController(ArtistService artistService) {
        this.artistService = artistService;
    }

    @GetMapping
    public Collection<Artist> getArtists() {
        return artistService.findAll();
    }

    @PostMapping()
    Artist save(@RequestBody Artist artist) {
        return artistService.save(artist);
    }

    @DeleteMapping("/{id}")
    void deleteById(@PathVariable String id) {
        artistService.deleteById(Integer.valueOf(id));
    }

    @PutMapping("/{id}")
    public Artist updateArtist(@PathVariable(value = "id") String id, @RequestBody Artist artist) {
        return artistService.updateArtist(Integer.valueOf(id), artist);
    }

    @GetMapping("/artistByGenre/{genre}")
    public List<Artist> getArtistsByGenre(@PathVariable(value = "genre") String genre){
        List<Artist> artists = artistService.artistsByGenre(genre);
        return artists;
    }

    @GetMapping("/bulkInsert")
    public void bulkInsert() {
            artistService.bulkInsert();
    }

}
