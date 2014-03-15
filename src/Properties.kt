package kotlinx.css

val animation = CssAnimation()
class CssAnimation() : CssProperty("animation") {
    val delay by CssSubProperty()
    val direction by CssSubProperty()
    val duration by CssSubProperty()
    val fill_mode by CssSubProperty()
    val iteration_count by CssSubProperty()
    val name by CssSubProperty()
    val play_state by CssSubProperty()
    val timing_function by CssSubProperty()
}

fun Style.appearance(value: String) = property("appearance", value)
fun Style.backface_visibility(value: String) = property("backface-visibility", value)

val background = CssBackground()
class CssBackground() : CssProperty("background") {
    val color by CssSubProperty()
    val image by CssSubProperty()
    val attachment by CssSubProperty()
    val clip by CssSubProperty()
    val origin by CssSubProperty()
    val position by CssSubProperty()
    val repeat by CssSubProperty()
    val size by CssSubProperty()
}

val padding = CssSides("padding")
val margin = CssSides("margin")

class CssSides(name : String) : CssProperty(name) {
    val left by CssSubProperty()
    val right by CssSubProperty()
    val top by CssSubProperty()
    val bottom by CssSubProperty()
}

open class CssBorderStyle(name : String, parent : CssProperty? = null) : CssProperty(name, parent) {
    val width by CssSubProperty()
    val style by CssSubProperty()
    val color by CssSubProperty()
}

val border = CssBorder()
class CssBorder() : CssBorderStyle("border") {
    val left = CssBorderStyle("left", this)
    val right = CssBorderStyle("right", this)
    val top = CssBorderStyle("top", this)
    val bottom = CssBorderStyle("bottom", this)

    val collapse by CssSubProperty()
    val image by CssSubProperty()
    val radius by CssSubProperty()
    val spacing by CssSubProperty()
}

fun Style.bottom(value: String) = property("bottom", value)
fun Style.box_shadow(value: String) = property("box-shadow", value)
fun Style.caption_side(value: String) = property("caption-side", value)
fun Style.clear(value: String) = property("clear", value)
fun Style.clip(value: String) = property("clip", value)
fun Style.color(value: String) = property("color", value)
fun Style.column(value: String) = property("column", value)
fun Style.content(value: String) = property("content", value)
fun Style.counter_increment(value: String) = property("counter-increment", value)
fun Style.counter_reset(value: String) = property("counter-reset", value)
fun Style.cursor(value: String) = property("cursor", value)
fun Style.direction(value: String) = property("direction", value)
fun Style.display(value: String) = property("display", value)
fun Style.empty_cells(value: String) = property("empty-cells", value)
fun Style.float(value: String) = property("float", value)

val font = CssFont()
class CssFont() : CssProperty("font") {
    val family by CssSubProperty()
    val size by CssSubProperty()
    val size_adjust by CssSubProperty()
    val stretch by CssSubProperty()
    val style by CssSubProperty()
    val variant by CssSubProperty()
    val weight by CssSubProperty()
}

fun Style.grid_columns(value: String) = property("grid-columns", value)
fun Style.grid_rows(value: String) = property("grid-rows", value)
fun Style.hanging_punctuation(value: String) = property("hanging-punctuation", value)
fun Style.height(value: String) = property("height", value)
fun Style.icon(value: String) = property("icon", value)
fun Style.left(value: String) = property("left", value)
fun Style.letter_spacing(value: String) = property("letter-spacing", value)
fun Style.line_height(value: String) = property("line-height", value)
fun Style.list_style(value: String) = property("list-style", value)
fun Style.max_height(value: String) = property("max-height", value)
fun Style.max_width(value: String) = property("max-width", value)
fun Style.min_height(value: String) = property("min-height", value)
fun Style.min_width(value: String) = property("min-width", value)
fun Style.nav(value: String) = property("nav", value)
fun Style.opacity(value: String) = property("opacity", value)
fun Style.outline(value: String) = property("outline", value)
fun Style.overflow(value: String) = property("overflow", value)
fun Style.overflow_x(value: String) = property("overflow-x", value)
fun Style.overflow_y(value: String) = property("overflow-y", value)
fun Style.page_break(value: String) = property("page-break", value)
fun Style.perspective(value: String) = property("perspective", value)
fun Style.perspective_origin(value: String) = property("perspective-origin", value)
fun Style.position(value: String) = property("position", value)
fun Style.punctuation_trim(value: String) = property("punctuation-trim", value)
fun Style.quotes(value: String) = property("quotes", value)
fun Style.resize(value: String) = property("resize", value)
fun Style.right(value: String) = property("right", value)
fun Style.rotation(value: String) = property("rotation", value)
fun Style.rotation_point(value: String) = property("rotation-point", value)
fun Style.table_layout(value: String) = property("table-layout", value)
fun Style.target(value: String) = property("target", value)

fun Style.text(value: String) = property("text", value)
fun Style.text_decoration(value: String) = property("text-decoration", value)
fun Style.text_align(value: String) = property("text-align", value)

fun Style.top(value: String) = property("top", value)
fun Style.transform(value: String) = property("transform", value)
fun Style.transition(value: String) = property("transition", value)
fun Style.unicode_bidi(value: String) = property("unicode-bidi", value)
fun Style.vertical_align(value: String) = property("vertical-align", value)
fun Style.visibility(value: String) = property("visibility", value)
fun Style.width(value: String) = property("width", value)
fun Style.white_space(value: String) = property("white-space", value)
fun Style.word_spacing(value: String) = property("word-spacing", value)
fun Style.word_break(value: String) = property("word-break", value)
fun Style.word_wrap(value: String) = property("word-wrap", value)
fun Style.z_index(value: String) = property("z-index", value)