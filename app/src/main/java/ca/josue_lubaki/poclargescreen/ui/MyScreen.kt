package ca.josue_lubaki.poclargescreen.ui


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.window.layout.DisplayFeature
import androidx.window.layout.FoldingFeature
import ca.josue_lubaki.poclargescreen.utils.AllPreview
import ca.josue_lubaki.poclargescreen.utils.isBookPosture
import ca.josue_lubaki.poclargescreen.utils.isSeparatingPosture
import ca.josue_lubaki.poclargescreen.utils.isTableTopPosture
import com.google.accompanist.adaptive.HorizontalTwoPaneStrategy
import com.google.accompanist.adaptive.TwoPane
import com.google.accompanist.adaptive.VerticalTwoPaneStrategy

/**
 * created by Josue Lubaki
 * date : 2023-07-19
 * version : 1.0.0
 */

@Composable
fun MyScreen(
    windowSizeClass: WindowSizeClass,
    displayFeatures : List<DisplayFeature>,
    modifier: Modifier = Modifier
) {
    Surface(modifier) {
        MyScreenContent(windowSizeClass, displayFeatures, modifier)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyScreenContent(
    windowSizeClass: WindowSizeClass,
    displayFeatures: List<DisplayFeature>,
    modifier: Modifier
) {
    val foldingFeature = displayFeatures.filterIsInstance<FoldingFeature>().firstOrNull()

    // Use a two pane layout if there is a fold impacting layout (meaning it is separating
    // or non-flat) or if we have a large enough width to show both.
    if (windowSizeClass.widthSizeClass == WindowWidthSizeClass.Expanded ||
        isBookPosture(foldingFeature) ||
        isTableTopPosture(foldingFeature) ||
        isSeparatingPosture(foldingFeature)
        )
    {
        // TwoPaneLayout()
        // Determine if we are going to be using a vertical strategy (as if laying out
        // both sides in a column). We want to do so if we are in a tabletop posture,
        // or we have an impactful horizontal fold. Otherwise, we'll use a horizontal strategy.
        val usingVerticalStrategy =
            isTableTopPosture(foldingFeature) ||
                (
                    isSeparatingPosture(foldingFeature) &&
                        foldingFeature.orientation == FoldingFeature.Orientation.HORIZONTAL
                    )

        if (usingVerticalStrategy) {
            // TwoPaneLayout - vertical
            TwoPane(
                first = {
                    // Content top pane
                    PaneContentTableTop()
                },
                second = {
                    // Content bottom pane
                    PaneContentTableBottom()
                },
                strategy = VerticalTwoPaneStrategy(splitFraction = 0.5f),
                displayFeatures = displayFeatures,
                modifier = modifier,
            )
        } else {
            // TwoPaneLayout - horizontal
            Column(modifier = modifier.fillMaxSize()) {
                MyTopAppBar()
                TwoPane(
                    first = {
                        // Content left pane
                        PaneContentBookStart()
                    },
                    second = {
                        // Content right pane
                        PaneContentBookEnd()
                    },
                    strategy = HorizontalTwoPaneStrategy(splitFraction = 0.5f),
                    displayFeatures = displayFeatures
                )
            }
        }
    } else {
        // OnePaneLayout() - regular layout
        PaneContentRegular()
    }
}

@Composable
fun PaneContentTableTop(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .windowInsetsPadding(
                WindowInsets.systemBars.only(
                    WindowInsetsSides.Horizontal + WindowInsetsSides.Top
                )
            )
            .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.5f))
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier.weight(1f),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = "PanelContentTableTop",
                style = MaterialTheme.typography.headlineSmall,
            )
        }
    }
}

@Composable
fun PaneContentTableBottom(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .windowInsetsPadding(
                WindowInsets.systemBars.only(
                    WindowInsetsSides.Horizontal + WindowInsetsSides.Bottom
                )
            )
            .background(MaterialTheme.colorScheme.inversePrimary.copy(alpha = 0.5f))
            .padding(vertical = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        MyTopAppBar()
        Box(
            modifier = Modifier.weight(1f),
            contentAlignment = Alignment.Center,
        ){
            Text(
                text = "PanelContentTableBottom",
                style = MaterialTheme.typography.headlineSmall,
            )
        }

    }
}

@Composable
fun PaneContentBookStart(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.secondary.copy(alpha = 0.5f))
            .padding(
                vertical = 8.dp,
                horizontal = 16.dp
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "PanelContentBookStart",
            style = MaterialTheme.typography.headlineSmall,
        )
    }
}

@Composable
fun PaneContentBookEnd(
    modifier: Modifier = Modifier
) {
    Column(
        modifier =  modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.onSecondary.copy(alpha = 0.5f))
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "PanelContentBookEnd",
            style = MaterialTheme.typography.headlineSmall,
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTopAppBar() {
    TopAppBar(
        title = {
            Text(
                text = "Accueil",
                style = MaterialTheme.typography.labelLarge,
            )
        },
        navigationIcon = {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    imageVector = Icons.Filled.Home,
                    contentDescription = "Menu",
                    modifier = Modifier.size(24.dp)
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.5f),
            titleContentColor = MaterialTheme.colorScheme.onSurface,
        ),
    )
}

@Composable
fun PaneContentRegular(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.tertiary.copy(alpha = 0.5f)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        MyTopAppBar()
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            contentAlignment = Alignment.Center,
        ){
            Text(
                text = "PanelContentRegular",
                style = MaterialTheme.typography.headlineSmall,
            )
        }
    }
}

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@AllPreview
@Composable
private fun MyScreenPreview() {
    BoxWithConstraints {
        MyScreen(
            windowSizeClass = WindowSizeClass.calculateFromSize(DpSize(maxWidth, maxHeight)),
            displayFeatures = listOf()
        )
    }
}