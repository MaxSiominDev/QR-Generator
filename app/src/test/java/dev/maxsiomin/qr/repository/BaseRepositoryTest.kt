package dev.maxsiomin.qr.repository

import junit.framework.TestCase
import org.junit.Test

class BaseRepositoryTest {

    @Test
    fun `non-connection error returns false`() {
        TestCase.assertFalse(BaseRepository.isConnectionError("some error"))
    }

    @Test
    fun `connection error returns true`() {
        TestCase.assertTrue(BaseRepository.isConnectionError("unable to resolve host"))
    }
}

