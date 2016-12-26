package pl.lenda.marcin.wzb.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import pl.lenda.marcin.wzb.entity.ItemsReserved;

import java.util.Collection;

/**
 * Created by Promar on 11.12.2016.
 */
@Repository
public class ItemSearchRepository {

    @Autowired
    MongoTemplate mongoTemplate;

    public Collection searchItems(String text){

        return mongoTemplate.find(Query.query(new Criteria()
                    .orOperator(Criteria.where("contentItem").regex(text, "i"),
                            Criteria.where("detailsContentItem").regex(text, "i"))
                ), ItemsReserved.class);
    }
}
