package com.bluehabit.budgetku.user

import com.bluehabit.budgetku.DriverFactory
import com.bluehabit.budgetku.createDatabase
import com.bluehabit.budgetku.entity.UserModel
import io.ktor.client.*

class UserSDK(
    private val driverFactory: DriverFactory,
    private val httpClient: HttpClient
) {
    private val db = createDatabase(driverFactory)
    private val api = UserApi(httpClient)

    @Throws(
        Exception::class
    )
    suspend fun signInWithEmail(
        email: String,
        password: String,
    ): Triple<Boolean, String, String?> {
        return try {
            val res = api.signInWithEmail(email, password)
            Triple(
                true,
                "success",
                res.data.userId
            )
        } catch (e: Exception) {
            Triple(false, e.message ?: "something wrong", null)
        }

    }

    @Throws(
        Exception::class
    )
    suspend fun insertUser(data: UserModel): Triple<Boolean, String, UserModel> {
        db.userQueries.insertUser(
            data.id,
            data.fullName,
            data.email,
            data.phoneNumber,
            data.dateOfBirth,
            data.countryCode,
            data.authProvider,
            data.authTokenProvider,
            data.status,
            data.createdAt,
            data.updatedAt
        )

        return Triple(
            true,
            "success",
            data
        )
    }

    @Throws(
        Exception::class
    )
    suspend fun getUser(id: String): Triple<Boolean, String, UserModel> {
        val data = db.userQueries.getById(id).executeAsOne()
        if (data.id.isNullOrBlank()) {
            return Triple(
                false,
                "failed",
                UserModel(
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    0,
                    0
                )
            )
        }

        return Triple(
            true,
            "success",
            UserModel(
                data.id,
                data.fullName,
                data.email,
                data.phoneNumber,
                data.dateOfBirth,
                data.countryCode,
                data.authProvider,
                data.authTokenProvider,
                data.status,
                data.createdAt,
                data.updatedAt
            )
        )
    }
}