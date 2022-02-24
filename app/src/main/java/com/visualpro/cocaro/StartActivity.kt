package com.visualpro.cocaro

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.appcompat.app.AlertDialog
import com.visualpro.cocaro.databinding.ActivityStartBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class StartActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding=ActivityStartBinding.inflate(layoutInflater, null, false)
        binding.btn2player.setOnClickListener{
            val dialog = android.app.AlertDialog.Builder(this)
            dialog.setTitle("AUTHOR")
            dialog.setOnCancelListener {
            }
            dialog.setMessage("NGUYỄN THỬ")
            dialog.setPositiveButton("OK", { dialog, which ->
              dialog.dismiss()
            })
            dialog.show()

        }
        binding.btnPlay.setOnClickListener{
            binding.progressBar.visibility= View.VISIBLE
            binding.progressBar.max=100
            CoroutineScope(Dispatchers.Main).launch {
                for (i in 0..100){
                    delay(18)
                    binding.progressBar.progress=  binding.progressBar.progress+1
                }
            }

            Handler(mainLooper).postDelayed({
                binding.progressBar.progress=0
                binding.progressBar.visibility= View.INVISIBLE
                startActivity(Intent(this, MainActivity::class.java))
            }, 2000)

        }
        setContentView(binding.root)
    }
}