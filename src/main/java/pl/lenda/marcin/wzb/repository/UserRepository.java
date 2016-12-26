package pl.lenda.marcin.wzb.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import pl.lenda.marcin.wzb.entity.UserAccount;

/**
 * Created by Promar on 10.10.2016.
 */
@Repository
public interface UserRepository extends MongoRepository<UserAccount, String> {

    void delete(UserAccount userAccount);
}
