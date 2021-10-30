package com.laszlo.musicApi.service;

import com.laszlo.musicApi.model.Song;
import com.laszlo.musicApi.repository.SongRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SongService {
    private final SongRepository songRepository;

    public SongService(SongRepository serviceRepository) { this.songRepository = serviceRepository; }

    public Collection<Song> findAll() { return songRepository.findAll(); }

    public Song save(Song song) { return songRepository.save(song); }

    public void delete(Song song) { songRepository.delete(song); }

    public List<Song> songsByGenre(String genre) {
        List<Song> songList = songRepository.findAll();
        return songList.stream()
                .filter(song -> song.getGenre() != null)
                .filter(song -> song.getGenre().equalsIgnoreCase(genre))
                .collect(Collectors.toList());
    }

    public List<Song> songsByYear(int year){
        return songRepository.findByYear(year);
    }
}
