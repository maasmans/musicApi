package com.laszlo.musicapi.repository;

import com.laszlo.musicapi.model.Artist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ArtistRepository extends JpaRepository<Artist,Integer> {
    List<Artist> findAll();
    List<Artist> findByName(String name);
}
