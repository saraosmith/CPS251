package com.sarasmith.coroutinesproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sarasmith.coroutinesproject.databinding.ActivityMainBinding
import kotlinx.coroutines.*
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    private lateinit var viewModel: MainViewModel

    private lateinit var binding: ActivityMainBinding
    private var layoutManager: RecyclerView.LayoutManager? = null
    private var adapter: RecyclerView.Adapter<RecyclerAdapter.ViewHolder>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        layoutManager = LinearLayoutManager(this)
        binding.contentMain.recyclerView.layoutManager = layoutManager
        adapter = RecyclerAdapter(viewModel)
        binding.contentMain.recyclerView.adapter = adapter
    }


    fun launchCoroutines(view: View){
        val names = binding.enterName.text.toString().split(" ")
        coroutineScope.launch(Dispatchers.Main) {
            for (name in names) {
                viewModel.addNames(performTask(name))
                adapter?.notifyItemInserted(adapter!!.itemCount)
            }
        }
    }

    private suspend fun performTask(name : String) : String {
        val delay = (Random.nextLong(10) + 1) * 1000
        delay(delay)
        return "The name is $name and the delay was $delay milliseconds"
    }
}
