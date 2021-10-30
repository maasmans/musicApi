package com.laszlo.musicApi.service;


import com.laszlo.musicApi.model.Song;
import com.laszlo.musicApi.repository.SongRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class SongService {
    private final SongRepository songRepository;

    public SongService(SongRepository serviceRepository) { this.songRepository = serviceRepository; }

    public Collection<Song> findAll() { return songRepository.findAll(); }

    public Song save(Song song) { return songRepository.save(song); }

    public void delete(Song song) { songRepository.delete(song); }


}
