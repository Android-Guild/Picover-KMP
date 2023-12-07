package com.intive.picover.shared.koin

import com.intive.picover.shared.getPlatform
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

internal val platformModule = module {
	singleOf(::getPlatform)
}
