/* 
 * Copyright (c) 2013 Manning Publications Co.
 * 
 * Book: http://manning.com/wheeler/
 * Blog: http://springinpractice.com/
 * Code: https://github.com/springinpractice
 */
package com.facegov.service.userregistration.service.impl;

import com.facegov.service.userregistration.dao.ContactDao;
import com.facegov.service.userregistration.model.Contact;
import com.facegov.service.userregistration.service.ContactService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.springframework.util.Assert.notNull;


/**
 * Contact service bean.
 * 
 * @author Willie Wheeler (willie.wheeler@gmail.com)
 */
@Service
@Transactional
public class ContactServiceImpl implements ContactService {
    @Inject private ContactDao contactDao;

    @Override
    public void createContact(Contact contact) {
        notNull(contact, "contact can't be null");
        contactDao.save(contact);
    }

    @Override
    public List<Contact> getContacts() {
        Iterable<Contact> iterable = contactDao.findAll();
        Iterator<Contact> iterator = iterable.iterator();
        List<Contact> contacts = new ArrayList<Contact>();
        while (iterator.hasNext()) {
            contacts.add(iterator.next());
        }
        return contacts;
    }

    @Override
    public List<Contact> getContactsByEmail(String email) {
        notNull(email, "email can't be null");
        return contactDao.findByEmailLike("%" + email + "%");
    }

    @Override
    public Contact getContact(Long id) {
        notNull(id, "id can't be null");
        return contactDao.findOne(id);
    }

    @Override
    public void updateContact(Contact contact) {
        notNull(contact, "contact can't be null");
        contactDao.save(contact);
    }

    @Override
    public void deleteContact(Long id) {
        notNull(id, "id can't be null");
        contactDao.delete(id);
    }
}