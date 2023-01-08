package feature.domaintypes.validated

sealed class StringRangeValidationError(val msg: String) {
    data class MinLength(val value: Int) : StringRangeValidationError("below length of $value")
    data class MaxLength(val value: Int) : StringRangeValidationError("exceeded length of $value")
}