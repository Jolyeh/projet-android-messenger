package com.example.messenger

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.messenger.ui.theme.MessengerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MessengerTheme {
                AppNavigation()
            }
        }
    }
}

//pages
@Composable
fun LoginPage(navController: NavController, modifier: Modifier = Modifier) {
    val email = remember {
        mutableStateOf("")
    }
    val password = remember {
        mutableStateOf("")
    }
    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceAround,
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF142026))
            .padding(20.dp)

    ){
        Spacer(Modifier.height(0.dp))
        Text(
            text = "Messenger",
            color = Color.White,
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold
        )
        Column (
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ){
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(55.dp),
                value = email.value,
                onValueChange = {newEmail ->
                    email.value = newEmail
                },
                placeholder = {
                    Text(
                        "Numéro du mobile ou email",
                        color = Color.Gray,
                    )
                },
                shape = RoundedCornerShape(15.dp)
            )
            Spacer(modifier = Modifier.padding(top = 20.dp))
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(55.dp),
                visualTransformation = PasswordVisualTransformation(),
                value = password.value,
                onValueChange = {newPassword ->
                    password.value = newPassword
                },
                placeholder = {
                    Text(
                        "Mot de passe",
                        color = Color.Gray,
                    )
                },
                shape = RoundedCornerShape(15.dp)
            )
            Spacer(modifier = Modifier.padding(top = 20.dp))
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(45.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF0062DE)
                ),
                onClick = {
                    navController.navigate("chat")
                }
            ) {
                Text(
                    "Se connecter",
                    color = Color.White
                )
            }
            Spacer(modifier = Modifier.padding(top = 20.dp))
            Text(
                text = "Mot de passe oublié ?",
                color = Color.Gray,
            )
        }

        OutlinedButton(
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp),
            onClick = {}
        ) {
            Text(
                "Créer un compte",
                color = Color.White,
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatPage(navController: NavController, modifier: Modifier = Modifier) {
    Scaffold (
        containerColor = Color.Black,
        topBar = {
            TopAppBar(
                navigationIcon = {
                    Icon(
                        modifier = Modifier
                            .background(
                                color = Color(0xFF2D2D2D),
                                shape = RoundedCornerShape(50.dp)
                            )
                            .padding(8.dp),
                        imageVector = Icons.Default.Menu,
                        tint = Color.White,
                        contentDescription = "Menu"
                    )
                },
                title = {
                    Text(
                        modifier = Modifier.padding(start = 6.dp),
                        text = "Discussions",
                        color = Color.White,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                },
                actions = {
                    Icon(
                        modifier = Modifier
                            .background(
                                color = Color(0xFF2D2D2D),
                                shape = RoundedCornerShape(50.dp)
                            )
                            .padding(8.dp),
                        imageVector = Icons.Default.Create,
                        tint = Color.White,
                        contentDescription = "Edit"
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Black
                )
            )
        },
        content = {
            Column (
                modifier = Modifier.padding(it)
            ){
                Spacer(Modifier.height(15.dp))
                LazyRow(
                ){
                    items(20){
                        StoryItem(name = "Story $it")
                    }

                }
                Spacer(Modifier.height(15.dp))
                LazyColumn (
                ){
                    items(20){
                        ChatItem(name = "Android $it", message = "Le natif est performant", date = "10 mars")
                    }

                }
            }
        },
        bottomBar = {
            BottomAppBar(
                containerColor = Color.Black,
                contentColor = Color.White
            ) {
                Row (
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround
                ){
                    Column (
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ){
                        Icon(imageVector = Icons.Default.Home, contentDescription = "Discussions", tint = Color(0xFF4098FC))
                        Text("Discussions",
                            color = Color(0xFF4098FC)
                        )
                    }
                    Column (
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ){
                        Icon(imageVector = Icons.Default.AccountCircle, contentDescription = "Profil")
                        Text("Profil")
                    }
                }

            }
        }
    )
}

// navigation

@Composable
fun AppNavigation() {
    val navController = rememberNavController() // Initialisation du contrôleur de navigation

    NavHost(navController = navController, startDestination = "login") {
        composable("login") {
            LoginPage(navController)  // Passer le NavController
        }
        composable("chat") {
            ChatPage(navController)  // Passer le NavController
        }
    }
}



//composants
@Composable
fun ChatItem(modifier: Modifier = Modifier, name: String?, message: String?, date: String?) {
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        verticalAlignment = Alignment.CenterVertically
    ){
        Image(
            modifier = Modifier
                .size(60.dp)
                .clip(CircleShape),
            painter = painterResource(id = R.drawable.img),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.width(10.dp))
        Column {
            Text(
                "$name",
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
            Text(
                "$message . $date",
                color = Color.Gray
            )
        }
    }
}

@Composable
fun StoryItem(modifier: Modifier = Modifier, name: String?) {
    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(start = 10.dp, end = 5.dp)
    ){
        Image(
            modifier = Modifier
                .size(70.dp)
                .clip(CircleShape),
            painter = painterResource(id = R.drawable.img2),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
        Text(
            "$name",
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MessengerTheme {
        AppNavigation()
    }
}