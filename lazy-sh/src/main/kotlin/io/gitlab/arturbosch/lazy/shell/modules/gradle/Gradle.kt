package io.gitlab.arturbosch.lazy.shell.modules.gradle

import io.gitlab.arturbosch.ksh.api.ShellClass
import io.gitlab.arturbosch.ksh.api.ShellMethod
import io.gitlab.arturbosch.ksh.api.ShellOption
import java.io.File
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.nio.file.StandardOpenOption

/**
 * @author Artur Bosch
 */
class Gradle : ShellClass {

	override val help: String = "Provides templating commands specific for gradle."

	@ShellMethod
	fun module(
			@ShellOption(["", "--name"]) name: String,
			@ShellOption(["-g", "--group"]) group: String,
			@ShellOption(["-l", "--language"], defaultValue = "kotlin") language: String,
			@ShellOption(["-kts", "--kotlin-dsl"], arity = 0, defaultValue = "false") kts: Boolean,
			@ShellOption(["-wd", "--working-dir"], defaultValue = ".") workingDir: String
	): String {
		check(name.isNotEmpty()) { "module name must not be empty." }
		check(!File("$workingDir/$name").exists()) { "module with name '$name' already exists." }
		check(group.contains(Regex("[\\w|.]*"))) { "group '$group' must have only words and dots." }
		check(language.isNotEmpty()) { "language must not be empty." }

		val base = Paths.get(workingDir).resolve(name).createDirs()
		val groupDirs = group.replace(".", "/")
		val main = base.resolve("src/main").createDirs()
		val test = base.resolve("src/test").createDirs()
		main.resolve("resources").createDirs()
		test.resolve("resources").createDirs()
		main.resolve(language).resolve(groupDirs).createDirs()
		test.resolve(language).resolve(groupDirs).createDirs()

		base.resolve(if (kts) "build.gradle.kts" else "build.gradle")
				.ifNotExists()
				?.createFile()
		base.resolve(if (kts) "settings.gradle.kts" else "settings.gradle")
				.ifExists()
				?.append("include '$name'")
		return "Successfully created module '$name'"
	}
}

fun Path.ifExists(): Path? = if (Files.exists(this)) this else null
fun Path.ifNotExists(): Path? = if (Files.notExists(this)) this else null

fun Path.append(content: String): Path =
		Files.write(this, content.toByteArray(), StandardOpenOption.APPEND)

fun Path.createFile(): Path = Files.createFile(this)
fun Path.createDirs(): Path = Files.createDirectories(this)
