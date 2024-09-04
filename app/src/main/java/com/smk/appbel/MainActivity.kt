package com.smk.appbel

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.smk.appbel.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private  lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding.cardAbout.setOnClickListener{
            val about = Intent(this@MainActivity, AboutActivity::class.java)
            startActivity(about)
        }

        binding.cardDiklat.setOnClickListener{
            val diklat = Intent(this@MainActivity, DiklatActivity::class.java)
            startActivity(diklat)
        }
        binding.cardPlay.setOnClickListener{
            val play = Intent(this@MainActivity, PlayListActivity::class.java)
            startActivity(play)
        }
        binding.btfb.setOnClickListener{
            openBrowser ("https://www.facebook.com/BBPPMPVBBL.Kemdikbud/")
        }

        binding.btwa.setOnClickListener{
            openBrowser("http://api.whatsapp.com/send?phone=62895383875089")
        }

        binding.btig.setOnClickListener{
            openBrowser ("https://www.instagram.com/bbppmpvbbl.kemdikbud/")

        }
        binding.btmaps.setOnClickListener{
            openBrowser ("https://maps.app.goo.gl/Lhqjz2EriBXQdBBv9")

        }

        binding.bty.setOnClickListener{
            openBrowser ("https://www.youtube.com/channel/UCACoese2mPkJSPxsF7Dg6_Q")

        }
        binding.btweb.setOnClickListener{
            openBrowser ("https://bbppmpvbbl.kemdikbud.go.id/bbppmpvbbl")

        }

    }
    fun openBrowser(url: String){
//        val builder = CustomTabsIntent.Builder()
//        val CustomTabsIntent = builder.build()
//        CustomTabsIntent.launchUrl(this@MainActivity, Uri.parse(url))
        val builder = CustomTabsIntent.Builder()
        val CustomTabsIntent = builder.build()
        CustomTabsIntent.launchUrl(this@MainActivity, Uri.parse(url))
    }
}