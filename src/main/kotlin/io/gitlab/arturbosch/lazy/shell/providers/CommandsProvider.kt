package io.gitlab.arturbosch.lazy.shell.providers

import io.gitlab.arturbosch.ksh.api.ShellClass
import io.gitlab.arturbosch.ksh.api.provider.ShellClassesProvider
import io.gitlab.arturbosch.kutils.Container
import io.gitlab.arturbosch.lazy.shell.modules.gradle.Gradle
import io.gitlab.arturbosch.lazy.shell.modules.services.Meta

class CommandsProvider : ShellClassesProvider {

    override fun provide(container: Container): List<ShellClass> =
        listOf(
            Gradle(),
            Meta()
        )
}
