package com.example.tp01.presentation.ui.deliveries

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.example.tp01.R
import com.example.tp01.databinding.ActivityNewDeliveriesBinding
import com.example.tp01.domain.models.Delivery


class NewDeliveriesActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNewDeliveriesBinding
    private val viewModel: NewDeliveriesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewDeliveriesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.trader.observe(this) {
            binding.txtIaspyx.text = String.format("%.2f", it.iaspyx).replace(',', '.', true)
            binding.txtSmiathil.text = String.format("%.2f", it.smiathil).replace(',', '.', true)
            binding.txtJasmalt.text = String.format("%.2f", it.jasmalt).replace(',', '.', true)
            binding.txtVethyx.text = String.format("%.2f", it.vethyx).replace(',', '.', true)
            binding.txtBilerium.text = String.format("%.2f", it.bilerium).replace(',', '.', true)

            binding.sldIaspyx.valueTo =
                binding.txtIaspyx.text.toString().replace(',', '.', true).toFloat()
            binding.sldSmiathil.valueTo =
                binding.txtSmiathil.text.toString().replace(',', '.', true).toFloat()
            binding.sldJasmalt.valueTo =
                binding.txtJasmalt.text.toString().replace(',', '.', true).toFloat()
            binding.sldVethyx.valueTo =
                binding.txtVethyx.text.toString().replace(',', '.', true).toFloat()
            binding.sldBilerium.valueTo =
                binding.txtBilerium.text.toString().replace(',', '.', true).toFloat()


            if (binding.sldIaspyx.valueTo == 0.01F) {
                binding.sldIaspyx.isEnabled = false
            }
            if (binding.sldSmiathil.valueTo == 0.01F) {
                binding.sldSmiathil.isEnabled = false
            }
            if (binding.sldJasmalt.valueTo == 0.01F) {
                binding.sldJasmalt.isEnabled = false
            }
            if (binding.sldVethyx.valueTo == 0.01F) {
                binding.sldVethyx.isEnabled = false
            }
            if (binding.sldBilerium.valueTo == 0.01F) {
                binding.sldBilerium.isEnabled = false
            }
        }

        //Envoyer une newDelivery dans le RecyclerView
        binding.btnSave.setOnClickListener {

            //Prendre les sliders dans une variable
            val iaspyx = binding.sldIaspyx.value
            val smiathil = binding.sldSmiathil.value
            val jasmalt = binding.sldJasmalt.value
            val vethyx = binding.sldVethyx.value
            val bilerium = binding.sldBilerium.value

            if (binding.sldIaspyx.valueTo == 0.01F &&
                binding.sldSmiathil.valueTo == 0.01F &&
                binding.sldJasmalt.valueTo == 0.01F &&
                binding.sldVethyx.valueTo == 0.01F &&
                binding.sldBilerium.valueTo == 0.01F
            ) {
                Toast.makeText(this, R.string.livraisonErreur, Toast.LENGTH_SHORT).show()
            } else {
                //Créer une delivery
                val delivery = Delivery(

                    String.format("%.2f", iaspyx).toString().replace(',', '.', true).toFloat(),
                    String.format("%.2f", smiathil).toString().replace(',', '.', true).toFloat(),
                    String.format("%.2f", jasmalt).toString().replace(',', '.', true).toFloat(),
                    String.format("%.2f", vethyx).toString().replace(',', '.', true).toFloat(),
                    String.format("%.2f", bilerium).toString().replace(',', '.', true).toFloat()

                )
                viewModel.addOne(delivery)
            }

            //Retirer les éléments choisis
            viewModel.removeElements(iaspyx, smiathil, jasmalt, vethyx, bilerium)

            //Remettre les sliders à 0
            binding.sldIaspyx.value = 0F
            binding.sldSmiathil.value = 0F
            binding.sldJasmalt.value = 0F
            binding.sldVethyx.value = 0F
            binding.sldBilerium.value = 0F

            viewModel.save();

            finish()
        }
    }

    //Pour lancer une une fenêtre de NewDeliveryActivity
    companion object {
        fun newDeliveryIntent(context: Context): Intent {
            return Intent(context, NewDeliveriesActivity::class.java)
        }
    }
}