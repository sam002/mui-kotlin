// Automatically generated - do not modify!

@file:JsModule("@mui/lab/TimelineItem")
@file:JsNonModule

package mui.material

import kotlinext.js.ReadonlyArray

external interface TimelineItemProps : react.PropsWithChildren {
    /**
     * The position where the timeline's item should appear.
     */
    var position: Union /* 'left' | 'right' */

    /**
     * The content of the component.
     */
    override var children: ReadonlyArray<react.ReactNode>?

    /**
     * Override or extend the styles applied to the component.
     */
    var classes: TimelineItemClasses

    /**
     * The system prop that allows defining system overrides as well as additional CSS styles.
     */
    var sx: SxProps<Theme>
}

/**
 *
 * Demos:
 *
 * - [Timeline](https://mui.com/components/timeline/)
 *
 * API:
 *
 * - [TimelineItem API](https://mui.com/api/timeline-item/)
 */
@JsName("default")
external val TimelineItem: react.FC<TimelineItemProps>
