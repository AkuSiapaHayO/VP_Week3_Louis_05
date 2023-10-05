package com.example.myapplication.ui.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ListItemDefaults.containerColor
import androidx.compose.material3.ListItemDefaults.contentColor
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.myapplication.R

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun Preview2() {
    Challenge2()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Challenge2() {

    var weight by rememberSaveable { mutableStateOf("") }
    var height by rememberSaveable { mutableStateOf("") }
    var isErrorWeight by rememberSaveable { mutableStateOf(false) }
    var isErrorHeight by rememberSaveable { mutableStateOf(false) }
    var isSubmitEnabled by rememberSaveable { mutableStateOf(false) }
    var showDialog by rememberSaveable { mutableStateOf(false) }


    val bmi: Double =
        if (!isErrorWeight && !isErrorHeight && weight.isNotBlank() && height.isNotBlank()) {
            val weightDouble = weight.toDouble()
            val heightDouble = height.toDouble() / 100
            val bmiValue = weightDouble / (heightDouble * heightDouble)
            bmiValue
        } else {
            0.0
        }

    LaunchedEffect(weight, height) {
        isSubmitEnabled =
            !isErrorWeight && !isErrorHeight && weight.isNotBlank() && height.isNotBlank()
    }


    Column(
        Modifier
            .padding(
                vertical = 32.dp,
                horizontal = 24.dp
            )
    ) {
        Image(
            painterResource(id = R.drawable.ic_action_face),
            null,
            Modifier
                .size(100.dp)
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally),
            contentScale = ContentScale.Crop
        )

        OutlinedTextField(
            value = weight,
            onValueChange = { newWeight ->
                weight = newWeight
                isErrorWeight = newWeight.isNotEmpty() && newWeight.toDoubleOrNull() ?: 0.0 <= 0
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = 12.dp,
                    vertical = 8.dp
                ),
            label = {
                Text(
                    text = "Weight in kg",
                    color = Color.Blue
                )
            },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            ),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color(0xFF62519E),
                unfocusedBorderColor = Color.Blue
            ),
            shape = RoundedCornerShape(32.dp),
//            isError = isErrorWeight,
            singleLine = true
        )

        if (isErrorWeight) {
            Text(
                text = "Please enter a valid weight greater than 0",
                color = Color.Red,
                modifier = Modifier
                    .align(CenterHorizontally)
            )
        } else if (weight.isEmpty()){
            Text(
                text = "Please enter a valid weight greater than 0",
                color = Color.Red,
                modifier = Modifier
                    .align(CenterHorizontally)
            )
        }

        OutlinedTextField(
            value = height,
            onValueChange = { newHeight ->
                height = newHeight
                isErrorHeight = newHeight.isNotEmpty() && (newHeight.toDoubleOrNull() ?: 0.0) <= 0
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = 12.dp,
                    vertical = 8.dp
                ),
            label = {
                Text(
                    text = "Height in cm",
                    color = Color.Blue
                )
            },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            ),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color(0xFF62519E),
                unfocusedBorderColor = Color.Blue
            ),
            shape = RoundedCornerShape(32.dp),
//            isError = isErrorHeight,
            singleLine = true
        )

        if (isErrorHeight) {
            Text(
                text = "Please enter a valid height greater than 0",
                color = Color.Red,
                modifier = Modifier
                    .align(CenterHorizontally)
            )
        } else if (height.isEmpty()){
            Text(
                text = "Please enter a valid height greater than 0",
                color = Color.Red,
                modifier = Modifier
                    .align(CenterHorizontally)
            )
        }

        Button(
            onClick = { showDialog = true },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            enabled = isSubmitEnabled,
            colors = ButtonDefaults.buttonColors(
                disabledContainerColor = Color(0xFFCBCBCB),
                containerColor = Color(0xFF75FAFC),
                disabledContentColor = Color(0xFF040404),
                contentColor = Color(0xFF040404)
            )
        ) {
            Text(
                text = "Calculate BMI"
            )
        }
    }

    if (showDialog) {
        Box(
            modifier = Modifier
                .background(Color.Gray.copy(alpha = 0.5f))
                .fillMaxSize()
                .padding(32.dp)
        ) {
            Dialog(
                onDismissRequest = { showDialog = false }
            ) {
                Column(
                    modifier = Modifier
                        .clip(RoundedCornerShape(32.dp))
                        .background(Color(0xFFEDE8F2))
                        .padding(horizontal = 28.dp)
                        .padding(top = 24.dp, bottom = 16.dp)
                ) {
                    Text(
                        text = "Your BMI Analysis",
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 20.sp
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(text = "Your Height: ${height.toDouble() / 100}", fontSize = 16.sp)
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(text = "Your Weight: ${weight.toDouble()}", fontSize = 16.sp)
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(text = "BMI: ${String.format("%.1f", bmi)}", fontSize = 16.sp)
                    Spacer(modifier = Modifier.height(2.dp))
                    if (bmi <= 18.4) {
                        Text(text = "You are Underweight", fontSize = 16.sp)
                    } else if (bmi <= 24.9) {
                        Text(text = "You are Normal", fontSize = 16.sp)
                    } else if (bmi <= 39.9) {
                        Text(text = "You are Overweight", fontSize = 16.sp)
                    } else {
                        Text(text = "You are Obese", fontSize = 16.sp)
                    }
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(),
                        contentAlignment = Alignment.BottomEnd
                    ) {
                        Button(
                            onClick = {
                                showDialog =
                                    false // Dismiss the dialog when the "OK" button is clicked
                            },
                            colors = ButtonDefaults.buttonColors(
                                disabledContainerColor = Color(0xFF62519E),
                                containerColor = Color(0xFF62519E),
                                disabledContentColor = Color(0xFFFCFCFC),
                                contentColor = Color(0xFFFCFCFD)
                            )
                        ) {
                            Text(text = "OK")
                        }
                    }
                }
            }
        }
    }
}

