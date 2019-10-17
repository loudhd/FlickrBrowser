package com.example.flickrbrowser

import android.nfc.Tag
import android.os.Bundle
import android.util.Log
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(TAG,"onCreate calles")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        val getRawData = GetRawData()
        getRawData.execute("https://api.flikr.com/services/feeds/photos_pubilc.gne?tags=android.oreo&format=json&soncallback=1")

        /*fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }*/
        Log.d(TAG,"onCreate ends")
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        Log.d(TAG,"onCreateOptionsMenu calles")
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        Log.d(TAG,"onOptionsItemSalected calles")
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
    /*companion object{
        private const val TAG = "MainActivity"
    }*/
    fun onDownloadComlete(data: String,status: DownloadStatus){
        if (status==DownloadStatus.OK){
            Log.d(TAG,"onDownloadComplete called,data is $data")
        }else{
            // download failed
            Log.d(TAG,"onDownloadCompleted failed with status $status. Error message is:$data")
        }
    }
}
