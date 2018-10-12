package uk.ac.ed.inf.voting

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var tally = 0
    private val prefsFile = "MyPrefsFile"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        upvotebutton.setOnClickListener { _ ->
            tally++
            display.text = "$tally"
        }
        downvotebutton.setOnClickListener { _ ->
            tally--
            display.text = "$tally"
        }
    }

    override fun onStart() {
        super.onStart()
        val settings = getSharedPreferences(prefsFile, Context.MODE_PRIVATE)
        tally = settings.getInt("storedTally", 0)
        display.text = "$tally"
    }

    override fun onStop() {
        super.onStop()
        val settings = getSharedPreferences(prefsFile, Context.MODE_PRIVATE)
        val editor = settings.edit()
        editor.putInt("storedTally", tally)
        editor.apply()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
