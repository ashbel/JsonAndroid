package com.mopanesystems.myapplication

import android.text.InputType
import com.mopanesystems.myapplication.Builders.ViewBuilder
import org.junit.Assert.assertEquals
import org.junit.Test

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