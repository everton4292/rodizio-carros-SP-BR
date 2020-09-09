package com.everton.rodzio_sp_android

import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Test

class UnitTest {
    @Test
    fun `should return true when a valid number is passed as a String parameter`() {
        val validNumber = mockk<ViewModelSp>()

        every { validNumber.isValidNumber("4") } returns true

        validNumber.isValidNumber("4")

        verify { validNumber.isValidNumber("4") }

    }

    @Test
    fun `should return false when a invalid number is passed as a String parameter`(){
        val notValidNumber = mockk<ViewModelSp>()

        every { notValidNumber.isValidNumber("N") } returns false

        notValidNumber.isValidNumber("N")

        verify { notValidNumber.isValidNumber("N") }
    }
}