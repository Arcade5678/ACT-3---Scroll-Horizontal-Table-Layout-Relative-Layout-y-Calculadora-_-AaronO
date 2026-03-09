package com.example.act3_scrollhorizontaltablelayoutrelativelayoutycalculadora

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.abs

class Exercici14Activity : AppCompatActivity() {

    private lateinit var tvResult: TextView
    private lateinit var tvExpression: TextView

    // Estat de la calculadora
    private var currentInput: String = "0"
    private var firstOperand: Double = 0.0
    private var pendingOperator: String = ""
    private var newInputExpected: Boolean = false
    private var expression: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exercici14)

        tvResult = findViewById(R.id.tvResult)
        tvExpression = findViewById(R.id.tvExpression)

        // Dígits
        val digitIds = mapOf(
            R.id.btn0 to "0", R.id.btn1 to "1", R.id.btn2 to "2",
            R.id.btn3 to "3", R.id.btn4 to "4", R.id.btn5 to "5",
            R.id.btn6 to "6", R.id.btn7 to "7", R.id.btn8 to "8",
            R.id.btn9 to "9"
        )
        for ((id, digit) in digitIds) {
            findViewById<Button>(id).setOnClickListener { appendDigit(digit) }
        }

        // Punt decimal
        findViewById<Button>(R.id.btnDot).setOnClickListener { appendDot() }

        // Operadors
        findViewById<Button>(R.id.btnPlus).setOnClickListener { setOperator("+") }
        findViewById<Button>(R.id.btnMinus).setOnClickListener { setOperator("−") }
        findViewById<Button>(R.id.btnMultiply).setOnClickListener { setOperator("×") }
        findViewById<Button>(R.id.btnDivide).setOnClickListener { setOperator("÷") }

        // Igual
        findViewById<Button>(R.id.btnEquals).setOnClickListener { calculateResult() }

        // AC
        findViewById<Button>(R.id.btnAC).setOnClickListener { clearAll() }

        // +/-
        findViewById<Button>(R.id.btnToggleSign).setOnClickListener { toggleSign() }

        // %
        findViewById<Button>(R.id.btnPercent).setOnClickListener { applyPercent() }
    }

    private fun appendDigit(digit: String) {
        if (newInputExpected) {
            currentInput = digit
            newInputExpected = false
        } else {
            currentInput = if (currentInput == "0") digit else currentInput + digit
        }
        updateDisplay()
    }

    private fun appendDot() {
        if (newInputExpected) {
            currentInput = "0."
            newInputExpected = false
        } else if (!currentInput.contains(".")) {
            currentInput += "."
        }
        updateDisplay()
    }

    private fun setOperator(op: String) {
        val value = currentInput.toDoubleOrNull() ?: return
        if (pendingOperator.isNotEmpty() && !newInputExpected) {
            // Calcula intermedi
            firstOperand = computeOperation(firstOperand, value, pendingOperator)
            expression = formatNumber(firstOperand) + " $op "
            currentInput = formatNumber(firstOperand)
        } else {
            firstOperand = value
            expression = formatNumber(value) + " $op "
        }
        pendingOperator = op
        newInputExpected = true
        updateDisplay()
    }

    private fun calculateResult() {
        if (pendingOperator.isEmpty()) return
        val secondOperand = currentInput.toDoubleOrNull() ?: return
        expression += formatNumber(secondOperand) + " ="
        val result = computeOperation(firstOperand, secondOperand, pendingOperator)
        currentInput = formatNumber(result)
        pendingOperator = ""
        newInputExpected = true
        updateDisplay()
    }

    private fun computeOperation(a: Double, b: Double, op: String): Double {
        return when (op) {
            "+" -> a + b
            "−" -> a - b
            "×" -> a * b
            "÷" -> if (b != 0.0) a / b else Double.NaN
            else -> b
        }
    }

    private fun clearAll() {
        currentInput = "0"
        firstOperand = 0.0
        pendingOperator = ""
        newInputExpected = false
        expression = ""
        updateDisplay()
    }

    private fun toggleSign() {
        val value = currentInput.toDoubleOrNull() ?: return
        currentInput = formatNumber(-value)
        updateDisplay()
    }

    private fun applyPercent() {
        val value = currentInput.toDoubleOrNull() ?: return
        currentInput = formatNumber(value / 100.0)
        updateDisplay()
    }

    private fun formatNumber(value: Double): String {
        if (value.isNaN()) return "Error"
        return if (value == kotlin.math.floor(value) && !value.isInfinite() && abs(value) < 1e15) {
            value.toLong().toString()
        } else {
            value.toBigDecimal().stripTrailingZeros().toPlainString()
        }
    }

    private fun updateDisplay() {
        tvResult.text = currentInput
        tvExpression.text = expression
    }
}
