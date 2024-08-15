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
annotation class NofficeClient

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class PureClient

@Qualifier
@MustBeDocumented
@Retention(AnnotationRetention.BINARY)
annotation class Debug

@Qualifier
@MustBeDocumented
@Retention(AnnotationRetention.BINARY)
annotation class HttpLoggingLevel

