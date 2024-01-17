package com.intive.picover.shared.common.validator

import com.intive.picover.shared.MR
import dev.icerock.moko.resources.StringResource

sealed class ValidationStatus(val errorMessageId: StringResource?) {
	data object EmptyText : ValidationStatus(MR.strings.TextShouldNotBeEmpty)
	data object BlankText : ValidationStatus(MR.strings.TextShouldNotBeBlank)
	data object TooLongText : ValidationStatus(MR.strings.TextIsTooLong)
	data object ValidText : ValidationStatus(null)
}
