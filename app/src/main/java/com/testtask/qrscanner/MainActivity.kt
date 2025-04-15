package com.testtask.qrscanner

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            QRCodeGeneratorApp()
        }
    }
}

@Composable
fun QRCodeGeneratorApp() {
    var text by remember { mutableStateOf("") }
    var qrCodeBitmap by remember { mutableStateOf<ImageBitmap?>(null) }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = "Генератор QR-кода",
                style = MaterialTheme.typography.headlineMedium
            )

            OutlinedTextField(
                value = text,
                onValueChange = { text = it },
                label = { Text("Введите текст") },
                modifier = Modifier.fillMaxWidth()
            )

            Button(
                onClick = {
                    if (text.isNotBlank()) {
                        qrCodeBitmap = QrCodeGenerator.generateQrCode(text)
                    }
                }
            ) {
                Text("Сгенерировать QR-код")
            }

            Spacer(modifier = Modifier.height(16.dp))

            if (qrCodeBitmap != null) {
                Image(
                    bitmap = qrCodeBitmap!!,
                    contentDescription = "QR Code",
                    modifier = Modifier.size(250.dp)
                )
            }
        }
    }
}