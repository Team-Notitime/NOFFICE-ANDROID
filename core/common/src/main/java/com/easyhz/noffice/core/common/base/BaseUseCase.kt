package com.easyhz.noffice.core.common.base

abstract class BaseUseCase<P, R> {
    abstract suspend operator fun invoke(param: P): Result<R>
}