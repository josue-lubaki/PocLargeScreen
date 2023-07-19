package ca.josue_lubaki.poclargescreen.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

/**
 * created by Josue Lubaki
 * date : 2023-07-19
 * version : 1.0.0
 */

@Preview(
    name = "1 - Phone Portrait",
    device = "spec:width=412dp,height=892dp,orientation=portrait"
)
@Preview(
    name = "2 - Phone Lanscape",
    device = "spec:width=412dp,height=892dp,orientation=landscape"
)
@Preview(
    name = "3 - Foldable Portrait",
    device = "spec:width=839dp,height=945dp,orientation=portrait"
)
@Preview(
    name = "4 - Foldable Landscape",
    device = "spec:width=839dp,height=945dp,orientation=landscape"
)
@Preview(
    name = "5 - Tablette Portrait",
    device = "spec:width=1280dp,height=900dp,orientation=portrait"
)
@Preview(
    name = "6 - Tablette Lanscape",
    device = "spec:width=1280dp,height=900dp,orientation=landscape"
)
@Preview(
    name = "7 - Desktop Landscape",
    device = "spec:width=1920dp,height=1080dp,orientation=landscape"
)
annotation class AllPreview