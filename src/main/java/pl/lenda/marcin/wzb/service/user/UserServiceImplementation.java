package pl.lenda.marcin.wzb.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.lenda.marcin.wzb.entity.UserAccount;
import pl.lenda.marcin.wzb.repository.UserRepository;

import java.util.List;

/**
 * Created by Promar on 28.10.2016.
 */
@Service
public class UserServiceImplementation implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImplementation(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserAccount registerUser(UserAccount newUserAccount) {
        userRepository.save(newUserAccount);
        return newUserAccount;
    }

    @Override
    public UserAccount updateAccount(UserAccount updateAccount) {
        userRepository.save(updateAccount);
        return updateAccount;
    }

    @Override
    public void removeAccount(UserAccount removeAccount) {
        userRepository.delete(removeAccount);
    }

    @Override
    public List<UserAccount> findAllUser() {
        return userRepository.findAll();
    }
}
