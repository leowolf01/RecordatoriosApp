package com.example.recordatoriosapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MaterialTheme {
                PantallaRecordatorios(recordatorios = DatosSimulados.listaRecordatorios)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaRecordatorios(recordatorios: List<Recordatorio>) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Mis Recordatorios") }
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(recordatorios) { recordatorio ->
                ItemRecordatorioCard(recordatorio)
            }
        }
    }
}

@Composable
fun ItemRecordatorioCard(recordatorio: Recordatorio) {
    val colorCategoria = DatosSimulados.mapaColorCategoria[recordatorio.categoria] ?: Color.Gray

    ElevatedCard(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = recordatorio.titulo,
                    style = MaterialTheme.typography.titleMedium
                )
                Surface(
                    color = colorCategoria,
                    shape = MaterialTheme.shapes.small
                ) {
                    Text(
                        text = recordatorio.categoria,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                        style = MaterialTheme.typography.labelSmall,
                        color = Color.White
                    )
                }
            }

            recordatorio.descripcion?.let { desc ->
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = desc, style = MaterialTheme.typography.bodyMedium)
            }

            Spacer(modifier = Modifier.height(8.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                Text(
                    text = "Prioridad: ${recordatorio.prioridad.nivel}",
                    style = MaterialTheme.typography.bodySmall
                )
                Text(
                    text = "Estado: ${recordatorio.estado.etiqueta}",
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}

