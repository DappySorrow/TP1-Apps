package com.example.tp01.presentation.ui.splash

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.tp01.R
import com.example.tp01.databinding.ActivitySplashBinding
import com.example.tp01.presentation.ui.deliveries.DeliveriesActivity
import com.example.tp01.presentation.ui.deliveries.DeliveriesViewModel

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding
    private val viewModel: SplashViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Garder les données de Trader à jour
        viewModel.trader.observe(this) {

            binding.tilNomCommercant.editText!!.setText(it.name)

            binding.txtIaspyx.text = String.format("%.2f", it.iaspyx)
            binding.txtSmiathil.text = String.format("%.2f", it.smiathil)
            binding.txtJasmalt.text = String.format("%.2f", it.jasmalt)
            binding.txtVethyx.text = String.format("%.2f", it.vethyx)
            binding.txtBilerium.text = String.format("%.2f", it.bilerium)
        }

        ////////////////////////////////////////////////////////////////////////

        //Ouveture de DeliveriesActivity
        binding.btnOuvrir.setOnClickListener {
            val deliveryActivityIntent =
                DeliveriesActivity.deliveryIntent(
                    this,
                    binding.tilNomCommercant.editText?.text.toString()
                )
            if (binding.tilNomCommercant.editText?.text.toString().isNotEmpty()) {

                viewModel.save(binding.tilNomCommercant.editText?.text.toString())

                startActivity(deliveryActivityIntent)
            } else {
                Toast.makeText(this, getString(R.string.nomErreur), Toast.LENGTH_SHORT)
                    .show()
            }
        }

        //Chargement des cargos
        binding.btnChargement.setOnClickListener {
            viewModel.load()
            viewModel.save(binding.tilNomCommercant.editText?.text.toString())
        }

        //Retirer les cargos
        binding.btnTeleverser.setOnClickListener {
            //TODO: Faire la fonction pour retirer les cargos

            viewModel.deleteAll()
        }
    }
}