package com.laszlo.musicapi.service;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.laszlo.musicapi.model.Artist;
import com.laszlo.musicapi.model.Song;
import com.laszlo.musicapi.repository.ArtistRepository;
import com.laszlo.musicapi.utility.JsonUtility;
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

    public Artist save(Artist newArtist) {
        String newArtistName = newArtist.getName();
        boolean sameNameFound = findAll().stream()
                .filter(artist -> artist.getName() != null)
                .anyMatch(artist -> artist.getName().equalsIgnoreCase(newArtistName));
        if(sameNameFound){
            return null;
        }
        return artistRepository.save(newArtist);
    }

    public Iterable<Artist> saveAll(Iterable<Artist> artists) {
        return artistRepository.saveAll(artists);
    }

    public List<Artist> artistsByName(String name) {
        return artistRepository.findByName(name);
    }

    /**
     * This method gets all the songs that have a specified genre and returns the matching artists.
     */
    public Set<Artist> artistsByGenre(String genre) {
        List<Song> songListFromGenre = songService.songsByGenre(genre);
        return songListFromGenre.stream()
                .filter(song -> song.getArtist() != null)
                .map(song -> songArtistMatcherByName(song))
                .filter(artist -> artist != null)
                .collect(Collectors.toSet());
    }

    /**
     * Now when an artist is not found it returns null, possible to if not found to create a new artist and then return.
     */
    public Artist songArtistMatcherByName(Song song) {
        String artistNameOfSong = song.getArtist();
        List<Artist> artists = artistRepository.findAll();
        Optional<Artist> matchingArtist = artists.stream()
                .filter(artist -> artistNameOfSong.equalsIgnoreCase(artist.getName()))
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

    /**
     * Here there is a check if the new artistName isn't already in the database. If not then the artist with the corresponding
     * id will be updated. If the id doesn't match any artist in the database than the new entry will be saved.
     */
    public Artist updateArtist(Integer id, Artist updatedArtist) {
        String nameUpdated = updatedArtist.getName();
        boolean sameNameFound = findAll().stream()
                .filter(artist -> artist.getName() != null)
                .anyMatch(artist -> artist.getName().equalsIgnoreCase(nameUpdated));
        if(nameUpdated == null || sameNameFound){
            return null;
        }
        Optional<Artist> optionalDbArtist = artistRepository.findById(id);
        if(optionalDbArtist.isPresent()){
            Artist dbArtist = optionalDbArtist.get();
            dbArtist.setName(nameUpdated);
            return artistRepository.save(dbArtist);
        }
        return artistRepository.save(updatedArtist);
    }
}
