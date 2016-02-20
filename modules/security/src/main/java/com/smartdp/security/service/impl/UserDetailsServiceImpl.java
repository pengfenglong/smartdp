package com.smartdp.security.service.impl;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.google.common.collect.Sets;
import com.smartdp.core.service.IBaseService;
import com.smartdp.platform.model.Privilege;
import com.smartdp.platform.model.Role;
import com.smartdp.platform.model.User;

public class UserDetailsServiceImpl implements UserDetailsService{

	@Autowired
	private IBaseService baseService;
	
	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException, DataAccessException {
		baseService.getBaseDao().setEntityClass(User.class);
		User user = (User) baseService.findUniqueBy("userName", username);
		if (user == null) {
			throw new UsernameNotFoundException("用户" + username + " 不存在");
		}
		Set<GrantedAuthority> grantedAuths = obtainGrantedAuthorities(user);
		boolean enabled = true;
		boolean accountNonExpired = true;
		boolean credentialsNonExpired = true;
		boolean accountNonLocked = true;

		UserDetails userdetails = new org.springframework.security.core.userdetails.User(user.getUserName(), user
				.getUserPassword(), enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, grantedAuths);

		return userdetails;
	}
	
	/**
	 * 获得用户所有角色的权限集合.
	 */
	private Set<GrantedAuthority> obtainGrantedAuthorities(User user) {
		Set<GrantedAuthority> authSet = Sets.newHashSet();
		for (Role role : user.getRoles()) {
			for (Privilege authority : role.getPrivileges()) {
				authSet.add(new GrantedAuthorityImpl(authority.getResourceId()));
			}
		}
		authSet.add(new GrantedAuthorityImpl("ROLE_USER"));
		return authSet;
	}

}
