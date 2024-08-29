package com.easyhz.noffice.data.auth.strategy

import android.content.Context
import android.util.Log
import androidx.credentials.ClearCredentialStateRequest
import androidx.credentials.CredentialManager
import androidx.credentials.GetCredentialRequest
import androidx.credentials.GetCredentialResponse
import com.easyhz.noffice.core.common.error.NofficeError
import com.easyhz.noffice.core.network.BuildConfig
import com.google.android.gms.auth.api.identity.AuthorizationRequest
import com.google.android.gms.auth.api.identity.AuthorizationResult
import com.google.android.gms.auth.api.identity.Identity
import com.google.android.gms.common.Scopes
import com.google.android.gms.common.api.Scope
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.android.libraries.identity.googleid.GoogleIdTokenParsingException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import javax.inject.Inject


class GoogleStrategy @Inject constructor(

) : BaseStrategy() {
    private val tag = this.javaClass.name
    override suspend fun login(context: Context): Result<String> = runCatching {
        val result = authenticateGoogleSignIn(context)
        handleGoogleSignIn(context, result)
    }

    override suspend fun logout(context: Context): Result<Unit> = runCatching {
        val request = ClearCredentialStateRequest()
        CredentialManager.create(context).clearCredentialState(request)
    }

    private suspend fun handleGoogleSignIn(
        context: Context,
        result: GetCredentialResponse
    ): String = withContext(Dispatchers.IO) {
        val credential = result.credential
        if (credential.type != GoogleIdTokenCredential.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL) {
            throwUnexpectedError(tag, "Unexpected type of credential (not TYPE_GOOGLE_ID_TOKEN_CREDENTIAL).")
        }

        try {
            val googleIdTokenCredential = GoogleIdTokenCredential.createFrom(credential.data)
            Log.d(tag, "Authentication success - type: ${googleIdTokenCredential.type}")

            authorizeGoogleSignIn(context).serverAuthCode
                ?: throw NofficeError.UnexpectedError
        } catch (e: GoogleIdTokenParsingException) {
            Log.e(tag, "GoogleIdTokenParsingException - $e")
            throw e
        } catch (e: Exception) {
            Log.e(tag, "Exception - $e")
            throw e
        }
    }

    private suspend fun authenticateGoogleSignIn(context: Context): GetCredentialResponse {
        val googleIdOption: GetGoogleIdOption = GetGoogleIdOption.Builder()
            .setServerClientId(BuildConfig.GOOGLE_CLIENT_ID)
            .setFilterByAuthorizedAccounts(false)
            .setAutoSelectEnabled(false)
            .build()

        val request: GetCredentialRequest =
            GetCredentialRequest.Builder().addCredentialOption(
                googleIdOption
            ).build()

        return CredentialManager.create(context)
                .getCredential(context = context, request = request)
    }

    private suspend fun authorizeGoogleSignIn(context: Context): AuthorizationResult {
        val requestedScopes = listOf(
            Scope(Scopes.PROFILE),
            Scope(Scopes.EMAIL),
        )

        val authorizationRequest = AuthorizationRequest.builder()
            .setRequestedScopes(requestedScopes)
            .requestOfflineAccess(BuildConfig.GOOGLE_CLIENT_ID)
            .build()

        return Identity.getAuthorizationClient(context)
            .authorize(authorizationRequest)
            .await()
    }
}