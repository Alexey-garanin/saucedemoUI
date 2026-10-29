package pages

import com.microsoft.playwright.Page

class LoginPage(page: Page) : BasePage(page){

    private val usernameInput = page.locator("[data-test='username']")
    private val passwordInput = page.locator("[data-test='password']")
    private val loginButton = page.locator("[data-test='login-button']")






    //tbu edit return page
    fun login(username: String, password: String): LoginPage {
        usernameInput.fill(username)
        passwordInput.fill(password)

        loginButton.click()
        return this
    }

}