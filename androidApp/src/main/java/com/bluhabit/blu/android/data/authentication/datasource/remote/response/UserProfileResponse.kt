/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluhabit.blu.android.data.authentication.datasource.remote.response

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class UserProfileResponse(
    @SerializedName("id")
    val id: String,
    @SerializedName("key")
    val key: String,
    @SerializedName("value")
    val value: String,
    @SerializedName("user_id")
    val userId: String,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("updated_at")
    val updatedAt: String,
    @SerializedName("deleted")
    val deleted: Boolean
)

fun List<UserProfileResponse>.toMap(): Map<String, String> {
    val data = HashMap<String, String>()
    this.forEach {
        data.put(it.key, it.value)
    }
    return data
}
