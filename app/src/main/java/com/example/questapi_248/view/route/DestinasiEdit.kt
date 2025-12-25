package com.example.questapi_248.view.route

import com.example.questapi_248.R

object DestinasiEdit : DestinasiNavigasi {

    override val route = "item_edit"
    override val titleRes = R.string.edit_siswa

    const val itemIdArg = "idSiswa"
    val routeWithArgs = "$route/{$itemIdArg}"
}
