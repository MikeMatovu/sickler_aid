package com.micodes.sickleraid.presentation.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.micodes.sickleraid.R
import com.micodes.sickleraid.data.remote.AuthState
import com.micodes.sickleraid.data.remote.DataProvider
import com.micodes.sickleraid.presentation.auth.AuthViewModel
import com.micodes.sickleraid.presentation.common.composable.TopAppBarComposable
import com.micodes.sickleraid.presentation.home.components.ContentGrid
import com.micodes.sickleraid.presentation.home.components.ContentRow
import com.micodes.sickleraid.presentation.home.components.SectionTitle
import com.micodes.sickleraid.presentation.navgraph.Screen
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavController,
    drawerState: DrawerState,
    authViewModel: AuthViewModel = hiltViewModel()
//    state: HomeState  TODO: Add Home state
) {

    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    val openLoginDialog = remember { mutableStateOf(false) }
    val authState = DataProvider.authState
    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },
        topBar = {
            TopAppBarComposable(
                title = {
                    Image(
                        painter = painterResource(id = R.drawable.ic_app), //TODO: add image
                        contentDescription = "Logo",
                        modifier = Modifier.fillMaxWidth(0.4f)
                    )
                },
                actions = listOf(
                    {
                        IconButton(onClick = { /* TODO */ }) {
                            Icon(
                                imageVector = Icons.Outlined.Notifications,
                                contentDescription = null
                            )
                        }
                    },
                    {
                        IconButton(onClick = { /* TODO */ }) {
                            Icon(
                                imageVector = Icons.Rounded.Search,
                                contentDescription = null
                            )
                        }
                    },
                    {
                        IconButton(onClick = {
                            scope.launch {
                                drawerState.apply {
                                    if (isClosed) open() else close()
                                }
                            }
                        }) {
                            Image(
                                painter = painterResource(id = R.drawable.ic_profile),
                                contentDescription = null,
                                modifier = Modifier.size(24.dp),
                            )
                        }
                    }
                )
            )
        },
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            item {
                SectionTitle("Medical Information")
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    ContentRow(
                        title = "Medical records",
                        onButtonClick = {
                            navController.navigate(Screen.MedicalRecords.route)
                        },
                        modifier = Modifier
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    ContentRow(
                        title = "Daily Checkup",
                        onButtonClick = {
                            navController.navigate(Screen.DailyCheckup.route)
                        },
                        modifier = Modifier
                    )
                }
            }
            item {

                SectionTitle("Medication List")

                val dummyResources2 = listOf(
                    SupportResource(title = "Resource 1", image = painterResource(id = R.drawable.cells)),
                    SupportResource(title = "Resource 1", image = painterResource(id = R.drawable.cells)),
                    SupportResource(title = "Resource 1", image = painterResource(id = R.drawable.cells)),
                )
                ContentGrid(resources = dummyResources2, height = 150)
            }

            item {

                //TODO GET RESOURCES FROM https://www.sicklecelldisease.org/support-and-community/links-resources/
                SectionTitle("My support")

                val dummyResources = listOf(
                    SupportResource(title = "Resource 1", image = painterResource(id = R.drawable.cells)),
                    SupportResource(title = "Resource 1", image = painterResource(id = R.drawable.cells)),
                    SupportResource(title = "Resource 1", image = painterResource(id = R.drawable.cells)),
                    SupportResource(title = "Resource 1", image = painterResource(id = R.drawable.cells)),
                    SupportResource(title = "Resource 1", image = painterResource(id = R.drawable.cells)),
                    SupportResource(title = "Resource 1", image = painterResource(id = R.drawable.cells)),
                )
                ContentGrid(resources = dummyResources, height = 300)
            }


            item {
                //End dummy content
                Button(
//                    onClick = {
//                        if (authState != AuthState.SignedIn)
//                            openLoginDialog.value = true
//                        else
//                            authViewModel.signOut()
//                    },
                    onClick = {
                        scope.launch {
                            snackbarHostState.showSnackbar("Snackbar")
                        }
                    },

                    modifier = Modifier
                        .size(width = 200.dp, height = 50.dp)
                        .padding(horizontal = 16.dp),
                    shape = RoundedCornerShape(10.dp),

                ) {
                    Text(
                        text = if (authState != AuthState.SignedIn) "Sign-in" else "Sign out",
                        modifier = Modifier.padding(6.dp),
                        color = MaterialTheme.colorScheme.primary
                    )
                }

                //End dummy content
            }
            //MOre dummy content
//            AnimatedVisibility(visible = openLoginDialog.value) {
//                Dialog(
//                    onDismissRequest = { openLoginDialog.value = false },
//                    properties = DialogProperties(
//                        usePlatformDefaultWidth = false
//                    )
//                ) {
//                    Surface(modifier = Modifier.fillMaxSize()) {
//                        LoginScreen(
//                            navController = navController,
//                        )
//                    }
//                }
//            }

        }


    }

}

@Composable
@Preview
fun HomeScreenPreview() {
//    HomeScreen(navController = rememberNavController())

}

