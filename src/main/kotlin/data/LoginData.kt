package data

import data.TestData as Data

data class LoginScenario(
    val username: String,
    val password: String,
    val error: String? = null,
    val description: String
) {
    override fun toString(): String = description
}

object LoginDataProviders{

    @JvmStatic
    fun validLogins() = listOf(
        LoginScenario(Data.Users.STANDARD_USER, Data.Passwords.PASSWORD, description = Data.Users.STANDARD_USER),
        LoginScenario(Data.Users.PROBLEM_USER, Data.Passwords.PASSWORD, description = Data.Users.PROBLEM_USER),
        LoginScenario(Data.Users.PERFORMANCE_GLITCH_USER, Data.Passwords.PASSWORD, description = Data.Users.PERFORMANCE_GLITCH_USER),
        LoginScenario(Data.Users.ERROR_USER, Data.Passwords.PASSWORD, description = Data.Users.ERROR_USER),
        LoginScenario(Data.Users.VISUAL_USER, Data.Passwords.PASSWORD, description = Data.Users.VISUAL_USER),
    )

    @JvmStatic
    fun invalidLogins() = listOf(
        LoginScenario("","", Data.LoginErrors.USERNAME_REQUIRED, "empty username and password"),
        LoginScenario("",Data.Passwords.PASSWORD, Data.LoginErrors.USERNAME_REQUIRED, "empty username"),
        LoginScenario(Data.Users.STANDARD_USER,"", Data.LoginErrors.PASSWORD_REQUIRED, "empty password"),
        LoginScenario(Data.Users.LOCKED_OUT_USER,Data.Passwords.PASSWORD, Data.LoginErrors.USER_LOCKED, Data.Users.LOCKED_OUT_USER),
        LoginScenario("new_user","secret_sauce2", Data.LoginErrors.INVALID_CREDENTIALS, "new_user"),
        LoginScenario(Data.Users.STANDARD_USER,"secret_sauce2", Data.LoginErrors.INVALID_CREDENTIALS, "standard user and wrong password"),
        )
}
