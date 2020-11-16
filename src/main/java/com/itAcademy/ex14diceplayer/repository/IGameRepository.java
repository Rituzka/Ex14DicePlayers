package com.itAcademy.ex14diceplayer.repository;

import com.itAcademy.ex14diceplayer.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IGameRepository extends JpaRepository<Game, Long> {
}
