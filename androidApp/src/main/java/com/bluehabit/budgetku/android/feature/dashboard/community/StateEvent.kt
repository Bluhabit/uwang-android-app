/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.android.feature.dashboard.community

import android.os.Parcelable
import com.bluehabit.budgetku.android.R
import com.bluehabit.budgetku.data.model.article.ArticleModel
import com.bluehabit.budgetku.data.model.post.ContentBudgetingPostModel
import com.bluehabit.budgetku.data.model.post.PostModel
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue
import java.math.BigDecimal
import java.time.LocalDateTime
import javax.annotation.concurrent.Immutable

@Immutable
@Parcelize
data class CommunityState(
    val selectedTab:Int=0
) : Parcelable

@Immutable
@Parcelize
data class CommunityDataState(
    val tabs: @RawValue List<String> = listOf(
        "Terbaru",
        "Followed",
        "Semua",
        "Trending",
        "Untukmu"
    ),
    val posts:@RawValue List<PostModel> = listOf(
        PostModel(
            displayName = "Devianrahmani",
            date = LocalDateTime.now(),
            avatar = com.bluehabit.budgetku.data.R.drawable.dummy_avatar,
            body = "Inget selalu bedakan pengeluaran antara kebutuhan dan keinginan. Jangan lupa juga buat selalu catet" +
                    " pengeluaran biar kakak tau tuh alokasi keuangan selama satu bulan itu kemana aja.",
            comments = 12,
            likes = 321,
            content = listOf(),
            mimeContent = com.bluehabit.budgetku.data.R.drawable.dummy_image_post,
            postType = 1
        ),
        PostModel(
            displayName = "KholodFahmi",
            date = LocalDateTime.now(),
            avatar = com.bluehabit.budgetku.data.R.drawable.dummy_avatar,
            body = "Hi! kembali lagi dengan Albon nih, seperti biasa sekarang mau share template alokasi keuangan untuk kamu pakai." +
                    " Bagian yang Rp5.000.000 nih!",
            comments = 12,
            likes = 321,
            content = listOf(
                ContentBudgetingPostModel(
                    icon =com.bluehabit.budgetku.data.R.drawable.ic_dummy_food,
                    name = "Living",
                    allocation = "Alokasi 60%",
                    amount = BigDecimal(3_000_000)
                ),
                ContentBudgetingPostModel(
                    icon = com.bluehabit.budgetku.data.R.drawable.ic_dummy_challenge,
                    name = "Saving",
                    allocation = "Alokasi 30%",
                    amount = BigDecimal(1_500_000)
                ),
                ContentBudgetingPostModel(
                    icon = com.bluehabit.budgetku.data.R.drawable.ic_dummy_category,
                    name = "Playing",
                    allocation = "Alokasi 10%",
                    amount = BigDecimal(500_000)
                )
            ),
            mimeContent = com.bluehabit.budgetku.data.R.drawable.dummy_image_post,
            postType = 2
        ),
        PostModel(
            displayName = "Aisyah alqa",
            date = LocalDateTime.now(),
            avatar = com.bluehabit.budgetku.data.R.drawable.dummy_avatar,
            body = "Tau gak sih kamu? Promo di market place itu bikin kamu boncos loh. " +
                    "Walaupun ngerasa banyak kupon yang gak kepake tapi giliran kepake jadi tambah boncos wkwkw.",
            comments = 12,
            likes = 321,
            content = listOf(),
            mimeContent = com.bluehabit.budgetku.data.R.drawable.dummy_image_post,
            postType = 3
        )
    ),
    val articles: @RawValue List<ArticleModel> = listOf(
        ArticleModel(
            title = "Pusat Bantuan",
            body = "Punya kendala atau pertanyyan terkait Budgetku? kamu bisa kirim di sini",
            image = com.bluehabit.budgetku.data.R.drawable.ic_dummy_article,
            date = LocalDateTime.now(),
            likes = 200
        ),
        ArticleModel(
            title = "Cerdas Finansial",
            body = "Yuk melek finansial bersama Budgetku. Tersedia course keuangan untukmu",
            image = com.bluehabit.budgetku.data.R.drawable.ic_dummy_article_2,
            date = LocalDateTime.now(),
            likes = 200
        ),
        ArticleModel(
            title = "Promo & Hadiah",
            body = "Yuk cek berbagai promo menarik di aplikasi Budgetku Sekarang!",
            image =com.bluehabit.budgetku.data.R.drawable.ic_dummy_article_3,
            date = LocalDateTime.now(),
            likes = 200
        ),
        ArticleModel(
            title = "Tips Keuangan",
            body = "Bingung ngatur budget? disini kamu bisa tahu tips ngatur keuangan",
            image =com.bluehabit.budgetku.data.R.drawable.ic_dummy_article_4,
            date = LocalDateTime.now(),
            likes = 200
        )
    )
) : Parcelable


sealed interface CommunityEvent{

}