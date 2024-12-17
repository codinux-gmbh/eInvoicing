package net.codinux.invoicing.model

/**
 * I didn't want to use kotlinx-datetime as it has incompatibilities between 0.5 and 0.6 which leads to compile errors
 * when both are on the class path, and doesn't compile with GraalVM.
 */
expect class LocalDate(year: Int, month: Int, dayOfMonth: Int) {

    companion object {
        fun now(): LocalDate
    }


    val dayOfMonth: Int
    val month: Int
    val year: Int

}