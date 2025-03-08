package com.andrespelaezp.personaltaskplanningapp.ui.screens.dashboard.dashboardGrid

sealed class DashboardGridItem {

    data class TextItem(
        val title: String,
        val info: String
    ) : DashboardGridItem()

    data class ImageItem(
        val url: String
    ) : DashboardGridItem()

    data class GraphItem(
        val data: String
    ) : DashboardGridItem()

}