package dev.maxsiomin.qr.extensions

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class StringKtTest {

    @Test
    fun stringToEditableToString_isSame() {
        val strings = listOf("", "something")

        strings.forEach { string ->
            assertThat(string.toEditable().toString()).isEqualTo(string)
        }
    }
}
