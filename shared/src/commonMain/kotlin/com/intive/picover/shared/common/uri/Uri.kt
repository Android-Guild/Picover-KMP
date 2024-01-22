package com.intive.picover.shared.common.uri

import dev.gitlive.firebase.storage.File

expect class Uri {
	val data: String
	fun toFile(): File
}
