package com.composemultiplatform.book.core.presentation.util

import androidx.compose.ui.input.key.Key.Companion.T
import bookappcmp.composeapp.generated.resources.Res
import bookappcmp.composeapp.generated.resources.error_disk_full
import bookappcmp.composeapp.generated.resources.error_no_internet
import bookappcmp.composeapp.generated.resources.error_request_timeout
import bookappcmp.composeapp.generated.resources.error_serialization
import bookappcmp.composeapp.generated.resources.error_too_many_requests
import bookappcmp.composeapp.generated.resources.error_unknown
import com.composemultiplatform.book.core.domain.DataError

fun DataError.toUiText(): UiText = when (this) {
    DataError.Local.DISK_FULL -> UiText.StringResourceId(Res.string.error_disk_full)
    DataError.Local.UNKNOWN -> UiText.StringResourceId(Res.string.error_unknown)
    DataError.Remote.REQUEST_TIMEOUT -> UiText.StringResourceId(Res.string.error_request_timeout)
    DataError.Remote.TOO_MANY_REQUESTS -> UiText.StringResourceId(Res.string.error_too_many_requests)
    DataError.Remote.NO_INTERNET -> UiText.StringResourceId(Res.string.error_no_internet)
    DataError.Remote.SERVER -> UiText.StringResourceId(Res.string.error_unknown)
    DataError.Remote.SERIALIZATION -> UiText.StringResourceId(Res.string.error_serialization)
    DataError.Remote.UNKNOWN -> UiText.StringResourceId(Res.string.error_unknown)
}