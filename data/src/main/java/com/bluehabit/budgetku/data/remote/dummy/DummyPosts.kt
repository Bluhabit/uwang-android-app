/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.data.remote.dummy

import com.bluehabit.budgetku.data.R
import com.bluehabit.budgetku.data.model.post.ContentBudgetingPostModel
import com.bluehabit.budgetku.data.model.post.PostModel
import com.bluehabit.budgetku.data.model.post.PostType
import java.math.BigDecimal

val dummyPosts :List<PostModel> = listOf(
    PostModel(
        displayName = "Devianrahmani",
        date = "24 Mei 2023,19:54",
        avatar = R.drawable.dummy_avatar,
        body = "Inget selalu bedakan pengeluaran antara kebutuhan dan keinginan. " +
                "Jangan lupa juga buat selalu catet pengeluaran biar " +
                "kakak tau tuh alokasi keuangan selama satu bulan itu kemana aja",
        comments = 15,
        likes = 321,
        content = listOf(),
        mimeContent = R.drawable.dummy_image_post,
        postType = PostType.TEXT
    ),
    PostModel(
        displayName = "Andikadiskardo",
        date = "22 Mei 2023,10:00",
        avatar = R.drawable.dummy_avatar_3,
        body = "Hi! kembali lagi dengan Albon nih, seperti biasa sekarang mau share template alokasi keuangan untuk kamu pakai." +
                " Bagian yang Rp5.000.000 nih!",
        comments = 10,
        likes = 564,
        content = listOf(
            ContentBudgetingPostModel(
                icon = R.drawable.dummy_category_kebutuhanrumah,
                name = "Living",
                allocation = "Alokasi 60%",
                amount = BigDecimal(3_000_000)
            ),
            ContentBudgetingPostModel(
                icon = R.drawable.dummy_category_investasi,
                name = "Saving",
                allocation = "Alokasi 30%",
                amount = BigDecimal(1_500_000)
            ),
            ContentBudgetingPostModel(
                icon = R.drawable.dummy_category_gadget,
                name = "Playing",
                allocation = "Alokasi 10%",
                amount = BigDecimal(500_000)
            )
        ),
        mimeContent = R.drawable.dummy_image_post,
        postType = PostType.BUDGETING_TEMPLATE
    ),
    PostModel(
        displayName = "Aisyah Qalqa",
        date = "24 Mei 2023, 15: 54",
        avatar = R.drawable.dummy_avatar_4,
        body = "Tau gak sih kamu? Promo di market place itu bikin kamu boncos loh. " +
                "Walaupun ngerasa banyak kupon yang gak kepake tapi giliran kepake jadi tambah boncos wkwkw.",
        comments = 8,
        likes = 231,
        content = listOf(),
        mimeContent = R.drawable.dummy_image_post,
        postType = PostType.IMAGE
    ),
    PostModel(
        displayName = "KevinAxel",
        date = "24 Mei 2023, 12:32",
        avatar = R.drawable.dummy_avatar_1,
        body = " Malam teman budgetku, aku mau curhat dong akhir-akhir ini pengeluaran aku cukup naik" +
                " karena harus pergi ke cafe hampir setiap mimggu." +
                " Kira kira teman Budgetku ada tips gak ya?",
        comments = 40,
        likes = 20,
        content = listOf(),
        mimeContent = R.drawable.dummy_image_post,
        postType = PostType.TEXT
    ),
    PostModel(
        displayName = "RizkyPratama",
        date = "25 Mei 2023, 08:23",
        avatar = R.drawable.dummy_avatar_2,
        body = "Hey teman-teman, ada yang mau sharing tips hemat belanja bulanan? Aku lagi mencoba strategi meal planning nih, " +
                "jadi bisa mengatur pengeluaran dan makanan sekaligus. Seru banget! Kalau ada yang punya tips lain," +
                " boleh dong dishare juga. Yuk, kita saling membantu dalam mengelola keuangan kita!",
        comments = 45,
        likes = 291,
        content = listOf(),
        mimeContent = R.drawable.dummy_image_post,
        postType = PostType.TEXT
    ),
    PostModel(
        displayName = "FitriaSari",
        date = "22 Mei 2023,10:00",
        avatar = R.drawable.dummy_avatar_3,
        body = "Hi! kembali lagi dengan Albon nih, seperti biasa sekarang mau share template alokasi keuangan untuk kamu pakai." +
                " Bagian yang Rp5.000.000 nih!",
        comments = 9,
        likes = 135,
        content = listOf(
            ContentBudgetingPostModel(
                icon = R.drawable.dummy_category_kebutuhanrumah,
                name = "Living",
                allocation = "Alokasi 60%",
                amount = BigDecimal(3_000_000)
            ),
            ContentBudgetingPostModel(
                icon = R.drawable.dummy_category_investasi,
                name = "Saving",
                allocation = "Alokasi 30%",
                amount = BigDecimal(1_500_000)
            ),
            ContentBudgetingPostModel(
                icon = R.drawable.dummy_category_gadget,
                name = "Playing",
                allocation = "Alokasi 10%",
                amount = BigDecimal(500_000)
            )
        ),
        mimeContent = R.drawable.dummy_image_post,
        postType = PostType.BUDGETING_TEMPLATE
    ),
    PostModel(
        displayName = "AnitaSantoso",
        date = "26 Mei 2023, 09:45",
        avatar = R.drawable.dummy_avatar_4,
        body = "Hey, ada yang punya rekomendasi buku atau podcast tentang keuangan pribadi? " +
                "Aku lagi mencari sumber inspirasi baru untuk meningkatkan pengetahuan keuangan. " +
                "Sharing dong kalau ada yang udah nemu yang bagus!",
        comments = 35,
        likes = 242,
        content = listOf(),
        mimeContent = R.drawable.dummy_image_post,
        postType = PostType.TEXT
    ),
)