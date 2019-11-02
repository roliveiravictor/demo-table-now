package com.stonetree.tablenow.constants


object Constants {

    object Endpoints {

        const val MERCHANTS = "/v1/merchants"
    }

    object Params {

        object Merchants {

            const val PAGE_LIMIT: Int = 30
            const val LIMIT: String = "limit"
            const val OFF_SET: String = "offset"
        }
    }

    object Actions {
        const val NAVIGATOR = "com.stonetree.tablenow.views.NavigatorActivity"
    }
}