package com.itAcademy.ex14diceplayer.service;

import com.itAcademy.ex14diceplayer.model.Player;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface IPlayerService {

    Iterable<Player> findAll();

    Page<Player> findAll(Pageable pageable);

    Optional<Player> findById(Long id);

    Player save(Player player);

    void deleteById(Long id);

}
