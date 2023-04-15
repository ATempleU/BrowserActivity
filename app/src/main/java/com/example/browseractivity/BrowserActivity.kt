package com.example.browseractivity
import PageViewerFragment
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle


class BrowserActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            // Create a new instance of PageViewerFragment
            val fragment = PageViewerFragment()

            // Get the FragmentManager and start a new FragmentTransaction
            supportFragmentManager.beginTransaction()
                .add(R.id.page_viewer, fragment) // add the fragment to the container with the id "page_viewer"
                .commit()
        }

    }
}


