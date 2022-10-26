package com.MoreiraJunior.cloud.temafinal2.playlistservice.repository;

import com.MoreiraJunior.cloud.temafinal2.playlistservice.model.Playlist;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PlaylistRepository extends MongoRepository<Playlist, String> {
}
