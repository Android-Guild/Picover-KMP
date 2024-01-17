package com.intive.picover.shared.koin

import androidx.compose.material3.SnackbarHostState
import com.intive.picover.auth.repository.AuthRepository
import com.intive.picover.images.repository.ImagesRepository
import com.intive.picover.main.navigation.launcher.Launcher
import com.intive.picover.shared.party.data.repo.PartiesRepository
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.auth.auth
import dev.gitlive.firebase.firestore.firestore
import dev.gitlive.firebase.storage.storage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import org.koin.dsl.module

internal val mainModule = module {
	single { Launcher() }
	single { SnackbarHostState() }
	single { Firebase.storage.reference }
	factory { AuthRepository(Firebase.auth, get()) }
	factory { PartiesRepository(Firebase.firestore) }
	factory { ImagesRepository(get(), Dispatchers.IO) }
}
