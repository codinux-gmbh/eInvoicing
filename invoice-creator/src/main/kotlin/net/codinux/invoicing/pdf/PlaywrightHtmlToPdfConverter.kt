package net.codinux.invoicing.pdf

import com.microsoft.playwright.Browser
import com.microsoft.playwright.BrowserType
import com.microsoft.playwright.Page
import com.microsoft.playwright.Playwright
import com.microsoft.playwright.options.Margin

class PlaywrightHtmlToPdfConverter {

    companion object {
        private val PlaywrightCreateOptions = Playwright.CreateOptions()

        private val BrowserLaunchOptions = BrowserType.LaunchOptions()

        private val BrowserContextOptions = Browser.NewContextOptions().apply {
            javaScriptEnabled = false
        }
    }


    fun createPdf(html: String): ByteArray =
        // TODO: playwright and browser should be created only once per app lifecycle - but they aren't thread safe, so create them once per Thread
        Playwright.create(PlaywrightCreateOptions).use { playwright ->
            playwright.chromium().launch(BrowserLaunchOptions).use { browser ->
                browser.newContext(BrowserContextOptions).use { context ->
                    val page = context.newPage()
//                    page.emulateMedia(Page.EmulateMediaOptions().setMedia(Media.PRINT)) // seems to have no effect
                    page.setContent(html)

                    val margin = "10mm" // as @page sets format and margin in HTML file, below settings seem to have no effect
                    val pdfBytes = page.pdf(Page.PdfOptions().setFormat("A4").setMargin(marginOf(margin)).setPreferCSSPageSize(false))
                    // but the running header and footer element of @page seems to be ignored, they remain in the normal page flow, therefore:
                    // TODO: set header- and footer template

                    pdfBytes
                }
            }
        }


    private fun marginOf(all: String) = Margin().setTop(all).setLeft(all).setRight(all).setBottom(all)

}