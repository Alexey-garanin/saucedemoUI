package dto

import com.microsoft.playwright.Locator


class ProductContext(
    val dto: ProductDto,
    val itemLocator: Locator
)

data class ProductDto(
    val title : String,
    val description : String,
    val price : String
)