package item

import BaseUiTest
import data.TestData
import io.qameta.allure.Epic
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import pages.LoginPage
import utils.Assertions

@Epic("Item page")
class ItemTest: BaseUiTest() {

    @Test
    @DisplayName("Verify navigation from product info page to products page")
    fun verifyNavigationToProductsPage() {
        val productsPage = LoginPage(page)
            .login(TestData.Users.STANDARD_USER, TestData.Passwords.PASSWORD)

        val product = productsPage.getProductDetails()

        productsPage.openProductCard(product.itemLocator)
            .backToProducts()
    }

    @Test
    @DisplayName("Product details should match between card and cart")
    fun verifyProductDetailsConsistencyBetweenCardAndCart() {
        val productsPage = LoginPage(page)
            .login(TestData.Users.STANDARD_USER, TestData.Passwords.PASSWORD)

        val itemPage = productsPage.openProductCard(productsPage.getProductDetails().itemLocator)
        val productFromCard = itemPage.getProductDetails()

        itemPage.addToCart(productFromCard)

        val productFromCart = itemPage.openCart().getProductDetails()

        Assertions.assertMatch(productFromCard.dto, productFromCart.dto)
    }

    @Test
    @DisplayName("Remove item from cart on item page")
    fun removeItemFromCartAtItemPage() {
        val productsPage = LoginPage(page)
            .login(TestData.Users.STANDARD_USER, TestData.Passwords.PASSWORD)

        val itemPage = productsPage.openProductCard(productsPage.getProductDetails().itemLocator)
        val productFromCard = itemPage.getProductDetails()

        itemPage.addToCart(productFromCard)
        itemPage.removeItemFromCart(productFromCard)

        itemPage.openCart()
            .assertThatCartIsEmpty()
    }


}