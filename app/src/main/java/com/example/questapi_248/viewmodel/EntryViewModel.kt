package com.example.questapi_248.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.questapi_248.modeldata.DetailSiswa
import com.example.questapi_248.modeldata.UIStateSiswa
import com.example.questapi_248.modeldata.toDataSiswa
import com.example.questapi_248.repositori.RepositoryDataSiswa
import retrofit2.Response

class EntryViewModel(
    private val repositoryDataSiswa: RepositoryDataSiswa
) : ViewModel() {

    var uiStateSiswa by mutableStateOf(UIStateSiswa())
        private set

    private fun validasiInput(
        uiState: DetailSiswa = uiStateSiswa.detailSiswa
    ): Boolean =
        with(uiState) {
            nama.isNotBlank() &&
                    alamat.isNotBlank() &&
                    telpon.isNotBlank()
        }

    fun updateUiState(detailSiswa: DetailSiswa) {
        uiStateSiswa =
            UIStateSiswa(
                detailSiswa = detailSiswa,
                isEntryValid = validasiInput(detailSiswa)
            )
    }

    suspend fun addSiswa() {
        if (validasiInput()) {
            val resp: Response<Void> =
                repositoryDataSiswa.postDataSiswa(
                    uiStateSiswa.detailSiswa.toDataSiswa()
                )

            if (resp.isSuccessful) {
                println("Sukses tambah data: ${resp.message()}")
            } else {
                println("Gagal tambah data: ${resp.errorBody()}")
            }
        }
    }
}
