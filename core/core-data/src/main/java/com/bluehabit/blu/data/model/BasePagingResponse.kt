/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.blu.data.model

import com.google.gson.annotations.SerializedName

data class BasePagingResponse<Item>(
    @SerializedName("page")
    val page: Long,
    @SerializedName("size")
    val size: Long,
    @SerializedName("totalElements")
    val totalElements: Long,
    @SerializedName("totalPages")
    val totalPages: Long,
    @SerializedName("items")
    val items: List<Item> = listOf()
)
