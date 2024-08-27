package com.easyhz.noffice.feature.sign.component.signUp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.easyhz.noffice.core.design_system.R
import com.easyhz.noffice.core.design_system.component.bottomSheet.BottomSheet
import com.easyhz.noffice.core.design_system.component.topBar.DetailTopBar
import com.easyhz.noffice.core.design_system.theme.Grey400
import com.easyhz.noffice.core.design_system.theme.Grey50
import com.easyhz.noffice.core.design_system.theme.White
import com.easyhz.noffice.core.design_system.util.terms.TermsType
import com.easyhz.noffice.core.design_system.util.topBar.DetailTopBarMenu

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun TermsDetailBottomSheet(
    sheetState: SheetState,
    termsType: TermsType,
    onDismissRequest: () -> Unit,
) {
    val scrollState = rememberScrollState()
    BottomSheet(
        sheetState = sheetState,
        shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp),
        containerColor = White,
        onDismissRequest = onDismissRequest,
        dragHandle = {
            DetailTopBar(
                modifier = Modifier.background(Grey50),
                title = stringResource(id =termsType.title),
                trailingItem = DetailTopBarMenu(
                    content = {
                        Icon(
                            modifier = Modifier.size(24.dp),
                            painter = painterResource(id = R.drawable.ic_x),
                            contentDescription = "left",
                            tint = Grey400
                        )
                    },
                    onClick = onDismissRequest
                ),
            )
        }
    ) {
        Box(modifier = Modifier.fillMaxHeight(0.9f)) {
            Column(
                modifier = Modifier.verticalScroll(scrollState)
            ) {
                com.easyhz.noffice.core.design_system.component.terms.TermsView(
                    modifier = Modifier,
                    fileName = termsType.fileName
                )
            }
        }
    }
}