package pages

import com.microsoft.playwright.Locator
import com.microsoft.playwright.Page
import com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat
import dto.ProductDto
import io.qameta.allure.Step
import org.assertj.core.api.Assertions

class ProductsPage(page: Page) : BasePage(page) {


    private val inventoryContainer= page.locator("[data-test='inventory-container']")

    private val sortContainer= page.locator("[data-test='product-sort-container']")

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

    @Step("Get all products details")
    fun getAllProductsDetails(): List<ProductDto> {
        return itemContainer.all().map { it ->
            ProductDto(
                title = it.locator(itemTitle).textContent(),
                description = it.locator(itemDesc).textContent(),
                price = it.locator(itemPrice).textContent()
            )
        }
    }

    @Step("Apply sorting: {sortName}")
    fun selectSort(sortName: String): ProductsPage {
        sortContainer.selectOption(sortName)
        return this
    }

    @Step("Assert product items order")
    fun assertProductsPageItemsOrder(actual:List<ProductDto>, expected:List<ProductDto> ): ProductsPage {
        Assertions.assertThat(actual).containsExactlyElementsOf(expected)
        return this
    }


}
