package com.easyhz.noffice.core.common.di

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class Dispatcher(val dispatcher: NofficeDispatchers)

enum class NofficeDispatchers {
    DEFAULT,
    IO,
}