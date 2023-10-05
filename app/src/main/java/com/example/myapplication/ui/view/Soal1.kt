package com.example.myapplication.ui.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material3.ExperimentalMaterial3Api
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
import com.example.myapplication.R

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun Preview1() {
    Challenge1()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Challenge1() {

    var base by rememberSaveable { mutableStateOf("") }
    var height by rememberSaveable { mutableStateOf("") }
    var area by rememberSaveable { mutableStateOf(0.0) }

    LaunchedEffect(base, height) {
        val baseNumeric = base.toDoubleOrNull() ?: 0.0
        val heightNumeric = height.toDoubleOrNull() ?: 0.0
        area = ( baseNumeric * heightNumeric ) / 2
    }

    Column (
        Modifier
            .padding(
                vertical = 32.dp,
                horizontal = 24.dp
            )
    ){
        Image(
            painterResource(id = R.drawable.ic_action_name),
            null,
            Modifier
                .size(120.dp)
                .rotate(-90f)
                .fillMaxWidth()
                .align(CenterHorizontally),
            contentScale = ContentScale.Crop
        )

        OutlinedTextField(
            value = base,
            onValueChange = { base = it },
            modifier = Modifier
                .clip(RoundedCornerShape(16.dp))
                .fillMaxWidth()
                .padding(
                    horizontal = 12.dp,
                    vertical = 8.dp
                ),
            label = { Text(
                text = "Base",
                color = Color.Blue
            ) },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            ),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color(0xFF62519E),
                unfocusedBorderColor = Color.Blue
            ),
            shape = RoundedCornerShape(32.dp)
        )



        OutlinedTextField(
            value = height,
            onValueChange = { height = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = 12.dp,
                    vertical = 8.dp
                ),
            label = { Text(
                text = "Height",
                color = Color.Blue
            ) },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            ),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color(0xFF62519E),
                unfocusedBorderColor = Color.Blue
            ),
            shape = RoundedCornerShape(32.dp)
        )

        Text(
            text = "The Triangle Area is: ",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .align(CenterHorizontally)
                .padding(top = 12.dp)
        )

        Text(
            text = "$area",
            fontSize = 40.sp,
            fontWeight = FontWeight.Black,
            modifier = Modifier
                .align(CenterHorizontally)
                .padding(top = 8.dp)
        )
    }
}


