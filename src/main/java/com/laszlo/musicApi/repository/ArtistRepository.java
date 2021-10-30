package com.laszlo.musicApi.repository;

import com.laszlo.musicApi.model.Artist;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Set;

public interface ArtistRepository extends CrudRepository<Artist,Integer> {
    List<Artist> findAll();
    List<Artist> findByName(String name);
}
