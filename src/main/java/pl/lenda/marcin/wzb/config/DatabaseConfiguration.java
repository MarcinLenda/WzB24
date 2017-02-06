package pl.lenda.marcin.wzb.config;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.Collections;

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

    @Value("${spring.data.mongodb.username}")
    private String username;

    @Value("${spring.data.mongodb.password}")
    private String password;

    @Value("${spring.data.mongodb.authentication.database}")
    private String authenticationDatabase;

    @Override
    protected String getDatabaseName() {
        return dbname;
    }

    @Override
    public Mongo mongo() throws Exception {
        MongoCredential credentials = MongoCredential
                .createCredential(username, authenticationDatabase, password.toCharArray());
        ServerAddress server = new ServerAddress(host, port);
        return new MongoClient(server, Collections.singletonList(credentials));
    }
}
