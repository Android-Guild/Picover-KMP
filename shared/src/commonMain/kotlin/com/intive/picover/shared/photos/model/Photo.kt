package com.intive.picover.shared.photos.model

import kotlin.random.Random

data class Photo(
	val height: Int,
	val width: Int,
	val url: String,
) {
	companion object {
		fun withRandomSize(url: String) =
			Photo(
				height = Random.nextInt(100, 200),
				width = Random.nextInt(200, 500),
				url = url,
			)
	}
}
