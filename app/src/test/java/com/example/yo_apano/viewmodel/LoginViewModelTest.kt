package com.example.yo_apano.viewmodel

import com.example.yo_apano.repository.UsuarioRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock

@ExperimentalCoroutinesApi
class LoginViewModelTest {

    private lateinit var viewModel: LoginViewModel
    private lateinit var repository: UsuarioRepository
    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        repository = mock(UsuarioRepository::class.java)
        viewModel = LoginViewModel(repository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `login with invalid email should set error`() = runTest {
        // Arrange
        val email = "test@invalid.com"
        val password = "password"

        // Act
        viewModel.login(email, password)
        testDispatcher.scheduler.advanceUntilIdle()

        // Assert
        assertEquals("El correo debe ser de @gmail.com", viewModel.error.value)
    }

    @Test
    fun `register with invalid email should set error`() = runTest {
        // Arrange
        val email = "test@another.com"
        val password = "password"
        val confirmPassword = "password"

        // Act
        viewModel.registrarUsuario(email, password, confirmPassword)
        testDispatcher.scheduler.advanceUntilIdle()

        // Assert
        assertEquals("El correo debe ser de @gmail.com", viewModel.error.value)
    }
}
