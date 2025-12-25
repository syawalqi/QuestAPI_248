package com.example.questapi_248.view.route

import com.example.questapi_248.R

object DestinasiDetail : DestinasiNavigasi {

    override val route = "detail_siswa"
    override val titleRes = R.string.detail_siswa

    const val itemIdArg = "idSiswa"
    val routeWithArgs = "$route/{$itemIdArg}"
}
