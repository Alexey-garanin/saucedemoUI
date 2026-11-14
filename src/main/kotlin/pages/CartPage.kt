package pages

import com.microsoft.playwright.Page
import com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat
import io.qameta.allure.Step

class CartPage(page: Page) : BasePage(page) {

    private val cartContainer = page.locator("[data-test='cart-contents-container']")
    private val continueShoppingBtn = page.locator("[data-test='continue-shopping']")
    private val checkoutBtn = page.locator("[data-test='checkout']")

    @Step("Assert cart page is visible")
    fun assertCartPageIsLoaded(): CartPage {
        assertThat(cartContainer).isVisible()
        assertThat(continueShoppingBtn).isVisible()
        assertThat(checkoutBtn).isVisible()
        return this
    }


}