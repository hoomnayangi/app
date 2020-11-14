package com.example.hackathon_midx.base_adapter

interface IBaseItemAction

enum class BaseItemAction :
    IBaseItemAction {
    ON_ITEM_ROW_CLICK
}

enum class AddressItemAction :
    IBaseItemAction {
    ON_ITEM_ADDRESS_QR, ON_ITEM_ADDRESS_SHARE, ON_ITEM_DESTINATION_TAG_COPY_CLICK
}

enum class WidgetItemAction :
    IBaseItemAction {
    ON_ITEM_CHECKED, ON_ITEM_UNCHECKED
}