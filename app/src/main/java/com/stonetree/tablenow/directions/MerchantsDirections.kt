package com.stonetree.tablenow.directions

import android.os.Bundle
import androidx.navigation.NavDirections
import com.stonetree.tablenow.R
import com.stonetree.tablenow.constants.Constants.Bundle.MERCHANT
import com.stonetree.tablenow.models.Merchant

class MerchantsDirections private constructor() {

    private data class ActionMerchantsToDetails(val merchant: Merchant) : NavDirections {
        override fun getActionId(): Int = R.id.view_details

        override fun getArguments(): Bundle {
            return Bundle().apply {
                putSerializable(MERCHANT, merchant)
            }
        }
    }

    companion object {
        fun actionMerchantsToDetails(merchant: Merchant): NavDirections =
            ActionMerchantsToDetails(merchant)
    }
}
