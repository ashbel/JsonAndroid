package com.mopanesystems.myapplication

import android.content.Context
import android.text.InputType
import androidx.test.platform.app.InstrumentationRegistry
import com.mopanesystems.myapplication.Builders.ViewBuilder
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun multiplication_isCorrect() {
        val viewBuilder = ViewBuilder()
        val result = viewBuilder.getInputType("numberSigned")
        assertEquals(result, InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_SIGNED )
    }


}