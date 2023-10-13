package com.example.sharepreferences

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.sharepreferences.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainBinding
    private val sharedFile = "SharedFileSample"

    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPreferences = getSharedPreferences(sharedFile, Context.MODE_PRIVATE)
    }

    override fun onClick(v: View?) {
        editor = sharedPreferences.edit()
        when (v?.id) {
            R.id.btnSave -> {

                editor.putString("name_key", binding.etName.text.toString())
                editor.putString("email_key", binding.etEmail.text.toString())
                editor.apply()
            }
            R.id.btnClear -> {
                binding.etName.setText("")
                binding.etEmail.setText("")
                editor.remove("name_key")
            }
            R.id.btnRetrieve -> {
                if (sharedPreferences.contains("name_key")) {
                    binding.etName.setText(sharedPreferences.getString("name_key", ""))
                }
                if (sharedPreferences.contains("email_key")) {
                    binding.etEmail.setText(sharedPreferences.getString("email_key", ""))
                }
            }
        }
    }
}