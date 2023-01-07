package feature.domaintypes.archetypes

abstract class StringRange(
    value: String,
    minLengthIncluded: Int,
    maxLengthIncluded: Int
) : Valuable<String> {

    init {
        if (!validate(value, minLengthIncluded, maxLengthIncluded)) throw IllegalArgumentException()
    }


    companion object {

        fun validate(value: String, minLength: Int, maxLength: Int): Boolean {
            return value.length in minLength..maxLength
        }
    }
}