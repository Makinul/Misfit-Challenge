package tech.misfit.challenge.data.network.helper

enum class Status {
    SUCCESS,
    ERROR,
    LOADING
}

data class Resource<out T>(
    val status: Status,
    val data: T?,
    val message: String?,
    val extraValue: Int = 0
) {

    companion object {
        fun <T> success(data: T?, extraValue: Int = 0): Resource<T> {
            return Resource(Status.SUCCESS, data, null, extraValue)
        }

        fun <T> error(msg: String, data: T?): Resource<T> {
            return Resource(Status.ERROR, data, msg)
        }

        fun <T> loading(data: T?): Resource<T> {
            return Resource(Status.LOADING, data, null)
        }
    }
}
