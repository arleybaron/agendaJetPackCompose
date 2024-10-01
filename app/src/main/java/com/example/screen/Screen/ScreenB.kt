package com.example.screen.Screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.screen.ContactViewModel

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.ui.draw.clip

@Composable
fun ScreenB(navController: NavController, contactViewModel: ContactViewModel) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFF5733))
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Lista de Contactos", fontSize = 24.sp, color = Color.White)

            // Mostrar los contactos registrados
            contactViewModel.contactos.forEachIndexed { index, contacto ->
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                        .padding(8.dp)

                ) {
                    Text(
                        text = "Nombre: ${contacto.nombre} ${contacto.apellido}",
                        color = Color.White
                    )
                    Text(text = "Teléfono: ${contacto.telefono}", color = Color.White)
                    Text(text = "Hobbie: ${contacto.hobbie}", color = Color.White)
                    Spacer(modifier = Modifier.height(8.dp))
                }

                Row {
                    // Botón para editar
                    IconButton(onClick = {
                        // Navegar a ScreenA con los datos del contacto para editar
                        navController.navigate("screen-a/${index}")
                    }) {
                        Icon(Icons.Default.Edit, contentDescription = "Editar", tint = Color.White)
                    }
                    // Botón para eliminar
                    IconButton(onClick = {
                        // Eliminar el contacto
                        contactViewModel.eliminarContacto(index)
                    }) {
                        Icon(
                            Icons.Default.Delete,
                            contentDescription = "Eliminar",
                            tint = Color.White
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = {

                    // Volver a la pantalla A
                    navController.navigate("screen-a") {
                        popUpTo("screen-a") { inclusive = true }
                    }
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White,
                    contentColor = Color(0xFFFF5733)
                ),
                modifier = Modifier.padding(top = 16.dp)
            ) {
                Text("Volver")
            }
        }
    }
}
