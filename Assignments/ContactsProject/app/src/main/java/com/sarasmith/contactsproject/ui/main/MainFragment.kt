package com.sarasmith.contactsproject.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer

import com.sarasmith.contactsproject.Contact
import com.sarasmith.contactsproject.MainActivity
import com.sarasmith.contactsproject.R
import com.sarasmith.contactsproject.databinding.FragmentMainBinding

class MainFragment : Fragment(){

    private var adapter: ContactListAdapter? = null


    companion object {
        fun newInstance() = MainFragment()
    }


    private val viewModel: MainViewModel by viewModels()
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)

        listenerSetup()
        observerSetup()
        recyclerSetup()

        return binding.root
    }

    private fun listenerSetup() {
        binding.buttonAdd.setOnClickListener {
            val name = binding.contactName.text.toString()
            val phoneNumber = binding.phoneNumber.text.toString()
            if (name != "" && phoneNumber != "") {
                val contact = Contact(name, Integer.parseInt(phoneNumber))
                viewModel.insertContact(contact)
                clearFields()
            } else {
                Toast.makeText(context, "Incomplete information", Toast.LENGTH_LONG).show()            }
        }
        binding.buttonFind.setOnClickListener { viewModel.findContact(
            binding.contactName.text.toString())
            binding.phoneNumber.text.toString()
            clearFields()
        }

        binding.buttonAsc.setOnClickListener {
            viewModel.getAllContactsAsc()
            clearFields()
        }

        binding.buttonDesc.setOnClickListener {
            viewModel.getAllContactsDesc()
            clearFields()
        }
    }


    private fun observerSetup() {

        viewModel.getAllContacts()?.observe(viewLifecycleOwner, Observer { contacts ->
            contacts?.let  {
                adapter?.setContactList(it)
            }
        })
        viewModel.getSearchResults().observe(viewLifecycleOwner, Observer  { contacts ->
            contacts?.let {
                if (it.isNotEmpty()) {
                    adapter?.setContactList(it)
                } else {
                    Toast.makeText((activity as MainActivity?)!!, "No Records Match Your Search", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }


    private fun recyclerSetup() {
        adapter = ContactListAdapter(R.layout.card_layout)
        binding.contactRecycler.layoutManager = LinearLayoutManager(context)
        binding.contactRecycler.adapter = adapter
    }

    private fun clearFields() {
        binding.contactName.setText("")
        binding.phoneNumber.setText("")
    }

    /*override fun onButtonClick(data: Contact) {
        viewModel.deleteContact(data.id)
    }*/
}