package pages

import com.microsoft.playwright.Page
import data.ProductContext
import data.ProductDto
import io.qameta.allure.Step
import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat

open class BasePage(protected  val page: Page) {

    val titlePage = page.locator("[data-test='title']")

    //item
    val itemContainer = page.locator("[data-test='inventory-item']")
    val itemTitle = page.locator("[data-test='inventory-item-name']")
    val itemDesc = page.locator("[data-test='inventory-item-desc']")
    val itemPrice = page.locator("[data-test='inventory-item-price']")
    val itemAddBtn = page.locator("[data-test^='add-to-cart']")


    @Step("Get product details")
    fun getProductDetails(): ProductContext  {
        assertThat(itemContainer.count()).isGreaterThan(0)
        val randomItem = itemContainer.all().random()

        val dto = ProductDto(
            title = randomItem.locator(itemTitle).textContent(),
            description = randomItem.locator(itemDesc).textContent(),
            price = randomItem.locator(itemPrice).textContent()
        )
        return ProductContext(dto, randomItem)
    }

    @Step("Verify product details match between list and card")
    fun assertProductDetailsMatch(listDto: ProductDto, cardDto: ProductDto) {
        Assertions.assertThat(cardDto).isEqualTo(listDto)
    }

}
