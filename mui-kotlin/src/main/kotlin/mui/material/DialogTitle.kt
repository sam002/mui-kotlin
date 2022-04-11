// Automatically generated - do not modify!

@file:JsModule("@mui/material/DialogTitle")
@file:JsNonModule

package mui.material

import mui.material.styles.Theme
import mui.system.SxProps

external interface DialogTitleProps :
    mui.system.StandardProps,
    react.dom.html.HTMLAttributes<org.w3c.dom.HTMLHeadingElement>,
    react.PropsWithChildren,
    mui.system.PropsWithSx {
    /**
     * The content of the component.
     */
    override var children: react.ReactNode?

    /**
     * Override or extend the styles applied to the component.
     */
    var classes: DialogTitleClasses?

    /**
     * The system prop that allows defining system overrides as well as additional CSS styles.
     */
    override var sx: SxProps<Theme>?
}

/**
 *
 * Demos:
 *
 * - [Dialogs](https://mui.com/material-ui/react-dialog/)
 *
 * API:
 *
 * - [DialogTitle API](https://mui.com/material-ui/api/dialog-title/)
 */
@JsName("default")
external val DialogTitle: react.FC<DialogTitleProps>
