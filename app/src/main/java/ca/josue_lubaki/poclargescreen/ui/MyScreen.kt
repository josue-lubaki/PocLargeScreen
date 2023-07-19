package ca.josue_lubaki.poclargescreen.ui


import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.window.layout.DisplayFeature

/**
 * created by Josue Lubaki
 * date : 2023-07-19
 * version : 1.0.0
 */

@Composable
fun MyScreen(
    windowSizeClass: WindowWidthSizeClass,
    displayFeatures : List<DisplayFeature>
) {
}

@Preview(showBackground = true)
@Composable
private fun MyScreenPreview() {
    MyScreen(
        windowSizeClass = WindowWidthSizeClass.Compact,
        displayFeatures = listOf()
    )
}