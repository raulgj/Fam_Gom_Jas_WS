package com.famgomjas.ws.service;

import com.famgomjas.ws.entities.Role;
import com.famgomjas.ws.entities.User;

public interface RoleSvc extends ISvc<Role>{
	public Role findFirstByName(String name);
}
