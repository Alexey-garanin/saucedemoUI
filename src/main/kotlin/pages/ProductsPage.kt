package pages

import com.microsoft.playwright.Page
import com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat
import io.qameta.allure.Step

class ProductsPage(page: Page) : BasePage(page) {


    private val inventoryContainer= page.locator("[data-test='inventory-container']")

    @Step("Assert products page is visible")
    fun assertProductsPageIsLoaded(): ProductsPage {
        assertThat(inventoryContainer).isVisible()
        return this
    }

}