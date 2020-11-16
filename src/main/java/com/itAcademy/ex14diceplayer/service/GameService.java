package com.itAcademy.ex14diceplayer.service;

import com.itAcademy.ex14diceplayer.repository.IGameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameService {

    @Autowired
    private IGameRepository gameRepository;
}
