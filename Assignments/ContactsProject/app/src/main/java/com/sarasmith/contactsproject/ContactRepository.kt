package com.sarasmith.contactsproject

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.*

class ContactRepository(application: Application) {

    val searchResults = MutableLiveData<List<Contact>>()
    private var contactDao: ContactDao?
    private val coroutineScope = CoroutineScope(Dispatchers.Main)
    val allContacts: LiveData<List<Contact>>?
    val allContactsAsc: LiveData<List<Contact>>?
    val allContactsDesc: LiveData<List<Contact>>?

    /*init {
        val db: ContactRoomDatabase? = ContactRoomDatabase.getDatabase(application)
        contactDao = db?.contactDao()
        allContactsAsc = contactDao?.sortAsc()
        allContactsDesc = contactDao?.getAllContactsDesc()
        allContacts = contactDao?.getAllContacts()

    }*/
    init {
        val db: ContactRoomDatabase? =
            ContactRoomDatabase.getDatabase(application)
        contactDao = db?.contactDao()
        allContacts = contactDao?.getAllContacts()
        allContactsAsc = contactDao?.getAllContactsAsc()
        allContactsDesc = contactDao?.getAllContactsDesc()
    }

    fun insertContact(newcontact: Contact) {
        coroutineScope.launch(Dispatchers.IO) {
            asyncInsert(newcontact)
        }
    }

    private fun asyncInsert(contact: Contact) {
        contactDao?.insertContact(contact)
        //contactDao?.getAllContactsAsc()
        //contactDao?.getAllContactsDesc()
    }

    fun deleteContact(id : Int) {
        coroutineScope.launch(Dispatchers.IO) {
            asyncDelete(id)
        }
    }

    private fun asyncDelete(id: Int) {
        contactDao?.deleteContact(id)
    }

    fun findContact(name: String) {

        coroutineScope.launch(Dispatchers.Main) {
            searchResults.value = asyncFind(name).await()
        }
    }

    private fun asyncFind(name: String): Deferred<List<Contact>?> =

        coroutineScope.async(Dispatchers.IO) {
            return@async contactDao?.findContact(name)
        }

    fun getAllContactsAsc() {
        coroutineScope.launch(Dispatchers.Main) {
            val allContactsAsc: LiveData<List<Contact>>? = asyncASC().await()
        }
    }
    private fun asyncASC() : Deferred<LiveData<List<Contact>>?> =
        coroutineScope.async(Dispatchers.IO) {
            return@async contactDao?.getAllContactsAsc()
        }
    fun getAllContactsDesc() {
        coroutineScope.launch(Dispatchers.Main) {
            val allContactsDesc: LiveData<List<Contact>>? = asyncDESC().await()
        }
    }
    private fun asyncDESC() : Deferred<LiveData<List<Contact>>?> =
        coroutineScope.async(Dispatchers.IO) {
            return@async contactDao?.getAllContactsDesc()
        }


}