package com.itAcademy.ex14diceplayer.repository;

import com.itAcademy.ex14diceplayer.model.Game;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IGameRepository extends MongoRepository<Game, Long> {

    @Query("{ 'player_id' : ?0 }")
    List<Game> findAllByPlayer(Long player_id);

}
