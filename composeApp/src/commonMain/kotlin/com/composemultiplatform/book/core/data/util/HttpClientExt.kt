package com.composemultiplatform.book.core.data.util

import com.composemultiplatform.book.core.domain.DataError
import com.composemultiplatform.book.core.domain.Result
import io.ktor.client.call.NoTransformationFoundException
import io.ktor.client.call.body
import io.ktor.client.network.sockets.SocketTimeoutException
import io.ktor.client.statement.HttpResponse
import io.ktor.util.network.UnresolvedAddressException
import kotlinx.coroutines.CancellationException
import kotlinx.serialization.SerializationException


suspend inline fun <reified T> safeCall(
    execute: () -> HttpResponse
): Result<T, DataError.Remote> {
    return try {
        responseToResult(execute())
    } catch (e: SocketTimeoutException) {
        e.printStackTrace()
        Result.Error(DataError.Remote.REQUEST_TIMEOUT)
    } catch (e: UnresolvedAddressException) {
        e.printStackTrace()
        Result.Error(DataError.Remote.NO_INTERNET)
    } catch (e: SerializationException) {
        e.printStackTrace()
        Result.Error(DataError.Remote.SERIALIZATION)
    } catch (e: NoTransformationFoundException) {
        e.printStackTrace()
        Result.Error(DataError.Remote.SERIALIZATION)
    } catch (e: Exception) {
        if (e is CancellationException) throw e
        e.printStackTrace()
        return Result.Error(DataError.Remote.UNKNOWN)
    }
}

suspend inline fun <reified T> responseToResult(
    response: HttpResponse
): Result<T, DataError.Remote> {
    return when (response.status.value) {
        in 200..299 -> Result.Success(response.body<T>())
        408 -> Result.Error(DataError.Remote.REQUEST_TIMEOUT)
        429 -> Result.Error(DataError.Remote.TOO_MANY_REQUESTS)
        in 500..599 -> Result.Error(DataError.Remote.SERVER)
        else -> Result.Error(DataError.Remote.UNKNOWN)
    }
}