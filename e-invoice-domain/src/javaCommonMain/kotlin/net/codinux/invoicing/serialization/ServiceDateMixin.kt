package net.codinux.invoicing.serialization

import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import net.codinux.invoicing.model.ServiceDate

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes(
    JsonSubTypes.Type(value = ServiceDate.DeliveryDate::class, name = "net.codinux.invoicing.model.ServiceDate.DeliveryDate"),
    JsonSubTypes.Type(value = ServiceDate.ServicePeriod::class, name = "net.codinux.invoicing.model.ServiceDate.ServicePeriod"),
)
abstract class ServiceDateMixin