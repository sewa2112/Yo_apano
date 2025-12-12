package com.example.yo_apano.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.yo_apano.repository.UsuarioRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class `LoginTest.kt` {

    // Regla para ejecutar tareas de LiveData de forma síncrona.
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    // Dispatcher de prueba para las coroutines.
    private val testDispatcher = StandardTestDispatcher()

    private lateinit var viewModel: LoginViewModel
    private lateinit var repository: UsuarioRepository

    @Before
    fun setUp() {
        // Establece el dispatcher principal para las pruebas.
        Dispatchers.setMain(testDispatcher)
        repository = UsuarioRepository()
        viewModel = LoginViewModel(repository)
    }

    @After
    fun tearDown() {
        // Restablece el dispatcher principal después de cada prueba.
        Dispatchers.resetMain()
    }

    @Test
    fun `login con credenciales validas debe actualizar el usuario actual`() = runTest {
        // Llama a la función de login con credenciales válidas.
        viewModel.login("test@correo.com", "1234")

        // Avanza el dispatcher para que se ejecuten las coroutines.
        testDispatcher.scheduler.advanceUntilIdle()

        // Verifica que el usuario actual no sea nulo y que el error sea nulo.
        assertNotNull(viewModel.usuarioActual.value)
        assertNull(viewModel.error.value)
        assertEquals("test@correo.com", viewModel.usuarioActual.value?.email)
    }

    @Test
    fun `login con credenciales invalidas debe establecer un mensaje de error`() = runTest {
        // Llama a la función de login con credenciales inválidas.
        viewModel.login("test@correo.com", "error")

        // Avanza el dispatcher para que se ejecuten las coroutines.
        testDispatcher.scheduler.advanceUntilIdle()

        // Verifica que el usuario actual sea nulo y que el mensaje de error sea el esperado.
        assertNull(viewModel.usuarioActual.value)
        assertEquals("Correo o contraseña incorrectos", viewModel.error.value)
    }

    @Test
    fun `login con campos vacios debe establecer un mensaje de error`() = runTest {
        // Llama a la función de login con el campo de la contraseña vacío.
        viewModel.login("test@correo.com", "")

        // Avanza el dispatcher para que se ejecuten las coroutines.
        testDispatcher.scheduler.advanceUntilIdle()

        // Verifica que el usuario actual sea nulo y que el mensaje de error sea el esperado.
        assertNull(viewModel.usuarioActual.value)
        assertEquals("Completa todos los campos", viewModel.error.value)
    }
}
