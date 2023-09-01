/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.eureka.data.profile.datasource.remote

import io.ktor.resources.Resource

@Resource("/api/v1/profile")
class ProfileApi {
    @Resource("detail")
    class GetDetailProfile(val parent: ProfileApi = ProfileApi())
}