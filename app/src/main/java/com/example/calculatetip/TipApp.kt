package com.example.calculatetip

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.calculatetip.ui.theme.CalculateTipTheme
import java.text.NumberFormat

@Composable
fun TipApp() {
    CalculateTipTheme {
        Surface {
            TipScreen()
        }
    }
}


@Composable
fun TipScreen() {
    var amountUserInput by remember { mutableStateOf("") }

    val amountValue = amountUserInput.toDoubleOrNull() ?: 0.0
    val tip = calculateTip(amountValue)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 40.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(R.string.calculate_tip),
            modifier = Modifier
                .padding(bottom = 16.dp, top = 40.dp)
                .align(alignment = Alignment.Start)
        )
        AmountField(
            amountUserInput,
            onValueChange = { amountUserInput = it },
            modifier = Modifier
                .padding(bottom = 32.dp)
                .fillMaxSize()
        )
        Text(
            text = stringResource(R.string.tip_amount, tip),
            style = MaterialTheme.typography.displaySmall
        )
        Spacer(modifier = Modifier.height(150.dp))
    }
}

fun calculateTip(amount: Double, tipPercent: Double = 15.0): String {
    val tip = amount * (tipPercent / 100)
    return NumberFormat.getCurrencyInstance().format(tip)
}

@Composable
fun AmountField(value: String, onValueChange: (String) -> Unit, modifier: Modifier = Modifier) {

    TextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier,
        label = { Text(stringResource(R.string.bill_amount)) },
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
    )
}

@Preview(showBackground = true)
@Composable
fun TipTimeLayoutPreview() {
    CalculateTipTheme {
        TipScreen()
    }
}
