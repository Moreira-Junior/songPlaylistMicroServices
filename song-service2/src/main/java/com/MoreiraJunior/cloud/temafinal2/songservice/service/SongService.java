package com.MoreiraJunior.cloud.temafinal2.songservice.service;

import com.MoreiraJunior.cloud.temafinal2.songservice.hystrix.*;
import com.MoreiraJunior.cloud.temafinal2.songservice.model.Song;
import com.MoreiraJunior.cloud.temafinal2.songservice.repository.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class SongService {

    @Autowired
    private SongRepository songRepository;

    public Song save(String name){
        return new SaveCommand("save", name, this.songRepository).execute();
    }

    public List<Song> findAll(){
        return new FindAllCommand("findAll", this.songRepository).execute();
    }

    public Optional<Song> findById(String id){
        return new FindByIdCommand("findById", id, this.songRepository).execute();
    }

    public List<Song> findByIds(List<String> ids){
        return new FindByIdsCommand("findByIds", ids, this.songRepository).execute();
    }

    public Optional<Song> findByName(String name){
        return new FindByNameCommand("findByName", name, this.songRepository).execute();
    }

    @Transactional
    public boolean deleteById (String id){
        return new DeleteByIdCommand("deleteById", id, this.songRepository).execute();
    }

    @Transactional
    public boolean delete (Song song){
        return new DeleteCommand("deleteByName", song, this.songRepository).execute();
    }
}
