package pages

import com.microsoft.playwright.Page
import com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat
import data.TestData
import io.qameta.allure.Step

class LoginPage(page: Page) : BasePage(page){

    private val usernameInput = page.locator("[data-test='username']")
    private val passwordInput = page.locator("[data-test='password']")
    private val loginButton = page.locator("[data-test='login-button']")
    private val loginContainer = page.locator("[data-test='login-container']")
    private val errorContainer = page.locator("[data-test='error']")

    private val loginUsernameInf = page.locator("[data-test='login-credentials']")
    private val loginPassInf = page.locator("[data-test='login-password']")

    @Step("Assert login page is visible")
    fun assertLoginPageIsLoaded(): LoginPage {
        assertThat(loginContainer).isVisible()
        return this
    }

    @Step("Verify page content")
    fun assertLoginUIElements(): LoginPage {
        assertThat(usernameInput).hasAttribute("placeholder", "Username")
        assertThat(passwordInput).hasAttribute("placeholder", "Password")

        assertThat(loginUsernameInf).containsText("Accepted usernames are:")
        assertThat(loginUsernameInf).containsText(TestData.Users.STANDARD_USER)
        assertThat(loginPassInf).containsText("Password for all users:")
        assertThat(loginPassInf).containsText(TestData.Passwords.PASSWORD)

        return this
    }

    @Step("Successfully logged in")
    fun login(username: String, password: String): ProductsPage {
        usernameInput.fill(username)
        passwordInput.fill(password)

        loginButton.click()
        val productsPage = ProductsPage(page)
        productsPage.assertProductsPageIsLoaded()

        return ProductsPage(page)
    }


    @Step("Assert login error message")
    fun assertLoginErrorMessage(username: String, password: String, error: String): LoginPage {
        usernameInput.fill(username)
        passwordInput.fill(password)

        loginButton.click()

        assertThat(errorContainer).containsText(error)
        return this
    }

}