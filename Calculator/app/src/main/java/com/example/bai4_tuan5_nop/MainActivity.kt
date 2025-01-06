package com.example.bai4_tuan5_nop

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.bai4_tuan5_nop.ui.theme.Bai4_Tuan5_NopTheme
import org.w3c.dom.Text

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Bai4_Tuan5_NopTheme {
                BMIScreen()
            }
        }
    }
}
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BMIScreen(modifier: Modifier=Modifier)
{
    var cao by remember {
        mutableStateOf("")
    }
    var nang by remember {
        mutableStateOf("")
    }
    var  ketqua by remember {
        mutableStateOf(" ")
    }
    Scaffold(
        modifier = modifier.padding(5.dp),
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = Color.Blue,
                    titleContentColor = Color.White
                ),
                title = {
                    Text(
                        text = "BMI Calculator",
                        fontWeight = FontWeight.ExtraBold
                    )
                }
            )
        }
    ) {     it->
        Column (
            modifier = modifier
                .padding(it)
                .fillMaxSize()
        ){
            TextField(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(bottom = 20.dp, top = 20.dp),
                value = cao,
                onValueChange = { cao=it},
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                trailingIcon = {Text(text = "Cm", color = Color.Gray)},
                label = {Text(text = "Chiều cao")}
            )
            TextField(modifier= modifier
                .fillMaxWidth()
                .padding(bottom = 20.dp),
                    value = nang,
                    onValueChange = { nang=it},
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    trailingIcon = {Text(text = "Kg", color = Color.Gray)},
                    label={Text(text = "Cân nặng")}
            )
            Box(modifier = modifier.padding(bottom = 20.dp)){
                Text(
                    text = "BMI: $ketqua", fontWeight = FontWeight.ExtraBold,
                    color = Color.Red
                )
            }
            Button(onClick = {
                try {
                    ketqua= ""
                    var c= cao.toDouble()
                    var n = nang.toDouble()
                    var kq=n/Math.pow(c/100,2.0)
                    ketqua += "${String.format("%.2f", kq)}. "
                    ketqua +=if (kq<18.5){
                        "Bạn gầy hơn so với chuẩn"
                    }
                    else if(kq>25)
                    {
                        "Bạn nặng hơn so với chuẩn"
                    }
                    else
                    {
                        "Hoàn toàn bình thường"
                    }
                }
                catch (e: Exception){
                    ketqua= " Nhập dữ liệu sai"
                }
            },
                modifier=modifier.align(Alignment.CenterHorizontally))
            {
                Text(text = "Tính", fontWeight = FontWeight.Bold)
            }
        }
    }
}



