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

package com.skydoves.themovies2.view.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.skydoves.bindables.BindingFragment
import com.skydoves.themovies2.R
import com.skydoves.themovies2.databinding.MainFragmentStarBinding
import com.skydoves.themovies2.view.adapter.PeopleAdapter
import org.koin.android.viewmodel.ext.android.getViewModel

class PersonListFragment :
  BindingFragment<MainFragmentStarBinding>(R.layout.main_fragment_star) {

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    super.onCreateView(inflater, container, savedInstanceState)
    return binding {
      viewModel = getViewModel<MainActivityViewModel>().apply { postPeoplePage(1) }
      lifecycleOwner = this@PersonListFragment
      adapter = PeopleAdapter()
    }.root
  }
}
