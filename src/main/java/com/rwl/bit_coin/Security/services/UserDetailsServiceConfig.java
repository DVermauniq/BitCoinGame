package com.rwl.bit_coin.Security.services;



import com.rwl.bit_coin.Security.models.Credentials;
import com.rwl.bit_coin.Security.repository.CredentialsRepo;
import com.rwl.bit_coin.exception.UsernameNotFoundException;
import com.rwl.bit_coin.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserDetailsServiceConfig implements UserDetailsService {

    @Autowired
    private CredentialsRepo credRepository;
    @Autowired
    AdminRepo adminRepo;
    @Autowired
    UserRepository userRepo;
    @Autowired
    AgentRepo agentRepo;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {


        Credentials credentials = credRepository.findByUsernameOrEmailOrPhoneNumber(username,username,username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username/email/phone number: " + username));

        return UserDetailsImpl.buildUser(credentials);
    }
}
