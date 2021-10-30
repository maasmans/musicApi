package com.laszlo.musicApi.controller;

import com.laszlo.musicApi.model.Artist;
import com.laszlo.musicApi.service.ArtistService;
import com.laszlo.musicApi.service.JsonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/artist")
public class ArtistController {
    private final ArtistService artistService;
    private final JsonService jsonService;

    public ArtistController(ArtistService artistService, JsonService jsonService) {
        this.artistService = artistService;
        this.jsonService = jsonService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Collection<Artist>> getArtists() {
        return new ResponseEntity(artistService.findAll(), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Artist> save(@RequestBody Artist artist) {
        return ResponseEntity.status(HttpStatus.CREATED).body(artistService.save(artist));
    }

    @GetMapping("/artistById/{id}")
    public Artist getArtistById(@PathVariable(value = "id") String artistId){
        Artist artist = artistService.artistsById(Integer.parseInt(artistId));
        return artist;
    }

    @GetMapping("/artistByName/{name}")
    public List<Artist> getArtistByName(@PathVariable(value = "name") String artistName){
        List<Artist> artist = artistService.artistsByName(artistName);
        return artist;
    }

//    @GetMapping("/artistByGenre/{genre}")
//    public Set<Artist> getArtistsByGenre(@PathVariable(value = "genre") String genre){
//        Set<Artist> artists = artistService.getArtistsByGenre(genre);
//        for(Artist artist :artists) System.out.println(artist.Name);
//        return artists;
//    }
//    @PostMapping("/fillDB")
//    public ResponseEntity<List<Artist>> save(@RequestBody List<Artist> artists) {
//        for(Artist artist : artists){
//            artistService.save(artist);
//        }
//        return ResponseEntity.status(HttpStatus.CREATED).body(artists);
//    }

    @GetMapping("/fillDB")
    public void fillDB(){
        //jsonService.fillDB();
        System.out.println("works");
    }
}
