package com.MoreiraJunior.cloud.temafinal2.songservice.repository;

import com.MoreiraJunior.cloud.temafinal2.songservice.model.Song;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;

public interface SongRepository extends MongoRepository <Song, String> {

    @Query("{'name': ?0}")
    Optional<Song> findByName(String name);

}
