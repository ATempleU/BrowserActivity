import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.EditText
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import com.example.browseractivity.R

class PageViewerFragment : Fragment() {

    private lateinit var backButton: ImageButton
    private lateinit var nextButton: ImageButton
    private lateinit var goButton: ImageButton
    private lateinit var urlField: EditText
    private lateinit var webView: WebView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_page_viewer, container, false)
        backButton = view.findViewById(R.id.back_button)
        nextButton = view.findViewById(R.id.next_button)
        goButton = view.findViewById(R.id.go_button)
        urlField = view.findViewById(R.id.url_field)
        webView = view.findViewById(R.id.web_view)

        backButton.setOnClickListener {
            if (webView.canGoBack()) webView.goBack()
        }

        nextButton.setOnClickListener {
            if (webView.canGoForward()) webView.goForward()
        }

        goButton.setOnClickListener {
            val url = urlField.text.toString()
            if (url.isNotEmpty()) webView.loadUrl(url)
        }

        // Find the Go ImageButton view and set a click listener
        val goButton: ImageButton = view.findViewById(R.id.go_button)
        goButton.setOnClickListener {
            var url = urlField.text.toString()
            // Check if the URL starts with "http://" or "https://"
            if (!url.startsWith("http://") && !url.startsWith("https://")) {
                // If it doesn't, add "http://" to the beginning of the URL
                url = "http://$url"
            }
            webView.loadUrl(url)
        }


        val nextButton: ImageButton = view.findViewById(R.id.next_button)
        nextButton.setOnClickListener {
            if (webView.canGoForward()) {
                webView.goForward()
            }
        }

        val backButton: ImageButton = view.findViewById(R.id.back_button)
        backButton.setOnClickListener {
            if (webView.canGoBack()) {
                webView.goBack()
            }
        }


        // Set a WebViewClient on the WebView to handle page loads
        webView.webViewClient = object : WebViewClient() {

            // Update the EditText with the URL of the page being viewed
            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                urlField.setText(url)
            }
        }

        return view
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (savedInstanceState != null) {
            webView.restoreState(savedInstanceState)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        webView.saveState(outState)
    }

    fun getCurrentUrl(): String {
        return webView.url ?: ""
    }

    inner class MyWebViewClient : WebViewClient() {

        override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
            urlField.setText(request?.url.toString())
            return false
        }
    }


}




