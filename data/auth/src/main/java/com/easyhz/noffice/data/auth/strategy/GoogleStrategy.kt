package com.easyhz.noffice.data.auth.strategy

import android.content.Context
import android.util.Log
import androidx.credentials.CredentialManager
import androidx.credentials.CustomCredential
import androidx.credentials.GetCredentialRequest
import androidx.credentials.GetCredentialResponse
import com.easyhz.noffice.core.common.error.NofficeError
import com.easyhz.noffice.data.auth.BuildConfig
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.android.libraries.identity.googleid.GoogleIdTokenParsingException
import javax.inject.Inject

class GoogleStrategy @Inject constructor(
): BaseStrategy {
    private val tag = this.javaClass.name

    override suspend fun login(context: Context): Result<String> = runCatching {
        val googleIdOption: GetGoogleIdOption = GetGoogleIdOption.Builder()
            .setServerClientId(BuildConfig.GOOGLE_CLIENT_ID)
            .setFilterByAuthorizedAccounts(false)
            .setAutoSelectEnabled(false)
            .build()

        val request: GetCredentialRequest =
            GetCredentialRequest.Builder().addCredentialOption(
                googleIdOption
            ).build()

        val result =
            CredentialManager.create(context)
                .getCredential(context = context, request = request)
        handleGoogleSignIn(result)
    }

    override suspend  fun logout() {
        TODO("Not yet implemented")
    }

    private fun handleGoogleSignIn(result: GetCredentialResponse): String {
        when (val credential = result.credential) {
            is CustomCredential -> {
                if (credential.type == GoogleIdTokenCredential.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL) {
                    try {
                        val googleIdTokenCredential = GoogleIdTokenCredential
                            .createFrom(credential.data)
                        return googleIdTokenCredential.idToken
                    } catch (e: GoogleIdTokenParsingException) {
                        throw e
                    }
                } else {
                    Log.d(tag, "Unexpected type of credential (not TYPE_GOOGLE_ID_TOKEN_CREDENTIAL). ")
                    throw NofficeError.UnexpectedError
                }
            }
            else -> {
                Log.d(tag, "Unexpected type of credential (not custom).")
                throw NofficeError.UnexpectedError
            }
        }
    }
}