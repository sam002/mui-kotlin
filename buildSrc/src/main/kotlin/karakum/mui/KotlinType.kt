package karakum.mui

private const val PROMISE = "Promise"

internal const val DYNAMIC = "dynamic"
internal const val UNION = "mui.system.Union"

private const val ELEMENT_TYPE = "react.ElementType"

private val CREATE_TRANSITION = """
(
  props: string | string[],
  options?: Partial<{ duration: number | string; easing: string; delay: number | string }>,
) => string
""".removePrefix("\n").removeSuffix("\n")

private val USE_TAB_PANEL_RETURN_VALUE_GET_ROOT_PROPS = """
() => {
    'aria-labelledby': string | undefined;
    hidden: boolean;
    id: string | undefined;
}""".removePrefix("\n").removeSuffix("\n")

private val SWIPEABLE_DRAWER_PROPS_ALLOW_SWIPE_IN_CHILDREN = """

  | boolean
  | ((e: TouchEvent, swipeArea: HTMLDivElement, paper: HTMLDivElement) => boolean)
""".removePrefix("\n").removeSuffix("\n")

private val KNOWN_TYPES = setOf(
    "T",
    "Value",
    "TDate",
    "TValue",
    "TOption",
    "OptionValue",
    "ItemValue",
    "CustomActionContext",
    "TLibFormatToken",
    "ReadonlyArray<T>",
    "ReadonlyArray<Value>",
    "PickerOnChangeFn<TDate>",
    "CalendarPickerView",

    "AlertColor",
    "GridDirection",
    "GridWrap",
    "Orientation",
    "PopoverReference",
    "PopperProps",

    "Breakpoints",
    "BreakpointsOptions",
    "Direction",
    "Shape",
    "ShapeOptions",
    "Spacing",

    "CSSProperties",

    "Mixins",
    "Palette",
    "Transitions",
    "ZIndex",

    "MixinsOptions",
    "PaletteOptions",
    "TransitionsOptions",
    // "ZIndexOptions",

    "CommonColors",
    "TypeText",
    "TypeAction",
    "TypeBackground",

    "SimplePaletteColorOptions",
    "CommonColorsOptions",

    "Easing",
    "Duration",

    "SxProps<Theme>",
)

private val KNOWN_TYPE_SUFFIXES = setOf(
    "Props",
    "Actions",
    "Origin",
    "Position",
    "Variant",
    "Color",
    "Size",
) + UNION_PROPERTIES
    .map {
        @Suppress("DEPRECATION")
        it.capitalize()
    }

private val STANDARD_TYPE_MAP = mapOf(
    "any" to "Any",
    "object" to "Any",
    "string | number | false" to "Any /* String or Number or Boolean /* false */ */",
    "string | number | null" to "Any /* String or Number */",

    // TODO: Probably need to replace all " | undefined" to " | null"
    "FormControlState | undefined" to "Any?",
    "string | undefined" to "String",

    "boolean" to "Boolean",
    "number" to "Number",
    "string" to "String",

    "void" to "Unit",
    "null" to "Nothing?",

    "false" to "Boolean /* false */",
    "true" to "Boolean /* true */",

    "Readonly<boolean>" to "Boolean",
    "string[]" to "ReadonlyArray<String>",
    "TValue[]" to "ReadonlyArray<TValue>",
    "ItemValue[]" to "ReadonlyArray<ItemValue>",
    "OptionValue[]" to "ReadonlyArray<OptionValue>",
    "TOption[]" to "ReadonlyArray<TOption>",

    "Date" to "kotlin.js.Date",

    "readonly CalendarPickerView[]" to "ReadonlyArray<CalendarPickerView>",
    "Breakpoint[]" to "ReadonlyArray<Breakpoint>",
    "UsePaginationItem[]" to "ReadonlyArray<UsePaginationItem>",

    "HTMLElement" to "web.html.HTMLElement",
    "HTMLDivElement" to "web.html.HTMLDivElement",
    "HTMLInputElement" to "web.html.HTMLInputElement",
    "HTMLTextAreaElement" to "web.html.HTMLTextAreaElement",

    "Element | (() => Element | null) | null" to "Element",
    "Partial<OptionsGeneric<any>>" to "popper.core.Options",
    "Partial<PaperProps<React.ElementType>>" to "PaperProps",
    "React.Ref<Instance>" to "react.Ref<popper.core.Instance>",
    "React.Ref<Element>" to "react.Ref<web.dom.Element>",
    "React.Ref<HTMLElement>" to "react.Ref<web.html.HTMLElement>",
    "React.Ref<HTMLInputElement | HTMLTextAreaElement>" to "react.Ref<web.html.HTMLInputElement /* or web.html.HTMLTextAreaElement*/>",
    "React.ElementType<TableCellBaseProps>" to "react.ElementType<*>",
    "React.RefCallback<Element>" to "react.RefCallback<web.dom.Element>",
    "React.RefCallback<HTMLInputElement>" to "react.RefCallback<web.html.HTMLInputElement>",
    "React.RefCallback<HTMLInputElement | HTMLTextAreaElement>" to "react.RefCallback<web.html.HTMLInputElement /* or web.html.HTMLTextAreaElement*/>",

    "{\n  bivarianceHack(event: {}, reason: 'backdropClick' | 'escapeKeyDown'): void;\n}['bivarianceHack']" to
            "(event: Any?, reason: String) -> Unit",

    "React.ReactNode" to "react.ReactNode",
    "NonNullable<React.ReactNode>" to "react.ReactNode",
    "string | React.ReactNode" to "react.ReactNode",
    "string | React.ReactElement" to "react.ReactNode",
    "string | number | React.ReactElement" to "react.ReactNode",
    "React.ReactNode | ((state: FormControlState) => React.ReactNode)" to "react.ReactNode",

    "React.Dispatch<React.SetStateAction<boolean>>" to "react.StateSetter<Boolean>",

    "React.ReactElement" to "react.ReactElement<*>",
    "React.ReactElement<any, any>" to "react.ReactElement<*>",
    "NonNullable<React.ReactElement>" to "react.ReactElement<*>",

    "React.ElementType" to "$ELEMENT_TYPE<*>",

    "React.Ref<unknown>" to "react.Ref<*>",
    "React.Ref<any>" to "react.Ref<*>",

    "React.AriaRole" to "react.dom.aria.AriaRole",

    "PaletteMode" to "mui.material.PaletteMode",
    "TransitionProps" to "mui.material.transitions.TransitionProps",
    "ClickAwayListenerProps" to "mui.base.ClickAwayListenerProps",
    "Partial<BaseModalClasses>" to "ModalClasses",
    "ChipProps<ChipComponent>" to "ChipProps",

    "React.InputHTMLAttributes<HTMLInputElement>" to "react.dom.html.InputHTMLAttributes<web.html.HTMLInputElement>",
    "React.ImgHTMLAttributes<HTMLImageElement> & {\n  sx?: SxProps<Theme>;\n}" to "react.dom.html.ImgHTMLAttributes<web.html.HTMLImageElement>",
    "React.ImgHTMLAttributes<HTMLImageElement>" to "react.dom.html.ImgHTMLAttributes<web.html.HTMLImageElement>",
    "React.HTMLAttributes<HTMLDivElement>" to "react.dom.html.HTMLAttributes<web.html.HTMLDivElement>",
    "Partial<React.HTMLAttributes<HTMLDivElement>>" to "react.dom.html.HTMLAttributes<web.html.HTMLDivElement>",
    "React.HTMLAttributes<HTMLElement>" to "react.dom.html.HTMLAttributes<web.html.HTMLElement>",

    "NonNullable<React.HTMLAttributes<any>['tabIndex']>" to "Int",
    "React.InputHTMLAttributes<unknown>['type']" to "InputType",
    "React.InputHTMLAttributes<HTMLInputElement>['type']" to "InputType",

    "React.ReactEventHandler" to "react.dom.events.ReactEventHandler<*>",
    "React.FocusEventHandler" to "react.dom.events.FocusEventHandler<*>",
    "React.MouseEventHandler" to "react.dom.events.MouseEventHandler<*>",
    "React.MouseEventHandler<HTMLElement>" to "react.dom.events.MouseEventHandler<web.html.HTMLElement>",

    "Node | Window" to "web.events.EventTarget /* web.dom.Node? or web.window.Window? */",

    "null | HTMLElement" to "web.html.HTMLElement?",
    "null | Element | ((element: Element) => Element)" to "Element? /* null | Element | ((element: Element) => Element) */",
    "string | ((value: number, index: number) => React.ReactNode)" to "String /* or (value: Number, index: Number) -> react.ReactNode*/",

    "DisableClearable" to "Boolean",
    "FreeSolo" to "Boolean",

    "SelectionMode" to "mui.system.Union /* 'none' | 'single' | 'multiple' */",

    "{ [key in Breakpoint]: number }" to "Record<Breakpoint, Number>",
    "Record<string, any>" to "Record<String, *>",
    "Record<string, any> & { mode: 'light' | 'dark' }" to "Record<String, *>",

    CREATE_TRANSITION to "(props: ReadonlyArray<String>, options: TransitionCreateOptions?) -> web.cssom.Transition",
    SWIPEABLE_DRAWER_PROPS_ALLOW_SWIPE_IN_CHILDREN to "Boolean /* or (e: TouchEvent, swipeArea: HTMLDivElement, paper: HTMLDivElement) -> Boolean*/",
    USE_TAB_PANEL_RETURN_VALUE_GET_ROOT_PROPS to "() -> UseTabPanelRootSlotProps",

    "'horizontal' | 'vertical'" to "mui.material.Orientation",
    "'vertical' | 'horizontal'" to "mui.material.Orientation",

    "typeof window.matchMedia" to "(query: String) -> web.cssom.MediaQueryList",

    "PopperPlacementType" to "popper.core.Placement",

    "typeof create" to "(props: ReadonlyArray<String>, options: TransitionCreateOptions?) -> web.cssom.Transition",
    "typeof getAutoHeightDuration" to "(height: Number) -> Number",

    "TabsDirection" to "mui.system.Direction",

    "MenuContextType" to "Any /* mui.base.MenuContextType */",
    "<TOther extends EventHandlers>(otherHandlers?: TOther) => UseMenuListboxSlotProps" to
            "Any /* <TOther extends EventHandlers>(otherHandlers?: TOther) => UseMenuListboxSlotProps */",

    "(otherHandlers?: EventHandlers) => UseMenuButtonRootSlotProps" to
            "Any /* (otherHandlers?: EventHandlers) => UseMenuButtonRootSlotProps */",

    "StateChangeCallback<State>" to "Any /* StateChangeCallback<State> */",
)

internal fun kotlinType(
    type: String,
    name: String? = null,
): String {
    if (type in KNOWN_TYPES)
        return type

    if (type == "number" && name == "tabIndex")
        return "Int"

    if (type == "string" && name != null && name.endsWith("ClassName"))
        return "ClassName"

    // TODO: Need to support "unknown" -> "Any" for all others
    if (("unknown" == type || type == "Value") && name == "value")
        return "Any"

    // For `RegularBreakpoints` of `Grid` component
    if (name in setOf("lg", "md", "sm", "xl", "xs") && type == "boolean | GridSize")
        return "Any /* boolean | 'auto' | number */"

    // For `FormControl.FormControlOwnProps`
    if (name == "defaultValue" && type == "unknown")
        return "Any"

    // For `FormControl.FormControlOwnProps`
    if (name == "anchorEl" && "null" in type && "Element" in type && "(() => Element)" in type && "PopoverVirtualElement" in type && "(() => PopoverVirtualElement)" in type)
        return "Element? /* null | Element | (() => Element) | PopoverVirtualElement | (() => PopoverVirtualElement) */"

    // For `useList.UseListReturnValue`
    if (name == "getRootProps" && type == "<TOther extends EventHandlers = {}>(otherHandlers?: TOther) => UseListRootSlotProps<TOther>")
        return "Any /* $type */"

    // For `useAutocomplete`
    if (
        (name == "getTagProps" && type == "AutocompleteGetTagProps")
        || (name == "value" && type == "AutocompleteValue<Value, Multiple, DisableClearable, FreeSolo>")
        || (name == "groupedOptions" && type == "Value[] | Array<AutocompleteGroupedOption<Value>>")
    )
        return "Any /* $type */"

    // For `Input.InputBaseProps`
    if (name == "type" && type == "undefined")
        return "InputType"

    // For `Snackbar.SnackbarClickAwayListenerSlotProps`
    if (name == "ownerState" && type == "SnackbarOwnerState")
        return "Any"

    // For `UseListParameters`
    if (name == "stateReducer" && type == "(state: State, action: ActionWithContext<ListAction<ItemValue> | CustomAction, ListActionContext<ItemValue> & CustomActionContext>) => State")
        return "Any /* $type */"

    // For `Select` (see `Select` in flst for `SelectValue<OptionValue, Multiple>` replacement)
    if (
        (name == "defaultValue" && type == "SelectValue<OptionValue, Multiple>")
        || (name == "value" && type == "SelectValue<OptionValue, Multiple>")
        || (name == "multiple" && type == "Multiple")
        || (name == "getSerializedValue" && type == "(option: SelectValue<SelectOption<OptionValue>, Multiple>) => React.InputHTMLAttributes<HTMLInputElement>['value']")
        || (name == "onChange" && type == "(event: React.MouseEvent | React.KeyboardEvent | React.FocusEvent | null, value: SelectValue<OptionValue, Multiple>) => void")
        || (name == "onHighlightChange" && type == "(event: React.MouseEvent<Element, MouseEvent> | React.KeyboardEvent<Element> | React.FocusEvent<Element, Element> | null, highlighted: OptionValue | null) => void")
        || (name == "renderValue" && type == "(option: SelectValue<SelectOption<OptionValue>, Multiple>) => React.ReactNode")
    )
        return "Any /* $type */"
    if (name == "popper" && type == "React.ComponentType<WithOptionalOwnerState<SelectPopperSlotProps<OptionValue, Multiple>>>")
        return "react.ComponentType<*>"

    // For `useListbox`
    if (name == "stateReducer" && type == "ListboxReducer<TOption>")
        return "Any /* ListboxReducer<TOption> */"

    // For `useMenu`
    if (name == "menuItems" && type == "Record<string, MenuItemMetadata>")
        return "Any /* Record<string, MenuItemMetadata> */"

    // For `useTabs`
    if (name == "tabsContextValue" && type == "TabsContextValue")
        return "Any /* TabsContextValue */"

    // For system theme interfaces
    if (name == "palette" && type.startsWith("Record<"))
        return "$DYNAMIC /* ${STANDARD_TYPE_MAP.getValue(type)} */"

    if (name == "dateAdapter")
        return "$DATE_ADAPTER /* $type */"

    STANDARD_TYPE_MAP[type]
        ?.also { return it }

    type.toFunctionType()
        ?.also { return it }

    if ((name == "minRows" || name == "maxRows") && type == "string | number")
        return "Int"

    if (type.endsWith(" | null")) {
        val t = kotlinType(type.removeSuffix(" | null"))
        return if (t == DYNAMIC) t else "$t?"
    }

    if (KNOWN_TYPE_SUFFIXES.any { type.endsWith(it) } && " | " !in type && type != "Color")
        return type

    val promiseResult = type.removeSurrounding("Promise<", ">")
    if (promiseResult != type)
        return "$PROMISE<${kotlinType(promiseResult)}>"

    val styleValueResult = type.removeSurrounding("ResponsiveStyleValue<", ">")
    if (styleValueResult != type)
        return "mui.system.ResponsiveStyleValue<${kotlinType(styleValueResult)}>"

    val refResult = type.removeSurrounding("React.Ref<", ">")
    if (refResult != type)
        return "react.Ref<${kotlinType(refResult)}>"

    if (type.startsWith("React.ElementType<"))
        return type.replace("React.ElementType", ELEMENT_TYPE)
            .replace("<TransitionProps>", "<mui.material.transitions.TransitionProps>")
            .replace(
                "React.HTMLAttributes<HTMLDivElement>",
                "react.dom.html.HTMLAttributes<web.html.HTMLDivElement>"
            )

    if (type.startsWith("React.") && "Handler<" in type) {
        val handlerType = type.removePrefix("React.")
            .replace("<any>", "<*>")
            .replace("<{}>", "<*>")
            .replace("<HTMLInputElement | HTMLTextAreaElement>", "<web.html.HTMLElement>")
            .replace("<HTMLTextAreaElement | HTMLInputElement>", "<web.html.HTMLElement>")
            .replace("<HTMLInputElement>", "<web.html.HTMLInputElement>")

        return "react.dom.events.$handlerType"
    }

    val propsType = type.removeSurrounding("React.JSXElementConstructor<", ">")
    if (propsType != type) {
        val typeParameter = propsType
            .takeIf { it.endsWith("Props") }
            ?.let { STANDARD_TYPE_MAP[it] ?: it }
            ?: "*"

        return "react.ComponentType<$typeParameter>"
    }

    val partialResult = type.removeSurrounding("Partial<", ">")
    if (partialResult != type) {
        if (partialResult.endsWith("Props")) {
            return when (partialResult) {
                "TouchRippleProps",
                "NativeSelectInputProps",
                -> DYNAMIC

                "StandardInputProps",
                -> "InputProps"

                "SelectProps",
                -> "SelectProps<*>"

                else -> STANDARD_TYPE_MAP[partialResult] ?: partialResult
            }
        } else if (partialResult.endsWith("Classes")) {
            return partialResult
        } else if (partialResult in KNOWN_TYPES) {
            return partialResult
        }
    }

    if (type.startsWith("'")) {
        // TODO: Don't understand why need this check. Should work without. Try to remove
        if (name == "overlap" && type == "'rectangular' | 'circular'") {
            return "BadgeOverlap"
        }

        return "$UNION /* $type */"
    }

    if (type.startsWith("\n  | '")) {
        val t = type.removePrefix("\n")
            .trimIndent()
            .replace("\n", " ")
            .removePrefix("| ")

        return "$UNION /* $t */"
    }

    if (type.startsWith("OverridableStringUnion<")) {
        val comment = type.removeSurrounding("OverridableStringUnion<", ">")
            .splitToSequence("\n")
            .filter { it.isNotEmpty() }
            .map { it.trimStart() }
            .joinToString(" ")

        if (comment == "Variant | 'inherit', TypographyPropsVariantOverrides")
            return "mui.material.styles.TypographyVariant"

        // TODO: Don't understand why need this check. Should work without. Try to remove
        if (name == "variant" && comment == "'standard' | 'dot', BadgePropsVariantOverrides")
            return "BadgeVariant"

        return "$UNION /* $comment */"
    }

    if (type.startsWith("TypographyProps<"))
        return "TypographyProps"

    type.toAlias()
        ?.also { return it }

    if (type.endsWith("']") || type.endsWith("'] | 'auto'"))
        return "$DYNAMIC /* $type */"

    // TODO: Remove when MUI completes migration to slots
    if ((name == "components" || name == "componentsProps") && type.startsWith("{\n") && "/**" !in type) {
        @Suppress("DEPRECATION")
        val interfaceName = name.capitalize()
        val defaultType = if (name == "components") "react.ElementType<*>" else "react.Props"
        return interfaceName + "\n\n" + componentInterface(interfaceName, type, defaultType)
    }

    // TODO: Need to process `SlotProps` interface separately from parent interface
    if (name == "slots" || name == "slotProps") {
        return if (!type.startsWith("{\n") || "/**" in type) {
            type
                .replace("<OptionValue, Multiple>", "")
                .replace("<TValue>", "")
        } else {
            // TODO: Else branch should die when MUI fully migrates to named slot types
            @Suppress("DEPRECATION")
            val interfaceName = name.capitalize()
            val defaultType = if (name == "slots") "react.ElementType<*>" else "react.Props"
            interfaceName + "\n\n" + componentInterface(interfaceName, type, defaultType)
        }
    }

    if (name != null && name.endsWith("Props") && name != "componentsProps" && name != "slotProps") {
        val comment = type.split("\n")
            .map { it.trim() }
            .joinToString(" ")

        return "react.Props /* $comment */"
    }

    return DYNAMIC
}

private fun componentInterface(
    sourceName: String,
    source: String,
    defaultType: String,
): String {
    val body = source
        .removeSurrounding("{\n", ";\n}")
        .trimIndent()
        .replace(";\n}", "\n}")
        .replace(";\n  ", "\n  ")
        .splitToSequence(";\n")
        .joinToString("\n") { line ->
            val (name, typeSource) = line.split("?: ")
            val type = STANDARD_TYPE_MAP[typeSource]
                ?.let { "$it?" }
                ?: "$defaultType? /* $typeSource */"

            "var $name: $type"
        }

    return "interface $sourceName {\n$body\n}"
}
