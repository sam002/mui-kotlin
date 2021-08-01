// Automatically generated - do not modify!

@file:JsModule("@material-ui/core/InputAdornment")
@file:JsNonModule

package material

external interface InputAdornmentProps : react.RProps {
    /**
     * Override or extend the styles applied to the component.
     */
    var classes: dynamic

    /**
     * The content of the component, normally an `IconButton` or string.
     */
    var children: react.ReactNode

    /**
     * Disable pointer events on the root.
     * This allows for the content of the adornment to focus the `input` on click.
     * @default false
     */
    var disablePointerEvents: Boolean

    /**
     * If children is a string then disable wrapping in a Typography component.
     * @default false
     */
    var disableTypography: Boolean

    /**
     * The position this adornment should appear relative to the `Input`.
     */
    var position: Union /* 'start' | 'end' */

    /**
     * The system prop that allows defining system overrides as well as additional CSS styles.
     */
    var sx: SxProps<Theme>

    /**
     * The variant to use.
     * Note: If you are using the `TextField` component or the `FormControl` component
     * you do not have to set this manually.
     */
    var variant: Union /* 'standard' | 'outlined' | 'filled' */
}

/**
 *
 * Demos:
 *
 * - [Text Fields](https://material-ui.com/components/text-fields/)
 *
 * API:
 *
 * - [InputAdornment API](https://material-ui.com/api/input-adornment/)
 */
@JsName("default")
external val InputAdornment: react.FC<InputAdornmentProps>
