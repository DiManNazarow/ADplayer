package dmitriy_nazarov.ru.adplayer.utils

class TextUtils {

    companion object {

        fun isTextEquals(textFirst: String?, teatSecond: String?): Boolean {
            return textFirst != null && teatSecond != null && textFirst.contentEquals(teatSecond)
        }

    }

}