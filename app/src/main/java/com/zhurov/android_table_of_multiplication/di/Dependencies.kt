package com.zhurov.android_table_of_multiplication.di

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import com.zhurov.android_table_of_multiplication.components.navigation.NavigationActionProvider
import com.zhurov.android_table_of_multiplication.components.navigation.NavigationViewModel
import com.zhurov.android_table_of_multiplication.components.navigation.NavigationViewModelFactory
import com.zhurov.android_table_of_multiplication.components.navigation.Navigator
import com.zhurov.android_table_of_multiplication.components.navigation.NavigatorImpl
import com.zhurov.android_table_of_multiplication.feature.game.domain.arguments.GameArguments
import com.zhurov.android_table_of_multiplication.feature.game.domain.factory.GameViewModelFactory
import com.zhurov.android_table_of_multiplication.feature.game.presentation.GameViewModel
import com.zhurov.android_table_of_multiplication.feature.game_result.domain.factory.GameResultViewModelFactory
import com.zhurov.android_table_of_multiplication.feature.game_result.presentation.GameResultViewModel
import com.zhurov.android_table_of_multiplication.feature.login.domain.factory.LoginViewModelFactory
import com.zhurov.android_table_of_multiplication.feature.login.presentation.LoginViewModel
import com.zhurov.android_table_of_multiplication.feature.root.domain.factory.MainViewModelFactory
import com.zhurov.android_table_of_multiplication.feature.root.presentation.MainActivity
import com.zhurov.android_table_of_multiplication.feature.root.presentation.MainViewModel
import com.zhurov.android_table_of_multiplication.feature.settings.domain.factory.SettingsViewModelFactory
import com.zhurov.android_table_of_multiplication.feature.settings.presentation.SettingsViewModel

lateinit var appContext: Context

lateinit var mainActivity: MainActivity

val navigationViewModelFactory by lazy {
    val navigator = NavigatorImpl()
    NavigationViewModelFactory(
        navigator = navigator,
        navigatorProvider = navigator
    )
}

val navigator: Navigator by lazy {
    ViewModelProvider(mainActivity.viewModelStore, navigationViewModelFactory)
        .get(NavigationViewModel::class.java).navigator
}

val navigationProvider: NavigationActionProvider by lazy {
    ViewModelProvider(mainActivity.viewModelStore, navigationViewModelFactory)
        .get(NavigationViewModel::class.java).navigatorProvider
}

val ViewModelStoreOwner.mainViewModel: MainViewModel
    get() {
        val mainViewModelFactory = MainViewModelFactory(navigator)
        return ViewModelProvider(viewModelStore, mainViewModelFactory)
            .get(MainViewModel::class.java)
    }

val ViewModelStoreOwner.loginViewModel: Lazy<LoginViewModel>
    get() = lazy {
            val loginViewModelFactory = LoginViewModelFactory(navigator)
            ViewModelProvider(viewModelStore, loginViewModelFactory)
                .get(LoginViewModel::class.java)
        }

val ViewModelStoreOwner.settingsViewModel: Lazy<SettingsViewModel>
    get() = lazy {
        val settingsViewModelFactory = SettingsViewModelFactory(navigator)
        ViewModelProvider(viewModelStore, settingsViewModelFactory)
            .get(SettingsViewModel::class.java)
    }

fun ViewModelStoreOwner.gameViewModel(gameArguments: GameArguments): GameViewModel {
        val gameViewModelFactory = GameViewModelFactory(navigator, gameArguments)
        return ViewModelProvider(viewModelStore, gameViewModelFactory)
            .get(GameViewModel::class.java)
    }

val ViewModelStoreOwner.gameResultViewModel: Lazy<GameResultViewModel> get() = lazy {
        val gameResultViewModelFactory = GameResultViewModelFactory(navigator)
        ViewModelProvider(viewModelStore, gameResultViewModelFactory)
            .get(GameResultViewModel::class.java)
}