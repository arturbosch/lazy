package io.gitlab.arturbosch.lazy.shell.providers

import io.gitlab.arturbosch.ksh.api.ShellSettings
import io.gitlab.arturbosch.ksh.api.provider.ShellSettingsProvider
import io.gitlab.arturbosch.kutils.Container
import io.gitlab.arturbosch.lazy.shell.LazySettings

class SettingsProvider : ShellSettingsProvider {

    override fun provide(container: Container): ShellSettings = LazySettings()
}
