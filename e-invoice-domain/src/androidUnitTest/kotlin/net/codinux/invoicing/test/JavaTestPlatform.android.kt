package net.codinux.invoicing.test

import io.mockk.mockk
import net.codinux.kotlin.android.AndroidContext

actual object JavaTestPlatform {

    actual fun initTestEnvironment() {
        AndroidContext.applicationContext = mockk(relaxed = true)
    }

}