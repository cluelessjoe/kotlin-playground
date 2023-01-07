package feature.domaintypes.archetypes

abstract class StringRange(
    value: String,
    minLength: Int,
    maxLength: Int
) : Valuable<String> {

    init {
        if (!validate(value, minLength, maxLength)) throw IllegalArgumentException()
    }


    companion object {

        fun validate(value: String, minLength: Int, maxLength: Int): Boolean {
            return value.length in minLength..maxLength
        }
    }
}