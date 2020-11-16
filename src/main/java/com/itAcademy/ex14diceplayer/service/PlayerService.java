package com.itAcademy.ex14diceplayer.service;


import com.itAcademy.ex14diceplayer.repository.IGameRepository;
import com.itAcademy.ex14diceplayer.repository.IPlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlayerService  {

    @Autowired
    private IGameRepository gameRepository;

    @Autowired
    private IPlayerRepository playerRepository;


}
