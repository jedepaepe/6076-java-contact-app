package eu.epfc.j6077.contactapp;

import java.util.List;

public class ContactUC {
    static void addContact() {
        Contact contact = ContactUI.showAddForm();
        ContactDao.add(contact);
    }

    static void deleteContact() {
        int id = ContactUI.showDeleteForm();
        ContactDao.delete(id);
    }

    static void updateContact() {
        Contact contact = ContactUI.showUpdateForm();
        ContactDao.update(contact);
    }

    static void consultContacts() {
        List<Contact> contacts = ContactDao.fetch();
        ContactUI.showList(contacts);
    }
}
