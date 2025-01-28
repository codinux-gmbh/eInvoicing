package net.codinux.invoicing.test

actual object JavaTestPlatform {

    actual fun initTestEnvironment() {
        // no-op on JVM (only initialized applicationContext mock on Android)
    }

}