package com.example.screen

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel

// Modelo de datos de Contacto
data class Contacto(val nombre: String, val apellido: String, val telefono: String, val hobbie: String)

class ContactViewModel : ViewModel() {
    var contactos = mutableStateListOf<Contacto>()
        private set

    fun agregarContacto(contacto: Contacto) {
        contactos.add(contacto)
    }

    fun eliminarContacto(index: Int) {
        contactos.removeAt(index)
    }

    fun editarContacto(index: Int, contacto: Contacto) {
        contactos[index] = contacto
    }
}

