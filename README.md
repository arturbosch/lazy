# lazy - a boilerplate generating cli tool

*~ Based on [ksh](https://github.com/arturbosch/ksh)*

```
lazy> help
Available commands:

      gradle: Provides templating commands specific for gradle.
        module: Generates a new Gradle module with given name, group, language.
      meta: Provides templating commands specific for META-INF stuff.
        services: Generates an META-INF/services/[your_service] file.

Builtin commands:

    * clear: Clears the terminal.
    * exit: Exits the shell.
    * help: Prints this help message.
    * !: Allows to executes arbitrary shell commands.
        file: Allows to execute ksh commands provided by a file.
    * stacktrace: Prints the last error.
      system: Provides different system utils.
        time: Prints the current time.
        date: Prints the current date.
```

### __gradle__

```
lazy> help gradle module
NAME
        gradle - Provides templating commands specific for gradle.
            module - Generates a new Gradle module with given name, group, language.

SYNOPSIS
        module [[--name] [string]] [[-g] [string]] [[-l] [string]] [-kts] [[-wd] [string]]

OPTIONS
        --name or [default] [string]
        -g or --group [string]
        -l or --language [string]
        -kts or --kotlin-dsl [boolean]
        -wd or --working-dir [string]
```

### __meta__

```
lazy> help meta services
NAME
        meta - Provides templating commands specific for META-INF stuff.
            services - Generates an META-INF/services/[your_service] file.

SYNOPSIS
        services [[--module] [string]] [-t] [[-s] [string]]

OPTIONS
        --module or [default] [string]
        -t or --test [boolean]
        -s or --service [string]
```
