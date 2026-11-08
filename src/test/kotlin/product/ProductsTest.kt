package product

import BaseUiTest
import data.TestData
import io.qameta.allure.Epic
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import pages.LoginPage
import kotlin.test.Test

@Epic("Products page")
class ProductsTest: BaseUiTest(){


    @Test
    @DisplayName("Product details should match between list and card")
    fun verifyProductDetailsConsistency() {
        val loginPage = LoginPage(page)
        val productsPage = loginPage.login(TestData.Users.STANDARD_USER, TestData.Passwords.PASSWORD)

        val productFromList = productsPage.getProductDetails()
        val productFromCard = productsPage.openProductCard(productFromList.itemLocator).getProductDetails()

        productsPage.assertProductDetailsMatch(productFromList.dto, productFromCard.dto)

    }

}
