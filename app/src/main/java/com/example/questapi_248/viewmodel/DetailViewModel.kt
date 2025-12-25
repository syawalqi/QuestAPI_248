package com.example.questapi_248.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.questapi_248.modeldata.DataSiswa
import com.example.questapi_248.repositori.RepositoryDataSiswa
import com.example.questapi_248.view.route.DestinasiDetail
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import retrofit2.Response

sealed interface StatusUiDetail {
    data class Success(val satuSiswa: DataSiswa) : StatusUiDetail
    object Error : StatusUiDetail
    object Loading : StatusUiDetail
}

class DetailViewModel(
    savedStateHandle: SavedStateHandle,
    private val repositoryDataSiswa: RepositoryDataSiswa
) : ViewModel() {

    private val idSiswa: Int =
        checkNotNull(savedStateHandle[DestinasiDetail.itemIdArg])

    var statusUiDetail: StatusUiDetail by mutableStateOf(StatusUiDetail.Loading)
        private set

    init {
        getSatuSiswa()
    }

    fun getSatuSiswa() {
        viewModelScope.launch {
            statusUiDetail = StatusUiDetail.Loading
            statusUiDetail = try {
                StatusUiDetail.Success(
                    satuSiswa = repositoryDataSiswa.getSatuSiswa(idSiswa)
                )
            } catch (e: IOException) {
                StatusUiDetail.Error
            } catch (e: HttpException) {
                StatusUiDetail.Error
            }
        }
    }

    suspend fun hapusSatuSiswa() {
        val resp: Response<Void> =
            repositoryDataSiswa.hapusSatuSiswa(idSiswa)

        if (resp.isSuccessful) {
            println("Sukses hapus data: ${resp.message()}")
        } else {
            println("Gagal hapus data: ${resp.errorBody()}")
        }
    }
}
