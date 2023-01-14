package bfa.blair.shopme.utils

class DataorException<T, Boolean, E : Exception> (
    var data : T? = null,
    var loading : Boolean? = null,
    var e : E? = null
)