package com.intive.picover.shared.common.toast

import android.content.Context
import android.widget.Toast
import io.kotest.core.spec.style.ShouldSpec
import io.mockk.Runs
import io.mockk.coEvery
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.mockkStatic
import io.mockk.unmockkAll
import io.mockk.verify
import kotlin.reflect.KSuspendFunction1
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.getString

internal class ToastPublisherTest : ShouldSpec(
	{

		val context: Context = mockk()
		val tested = ToastPublisher(context)

		beforeSpec {
			val getStringFunction: KSuspendFunction1<StringResource, String> = ::getString
			mockkStatic(Toast::class)
			mockkStatic(getStringFunction)
		}

		afterSpec {
			unmockkAll()
		}

		should("create and show toast with given message WHEN show called") {
			val stringResource: StringResource = mockk()
			coEvery { getString(stringResource) } returns "Message"
			every { Toast.makeText(any(), any<String>(), any()).show() } just Runs

			tested.show(stringResource)

			verify { Toast.makeText(context, "Message", Toast.LENGTH_LONG) }
		}
	},
)
