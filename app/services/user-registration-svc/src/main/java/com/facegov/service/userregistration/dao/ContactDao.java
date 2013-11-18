package com.facegov.service.userregistration.dao;

import com.facegov.service.userregistration.model.Contact;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @author Emanuele Pugliese (puglieseweb@gmail.com)
 */
public interface ContactDao extends CrudRepository<Contact, Long> {
	
	List<Contact> findByEmailLike(String email);
}
