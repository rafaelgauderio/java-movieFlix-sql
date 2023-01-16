package com.rafaeldeluca.movieflix.services;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rafaeldeluca.movieflix.entities.User;
import com.rafaeldeluca.movieflix.repositories.UserRepository;
import com.rafaeldeluca.movieflix.services.exceptions.ResourceNotFoundException;
import com.rafaeldeluca.movifliex.dto.UserDTO;

@Service
public class UserService implements UserDetailsService {

	private static Logger logger = LoggerFactory.getLogger(UserService.class);
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private AuthService authService;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		User user = userRepository.findByEmail(email);
		
		if (user == null) {
			logger.error("O usuário não foi encontrado: " + email);
			throw new UsernameNotFoundException("O email informado não foi encontrado");
		}
		
		logger.info("Usuário encontrado: " + email);
		
		return user;
	}
	
	@Transactional(readOnly = true)
	public UserDTO findById(Long id) {
		
		authService.validateSelfOrAdmin(id);
		
		Optional <User> object = userRepository.findById(id);
		User entity = object.orElseThrow(() -> new ResourceNotFoundException("Entidade não foi encontrada"));
		UserDTO userDTO = new UserDTO(entity);
		return userDTO;
		
	}
	
	@Transactional(readOnly = true)
	public UserDTO returnUserProfile( ) {		
		
		User user = authService.authenticated();
		Long id = user.getId();
		authService.validateSelfOrAdmin(id);
		UserDTO userDTO = new UserDTO(user);
		return userDTO;	
		
	}


}