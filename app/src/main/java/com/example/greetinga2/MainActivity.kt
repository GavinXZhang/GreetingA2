package com.example.greetinga2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.example.greetinga2.ui.theme.GreetingA2Theme
import java.util.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GreetingA2Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    GreetingScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun GreetingScreen(modifier: Modifier = Modifier) {
    // State to hold the name entered by the user
    var name by remember { mutableStateOf(TextFieldValue("")) }
    // State to hold the personalized greeting
    var greetingMessage by remember { mutableStateOf("") }

    // Layout: A box to center elements
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Input TextField for user's name
            TextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Enter your name") },
                modifier = Modifier.padding(16.dp)
            )

            // Button to generate the greeting
            Button(
                onClick = {
                    greetingMessage = generateGreetingMessage(name.text)
                },
                modifier = Modifier.padding(16.dp)
            ) {
                Text("Greet Me")
            }

            // Display the personalized greeting
            if (greetingMessage.isNotEmpty()) {
                Text(
                    text = greetingMessage,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
    }
}

// Function to generate the greeting message based on time of day and user's name
fun generateGreetingMessage(name: String): String {
    // Get the current time of day
    val currentHour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)

    // Determine the time-based greeting
    val timeGreeting = when (currentHour) {
        in 5..11 -> "Good Morning"
        in 12..17 -> "Good Afternoon"
        in 18..21 -> "Good Evening"
        else -> "Hello"
    }

    // Return the personalized greeting
    return "$timeGreeting, $name!"
}

@Preview(showBackground = true)
@Composable
fun GreetingScreenPreview() {
    GreetingA2Theme {
        GreetingScreen()
    }
}