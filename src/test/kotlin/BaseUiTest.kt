import com.microsoft.playwright.Browser
import com.microsoft.playwright.BrowserContext
import com.microsoft.playwright.BrowserType
import com.microsoft.playwright.Page
import com.microsoft.playwright.Playwright
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.extension.RegisterExtension
import utils.AllureScreenshotExtension

open class BaseUiTest {
    private lateinit var playwright: Playwright
    private lateinit var browser: Browser
    private lateinit var context: BrowserContext
    protected lateinit var page: Page

    protected val defaultTimeout: Double = 2000.0


    @RegisterExtension
    val screenshotExtension = AllureScreenshotExtension { page }

    @BeforeEach
    fun setUp() {
        playwright = Playwright.create()
        browser = playwright.chromium().launch(
            BrowserType.LaunchOptions()
                .setHeadless(true)
        )
        context = browser.newContext()
        page = context.newPage()
        page.navigate("https://www.saucedemo.com/")
    }

    @AfterEach
    fun tearDown() {

        try {
            page.close()
            context.close()
            browser.close()
            playwright.close()
        } catch (e: Exception) {
            println("Error: ${e.message}")
        }
    }
}