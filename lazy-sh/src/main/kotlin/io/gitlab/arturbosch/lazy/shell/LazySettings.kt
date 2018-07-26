package io.gitlab.arturbosch.lazy.shell

import io.gitlab.arturbosch.ksh.api.Prompt

/**
 * @author Artur Bosch
 */
class LazySettings : Prompt {

	override val applicationName: String = "lazy"
	override val historyFile: String = System.getProperty("user.home") + "/.lazy/history"
	override fun message(): String = "lazy> "
}
