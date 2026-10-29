package login

import BaseUiTest
import org.junit.jupiter.api.Assertions.assertTrue
import pages.LoginPage
import org.junit.jupiter.api.Test
import com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat




class LoginTest: BaseUiTest() {


    @Test
    fun testForScreenshot() {


        val fakeLocator = page.locator("[data-test='fake-element-123']")

        com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat(fakeLocator).isVisible()
    }

    @Test
    fun loginSuccess() {
        val loginSteps = LoginPage(page)
        assertThat(loginSteps.titlePage).isVisible()
        loginSteps.login("standard_user", "secret_sauce")

        page.waitForTimeout(defaultTimeout)
        page.waitForURL("**/inventory.html")

        assertTrue(page.isVisible(".inventory_list"))

        assertThat(loginSteps.titlePage).isVisible()

    }


}