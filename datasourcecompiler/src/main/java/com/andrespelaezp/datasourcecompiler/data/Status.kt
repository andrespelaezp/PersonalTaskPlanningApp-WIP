package com.andrespelaezp.datasourcecompiler.data

enum class Status(val color: String) {
    NEW("#DBBF21"),
    TODO("#21D6DB"),
    IN_PROGRESS("#415B5C"),
    BLOCKED("#87657C"),
    CLOSED("#DB2EA4"),
    OVERDUE("#FF0000"),
}