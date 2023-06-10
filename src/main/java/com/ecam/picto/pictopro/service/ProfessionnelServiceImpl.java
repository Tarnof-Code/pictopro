package com.ecam.picto.pictopro.service;

import java.util.*;


import com.ecam.picto.pictopro.entity.Role;
import com.ecam.picto.pictopro.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ecam.picto.pictopro.entity.Professionnel;
import com.ecam.picto.pictopro.repository.ProfessionnelRepository;

@Service
public class ProfessionnelServiceImpl implements ProfessionnelService {

	@Autowired
	ProfessionnelRepository professionnelRepository;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public void save(Professionnel user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		Optional<Role> defaultRoleOptional = roleRepository.findByName("ROLE_PRO");
		if (defaultRoleOptional.isPresent()) {
			Role defaultRole = defaultRoleOptional.get();
			user.setRoles(Collections.singleton(defaultRole));
		}
		professionnelRepository.save(user);
	}

	@Override
	public List<Professionnel> findAll() {
		try {
			return professionnelRepository.findAll();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Professionnel findById(int id) {
		return professionnelRepository.findById(id);
	}

	@Override
	public Professionnel findByUsername(String username) {
		return professionnelRepository.findByUsername(username);
	}
}