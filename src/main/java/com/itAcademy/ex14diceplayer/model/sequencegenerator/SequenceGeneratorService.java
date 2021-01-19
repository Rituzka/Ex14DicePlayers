package com.itAcademy.ex14diceplayer.model.sequencegenerator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.Objects;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

@Service
public class SequenceGeneratorService {

    private final MongoOperations mongoOperations;

    @Autowired
    public SequenceGeneratorService(MongoOperations mongoOperations){
        this.mongoOperations = mongoOperations;
    }

    public Long generateSequence(String seqName){
        Sequence counter = mongoOperations.findAndModify(
                query(where("_id").is(seqName)),
                new Update().inc("value",1),
                options().returnNew(true).upsert(true),
                Sequence.class);

        return !Objects.isNull(counter) ? counter.getValue() : 1;

    }
}
