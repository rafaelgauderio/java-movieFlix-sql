package com.rafaeldeluca.movieflix.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rafaeldeluca.movieflix.entities.User;
import com.rafaeldeluca.movieflix.repositories.UserRepository;
import com.rafaeldeluca.movieflix.services.exceptions.ForbiddenException;
import com.rafaeldeluca.movieflix.services.exceptions.UnauthorizedException;

@Service
public class AuthService {

	@Autowired
	private UserRepository userRepository;

	@Transactional(readOnly = true)
	public User authenticated() {

		try {
			String username = SecurityContextHolder.getContext().getAuthentication().getName();
			return userRepository.findByEmail(username);

		} catch (Exception error) {
			throw new UnauthorizedException("Usuário inválido");
		}
	}

	public void validateSelfOrAdmin(Long userId) {

		User user = authenticated();
		if (!user.getId().equals(userId) && !user.hasHole("ROLE_ADMIN")) {
			throw new ForbiddenException("Access denied");
		}

	}

}