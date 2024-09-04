package com.smk.appbel

import android.net.Uri
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.smk.appbel.databinding.ActivityAboutBinding
import com.smk.appbel.databinding.ActivityMainBinding

class AboutActivity : AppCompatActivity() {
    private  lateinit var binding: ActivityAboutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityAboutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
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
        CustomTabsIntent.launchUrl(this@AboutActivity, Uri.parse(url))
    }
}