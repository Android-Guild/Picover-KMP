package com.intive.picover.parties.model

import java.io.Serializable

data class AddPartyResult(
	val title: String,
	val description: String,
) : Serializable
