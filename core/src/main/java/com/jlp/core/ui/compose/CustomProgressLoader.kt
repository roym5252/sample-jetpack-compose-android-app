package com.jlp.core.ui.compose

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.jlp.core.R

@Composable
fun CustomProgressLoader(modifier: Modifier) {

    Box(modifier =Modifier.fillMaxSize(),contentAlignment= Alignment.Center){
        val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.loading_animation))
        val progress by animateLottieCompositionAsState(composition,isPlaying = true,restartOnPlay=true, iterations = LottieConstants.IterateForever)

        LottieAnimation(
            composition = composition,
            progress = { progress },
            modifier = modifier.size(200.dp)
        )
    }

}