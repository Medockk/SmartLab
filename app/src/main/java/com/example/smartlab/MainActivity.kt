package com.example.smartlab

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
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
import coil.compose.AsyncImage
import com.example.smartlab.CreateCard.CreateCardScreen
import com.example.smartlab.CreatePassword.CreatePasswordScreen
import com.example.smartlab.EmailCode.EmailCodeScreen
import com.example.smartlab.OnBoard.OnBoardScreen
import com.example.smartlab.SignIn.SignInScreen
import com.example.smartlab.SplashScreen.SplashScreen
import com.example.smartlab.data.network.SupabaseInit.client
import com.example.smartlab.feature_app.domain.model.UserData
import com.example.smartlab.feature_app.domain.usecase.Auth.GetUserDataUseCase
import com.example.smartlab.feature_app.domain.usecase.Auth.SignInUseCase
import com.example.smartlab.feature_app.domain.usecase.Auth.SignUpUseCase
import com.example.smartlab.feature_app.presentation.Analuzes.AnalyzesScreen
import com.example.smartlab.feature_app.presentation.AnalyzesCategory.AnalyzesCategoryScreen
import com.example.smartlab.feature_app.presentation.Cart.CartScreen
import com.example.smartlab.feature_app.presentation.MakingOrder.MakingOrderScreen
import com.example.smartlab.feature_app.presentation.PatientCard.PatientCardScreen
import com.example.smartlab.feature_app.presentation.Payment.PaymentScreen
import com.example.smartlab.navGraph.Route
import com.example.smartlab.test_composable_func.Test
import com.example.smartlab.ui.theme.SmartLabTheme
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.auth.providers.builtin.OTP
import io.github.jan.supabase.postgrest.postgrest
import io.github.jan.supabase.postgrest.query.filter.TextSearchType
import io.github.jan.supabase.storage.storage
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable
import org.koin.androidx.compose.koinViewModel
import java.io.ByteArrayOutputStream
import kotlin.time.Duration
import kotlin.time.Duration.Companion.minutes

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.decorView.apply {
            systemUiVisibility =
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_FULLSCREEN
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
                    composable("Test"){
                        Test(navController)
                    }
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
                        PaymentScreen(navController)
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
    LaunchedEffect(key1 = !state.isSignUpped) {
        if (state.isSignUpped) {
            navController.navigate("q1") {
                popUpTo("q") {
                    inclusive = true
                }
            }
            Log.v("signup", "is already sign up")
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

            val c = rememberCoroutineScope()
            Spacer(Modifier.padding(vertical = 10.dp))
            Button({
                viewModel.onEvent(qEvent.SignUp)

                c.launch {
                    Log.e("o", client.auth.currentSessionOrNull()?.accessToken.toString())
                    Log.e("o", client.auth.currentSessionOrNull()?.providerRefreshToken.toString())
                    Log.e("o", client.auth.currentSessionOrNull()?.providerToken.toString())
                    Log.e("o", client.auth.currentSessionOrNull()?.refreshToken.toString())
                    Log.e("o", client.auth.currentSessionOrNull()?.tokenType.toString())
                    Log.e("o", client.auth.currentSessionOrNull()?.type.toString())
                    Log.e("o", client.auth.currentSessionOrNull()?.user.toString())
                }
            }) {
                Text("otp")
            }
        }
    }
}

private const val mail = "andreev.arsenij2020@gmail.com"

class qViewModel(
    private val signInUseCase: SignInUseCase,
    private val signUpUseCase: SignUpUseCase,
    private val getUserDataUseCase: GetUserDataUseCase
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
                UserData.email = state.value.email
            }

            is qEvent.EnteredPassword -> {
                _state.value = state.value.copy(
                    pass = event.value
                )
                UserData.password = state.value.pass
            }

            qEvent.SignIn -> {
                viewModelScope.launch {
                    try {
                        Log.e("clicked", "clicked")
                        if (UserData.password.length == 4) {
                            val signIn = signInUseCase.invoke(
                                mail = state.value.email,
                                pass = UserData.password + "00"
                            )
                            Log.e("sign in use case", "starting sign inning")
                            _state.value = state.value.copy(
                                isLogged = signIn
                            )
//                            userData.forEach {
//                                Log.e("foreach", "foreach")
//                                if (it.userID.isEmpty() && it.userID == ""){
//                                    Log.e("empty", "empty")
//                                    signUpUseCase(
//                                        mail = UserData.email,
//                                        pass = "${UserData.password}00",
//                                        userData = UserData(
//                                            name = "name",
//                                            surname = "surname",
//                                            patronymic = "patronymic",
//                                            birthdayData = "birthdayData",
//                                            gender = "gender"
//                                        )
//                                    )
//                                    Log.e("sign up use case", "starting sign upping")
//                                    _state.value = state.value.copy(
//                                        isSignUpped = true
//                                    )
//                                }else{
//                                    Log.e("not empty", "not empty")
//                                    signInUseCase(
//                                        mail = UserData.email,
//                                        pass = UserData.password + "00"
//                                    )
//                                    Log.e("sign in use case", "starting sign inning")
//                                    _state.value = state.value.copy(
//                                        isLogged = true
//                                    )
//                                }
//                            }
                        }
//                        useCase(
//                            mail = state.value.email,
//                            pass = state.value.pass
//                        )

//                        client.auth.signInWith(Email) {
//                            this.email = state.value.email
//                            this.password = state.value.pass
//                        }
//                        _state.value = state.value.copy(
//                            isLogged = true
//                        )
                    } catch (e: Exception) {
                        try {
                            val signUp = signUpUseCase(
                                mail = state.value.email,
                                pass = "${UserData.password}00",
                                userData = UserData(
                                    name = "name",
                                    surname = "surname",
                                    patronymic = "patronymic",
                                    birthdayData = "birthdayData",
                                    gender = "gender",
                                    address = "address"
                                )
                            )
                            _state.value = state.value.copy(
                                isSignUpped = signUp
                            )
                        } catch (e: Exception) {
                            Log.e("supaUpError", e.message.toString())
                        }
                        Log.e("supaInError", e.message.toString())
                    }
                }
            }

            qEvent.SignUp -> {
                viewModelScope.launch {
                    try {
                        client.auth.signInWith(OTP){
                            email = mail
                            Log.e("token", this.captchaToken.toString())
                        }
                        _state.value = state.value.copy(
                            isLogged = true
                        )
                    } catch (e: Exception) {
                        Log.e("otp", e.message.toString())
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
    val isSignUpped: Boolean = false,
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
        val cor = rememberCoroutineScope()
        Column {
            val bucket = remember { mutableStateOf("") }
            OutlinedTextField(
                value = bucket.value,
                onValueChange = {bucket.value = it},
                label = { Text("bucket") }
            )
            var url = remember { mutableStateOf("") }
            Row {
                Button(
                    {
                        cor.launch {
                            try {
                                val id = client.auth.currentUserOrNull()?.id.toString()
                                Log.v("id", id)
                                client.storage.createBucket(id = id){
                                    this.public = true
                                }
                            } catch (e: Exception) {
                                Log.e("ex", e.message.toString())
                            }
                        }
                    }
                ) { Text("create bucket") }
                Button({
                    cor.launch {
                        try {
                            val id = client.auth.currentUserOrNull()?.id.toString()
                            val buc = client.storage.from(id)
                            url.value = buc.createSignedUrl(
                                path = "myIcon.png",
                                expiresIn = 5.minutes
                            )
                        } catch (e: Exception) {
                            Log.e("ex", e.message.toString())
                        }

                    }
                }) { Text("download img") }
                val context = LocalContext.current
                Button({
                    cor.launch {
                        try {
                            val image = R.drawable.man

                            val bitmap = BitmapFactory.decodeResource(context.resources, image)
                            val baos = ByteArrayOutputStream()
                            bitmap.compress(Bitmap.CompressFormat.PNG, 60, baos)
                            val id = client.auth.currentUserOrNull()?.id.toString()
                            val buc = client.storage.from(id).createSignedUploadUrl(
                                path = "myIcon.png",
                                upsert = true
                            )
                            client.storage.from(id).uploadToSignedUrl(
                                path = "myIcon.png",
                                token = buc.token,
                                data = baos.toByteArray()
                            ){
                                upsert = true
                            }
                        } catch (e: Exception) {
                            Log.e("ex", e.message.toString())
                        }

                    }
                }) { Text("upload img") }
            }
            AsyncImage(
                model = url.value,
                contentDescription = null,
                modifier = Modifier.size(250.dp)
            )
            Text("You are logged")
            Button(
                {
                    viewModel.signOut()
                }
            ) { Text("sign out") }
            val c = rememberCoroutineScope()
            val token = remember { mutableStateOf("") }
            OutlinedTextField(
                value = token.value,
                onValueChange = {token.value = it},
                label = { Text("filter") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
            )
            Button({
                c.launch {
                    Log.e("s", "start")
                    try {
                        val list = client.postgrest["ListOfProcedures"].select{
                            filter {
                                textSearch("name", "'анализ'", TextSearchType.NONE)
                            }
                        }.decodeList<ListOfProcedures>()
                        Log.e("s", "$list")
                        list.forEach {
                            Log.v("ilike", it.name)
                        }
                    } catch (e: Exception) {
                        Log.e("verifyEMailEx", e.message.toString())
                    }
                }
            }) { Text("verify your otp") }
        }
    }
}

@Serializable
data class ListOfProcedures(
    val id: Int = 0,
    val name: String = "",
    val time: String = "",
    val price: String = "",
)

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