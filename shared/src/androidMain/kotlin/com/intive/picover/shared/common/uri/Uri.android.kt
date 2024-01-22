package com.intive.picover.shared.common.uri

import android.net.Uri
import dev.gitlive.firebase.storage.File

actual class Uri(actual val data: String) {
	constructor(data: Uri) : this(data.toString())

	actual fun toFile(): File =
		File(Uri.parse(data))
}
