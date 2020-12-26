package com.itAcademy.ex14diceplayer.repository;

import com.itAcademy.ex14diceplayer.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface IPlayerRepository extends JpaRepository<Player, Long> {

}
