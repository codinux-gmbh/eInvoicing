package net.codinux.invoicing.platform

import kotlinx.cinterop.UnsafeNumber
import platform.Foundation.NSNumber
import platform.darwin.NSInteger


// NSInteger is an Int on 32-bit system and Long on 64-bit systems -> convert Int to NSInteger
@OptIn(UnsafeNumber::class)
fun Int.toNSInteger(): NSInteger =
    NSNumber(int = this).integerValue

@OptIn(UnsafeNumber::class)
fun Long.toNSInteger(): NSInteger =
    NSNumber(longLong = this).integerValue