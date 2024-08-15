package com.easyhz.noffice.core.network.di

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class NofficeRetrofit

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class TokenRetrofit

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class DefaultClient

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class TokenClient

@Qualifier
@MustBeDocumented
@Retention(AnnotationRetention.BINARY)
annotation class Debug

@Qualifier
@MustBeDocumented
@Retention(AnnotationRetention.BINARY)
annotation class HttpLoggingLevel

