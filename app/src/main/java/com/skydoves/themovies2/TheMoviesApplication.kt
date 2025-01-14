/*
 * Designed and developed by 2019 skydoves (Jaewoong Eum)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.skydoves.themovies2

import android.app.Application
import com.facebook.stetho.Stetho
import com.skydoves.sandwich.SandwichInitializer
import com.skydoves.themovies2.di.networkModule
import com.skydoves.themovies2.di.persistenceModule
import com.skydoves.themovies2.di.repositoryModule
import com.skydoves.themovies2.di.viewModelModule
import com.skydoves.themovies2.operator.GlobalResponseOperator
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

@Suppress("unused")
class TheMoviesApplication : Application() {

  override fun onCreate() {
    super.onCreate()

    startKoin {
      androidContext(this@TheMoviesApplication)
      modules(networkModule)
      modules(persistenceModule)
      modules(repositoryModule)
      modules(viewModelModule)
    }

    // initialize global sandwich operator
    SandwichInitializer.sandwichOperator = GlobalResponseOperator<Any>(this)

    if (BuildConfig.DEBUG) {
      Timber.plant(Timber.DebugTree())
    }

    Stetho.initializeWithDefaults(this)
  }
}
