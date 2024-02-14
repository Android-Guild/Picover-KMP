package com.intive.picover.shared.common.validator

import org.jetbrains.compose.resources.StringResource
import picover.shared.generated.resources.Res

sealed class ValidationStatus(val errorMessageId: StringResource?) {
	data object EmptyText : ValidationStatus(Res.string.TextShouldNotBeEmpty)
	data object BlankText : ValidationStatus(Res.string.TextShouldNotBeBlank)
	data object TooLongText : ValidationStatus(Res.string.TextIsTooLong)
	data object ValidText : ValidationStatus(null)
}
