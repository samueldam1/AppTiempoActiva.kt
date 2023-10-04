package com.example.tiempoactiva

import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tiempoactiva.ui.theme.TiempoActivaTheme

class MainActivity : ComponentActivity() {

    val TAG: String = "Estado"

    var isAppActive: Boolean = false
    var totalTimeActive: Long = 0
    var starTime: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TiempoActivaTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android")
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume")
        if (!isAppActive) {
            isAppActive = true
            starTime = SystemClock.elapsedRealtime()
        }
        Toast.makeText(this, "Tiempo Activo: ${totalTimeActive / 1000}s", Toast.LENGTH_LONG).show()
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause")
        if (isAppActive) {
            isAppActive = false
            var elapsedTime = SystemClock.elapsedRealtime() - starTime
            totalTimeActive += elapsedTime
            Log.d(TAG, "Tiempo Activo: ${totalTimeActive / 1000}s")
        }
    }
}
@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Column {
        Text(
            text = "Hello $name!",
            fontSize = 38.sp,
            modifier = Modifier.padding(vertical = 150.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TiempoActivaTheme {
        Greeting("Samuel")
    }
}