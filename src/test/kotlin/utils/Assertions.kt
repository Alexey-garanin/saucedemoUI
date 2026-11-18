package utils

import dto.ProductDto
import io.qameta.allure.Step
import org.assertj.core.api.Assertions

object Assertions {

    @Step("Verify product details match")
    fun assertMatch(firstDto: ProductDto, secondDto: ProductDto) {
        Assertions.assertThat(firstDto).isEqualTo(secondDto)
    }

}
