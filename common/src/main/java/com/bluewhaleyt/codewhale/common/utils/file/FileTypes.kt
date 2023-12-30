package com.bluewhaleyt.codewhale.common.utils.file

enum class FileTypes(
    val extensions: Array<String>
) {
    BitmapImage(
        extensions = arrayOf("bmp", "jpg", "jpeg", "png", "tiff", "webp")
    ),
    Markdown(
        extensions = arrayOf("md", "mdx")
    ),
    Markup(
        extensions = arrayOf("htm", "html", "xhtml")
    ),
    Java(
        extensions = arrayOf("java")
    ),
    JavaScript(
        extensions = arrayOf("js")
    ),
    TypeScript(
        extensions = arrayOf("ts")
    ),
    VectorImage(
        extensions = arrayOf("svg", "swf")
    ),
}