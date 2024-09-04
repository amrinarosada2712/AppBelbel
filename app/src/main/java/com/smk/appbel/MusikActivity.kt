package com.smk.appbel

import android.media.MediaPlayer
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.bumptech.glide.Glide
import com.smk.appbel.databinding.ActivityMusikBinding
import com.smk.appbel.databinding.ItemGambarBinding

class MusikActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMusikBinding

    //deklasikan varibale yg digunakan untuk menamoung semua resources (gambar, audio)

    private val angka = arrayListOf(
        "Indonesia Raya",
        "Pada Munegeri",
        "Pemuda Pemudi"

    )

    private val audoioangka = arrayListOf(
        R.raw.indonesiaraya,
        R.raw.padamunegeri,
        R.raw.pemudapemudi

    )

    private val gambarangkan = arrayListOf(
        R.drawable.indonesiaraya,
        R.drawable.bagimunegeri,
        R.drawable.majutakgentar
    )

    //mendeklarasikan adapter untuk halaman

    private lateinit var adapter: PagerAdapter

    //mendeklarasikan media player
    private var mMediaPlayer: MediaPlayer? = null


    private var onPagelistener = object: ViewPager.OnPageChangeListener{

        override fun onPageScrolled(
            position: Int,
            positionOffset: Float,
            positionOffsetPixels: Int
        ) {
            return Unit
        }

        override fun onPageSelected(position: Int) {
            //user define fun untuk menentukan audio di play, tergantung posisinya
            startAudio(position)
        }

        override fun onPageScrollStateChanged(state: Int) {
            return Unit
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMusikBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets

        }

        //view
        adapter = AdapterGambar()
        binding.main.adapter = adapter

        //
        binding.main.addOnPageChangeListener(onPagelistener)
        binding.main.currentItem = 0
        startAudio(0)

    }

    fun destroyMediaPlayer(){
        //menguji apakah player player sdg berjalan
        if(mMediaPlayer !=null){
            //maka lakukan lah reset dan release terhadap player
            mMediaPlayer?.reset()
            mMediaPlayer?.release()
        }
        mMediaPlayer = null
    }

    override fun onStop() {
        super.onStop()
        destroyMediaPlayer()
    }

    fun startAudio (posisi: Int){
        if(mMediaPlayer != null){
            mMediaPlayer?.reset()
            mMediaPlayer?.release()
        }
        //play audio posisi sekarang
        mMediaPlayer = MediaPlayer.create(this@MusikActivity, audoioangka[posisi])
        mMediaPlayer?.start()
    }

    //
    private inner class AdapterGambar: PagerAdapter(){
        override fun getCount(): Int {
            //bnayknya gambar
            return gambarangkan.size
        }

        override fun isViewFromObject(view: View, `object`: Any): Boolean {
            return view === `object` as RelativeLayout
        }

        //bagian geral
        override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
            container.removeView(`object` as RelativeLayout)
        }

        //mengubungkan dengan item gambar didalam activity_main
        override fun instantiateItem(container: ViewGroup, position: Int): Any {

            val binding = ItemGambarBinding.inflate(LayoutInflater.from(container.context), container, false)

            //menampilkan ke img angka
            Glide.with(this@MusikActivity)
                .load(gambarangkan[position])
                .into(binding.imgAngka)

            //menampilkan ke audio
            Glide.with(this@MusikActivity)
                .load(R.drawable.audio)
                .into(binding.imgReplay)

            //menampilkan ke angka
            binding.tvHuruf.text = angka[position]

            //memutar audio dengan posisinya
            binding.imgReplay.setOnClickListener{
                this@MusikActivity.startAudio(position)
            }

            (container as ViewPager).addView(binding.root)
            return binding.root
        }

    }

}