package com.example.questapi_222.uicontroller.route

import com.example.questapi_222.R

object DestinasiDetail {
    const val route = "detail_siswa"

    val titleRes = R.string.detail_siswa

    const val siswaIdArg = "siswaId"
    val routeWithArgs = "$route/{$siswaIdArg}"
}
