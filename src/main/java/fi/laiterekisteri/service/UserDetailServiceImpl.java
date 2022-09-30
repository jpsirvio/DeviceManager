package fi.laiterekisteri.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import fi.laiterekisteri.domain.Person;
import fi.laiterekisteri.domain.PersonRepository;


@Service
public class UserDetailServiceImpl implements UserDetailsService  {
	private final PersonRepository repository;

	@Autowired
	public UserDetailServiceImpl(PersonRepository pRepository) {
		this.repository = pRepository;
	}

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {   
    	Person curruser = repository.findByUsername(username);
        UserDetails user = new org.springframework.security.core.userdetails.User(username, curruser.getPasswordHash(), 
        		AuthorityUtils.createAuthorityList(curruser.getRole()));
        return user;
    }   
} 