package com.example.myapplication.ui.view

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.R
import kotlinx.coroutines.launch

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun Preview3() {
    Challenge3()
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Challenge3() {

    var name by rememberSaveable { mutableStateOf("") }
    var birthYear by rememberSaveable { mutableStateOf("") }

    var nameFocus by rememberSaveable { mutableStateOf(false) }
    var birthYearFocus by rememberSaveable { mutableStateOf(false) }

    var isSubmitEnabled by rememberSaveable { mutableStateOf(false) }
    var isBirthYearError by rememberSaveable { mutableStateOf(false) }
    var isSubmitted by rememberSaveable { mutableStateOf(false) }

    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    val age: Int = if (!isBirthYearError && birthYear.isNotBlank()) {
        val birthYearInt = birthYear.toInt()
        2023 - birthYearInt
    } else {
        0
    }

    Scaffold (
        snackbarHost = {SnackbarHost(snackbarHostState)},
        content = {
            Column {
                Text(
                    text = "AgeCalculator",
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color(0xFFDB8EF6))
                        .padding(horizontal = 24.dp, vertical = 16.dp),
                    fontSize = 18.sp,
                    color = Color(0xFFF8E2FB)
                )

                Box(
                    modifier = Modifier
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Column {
                        Image(
                            painter = painterResource(id = R.drawable.ic_action_smiley),
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .size(120.dp)
                                .fillMaxWidth()
                                .align(CenterHorizontally)
                        )

                        OutlinedTextField(
                            value = name,
                            onValueChange = { name = it },
                            label = {
                                if (name.isNotEmpty()) {
                                    null
                                } else if (!nameFocus){
                                    Text(text = "Enter your name")
                                } else {
                                    null
                                }
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 24.dp, vertical = 8.dp)
                                .onFocusChanged { nameFocus = it.isFocused },
                            shape = RoundedCornerShape(24.dp),
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                unfocusedBorderColor = Color(0xFFDB8FF6),
                                textColor = Color(0xFFDB8FF6),
                                focusedBorderColor = Color(0xFFDB8FF6)
                            ),
                            keyboardOptions = KeyboardOptions.Default.copy(
                                keyboardType = KeyboardType.Text,
                                imeAction = ImeAction.Next
                            )
                        )

                        OutlinedTextField(
                            value = birthYear,
                            onValueChange = { newBirthYear ->
                                birthYear = newBirthYear
                                isBirthYearError =
                                    (birthYear.isNotEmpty()
                                            && !birthYear.matches(Regex("^\\d+\$"))
                                            || (birthYear.toIntOrNull() ?: 0) < 0
                                            || ((birthYear.toIntOrNull()) ?: 0) > 2023)
                            },
                            label = {
                                if (birthYear.isNotEmpty()) {
                                    null
                                } else if (!birthYearFocus){
                                    Text(text = "Enter your birth year")
                                } else {
                                    null
                                }
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 24.dp, vertical = 8.dp)
                                .onFocusChanged { birthYearFocus = it.isFocused },
                            shape = RoundedCornerShape(24.dp),
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                unfocusedBorderColor = Color(0xFFDB8FF6),
                                textColor = Color(0xFFDB8FF6),
                                focusedBorderColor = Color(0xFFDB8FF6)
                            ),
                            keyboardOptions = KeyboardOptions.Default.copy(
                                keyboardType = KeyboardType.Number
                            )
                        )

                        if (isBirthYearError) {
                            Text(
                                text = "Invalid Year",
                                color = Color.Red,
                                modifier = Modifier
                                    .padding(top = 0.dp, start = 36.dp)
                            )
                        }

                        LaunchedEffect(name, birthYear) {
                            isSubmitEnabled =
                                !isBirthYearError && name.isNotEmpty() && birthYear.isNotEmpty()
                        }

                        Button(
                            onClick = {
                                isSubmitted = true
                                scope.launch {
                                    snackbarHostState.showSnackbar(
                                        "Hi, $name! Your age is $age"
                                    )
                                }
                            },
                            enabled = isSubmitEnabled,
                            modifier = Modifier
                                .padding(top = 12.dp)
                                .align(CenterHorizontally),
                            colors = ButtonDefaults.buttonColors(
                                contentColor = Color.White,
                                containerColor = Color(0xFFD096E8)
                            )
                        ) {
                            Text(
                                text = "CALCULATE YOUR AGE",
                                modifier = Modifier
                                    .padding(vertical = 8.dp)
                            )
                        }

                        if ((birthYear.isNotEmpty()
                                    && !birthYear.matches(Regex("^\\d+\$"))
                                    || (birthYear.toIntOrNull() ?: 0) < 0
                                    || (birthYear.toIntOrNull() ?: 0) > 2023) || birthYear.isEmpty()
                        ) {
                            isSubmitted = false
                        }

                        if (isSubmitted && !isBirthYearError && birthYear.isNotEmpty()) {
                            Text(
                                text = "Hi, $name! Your age is $age",
                                color = Color(0xFFD096E8),
                                modifier = Modifier
                                    .padding(top = 16.dp)
                                    .border(2.dp, Color(0xFFD096E8), RoundedCornerShape(32.dp))
                                    .align(CenterHorizontally)
                                    .padding(vertical = 12.dp, horizontal = 32.dp),
                                fontWeight = FontWeight.Bold,
                                fontSize = 18.sp
                            )
                        }
                    }
                }
            }
        }
    )
}


