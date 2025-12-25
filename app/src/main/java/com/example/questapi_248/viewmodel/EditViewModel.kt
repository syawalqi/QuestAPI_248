package com.example.questapi_248.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.questapi_248.modeldata.DetailSiswa
import com.example.questapi_248.modeldata.UIStateSiswa
import com.example.questapi_248.modeldata.toDataSiswa
import com.example.questapi_248.modeldata.toUiStateSiswa
import com.example.questapi_248.repositori.RepositoryDataSiswa
import com.example.questapi_248.view.route.DestinasiDetail
import kotlinx.coroutines.launch
import retrofit2.Response

class EditViewModel(
    savedStateHandle: SavedStateHandle,
    private val repositoryDataSiswa: RepositoryDataSiswa
) : ViewModel() {

    var uiStateSiswa by mutableStateOf(UIStateSiswa())
        private set

    private val idSiswa: Int =
        checkNotNull(savedStateHandle[DestinasiDetail.itemIdArg])

    init {
        viewModelScope.launch {
            uiStateSiswa =
                repositoryDataSiswa
                    .getSatuSiswa(idSiswa)
                    .toUiStateSiswa(true)
        }
    }

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

    suspend fun editSatuSiswa() {
        if (validasiInput(uiStateSiswa.detailSiswa)) {
            val call: Response<Void> =
                repositoryDataSiswa.editSatuSiswa(
                    idSiswa,
                    uiStateSiswa.detailSiswa.toDataSiswa()
                )

            if (call.isSuccessful) {
                println("Update sukses: ${call.message()}")
            } else {
                println("Update error: ${call.errorBody()}")
            }
        }
    }
}
