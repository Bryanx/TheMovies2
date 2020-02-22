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

package com.skydoves.themovies2.view.viewholder

import android.view.View
import com.bumptech.glide.Glide
import com.github.florent37.glidepalette.BitmapPalette
import com.github.florent37.glidepalette.GlidePalette
import com.skydoves.baserecyclerviewadapter.BaseViewHolder
import com.skydoves.themovies2.api.Api
import com.skydoves.themovies2.models.entity.Tv
import com.skydoves.themovies2.view.ui.details.tv.TvDetailActivity
import kotlinx.android.synthetic.main.item_poster.view.*

class TvListViewHolder(val view: View) : BaseViewHolder(view) {

  private lateinit var tv: Tv

  @Throws(Exception::class)
  override fun bindData(data: Any) {
    if (data is Tv) {
      tv = data
      drawItem()
    }
  }

  private fun drawItem() {
    itemView.run {
      item_poster_title.text = tv.name
      tv.poster_path?.let {
        Glide.with(context)
          .load(Api.getPosterPath(it))
          .listener(GlidePalette.with(Api.getPosterPath(it))
            .use(BitmapPalette.Profile.VIBRANT)
            .intoBackground(item_poster_palette)
            .crossfade(true))
          .into(item_poster_post)
      }
    }
  }

  override fun onClick(v: View?) = TvDetailActivity.startActivityModel(context(), tv)

  override fun onLongClick(p0: View?) = false
}
