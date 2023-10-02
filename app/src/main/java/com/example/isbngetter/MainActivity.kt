package com.example.isbngetter

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.isbngetter.ui.theme.ISBNGetterTheme
/*バーコード用*/
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import com.google.zxing.integration.android.IntentIntegrator
import com.google.zxing.integration.android.IntentResult
import com.journeyapps.barcodescanner.ScanContract
import com.journeyapps.barcodescanner.ScanIntentResult
import com.journeyapps.barcodescanner.ScanOptions

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ISBNGetterTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    //Greeting("Android")
                    ScanCode()
                }
            }
        }
    }
}

@Composable
fun ScanCode() {
    // QRコードを読み取りが成功しデータを取得したらsuccess,失敗したらerrorを表示
    val barcodeLauncher = rememberLauncherForActivityResult(ScanContract()) { result: ScanIntentResult ->
        if (result.contents == null) {
            println("error")
        } else {
            Log.i("ISBN GET SUCCESS",result.contents)
            println(result.contents)
        }
    }



    /*todo:ボタンの大きさ修正*/
    Button(onClick = {barcodeLauncher.launch(ScanOptions())}) {
        Text(text = "QRコード読み取り")
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ISBNGetterTheme {
        ScanCode()
    }
}