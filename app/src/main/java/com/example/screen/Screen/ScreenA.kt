package com.example.screen.Screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.screen.ContactViewModel
import com.example.screen.Contacto

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenA(
    navController: NavController,
    contactViewModel: ContactViewModel = viewModel(),
    contactIndex: Int? = null
){
    var nombre by remember { mutableStateOf(TextFieldValue()) }
    var apellido by remember { mutableStateOf(TextFieldValue()) }
    var telefono by remember { mutableStateOf(TextFieldValue()) }
    var hobbie by remember { mutableStateOf(TextFieldValue()) }

    var showError by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf("") }

    val focusManager = LocalFocusManager.current

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFF5733))
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center, // Centrar verticalmente
            horizontalAlignment = Alignment.CenterHorizontally // centrar horizontalmente
        ) {
            Text(text = "Agenda de Contactos", fontSize = 24.sp, color = Color.White)

            OutlinedTextField(
                value = nombre,
                onValueChange = { nombre = it },
                label = { Text("Nombre", color = Color.White) },
                shape = CircleShape, // Campos redondeados
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color(0xFFFFFFFF),
                    unfocusedBorderColor = Color.White
                ),
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next // Configurar IME para "Next"
                ),
                keyboardActions = KeyboardActions(
                    onNext = { focusManager.moveFocus(FocusDirection.Down) } // Mover foco al siguiente
                )
            )

            OutlinedTextField(
                value = apellido,
                onValueChange = { apellido = it },
                label = { Text("Apellido", color = Color.White) },
                shape = CircleShape,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color(0xFFFFFFFF),
                    unfocusedBorderColor = Color.White
                ),
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next // Configurar IME para "Next"
                ),
                keyboardActions = KeyboardActions(
                    onNext = { focusManager.moveFocus(FocusDirection.Down) } // Mover foco al siguiente
                )
            )

            OutlinedTextField(
                value = telefono,
                onValueChange = { telefono = it },
                label = { Text("Teléfono", color = Color.White) },
                shape = CircleShape, // Bordes redondeados
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color(0xFFFFFFFF),
                    unfocusedBorderColor = Color.White
                ),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number, // Solo números
                    imeAction = ImeAction.Next // Configurar IME para "Next"
                ),
                keyboardActions = KeyboardActions(
                    onNext = { focusManager.moveFocus(FocusDirection.Down) } // Mover foco al siguiente
                )
            )

            OutlinedTextField(
                value = hobbie,
                onValueChange = { hobbie = it },
                label = { Text("Hobbie", color = Color.White) },
                shape = CircleShape, // Bordes redondeados
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color(0xFFFFFFFF),
                    unfocusedBorderColor = Color.White
                ),
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done // Configurar IME para "Done"
                ),
                keyboardActions = KeyboardActions(
                    onDone = { focusManager.clearFocus() } // Cerrar teclado al presionar "Done"
                )
            )

            if(showError){
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color(0xFFFFEBEB), shape = CircleShape)
                        .padding(8.dp)
                ){
                    Text(
                        text = errorMessage,
                        color = Color(0xFFB00020),
                        modifier = Modifier.padding(8.dp)
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
            }

            Button(
                onClick = {
                    if (nombre.text.isBlank() || apellido.text.isBlank() || telefono.text.isBlank() || hobbie.text.isBlank()) {
                        errorMessage = "Todos los campos son obligatorios"
                        showError = true
                    } else if (!telefono.text.matches(Regex("\\d{10}"))) {
                        errorMessage = "El telefono debe contener exactamente 10 digitos"
                        showError = true
                    } else {
                        showError = false
                        val nuevoContacto = Contacto(
                            nombre = nombre.text,
                            apellido = apellido.text,
                            telefono = telefono.text,
                            hobbie = hobbie.text
                        )
                        if (contactIndex != null) {
                            // Editar contacto existente
                            contactViewModel.editarContacto(contactIndex, nuevoContacto)
                        } else {
                            // Agregar nuevo contacto
                            contactViewModel.agregarContacto(nuevoContacto)
                        }
                        navController.navigate("screen-b")
                    }
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White,
                    contentColor = Color(0xFFFF5733)
                ),
                modifier = Modifier.padding(top = 16.dp)
            ) {
                Text(if (contactIndex != null) "Actualizar Contacto" else "Registrar Contacto")
            }
        }
    }
}
