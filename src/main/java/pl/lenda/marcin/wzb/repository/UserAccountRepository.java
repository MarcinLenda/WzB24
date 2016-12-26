package pl.lenda.marcin.wzb.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import pl.lenda.marcin.wzb.entity.UserAccount;

import java.util.List;

/**
 * Created by Promar on 06.11.2016.
 */
public interface UserAccountRepository extends MongoRepository<UserAccount, String> {

    List<UserAccount> findByRole(String role);

    UserAccount findByUsername(String username);

    UserAccount findByNameAndSurname(String name, String surname);

    UserAccount findByUsernameAndActiveTrue(String username);

    UserAccount findByNumberUser(String numberUser);

    List<UserAccount> findAllByActiveTrue();

    List<UserAccount> findAllByActiveFalse();
}
