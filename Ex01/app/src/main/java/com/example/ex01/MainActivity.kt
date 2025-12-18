package com.example.ex01

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.example.ex01.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import org.threeten.bp.LocalDate
import androidx.core.content.edit


class MainActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    private lateinit var binding: ActivityMainBinding
    private var dataSeleccionada: LocalDate? = null

    companion object {
        private const val PREFS = "ex01_prefs"
        private const val K_NOM = "nom"
        private const val K_COGNOMS = "cognoms"
        private const val K_DATA = "data"
        private const val K_MODALITAT = "modalitat"
        private const val K_TRACTE = "tracte"
        private const val K_AVISOS = "avisos"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // ===== AUTO-LOGIN =====
        val prefs = getSharedPreferences(PREFS, MODE_PRIVATE)
        val nomGuardat = prefs.getString(K_NOM, null)
        if (!nomGuardat.isNullOrBlank()) {
            startActivity(Intent(this, PassActivity::class.java))
            finish()
            return
        }

        // ===== SPINNERS (des de strings.xml) =====
        val adapterModalitat = ArrayAdapter.createFromResource(
            this,
            R.array.modalitat_array,
            android.R.layout.simple_spinner_item
        ).also { it.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item) }
        binding.spModalitat.adapter = adapterModalitat

        val adapterTracte = ArrayAdapter.createFromResource(
            this,
            R.array.tracte_array,
            android.R.layout.simple_spinner_item
        ).also { it.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item) }
        // IMPORTANT: aquí tens el teu spinner de tracte (tu li dius spVip)
        binding.spVip.adapter = adapterTracte

        // ===== DATA (CalendarView) =====
        binding.calendarView.setOnDateChangeListener { _, year, month, dayOfMonth ->
            dataSeleccionada = LocalDate.of(year, month + 1, dayOfMonth)
            binding.tvData.text = dataSeleccionada.toString()
        }

        // ===== BOTÓ NETEJAR =====
        binding.btnNetejar.setOnClickListener {
            binding.etNom.setText("")
            binding.etCognom.setText("")
            binding.etPassword.setText("")
            binding.spModalitat.setSelection(0)
            binding.spVip.setSelection(0)
            binding.swAvisos.isChecked = false
            binding.tvData.text = ""
            dataSeleccionada = null
        }

        // ===== BOTÓ REGISTRAR / CONTINUAR =====
        binding.btnRegistrar.setOnClickListener {

            // 1) Llegeix dades
            val nom = binding.etNom.text.toString().trim()
            val cognoms = binding.etCognom.text.toString().trim()
            val password = binding.etPassword.text.toString()
            val modalitatSel = binding.spModalitat.selectedItem.toString()
            val tracteSel = binding.spVip.selectedItem.toString()
            val avisos = binding.swAvisos.isChecked
            val dataSel = dataSeleccionada?.toString() ?: ""

            // 2) Validacions mínimes (obligatori)
            if (nom.isBlank()) {
                binding.etNom.error = "Nom obligatori"
                return@setOnClickListener
            }
            if (cognoms.isBlank()) {
                binding.etCognom.error = "Cognoms obligatoris"
                return@setOnClickListener
            }
            if (password.length < 6) {
                binding.etPassword.error = "Password mínim 6 caràcters"
                return@setOnClickListener
            }
            if (dataSel.isBlank()) {
                Snackbar.make(binding.root, "Selecciona una data de naixement", Snackbar.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // 3) Guardar dades (SharedPreferences) amb KTX
            prefs.edit {
                putString(K_NOM, nom)
                putString(K_COGNOMS, cognoms)
                putString(K_DATA, dataSel)
                putString(K_MODALITAT, modalitatSel)
                putString(K_TRACTE, tracteSel)
                putBoolean(K_AVISOS, avisos)
            }

            // 4) SnackBar
            Snackbar.make(binding.root, "Registre guardat", Snackbar.LENGTH_SHORT).show()

            // 5) LogCat resum
            Log.d("EX01", "nom=$nom, cognoms=$cognoms, data=$dataSel, modalitat=$modalitatSel, tracte=$tracteSel, avisos=$avisos")

            // 6) Anar a PassActivity (sense extras)
            startActivity(Intent(this, PassActivity::class.java))
            finish()
        }
    }

    override fun onItemSelected(
        parent: AdapterView<*>?,
        view: View?,
        position: Int,
        id: Long
    ) {
        TODO("Not yet implemented")
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        TODO("Not yet implemented")
    }
}
