package pages

import com.microsoft.playwright.Locator
import com.microsoft.playwright.Page
import com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat
import data.ProductDto
import io.qameta.allure.Step
import org.assertj.core.api.Assertions

class ProductsPage(page: Page) : BasePage(page) {


    private val inventoryContainer= page.locator("[data-test='inventory-container']")

    @Step("Assert products page is visible")
    fun assertProductsPageIsLoaded(): ProductsPage {
        assertThat(inventoryContainer).isVisible()
        return this
    }

    @Step("Open product card")
    fun openProductCard(product: Locator): ItemPage {
        product.locator(itemTitle).click()

        val itemPage = ItemPage(page)
        itemPage.assertItemPageIsLoaded()
        return itemPage
    }

}
