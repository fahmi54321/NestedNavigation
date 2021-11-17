package com.android.nestednavigation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.android.nestednavigation.ui.theme.NestedNavigationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NestedNavigationTheme {
                Surface(
                    color = MaterialTheme.colors.background
                ) {
                    MyScreens()
                }
            }
        }
    }
}

@Composable
fun MyScreens() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "A"
    ) {
        composable(
            "A"
        ) {
            Screen(label = it.destination.route ?: "") {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Button(
                        onClick = { navController.navigate("B") },
                        modifier = Modifier
                            .padding(8.dp)
                    ) {
                        Text(text = "Go to Screen B")
                    }
                    Button(
                        onClick = { navController.navigate("C") },
                        modifier = Modifier.padding(8.dp)
                    ) {
                        Text(text = "Go to Screen C")
                    }
                }
            }
        }

        //nested navigation
        navigation(
            route = "B",
            startDestination = "BA"
        ) {
            composable(
                route = "BA"
            ) {
                Screen(label = it.destination.route ?: "") {
                    Column(
                        modifier = Modifier
                            .fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Button(
                            onClick = { navController.navigate("BB") },
                            modifier = Modifier
                                .padding(8.dp)
                        ) {
                            Text(text = "Go to BB")
                        }

                        Button(
                            onClick = { navController.navigate("BC") },
                            modifier = Modifier
                                .padding(8.dp)
                        ) {
                            Text(text = "Go to BC")
                        }

                        Button(
                            onClick = { navController.navigate("BD") },
                            modifier = Modifier
                                .padding(8.dp)
                        ) {
                            Text(text = "Go to BD")
                        }
                    }
                }
            }

            composable(
                route = "BB"
            ) {
                Screen(label = it.destination.route?:"")
            }

            composable(
                route = "BC"
            ) {
                Screen(label = it.destination.route?:"")
            }

            composable(
                route = "BD"
            ) {
                Screen(label = it.destination.route?:"")
            }
        }

        //nested navigation
        composable("C") {
            Screen(label = it.destination.route ?: "") {

            }
        }
    }
}

@Composable
fun Screen(label: String, content: @Composable () -> Unit = {}) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = label,
            fontSize = 30.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(16.dp)
        )

        //screen lain
        content()
    }
}

