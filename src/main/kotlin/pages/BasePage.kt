package pages

import com.microsoft.playwright.Page

open class BasePage(protected  val page: Page) {
    val titlePage = page.locator("[data-test='title']")



}