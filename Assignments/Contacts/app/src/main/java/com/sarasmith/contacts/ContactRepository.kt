package com.sarasmith.contacts

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.*

class ContactRepository(application: Application) {
    val searchResults = MutableLiveData<List<Contact>>()
    private var contactDao: ContactDao?
    private val coroutineScope = CoroutineScope(Dispatchers.Main)
    var allContacts: LiveData<List<Contact>>?

    init {
        val db: ContactRoomDatabase? = ContactRoomDatabase.getDatabase(application)
        contactDao = db?.contactDao()
        allContacts = contactDao?.getAllContacts()
    }

    fun insertContact(newcontact: Contact) {
        coroutineScope.launch(Dispatchers.IO) {
            asyncInsert(newcontact)
        }
    }

    private suspend fun asyncInsert(contact: Contact) {
        contactDao?.insertContact(contact)
    }

    fun deleteContact(id: Int) {
        coroutineScope.launch(Dispatchers.IO) {
            asyncDelete(id)
        }
    }

    private suspend fun asyncDelete(id: Int) {
        contactDao?.deleteContact(id)
    }

    fun findContact(name: String) {
        coroutineScope.launch(Dispatchers.Main) {
            searchResults.value = asyncFind(name).await()
        }
    }

    private fun asyncFind(name: String) =
        coroutineScope.async(Dispatchers.IO) {
            return@async contactDao?.findContact(name)
        }

    fun sortContactsAsc() {
        coroutineScope.launch(Dispatchers.Main) {
            searchResults.value = asyncAscSort().await()
        }
    }
    fun sortContactsDesc() {
        coroutineScope.launch(Dispatchers.Main) {
            searchResults.value = asyncDescSort().await()
        }
    }

    private fun asyncAscSort() =
        coroutineScope.async(Dispatchers.IO) {
            return@async contactDao?.getAllContactsAsc()
        }

    private fun asyncDescSort() =
        coroutineScope.async(Dispatchers.IO) {
            return@async contactDao?.getAllContactsDesc()
        }
}