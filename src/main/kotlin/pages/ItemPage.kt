package pages

import com.microsoft.playwright.Page
import com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat
import io.qameta.allure.Step

class ItemPage(page: Page) : BasePage(page){

    private val backToProducts = page.locator("[data-test='back-to-products']")



    @Step("Assert Item page is visible")
    fun assertItemPageIsLoaded(): ItemPage {
        assertThat(backToProducts).isVisible()
        assertThat(itemContainer).isVisible()
        return this
    }



}