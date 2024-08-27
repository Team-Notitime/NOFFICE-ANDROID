package com.easyhz.noffice.feature.my_page.screen.detail.terms

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.easyhz.noffice.core.design_system.R
import com.easyhz.noffice.core.design_system.component.scaffold.NofficeBasicScaffold
import com.easyhz.noffice.core.design_system.component.terms.TermsView
import com.easyhz.noffice.core.design_system.component.topBar.DetailTopBar
import com.easyhz.noffice.core.design_system.theme.Grey400
import com.easyhz.noffice.core.design_system.theme.Grey50
import com.easyhz.noffice.core.design_system.util.terms.TermsType
import com.easyhz.noffice.core.design_system.util.topBar.DetailTopBarMenu

@Composable
fun TermsScreen(
    modifier: Modifier = Modifier,
    termsType: TermsType,
    navigateToUp: () -> Unit
) {
    NofficeBasicScaffold(
        containerColor = Grey50,
        statusBarColor = Grey50,
        navigationBarColor = Grey50,
        topBar = {
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
                    onClick = { navigateToUp() }
                ),
            )
        }
    ) { paddingValues ->
        TermsView(
            modifier = modifier.padding(paddingValues),
            fileName = termsType.fileName
        )
    }
}