package com.itAcademy.ex14diceplayer.model.sequencegenerator;

import com.itAcademy.ex14diceplayer.model.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;

public class SequencePlayerListener extends AbstractMongoEventListener<Player> {

    @Autowired
    private SequenceGeneratorService sequenceService;

    @Override
    public void onBeforeConvert(BeforeConvertEvent<Player> event) {
        if(event.getSource().getId() < 1) {
            event.getSource().setId(sequenceService.generateSequence(Player.SEQUENCE_NAME));
        }
    }

}
