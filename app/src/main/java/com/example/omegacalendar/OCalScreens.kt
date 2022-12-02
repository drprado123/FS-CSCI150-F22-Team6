package com.example.omegacalendar

import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import com.example.omegacalendar.data.EventViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.omegacalendar.ui.MonthComponent
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.navArgument
import com.example.omegacalendar.data.AppDatabase
import com.example.omegacalendar.data.EventViewModelFactory
import com.example.omegacalendar.ui.ShowMonth

enum class OCalScreen(){
    Month,
    Day
}
@Composable
fun OmegaAppBar(
    currentScreen: String,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = { Text(stringResource(id = R.string.app_name)
        ) },
        modifier = modifier,
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.back_button)
                    )
                }
            }
        }
    )
}

@Composable
fun OmegaCalendarApp(modifier: Modifier = Modifier, viewModel: EventViewModel){
    // TODO: Create NavController
    val navController = rememberNavController()
    // TODO: Get current back stack entry
    val backStackEntry by navController.currentBackStackEntryAsState()

    // TODO: Get the name of the current screen
    val currentScreen = backStackEntry?.destination?.route ?: "month"

    Scaffold(
        topBar = {
            OmegaAppBar(
                currentScreen = currentScreen,
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = { navController.navigateUp() }
            )
        }
    ) { innerPadding ->
        val uiState by viewModel.uiState.collectAsState()

        // TODO: add NavHost
        NavHost(
            navController = navController,
            startDestination = "month",//OCalScreen.Month.name,
            modifier = modifier.padding(innerPadding)
        ) {
            composable(route = "month") {
                MonthComponent(
                    onNextMonthButtonClicked = {
                        viewModel.nextMonthButton()
                    },
                    onPrevMonthButtonClicked = {
                        viewModel.prevMonthButton()
                    },
                    onDayClicked = {monthNum, dayNum, yearNum ->
                        navController.navigate("day/$monthNum/$dayNum/$yearNum")//?monthNum={monthNum},dayNum={dayNum},yearNum={yearNum}")
                    },
                    viewModel = viewModel,
                    m = uiState.mnNum,
                    y = uiState.yrNum
                )
            }

            //day screen goes here
            composable(route = "day/{monthNum}/{dayNum}/{yearNum}",//?monthNum={monthNum},dayNum={dayNum},yearNum={yearNum}",
                arguments = listOf(
                    navArgument("monthNum") {type = NavType.IntType},
                    navArgument("dayNum") {type = NavType.IntType},
                    navArgument("yearNum") {type = NavType.IntType}
                )
            ) { backStackEntry ->

                val m = backStackEntry.arguments?.getInt("monthNum") ?: 0
                val d = backStackEntry.arguments?.getInt("dayNum") ?: 0
                val y = backStackEntry.arguments?.getInt("yearNum") ?: 0

                ShowMonth(month = m, day = d, year = y)
            }
            //composable(route = CupcakeScreen.Flavor.name) {
            //    val context = LocalContext.current
            //    SelectOptionScreen(
            //        subtotal = uiState.price,
            //        onNextButtonClicked = {
            //            navController.navigate(CupcakeScreen.Pickup.name)
            //        },
            //        onCancelButtonClicked = {cancelOrderAndNavigateToStart(viewModel, navController)},
            //        options = flavors.map{ id -> stringResource(id) },
            //        onSelectionChanged = {viewModel.setFlavor(it)}
            //    )
            //}
        }
    }
}