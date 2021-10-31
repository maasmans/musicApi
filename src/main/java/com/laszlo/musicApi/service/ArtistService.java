package com.laszlo.musicApi.service;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.laszlo.musicApi.model.Artist;
import com.laszlo.musicApi.model.Song;
import com.laszlo.musicApi.repository.ArtistRepository;
import com.laszlo.musicApi.utility.JsonUtility;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ArtistService {
    private final ArtistRepository artistRepository;
    private final SongService songService;

    public ArtistService(ArtistRepository artistRepository,
            SongService songService) {
        this.artistRepository = artistRepository;
        this.songService = songService;
    }

    public Collection<Artist> findAll() {
        return artistRepository.findAll();
    }

    public Artist save(Artist artist) {
        return artistRepository.save(artist);
    }

    public Iterable<Artist> saveAll(Iterable<Artist> artists) {
        return artistRepository.saveAll(artists);
    }

    public List<Artist> artistsByName(String name) {
        return artistRepository.findByName(name);
    }

    /**
     * This one is a bit counter intuitive. You would expect that artists would have a genre. This is not the case so
     * this method gets all the songs that have the specified genre and returns the matching artists.
     */
    public Set<Artist> artistsByGenre(String genre) {
        List<Song> songListFromGenre = songService.songsByGenre(genre);
        return songListFromGenre.stream()
                .filter(song -> song.getArtist() != null)
                .map(song -> songArtistMatcherByName(song))
                .filter(artist -> artist != null)
                .collect(Collectors.toSet());
    }

    public Artist songArtistMatcherByName(Song song) {
        String artistNameOfSong = song.getArtist();
        List<Artist> artists = artistRepository.findAll();
        Optional<Artist> matchingArtist = artists.stream()
                .filter(artist -> artist.getName() != null)
                .filter(artist -> artistNameOfSong.toLowerCase().contains(artist.getName().toLowerCase()))
                .findFirst();
        if (matchingArtist.isPresent()) {
            return matchingArtist.get();
        }else{
            return null;
        }
    }

    public void bulkInsert() {
        Gson serialiser = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
                .create();
        Type targetClassType = new TypeToken<ArrayList<Artist>>() { }.getType();

        String json = JsonUtility.readFileAsString(JsonUtility.ARTISTSPATH);
        List<Artist> toInsert = serialiser.fromJson(json,targetClassType);
        if(toInsert.size() > 0) {
            toInsert.stream().forEach(x->x.setId(null));
            artistRepository.saveAll(toInsert);
        }
    }

    public void deleteById(Integer id) {
        artistRepository.deleteById(id);
    }

    public Artist updateArtist(Integer id, Artist updatedArtist) {
        Optional<Artist> optionalDbArtist = artistRepository.findById(id);
        if(optionalDbArtist.isPresent()){
            Artist dbArtist = optionalDbArtist.get();
            if(updatedArtist.getName() != null){
                dbArtist.setName(updatedArtist.getName());
            }
            return artistRepository.save(dbArtist);
        }
        return artistRepository.save(updatedArtist);
    }
}
