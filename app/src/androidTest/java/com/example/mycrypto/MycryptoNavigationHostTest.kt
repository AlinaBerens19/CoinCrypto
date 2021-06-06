package com.example.mycrypto




import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.performClick
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import junit.framework.TestCase.fail
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MycryptoNavigationHostTest {

    @get:Rule
    val composeTestRule = createComposeRule()
    lateinit var navController: NavHostController

    @ExperimentalCoroutinesApi
    @ExperimentalMaterialApi
    @Before
    fun setupCryptoNavHost() {
        composeTestRule.setContent {
            navController = rememberNavController()
            //CryptoNavHost(navController = navController)
        }
        //fail()
    }

    @Test
    fun cryptoNavHost() {
        composeTestRule
            .onNodeWithContentDescription("MarketScreen")
            .assertIsDisplayed()
    }

    @Test
    fun rallyNavHost_navigateToAllAccounts_viaUI() {
        composeTestRule
            .onNodeWithContentDescription("MarketScreen")
            .performClick()
        composeTestRule
            .onNodeWithContentDescription("DetailCoin")
            .assertIsDisplayed()
    }

}