package com.example.final_project.remote.repository

import com.example.final_project.models.DetailTourism
import com.example.final_project.remote.api.BaseApiResponse
import com.example.final_project.remote.network.NetworkResult
import com.example.final_project.remote.network.RemoteDataSource
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@ActivityRetainedScoped
class Repository @Inject constructor(private val remoteDataSource: RemoteDataSource):
    BaseApiResponse() {
        suspend fun getDetailTourism(tourismID: Long): Flow<NetworkResult<DetailTourism>> {
            return flow<NetworkResult<DetailTourism>> {
                emit(safeApiCall { remoteDataSource.getDetailTourism(tourismID) })
            }.flowOn(Dispatchers.IO)
        }

        suspend fun getTourismByType(tourismType: String): Flow<NetworkResult<List<DetailTourism>>> {
            return flow<NetworkResult<List<DetailTourism>>> {
                emit(safeApiCall { remoteDataSource.getAllTourismByType(tourismType) })
            }.flowOn(Dispatchers.IO)
        }
}