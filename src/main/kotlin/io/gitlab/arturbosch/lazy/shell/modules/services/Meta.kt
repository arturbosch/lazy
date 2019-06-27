package io.gitlab.arturbosch.lazy.shell.modules.services

import io.gitlab.arturbosch.ksh.api.ShellClass
import io.gitlab.arturbosch.ksh.api.ShellMethod
import io.gitlab.arturbosch.ksh.api.ShellOption
import io.gitlab.arturbosch.kutils.createFile
import io.gitlab.arturbosch.kutils.hasEnding
import io.gitlab.arturbosch.kutils.isTrue
import io.gitlab.arturbosch.kutils.streamOnce
import java.nio.file.Paths

class Meta : ShellClass {

    override val help: String = "Provides templating commands specific for META-INF stuff."

    @ShellMethod(help = "Generates an META-INF/services/[your_service] file.")
    fun services(
        @ShellOption(["", "--module"]) module: String,
        @ShellOption(value = ["--test", "-t"], defaultValue = "false") forTest: Boolean,
        @ShellOption(value = ["--service", "-s"]) serviceName: String
    ): String {
        val modulePath = Paths.get(".")
            .streamOnce()
            .find { it.hasEnding(module) }
            ?: throw IllegalArgumentException("No module with name '$module' found")
        val metaPath = modulePath.streamOnce(excludeRoot = true)
            .find { it.hasEnding("META-INF/services") }

        val inMain = metaPath?.toString()?.contains("src/main/resources")
        val inTest = metaPath?.toString()?.contains("src/test/resources")

        if (inTest.isTrue() || inMain.isTrue()) {
            metaPath.resolve(serviceName).createFile()
        } else {
            if (forTest) {
                modulePath.resolve("src/test/resources/META-INF/services/$serviceName").createFile()
            } else { // for main
                modulePath.resolve("src/main/resources/META-INF/services/$serviceName").createFile()
            }
        }

        return "Successfully generated META-INF/services for $module"
    }
}
