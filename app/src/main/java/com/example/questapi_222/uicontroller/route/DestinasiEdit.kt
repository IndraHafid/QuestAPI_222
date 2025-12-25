package com.example.questapi_222.uicontroller.route

import com.example.questapi_222.R

object DestinasiEdit {
    const val route = "edit_siswa"

    val titleRes = R.string.edit_siswa

    const val siswaIdArg = "siswaId"
    val routeWithArgs = "$route/{$siswaIdArg}"
}
