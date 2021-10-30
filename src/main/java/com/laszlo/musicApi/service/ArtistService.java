package com.laszlo.musicApi.service;

import com.laszlo.musicApi.model.Artist;
import com.laszlo.musicApi.repository.ArtistRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ArtistService {
    private final ArtistRepository artistRepository;

    public ArtistService(ArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
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
}
