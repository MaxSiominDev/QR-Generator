package dev.maxsiomin.qr.util

import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LoginUtilTest {

    private lateinit var email: Email
    private lateinit var password: Password

    @Test
    fun incorrectEmailFails() {
        email = Email("something")
        Assert.assertFalse(email.isCorrect)
        email = Email("something.")
        Assert.assertFalse(email.isCorrect)
        email = Email("something.something")
        Assert.assertFalse(email.isCorrect)
        email = Email("something.something@")
        Assert.assertFalse(email.isCorrect)
        email = Email("something@.")
        Assert.assertFalse(email.isCorrect)
        email = Email("")
        Assert.assertFalse(email.isCorrect)
    }

    @Test
    fun correctEmailPasses() {
        email = Email("something@something.something")
        Assert.assertTrue(Email("something@something.something").isCorrect)
    }

    @Test
    fun emptyPasswordFails() {
        password = Password("")
        Assert.assertFalse(password.isStrong)
        Assert.assertTrue(password.isEmpty)
    }

    @Test
    fun shortPasswordFails() {
        password = Password("qwerty")
        Assert.assertFalse(password.isStrong)
        Assert.assertFalse(password.isEmpty)
    }

    @Test
    fun goodPasswordPasses() {
        password = Password("12co78qw9836")
        Assert.assertTrue(password.isStrong)
        Assert.assertFalse(password.isEmpty)
    }
}
