package com.itAcademy.ex14diceplayer.service;

import com.itAcademy.ex14diceplayer.model.Game;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface IGameService {

    Iterable<Game> findAll();

    Page<Game> findAll(Pageable pageable);

    Optional<Game> findById(Long id);

    Game save(Game game);

    void deleteById(Long id);


}
