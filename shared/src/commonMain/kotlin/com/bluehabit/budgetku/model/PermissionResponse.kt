/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.model

data class PermissionResponse(
    var permissionId:String? = null,
    var permissionName:String? = null,
    var permissionType:String? = null,
    var permissionGroup:String?=null,
    var createdAt: String?,
    var updatedAt: String?
)
