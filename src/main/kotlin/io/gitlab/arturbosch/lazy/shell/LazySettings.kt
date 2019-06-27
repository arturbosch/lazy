package io.gitlab.arturbosch.lazy.shell

import io.gitlab.arturbosch.ksh.api.ShellSettings

class LazySettings : ShellSettings {

    override val applicationName: String = "lazy"
    override val historyFile: String = System.getProperty("user.home") + "/.lazy/history"
    override fun prompt(): String = "lazy> "
}
