package pl.lenda.marcin.wzb.service.user_account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.lenda.marcin.wzb.dto.ChangePasswordDto;
import pl.lenda.marcin.wzb.entity.UserAccount;
import pl.lenda.marcin.wzb.repository.UserAccountRepository;

import java.util.Collection;
import java.util.List;

/**
 * Created by Promar on 06.11.2016.
 */
@Service
public class UserAccountImplementation implements UserAccountService{

    @Autowired
    UserAccountRepository userAccountRepository;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void registerNewUser(UserAccount newUserAccount) {
        if(newUserAccount.getId()==null) {
            newUserAccount.setPassword(bCryptPasswordEncoder.encode(newUserAccount.getPassword()));
        }
        if (checkIfAdminExists()) {
            newUserAccount.setRole("USER");
        } else {
            newUserAccount.setRole("ADMIN");
        }
        userAccountRepository.save(newUserAccount);

    }

    @Override
    public UserAccount findByUsername(String username) {
        return userAccountRepository.findByUsername(username);
    }

    @Override
    public UserAccount editData(UserAccount userAccount) {
        return userAccountRepository.save(userAccount);
    }

    @Override
    public UserAccount findByNameAndSurname(String name, String surname) {
        return userAccountRepository.findByNameAndSurname(name, surname);
    }

    @Override
    public UserAccount findByNumberUser(String numberUser) {
        return userAccountRepository.findByNumberUser(numberUser);
    }

    @Override
    public List<UserAccount> findActiveAccount() {
        return userAccountRepository.findAllByActiveTrue();
    }

    @Override
    public List<UserAccount> findNotActiveAccount() {
        return userAccountRepository.findAllByActiveFalse();
    }

    @Override
    public List<UserAccount> findAllAccount() {
        return userAccountRepository.findAll();
    }

    @Override
    public boolean updateRole(UserAccount userAccount) {
        userAccountRepository.save(userAccount);
        return true;
    }

    @Override
    public void changePassword(ChangePasswordDto changePasswordDto, UserAccount userAccount) {
       userAccount.setPassword(bCryptPasswordEncoder.encode(changePasswordDto.getNewPassword()));
       userAccountRepository.save(userAccount);
    }

    @Override
    public void removeAccount(UserAccount userAccount) {
        userAccountRepository.delete(userAccount);
    }

    @Override
    public void makeActiveAccount(UserAccount userAccount) {
        userAccountRepository.save(userAccount);
    }

    @Override
    public String getRoleOfLoggedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        return authorities.iterator().next().toString();
    }

    private boolean checkIfAdminExists() {
        List<UserAccount> userEntityList = userAccountRepository.findByRole("ADMIN");
        for (UserAccount userEntity : userEntityList) {
            if (userEntity.getRole().equals("ADMIN")) {
                return true;
            }
        }
        return false;
    }
}
