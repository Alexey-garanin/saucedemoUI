package product

import BaseUiTest
import data.TestData
import io.qameta.allure.Epic
import io.qameta.allure.Story
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import pages.LoginPage
import kotlin.test.Test

@Epic("Products page")
class ProductsTest: BaseUiTest(){

    //[tbu] 1.remove test 2.add several products to card 3.burger-menu tests

    @Test
    @Story("Data Consistency")
    @DisplayName("Product details should match between list and card")
    fun verifyProductDetailsConsistency() {
        val loginPage = LoginPage(page)
        val productsPage = loginPage.login(TestData.Users.STANDARD_USER, TestData.Passwords.PASSWORD)

        val productFromList = productsPage.getProductDetails()
        val productFromCard = productsPage.openProductCard(productFromList.itemLocator).getProductDetails()

        productsPage.assertProductDetailsMatch(productFromList.dto, productFromCard.dto)
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("data.ProductsPageData#sortScenarios")
    @Story("Sorting Scenarios")
    @DisplayName("Verify product sorting")
    fun verifySorting(sortName: String) {
        val loginPage = LoginPage(page)
        val productsPage = loginPage.login(TestData.Users.STANDARD_USER, TestData.Passwords.PASSWORD)

        val default = productsPage.getAllProductsDetails()

        val expected = when (sortName) {
            "Name (A to Z)"       -> default.sortedBy { it.title }
            "Name (Z to A)"       -> default.sortedByDescending { it.title }
            "Price (low to high)" -> default.sortedBy { it.price.removePrefix("$").toDouble() }
            "Price (high to low)" -> default.sortedByDescending { it.price.removePrefix("$").toDouble() }
            else -> throw IllegalArgumentException("Unknown sort type: $sortName")
        }

        productsPage.selectSort(sortName)
        val actual = productsPage.getAllProductsDetails()
        productsPage.assertProductsPageItemsOrder(actual,expected)
    }

    @Test
    @Story("Data Consistency")
    @DisplayName("Product details should match between list and cart")
    fun verifyProductDetailsConsistencyBetweenListAndCart(){
        val loginPage = LoginPage(page)
        val productsPage = loginPage.login(TestData.Users.STANDARD_USER, TestData.Passwords.PASSWORD)

        val productFromList = productsPage.getProductDetails()
        productsPage.addToCart(productFromList)

       val productFromCart = productsPage.openCart().getProductDetails()

       productsPage.assertProductDetailsMatch(productFromList.dto, productFromCart.dto)

    }


}
