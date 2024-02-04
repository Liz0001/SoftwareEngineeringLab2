package com.SoftwareEngineering.selab2.data

/**
 * This data class defines a Window, Door, Light state data.
 * */
data class States(
    val doorOpen: Boolean = true,
    val lightOn: Boolean = true,
    val windowOpen: Boolean = true
)
