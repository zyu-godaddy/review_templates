
# Review of template languages

What we need is to render some simple templates containing
placeholders like `person.name` ;
in the future we might need something more like `if-else` and `foreach`

## Velocity vs FreeMarker

Two similar solutions. Both are great.
There's no real difference for most of our use cases.

Sample templates to render the same output:
- [data model input](./src/main/resources/model.json)
- [Velocity](./src/main/resources/templates/Velocity.txt) template
- [FreeMarker](./src/main/resources/templates/FreeMarker.txt) template
- [expected output](./src/main/resources/expectedRender.txt)

Performance: microseconds to render the sample templates:
- Velocity:  120 us
- FreeMarker: 60 us

Velocity is a simpler language; it's easy to invoke Java methods within
to do whatever it is missing, like `$person.getName().toUpperCase()`

FreeMarker is a more sophisticated language with more constructs so that
it is self-sufficient. Drawbacks:
- another "big" language to learn, with unfamiliar syntax
- more verbose than Velocity
  - `${foo}` vs `$foo`
  - `<#if x>...</#if>` vs `#if(x)...#end`
  - `<#list people as p>...</#list>` vs `#foreach($p in $people)...#end`
- hard to invoke Java methods within
- geared for human friendly outputs
  - date/number in locale format by default
  - boolean can't be rendered directly

Velocity is probably easier and less surprising for Java programmers.

## Other template languages

### Thymeleaf

Thymeleaf is designed to generate HTML documents. While it does support 
[text mode](https://www.thymeleaf.org/doc/tutorials/3.0/usingthymeleaf.html#textual-template-modes),
the syntax is unbearably verbose; for that reason we'll rule it out.

### StringTemplate

https://github.com/antlr/stringtemplate4/blob/master/doc/templates.md

From the author of ANTLR. A template is considered a generative grammar.
This is a little weird for programmers familiar in Java-like languages.
There are quite some restrictions for philosophical reasons,
making it not very convenient to use for a lot of cases.

### Mustache

https://github.com/samskivert/jmustache

A cute simple template language. The philosophy is that anything complex
should be computed and stored in data model, keeping template itself simple.
Might be inconvenient to use for some cases.

