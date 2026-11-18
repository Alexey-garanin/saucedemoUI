package pages

import com.microsoft.playwright.Page
import dto.ProductContext
import dto.ProductDto
import io.qameta.allure.Step
import org.assertj.core.api.Assertions
import com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat

open class BasePage(protected  val page: Page) {

    val titlePage = page.locator("[data-test='title']")

    //item
    val itemContainer = page.locator("[data-test='inventory-item']")
    val itemTitle = page.locator("[data-test='inventory-item-name']")
    val itemDesc = page.locator("[data-test='inventory-item-desc']")
    val itemPrice = page.locator("[data-test='inventory-item-price']")
    val itemAddBtn = page.locator("[data-test^='add-to-cart']")
    val itemRemoveBtn = page.locator("[data-test^='remove']")

    val cartIcon = page.locator("[data-test='shopping-cart-link']")
    val cartIconCounter = page.locator("[data-test='shopping-cart-badge']")


    @Step("Get product details")
    fun getProductDetails(): ProductContext  {
        Assertions.assertThat(itemContainer.count()).isGreaterThan(0)
        val randomItem = itemContainer.all().random()

        val dto = ProductDto(
            title = randomItem.locator(itemTitle).textContent(),
            description = randomItem.locator(itemDesc).textContent(),
            price = randomItem.locator(itemPrice).textContent()
        )
        return ProductContext(dto, randomItem)
    }

    @Step("Add product to cart: {product.dto.title}")
    fun addToCart(product: ProductContext) {
        product.itemLocator.locator(itemAddBtn).click()
        assertThat(product.itemLocator.locator(itemRemoveBtn)).isVisible()
        assertThat(cartIconCounter).isVisible()
    }

    @Step("Open shopping cart")
    fun openCart(): CartPage{
        cartIcon.click()
        return CartPage(page).assertCartPageIsLoaded()
    }

    @Step("Remove item from cart")
    fun removeItemFromCart(product: ProductContext) {
        itemRemoveBtn.click()
        assertThat(product.itemLocator.locator(itemAddBtn)).isVisible()
        assertThat(cartIconCounter).isHidden()
    }

}
