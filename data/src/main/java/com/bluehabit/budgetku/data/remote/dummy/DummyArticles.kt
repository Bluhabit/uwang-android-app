/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.data.remote.dummy

import com.bluehabit.budgetku.data.R
import com.bluehabit.budgetku.data.model.article.ArticleModel
import java.time.LocalDateTime

val dummyArticles: List<ArticleModel> = listOf(
    ArticleModel(
        title = "Pusat Bantuan",
        body = "Punya kendala atau pertanyyan terkait Budgetku? kamu bisa kirim di sini",
        image = R.drawable.ic_dummy_article,
        date = "11 April 2023, 22:54",
        likes = 200
    ),
    ArticleModel(
        title = "Cerdas Finansial",
        body = "Yuk melek finansial bersama Budgetku. Tersedia course keuangan untukmu",
        image = R.drawable.ic_dummy_article_2,
        date = "11 April 2023, 22:54",
        likes = 300
    ),
    ArticleModel(
        title = "Promo & Hadiah",
        body = "Yuk cek berbagai promo menarik di aplikasi Budgetku Sekarang!",
        image = R.drawable.ic_dummy_article_3,
        date = "11 April 2023, 22:54",
        likes = 10
    ),
    ArticleModel(
        title = "Tips Keuangan",
        body = "Bingung ngatur budget? disini kamu bisa tahu tips ngatur keuangan",
        image = R.drawable.ic_dummy_article_4,
        date = "11 April 2023, 22:54",
        likes = 355
    )
)