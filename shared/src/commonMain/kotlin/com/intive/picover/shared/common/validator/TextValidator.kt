package com.intive.picover.shared.common.validator

import com.intive.picover.shared.common.validator.ValidationStatus.BlankText
import com.intive.picover.shared.common.validator.ValidationStatus.EmptyText
import com.intive.picover.shared.common.validator.ValidationStatus.TooLongText
import com.intive.picover.shared.common.validator.ValidationStatus.ValidText

class TextValidator(
	private val allowEmpty: Boolean,
	private val allowBlank: Boolean,
	val maxLength: Int?,
) {

	fun validate(text: String) =
		when {
			!allowEmpty && text.isEmpty() -> EmptyText
			!allowBlank && text.isBlank() -> BlankText
			maxLength != null && text.length > maxLength -> TooLongText
			else -> ValidText
		}

	companion object {
		val Short = TextValidator(allowEmpty = false, allowBlank = false, maxLength = 20)
		val Long = TextValidator(allowEmpty = false, allowBlank = false, maxLength = 80)
	}
}
