package com.itAcademy.ex14diceplayer.model.sequencegenerator;

import com.itAcademy.ex14diceplayer.model.Game;
import com.itAcademy.ex14diceplayer.model.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;

public class SequenceGameListener extends AbstractMongoEventListener<Game> {
    @Autowired
    private SequenceGeneratorService sequenceService;

    @Override
    public void onBeforeConvert(BeforeConvertEvent<Game> event) {
        if(event.getSource().getId() < 1) {
            event.getSource().setId(sequenceService.generateSequence(Player.SEQUENCE_NAME));
        }
    }
}
