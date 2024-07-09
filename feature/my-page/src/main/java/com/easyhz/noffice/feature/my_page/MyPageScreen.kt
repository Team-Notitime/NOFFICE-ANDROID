package com.easyhz.noffice.feature.my_page

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.easyhz.noffice.core.design_system.component.scaffold.NofficeScaffold

@Composable
fun MyPageScreen() {
    NofficeScaffold(
        topBar = {
            Box(modifier = Modifier.fillMaxWidth()) {
                Text(text = "MyPage")
            }
        }
    ) {
        LazyColumn(
            modifier = Modifier.padding(it)
        ) {
            items(50) {
                Text(text = "$it .. MyPageScreen", modifier = Modifier
                    .fillMaxWidth()
                    .height(32.dp))
            }
        }
    }
}