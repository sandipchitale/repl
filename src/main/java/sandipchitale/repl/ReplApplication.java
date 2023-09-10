package sandipchitale.repl;

import org.jline.reader.History;
import org.jline.reader.LineReader;
import org.jline.reader.LineReaderBuilder;
import org.jline.reader.impl.completer.StringsCompleter;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;
import org.jline.utils.InfoCmp;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ReplApplication {

	private static final String HELP_LINE = "%30s - %s%n";
	
	@Bean
	public CommandLineRunner clr () {
	    return (String... args) -> {
			Terminal terminal = TerminalBuilder
					.builder()
					.jna(true)
					.jansi(false)
					.dumb(false)
					.build();
			LineReader reader = LineReaderBuilder.builder()
					.terminal(terminal)
					.completer(new StringsCompleter("/help", "/history", "/cls", "#"))
					.build();

			String replCommand = String.join(" ", args);
			while (true) {
				String line = reader.readLine(String.format("%d > %s ", reader.getHistory().size() + 1, replCommand));
				if (line == null || line.equalsIgnoreCase("exit")) {
					break;
				}
				if (line.trim().isEmpty()) {
					continue;
				}
				if (line.trim().startsWith("#")) {
					reader.getHistory().add(line);
				} else if (line.trim().equalsIgnoreCase("/history")) {
					reader.getHistory().forEach(((History.Entry entry) -> {
						System.out.printf("%d %s%n", entry.index() + 1, entry.line());
					}));
				} else if (line.trim().equalsIgnoreCase("/help")) {
					System.out.printf(HELP_LINE, "/history", "show history");
					System.out.printf(HELP_LINE, "/help",  "this");
					System.out.printf(HELP_LINE, "/cls", "clear screens");
					System.out.printf(HELP_LINE, replCommand, "repl command");
				} else if (line.trim().equalsIgnoreCase("/cls")) {
					terminal.puts(InfoCmp.Capability.clear_screen);terminal.flush();
				} else {
					reader.getHistory().add(line);
					String commandLine = String.format("%s %s", replCommand, line);
					int exitCode = new ProcessBuilder(commandLine.split("\s+")).inheritIO().start().waitFor();
					if (exitCode != 0) {
						System.err.printf("%s exited with exit code: %d%n", commandLine, exitCode);
					}
				}
			}
	    };
	}

	public static void main(String[] args) {
		SpringApplication.run(ReplApplication.class, args);
	}

}
