package com.jlp.core.datasource.remote.model.productlist

data class RemoteQuickAddToBasket(
    val showChooseSizeTriggerQV: Boolean,
    val showEmailMeTriggerQV: Boolean,
    val showMoreInfoRedirectPDP: Boolean,
    val showPermanentOos: Boolean,
    val simpleAddToBasket: Boolean,
    val simpleMobileEmailMe: Boolean
)