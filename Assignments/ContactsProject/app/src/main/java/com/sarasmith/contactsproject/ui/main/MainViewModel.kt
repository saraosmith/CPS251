package com.sarasmith.contactsproject.ui.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.sarasmith.contactsproject.Contact
import com.sarasmith.contactsproject.ContactRepository

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: ContactRepository = ContactRepository(application)
    private val allContacts: LiveData<List<Contact>>? = repository.allContacts
    private val searchResults: MutableLiveData<List<Contact>> = repository.searchResults
    private val ascContacts: LiveData<List<Contact>>? = repository.allContactsAsc
    private val descContacts: LiveData<List<Contact>>? = repository.allContactsDesc


    /*init {
        allContacts = null
        searchResults = repository.searchResults
        sortASC()
    }*/

    fun insertContact(contact: Contact) {
        repository.insertContact(contact)
    }

    fun findContact(name: String) {
        repository.findContact(name)
    }

    fun deleteContact(id: Int) {
        repository.deleteContact(id)
    }

    fun getSearchResults(): MutableLiveData<List<Contact>> {
        return searchResults
    }

    fun getAllContacts(): LiveData<List<Contact>>? {
        return allContacts
    }
    fun getAllContactsAsc(): LiveData<List<Contact>>? {
        return ascContacts
    }
    fun getAllContactsDesc(): LiveData<List<Contact>>? {
        return descContacts
    }
}