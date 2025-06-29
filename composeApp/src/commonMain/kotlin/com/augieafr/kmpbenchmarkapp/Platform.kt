package com.augieafr.kmpbenchmarkapp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform