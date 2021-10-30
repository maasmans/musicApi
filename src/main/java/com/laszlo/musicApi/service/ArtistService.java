package com.laszlo.musicApi.service;

import com.laszlo.musicApi.model.Artist;
import com.laszlo.musicApi.model.Song;
import com.laszlo.musicApi.repository.ArtistRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
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

    public Artist artistsById(int id) {
        Optional<Artist> artist = artistRepository.findById(id);
        if(artist.isPresent()){
            return artist.get();
        }
        return null;
    }

    public List<Artist> artistsByGenre(String genre) {
        List<Song> songListFromGenre = songService.songsByGenre(genre);
        return songListFromGenre.stream()
                .filter(song -> song.getArtist() != null)
                .map(song -> songArtistMatcherByName(song))
                .collect(Collectors.toList());
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
            System.out.println("Artist with 'song' : '" + song.toString() + "' not found");
            return null;
        }
    }
}
