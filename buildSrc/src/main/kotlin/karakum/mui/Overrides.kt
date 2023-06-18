package karakum.mui

internal fun fixOverrides(
    name: String,
    content: String,
): String =
    when (name) {
        "Autocomplete",
        -> content
            .override("disabled")
            .override("readOnly")
            .replaceFirst("var key: String", "override var key: react.Key? /* Key */")

        "Box",
        -> content
            .override("component")

        "ButtonBase",
        -> content
            .override("disabled")
            .override("tabIndex")

        "Dialog",
        -> content
            .override("disableEscapeKeyDown")
            .override("onBackdropClick")
            .override("onClose")
            .override("open")
            .replaceFirst("override var children:", "    /* override */ var children:")

        "Drawer",
        -> content
            .override("onClose")
            .replaceFirst("override var children:", "    /* override */ var children:")

        "Popover",
        -> content
            .override("container")
            .override("onClose")
            .override("open")
            .replaceFirst("override var children:", "    /* override */ var children:")

        "Button",
        "Fab",
        "IconButton",
        -> content
            .override("disabled")

        "ToggleButton",
        -> content
            .override("disabled")
            .override("value")

        "LoadingButton",
        -> content
            .override("classes")

        "SwipeableDrawer",
        -> content
            .override("open")

        "MultiSelect",
        -> content
            .override("disabled")
            .replace("disabled: Boolean", "disabled: Boolean?")
            .replace("var component: dynamic", "var component: react.ElementType<*>?")

        "Option",
        -> content
            .replace("var component: dynamic", "var component: react.ElementType<*>?")

        "Select",
        -> content
            .replace("var component: dynamic", "var component: react.ElementType<*>?")
            .replaceLast("var disabled: Boolean?", "override var disabled: Boolean?")

        "TableCell",
        -> content
            .override("align")
            .override("scope")

        "SpeedDial",
        -> content
            .override("hidden")

        "Tab",
        -> content
            .override("slots")
            .replace("slots: TabSlots?", "slots: ButtonSlots? /* TabSlots? */")
            .override("slotProps")
            .replace(": SlotProps?", ": ButtonOwnProps.SlotProps?")

        "MenuItem",
        -> content
            .override("autoFocus")
            .replace(
                "onClick: react.dom.events.MouseEventHandler<web.html.HTMLElement>?",
                "onClick: react.dom.events.MouseEventHandler<web.html.HTMLLIElement>?"
            )

        "MenuList",
        -> content
            .override("autoFocus")

        "Modal",
        -> content
            .replace("children: react.ReactElement<*>", "children: dynamic /* react.ReactElement<*> */")

        "createTheme",
        -> {
            if ("mui.system.ThemeOptions" !in content) {
                content
            } else {
                content
                    .override("unstable_sxConfig", all = true)
                    .override("unstable_sx")
            }
        }

        else -> content
    }

private fun String.override(
    name: String,
    last: Boolean = false,
    all: Boolean = false,
): String {
    if (all) {
        return replace("var $name:", "override var $name:")
    }

    if (last) {
        return replaceLast("var $name:", "override var $name:")
    }

    return replaceFirst("var $name:", "override var $name:")
}

private fun String.replaceLast(oldValue: String, newValue: String): String =
    replaceFirst("(?s)(.*)$oldValue".toRegex(), "$1$newValue")
