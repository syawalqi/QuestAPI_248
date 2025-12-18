package com.example.questapi_248.repositori


interface RepositoryDataSiswa {

    suspend fun getDataSiswa(): List<DataSiswa>

    suspend fun postDataSiswa(dataSiswa: DataSiswa): retrofit2.Response<Void>
//


}