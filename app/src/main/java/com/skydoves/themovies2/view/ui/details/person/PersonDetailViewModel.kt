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

package com.skydoves.themovies2.view.ui.details.person

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.switchMap
import com.skydoves.themovies2.base.DispatchViewModel
import com.skydoves.themovies2.models.network.PersonDetail
import com.skydoves.themovies2.repository.PeopleRepository
import timber.log.Timber

class PersonDetailViewModel constructor(
  private val peopleRepository: PeopleRepository
) : DispatchViewModel() {

  private val personIdLiveData: MutableLiveData<Int> = MutableLiveData()
  val personLiveData: LiveData<PersonDetail?>

  private val isLoading: ObservableBoolean = ObservableBoolean(false)

  init {
    Timber.d("Injection : PersonDetailViewModel")

    this.personLiveData = personIdLiveData.switchMap { id ->
      launchOnViewModelScope {
        isLoading.set(true)
        peopleRepository.loadPersonDetail(id) {
          isLoading.set(false)
        }.asLiveData()
      }
    }
  }

  fun postPersonId(id: Int) = personIdLiveData.postValue(id)
}
