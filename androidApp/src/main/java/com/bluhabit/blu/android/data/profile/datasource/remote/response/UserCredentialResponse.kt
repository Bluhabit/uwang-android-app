/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluhabit.blu.android.data.profile.datasource.remote.response

import com.google.gson.annotations.SerializedName

data class UserCredentialResponse(

	@field:SerializedName("full_name")
	val fullName: String,

	@field:SerializedName("deleted")
	val deleted: String,

	@field:SerializedName("updated_at")
	val updatedAt: String,

	@field:SerializedName("date_of_birth")
	val dateOfBirth: String,

	@field:SerializedName("created_at")
	val createdAt: String,

	@field:SerializedName("id")
	val id: String,

	@field:SerializedName("user_profile")
	val userProfile: List<UserProfile>,

	@field:SerializedName("email")
	val email: String,

	@field:SerializedName("auth_provider")
	val authProvider: String,

	@field:SerializedName("username")
	val username: String,

	@field:SerializedName("status")
	val status: String
)

data class UserProfile(

	@field:SerializedName("id")
	val id: String,

	@field:SerializedName("value")
	val value: Any,

	@field:SerializedName("key")
	val key: String
)
