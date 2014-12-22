package kotlinx.css.test

import kotlinx.css.*
import org.junit.*
import kotlin.test.*
import org.jetbrains.spek.api.*

fun renderCSS(body: StyleContainer.() -> Unit): String {
    val style = StyleContainer()
    style.body()
    return style.toString().replace("  a: b;\n", "")
}

fun Style.placeholder() {
    property("a", "b")
}

class CssBuilderSpek() : Spek() {{
    given("css builder") {

        on("using tag") {
            val css = renderCSS {
                a { placeholder() }
            }
            it("should generate tag selector") {
                assertEquals("a {\n}\n\n", css)
            }
        }

        on("using tag and id") {
            val css = renderCSS {
                a.id("top") { placeholder() }
            }
            it("should build tag#id selector") {
                assertEquals("a#top {\n}\n\n", css)
            }
        }

        on("using id") {
            val css = renderCSS {
                id("top") { placeholder() }
            }
            it("should build #id selector") {
                assertEquals("#top {\n}\n\n", css)
            }
        }

        on("using style") {
            val css = renderCSS {
                style("top") { placeholder() }
            }
            it("should build .style selector") {
                assertEquals(".top {\n}\n\n", css)
            }
        }

        on("using style and id") {
            val css = renderCSS {
                style("top").id("main") { placeholder() }
            }
            it("should build .style#id selector") {
                assertEquals(".top#main {\n}\n\n", css)
            }
        }

        on("using tag, style and id") {
            val css = renderCSS {
                div.style("top").id("main") { placeholder() }
            }
            it("should build tag.style#id selector") {
                assertEquals("div.top#main {\n}\n\n", css)
            }
        }

        on("using media") {
            val css = renderCSS {
                div {
                    media("min-width:1200px") { placeholder() }
                }
            }
            it("should build @media { div } selector") {
                assertEquals("@media (min-width:1200px) {\ndiv {\n}\n\n}\n\n", css)
            }
        }

        on("using pseudo-selector") {
            val css = renderCSS {
                a.select("hover") {
                    placeholder()
                }
            }
            it("should build a:hover selector") {
                assertEquals("a:hover {\n}\n\n", css)
            }
        }

        on("using not pseudo-selector") {
            val css = renderCSS {
                a.not.attribute("href") {
                    placeholder()
                }
            }
            it("should build a:not([href]) selector") {
                assertEquals("a:not([href]) {\n}\n\n", css)
            }
        }
    }
}
}

class CssBasicsTests() {
    Test fun testCssClassDual() {
        val doc = renderCSS {
            style("top").style("nav") { placeholder() }
        }
        assertEquals(".top.nav {\n}\n\n", doc)
    }

    Test fun testCssClassDual2() {
        val doc = renderCSS {
            style("top") {
                placeholder()
                style("nav") { placeholder() }
            }
        }
        assertEquals(".top {\n}\n\n.top.nav {\n}\n\n", doc)
    }

    Test fun testCssProperty() {
        val doc = renderCSS {
            a {
                border(1.px)
            }
        }
        assertEquals("a {\n  border: 1px solid;\n}\n\n", doc)
    }

    Test fun testCssNested() {
        val doc = renderCSS {
            a {
                placeholder()
                nested.div { placeholder() }
            }
        }
        assertEquals("a {\n}\n\na div {\n}\n\n", doc)

    }

    Test fun testCssImmediate() {
        val doc = renderCSS {
            a {
                placeholder()
                immediate.div { placeholder() }
            }
        }
        assertEquals("a {\n}\n\na>div {\n}\n\n", doc)

    }

    Test fun testCssTagOr() {
        val doc = renderCSS {
            a.or.div { placeholder() }
        }
        assertEquals("a,div {\n}\n\n", doc)
    }

    Test fun testCssNext() {
        val doc = renderCSS {
            a {
                placeholder()
                next.div { placeholder() }
            }
        }
        assertEquals("a {\n}\n\na+div {\n}\n\n", doc)

    }
    Test fun testCssAfter() {
        val doc = renderCSS {
            a {
                placeholder()
                after.div { placeholder() }
            }
        }
        assertEquals("a {\n}\n\na~div {\n}\n\n", doc)

    }

    Test fun testCssAttribute() {
        val doc = renderCSS {
            a.attribute("href") {
                placeholder()
                nested.div { placeholder() }
            }
        }
        assertEquals("a[href] {\n}\n\na[href] div {\n}\n\n", doc)
    }

    Test fun testCssAttribute2() {
        val doc = renderCSS {
            a {
                attribute("href") {
                    placeholder()
                }
                attribute("name") {
                    placeholder()
                    nested.div { placeholder() }
                }
            }
        }
        assertEquals("a[href] {\n}\n\na[name] {\n}\n\na[name] div {\n}\n\n", doc)
    }

    Test fun testCssAttributeConditions() {
        val doc = renderCSS {
            a {
                attribute("equals", "value", AttributeOperation.equals) {
                    placeholder()
                }
                attribute("startsWith", "value", AttributeOperation.startsWith) {
                    placeholder()
                }
                attribute("endsWith", "value", AttributeOperation.endsWith) {
                    placeholder()
                }
                attribute("contains", "value", AttributeOperation.contains) {
                    placeholder()
                }
            }
        }
        assertEquals("""a[equals=value] {
}

a[startsWith^=value] {
}

a[endsWith$=value] {
}

a[contains*=value] {
}

""", doc)
    }


    Test fun completeTest() {
        val doc = renderCSS {
            a.id("top") {
                border(1.px)

                immediate.div.id("content").select("last-child") {
                    // @media('width < 640px') a#top > div#content:last-child
                    placeholder()
                }

                immediate {
                    // a#top > *
                    any { placeholder() }

                    div {
                        // a#top > div
                        placeholder()
                    }

                    a.style("foo") {
                        // a#top > a.foo
                        placeholder()
                    }

                    next.div.style("bar") {
                        // a#top > * + div.bar
                        // selects all div tags with bar style which are preceded by any immediate child of a with id top
                        placeholder()
                    }
                }

                select("hover").select("focus") {
                    // a#top:hover:focused
                    placeholder()
                }

                nested.tag("article").id("foo") {
                    // a#top article#foo
                    placeholder()

                }

                nested.div.style("muted") {
                    // a#top div.muted
                    placeholder()
                }

            }

        }
        assertEquals(
                """a#top {
  border: 1px solid;
}

a#top>div#content:last-child {
}

a#top>* {
}

a#top>div {
}

a#top>a.foo {
}

a#top>*+div.bar {
}

a#top:hover:focus {
}

a#top article#foo {
}

a#top div.muted {
}

""", doc)
    }
}