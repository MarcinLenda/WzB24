package pl.lenda.marcin.wzb.service.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.lenda.marcin.wzb.entity.HistoryLoggedAppIn;
import pl.lenda.marcin.wzb.entity.UserAccount;
import pl.lenda.marcin.wzb.repository.UserAccountRepository;
import pl.lenda.marcin.wzb.service.history.HistoryLoggedInService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Promar on 06.11.2016.
 */
@Service
public class MongoUserDetailsService implements UserDetailsService {

    @Autowired
    private UserAccountRepository userAccountRepository;
    @Autowired
    private HistoryLoggedInService historyLoggedInService;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserAccount userAccount = getUserDetail(username);
        UserDetails userDetails = new User(userAccount.getUsername(), userAccount.getPassword(), getAuthorities(userAccount.getRole()));

            UserAccount userAccount1 = userAccountRepository.findByUsername(username);
            HistoryLoggedAppIn whoLogged = new HistoryLoggedAppIn();
            whoLogged.setDateLogged(new Date());
            whoLogged.setName(userAccount1.getName());
            whoLogged.setSurname(userAccount1.getSurname());
            whoLogged.setUsername(userAccount1.getUsername());

            if (historyLoggedInService.findByUsername(userDetails.getUsername()).size() != 0) {
                List<HistoryLoggedAppIn> historyLoggedAppIn = historyLoggedInService.findByUsername(userDetails.getUsername());
                whoLogged.setHowManyTimesLoggedIn(historyLoggedAppIn.size() +1);
            } else {
                whoLogged.setHowManyTimesLoggedIn(1);

            }

            historyLoggedInService.saveWhoLoggedIn(whoLogged);



        return userDetails;
    }

    public List<GrantedAuthority> getAuthorities(String role) {
        List<GrantedAuthority> authList = new ArrayList<>();
        if (role.equalsIgnoreCase("SUPER_ADMIN")) {
            authList.add(new SimpleGrantedAuthority("ROLE_SUPER_ADMIN"));

        }else if(role.equalsIgnoreCase("ADMIN")) {
            authList.add(new SimpleGrantedAuthority("ROLE_ADMIN"));

        } else if(role.equalsIgnoreCase("MODERATOR")){
            authList.add(new SimpleGrantedAuthority("ROLE_MODERATOR"));

        } else if(role.equalsIgnoreCase("SUPER_USER")){
            authList.add(new SimpleGrantedAuthority("ROLE_SUPER_USER"));
        }
        else if (role.equalsIgnoreCase("USER")) {
            authList.add(new SimpleGrantedAuthority("ROLE_USER"));

        }

        return authList;
    }


    public UserAccount getUserDetail(String username) {
        UserAccount userAccount = userAccountRepository.findByUsername(username);

        if(userAccount.getUsername().equals("mlenda@bimsplus.com.pl")){
            userAccount.setRole("SUPER_ADMIN");
            userAccountRepository.save(userAccount);
        }

        System.out.println(userAccount.getName());
        System.out.println(userAccount.getRole());

        List<UserAccount> listUserAccount = userAccountRepository.findAllByActiveFalse();

        for(UserAccount u: listUserAccount){
            System.out.println(u);
        }

        UserAccount userAccountBeActive = userAccountRepository.findByUsernameAndActiveTrue(userAccount.getUsername());
        return userAccountBeActive;


    }
}
