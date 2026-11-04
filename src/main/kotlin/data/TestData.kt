package data

object TestData {

    object Users {
        const val STANDARD_USER = "standard_user"
        const val LOCKED_OUT_USER = "locked_out_user"
        const val PROBLEM_USER = "problem_user"
        const val PERFORMANCE_GLITCH_USER = "performance_glitch_user"
        const val ERROR_USER = "error_user"
        const val VISUAL_USER = "visual_user"
    }

    object Passwords {
        const val PASSWORD = "secret_sauce"
    }

    object LoginErrors {
        const val USERNAME_REQUIRED = "Epic sadface: Username is required"
        const val PASSWORD_REQUIRED = "Epic sadface: Password is required"
        const val INVALID_CREDENTIALS = "Epic sadface: Username and password do not match any user in this service"
        const val USER_LOCKED = "Epic sadface: Sorry, this user has been locked out."
    }
}
