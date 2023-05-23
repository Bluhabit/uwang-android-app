/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.data.remote.dummy

import com.bluehabit.budgetku.data.model.article.ArticleModel

val dummyArticles: List<ArticleModel> = listOf(
    ArticleModel(
        title = "Pusat Bantuan",
        body = "Punya kendala atau pertanyyan terkait Budgetku? kamu bisa kirim di sini",
        image = "https://github.com/Blue-Habit/budgetku-mobile-app/blob/feature/BKA-131/assets/dummy_article_community_1.webp",
        date = "11 April 2023, 22:54",
        likes = 200
    ),
    ArticleModel(
        title = "Cerdas Finansial",
        body = "Yuk melek finansial bersama Budgetku. Tersedia course keuangan untukmu",
        image = "https://github.com/Blue-Habit/budgetku-mobile-app/blob/feature/BKA-131/assets/dummy_article_community_2.webp",
        date = "11 April 2023, 22:54",
        likes = 300
    ),
    ArticleModel(
        title = "Promo & Hadiah",
        body = "Yuk cek berbagai promo menarik di aplikasi Budgetku Sekarang!",
        image = "https://github.com/Blue-Habit/budgetku-mobile-app/blob/feature/BKA-131/assets/dummy_article_community_3.webp",
        date = "11 April 2023, 22:54",
        likes = 10
    ),
    ArticleModel(
        title = "Tips Keuangan",
        body = "Bingung ngatur budget? disini kamu bisa tahu tips ngatur keuangan",
        image = "https://github.com/Blue-Habit/budgetku-mobile-app/blob/feature/BKA-131/assets/dummy_article_community_4.webp",
        date = "11 April 2023, 22:54",
        likes = 355
    )
)