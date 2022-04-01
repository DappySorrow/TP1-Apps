package com.example.tp01.presentation.ui.deliveries

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tp01.R
import com.example.tp01.databinding.ActivityDeliveriesBinding
import com.example.tp01.presentation.adapters.DeliveryRecyclerViewAdapter

class DeliveriesActivity : AppCompatActivity() {

    private lateinit var deliveryRecyclerViewAdapter: DeliveryRecyclerViewAdapter
    private lateinit var binding: ActivityDeliveriesBinding
    private val viewModel: DeliveriesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDeliveriesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        deliveryRecyclerViewAdapter = DeliveryRecyclerViewAdapter()
        binding.rcvDeliveries.layoutManager =
            LinearLayoutManager(this)
        binding.rcvDeliveries.adapter = deliveryRecyclerViewAdapter

        viewModel.deliveries.observe(this) {
            deliveryRecyclerViewAdapter.differ.submitList(it)
        }

        //Mettre la variable reçue dans l'interface
        binding.textView.text = getString(R.string.deliveriesTitre, intent.getStringExtra(NOM_COMMERCANT))

        //Le bouton pour ajouter
        binding.btnNewDelivery.setOnClickListener {
            val newDeliveryActivityIntent =
                NewDeliveriesActivity.newDeliveryIntent(this)
            startActivity(newDeliveryActivityIntent)
        }

    }

    //Ouverture de DeliveryActivity
    companion object {
        const val NOM_COMMERCANT = ""

        fun deliveryIntent(context: Context, name: String): Intent {
            val intent = Intent(context, DeliveriesActivity::class.java)
            intent.putExtra(NOM_COMMERCANT, name)
            return intent
        }
    }
}