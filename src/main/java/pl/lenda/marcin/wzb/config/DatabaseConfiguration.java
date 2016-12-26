package pl.lenda.marcin.wzb.config;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * Created by promar7 on 17.07.16.
 */
@Configuration
@EnableMongoRepositories("pl.lenda.marcin.wzb.repository")
public class DatabaseConfiguration extends AbstractMongoConfiguration{

    @Value("${spring.data.mongodb.host}")
    private String host;

    @Value("${spring.data.mongodb.port}")
    private Integer port;

    @Value("${spring.data.mongodb.database}")
    private String dbname;

    @Override
    protected String getDatabaseName() {
        return dbname;
    }

    @Override
    public Mongo mongo() throws Exception {
        return new MongoClient(host+":"+port);
    }
}
