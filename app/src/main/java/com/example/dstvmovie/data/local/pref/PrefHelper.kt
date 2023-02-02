package com.example.dstvmovie.data.local.pref

import kotlinx.coroutines.flow.Flow

interface PrefHelper {

    suspend fun isFirstRun(): Flow<Boolean>

    suspend fun clear()

}