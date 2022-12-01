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
import com.example.omegacalendar.data.AppDatabase
import com.example.omegacalendar.data.EventViewModelFactory
import com.example.omegacalendar.ui.DailyScreen

enum class OCalScreen(){
    Month,
    Day,
}

var DayTemp:Int=0

@Composable
fun OmegaAppBar(
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = { Text(stringResource(id = R.string.app_name)) },
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
//@Composable
//fun OmegaAppBottomBar(
//    canNavigateBack: Boolean,
//    navigateUp: () -> Unit,
//    modifier: Modifier = Modifier
//) {
//    BottomAppBar(
//
//    )
//}
@Composable
fun OmegaCalendarApp(modifier: Modifier = Modifier, viewModel: EventViewModel){
    // TODO: Create NavController
    val navController = rememberNavController()
    // TODO: Get current back stack entry

    // TODO: Get the name of the current screen

    Scaffold(
        topBar = {
            OmegaAppBar(
                canNavigateBack = false,
                navigateUp = { /* TODO: implement back navigation */ }
            )
        }
    ) { innerPadding ->
        val uiState by viewModel.uiState.collectAsState()

        // TODO: add NavHost
        NavHost(
            navController = navController,
            startDestination = OCalScreen.Month.name,
            modifier = modifier.padding(innerPadding)
        ) {
            composable(route = OCalScreen.Month.name) {
                MonthComponent(
                    onNextMonthButtonClicked = {
                        viewModel.nextMonthButton()
                    },
                    onPrevMonthButtonClicked = {
                        viewModel.prevMonthButton()
                    },
                    viewModel = viewModel,
                    m = uiState.mnNum,
                    y = uiState.yrNum,
                    navController = navController
                )
            }
            composable(route = OCalScreen.Day.name) {
                DailyScreen(
//                    day = OCalScreen.Day.name,
                    day = DayTemp,
                    month = uiState.mnNum,
                    year = uiState.yrNum,
                    modifier = Modifier,
                    viewModel = viewModel
                )
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