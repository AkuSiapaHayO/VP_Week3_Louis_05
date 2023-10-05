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

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun Preview4() {
    Challenge4()
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Challenge4() {

    var score1 by rememberSaveable { mutableStateOf("") }
    var score2 by rememberSaveable { mutableStateOf("") }
    var score3 by rememberSaveable { mutableStateOf("") }

    var score1Focus by rememberSaveable { mutableStateOf(false) }
    var score2Focus by rememberSaveable { mutableStateOf(false) }
    var score3Focus by rememberSaveable { mutableStateOf(false) }

    var isScore1Error by rememberSaveable { mutableStateOf(false) }
    var isScore2Error by rememberSaveable { mutableStateOf(false) }
    var isScore3Error by rememberSaveable { mutableStateOf(false) }
    var isSubmitted by rememberSaveable { mutableStateOf(false) }

    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    val average: Double =
        if (!isScore1Error && !isScore2Error && !isScore3Error && score1.isNotBlank() && score2.isNotBlank() && score3.isNotBlank()) {
            val score1Double = score1.toDouble()
            val score2Double = score2.toDouble()
            val score3Double = score3.toDouble()
            val averageValue = (score1Double + score2Double + score3Double) / 3
            averageValue
        } else {
            0.0
        }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
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
                            painter = painterResource(id = R.drawable.logo_uc_fix_sep_2021_01),
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .padding(bottom = 24.dp)
                                .size(120.dp)
                                .fillMaxWidth()
                                .align(Alignment.CenterHorizontally)
                        )

                        OutlinedTextField(
                            value = score1,
                            onValueChange = {
                                score1 = it
                                isSubmitted = false

                            },
                            label = {
                                if (score1.isNotEmpty()) {
                                    null
                                } else if (!score1Focus) {
                                    Text(text = "Louis's Score")
                                } else {
                                    null
                                }
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 24.dp, vertical = 8.dp)
                                .onFocusChanged { score1Focus = it.isFocused },
                            shape = RoundedCornerShape(24.dp),
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                unfocusedBorderColor = Color(0xFFDB8FF6),
                                textColor = Color(0xFFDB8FF6),
                                focusedBorderColor = Color(0xFFDB8FF6)
                            ),
                            keyboardOptions = KeyboardOptions.Default.copy(
                                keyboardType = KeyboardType.Number,
                                imeAction = ImeAction.Next
                            ),
                            isError = isScore1Error
                        )

                        if (isScore1Error) {
                            Text(
                                text = "Invalid Format",
                                color = Color.Red,
                                modifier = Modifier
                                    .padding(top = 0.dp, start = 36.dp)
                            )
                        }

                        OutlinedTextField(
                            value = score2,
                            onValueChange = { score2 = it },
                            label = {
                                if (score2.isNotEmpty()) {
                                    null
                                } else if (!score2Focus) {
                                    Text(text = "Karyna's Score")
                                } else {
                                    null
                                }
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 24.dp, vertical = 8.dp)
                                .onFocusChanged { score2Focus = it.isFocused },
                            shape = RoundedCornerShape(24.dp),
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                unfocusedBorderColor = Color(0xFFDB8FF6),
                                textColor = Color(0xFFDB8FF6),
                                focusedBorderColor = Color(0xFFDB8FF6)
                            ),
                            keyboardOptions = KeyboardOptions.Default.copy(
                                keyboardType = KeyboardType.Number
                            ),
                            isError = isScore2Error
                        )

                        if (isScore2Error) {
                            Text(
                                text = "Invalid Format",
                                color = Color.Red,
                                modifier = Modifier
                                    .padding(top = 0.dp, start = 36.dp)
                            )
                        }

                        OutlinedTextField(
                            value = score3,
                            onValueChange = { score3 = it },
                            label = {
                                if (score3.isNotEmpty()) {
                                    null
                                } else if (!score3Focus) {
                                    Text(text = "Bryan's Score")
                                } else {
                                    null
                                }
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 24.dp, vertical = 8.dp)
                                .onFocusChanged { score3Focus = it.isFocused },
                            shape = RoundedCornerShape(24.dp),
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                unfocusedBorderColor = Color(0xFFDB8FF6),
                                textColor = Color(0xFFDB8FF6),
                                focusedBorderColor = Color(0xFFDB8FF6)
                            ),
                            keyboardOptions = KeyboardOptions.Default.copy(
                                keyboardType = KeyboardType.Number
                            ),
                            isError = isScore3Error
                        )

                        if (isScore3Error) {
                            Text(
                                text = "Invalid Format",
                                color = Color.Red,
                                modifier = Modifier
                                    .padding(top = 0.dp, start = 36.dp)
                            )
                        }

                        Button(
                            onClick = {

                                isScore1Error = !checkScore(score1)
                                isScore2Error = !checkScore(score2)
                                isScore3Error = !checkScore(score3)

                                if (!isScore1Error && !isScore2Error && !isScore3Error) {
                                    isSubmitted = true
                                    scope.launch {
                                        snackbarHostState.showSnackbar(
                                            if (average < 70) {
                                                "Siswa perlu diberi soal tambahan"
                                            } else {
                                                "Siswa mengerti pembelajaran"
                                            }
                                        )
                                    }
                                }
                            },
                            modifier = Modifier
                                .padding(top = 12.dp)
                                .align(Alignment.CenterHorizontally),
                            colors = ButtonDefaults.buttonColors(
                                contentColor = Color.White,
                                containerColor = Color(0xFFD096E8)
                            )
                        ) {
                            Text(
                                text = "CALCULATE AVERAGE",
                                modifier = Modifier
                                    .padding(vertical = 8.dp)
                            )
                        }

                        if (isScore1Error && isScore2Error && isScore3Error) {
                            isSubmitted = false
                        }

                        if (isSubmitted) {
                            Text(
                                text = "Average Score : $average",
                                color = Color(0xFFD096E8),
                                modifier = Modifier
                                    .padding(top = 16.dp)
                                    .border(2.dp, Color(0xFFD096E8), RoundedCornerShape(32.dp))
                                    .align(Alignment.CenterHorizontally)
                                    .padding(vertical = 24.dp, horizontal = 32.dp),
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

fun checkScore(score: String): Boolean {
    return try {
        val scoreDouble = score.toDouble()
        scoreDouble in 0.0..100.0
    } catch (e: NumberFormatException) {
        false
    }
}
