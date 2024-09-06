package com.devkishan.nseapi.screens

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.devkishan.nseapi.model.AllIndices
import com.devkishan.nseapi.viewmodel.NseViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NseScreen(modifier: Modifier, viewModel: NseViewModel = hiltViewModel()) {
    val uiState by viewModel.uiState.collectAsState()
    Scaffold(topBar = {
        TopAppBar(title = { Text("Nse Api Page") })
    }) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            when (uiState) {
                is NseViewModel.UiState.Loading -> {
                    CircularProgressIndicator()
                }

                is NseViewModel.UiState.Success -> {
                    SuccessView(
                        modifier = Modifier.fillMaxSize(),
                        indices = (uiState as NseViewModel.UiState.Success).indices,
                        onDeleteClick = { viewModel.deleteIndex(it) }
                    )
                }

                is NseViewModel.UiState.Error -> {
                    ErrorView(
                        modifier = Modifier.fillMaxSize(),
                        errorMsg = (uiState as NseViewModel.UiState.Error).message,
                        onRetryClick = { viewModel.getAllIndices() }
                    )
                }

                is NseViewModel.UiState.Empty -> {

                }
            }
        }
    }
}


@Composable
fun ErrorView(
    modifier: Modifier = Modifier,
    errorMsg: String,
    onRetryClick: () -> Unit
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = errorMsg,
            color = MaterialTheme.colorScheme.error,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(8.dp))

        Button(onClick = onRetryClick) {
            Text(text = "Retry")
        }
    }
}

@Composable
fun IndicesList(
    modifier: Modifier = Modifier,
    indices: List<AllIndices>,
    onDeleteClick: (indexSymbol: String) -> Unit
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(indices) { index ->
            Row(
                modifier = Modifier
                    .padding(8.dp)
                    .border(1.dp, Color.Red, RoundedCornerShape(8.dp))
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = Modifier.weight(1f),
                    text = index.indexSymbol
                )
                IconButton(onClick = { onDeleteClick(index.indexSymbol) }) {
                    Icon(imageVector = Icons.Default.Delete, contentDescription = null)
                }
            }
        }
    }
}



@Composable
fun SuccessView(
    modifier: Modifier = Modifier,
    indices: List<AllIndices>,
    onDeleteClick: (key: String) -> Unit,

    ) {
    Column(
        modifier = modifier
    ) {
        IndicesList(
            modifier = Modifier.weight(1f),
            indices = indices,
            onDeleteClick = onDeleteClick
        )

        HorizontalDivider()

    }
}

@Preview(showSystemUi = true)
@Composable
fun NseScreenPreview() {
    NseScreen(modifier = Modifier)
}
