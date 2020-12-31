package org.mydaily.data.repository

import org.mydaily.data.remote.datasource.SampleRemoteDataSource

class SampleRepoImpl(
    private val remoteDataSource: SampleRemoteDataSource
) : SampleRepo {
}