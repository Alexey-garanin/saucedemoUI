package login

import BaseUiTest
import pages.LoginPage
import org.junit.jupiter.api.Test
import data.LoginScenario
import io.qameta.allure.Epic
import io.qameta.allure.Story
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource

@Epic("Login page")
class LoginTest: BaseUiTest() {

    @Test
    @Story("UI Elements")
    @DisplayName("Checking the UI elements of the login page")
    fun checkLoginPageUI() {
        LoginPage(page)
            .assertLoginPageIsLoaded()
            .assertLoginUIElements()
    }

    @ParameterizedTest(name = "#{index}: {0}")
    @Story("Positive Scenarios")
    @MethodSource("data.LoginDataProviders#validLogins")
    @DisplayName("Successfully logged")
    fun loginSuccessForAllUsers(scenario: LoginScenario) {
        val loginSteps = LoginPage(page)
        loginSteps.assertLoginPageIsLoaded()
        loginSteps.login(scenario.username, scenario.password)
    }


    @ParameterizedTest(name = "#{index}: {0}")
    @Story("Negative Scenarios")
    @MethodSource("data.LoginDataProviders#invalidLogins")
    @DisplayName("Login error checking")
    fun checkLoginErrors(scenario: LoginScenario){
        val loginSteps = LoginPage(page)
        loginSteps.assertLoginPageIsLoaded()

        loginSteps.assertLoginErrorMessage(scenario.username, scenario.password, scenario.error!!)
    }


}