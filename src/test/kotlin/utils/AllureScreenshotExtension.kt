package utils

import com.microsoft.playwright.Page
import io.qameta.allure.Allure
import org.junit.jupiter.api.extension.AfterTestExecutionCallback
import org.junit.jupiter.api.extension.ExtensionContext
import java.io.ByteArrayInputStream

class AllureScreenshotExtension(private val pageProvider: () -> Page) : AfterTestExecutionCallback {

    override fun afterTestExecution(context: ExtensionContext) {
        context.executionException.ifPresent {
            try {
                val screenshot = pageProvider().screenshot()
                Allure.addAttachment(
                    "Failure Screenshot",
                    "image/png",
                    ByteArrayInputStream(screenshot),
                    "png"
                )
            } catch (e: Exception) {
                println("Не удалось сделать скриншот: ${e.message}")
            }
        }
    }
}