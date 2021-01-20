package com.itAcademy.ex14diceplayer.repository;


import com.itAcademy.ex14diceplayer.model.Player;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPlayerRepository extends MongoRepository<Player, Long> {



}
