package com.bluewhaleyt.codewhale.ui.screen.main.pages

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.PagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.outlined.FileDownload
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.bluewhaleyt.codewhale.R
import com.bluewhaleyt.codewhale.ResetAppButton
import com.bluewhaleyt.codewhale.common.ui.component.AnimateIconButton
import com.bluewhaleyt.codewhale.common.ui.component.Info
import com.bluewhaleyt.codewhale.ui.screen.main.MainViewModel
import dev.jeziellago.compose.markdowntext.MarkdownText

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PrivacyPolicyPage(
    viewModel: MainViewModel = viewModel(),
    pagerState: PagerState
) {
    val context = LocalContext.current
    val fileContent = viewModel.getPrivacyPolicyFile(context = context)
    val scope = rememberCoroutineScope()

    PageWrapper(
        title = { Text(text = "Privacy Policy") },
        columnModifier = Modifier.padding(16.dp),
        actions = { scrollState ->
            ResetAppButton()
            AnimateIconButton(
                visible = scrollState.value == scrollState.maxValue,
                imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                contentDescription = "Continue",
                onClick = {
                    viewModel.onContinueButtonClick(
                        scope = scope,
                        pagerState = pagerState
                    )
                }
            )
        }
    ) {
        Info(
            modifier = Modifier.padding(top = 5.dp, bottom = 40.dp),
            icon = { Icon(imageVector = Icons.Outlined.FileDownload, contentDescription = null) },
            text = { Text(text = "Read the content carefully, and scroll to the end to continue") }
        )
        MarkdownText(
            markdown = fileContent,
            color = LocalContentColor.current,
            style = LocalTextStyle.current,
            fontResource = R.font.quicksand_medium
        )
    }

}