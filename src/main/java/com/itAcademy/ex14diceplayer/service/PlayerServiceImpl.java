package com.itAcademy.ex14diceplayer.service;


import com.itAcademy.ex14diceplayer.model.Player;
import com.itAcademy.ex14diceplayer.repository.IPlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class PlayerServiceImpl implements IPlayerService {
    @Autowired
    private IPlayerRepository playerRepository;

    @Override
    @Transactional(readOnly = true)
    public Iterable<Player> findAll() {
        return playerRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Player> findAll(Pageable pageable) {
        return playerRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Player> findById(Long id) {
        return playerRepository.findById(id);
    }

    @Override
    @Transactional
    public Player save(Player player) {
        return playerRepository.save(player);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        playerRepository.deleteById(id);

    }
}
