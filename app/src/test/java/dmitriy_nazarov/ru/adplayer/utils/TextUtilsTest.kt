package dmitriy_nazarov.ru.adplayer.utils

import org.junit.Assert.*
import org.junit.Test

class TextUtilsTest {

    @Test
    fun checkIfTextIsEquals() {
        assertTrue(TextUtils.isTextEquals("test", "test"))
        assertFalse(TextUtils.isTextEquals(null, null))
        assertFalse(TextUtils.isTextEquals("", "test"))
        assertFalse(TextUtils.isTextEquals(null, "test"))
        assertTrue(TextUtils.isTextEquals("", ""))
    }

    @Test
    fun checkTextIsEmpty() {
        assertTrue(TextUtils.isTextEmpty(""))
        assertTrue(TextUtils.isTextEmpty(null))
        assertFalse(TextUtils.isTextEmpty("text"))
    }

}