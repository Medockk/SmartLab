package com.example.smartlab

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.smartlab.CreateCard.CreateCardScreen
import com.example.smartlab.CreatePassword.CreatePasswordScreen
import com.example.smartlab.EmailCode.EmailCodeScreen
import com.example.smartlab.OnBoard.OnBoardScreen
import com.example.smartlab.SignIn.SignInScreen
import com.example.smartlab.SplashScreen.SplashScreen
import com.example.smartlab.data.network.SupabaseInit.client
import com.example.smartlab.feature_app.domain.model.UserData
import com.example.smartlab.feature_app.domain.usecase.Auth.SignInUseCase
import com.example.smartlab.feature_app.domain.usecase.Auth.SignUpUseCase
import com.example.smartlab.feature_app.presentation.Analuzes.AnalyzesScreen
import com.example.smartlab.feature_app.presentation.AnalyzesCategory.AnalyzesCategoryScreen
import com.example.smartlab.feature_app.presentation.Cart.CartScreen
import com.example.smartlab.feature_app.presentation.MakingOrder.MakingOrderScreen
import com.example.smartlab.feature_app.presentation.PatientCard.PatientCardScreen
import com.example.smartlab.feature_app.presentation.Payment.PaymentScreen
import com.example.smartlab.navGraph.Route
import com.example.smartlab.ui.theme.SmartLabTheme
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.auth.providers.builtin.Email
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.decorView.apply {
            systemUiVisibility = View.SYSTEM_UI_FLAG_VISIBLE or View.SYSTEM_UI_FLAG_FULLSCREEN

        }
        enableEdgeToEdge()
        setContent {
            val viewModel: MainActivityViewModel = koinViewModel()
            val navController = rememberNavController()
            LaunchedEffect(key1 = true) {
                viewModel.checkRoute()
            }
            SmartLabTheme {
                NavHost(navController, startDestination = Route.SplashScreen.route) {
                    composable("q") {
                        q(navController)
                    }
                    composable("q1") {
                        q1(navController)
                    }
                    composable(Route.SplashScreen.route) {
                        SplashScreen(navController)
                    }
                    composable(Route.OnBoardScreen.route) {
                        OnBoardScreen(navController)
                    }
                    composable(Route.SignInScreen.route) {
                        SignInScreen(navController)
                    }
                    composable(Route.EmailCodeScreen.route) {
                        EmailCodeScreen(navController)
                    }
                    composable(Route.CreatePasswordScreen.route) {
                        CreatePasswordScreen(navController)
                    }
                    composable(Route.CreateCardScreen.route) {
                        CreateCardScreen(navController)
                    }
                    composable(Route.AnalyzesScreen.route) {
                        AnalyzesScreen(navController)
                    }
                    composable(Route.AnalyzesCategoryScreen.route) {
                        AnalyzesCategoryScreen(navController)
                    }
                    composable(Route.PatientCardScreen.route) {
                        PatientCardScreen(navController)
                    }
                    composable(Route.CartScreen.route) {
                        CartScreen(navController)
                    }
                    composable(Route.MakingOrderScreen.route) {
                        MakingOrderScreen(navController)
                    }
                    composable(Route.PaymentScreen.route) {
                        PaymentScreen()
                    }
                }
            }
        }
    }
}

@Composable
fun q(
    navController: NavController,
    viewModel: qViewModel = koinViewModel()
) {

    val state = viewModel.state.value

    LaunchedEffect(key1 = !state.isLogged) {
        if (state.isLogged) {
            navController.navigate("q1") {
                popUpTo("q") {
                    inclusive = true
                }
            }
            Log.v("signin", "is already sign in")
        }
    }

    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        val uid = remember { mutableStateOf("") }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(
                state.email, {
                    viewModel.onEvent(qEvent.EnteredEmail(it))
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                label = {
                    Text("email")
                },
                textStyle = TextStyle(
                    color = Color.Black
                )
            )
            OutlinedTextField(
                state.pass, {
                    viewModel.onEvent(qEvent.EnteredPassword(it))
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                label = {
                    Text("password")
                },
                textStyle = TextStyle(
                    color = Color.Black
                )
            )

            Button({
                viewModel.onEvent(qEvent.SignIn)
                uid.value = client.auth.currentUserOrNull()?.email.toString()
            }) {
                Text("sign in", fontSize = 32.sp)
            }

            Spacer(Modifier.padding(vertical = 10.dp))
            Button({
                viewModel.onEvent(qEvent.SignUp)
            }) {
                Text("sign Up")
            }
        }
    }
}

class qViewModel(
    private val useCase: SignInUseCase,
    private val useCase1: SignUpUseCase
) : ViewModel() {

    private val _state = mutableStateOf(qState())
    val state: State<qState> = _state

    init {
        _state.value = state.value.copy(
            isLogged = getCurrentUser()
        )
    }

    private fun getCurrentUser(): Boolean {
        return client.auth.currentUserOrNull() != null
    }

    fun onEvent(event: qEvent) {
        when (event) {
            is qEvent.EnteredEmail -> {
                _state.value = state.value.copy(
                    email = event.value
                )
            }

            is qEvent.EnteredPassword -> {
                _state.value = state.value.copy(
                    pass = event.value
                )
            }

            qEvent.SignIn -> {
                viewModelScope.launch {
                    try {
                        useCase(
                            mail = UserData.email,
                            pass = UserData.password
                        )
//                        useCase(
//                            mail = state.value.email,
//                            pass = state.value.pass
//                        )

//                        client.auth.signInWith(Email) {
//                            this.email = state.value.email
//                            this.password = state.value.pass
//                        }
                        _state.value = state.value.copy(
                            isLogged = true
                        )
                        Log.v("supaIn", "sign in")
                    } catch (e: Exception) {
                        Log.e("supaError", e.message.toString())
                    }
                }
            }

            qEvent.SignUp -> {
                viewModelScope.launch {
                    try {
                        useCase1(
                            mail = state.value.email,
                            pass = state.value.pass,
                            userData = UserData(
                                name = "name",
                                surname = "surname",
                                patronymic = "patronymic",
                                birthdayData = "birthdayData",
                                gender = "gender"
                            )
                        )
//                        client.auth.signUpWith(Email){
//                            this.email = _state.value.email
//                            this.password = _state.value.pass
//                        }
                        _state.value = state.value.copy(
                            isLogged = true
                        )
                        Log.v("supaUp", "sign up")
                    } catch (e: Exception) {
                        Log.e("supaUp", e.message.toString())
                    }
                }
            }
        }
    }
}

sealed class qEvent {
    data class EnteredEmail(val value: String) : qEvent()
    data class EnteredPassword(val value: String) : qEvent()

    data object SignUp : qEvent()
    data object SignIn : qEvent()
}

data class qState(
    val isLogged: Boolean = false,
    val email: String = "",
    val pass: String = "",
)

@Composable
fun q1(
    navController: NavController,
    viewModel: q1ViewModel = viewModel()
) {
    val state = viewModel.state.value

    LaunchedEffect(key1 = !state.isSignOut) {
        if (state.isSignOut) {
            navController.navigate("q") {
                popUpTo("q1") {
                    inclusive = true
                }
            }
        }
    }

    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column {
            Text("You are logged")
            Button(
                {
                    viewModel.signOut()
                }
            ) { Text("sign out") }
        }
    }
}

class q1ViewModel : ViewModel() {

    private val _state = mutableStateOf(q1State())
    val state: State<q1State> = _state

    fun signOut() {
        viewModelScope.launch {
            client.auth.signOut()
            _state.value = state.value.copy(
                isSignOut = true
            )
        }
    }
}

data class q1State(
    val isSignOut: Boolean = false,
)