package com.farzin.rickmortymultimodular.ui.screens.common_components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.farzin.rickmortymultimodular.R
import com.farzin.rickmortymultimodular.ui.theme.RickTextPrimary

@Composable
fun SimpleToolbar(
    title: String,
    backAction: (() -> Unit)? = null,
) {


    Column {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 12.dp   )
        ) {

            if (backAction != null) {
                Box(
                    modifier = Modifier
                        .background(shape = RoundedCornerShape(12.dp), color = Color.Transparent)
                        .clip(RoundedCornerShape(12.dp))
                        .clickable { backAction() }

                ){
                    Icon(
                        painter = painterResource(R.drawable.baseline_arrow_back_24),
                        contentDescription = null,
                        tint = RickTextPrimary,
                        modifier = Modifier
                            .align(Alignment.Center).padding(4.dp)
                    )
                }
                Spacer(Modifier.width(12.dp))
            }
            Text(
                text = title,
                fontSize = 30.sp,
                style = TextStyle(
                    color = RickTextPrimary,
                    fontWeight = FontWeight.Bold
                )
            )
        }
        Box(
            modifier = Modifier
                .background(RickTextPrimary)
                .fillMaxWidth()
                .height(1.dp)
        )
    }


}