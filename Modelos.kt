package com.example.recordatoriosapp

import androidx.compose.ui.graphics.Color

sealed class EstadoRecordatorio(val etiqueta: String) {
    object Pendiente : EstadoRecordatorio("Pendiente")
    object EnProceso : EstadoRecordatorio("En Proceso")
    object Completado : EstadoRecordatorio("Completado")
}

sealed class Prioridad(val nivel: String) {
    object Alta : Prioridad("Alta")
    object Media : Prioridad("Media")
    object Baja : Prioridad("Baja")
}

data class Recordatorio(
    val id: Int,
    val titulo: String,
    val descripcion: String? = null,
    val categoria: String,
    val prioridad: Prioridad,
    val estado: EstadoRecordatorio,
    val ubicacion: String? = null
)

object DatosSimulados {

    val categoriasUnicas: Set<String> = setOf("Trabajo", "Estudio", "Personal", "Salud")

    val mapaColorCategoria: Map<String, Color> = mapOf(
        "Trabajo" to Color(0xFFE57373),
        "Estudio" to Color(0xFF64B5F6),
        "Personal" to Color(0xFF81C784),
        "Salud" to Color(0xFFFFB74D)
    )

    val listaRecordatorios: List<Recordatorio> = listOf(
        Recordatorio(
            id = 1,
            titulo = "Entregar API 1 Mobile",
            descripcion = "Subir informe PDF con evidencias y repo",
            categoria = "Estudio",
            prioridad = Prioridad.Alta,
            estado = EstadoRecordatorio.Pendiente
        ),
        Recordatorio(
            id = 2,
            titulo = "Reunión de equipo",
            descripcion = "Sincronización semanal",
            categoria = "Trabajo",
            prioridad = Prioridad.Media,
            estado = EstadoRecordatorio.EnProceso
        ),
        Recordatorio(
            id = 3,
            titulo = "Comprar insumos",
            descripcion = null,
            categoria = "Personal",
            prioridad = Prioridad.Baja,
            estado = EstadoRecordatorio.Completado
        )
    )
}

