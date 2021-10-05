// Automatically generated - do not modify!

@file:JsModule("@mui/lab/Masonry")
@file:JsNonModule

package mui.material

import kotlinext.js.ReadonlyArray

external interface MasonryProps : react.PropsWithChildren {
    /**
     * The content of the component. It's recommended to be `<MasonryItem />`s.
     */
    override var children: ReadonlyArray<react.ReactNode>?

    /**
     * Override or extend the styles applied to the component.
     */
    var classes: MasonryClasses

    /**
     * Number of columns.
     * @default 4
     */
    var columns: ResponsiveStyleValue<dynamic>

    /**
     * Defines the space between children. It is a factor of the theme's spacing.
     * @default 1
     */
    var spacing: ResponsiveStyleValue<dynamic>

    /**
     * Allows defining system overrides as well as additional CSS styles.
     */
    var sx: SxProps<Theme>
}

/**
 *
 * Demos:
 *
 * - [Masonry](https://mui.com/components/masonry/)
 *
 * API:
 *
 * - [Masonry API](https://mui.com/api/masonry/)
 */
@JsName("default")
external val Masonry: react.FC<MasonryProps>
