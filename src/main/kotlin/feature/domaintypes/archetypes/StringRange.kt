package feature.domaintypes.archetypes

import kotlinlang.dataclass.DataClassConstructorReturningNullable

abstract class StringRange(
    value: String,
    minLength: Int,
    maxLength: Int
) {

    init {
        if (!validate(value, minLength, maxLength)) throw IllegalArgumentException()
    }


    companion object {

        fun validate(value: String, minLength: Int, maxLength: Int): Boolean {
            return value.length in minLength..maxLength
        }

        operator fun invoke(
            value: String,
            minLength: Int,
            maxLength: Int
        ): DataClassConstructorReturningNullable? {
            return if (validate(value, minLength, maxLength))
                DataClassConstructorReturningNullable(value)
            else
                null
        }

    }
}