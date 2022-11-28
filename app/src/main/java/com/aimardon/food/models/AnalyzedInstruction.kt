package com.aimardon.food.models

data class AnalyzedInstruction(
    val name: String,
    val steps: List<Step>
)