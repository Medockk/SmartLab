package com.example.smartlab

import android.os.Bundle
import android.util.Log
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
        enableEdgeToEdge()
        setContent {
            val viewModel: MainActivityViewModel = koinViewModel()
            val navController = rememberNavController()
            LaunchedEffect(key1 = true) {
                viewModel.checkRoute()
            }
            SmartLabTheme{
                NavHost(navController, startDestination = "q"){
                    composable("q"){
                        q()
                    }
                    composable(Route.SplashScreen.route){
                        SplashScreen(navController)
                    }
                    composable(Route.OnBoardScreen.route){
                        OnBoardScreen(navController)
                    }
                    composable(Route.SignInScreen.route){
                        SignInScreen(navController)
                    }
                    composable(Route.EmailCodeScreen.route){
                        EmailCodeScreen(navController)
                    }
                    composable(Route.CreatePasswordScreen.route){
                        CreatePasswordScreen(navController)
                    }
                    composable(Route.CreateCardScreen.route){
                        CreateCardScreen(navController)
                    }
                    composable(Route.AnalyzesScreen.route){
                        AnalyzesScreen(navController)
                    }
                    composable(Route.AnalyzesCategoryScreen.route){
                        AnalyzesCategoryScreen(navController)
                    }
                    composable(Route.PatientCardScreen.route){
                        PatientCardScreen(navController)
                    }
                    composable(Route.CartScreen.route){
                        CartScreen(navController)
                    }
                    composable(Route.MakingOrderScreen.route){
                        MakingOrderScreen(navController)
                    }
                    composable(Route.PaymentScreen.route){
                        PaymentScreen()
                    }
                }
            }
        }
    }
}

@Composable
fun q() {
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
        val coroutineScope = rememberCoroutineScope()
        val uid = remember { mutableStateOf("") }
        var email by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }
        Column (
            modifier = Modifier.fillMaxWidth()
                .padding(horizontal = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            OutlinedTextField(
                email, {email = it},
                modifier = Modifier.fillMaxWidth()
                    .padding(horizontal = 20.dp),
                label = {
                    Text("email")
                }
            )
            OutlinedTextField(
                password, {password = it},
                modifier = Modifier.fillMaxWidth()
                    .padding(horizontal = 20.dp),
                label = {
                    Text("password")
                }
            )

            Button({
                coroutineScope.launch {
                    try {
                        if (client.auth.currentUserOrNull() != null){
                            client.auth.signInWith(Email){
                                this.email = email
                                this.password = password
                            }
                            Log.v("supaIn", "sign in")
                        }else{
                            client.auth.signUpWith(Email){
                                this.email = email
                                this.password = password
                            }
                            Log.v("supaUp", "sign up")
                        }
                    } catch (e: Exception) {
                        Log.e("supaError", e.message.toString())
                    }
                }
                uid.value = client.auth.currentUserOrNull()?.identities
                    .toString()
            }) {
                Text("sign in/sign up", fontSize = 32.sp)
            }
            Spacer(Modifier.padding(vertical = 10.dp))
            Text(uid.value)
        }
    }
}