package ir.adicom.foody.ui.fragments.instructions

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import ir.adicom.foody.R
import ir.adicom.foody.models.Result
import ir.adicom.foody.util.Constants

class InstructionsFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_instructions, container, false)

        val args = arguments
        val myBundle: Result? = args?.getParcelable(Constants.RECIPE_RESULT_KEY)

        val instructionsWebview = view.findViewById<WebView>(R.id.instructions_webView)
        instructionsWebview.webViewClient = object : WebViewClient() {}
        val websiteUrl = myBundle!!.sourceUrl
        instructionsWebview.loadUrl(websiteUrl)

        return view
    }
}