package com.github.vgramer.opaplugin.ide.colors

import com.intellij.openapi.editor.DefaultLanguageHighlighterColors
import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.openapi.options.colors.AttributesDescriptor

/**
 * Kind of "token" for which the user can change color in the Syntax highlighting.
 *
 * note: this enum lust be keep in sync with [com.github.vgramer.opaplugin.ide.highlight.RegoHighlighter]. If you define
 * a color that in not defined in the highlighter, then user will be able to customize it but ide will never render it.
 *
 * @see com.github.vgramer.opaplugin.ide.colors.RegoColorSettingsPage
 * @see com.github.vgramer.opaplugin.ide.highlight.RegoHighlighter
 */
enum class RegoColor(humanName: String, val default: TextAttributesKey? = null) {

    KEYWORD("Keywords//Keyword", DefaultLanguageHighlighterColors.KEYWORD),
    NUMBER("Literals//Number", DefaultLanguageHighlighterColors.NUMBER),
    BOOLEAN("Literals//Boolean", DefaultLanguageHighlighterColors.NUMBER),
    STRING("Literals//Strings//String", DefaultLanguageHighlighterColors.STRING),
    LINE_COMMENT("Comments//Line comment", DefaultLanguageHighlighterColors.LINE_COMMENT),


    BRACES("Braces and Operators//Braces", DefaultLanguageHighlighterColors.BRACES),
    BRACKETS("Braces and Operators//Brackets", DefaultLanguageHighlighterColors.BRACKETS),
    PARENTHESES("Braces and Operators//Parentheses", DefaultLanguageHighlighterColors.PARENTHESES),
    DOT("Braces and Operators//Dot", DefaultLanguageHighlighterColors.DOT),
    COMMA("Braces and Operators//Comma", DefaultLanguageHighlighterColors.COMMA),
    SEMICOLON("Braces and Operators//Semicolon", DefaultLanguageHighlighterColors.SEMICOLON),
    OPERATOR("Braces and Operators//Operation sign\"", DefaultLanguageHighlighterColors.OPERATION_SIGN),
    ;


    val textAttributesKey = TextAttributesKey.createTextAttributesKey("com.github.vgramer.opaplugin.$name", default)
    val attributesDescriptor = AttributesDescriptor(humanName, textAttributesKey)
}