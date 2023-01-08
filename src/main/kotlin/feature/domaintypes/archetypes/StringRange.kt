package feature.domaintypes.archetypes

abstract class StringRange(
    value: String,
    minLengthIncluded: Int,
    maxLengthIncluded: Int
) : Valuable<String> {

    init {
        if (!validate(value, minLengthIncluded, maxLengthIncluded)) throw IllegalArgumentException("Value '$value' is invalid")
    }

    companion object {

        fun validate(value: String, minLengthIncluded: Int, maxLengthIncluded: Int): Boolean {
            return value.length in minLengthIncluded..maxLengthIncluded
        }
    }
}