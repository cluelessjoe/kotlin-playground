package feature.domaintypes.validated

import arrow.core.*

abstract class StringRange(
    value: String,
    minLengthIncluded: Int,
    maxLengthIncluded: Int
) : Valuable<String> {

    init {
        val validationErrors = validationErrors(value, minLengthIncluded, maxLengthIncluded)
        if (validationErrors.isNotEmpty()) {
            throw IllegalStateException("Value '$value' has the following issue(s): ${validationErrors.joinToString { e -> e.msg }}")
        }
    }

    companion object {

        private fun validationErrors(
            value: String,
            minLength: Int,
            maxLength: Int,
        ): List<StringRangeValidationError> {
            return if (value.length < minLength)
                listOf(StringRangeValidationError.MinLength(minLength))
            else if (value.length > maxLength)
                listOf(StringRangeValidationError.MaxLength(maxLength))
            else listOf()
        }

        fun <T : StringRange> validate(
            value: String,
            minLength: Int,
            maxLength: Int,
            factory: (String) -> T
        ): Validated<Nel<StringRangeValidationError>, T> {
            val validationErrors = validationErrors(value, minLength, maxLength)
            return if (validationErrors.isEmpty())
                Valid(factory(value))
            else Invalid(NonEmptyList.fromListUnsafe(validationErrors))
        }
    }
}