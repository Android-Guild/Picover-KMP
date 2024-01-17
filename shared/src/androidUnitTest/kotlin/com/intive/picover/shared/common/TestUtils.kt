package com.intive.picover.shared.common

import io.mockk.MockKStubScope

fun <T> mockkAnswer(block: MockKStubScope<T, T>.() -> Unit) =
	@Suppress("ktlint:standard:function-naming")
	fun MockKStubScope<T, T>.() {
		block()
	}
